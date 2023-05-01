/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabajofinal;


import com.google.gson.Gson;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author mollm
 */
public class Alumno {
    private static Scanner sc = new Scanner(System.in).useDelimiter("\n");
    private static Scanner scanner = new Scanner(System.in);

    private static Conexion conexion = new Conexion();
    
    String nombre;
    int legajo;
    ArrayList<String> correlativas = new ArrayList<>();
    
    public Alumno(String nombre, int legajo) {
        this.nombre = nombre;
        this.legajo = legajo;
    }
    public Alumno() {}
    
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setLegajo(int legajo) {
        this.legajo = legajo;
    }
    public ArrayList<String> getCorreltivas() {
        return correlativas;
    }
    public void setCorrelativas(ArrayList<String> correlativas) {
        this.correlativas = correlativas;
    }
    @Override
    public String toString() {
        return "Alumno{" + "nombre=" + nombre + ", legajo=" + legajo + ", correlativas=" + correlativas + '}';
    }
    public static void crearAlumno() throws SQLException{
    
        Alumno alumno = new Alumno();

        ////---CREACION DE ALUMNO---
        conexion.estableceConexion();
        Statement stmt = conexion.conectar.createStatement();
        
        System.out.println("Ingrese el nombre del Alumno");
        String nombre = sc.next();
        alumno.setNombre(nombre);

        System.out.println("Ingrese un número de 5 dígitos para el legajo");
            while (!scanner.hasNextInt()) {
                System.out.println("Error: Debe ingresar un número entero. Ingrese nuevamente.");
                scanner.next();
            }
            int legajo = sc.nextInt();
            alumno.setLegajo(legajo);

        // Validar que el número tenga exactamente 5 dígitos y que sean todos números
        if (Integer.toString(legajo).matches("\\d{5}")) {
            
            ResultSet rs = stmt.executeQuery("SELECT * FROM tabla_alumnos WHERE legajo = '"+legajo+"';");
            if (rs.next()) {

                legajo = rs.getInt(2);
                String correlativasJson = rs.getString(3);

                Alumno alumnoEncontrado = new Alumno(nombre,legajo);
                ArrayList<String> correlativas = new com.google.gson.Gson().fromJson(correlativasJson, ArrayList.class);
                alumnoEncontrado.setCorrelativas(correlativas);

                System.out.println("Alumno encontrado: " + alumnoEncontrado);
                System.out.println("Por favor escribe otro numero de legajo.");
            } else {
                System.out.println("Ingrese el número de correlativas que tendra el Alumno");

                int numeros = sc.nextInt();

                System.out.println("Ingrese las correlativas que trenda el Alumno");
                ArrayList<String> correlativas = new ArrayList<>();

                for (int i = 0; i < numeros; i++) {
                    String input = sc.next();
                    correlativas.add(input);
                }

                String correlativasJson = new Gson().toJson(correlativas);

                stmt.executeUpdate("INSERT INTO tabla_alumnos VALUES(\"" + nombre + "\",'" + legajo + "', '" + correlativasJson + "');");
                conexion.cerrarConnection(); 
                System.out.println("El alumno se agrego a la base de datos");
            }
        }else{
            System.out.println("Error: Debe ingresar un número de 5 dígitos.");
        }

    }      
}
