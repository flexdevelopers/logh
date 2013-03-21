package logh



import org.junit.*
import grails.test.mixin.*

@TestFor(PickController)
@Mock(Pick)
class PickControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/pick/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.pickInstanceList.size() == 0
        assert model.pickInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.pickInstance != null
    }

    void testSave() {
        controller.save()

        assert model.pickInstance != null
        assert view == '/pick/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/pick/show/1'
        assert controller.flash.message != null
        assert Pick.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/pick/list'

        populateValidParams(params)
        def pick = new Pick(params)

        assert pick.save() != null

        params.id = pick.id

        def model = controller.show()

        assert model.pickInstance == pick
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/pick/list'

        populateValidParams(params)
        def pick = new Pick(params)

        assert pick.save() != null

        params.id = pick.id

        def model = controller.edit()

        assert model.pickInstance == pick
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/pick/list'

        response.reset()

        populateValidParams(params)
        def pick = new Pick(params)

        assert pick.save() != null

        // test invalid parameters in update
        params.id = pick.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/pick/edit"
        assert model.pickInstance != null

        pick.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/pick/show/$pick.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        pick.clearErrors()

        populateValidParams(params)
        params.id = pick.id
        params.version = -1
        controller.update()

        assert view == "/pick/edit"
        assert model.pickInstance != null
        assert model.pickInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/pick/list'

        response.reset()

        populateValidParams(params)
        def pick = new Pick(params)

        assert pick.save() != null
        assert Pick.count() == 1

        params.id = pick.id

        controller.delete()

        assert Pick.count() == 0
        assert Pick.get(pick.id) == null
        assert response.redirectedUrl == '/pick/list'
    }
}
