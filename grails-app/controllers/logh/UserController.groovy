package logh

import org.springframework.dao.DataIntegrityViolationException

class UserController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def beforeInterceptor = [action: this.&init]

    Boolean isAdmin = false

    def init() {
        isAdmin = session?.user?.admin
        return true
    }

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        if (isAdmin) {
            return [userInstanceList: User.list(params), userInstanceTotal: User.count()]
        }
        def users = [User.get(session.user.id)]
        [userInstanceList: users, userInstanceTotal: users.size()]
    }

    def search = {
        flash.message = "Search results for: ${params.q}"
        def resultsMap = User.search(params.q, params)
        render(view: 'list',
                model: [
                        userInstanceList: resultsMap.results,
                        userInstanceTotal: User.countHits(params.q)
                ]
        )
    }

    def create() {
        [userInstance: new User(params)]
    }

    def save() {
        def userInstance = new User(params)
        if (!userInstance.save(flush: true)) {
            render(view: "create", model: [userInstance: userInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'user.label', default: 'User'), userInstance.id])
        redirect(action: "show", id: userInstance.id)
    }

    def show(Long id) {
        def userInstance = User.get(id)
        if (!userInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), id])
            redirect(action: "list")
            return
        }

        [userInstance: userInstance]
    }

    def edit(Long id) {
        def userInstance = User.get(id)
        if (!userInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), id])
            redirect(action: "list")
            return
        }

        [userInstance: userInstance]
    }

    def update(Long id, Long version) {
        def userInstance = User.get(id)
        if (!userInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (userInstance.version > version) {
                userInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'user.label', default: 'User')] as Object[],
                        "Another user has updated this User while you were editing")
                render(view: "edit", model: [userInstance: userInstance])
                return
            }
        }

        if (params.password != userInstance.password)
        {
            params.password = params.password.encodeAsSHA();
        }

        userInstance.properties = params

        if (!userInstance.save(flush: true)) {
            render(view: "edit", model: [userInstance: userInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'user.label', default: 'User'), userInstance.id])
        redirect(action: "show", id: userInstance.id)
    }

    def delete(Long id) {
        def userInstance = User.get(id)
        if (!userInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), id])
            redirect(action: "list")
            return
        }

        try {
            userInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'user.label', default: 'User'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'user.label', default: 'User'), id])
            redirect(action: "show", id: id)
        }
    }

    def login = {

    }

    def logout = {
        flash.message = "Thank you, come again ${session.user.firstName} ${session.user.lastName}"
        session.user = null
        redirect(action: 'login')
    }

    def authenticate = {
        def user = User.findByUsernameAndPassword(params.username, params.password.encodeAsSHA())

        if (user) {
            session.user = user
            flash.message = "Hello ${user.firstName} ${user.lastName}"
            redirect(uri:'/')
        }
        else
        {
            flash.message = "Sorry, ${params.username}. Please try again."
            redirect(action: 'login')
        }
    }

    def unauthorized = {

    }
}
