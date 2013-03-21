package logh

class Team {
    String name
    String description
    Date dateCreated
    Date lastUpdated

    static belongsTo = [league:League, coach:Coach]

    static hasMany = [picks:Pick]

    static constraints = {
        name(blank: false, nullable: false, maxSize: 50)
        description(maxSize: 100)
        league(blank: false, nullable: false)
        coach(blank: false, nullable: false)
    }

    String toString() {
       return "$name"
    }
}
