package logh

class Pick {
    Game game
    Week week
    Squad loser
    Boolean correct

    static belongsTo = [team:Team]

    static constraints = {
        team()
        week(nullable: false)
        game(nullable: false)
        loser(nullable: false)
        correct()
    }

    String toString() {
        String string = ""
        if (week && game && loser) {
           string = "$game.week - $game (Loser: $loser.abbrev) - Correct: $correct"
        }
        return string
    }
}
