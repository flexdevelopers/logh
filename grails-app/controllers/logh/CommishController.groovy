package logh

import org.springframework.dao.DataIntegrityViolationException

class CommishController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [commishInstanceList: Commish.list(params), commishInstanceTotal: Commish.count()]
    }

    def create() {
        [commishInstance: new Commish(params)]
    }

    def save() {
        def commishInstance = new Commish(params)
        if (!commishInstance.save(flush: true)) {
            render(view: "create", model: [commishInstance: commishInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'commish.label', default: 'Commish'), commishInstance.id])
        redirect(action: "show", id: commishInstance.id)
    }

    def show(Long id) {
        def commishInstance = Commish.get(id)
        if (!commishInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'commish.label', default: 'Commish'), id])
            redirect(action: "list")
            return
        }

        [commishInstance: commishInstance]
    }

    def edit(Long id) {
        def commishInstance = Commish.get(id)
        if (!commishInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'commish.label', default: 'Commish'), id])
            redirect(action: "list")
            return
        }

        [commishInstance: commishInstance]
    }

    def update(Long id, Long version) {
        def commishInstance = Commish.get(id)
        if (!commishInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'commish.label', default: 'Commish'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (commishInstance.version > version) {
                commishInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'commish.label', default: 'Commish')] as Object[],
                        "Another user has updated this Commish while you were editing")
                render(view: "edit", model: [commishInstance: commishInstance])
                return
            }
        }

        commishInstance.properties = params

        if (!commishInstance.save(flush: true)) {
            render(view: "edit", model: [commishInstance: commishInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'commish.label', default: 'Commish'), commishInstance.id])
        redirect(action: "show", id: commishInstance.id)
    }

    def delete(Long id) {
        def commishInstance = Commish.get(id)
        if (!commishInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'commish.label', default: 'Commish'), id])
            redirect(action: "list")
            return
        }

        try {
            commishInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'commish.label', default: 'Commish'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'commish.label', default: 'Commish'), id])
            redirect(action: "show", id: id)
        }
    }
}
