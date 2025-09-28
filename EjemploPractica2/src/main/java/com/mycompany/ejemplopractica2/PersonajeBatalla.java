package com.mycompany.ejemplopractica2;

import java.util.Random;

public class PersonajeBatalla extends Thread {
    private Personaje personaje;
    private Personaje oponente;
    private Batalla batalla;
    private boolean batallaContinua;
    
    public PersonajeBatalla(Personaje personaje, Personaje oponente, Batalla batalla) {
        this.personaje = personaje;
        this.oponente = oponente;
        this.batalla = batalla;
        this.batallaContinua = true;
    }
    
    @Override
    public void run() {
        Random random = new Random();
        
        while (batallaContinua && personaje.getHpActual() > 0 && oponente.getHpActual() > 0) {
            try {
                // Simular tiempo entre ataques según la velocidad
                Thread.sleep(1000 / personaje.getVelocidad());
                
                // Comprobar si la batalla debe continuar
                if (!batallaContinua || oponente.getHpActual() <= 0) {
                    break;
                }
                
                // Probabilidad de esquivar basada en la agilidad del oponente (1-10%)
                boolean esquiva = random.nextInt(100) < oponente.getAgilidad() * 10;
                
                if (esquiva) {
                    batalla.agregarEvento(personaje.getNombre() + " ataca a " + oponente.getNombre() + " - Falló (esquiva)");
                } else {
                    // Calcular daño (ataque - defensa, mínimo 1)
                    int daño = Math.max(1, personaje.getAtaque() - oponente.getDefensa());
                    
                    // Aplicar daño
                    int nuevoHp = Math.max(0, oponente.getHpActual() - daño);
                    oponente.setHpActual(nuevoHp);
                    
                    batalla.agregarEvento(personaje.getNombre() + " ataca a " + oponente.getNombre() + 
                                         " con " + personaje.getArma() + " causando " + daño + 
                                         " puntos de daño. HP restante: " + oponente.getHpActual());
                    
                    // Verificar si el oponente ha sido derrotado
                    if (oponente.getHpActual() <= 0) {
                        batalla.agregarEvento(oponente.getNombre() + " ha sido derrotado!");
                        batalla.setGanador(personaje.getNombre());
                        batallaContinua = false;
                    }
                }
            } catch (InterruptedException e) {
                batallaContinua = false;
                Thread.currentThread().interrupt();
            }
        }
    }
    
    public void detenerBatalla() {
        batallaContinua = false;
    }
}