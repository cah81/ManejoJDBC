package test;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestMySqlJDBC {
    public static void main(String[] args)  {

        //esta es la cadena de conexion
        //el useSSL = false se coloca para no trabajar con ssl
        String url = "jdbc:mysql://localhost:3306/test?useSSL=false&useTimezone=true&serverTimeZone=UTC&allowPublicKeyRetrieval=true";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            //para la conexion se proporciomna usuario y contraseña
            Connection conexion = DriverManager.getConnection(url,"root","admin");
            //del lado derecho estamos implementando un tipo interface
            //statement se usa para poder meter las instrucciones en sql
            Statement instruccionSQL = conexion.createStatement();
            var sql = "SELECT id_persona,nombre,apellido,email,telefono FROM persona";
            //esto ejecuta la query se usa el ResulSet , el statement con la conexion creada
            ResultSet resultado = instruccionSQL.executeQuery(sql);
            //como pueden a ver varios resultados iteramos la tabla
            //ingresa la primera vez y va haciendo el recorrido mientras hay registros
            while(resultado.next()){
                System.out.print("id_persona  " + resultado.getInt("id_persona"));
                System.out.print(" Nombre: " + resultado.getString("nombre"));
                System.out.print(" Apellido: " + resultado.getString("apellido"));
                System.out.print(" Email: " + resultado.getString("email"));
                System.out.print(" Telefono: " + resultado.getString("telefono"));
                System.out.println();
            }
            resultado.close();
            instruccionSQL.close();
            conexion.close();

        }catch (ClassNotFoundException ex){
           ex.printStackTrace(System.out);
        } catch (SQLException ex) {
            Logger.getLogger(TestMySqlJDBC.class.getName()).log(Level.SEVERE,null,ex);
            ex.printStackTrace(System.out); // muestra el error por la salida estandar
        }


    }
}
