package Datos;

import domain.Persona;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static Datos.Conexion.close;
import static Datos.Conexion.getConnection;

public class PersonaDao {
    private static final String SQL_SELECT = "SELECT id_persona,nombre,apellido,email,telefono FROM persona";

    private static final String QUERY = "SELECT id_persona,nombre,apellido,email,telefono FROM persona WHERE id_persona = ?";



    private static final String SQL_INSERT = "INSERT INTO persona(nombre, apellido, email, telefono) VALUES(?, ?, ?, ?)";

    private static final String SQL_UPDATE = "UPDATE persona SET nombre = ?, apellido = ?, email = ?, telefono = ? WHERE id_persona = ?";

    private static final String SQL_DELETE = "DELETE FROM persona where id_persona = ?";

    public List<Persona> seleccionar()  {
        //definimos variables que vamos a usar
        Connection conn = null;
        PreparedStatement instruccionSQL = null;
        ResultSet rs = null;
        Persona persona = null;
        List<Persona> personas = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            instruccionSQL = conn.prepareStatement(SQL_SELECT);
            rs = instruccionSQL.executeQuery();
            while(rs.next()){
                int idPersona = rs.getInt("id_persona");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String email = rs.getString("email");
                String telefono = rs.getString("telefono");
                persona = new Persona(idPersona,nombre,apellido,email,telefono);
                personas.add(persona);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally {
            try{
                close(rs);
                close(instruccionSQL);
                close(conn);
            }catch (SQLException ex){
                ex.printStackTrace();
            }
        }
            return  personas;
    }

    public int insertar(Persona persona){
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros =0;
        try {
            conn = Conexion.getConnection();//la conexion siempre va
            stmt = conn.prepareStatement(SQL_INSERT);//la query que se va a ejecutar
            stmt.setString(1, persona.getNombre());//los campos de la tabla que estan a modificar en la tabla
            stmt.setString(2, persona.getApellido());
            stmt.setString(3,persona.getEmail());
            stmt.setString(4, persona.getTelefono());
            registros = stmt.executeUpdate();//modifica estado de la base de datos se usa para insert update o  delete

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        finally{
            try {
                close(stmt);
                close(conn);
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }

        }
        return registros;
    }

    public int actualizar(Persona persona){
        Connection conn = null;
        PreparedStatement stmt = null;

        int registros = 0;
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE); //prepara la query a ejecutar

            stmt.setString(1, persona.getNombre());
            stmt.setString(2, persona.getApellido());
            stmt.setString(3, persona.getEmail());
            stmt.setString(4, persona.getTelefono());
            stmt.setInt(5, persona.getIdPersona());//en este caso el id_persona es tipo int
            registros = stmt.executeUpdate();//ejecucion de la query
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
            try {
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return registros;
    }

    public int eliminar(Persona persona){
        Connection conn = null;
        PreparedStatement stmt = null;

        int registros = 0;
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_DELETE); //prepara la query a ejecutar
            stmt.setInt(1, persona.getIdPersona());//en este caso el id_persona es tipo int


            registros = stmt.executeUpdate();//ejecucion de la query
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
            try {
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return registros;
    }



    public Persona findById(Persona persona)  {
        //definimos variables que vamos a usar
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ///ResultSet rs =null;

        Persona personaBuscada = null;

        try {
            conn = Conexion.getConnection();
            preparedStatement = conn.prepareStatement(QUERY);

            preparedStatement.setInt(1, persona.getIdPersona());
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id_persona");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String email = rs.getString("email");
                String telefono = rs.getString("telefono");
                System.out.println(id + "," + nombre + "," + apellido + "," + email + "," + telefono);
                personaBuscada = new Persona(id,nombre,apellido,email,telefono);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally {
            try{
                //close(rs);
               // close(rs);
                close(conn);
            }catch (SQLException ex){
                ex.printStackTrace();
            }
        }
        return personaBuscada;
    }



}
