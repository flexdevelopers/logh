package logh

import org.springframework.dao.DataIntegrityViolationException

class PickController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [pickInstanceList: Pick.list(params), pickInstanceTotal: Pick.count()]
    }

    def create() {
        [pickInstance: new Pick(params)]
    }

    def save() {
        def pickInstance = new Pick(params)
        if (!pickInstance.save(flush: true)) {
            render(view: "create", model: [pickInstance: pickInstance])
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

        [pickInstance: pickInstance]
    }

    def update(Long id, Long version) {
        def pickInstance = Pick.get(id)
        if (!pickInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'pick.label', default: 'Pick'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (pickInstance.version > version) {
                pickInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'pick.label', default: 'Pick')] as Object[],
                        "Another user has updated this Pick while you were editing")
                render(view: "edit", model: [pickInstance: pickInstance])
                return
            }
        }

        pickInstance.properties = params

        if (!pickInstance.save(flush: true)) {
            render(view: "edit", model: [pickInstance: pickInstance])
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
