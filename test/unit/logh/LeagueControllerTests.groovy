package logh



import org.junit.*
import grails.test.mixin.*

@TestFor(LeagueController)
@Mock(League)
class LeagueControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/league/list" == response.redirectedUrl
    }
}
