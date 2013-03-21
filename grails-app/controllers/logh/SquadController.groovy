package logh

import org.springframework.dao.DataIntegrityViolationException

class SquadController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [squadInstanceList: Squad.list(params), squadInstanceTotal: Squad.count()]
    }

    def create() {
        [squadInstance: new Squad(params)]
    }

    def save() {
        def squadInstance = new Squad(params)
        if (!squadInstance.save(flush: true)) {
            render(view: "create", model: [squadInstance: squadInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'squad.label', default: 'Squad'), squadInstance.id])
        redirect(action: "show", id: squadInstance.id)
    }

    def show(Long id) {
        def squadInstance = Squad.get(id)
        if (!squadInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'squad.label', default: 'Squad'), id])
            redirect(action: "list")
            return
        }

        [squadInstance: squadInstance]
    }

    def edit(Long id) {
        def squadInstance = Squad.get(id)
        if (!squadInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'squad.label', default: 'Squad'), id])
            redirect(action: "list")
            return
        }

        [squadInstance: squadInstance]
    }

    def update(Long id, Long version) {
        def squadInstance = Squad.get(id)
        if (!squadInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'squad.label', default: 'Squad'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (squadInstance.version > version) {
                squadInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'squad.label', default: 'Squad')] as Object[],
                        "Another user has updated this Squad while you were editing")
                render(view: "edit", model: [squadInstance: squadInstance])
                return
            }
        }

        squadInstance.properties = params

        if (!squadInstance.save(flush: true)) {
            render(view: "edit", model: [squadInstance: squadInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'squad.label', default: 'Squad'), squadInstance.id])
        redirect(action: "show", id: squadInstance.id)
    }

    def delete(Long id) {
        def squadInstance = Squad.get(id)
        if (!squadInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'squad.label', default: 'Squad'), id])
            redirect(action: "list")
            return
        }

        try {
            squadInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'squad.label', default: 'Squad'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'squad.label', default: 'Squad'), id])
            redirect(action: "show", id: id)
        }
    }
}
