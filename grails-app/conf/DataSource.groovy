dataSource {
    pooled = true
    driverClassName = "com.mysql.jdbc.Driver"
    username = "logh"
    password = "logh"
}
hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = false
    cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory'
}
// environment specific settings
environments {
    development {
        dataSource {
            dbCreate = "create-drop" // one of 'create', 'create-drop', 'update', 'validate', ''
            url = "jdbc:mysql://localhost:3306/logh_dev?autoreconnect=true"
        }
    }
    test {
        dataSource {
            dbCreate = "create-drop"
            url = "jdbc:mysql://localhost:3306/logh_dev?autoreconnect=true"
        }
    }
    production {
        dataSource {
            driverClassName = "com.cloudbees.jdbc.Driver"
            dbCreate = "create-drop"
            url = "jdbc:cloudbees://logh_dev"
            pooled = true
            properties {
               maxActive = -1
               minEvictableIdleTimeMillis=1800000
               timeBetweenEvictionRunsMillis=1800000
               numTestsPerEvictionRun=3
               testOnBorrow=true
               testWhileIdle=true
               testOnReturn=true
               validationQuery="SELECT 1"
            }
        }
    }
}
