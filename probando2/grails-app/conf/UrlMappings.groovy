class UrlMappings {

	static mappings = {

        "/persona/$id?"(controller: "persona", parseRequest:true){
            action = [GET:"getPersona", DELETE:"deletePersona" , POST:"postPersona"]
            constraints={
            }
        }


        "/"(view:"/index")
        "500"(view:'/error')
	}
}
