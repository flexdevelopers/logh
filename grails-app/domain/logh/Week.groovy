package logh

class Week {
    Integer number
    String name
    Date startDate
    Date endDate

    static hasMany = [games:Game]

    static constraints = {
        number(blank: false, min: 1)
        name(blank: false)
        startDate()
        endDate()
    }

    String toString() {
        return "$name"
    }
}
