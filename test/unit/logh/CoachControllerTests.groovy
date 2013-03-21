package logh



import org.junit.*
import grails.test.mixin.*

@TestFor(CoachController)
@Mock(Coach)
class CoachControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/coach/list" == response.redirectedUrl
    }
}
