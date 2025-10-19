package com.mycompany.interfazgrafica.vista.admin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CrearVendedorDialog extends JDialog implements ActionListener {
    
    private JTextField codigoField;
    private JTextField nombreField;
    private JComboBox<String> generoComboBox;
    private JPasswordField passwordField;
    private JButton crearButton;
    private JButton cancelarButton;
    
    public CrearVendedorDialog(JFrame parent) {
        super(parent, "Crear Vendedor", true);
        
        // Configurar el diálogo
        setSize(400, 300);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());
        
        // Panel principal con GridLayout
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Componentes del formulario
        panel.add(new JLabel("Código:"));
        codigoField = new JTextField();
        panel.add(codigoField);
        
        panel.add(new JLabel("Nombre:"));
        nombreField = new JTextField();
        panel.add(nombreField);
        
        panel.add(new JLabel("Género:"));
        generoComboBox = new JComboBox<>(new String[]{"Masculino", "Femenino"});
        panel.add(generoComboBox);
        
        panel.add(new JLabel("Contraseña:"));
        passwordField = new JPasswordField();
        panel.add(passwordField);
        
        // Panel para los botones
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        
        crearButton = new JButton("Crear");
        crearButton.addActionListener(this);
        buttonPanel.add(crearButton);
        
        cancelarButton = new JButton("Cancelar");
        cancelarButton.addActionListener(this);
        buttonPanel.add(cancelarButton);
        
        // Agregar paneles al diálogo
        add(panel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == crearButton) {
            // Validar campos
            if (codigoField.getText().isEmpty() || nombreField.getText().isEmpty() || 
                passwordField.getPassword().length == 0) {
                JOptionPane.showMessageDialog(this, 
                        "Por favor complete todos los campos", 
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Aquí se procesaría la creación del vendedor
            JOptionPane.showMessageDialog(this, 
                    "Vendedor creado exitosamente", 
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } else if (e.getSource() == cancelarButton) {
            dispose();
        }
    }
}