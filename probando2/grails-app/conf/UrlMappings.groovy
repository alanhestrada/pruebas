class UrlMappings {

	static mappings = {

        "/persona/$id?"(controller: "persona", parseRequest:true){
            action = [GET:"getPersona", DELETE:"deletePersona" , POST:"postPersona"]
            constraints={
            }
        }

        "/persona/$id/direccion/$idDir"(controller: "persona", parseRequest:true){
            action = [DELETE:"deleteDireccion" ]
            constraints={
            }
        }


        "/"(view:"/index")
        "500"(view:'/error')
	}
}
