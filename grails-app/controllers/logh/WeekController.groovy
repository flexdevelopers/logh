package logh

import grails.converters.JSON
import org.springframework.dao.DataIntegrityViolationException

class WeekController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [weekInstanceList: Week.list(params), weekInstanceTotal: Week.count()]
    }

    def create() {
        [weekInstance: new Week(params)]
    }

    def save() {
        def weekInstance = new Week(params)
        if (!weekInstance.save(flush: true)) {
            render(view: "create", model: [weekInstance: weekInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'week.label', default: 'Week'), weekInstance.id])
        redirect(action: "show", id: weekInstance.id)
    }

    def show(Long id) {
        def weekInstance = Week.get(id)
        if (!weekInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'week.label', default: 'Week'), id])
            redirect(action: "list")
            return
        }

        [weekInstance: weekInstance]
    }

    def edit(Long id) {
        def weekInstance = Week.get(id)
        if (!weekInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'week.label', default: 'Week'), id])
            redirect(action: "list")
            return
        }

        [weekInstance: weekInstance]
    }

    def update(Long id, Long version) {
        def weekInstance = Week.get(id)
        if (!weekInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'week.label', default: 'Week'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (weekInstance.version > version) {
                weekInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'week.label', default: 'Week')] as Object[],
                        "Another user has updated this Week while you were editing")
                render(view: "edit", model: [weekInstance: weekInstance])
                return
            }
        }

        weekInstance.properties = params

        if (!weekInstance.save(flush: true)) {
            render(view: "edit", model: [weekInstance: weekInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'week.label', default: 'Week'), weekInstance.id])
        redirect(action: "show", id: weekInstance.id)
    }

    def delete(Long id) {
        def weekInstance = Week.get(id)
        if (!weekInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'week.label', default: 'Week'), id])
            redirect(action: "list")
            return
        }

        try {
            weekInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'week.label', default: 'Week'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'week.label', default: 'Week'), id])
            redirect(action: "show", id: id)
        }
    }

    def ajaxGetGames = {
        def list = []
        def week = Week.get(params.id)
        def games = week?.games
        games.each {
            list.add(['id': it.id, 'name': it.toString()])
        }
        render list as JSON
    }
}
