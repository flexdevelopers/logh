import grails.util.GrailsUtil
import logh.*

class BootStrap {

    def init = { servletContext ->
        switch (GrailsUtil.environment)
        {
            case "development":
            case "production":
                def jeremyUser = new User(firstName: 'Jeremy', lastName: 'Mitchell', email: 'mitch@gmail.com', username: 'x', password: 'x', role: 'admin')
                jeremyUser.save(failOnError: true)

                def toddUser = new User(firstName: 'Todd', lastName: 'Tobin', email: 'todd@gmail.com', username: 't', password: 't')
                toddUser.save(failOnError: true)

                def ryanUser = new User(firstName: 'Ryan', lastName: 'Goard', email: 'turtle@gmail.com', username: 'r', password: 'r')
                ryanUser.save(failOnError: true)

                def jeremyCoach = new Coach(user: jeremyUser)
                jeremyCoach.save(failOnError: true)

                def toddCoach = new Coach(user: toddUser)
                toddCoach.save(failOnError: true)

                def ryanCoach = new Coach(user: ryanUser)
                ryanCoach.save(failOnError: true)

                def jeremyCommish = new Commish(user: jeremyUser)
                jeremyCommish.save(failOnError: true)

                def toddCommish = new Commish(user: toddUser)
                toddCommish.save(failOnError: true)

                def rmlLeague = new League(name: 'RML', description: 'rocky mtn league', password: 'rml', commish: jeremyCommish)
                rmlLeague.save(failOnError: true)

                def jashonLeague = new League(name: 'Jashon', description: 'jashon league', password: 'jashon', commish: toddCommish)
                jashonLeague.save(failOnError: true)

                def dragonsTeam = new Team(name: 'fire-breathing dragons', description: 'dragons unite', league: rmlLeague, coach: jeremyCoach)
                dragonsTeam.save(failOnError: true)

                def oystersTeam = new Team(name: 'rocky mtn oysters', description: 'the team formerly known as rmo', league: rmlLeague, coach: toddCoach)
                oystersTeam.save(failOnError: true)

                def rhinosTeam = new Team(name: 'rhinos', description: 'don\'t mess with the rhinos', league: rmlLeague, coach: ryanCoach)
                rhinosTeam.save(failOnError: true)

                def duckiesTeam = new Team(name: 'rubber duckies', description: 'badass ducks', league: jashonLeague, coach: jeremyCoach)
                duckiesTeam.save(failOnError: true)

                def saladsTeam = new Team(name: 'big salads', description: 'are you happy, elaine?', league: jashonLeague, coach: toddCoach)
                saladsTeam.save(failOnError: true)

                def ari = new Squad(name: 'Arizona Cardinals', abbrev: 'ARI')
                ari.save(failOnError: true)

                def atl = new Squad(name: 'Atlanta Falcons', abbrev: 'ATL')
                ari.save(failOnError: true)

                def bal = new Squad(name: 'Baltimore Ravens', abbrev: 'BAL')
                bal.save(failOnError: true)

                def buf = new Squad(name: 'Buffalo Bills', abbrev: 'BUF')
                buf.save(failOnError: true)

                def car = new Squad(name: 'Carolina Panthers', abbrev: 'CAR')
                car.save(failOnError: true)

                def chi = new Squad(name: 'Chicago Bears', abbrev: 'CHI')
                chi.save(failOnError: true)

                def cin = new Squad(name: 'Cincinnati Bengals', abbrev: 'CIN')
                cin.save(failOnError: true)

                def cle = new Squad(name: 'Cleveland Browns', abbrev: 'CLE')
                cle.save(failOnError: true)

                def dal = new Squad(name: 'Dallas Cowboys', abbrev: 'DAL')
                dal.save(failOnError: true)

                def den = new Squad(name: 'Denver Broncos', abbrev: 'DEN')
                den.save(failOnError: true)

                def det = new Squad(name: 'Detroit Lions', abbrev: 'DET')
                det.save(failOnError: true)

                def gbp = new Squad(name: 'Green Bay Packers', abbrev: 'GBP')
                gbp.save(failOnError: true)

                def hou = new Squad(name: 'Houston Texans', abbrev: 'HOU')
                hou.save(failOnError: true)

                def ind = new Squad(name: 'Indianapolis Colts', abbrev: 'IND')
                ind.save(failOnError: true)

                def jac = new Squad(name: 'Jacksonville Jaguars', abbrev: 'JAC')
                jac.save(failOnError: true)

                def kc = new Squad(name: 'Kansas City Chiefs', abbrev: 'KC')
                kc.save(failOnError: true)

                def mia = new Squad(name: 'Miami Dolphins', abbrev: 'MIA')
                mia.save(failOnError: true)

                def min = new Squad(name: 'Minnesota Vikings', abbrev: 'MIN')
                min.save(failOnError: true)

                def ne = new Squad(name: 'New England Patriots', abbrev: 'NE')
                ne.save(failOnError: true)

                def no = new Squad(name: 'New Orleans Saints', abbrev: 'NO')
                no.save(failOnError: true)

                def nyg = new Squad(name: 'New York Giants', abbrev: 'NYG')
                nyg.save(failOnError: true)

                def nyj = new Squad(name: 'New York Jets', abbrev: 'NYJ')
                nyj.save(failOnError: true)

                def oak = new Squad(name: 'Oakland Raiders', abbrev: 'OAK')
                oak.save(failOnError: true)

                def phi = new Squad(name: 'Philadelphia Eagles', abbrev: 'PHI')
                phi.save(failOnError: true)

                def pit = new Squad(name: 'Pittsburgh Steelers', abbrev: 'PIT')
                pit.save(failOnError: true)

                def sd = new Squad(name: 'San Diego Chargers', abbrev: 'SD')
                sd.save(failOnError: true)

                def sea = new Squad(name: 'Seattle Seahawks', abbrev: 'SEA')
                sea.save(failOnError: true)

                def sf = new Squad(name: 'San Francisco 49ers', abbrev: 'SF')
                sf.save(failOnError: true)

                def stl = new Squad(name: 'St Louis Rams', abbrev: 'STL')
                stl.save(failOnError: true)

                def tb = new Squad(name: 'Tampa Bay Buccaneers', abbrev: 'TB')
                tb.save(failOnError: true)

                def ten = new Squad(name: 'Tennessee Titans', abbrev: 'TEN')
                ten.save(failOnError: true)

                def was = new Squad(name: 'Washington Redskins', abbrev: 'WAS')
                was.save(failOnError: true)

                def week1 = new Week(number: 1, name: 'Week 1', startDate: new Date(), endDate: new Date())
                week1.save(failOnError: true)

                def week2 = new Week(number: 2, name: 'Week 2', startDate: new Date(), endDate: new Date())
                week2.save(failOnError: true)

                def den_was_1_game = new Game(home: den, visitor: was, week: week1, startDate: new Date())
                den_was_1_game.save(failOnError: true)

                def ari_stl_1_game = new Game(home: ari, visitor: stl, week: week1, startDate: new Date())
                ari_stl_1_game.save(failOnError: true)

                def phi_dal_2_game = new Game(home: phi, visitor: dal, week: week2, startDate: new Date())
                phi_dal_2_game.save(failOnError: true)

                def sea_kc_1_game = new Game(home: sea, visitor: kc, week: week2, startDate: new Date())
                sea_kc_1_game.save(failOnError: true)

                def dragonsPick1 = new Pick(team: dragonsTeam, game: den_was_1_game, week: week1, loser: was, correct: true)
                dragonsPick1.save(failOnError: true)

                def dragonsPick2 = new Pick(team: dragonsTeam, game: phi_dal_2_game, week: week2, loser: dal, correct: false)
                dragonsPick2.save(failOnError: true)

                break
        }
    }

    def destroy = {
    }
}
