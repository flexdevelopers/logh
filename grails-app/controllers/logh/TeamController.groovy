package logh

import org.springframework.dao.DataIntegrityViolationException

class TeamController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def beforeInterceptor = [action: this.&init]

    Boolean isAdmin = false

    def init() {
        isAdmin = session?.user?.admin
        verifyTeamDependencies()
    }

    def verifyTeamDependencies() {
        def user = User.get(session.user.id)
        def coach = Coach.findByUser(user)
        if (!coach && !isAdmin) {
            def newCoach = new Coach(user: user)
            newCoach.save()
        }
    }

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        if (isAdmin) {
            return [teamInstanceList: Team.list(params), teamInstanceTotal: Team.count()]
        }
        def coach = Coach.findByUser(User.get(session.user.id))
        def teams = Team.findAllByCoach(coach, params)
        [teamInstanceList: teams, teamInstanceTotal: Team.findAllByCoach(coach).size()]
    }

    def create() {
        def coaches = isAdmin ? Coach.list() : Coach.findAllByUser(User.get(session.user.id))
        [teamInstance: new Team(params), coaches: coaches, checkLeaguePassword: true]
    }

    def save() {
        def teamInstance = new Team(params)
        def coaches = isAdmin ? Coach.list() : Coach.findAllByUser(User.get(session.user.id))
        if (teamInstance.validate()) {
            def league = League.findById(params.league.id)
            if (league.password && league.password != params.leaguePassword.encodeAsSHA()) {
                flash.message = "wrong password for the league, bud"
                render(view: 'create', model: [teamInstance: teamInstance, coaches: coaches, checkLeaguePassword: true])
                return
            } else {
                teamInstance.save(flush: true)
            }
        }
        else {
            render(view: "create", model: [teamInstance: teamInstance, coaches: coaches, checkLeaguePassword: true])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'team.label', default: 'Team'), teamInstance.id])
        redirect(action: "show", id: teamInstance.id)
    }

    def show(Long id) {
        def teamInstance = Team.get(id)
        if (!teamInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'team.label', default: 'Team'), id])
            redirect(action: "list")
            return
        }

        [teamInstance: teamInstance]
    }

    def edit(Long id) {
        def teamInstance = Team.get(id)
        if (!teamInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'team.label', default: 'Team'), id])
            redirect(action: "list")
            return
        }
        def coaches = isAdmin ? Coach.list() : Coach.findAllByUser(User.get(session.user.id))
        [teamInstance: teamInstance, coaches: coaches, checkLeaguePassword: false]
    }

    def update(Long id, Long version) {
        def teamInstance = Team.get(id)
        if (!teamInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'team.label', default: 'Team'), id])
            redirect(action: "list")
            return
        }
        def coaches = isAdmin ? Coach.list() : Coach.findAllByUser(User.get(session.user.id))
        if (version != null) {
            if (teamInstance.version > version) {
                teamInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'team.label', default: 'Team')] as Object[],
                        "Another user has updated this Team while you were editing")
                render(view: "edit", model: [teamInstance: teamInstance, coaches: coaches, checkLeaguePassword: false])
                return
            }
        }

        teamInstance.properties = params

        if (!teamInstance.save(flush: true)) {
            render(view: "edit", model: [teamInstance: teamInstance, coaches: coaches, checkLeaguePassword: false])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'team.label', default: 'Team'), teamInstance.id])
        redirect(action: "show", id: teamInstance.id)
    }

    def delete(Long id) {
        def teamInstance = Team.get(id)
        if (!teamInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'team.label', default: 'Team'), id])
            redirect(action: "list")
            return
        }

        try {
            teamInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'team.label', default: 'Team'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'team.label', default: 'Team'), id])
            redirect(action: "show", id: id)
        }
    }
}
