package com.mycompany.ejemplopractica2;

import java.util.Scanner;

public class EjemploPractica2 {
    private static Sistema sistema = new Sistema();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean salir = false;
        
        while (!salir) {
            mostrarMenu();
            int opcion = obtenerOpcion();
            
            switch (opcion) {
                case 1:
                    agregarPersonaje();
                    break;
                case 2:
                    modificarPersonaje();
                    break;
                case 3:
                    eliminarPersonaje();
                    break;
                case 4:
                    visualizarPersonajes();
                    break;
                case 5:
                    simularBatalla();
                    break;
                case 6:
                    verHistorialBatallas();
                    break;
                case 7:
                    buscarPersonajePorNombre();
                    break;
                case 8:
                    guardarCargarEstado();
                    break;
                case 9:
                    mostrarDatosEstudiante();
                    break;
                case 0:
                    salir = true;
                    System.out.println("¡Gracias por usar ArenaUSAC! ¡Hasta pronto!");
                    break;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
                    break;
            }
        }
    }
    
    private static void mostrarMenu() {
        System.out.println("\n===== ArenaUSAC - Menú Principal =====");
        System.out.println("1. Agregar personaje");
        System.out.println("2. Modificar personaje");
        System.out.println("3. Eliminar personaje");
        System.out.println("4. Visualizar personajes registrados");
        System.out.println("5. Simular batalla");
        System.out.println("6. Ver historial de batallas");
        System.out.println("7. Buscar personaje por nombre");
        System.out.println("8. Guardar/Cargar estado del sistema");
        System.out.println("9. Ver datos del estudiante");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
    }
    
    private static int obtenerOpcion() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
    private static void agregarPersonaje() {
        System.out.println("\n===== Agregar Personaje =====");
        
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        
        // Verificar si el nombre ya existe
        if (sistema.existePersonajePorNombre(nombre)) {
            System.out.println("Error: Ya existe un personaje con ese nombre.");
            return;
        }
        
        System.out.print("Arma: ");
        String arma = scanner.nextLine();
        
        int hp = obtenerEntero("Puntos de vida (100-500): ", 100, 500);
        int ataque = obtenerEntero("Nivel de ataque (10-100): ", 10, 100);
        int velocidad = obtenerEntero("Velocidad (1-10): ", 1, 10);
        int agilidad = obtenerEntero("Agilidad (1-10): ", 1, 10);
        int defensa = obtenerEntero("Defensa (1-50): ", 1, 50);
        
        boolean exito = sistema.agregarPersonaje(nombre, arma, hp, ataque, velocidad, agilidad, defensa);
        
        if (exito) {
            System.out.println("Personaje agregado con éxito!");
        } else {
            System.out.println("Error al agregar el personaje. Verifique los datos ingresados.");
        }
    }
    
    private static int obtenerEntero(String mensaje, int min, int max) {
        int valor;
        while (true) {
            System.out.print(mensaje);
            try {
                valor = Integer.parseInt(scanner.nextLine());
                if (valor >= min && valor <= max) {
                    return valor;
                } else {
                    System.out.println("Error: El valor debe estar entre " + min + " y " + max + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un número válido.");
            }
        }
    }
    
    private static void modificarPersonaje() {
        System.out.println("\n===== Modificar Personaje =====");
        
        System.out.print("Ingrese ID o nombre del personaje: ");
        String entrada = scanner.nextLine();
        
        Personaje personaje;
        try {
            int id = Integer.parseInt(entrada);
            personaje = sistema.buscarPersonajePorId(id);
        } catch (NumberFormatException e) {
            personaje = sistema.buscarPersonajePorNombre(entrada);
        }
        
        if (personaje == null) {
            System.out.println("Personaje no encontrado.");
            return;
        }
        
        System.out.println("\nPersonaje encontrado:");
        System.out.println(personaje);
        
        System.out.println("\nIngrese los nuevos datos (deje en blanco para mantener el valor actual):");
        
        System.out.print("Arma [" + personaje.getArma() + "]: ");
        String arma = scanner.nextLine();
        if (arma.isEmpty()) {
            arma = personaje.getArma();
        }
        
        int hp = personaje.getHp();
        int ataque = personaje.getAtaque();
        int velocidad = personaje.getVelocidad();
        int agilidad = personaje.getAgilidad();
        int defensa = personaje.getDefensa();
        
        String entrada2;
        
        System.out.print("Puntos de vida (100-500) [" + hp + "]: ");
        entrada2 = scanner.nextLine();
        if (!entrada2.isEmpty()) {
            try {
                hp = Integer.parseInt(entrada2);
                if (hp < 100 || hp > 500) {
                    System.out.println("Valor fuera de rango. Se mantendrá el valor actual.");
                    hp = personaje.getHp();
                }
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido. Se mantendrá el valor actual.");
            }
        }
        
        System.out.print("Nivel de ataque (10-100) [" + ataque + "]: ");
        entrada2 = scanner.nextLine();
        if (!entrada2.isEmpty()) {
            try {
                ataque = Integer.parseInt(entrada2);
                if (ataque < 10 || ataque > 100) {
                    System.out.println("Valor fuera de rango. Se mantendrá el valor actual.");
                    ataque = personaje.getAtaque();
                }
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido. Se mantendrá el valor actual.");
            }
        }
        
        System.out.print("Velocidad (1-10) [" + velocidad + "]: ");
        entrada2 = scanner.nextLine();
        if (!entrada2.isEmpty()) {
            try {
                velocidad = Integer.parseInt(entrada2);
                if (velocidad < 1 || velocidad > 10) {
                    System.out.println("Valor fuera de rango. Se mantendrá el valor actual.");
                    velocidad = personaje.getVelocidad();
                }
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido. Se mantendrá el valor actual.");
            }
        }
        
        System.out.print("Agilidad (1-10) [" + agilidad + "]: ");
        entrada2 = scanner.nextLine();
        if (!entrada2.isEmpty()) {
            try {
                agilidad = Integer.parseInt(entrada2);
                if (agilidad < 1 || agilidad > 10) {
                    System.out.println("Valor fuera de rango. Se mantendrá el valor actual.");
                    agilidad = personaje.getAgilidad();
                }
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido. Se mantendrá el valor actual.");
            }
        }
        
        System.out.print("Defensa (1-50) [" + defensa + "]: ");
        entrada2 = scanner.nextLine();
        if (!entrada2.isEmpty()) {
            try {
                defensa = Integer.parseInt(entrada2);
                if (defensa < 1 || defensa > 50) {
                    System.out.println("Valor fuera de rango. Se mantendrá el valor actual.");
                    defensa = personaje.getDefensa();
                }
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido. Se mantendrá el valor actual.");
            }
        }
        
        boolean exito = sistema.modificarPersonaje(personaje.getId(), arma, hp, ataque, velocidad, agilidad, defensa);
        
        if (exito) {
            System.out.println("Personaje modificado con éxito!");
        } else {
            System.out.println("Error al modificar el personaje.");
        }
    }
    
    private static void eliminarPersonaje() {
        System.out.println("\n===== Eliminar Personaje =====");
        
        System.out.print("Ingrese ID o nombre del personaje: ");
        String entrada = scanner.nextLine();
        
        Personaje personaje;
        try {
            int id = Integer.parseInt(entrada);
            personaje = sistema.buscarPersonajePorId(id);
        } catch (NumberFormatException e) {
            personaje = sistema.buscarPersonajePorNombre(entrada);
        }
        
        if (personaje == null) {
            System.out.println("Personaje no encontrado.");
            return;
        }
        
        System.out.println("\nPersonaje encontrado:");
        System.out.println(personaje);
        
        System.out.print("\n¿Está seguro que desea eliminar a este personaje? (s/n): ");
        String confirmacion = scanner.nextLine().toLowerCase();
        
        if (confirmacion.equals("s")) {
            boolean exito = sistema.eliminarPersonaje(personaje.getId());
            
            if (exito) {
                System.out.println("Personaje eliminado con éxito!");
            } else {
                System.out.println("Error al eliminar el personaje.");
            }
        } else {
            System.out.println("Operación cancelada.");
        }
    }
    
    private static void visualizarPersonajes() {
        System.out.println("\n===== Personajes Registrados =====");
        
        Personaje[] personajes = sistema.getPersonajes();
        
        if (personajes.length == 0) {
            System.out.println("No hay personajes registrados.");
        } else {
            for (Personaje p : personajes) {
                System.out.println(p);
            }
        }
    }
    
    private static void simularBatalla() {
        System.out.println("\n===== Simular Batalla =====");
        
        Personaje[] personajes = sistema.getPersonajes();
        
        if (personajes.length < 2) {
            System.out.println("Se necesitan al menos 2 personajes para simular una batalla.");
            return;
        }
        
        // Mostrar personajes disponibles
        System.out.println("Personajes disponibles:");
        for (Personaje p : personajes) {
            System.out.println(p);
        }
        
        // Seleccionar personaje 1
        System.out.print("\nSeleccione el primer personaje (ID o nombre): ");
        String entrada1 = scanner.nextLine();
        
        Personaje personaje1;
        try {
            int id = Integer.parseInt(entrada1);
            personaje1 = sistema.buscarPersonajePorId(id);
        } catch (NumberFormatException e) {
            personaje1 = sistema.buscarPersonajePorNombre(entrada1);
        }
        
        if (personaje1 == null) {
            System.out.println("Personaje no encontrado.");
            return;
        }
        
        // Seleccionar personaje 2
        System.out.print("Seleccione el segundo personaje (ID o nombre): ");
        String entrada2 = scanner.nextLine();
        
        Personaje personaje2;
        try {
            int id = Integer.parseInt(entrada2);
            personaje2 = sistema.buscarPersonajePorId(id);
        } catch (NumberFormatException e) {
            personaje2 = sistema.buscarPersonajePorNombre(entrada2);
        }
        
        if (personaje2 == null) {
            System.out.println("Personaje no encontrado.");
            return;
        }
        
        // Verificar que son personajes diferentes
        if (personaje1.getId() == personaje2.getId()) {
            System.out.println("Error: No puede seleccionar el mismo personaje para ambos combatientes.");
            return;
        }
        
        // Iniciar batalla
        try {
            System.out.println("\n¡COMIENZA LA BATALLA!");
            System.out.println(personaje1.getNombre() + " vs " + personaje2.getNombre());
            System.out.println("========================================");
            
            sistema.simularBatalla(personaje1, personaje2);
            
        } catch (InterruptedException e) {
            System.out.println("Error: La batalla fue interrumpida.");
            Thread.currentThread().interrupt();
        }
    }
    
    private static void verHistorialBatallas() {
        System.out.println("\n===== Historial de Batallas =====");
        
        Batalla[] batallas = sistema.getHistorialBatallas();
        
        if (batallas.length == 0) {
            System.out.println("No hay batallas registradas.");
        } else {
            for (Batalla b : batallas) {
                System.out.println(b);
                
                System.out.print("¿Desea ver la bitácora completa? (s/n): ");
                String respuesta = scanner.nextLine().toLowerCase();
                
                if (respuesta.equals("s")) {
                    System.out.println("\n===== Bitácora de la Batalla =====");
                    EventoBatalla[] eventos = b.getBitacora();
                    for (EventoBatalla evento : eventos) {
                        System.out.println(evento);
                    }
                    System.out.println("================================");
                }
            }
        }
    }
    
    private static void buscarPersonajePorNombre() {
        System.out.println("\n===== Buscar Personaje por Nombre =====");
        
        System.out.print("Ingrese el nombre del personaje: ");
        String nombre = scanner.nextLine();
        
        Personaje personaje = sistema.buscarPersonajePorNombre(nombre);
        
        if (personaje == null) {
            System.out.println("Personaje no encontrado.");
            return;
        }
        
        System.out.println("\nInformación del Personaje:");
        System.out.println(personaje);
        
        System.out.println("\nEstadísticas de Batalla:");
        System.out.println("Batallas totales: " + personaje.getTotalBatallas());
        System.out.println("Batallas ganadas: " + personaje.getBatallasGanadas());
        System.out.println("Batallas perdidas: " + personaje.getBatallasPerdidas());
    }
    
    private static void guardarCargarEstado() {
        System.out.println("\n===== Guardar/Cargar Estado del Sistema =====");
        System.out.println("1. Guardar estado");
        System.out.println("2. Cargar estado");
        System.out.println("0. Volver al menú principal");
        System.out.print("Seleccione una opción: ");
        
        int opcion = obtenerOpcion();
        
        switch (opcion) {
            case 1:
                System.out.print("Nombre del archivo para guardar: ");
                String archivoGuardar = scanner.nextLine();
                
                if (!archivoGuardar.endsWith(".txt")) {
                    archivoGuardar += ".txt";
                }
                
                sistema.guardarEstadoTexto(archivoGuardar);
                boolean exitoGuardar = sistema.guardarEstado(archivoGuardar.replace(".txt", ".dat"));
                
                if (exitoGuardar) {
                    System.out.println("Estado guardado con éxito en " + archivoGuardar);
                } else {
                    System.out.println("Error al guardar el estado.");
                }
                break;
                
            case 2:
                System.out.print("Nombre del archivo para cargar: ");
                String archivoCargar = scanner.nextLine();
                
                if (!archivoCargar.endsWith(".dat")) {
                    archivoCargar += ".dat";
                }
                
                boolean exitoCargar = sistema.cargarEstado(archivoCargar);
                
                if (exitoCargar) {
                    System.out.println("Estado cargado con éxito desde " + archivoCargar);
                } else {
                    System.out.println("Error al cargar el estado.");
                }
                break;
                
            case 0:
                return;
                
            default:
                System.out.println("Opción inválida.");
        }
    }
    
    private static void mostrarDatosEstudiante() {
        System.out.println("\n===== Datos del Estudiante =====");
        System.out.println("Nombre: Si no");
        System.out.println("Carné: cambian esto");
        System.out.println("Curso: De una vez");
        System.out.println("Sección: 0");
    }
}