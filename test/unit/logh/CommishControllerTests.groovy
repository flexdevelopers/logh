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
}
