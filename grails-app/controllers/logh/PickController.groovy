package logh

import org.springframework.dao.DataIntegrityViolationException

class PickController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def beforeInterceptor = [action: this.&init]

    Boolean isAdmin = false

    def init() {
        isAdmin = session?.user?.admin
        verifyPickDependencies()
    }

    def verifyPickDependencies() {
        if (isAdmin)
            return true
        def user = User.get(session.user.id)
        def coach = Coach.findByUser(user)
        if (!coach) {
            def newCoach = new Coach(user: user)
            newCoach.save()
        }
        def teams = Team.findAllByCoach(coach)
        if (teams.size() == 0) {
            flash.message = "You need to create a team first"
            redirect(controller: 'team', action: 'create')
            return false
        }
    }

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        if (isAdmin) {
            return [pickInstanceList: Pick.list(params), pickInstanceTotal: Pick.count()]
        }
        def coach = Coach.findByUser(User.get(session.user.id))
        def teams = Team.findAllByCoach(coach)
        def picks = Pick.findAllByTeamInList(teams, params)
        [pickInstanceList: picks, pickInstanceTotal: Pick.findAllByTeamInList(teams).size()]
    }

    def create() {
        def teams = (isAdmin) ? Team.list() : Team.findAllByCoach(Coach.findByUser(User.get(session.user.id)))

        [
                pickInstance: new Pick(params),
                pickTeams: teams,
                pickSquads: [],
                isAdmin: isAdmin
        ]
    }

    def save() {
        def pickInstance = new Pick(params)
        def teams = (isAdmin) ? Team.list() : Team.findAllByCoach(Coach.findByUser(User.get(session.user.id)))
        if (!pickInstance.save(flush: true)) {
            render(view: "create", model: [pickInstance: pickInstance, pickTeams: teams, pickSquads: (pickInstance?.week && pickInstance?.game) ? [Game.get(pickInstance.game.id).home, Game.get(pickInstance.game.id).visitor] : []])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'pick.label', default: 'Pick'), pickInstance.id])
        redirect(action: "show", id: pickInstance.id)
    }

    def show(Long id) {
        def pickInstance = Pick.get(id)
        if (!pickInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'pick.label', default: 'Pick'), id])
            redirect(action: "list")
            return
        }

        [pickInstance: pickInstance]
    }

    def edit(Long id) {
        def pickInstance = Pick.get(id)
        if (!pickInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'pick.label', default: 'Pick'), id])
            redirect(action: "list")
            return
        }
        def teams = (isAdmin) ? Team.list() : Team.findAllByCoach(Coach.findByUser(User.get(session.user.id)))

        [
                pickInstance: pickInstance,
                pickTeams: teams,
                pickSquads: (pickInstance?.week && pickInstance?.game) ? [Game.get(pickInstance.game.id).home, Game.get(pickInstance.game.id).visitor] : [],
                isAdmin: isAdmin
        ]
    }

    def update(Long id, Long version) {
        def pickInstance = Pick.get(id)
        if (!pickInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'pick.label', default: 'Pick'), id])
            redirect(action: "list")
            return
        }

        def teams = (isAdmin) ? Team.list() : Team.findAllByCoach(Coach.findByUser(User.get(session.user.id)))
        if (version != null) {
            if (pickInstance.version > version) {
                pickInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'pick.label', default: 'Pick')] as Object[],
                        "Another user has updated this Pick while you were editing")
                render(view: "edit", model: [pickInstance: pickInstance, pickTeams: teams, pickSquads: (pickInstance?.week && pickInstance?.game) ? [Game.get(pickInstance.game.id).home, Game.get(pickInstance.game.id).visitor] : []])
                return
            }
        }

        pickInstance.properties = params

        if (!pickInstance.save(flush: true)) {
            render(view: "edit", model: [pickInstance: pickInstance, pickTeams: teams, pickSquads: (pickInstance?.week && pickInstance?.game) ? [Game.get(pickInstance.game.id).home, Game.get(pickInstance.game.id).visitor] : []])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'pick.label', default: 'Pick'), pickInstance.id])
        redirect(action: "show", id: pickInstance.id)
    }

    def delete(Long id) {
        def pickInstance = Pick.get(id)
        if (!pickInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'pick.label', default: 'Pick'), id])
            redirect(action: "list")
            return
        }

        try {
            pickInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'pick.label', default: 'Pick'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'pick.label', default: 'Pick'), id])
            redirect(action: "show", id: id)
        }
    }
}
