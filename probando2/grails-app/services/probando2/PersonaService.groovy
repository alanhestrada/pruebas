package probando2


class PersonaService {

    def getPersona(Map data) {
        def persona = Persona.get(data.id)

        print "Get de la persona: ${persona?.properties} "

        if (persona) {
            return datosDeSalidaPersona(persona) // ver esto que devuelvo
        } else {
            return null
        }

    }

    def deletePersona(Map data) {
        def persona = Persona.get(data.id)

        if (persona) {
            persona.delete(flush: true)
            print "Se a borrado la persona ${data.id}"
            print "Persona Borrada: ${persona}"
        } else {
            return null
        }
    }

    def postPersona(Map data) {

            Persona persona = new Persona(
                    nombre: data.nombre,
                    apellido: data.apellido,
                    edad: data.edad,
                    email: data.email,
                    direccion: data.direccion
            )
        persona.save(flush: true)

        if (persona) {
            print "Se ha agregado una persona"
            if(data.containsKey("email") ){
                return [
                        nombre   : persona.nombre,
                        apellido : persona.apellido,
                        edad     : persona.edad,
                        email    : persona.email,
                        direccion: persona.direccion
                ]
            }
            else  {
                return [
                        nombre   : persona.nombre,
                        apellido : persona.apellido,
                        edad     : persona.edad,

                        direccion: persona.direccion
                ]
            }
        }
        else{
            return null
        }


    }

    Map<String, Object> datosValidosGetPersona(Map<String, Object> data) {
        def datosValidosPersona = [:]
        datosValidosPersona.id = data.id as Integer

        print "Se obtuvo el mapa validado ${datosValidosPersona}"
        return datosValidosPersona
    }

    Map<String, Object> datosValidosDeletePersona(Map<String, Object> data) {
        def datosValidosPersona = [:]
        datosValidosPersona.id = data.id as Integer

        print "Se obtuvo el mapa validado ${datosValidosPersona}"
        return datosValidosPersona
    }

    Map<String, Object> datosValidosPostPersona(Map<String, Object> data) {
        def datosValidosPersona = [:]
        datosValidosPersona.nombre = data.nombre as String
        datosValidosPersona.apellido = data.apellido as String
        datosValidosPersona.edad = data.edad as Integer
        if (data.containsKey("email"))
            datosValidosPersona.email = data.email as String
        datosValidosPersona.direccion = data.direccion as String

        print "Se obtuvo el mapa validado"
        return datosValidosPersona
    }

    Map<String, Object> datosDeSalidaPersona(Persona data) {
        def datosDeSalidaPersona = [:]

        datosDeSalidaPersona.id = data.id
        datosDeSalidaPersona.nombre = data.nombre
        datosDeSalidaPersona.apellido = data.apellido
        datosDeSalidaPersona.edad = data.edad
        if (!(data.email == null))
         {datosDeSalidaPersona.email = data.email}
        datosDeSalidaPersona.direccion = data.direccion

        return datosDeSalidaPersona
    }

    boolean validacionGetPersona(Map<String, Object> data) {
        print "Entro a la validacion"

        try {
            Integer id = data.id as Integer
            if (id < 1) {
                print "No es mayor o igual a 1: ${id}"
                return false
            }
        } catch (Exception e) {
            print "No es numerico"
            return false
        }

        print "Paso las validaciones"

        return true
    }

    boolean validacionDeletePersona(Map<String, Object> data) {
        print "Entro a la validacion"

        try {
            Integer id = data.id as Integer
            if (id < 1) {
                print "No es mayor o igual a 1: ${id}"
                return false
            }
        } catch (Exception e) {
            print "No es numerico"
            return false
        }

        print "Paso las validaciones"

        return true
    }

    boolean validacionPostPersona(Map<String, Object> json) {
        print "Entro a la validacion de persona"

        if (!(json.nombre instanceof String)) {
            print "El no nombre no es un string"
            return false
        }
        if (!(json.apellido instanceof String)) {
            print "El apellido no es un string"
            return false
        }
        if (!(json.edad instanceof Integer)) {
            print "La edad no es un entero"
            return false
        } else{
          if(
          json.edad <= 0){
              print "La edad no puede ser menor o igual que 0"
              return false
          }
      }
      if (json.containsKey("email")){
          if(!(json.email instanceof String)){
              print "El email no es un string"
              return false
          }
      }
      if (!(json.direccion instanceof String)){
          print "La direccion no es un string"
          return false
      }
      return true
    }
}