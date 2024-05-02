package org.dv.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    // Metodo para conectarse a la BD
    public static Connection getConexion(){

        Connection conexion = null;
        var baseDatos = "estudiantes";
        var url = "jdbc:mysql://localhost:3306/" + baseDatos;
        var usuario = "root";
        var password = "ds2v24";
        try {
            // Se usa para cargar la clase del driver de mysql
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(url, usuario, password);
        }catch (ClassNotFoundException | SQLException e){
            System.out.println("Ha ocurrido un error al intentar conectarse a la BD " + e.getMessage());
        }
        return conexion;
    }

    // Metodo para pruebar la conexion
    public static void main(String[] args) {

        var connection = Conexion.getConexion();
        if (connection != null){
            System.out.println("Conexion Exitosa!!! " + connection);
        }else {
            System.out.println("Ocurrio un error al conectarse =(");
        }
    }
}
