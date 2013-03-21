package logh

class League {
    String name
    String description
    String password
    Boolean paid = false
    Date dateCreated
    Date lastUpdated

    static hasMany = [teams:Team]

    static belongsTo = [commish:Commish]

    static constraints = {
        name(blank: false, unique: true, maxSize: 100)
        description(maxSize: 100)
        password(blank: true, nullable: true, password: true)
        paid()
    }

    def beforeInsert = {
        password = password?.encodeAsSHA() ?: password
    }

    String toString() {
        return "$name"
    }
}
