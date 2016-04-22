import probando2.*

class BootStrap {

    def init = { servletContext ->
        print "Cargando datos..."





        //aca esta parte me parece que no funciona, ver como sigue la carga
        Persona persona1 = new Persona (nombre:'Alan', apellido: 'Estrada' , edad: 99 , email:'alan@mail.com', direccion:'casa13')
        //persona1.id=1
        persona1.save(flush:true)
        print "Persona1.id="+persona1.id

        Persona persona2 = new Persona(nombre:'Diego', apellido:'Maradona',edad:444, email:'mara@mail.com',direccion:'casa22')
        //persona2.id=2
        persona2.save(flush:true)
        print "Persona2.id="+persona2.id

        Persona persona3 = new Persona(nombre:'Nelson', apellido:'Mandela',edad:444, email:'mandela@mail.com',direccion:'casa23')
        //persona2.id=2
        persona3.save(flush:true)
        print "Persona2.id="+persona3.id
    }


    def destroy = {
    }
}
