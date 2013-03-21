package logh



import org.junit.*
import grails.test.mixin.*

@TestFor(SquadController)
@Mock(Squad)
class SquadControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/squad/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.squadInstanceList.size() == 0
        assert model.squadInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.squadInstance != null
    }

    void testSave() {
        controller.save()

        assert model.squadInstance != null
        assert view == '/squad/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/squad/show/1'
        assert controller.flash.message != null
        assert Squad.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/squad/list'

        populateValidParams(params)
        def squad = new Squad(params)

        assert squad.save() != null

        params.id = squad.id

        def model = controller.show()

        assert model.squadInstance == squad
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/squad/list'

        populateValidParams(params)
        def squad = new Squad(params)

        assert squad.save() != null

        params.id = squad.id

        def model = controller.edit()

        assert model.squadInstance == squad
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/squad/list'

        response.reset()

        populateValidParams(params)
        def squad = new Squad(params)

        assert squad.save() != null

        // test invalid parameters in update
        params.id = squad.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/squad/edit"
        assert model.squadInstance != null

        squad.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/squad/show/$squad.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        squad.clearErrors()

        populateValidParams(params)
        params.id = squad.id
        params.version = -1
        controller.update()

        assert view == "/squad/edit"
        assert model.squadInstance != null
        assert model.squadInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/squad/list'

        response.reset()

        populateValidParams(params)
        def squad = new Squad(params)

        assert squad.save() != null
        assert Squad.count() == 1

        params.id = squad.id

        controller.delete()

        assert Squad.count() == 0
        assert Squad.get(squad.id) == null
        assert response.redirectedUrl == '/squad/list'
    }
}
