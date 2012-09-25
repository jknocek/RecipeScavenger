import org.codehaus.groovy.grails.orm.hibernate.cfg.GrailsAnnotationConfiguration

dataSource {
	dbCreate = "update" // one of 'create', 'create-drop','update'
	url = "jdbc:postgresql:recdb"
	driverClassName = "org.postgresql.Driver"
	dialect = 'com.e4net.hibernate.dialect.PostgreSQL82Dialect'
	logSql = true
	username = "rec"
	password = "rec"
}

hibernate {
    cache.use_second_level_cache = true
	cache.use_query_cache = true
	cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory'
	validator.apply_to_ddl = false
	validator.autoregister_listeners = false
}

environments {
	development {
		dataSource {
			dbCreate = "update" // one of 'create', 'create-drop','update'
			url = "jdbc:postgresql:recdb"
		}
	}
	test {
		dataSource {
			dbCreate = "update"
			url = "jdbc:postgresql:recdb"
		}
	}
	production {
		dataSource {
			dbCreate = "update"
			url = "jdbc:postgresql:recdb"
		}
	}
}