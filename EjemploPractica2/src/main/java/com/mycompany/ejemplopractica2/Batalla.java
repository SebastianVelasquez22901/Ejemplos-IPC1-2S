package com.mycompany.ejemplopractica2;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Batalla implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int id;
    private Date fechaHora;
    private String personaje1;
    private String personaje2;
    private String ganador;
    
    // Array para almacenar eventos de batalla
    private EventoBatalla[] bitacora;
    private int numEventos;
    
    public Batalla(int id, String personaje1, String personaje2) {
        this.id = id;
        this.fechaHora = new Date();
        this.personaje1 = personaje1;
        this.personaje2 = personaje2;
        this.bitacora = new EventoBatalla[100]; // Tamaño inicial
        this.numEventos = 0;
    }
    
    public int getId() {
        return id;
    }
    
    public Date getFechaHora() {
        return fechaHora;
    }
    
    public String getPersonaje1() {
        return personaje1;
    }
    
    public String getPersonaje2() {
        return personaje2;
    }
    
    public String getGanador() {
        return ganador;
    }
    
    public void setGanador(String ganador) {
        this.ganador = ganador;
    }
    
    public EventoBatalla[] getBitacora() {
        EventoBatalla[] resultado = new EventoBatalla[numEventos];
        for (int i = 0; i < numEventos; i++) {
            resultado[i] = bitacora[i];
        }
        return resultado;
    }
    
    public void agregarEvento(String descripcion) {
        // Verificar si es necesario aumentar el tamaño
        if (numEventos >= bitacora.length) {
            EventoBatalla[] nuevaBitacora = new EventoBatalla[bitacora.length * 2];
            for (int i = 0; i < bitacora.length; i++) {
                nuevaBitacora[i] = bitacora[i];
            }
            bitacora = nuevaBitacora;
        }
        
        // Agregar el evento
        EventoBatalla evento = new EventoBatalla(descripcion);
        bitacora[numEventos] = evento;
        numEventos++;
        
        // Mostrar en consola
        System.out.println(descripcion);
    }
    
    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return "Batalla #" + id + " | Fecha: " + sdf.format(fechaHora) + 
               " | Participantes: " + personaje1 + " vs " + personaje2 + 
               " | Ganador: " + (ganador != null ? ganador : "En curso");
    }
    
    public String getResumen() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return "Batalla #" + id + "\n" +
               "Fecha: " + sdf.format(fechaHora) + "\n" +
               "Participantes: " + personaje1 + " vs " + personaje2 + "\n" +
               "Ganador: " + (ganador != null ? ganador : "En curso");
    }
}