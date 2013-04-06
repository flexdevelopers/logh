1. clone repo (https://github.com/flexdevelopers/logh.git)
2. open project #1 and point to root (this is your grails app)
3. open project #2 and point to root/client-app/flex (this is your flex app)
4. In flex app, run 'Build Third Party Library RSL' ant target (then feel free to revert the changes made to flex/lib/cairngorm3)
5. In flex app, run 'Deploy All Development'
6. In grails app, hit run/debug