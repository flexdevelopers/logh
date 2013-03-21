package logh

class Squad {

    String name
    String abbrev

    static constraints = {
        name(blank: false, maxSize: 30)
        abbrev(blank: false, unique: true)
    }

    String toString() {
        return "$name"
    }
}
