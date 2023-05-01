/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.trabajofinal;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;



/**
 *
 * @author mollm
 */
public class TrabajoFinal {
    
    private static Conexion conexion = new Conexion();

    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        Conexion conexion = new Conexion();
        Connection connection = conexion.estableceConexion();
        if (connection != null) {
            System.out.println("Conectado a la base de datos");
        } else {
            System.out.println("No se pudo conectar a la base de datos");
        }
        
       
        System.out.println("Seleccione una opción");
        System.out.println("1 - Agregar Alumno");
        System.out.println("2 - Agregar Materia");
        int opcion = sc.nextInt();
        
        if(opcion == 1){
            Alumno alumno = new Alumno();
            alumno.crearAlumno();
        }else if (opcion == 2) {
            Materia materia = new Materia();
            materia.crearMateria();
        }else {
            System.out.println("No seleccionó una opción válida!");
        }
    }
}
