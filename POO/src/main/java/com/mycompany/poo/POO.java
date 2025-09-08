package com.mycompany.poo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Clase principal con interfaz gráfica
public class POO extends JFrame {
    private Carro miCarro;
    private JLabel estadoLabel;
    private JButton encenderBtn, avanzarBtn, apagarBtn;

    public POO() {
        setTitle("Simulación de Carro - POO");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        miCarro = new Carro("Toyota", "Rojo");
        estadoLabel = new JLabel(miCarro.getEstado(), SwingConstants.CENTER);

        encenderBtn = new JButton("Encender");
        avanzarBtn = new JButton("Avanzar");
        apagarBtn = new JButton("Apagar");

        JPanel panelBotones = new JPanel();
        panelBotones.add(encenderBtn);
        panelBotones.add(avanzarBtn);
        panelBotones.add(apagarBtn);

        add(estadoLabel, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        encenderBtn.addActionListener(e -> {
            miCarro.encender();
            actualizarEstado();
        });

        avanzarBtn.addActionListener(e -> {
            miCarro.avanzar();
            actualizarEstado();
        });

        apagarBtn.addActionListener(e -> {
            miCarro.apagar();
            actualizarEstado();
        });

        setSize(400, 200);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void actualizarEstado() {
        estadoLabel.setText(miCarro.getEstado());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(POO::new);
    }
}

// Clase Carro con métodos y atributos
class Carro {
    private String marca;
    private String color;
    private boolean encendido;
    private int kilometros;

    public Carro(String marca, String color) {
        this.marca = marca;
        this.color = color;
        this.encendido = false;
        this.kilometros = 0;
    }

    public void encender() {
        if (!encendido) {
            encendido = true;
        }
    }

    public void avanzar() {
        if (encendido) {
            kilometros += 10;
        }
    }

    public void apagar() {
        if (encendido) {
            encendido = false;
        }
    }

    public String getEstado() {
        return "<html>Marca: " + marca +
                "<br>Color: " + color +
                "<br>Encendido: " + (encendido ? "Sí" : "No") +
                "<br>Kilómetros recorridos: " + kilometros + "</html>";
    }
}