package logh



import org.junit.*
import grails.test.mixin.*

@TestFor(WeekController)
@Mock(Week)
class WeekControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/week/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.weekInstanceList.size() == 0
        assert model.weekInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.weekInstance != null
    }

    void testSave() {
        controller.save()

        assert model.weekInstance != null
        assert view == '/week/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/week/show/1'
        assert controller.flash.message != null
        assert Week.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/week/list'

        populateValidParams(params)
        def week = new Week(params)

        assert week.save() != null

        params.id = week.id

        def model = controller.show()

        assert model.weekInstance == week
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/week/list'

        populateValidParams(params)
        def week = new Week(params)

        assert week.save() != null

        params.id = week.id

        def model = controller.edit()

        assert model.weekInstance == week
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/week/list'

        response.reset()

        populateValidParams(params)
        def week = new Week(params)

        assert week.save() != null

        // test invalid parameters in update
        params.id = week.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/week/edit"
        assert model.weekInstance != null

        week.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/week/show/$week.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        week.clearErrors()

        populateValidParams(params)
        params.id = week.id
        params.version = -1
        controller.update()

        assert view == "/week/edit"
        assert model.weekInstance != null
        assert model.weekInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/week/list'

        response.reset()

        populateValidParams(params)
        def week = new Week(params)

        assert week.save() != null
        assert Week.count() == 1

        params.id = week.id

        controller.delete()

        assert Week.count() == 0
        assert Week.get(week.id) == null
        assert response.redirectedUrl == '/week/list'
    }
}
