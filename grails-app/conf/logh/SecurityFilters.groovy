package logh

class SecurityFilters {
    def filters = {
        loginCheck(controller: 'coach|commish|game|league|pick|squad|team|week', action: '*') {
            before = {
                if (!session?.user) {
                    flash.message = "Please sign in to LOGH"
                    redirect(controller: 'user', action: 'login')
                    return false
                }
            }
            after = { Map model ->

            }
            afterView = { Exception e ->

            }
        }

        loginCheckUser(controller: 'user', action: '*', actionExclude: 'create|save|login|logout|authenticate') {
            before = {
                if (!session?.user) {
                    flash.message = "Please sign in to LOGH"
                    redirect(controller: 'user', action: 'login')
                    return false
                }
            }
            after = { Map model ->

            }
            afterView = { Exception e ->

            }
        }

        adminCheck(controller: 'game|squad|week', action: '*', actionExclude: 'ajax*', find: true) {
            before = {
                if (!session?.user?.admin) {
                    return false
                }
            }
            after = { Map model ->

            }
            afterView = { Exception e ->

            }
        }
    }
}
