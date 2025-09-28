package com.mycompany.ejemplopractica2;

import java.io.Serializable;

public class Personaje implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int id;
    private String nombre;
    private String arma;
    private int hp;
    private int ataque;
    private int velocidad;
    private int agilidad;
    private int defensa;
    private int hpActual;
    private int batallasGanadas;
    private int batallasPerdidas;
    
    public Personaje(int id, String nombre, String arma, int hp, int ataque, int velocidad, int agilidad, int defensa) {
        this.id = id;
        this.nombre = nombre;
        this.arma = arma;
        this.hp = hp;
        this.hpActual = hp;
        this.ataque = ataque;
        this.velocidad = velocidad;
        this.agilidad = agilidad;
        this.defensa = defensa;
        this.batallasGanadas = 0;
        this.batallasPerdidas = 0;
    }
    
    // Getters y setters
    public int getId() {
        return id;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getArma() {
        return arma;
    }
    
    public void setArma(String arma) {
        this.arma = arma;
    }
    
    public int getHp() {
        return hp;
    }
    
    public void setHp(int hp) {
        this.hp = hp;
        this.hpActual = hp;
    }
    
    public int getHpActual() {
        return hpActual;
    }
    
    public void setHpActual(int hpActual) {
        this.hpActual = hpActual;
    }
    
    public int getAtaque() {
        return ataque;
    }
    
    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }
    
    public int getVelocidad() {
        return velocidad;
    }
    
    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }
    
    public int getAgilidad() {
        return agilidad;
    }
    
    public void setAgilidad(int agilidad) {
        this.agilidad = agilidad;
    }
    
    public int getDefensa() {
        return defensa;
    }
    
    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }
    
    public int getBatallasGanadas() {
        return batallasGanadas;
    }
    
    public void incrementarBatallasGanadas() {
        this.batallasGanadas++;
    }
    
    public int getBatallasPerdidas() {
        return batallasPerdidas;
    }
    
    public void incrementarBatallasPerdidas() {
        this.batallasPerdidas++;
    }
    
    public int getTotalBatallas() {
        return batallasGanadas + batallasPerdidas;
    }
    
    public void reiniciarHp() {
        this.hpActual = this.hp;
    }
    
    @Override
    public String toString() {
        return "ID: " + id + " | Nombre: " + nombre + " | Arma: " + arma + 
               " | HP: " + hpActual + "/" + hp + " | Ataque: " + ataque + 
               " | Velocidad: " + velocidad + " | Agilidad: " + agilidad + 
               " | Defensa: " + defensa;
    }
}