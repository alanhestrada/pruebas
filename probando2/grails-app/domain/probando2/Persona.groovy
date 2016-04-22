package probando2

class Persona {
    String nombre
    String apellido
    Integer edad
    String email
    String direccion
    //static hasMany = [direcciones:Direccion]


    static constraints = {
        nombre(nullable:true)
        apellido(nullable:true)
        edad(nullable:true)
        email(nullable:true)
        direccion(nullable:true)
    }
}
