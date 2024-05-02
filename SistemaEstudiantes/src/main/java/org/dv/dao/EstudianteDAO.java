package org.dv.dao;

import org.dv.conexion.Conexion;
import org.dv.modelo.Estudiante;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

// Patron de diseño DAO
// DAO - Data Access Object
public class EstudianteDAO {

    // Metodo para listar los estudiantes
    public List<Estudiante> listarEstudiantes(){
        List<Estudiante> estudiantes = new ArrayList<>();
        //Prepara la sentencia SQL que se ejecuta hacia la BD
        PreparedStatement ps;
        ResultSet rs;
        Connection conx = Conexion.getConexion();
        String sql = "SELECT * FROM estudiante ORDER BY idEstudiante";
        try {
            ps = conx.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                var estudiante = new Estudiante();
                estudiante.setIdEstudiante(rs.getInt("idEstudiante"));
                estudiante.setNombre(rs.getString("nombre"));
                estudiante.setApellido(rs.getString("apellido"));
                estudiante.setTelefono(rs.getString("telefono"));
                estudiante.setEmail(rs.getString("email"));
                estudiantes.add(estudiante);
            }
        }catch (Exception e){
            System.out.println("Ocurrio un error al consultar datos " + e.getMessage());
        }
        finally {
            try {
                conx.close();
            } catch (Exception e) {
                System.out.println("Ocurrio un error al cerrar la conexion de la BD " + e.getMessage());
            }
        }
        return estudiantes;
    }

    // Metodo para buscar un estudiante por ID
    public boolean buscarEstudianteId(Estudiante estudiante){

        Connection conx = Conexion.getConexion();
        PreparedStatement ps;
        ResultSet rs;
        String sql = "SELECT * FROM estudiante WHERE idEstudiante = ?";
        try {
            ps = conx.prepareStatement(sql);
            ps.setInt(1, estudiante.getIdEstudiante());
            rs = ps.executeQuery();
            if (rs.next()){
                estudiante.setNombre(rs.getString("nombre"));
                estudiante.setApellido(rs.getString("apellido"));
                estudiante.setTelefono(rs.getString("telefono"));
                estudiante.setEmail(rs.getString("email"));

                return true;
            }
        }catch (Exception e){
            System.out.println("Ocurrio un error al buscar estudiante por ID " + e.getMessage());
        }
        finally {
            try {
                conx.close();
            } catch (Exception e) {
                System.out.println("Ocurrio un error al cerrar la conexion de la BD " + e.getMessage());
            }
        }
        return false;
    }

    // Metodo para agregar un nuevo estudiante
    public boolean agregarEstudiante(Estudiante estudiante){

        Connection conx = Conexion.getConexion();
        PreparedStatement ps;
        String sql = "INSERT INTO estudiante(nombre, apellido, telefono, email)" +
                "VALUES(?, ?, ?, ?)";
        try {
            ps = conx.prepareStatement(sql);
            ps.setString(1, estudiante.getNombre());
            ps.setString(2, estudiante.getApellido());
            ps.setString(3, estudiante.getTelefono());
            ps.setString(4, estudiante.getEmail());
            ps.execute();

            return true;

        }catch (Exception e){
            System.out.println("Ocurrio un error al insertar en la BD" + e.getMessage());
        }
        finally {
            try {
                conx.close();
            } catch (Exception e) {
                System.out.println("Ocurrio un error al cerrar la conexion de la BD " + e.getMessage());
            }
        }
        return false;
    }

    // Metodo para modificar un estudiante existente en la BD
    public boolean modificarEstudiante(Estudiante estudiante){

        Connection conx = Conexion.getConexion();
        PreparedStatement ps;
        String sql = "UPDATE estudiante SET nombre = ?, apellido = ?, telefono = ?, email = ?" +
                "WHERE idEstudiante = ?";
        try {
            ps = conx.prepareStatement(sql);
            ps.setString(1, estudiante.getNombre());
            ps.setString(2, estudiante.getApellido());
            ps.setString(3, estudiante.getTelefono());
            ps.setString(4, estudiante.getEmail());
            ps.setInt(5, estudiante.getIdEstudiante());
            ps.execute();

            return true;

        }catch (Exception e){
            System.out.println("Ocurrio un error al modificar en la BD " + e.getMessage());
        }
        finally {
            try {
                conx.close();
            } catch (Exception e) {
                System.out.println("Ocurrio un error al cerrar la conexion de la BD " + e.getMessage());
            }
        }
        return false;
    }

    // Metodo para eliminar un estudiante de la BD
    public boolean eliminarEstudiante(Estudiante estudiante){

        Connection conx = Conexion.getConexion();
        PreparedStatement ps;
        String sql = "DELETE FROM estudiante WHERE idEstudiante = ?";
        try {
            ps = conx.prepareStatement(sql);
            ps.setInt(1, estudiante.getIdEstudiante());
            ps.execute();
            return true;
        }catch (Exception e){
            System.out.println("Ocurrio un error al eliminar el estudiante " + e.getMessage());
        }
        finally {
            try {
                conx.close();
            } catch (Exception e) {
                System.out.println("Ocurrio un error al cerrar la conexion de la BD " + e.getMessage());
            }
        }
        return false;
    }

    // Metodo para probar la clase y los metodos
    public static void main(String[] args) {

        var estudianteDAO = new EstudianteDAO();

        // Insertar un nuevo estudiante
        /*Estudiante estudianteAdd = new Estudiante("Mel", "Doe", "3344556677", "mel@mail.com");
        var agregado = estudianteDAO.agregarEstudiante(estudianteAdd);
        if (agregado){
            System.out.println("Estudiante agregado Exitosamente!!!");
        }else {
            System.out.println("No se pudo agregar el registro =(");
        }*/

        // Modificar por ID
        /*Estudiante estudianteMod = new Estudiante(3,"Mike", "Doe", "2010999",
                "mike@mail.com");
        var modificado = estudianteDAO.modificarEstudiante(estudianteMod);
        if(modificado){
            System.out.println("Registro Modificado Exitosamente! " + estudianteMod);
        }else {
            System.out.println("No se encontro estudiante con ID: " + estudianteMod.getIdEstudiante());
        }*/

        // Eliminar un estudiante de la BD
        var estudianteElim = new Estudiante(7);
        var eliminado = estudianteDAO.eliminarEstudiante(estudianteElim);
        if (eliminado){
            System.out.println("Registro Eliminado Exitosamente! " + estudianteElim);
        }else {
            System.out.println("No se encontro estudiante con ID: " + estudianteElim.getIdEstudiante());
        }

        // Lista de los estudiantes
        System.out.println("Listado Estudiantes:");
        List<Estudiante> estudiantes = estudianteDAO.listarEstudiantes();
        estudiantes.forEach(System.out::println);

        // Buscar por ID
        /*Estudiante estudiante1 = new Estudiante(4);
        // Se imprime el estudiante antes de la busqueda a la BD
        System.out.println("Estudiante antes de la busqueda: " + estudiante1);
        var encontrado = estudianteDAO.buscarEstudianteId(estudiante1);
        if(encontrado){
            System.out.println("Estudiante encontrado! " + estudiante1);
        }else {
            System.out.println("No se encontro estudiante con ID: " + estudiante1.getIdEstudiante());
        }*/


    }
}

