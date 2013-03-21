package logh



import org.junit.*
import grails.test.mixin.*

@TestFor(CommishController)
@Mock(Commish)
class CommishControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/commish/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.commishInstanceList.size() == 0
        assert model.commishInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.commishInstance != null
    }

    void testSave() {
        controller.save()

        assert model.commishInstance != null
        assert view == '/commish/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/commish/show/1'
        assert controller.flash.message != null
        assert Commish.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/commish/list'

        populateValidParams(params)
        def commish = new Commish(params)

        assert commish.save() != null

        params.id = commish.id

        def model = controller.show()

        assert model.commishInstance == commish
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/commish/list'

        populateValidParams(params)
        def commish = new Commish(params)

        assert commish.save() != null

        params.id = commish.id

        def model = controller.edit()

        assert model.commishInstance == commish
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/commish/list'

        response.reset()

        populateValidParams(params)
        def commish = new Commish(params)

        assert commish.save() != null

        // test invalid parameters in update
        params.id = commish.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/commish/edit"
        assert model.commishInstance != null

        commish.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/commish/show/$commish.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        commish.clearErrors()

        populateValidParams(params)
        params.id = commish.id
        params.version = -1
        controller.update()

        assert view == "/commish/edit"
        assert model.commishInstance != null
        assert model.commishInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/commish/list'

        response.reset()

        populateValidParams(params)
        def commish = new Commish(params)

        assert commish.save() != null
        assert Commish.count() == 1

        params.id = commish.id

        controller.delete()

        assert Commish.count() == 0
        assert Commish.get(commish.id) == null
        assert response.redirectedUrl == '/commish/list'
    }
}
