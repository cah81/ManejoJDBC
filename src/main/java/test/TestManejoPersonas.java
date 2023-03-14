package test;

import Datos.PersonaDao;
import domain.Persona;

import java.sql.SQLException;
import java.util.List;

public class TestManejoPersonas {
    public static void main(String[] args)  {
        PersonaDao personaDao = new PersonaDao();
        List<Persona> personas = personaDao.seleccionar();
        for(Persona persona:personas){
            System.out.println(persona);
        }
        //usando una funcion lamda se obtiene el mismo resultado
        personas.forEach(persona -> {
            System.out.println("persona : " + persona);
        });

        //insertando un nuevo objeto  tipo persona

        Persona personaNueva = new Persona("luis","gonzalez","lgonzalez@gmail.com","624228177");
        personaDao.insertar(personaNueva);
        personas.forEach(persona -> {
            System.out.println("persona" + persona) ;
        });
        System.out.println("registros insertados " + personaDao.insertar(personaNueva));


        //actualizando objeto persona

        Persona personaModificar = new Persona(7, "Juan Carlos", "Esparza", "jcesparza@mail.com", "554456587");
        personaDao.actualizar(personaModificar);

        personas.forEach(persona -> {
            System.out.println("Nueva lista");
            System.out.println("Persona :  " + persona);
        });
        //eliminar objeto persona
        Persona personaEliminar = new Persona(26);
        personaDao.eliminar(personaEliminar);

        personas.forEach(persona -> {
            System.out.println("Nueva lista despues de eliminada");
            System.out.println("Persona :  " + persona);
        });

        //buscar Objeto persona por id
        Persona persona = new Persona(20);
        System.out.println("la persona con id es :" + personaDao.findById(persona));







    }




}
