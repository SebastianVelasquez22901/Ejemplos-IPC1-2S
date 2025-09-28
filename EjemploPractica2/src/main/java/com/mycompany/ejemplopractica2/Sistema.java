package com.mycompany.ejemplopractica2;

import java.io.*;
import java.util.Scanner;

public class Sistema {
    private Personaje[] personajes;
    private Batalla[] historialBatallas;
    private int numPersonajes;
    private int numBatallas;
    private int contadorIdPersonajes;
    private int contadorIdBatallas;
    
    public Sistema() {
        this.personajes = new Personaje[10]; // Tamaño inicial
        this.historialBatallas = new Batalla[10]; // Tamaño inicial
        this.numPersonajes = 0;
        this.numBatallas = 0;
        this.contadorIdPersonajes = 1;
        this.contadorIdBatallas = 1;
    }
    
    public boolean agregarPersonaje(String nombre, String arma, int hp, int ataque, int velocidad, int agilidad, int defensa) {
        System.out.println("Personaje agregado");
        
        return true;
    }
    
    public boolean existePersonajePorNombre(String nombre) {
        for (int i = 0; i < numPersonajes; i++) {
            if (personajes[i].getNombre().equalsIgnoreCase(nombre)) {
                return true;
            }
        }
        return false;
    }
    
    public Personaje buscarPersonajePorNombre(String nombre) {
        for (int i = 0; i < numPersonajes; i++) {
            if (personajes[i].getNombre().equalsIgnoreCase(nombre)) {
                return personajes[i];
            }
        }
        return null;
    }
    
    public Personaje buscarPersonajePorId(int id) {
        for (int i = 0; i < numPersonajes; i++) {
            if (personajes[i].getId() == id) {
                return personajes[i];
            }
        }
        return null;
    }
    
    public boolean modificarPersonaje(int id, String arma, int hp, int ataque, int velocidad, int agilidad, int defensa) {
        Personaje personaje = buscarPersonajePorId(id);
        
        if (personaje == null) {
            return false;
        }
        
        // Validar rangos
        if (hp < 100 || hp > 500 || 
            ataque < 10 || ataque > 100 || 
            velocidad < 1 || velocidad > 10 || 
            agilidad < 1 || agilidad > 10 || 
            defensa < 1 || defensa > 50) {
            return false;
        }
        
        personaje.setArma(arma);
        personaje.setHp(hp);
        personaje.setAtaque(ataque);
        personaje.setVelocidad(velocidad);
        personaje.setAgilidad(agilidad);
        personaje.setDefensa(defensa);
        
        return true;
    }
    
    public boolean eliminarPersonaje(int id) {
        System.out.println("Se elimino un personaje");
        
        return true;
    }
    
    public Personaje[] getPersonajes() {
        Personaje[] resultado = new Personaje[numPersonajes];
        for (int i = 0; i < numPersonajes; i++) {
            resultado[i] = personajes[i];
        }
        return resultado;
    }
    
    public Batalla[] getHistorialBatallas() {
        Batalla[] resultado = new Batalla[numBatallas];
        System.out.println("Se regresó la batalla");
        return resultado;
    }
    
    public void simularBatalla(Personaje personaje1, Personaje personaje2) throws InterruptedException {
        System.out.println("Simulacion de batalla");
    }
    
    public boolean guardarEstado(String archivo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeInt(numPersonajes);
            for (int i = 0; i < numPersonajes; i++) {
                oos.writeObject(personajes[i]);
            }
            
            oos.writeInt(numBatallas);
            for (int i = 0; i < numBatallas; i++) {
                oos.writeObject(historialBatallas[i]);
            }
            
            oos.writeInt(contadorIdPersonajes);
            oos.writeInt(contadorIdBatallas);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean cargarEstado(String archivo) {
    try {
        // Obtener la ruta del directorio actual de trabajo
        String directorioActual = System.getProperty("user.dir");
        File file = new File(directorioActual, archivo);
        
        System.out.println("Buscando archivo en: " + file.getAbsolutePath());
        
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        
        numPersonajes = ois.readInt();
        personajes = new Personaje[Math.max(10, numPersonajes * 2)];
        for (int i = 0; i < numPersonajes; i++) {
            personajes[i] = (Personaje) ois.readObject();
        }
        
        numBatallas = ois.readInt();
        historialBatallas = new Batalla[Math.max(10, numBatallas * 2)];
        for (int i = 0; i < numBatallas; i++) {
            historialBatallas[i] = (Batalla) ois.readObject();
        }
        
        contadorIdPersonajes = ois.readInt();
        contadorIdBatallas = ois.readInt();
        
        ois.close();
        return true;
    } catch (IOException | ClassNotFoundException e) {
        System.out.println("Error al cargar el archivo: " + e.getMessage());
        e.printStackTrace();
        return false;
    }
}
    
    public void guardarEstadoTexto(String archivo) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(archivo))) {
            // Guardar personajes
            writer.println("===== PERSONAJES =====");
            for (int i = 0; i < numPersonajes; i++) {
                Personaje p = personajes[i];
                writer.println(p.getId() + "|" + p.getNombre() + "|" + p.getArma() + "|" + 
                              p.getHp() + "|" + p.getAtaque() + "|" + p.getVelocidad() + "|" + 
                              p.getAgilidad() + "|" + p.getDefensa() + "|" + 
                              p.getBatallasGanadas() + "|" + p.getBatallasPerdidas());
            }
            
            // Guardar batallas
            writer.println("===== BATALLAS =====");
            for (int i = 0; i < numBatallas; i++) {
                Batalla b = historialBatallas[i];
                writer.println(b.getId() + "|" + b.getFechaHora().getTime() + "|" + 
                              b.getPersonaje1() + "|" + b.getPersonaje2() + "|" + 
                              (b.getGanador() != null ? b.getGanador() : ""));
                
                // Guardar bitácora de la batalla
                writer.println("--- BITACORA ---");
                EventoBatalla[] eventos = b.getBitacora();
                for (EventoBatalla evento : eventos) {
                    writer.println(evento.getDescripcion());
                }
                writer.println("--- FIN BITACORA ---");
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}