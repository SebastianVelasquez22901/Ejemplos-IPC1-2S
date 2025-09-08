/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.ejemplo2;

import java.util.*;

/**
 *
 * @author SBGam
 */
public class Ejemplo2 {

    static Scanner scanner = new Scanner(System.in);
    static Producto[] productos = new Producto[100];
    static int contadorProductos = 0;
    static String token = "P1SAVB";

    public static void main(String[] args) {
        while (true) {
            mostrarMenu();
            int opcion = leerEntero("Seleccione una opción: ");
            switch (opcion) {
                case 1: agregarProducto(); break;
                case 2: buscarProducto(); break;
                case 3: eliminarProducto(); break;
                case 4: registrarVenta(); break;
                case 5: generarReportes(); break;
                case 6: verDatosEstudiante(); break;
                case 7: bitacora(); break;
                case 8: System.out.println("¡Hasta luego!"); return;
                default: System.out.println("Opción inválida.");
            }
        }
    }

    static void mostrarMenu() {
        System.out.println("\n--- Menú de Gestión de Productos ---");
        System.out.println("1. Agregar Producto");
        System.out.println("8. Salir");
    }

    static void agregarProducto() {
        if (contadorProductos >= productos.length) {
            System.out.println("No se pueden agregar más productos.");
            return;
        }
        System.out.println("\n--- Agregar Producto ---");
        System.out.print("Nombre del producto: ");
        String nombre = scanner.nextLine();
        System.out.print("Categoría: ");
        String categoria = scanner.nextLine();
        double precio = leerDouble("Precio: ");
        int cantidad = leerEntero("Cantidad en stock: ");
        String codigo = generarCodigoUnico(token, nombre, categoria);
        productos[contadorProductos++] = new Producto(nombre, categoria, precio, cantidad, codigo);
        System.out.println("Producto agregado con código: " + codigo);
    }

    static String generarCodigoUnico(String token, String nombre, String categoria) {
        String parteNombre = nombre.length() >= 3 ? nombre.substring(0, 3).toUpperCase() : nombre.toUpperCase();
        String parteCategoria = categoria.length() >= 3 ? categoria.substring(0, 3).toUpperCase() : categoria.toUpperCase();
        int random = new Random().nextInt(9000) + 1000;
        return token + "-" + parteNombre + "-" + parteCategoria + "-" + random;
    }

    static void buscarProducto() {
        System.out.println("\n--- Buscar Producto ---");
        
        System.out.println("Producto no encontrado.");
    }

    static void eliminarProducto() {
        System.out.println("\n--- Eliminar Producto ---");
        
        System.out.println("Producto no encontrado.");
    }

    static void registrarVenta() {
        
        System.out.println("Producto no encontrado.");
    }

    static void generarReportes() {
        System.out.println("\n--- Reporte de Productos ---");
        for (int i = 0; i < contadorProductos; i++) {
            if (productos[i] != null) {
                System.out.println(productos[i]);
            }
        }
    }

    static void verDatosEstudiante() {
        System.out.println("\n--- Datos del Estudiante ---");
        System.out.println("Nombre: [Tu Nombre]");
        System.out.println("Carné: [Tu Carné]");
    }

    static void bitacora() {
        System.out.println("\n--- Bitácora ---");
        System.out.println("Funcionalidades usadas en esta sesión: [Implementar si se desea]");
    }

    static int leerEntero(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un número válido.");
            }
        }
    }

    static double leerDouble(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un número válido.");
            }
        }
    }
}

class Producto {
    String nombre, categoria, codigo;
    double precio;
    int cantidad;

    public Producto(String nombre, String categoria, double precio, int cantidad, String codigo) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
        this.cantidad = cantidad;
        this.codigo = codigo;
    }

    @Override
    public String toString() {
        return "Código: " + codigo + " | Nombre: " + nombre + " | Categoría: " + categoria +
                " | Precio: Q" + precio + " | Stock: " + cantidad;
    }
}