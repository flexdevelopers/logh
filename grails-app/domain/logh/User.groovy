package logh

class User implements Serializable {
    String firstName
    String lastName
    String email
    String username
    String password
    String role = "user"

    static searchable = {
        only: ["firstName", "lastName"]
    }

    static constraints = {
        firstName(blank: false, nullable: false, maxSize: 20)
        lastName(blank: false, nullable: false, maxSize: 20)
        email(blank: false, nullable: false, email: true, unique: true)
        username(blank: false, nullable: false, unique: true, maxSize: 20)
        password(blank: false, nullable: false, password: true)
        role(blank: false, nullable: false, inList: ['admin', 'user'])
    }

    static transients = ['admin']

    boolean isAdmin() {
        return role == "admin"
    }

    def beforeInsert = {
        password = password.encodeAsSHA()
    }

    String toString() {
        return "$firstName $lastName"
    }
}
