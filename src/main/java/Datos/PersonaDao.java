package Datos;

import domain.Persona;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonaDao {
    private static final String SQL_SELECT = "SELECT id_persona,nombre,apellido,email,telefono FROM persona";

    public List<Persona> seleccionar() throws SQLException {
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
            Conexion.close(rs);
            Conexion.close(instruccionSQL);
            Conexion.close(conn);

        }
            return  personas;
    }
}
