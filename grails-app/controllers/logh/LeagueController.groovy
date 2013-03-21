package logh

import org.springframework.dao.DataIntegrityViolationException

class LeagueController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def beforeInterceptor = [action: this.&init]

    Boolean isAdmin = false

    def init() {
        isAdmin = session?.user?.admin
        verifyLeagueDependencies()
    }

    def verifyLeagueDependencies() {
        def user = User.get(session.user.id)
        def commish = Commish.findByUser(user)
        if (!commish && !isAdmin) {
            def newCommish = new Commish(user: user)
            newCommish.save()
        }
    }

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        if (isAdmin) {
            return [leagueInstanceList: League.list(params), leagueInstanceTotal: League.count()]
        }
        def commish = Commish.findByUser(User.get(session.user.id))
        def leagues = League.findAllByCommish(commish, params)
        [leagueInstanceList: leagues, leagueInstanceTotal: League.findAllByCommish(commish).size()]
    }

    def create() {
        def commishes = isAdmin ? Commish.list() : Commish.findAllByUser(User.get(session.user.id))

        [
                leagueInstance: new League(params),
                commishes: commishes
        ]
    }

    def save() {
        def leagueInstance = new League(params)
        def commishes = isAdmin ? Commish.list() : Commish.findAllByUser(User.get(session.user.id))
        if (!leagueInstance.save(flush: true)) {
            render(view: "create", model: [leagueInstance: leagueInstance, commishes: commishes])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'league.label', default: 'League'), leagueInstance.id])
        redirect(action: "show", id: leagueInstance.id)
    }

    def show(Long id) {
        def leagueInstance = League.get(id)
        if (!leagueInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'league.label', default: 'League'), id])
            redirect(action: "list")
            return
        }

        [leagueInstance: leagueInstance]
    }

    def edit(Long id) {
        def leagueInstance = League.get(id)
        if (!leagueInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'league.label', default: 'League'), id])
            redirect(action: "list")
            return
        }

        def commishes = isAdmin ? Commish.list() : Commish.findAllByUser(User.get(session.user.id))

        [
                leagueInstance: leagueInstance,
                commishes: commishes
        ]
    }

    def update(Long id, Long version) {
        def leagueInstance = League.get(id)
        if (!leagueInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'league.label', default: 'League'), id])
            redirect(action: "list")
            return
        }

        def commishes = isAdmin ? Commish.list() : Commish.findAllByUser(User.get(session.user.id))
        if (version != null) {
            if (leagueInstance.version > version) {
                leagueInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'league.label', default: 'League')] as Object[],
                          "Another user has updated this League while you were editing")
                render(view: "edit", model: [leagueInstance: leagueInstance, commishes: commishes])
                return
            }
        }

        if (params.password != leagueInstance.password)
        {
            params.password = params.password.encodeAsSHA();
        }

        leagueInstance.properties = params

        if (!leagueInstance.save(flush: true)) {
            render(view: "edit", model: [leagueInstance: leagueInstance, commishes: commishes])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'league.label', default: 'League'), leagueInstance.id])
        redirect(action: "show", id: leagueInstance.id)
    }

    def delete(Long id) {
        def leagueInstance = League.get(id)
        if (!leagueInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'league.label', default: 'League'), id])
            redirect(action: "list")
            return
        }

        try {
            leagueInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'league.label', default: 'League'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'league.label', default: 'League'), id])
            redirect(action: "show", id: id)
        }
    }
}
