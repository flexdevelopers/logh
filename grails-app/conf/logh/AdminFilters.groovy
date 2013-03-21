package logh

class AdminFilters {
    def filters = {
        adminCheck(controller: 'coach|commish|game|squad|week', action: '*', actionExclude: 'ajax*', find: true) {
            before = {
                if (!session?.user?.admin) {
                    redirect(controller: 'user', action: 'unauthorized')
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
