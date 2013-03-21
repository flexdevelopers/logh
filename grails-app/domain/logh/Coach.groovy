package logh

class Coach {

    static belongsTo = [user:User]

    static hasMany = [teams:Team]

    static constraints = {
        user(unique: true)
    }

    String toString() {
        return "Coach $user.lastName"
    }
}
