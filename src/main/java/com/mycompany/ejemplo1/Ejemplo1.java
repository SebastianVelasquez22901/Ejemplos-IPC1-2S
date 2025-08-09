/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.ejemplo1;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 *
 * @author SBGam
 */


public class Ejemplo1 {
    // Vectores para almacenar los datos de los personajes
    private static String[] nombres = new String[100];
    private static String[] armas = new String[100];
    private static String[][] habilidades = new String[100][5]; // Máximo 5 habilidades por personaje
    private static int[] nivelesPoder = new int[100];
    private static boolean[] personajesActivos = new boolean[100]; // Para controlar eliminación lógica
    private static int contadorPersonajes = 0;
    
    // Vectores para almacenar las peleas
    private static int[] peleaPersonaje1 = new int[500]; // IDs de personajes que pelean
    private static int[] peleaPersonaje2 = new int[500];
    private static String[] fechasPeleas = new String[500];
    private static int contadorPeleas = 0;
    
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        mostrarMenu();
    }
    
    public static void mostrarMenu() {
        int opcion = 0;
        
        do {
            System.out.println("\n=== SISTEMA DE GESTIÓN DE PERSONAJES ===");
            System.out.println("1. Agregar personaje");
            System.out.println("2. Modificar datos de personaje");
            System.out.println("3. Eliminar personaje");
            System.out.println("4. Ver datos de un personaje");
            System.out.println("5. Visualizar todos los personajes");
            System.out.println("6. Registrar pelea");
            System.out.println("7. Consultar historial de peleas");
            System.out.println("8. Salir");
            System.out.print("Seleccione una opción: ");
            
            try {
                opcion = Integer.parseInt(scanner.nextLine());
                
                switch(opcion) {
                    case 1:
                        agregarPersonaje();
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        verDatosPersonaje();
                        break;
                    case 5:
                        break;
                    case 6:
                        break;
                    case 7:
                        break;
                    case 8:
                        System.out.println("ESTUDIANTE POR DEFECTO");
                        break;
                    default:
                        System.out.println("Opción no válida. Intente nuevamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un número válido.");
            }
            
        } while(opcion != 8);
    }
    
    public static void agregarPersonaje() {
        if (contadorPersonajes >= 100) {
            System.out.println("Error: No se pueden agregar más personajes. Límite alcanzado.");
            return;
        }
        
        System.out.println("\n=== AGREGAR PERSONAJE ===");
        
        // Solicitar nombre
        String nombre;
        boolean nombreUnico = false;
        do {
            System.out.print("Ingrese el nombre del personaje: ");
            nombre = scanner.nextLine().trim();
            
            if (nombre.isEmpty()) {
                System.out.println("Error: El nombre no puede estar vacío.");
                continue;
            }
            
            // Verificar que el nombre sea único
            nombreUnico = true;
            for (int i = 0; i < contadorPersonajes; i++) {
                if (personajesActivos[i] && nombres[i].equalsIgnoreCase(nombre)) {
                    System.out.println("Error: Ya existe un personaje con ese nombre.");
                    nombreUnico = false;
                    break;
                }
            }
        } while (!nombreUnico);
        
        // Solicitar arma
        System.out.print("Ingrese el arma del personaje: ");
        String arma = scanner.nextLine().trim();
        
        // Solicitar habilidades
        String[] habilidadesPersonaje = new String[5];
        System.out.println("Ingrese hasta 5 habilidades (presione Enter sin texto para terminar):");
        int contadorHabilidades = 0;
        for (int i = 0; i < 5; i++) {
            System.out.print("Habilidad " + (i + 1) + ": ");
            String habilidad = scanner.nextLine().trim();
            if (!habilidad.isEmpty()) {
                habilidadesPersonaje[i] = habilidad;
                contadorHabilidades++;
            } else {
                break;
            }
        }
        
        // Solicitar nivel de poder
        int nivelPoder = 0;
        boolean nivelValido = false;
        do {
            try {
                System.out.print("Ingrese el nivel de poder (1-100): ");
                nivelPoder = Integer.parseInt(scanner.nextLine());
                if (nivelPoder >= 1 && nivelPoder <= 100) {
                    nivelValido = true;
                } else {
                    System.out.println("Error: El nivel debe estar entre 1 y 100.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un número válido.");
            }
        } while (!nivelValido);
        
        // Agregar el personaje a los vectores
        nombres[contadorPersonajes] = nombre;
        armas[contadorPersonajes] = arma;
        for (int i = 0; i < 5; i++) {
            habilidades[contadorPersonajes][i] = habilidadesPersonaje[i];
        }
        nivelesPoder[contadorPersonajes] = nivelPoder;
        personajesActivos[contadorPersonajes] = true;
        contadorPersonajes++;
        
        System.out.println("¡Personaje agregado exitosamente! ID asignado: " + contadorPersonajes);
    }
    
    public static void verDatosPersonaje() {
        if (contadorPersonajes == 0) {
            System.out.println("No hay personajes registrados.");
            return;
        }
        
        System.out.println("\n=== VER DATOS DE PERSONAJE ===");
        int id = solicitarIDPersonaje();
        if (id == -1) return;
        
        int indice = id - 1;
        System.out.println("\n--- INFORMACIÓN DEL PERSONAJE ---");
        System.out.println("ID: " + id);
        System.out.println("Nombre: " + nombres[indice]);
        System.out.println("Arma: " + armas[indice]);
        System.out.println("Nivel de poder: " + nivelesPoder[indice]);
        System.out.println("Habilidades:");
        
        boolean tieneHabilidades = false;
        for (int i = 0; i < 5; i++) {
            if (habilidades[indice][i] != null && !habilidades[indice][i].isEmpty()) {
                System.out.println("  - " + habilidades[indice][i]);
                tieneHabilidades = true;
            }
        }
        
        if (!tieneHabilidades) {
            System.out.println("  - Sin habilidades registradas");
        }
    }
    
    public static void visualizarPersonajes() {
        if (contadorPersonajes == 0) {
            System.out.println("No hay personajes registrados.");
            return;
        }
        
        System.out.println("\n=== LISTA DE PERSONAJES ===");
        System.out.println("ID\tNombre\t\t\tNivel de Poder");
        System.out.println("----------------------------------------");
        
        boolean hayPersonajes = false;
        for (int i = 0; i < contadorPersonajes; i++) {
            if (personajesActivos[i]) {
                System.out.printf("%d\t%-20s\t%d\n", (i + 1), nombres[i], nivelesPoder[i]);
                hayPersonajes = true;
            }
        }
        
        if (!hayPersonajes) {
            System.out.println("No hay personajes activos.");
        }
    }
    
    public static int solicitarIDPersonaje() {
        try {
            System.out.print("Ingrese el ID del personaje: ");
            int id = Integer.parseInt(scanner.nextLine());
            
            if (id < 1 || id > contadorPersonajes) {
                System.out.println("Error: ID no válido.");
                return -1;
            }
            
            if (!personajesActivos[id - 1]) {
                System.out.println("Error: El personaje con ID " + id + " ha sido eliminado.");
                return -1;
            }
            
            return id;
        } catch (NumberFormatException e) {
            System.out.println("Error: Debe ingresar un número válido.");
            return -1;
        }
    }
}