import logh.League

/**
 * Created with IntelliJ IDEA.
 * User: neo
 * Date: 2/21/13
 * Time: 3:28 PM
 * To change this template use File | Settings | File Templates.
 */
class LeagueIntegrationTests extends GroovyTestCase {
    void testStuff() {
        def league = new League()
        assertFalse("Validation should fail", league.validate())
        assertTrue("There should be errors", league.hasErrors())

        println "\nErrors:"
        println league.errors ?: "No errors found"

        assertNotNull("Expecting to find an error for numberOfTeams", badField)
    }
}
