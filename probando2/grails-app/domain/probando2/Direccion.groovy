package probando2

class Direccion {
    String calle
    Integer numero
    String dpto
    Integer piso
    static belongsTo = [persona:Persona]

    static constraints = {
        calle(nullable:true)
        numero(nullable:true)
        dpto(nullable:true)
        piso(nullable:true)
    }
}
