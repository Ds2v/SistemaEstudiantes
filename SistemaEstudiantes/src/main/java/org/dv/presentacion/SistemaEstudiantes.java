package org.dv.presentacion;

import org.dv.dao.EstudianteDAO;
import org.dv.modelo.Estudiante;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SistemaEstudiantes {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        var estudianteDAO = new EstudianteDAO();

        var salir = false;
        while (!salir){
            mostarMenu();
            try {
                salir = ejecutarOpcion(scan, estudianteDAO);
            }catch (Exception e){
                System.out.println("Ingrese una opcion del Menu: " + e.getMessage());
            }
        }
    }

    // Metodo para mostrar el Menu
    private static void mostarMenu(){
        System.out.print("""
                ***** SISTEMA DE ESTUDIANTES *****
                
                1. Listar estudiante
                2. Agregar estudiante
                3. Buscar estudiante
                4. Modificar estudiante
                5. Eliminar estudiante
                6. Salir
                """);
        System.out.print("Escriba la opcion deseada: ");
    }

    // Metodo para ejecutar la opcion tomada por el usuario
    private static boolean ejecutarOpcion(Scanner scan, EstudianteDAO estudianteDAO){
        var opcion = Integer.parseInt(scan.nextLine());
        var salir = false;
        switch (opcion){
            case 1 -> { // Lista un estudiante
                System.out.println("Listado Estudiantes:");
                var estudiantes = estudianteDAO.listarEstudiantes();
                estudiantes.forEach(System.out::println);
                System.out.println();
            }
            case 2 -> { // Agrega un estudiante
                var estudianteAdd = new Estudiante();
                System.out.print("Ingrese el nombre:");
                estudianteAdd.setNombre(scan.nextLine());
                System.out.print("Ingrese el apellido:");
                estudianteAdd.setApellido(scan.nextLine());
                System.out.print("Ingrese el telefono:");
                estudianteAdd.setTelefono(scan.nextLine());
                System.out.print("Ingrese el email:");
                estudianteAdd.setEmail(scan.nextLine());

                var agregado = estudianteDAO.agregarEstudiante(estudianteAdd);
                if (agregado){
                    System.out.println("Estudiante agregado Exitosamente!!!");
                }else {
                    System.out.println("No se pudo agregar el registro =(");
                }
            }
            case 3 -> { // Busca un estudiante
                System.out.print("Ingrese el codigo del estudiante a buscar: ");
                var codigo = Integer.parseInt(scan.nextLine());
                var estudianteBusq = new Estudiante(codigo);
                var encontrado = estudianteDAO.buscarEstudianteId(estudianteBusq);
                System.out.println();
                if(encontrado){
                    System.out.println("Estudiante encontrado! " + estudianteBusq);
                }else {
                    System.out.println("No se encontro estudiante con ID: " + codigo);
                }
            }
            case 4 -> { // Modifica un estudiante
                System.out.print("Ingrese el codigo del estudiante a modificar :");
                var codigo = Integer.parseInt(scan.nextLine());
                var estudianteMod = new Estudiante(codigo);
                var codEncontrado = estudianteDAO.buscarEstudianteId(estudianteMod);
                if (codEncontrado) {
                    System.out.print("Ingrese el nombre:");
                    estudianteMod.setNombre(scan.nextLine());
                    System.out.print("Ingrese el apellido:");
                    estudianteMod.setApellido(scan.nextLine());
                    System.out.print("Ingrese el telefono:");
                    estudianteMod.setTelefono(scan.nextLine());
                    System.out.print("Ingrese el email:");
                    estudianteMod.setEmail(scan.nextLine());
                    var modificado = estudianteDAO.modificarEstudiante(estudianteMod);
                    if(modificado){
                        System.out.println("Registro Modificado Exitosamente! " + estudianteMod);
                    }else {
                        System.out.println("Error al Mofificar");
                    }
                }else {
                    System.out.println("No se encontro estudiante con ID: " + codigo);
                }
            }
            case 5 -> { // Elimina un estudiante
                System.out.print("Ingrese el codigo del estudiante a eliminar: ");
                var codigo = Integer.parseInt(scan.nextLine());
                var estudianteElim = new Estudiante(codigo);
                var codEncontrado = estudianteDAO.buscarEstudianteId(estudianteElim);
                if (codEncontrado) {
                    var eliminado = estudianteDAO.eliminarEstudiante(estudianteElim);
                    if (eliminado){
                        System.out.println("Registro eliminado Exitosamente!!!");
                    }else {
                        System.out.println("Error al Eliminar");
                    }
                }else {
                    System.out.println("No se encontro estudiante con ID: " + codigo);
                }

            }
            case 6 -> { // Cierra el programa
                System.out.println("Regresa Pronto!!!");
                salir = true;
            }
            default -> {
                System.out.println("Ingreso la opcion incorrecta -> 1 - 6");
            }
        }
        return salir;
    }
}
