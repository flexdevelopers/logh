package logh

class Commish {

    static belongsTo = [user:User]

    static hasMany = [leagues:League]

    static constraints = {
        user(unique: true)
    }

    String toString() {
        return "Commish $user.lastName"
    }
}
