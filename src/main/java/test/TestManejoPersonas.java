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


    }
}
