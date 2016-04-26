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
            print "Se a borrado la persona ${data.id}"
            print "Persona Borrada: ${persona}"
            persona.delete(flush: true)
            return true
        } else {
            return false
        }
    }


    def deleteDireccion(Map data) {
        def persona = Persona.get(data.id)
        def direccion = Direccion.get(data.idDir)

        if (persona) {
            print "Se a borrado direccion ${data.idDir} de la persona ${data.id}"
            direccion.delete(flush: true)
            return true
        } else {
            return false
        }
    }

    def postPersona(Map data) {
        print "POST PERSONA : "
        Persona persona1 = new Persona()
        persona1.nombre= data.nombre
        persona1.apellido= data.apellido
        persona1.edad= data.edad
        persona1.email=data.email
        persona1.save(flush: true)


         print "direcciones post"+ data.direcciones
        List<Direccion> listDir = []
        data.direcciones.each{
            Direccion dir = new Direccion()
            dir.calle= it.calle
            dir.numero= it.numero
            dir.dpto=it.dpto
            dir.piso=it.piso
            dir.persona= persona1
            dir.save(flush: true)
            listDir.push(dir)
        }

        persona1.direcciones=listDir
        print "las persona" +persona1.properties
        if (persona1) {
            return datosDeSalidaPersona(persona1)
        } else {
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

    Map<String, Object> datosValidosDeleteDireccion(Map<String, Object> data) {
        def datosValidosPersona = [:]
        datosValidosPersona.id = data.id as Integer
        datosValidosPersona.idDir = data.idDir as Integer

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
        datosValidosPersona.direcciones = data.direcciones

        print "Se obtuvo el mapa validado"
        return datosValidosPersona
    }


    Map<String, Object> datosDeSalidaPersona(Persona persona) {
        def datosDeSalidaPersona = [:]

        datosDeSalidaPersona.id = persona.id
        datosDeSalidaPersona.nombre = persona.nombre
        datosDeSalidaPersona.apellido = persona.apellido
        datosDeSalidaPersona.edad = persona.edad

        if (!(persona.email == null)) {
            datosDeSalidaPersona.email = persona.email
        }
        datosDeSalidaPersona.direcciones = []
        persona.direcciones.each {
                def dir = [:]
                dir.id = it.id
                dir.calle = it.calle
                dir.numero = it.numero
                if (!(it.dpto == null)) {
                    dir.dpto = it.dpto
                }
                if (!(it.piso == null)) {
                    dir.piso = it.piso
                }
                datosDeSalidaPersona.direcciones.push(dir)
            }

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

    boolean validacionDeleteDireccion(Map<String, Object> data) {
        print "Entro a la validacion"

        try {
            Integer id = data.id as Integer
            if (id < 1) {
                print "El id Persona No es mayor o igual a 1: ${id}"
                return false
            }
        } catch (Exception e) {
            print "No es numerico"
            return false
        }
        try {
            Integer idDir = data.idDir as Integer
            if (idDir< 1) {
                print "El id Direccion No es mayor o igual a 1: ${idDir}"
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
        } else {
            if (json.edad <= 0) {
                print "La edad no puede ser menor o igual que 0"
                return false
            }
        }
        if (json.containsKey("email")) {
            if (!(json.email instanceof String)) {
                print "El email no es un string"
                return false
            }
        }
     if(json.containsKey("direcciones")) {
         json.direcciones.each {
             if (!(it.calle instanceof String)) {
                 print "La calle no es un string"
                 return false
             }
             if (!(it.numero instanceof Integer)) {
                 print "El numero no es un integer"
                 return false
             }
             if (it.numero < 0) {
                 print "El numero es negativo"
                 return false
             }
             if (!(it.dpto instanceof String)) {
                 print "El dpto no es un string"
                 return false
             }
             if (!(it.piso instanceof Integer)) {
                 print "El piso no es un entero"
                 return false
             }
         }
     }
        return true
    }
}