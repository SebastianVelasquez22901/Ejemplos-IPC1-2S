package com.mycompany.ejemplopractica2;

import java.io.Serializable;

public class EventoBatalla implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String descripcion;
    private long timestamp;
    
    public EventoBatalla(String descripcion) {
        this.descripcion = descripcion;
        this.timestamp = System.currentTimeMillis();
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    @Override
    public String toString() {
        return descripcion;
    }
}