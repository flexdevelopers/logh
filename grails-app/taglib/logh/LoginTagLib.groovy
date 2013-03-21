package logh

class LoginTagLib {
    def loginControl = {
        if (request.getSession(false) && session.user) {
            out << "Hello ${session.user.toString()} "
            out << "[${link(controller: 'user', action: 'logout') {'Sign Out'}}]"
        }
        else {
            out << "[${link(controller: 'user', action: 'login') {'Sign In'}}] [${link(controller: 'user', action: 'create') {'Create User'}}]"
        }
    }
}
