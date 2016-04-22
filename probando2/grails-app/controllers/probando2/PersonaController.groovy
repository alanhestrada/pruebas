package probando2

import grails.converters.JSON

class PersonaController {

    PersonaService personaService



    def getPersona() {

        println "==========    [INICIA_GET_DE_PERSONA]    =========="
        Map<String, Object> queryStrings = getParams() //QueryParams

        print "queryStrings: " + queryStrings.id

        Map resp
        if (personaService.validacionGetPersona(queryStrings)) {
            def data = personaService.datosValidosGetPersona(queryStrings)   //paso los datos formateados
            Map persona = personaService.getPersona(data)
            if (persona) {
                resp = [response: persona, status: 200]
            }else{
                resp = [response: ["message": "No existe la persona"], status: 404]
            }
        }else
            resp = [response: ["message": "Los datos ingresados no son correctos"], status:400]

        print "Mapa Final: "+resp

        render resp as JSON
    }

    def deletePersona() {
        println "==========    [INICIA_DELETE_DE_PERSONA]    =========="
        Map<String, Object> queryStrings = getParams() //QueryParams

        print "queryStrings: " + queryStrings.id

        Map resp
        if (personaService.validacionDeletePersona(queryStrings)) {
            def data = personaService.datosValidosDeletePersona(queryStrings)   //paso los datos formateados
            Map persona = personaService.deletePersona(data)
            if (persona) {
                resp = [response: persona, status: 200]
            }else{
                resp = [response: ["message": "No existe la persona a borrar"], status: 404]
            }
        }else
            resp = [response: ["message": "Los datos ingresados son incorrectos"], status:400]

        print "Mapa Final: "+resp

        render resp as JSON
    }

    def postPersona(){
        println "==========    [INICIA_POST_DE_PERSONA]    =========="
        Map json = request.getJSON()
        Map queryStrings = getParams()

        Map resp
        if (personaService.validacionPostPersona(json))
        {
             def data = personaService.datosValidosPostPersona(json)
             Map persona = personaService.postPersona(data)
             if(persona){
                 resp = [response: persona, status: 200]
             }else{
                 resp = [response: ["message": "No puede realizar el post"], status: 404]
             }
        }else
            resp = [response: ["message": "Los datos ingresados son incorrectos"], status:400]

        print "Mapa Final: "+resp

        render resp as JSON
    }

}
