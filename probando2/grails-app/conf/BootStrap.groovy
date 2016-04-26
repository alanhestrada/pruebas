import probando2.*

class BootStrap {

    def init = { servletContext ->
        print "Cargando datos..."

        Persona persona1 = new Persona (nombre:'Pibe', apellido: 'Valderrama' , edad: 99 , email:'pibe@mail.com')
        persona1.save(flush: true)
        Direccion dir1 = new Direccion (persona: persona1,calle: 'Mitre', numero: 999, dpto:'A', piso:3)
        dir1.persona=persona1
        dir1.save(flush: true)

        Persona persona2 = new Persona (nombre:'Nelson', apellido: 'Mandela' , edad: 102 , email:'mandela@mail.com')
        persona2.save(flush: true)
        Direccion dir3 = new Direccion (persona: persona1,calle:'España', numero:34, dpto:'H', piso:5)
        dir3.persona=persona2
        dir3.save(flush: true)

        Persona persona3 = new Persona(nombre:'Diego', apellido:'Maradona',edad: 444, email:'mandela@mail.com')
        persona3.save(flush: true)
        Direccion dir4 = new Direccion(persona:persona3, calle:'Chacabuco', numero:21,dpto:'F',piso:7)
        dir4.persona=persona3
        dir4.save(flush: true)
        Direccion dir2 = new Direccion (persona: persona1,calle: 'Sarmiento', numero: 44, dpto:'C', piso:8)
        dir2.persona=persona3
        dir2.save(flush: true)

        print "terminada la carga de datos..."

    }


    def destroy = {
    }
}