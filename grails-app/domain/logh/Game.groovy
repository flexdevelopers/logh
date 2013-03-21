package logh

class Game {
    Squad home
    Squad visitor
    Squad loser
    Date startDate

    static belongsTo = [week: Week]

    static constraints = {
        home(blank: false)
        visitor(blank: false)
        loser(blank: false, nullable: true)
        week(blank: false)
        startDate()
    }

    String toString() {
        return "$visitor.abbrev at $home.abbrev ($week.name)"
    }
}
