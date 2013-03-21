package logh



import org.junit.*
import grails.test.mixin.*

@TestFor(TeamController)
@Mock(Team)
class TeamControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/team/list" == response.redirectedUrl
    }
}
