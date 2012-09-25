class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

		"/"(controller:'Home', action:"/home")
		
		"500"(view:'/error')
	}
}
