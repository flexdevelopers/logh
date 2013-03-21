package logh

class UserFilters {
    def filters = {
        userSearch(controller:'user', action:'search') {
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
        userCreate(controller:'user', action:'create') {
            before = {
                if (session?.user && !session?.user?.admin) {
                    flash.message = "You already have a user, bud"
                    redirect(action: 'list')
                    return false
                }
            }
            after = { Map model ->

            }
            afterView = { Exception e ->

            }
        }
        userDelete(controller:'user', action:'delete') {
            before = {
                if (!session?.user?.admin) {
                    flash.message = "Sorry, that function is reserved for admins only"
                    redirect(action: 'list')
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
