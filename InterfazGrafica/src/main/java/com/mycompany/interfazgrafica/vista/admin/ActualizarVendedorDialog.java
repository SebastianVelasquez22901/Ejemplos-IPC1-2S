package com.mycompany.interfazgrafica.vista.admin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActualizarVendedorDialog extends JDialog implements ActionListener {
    
    private String codigoVendedor;
    private JTextField nombreField;
    private JPasswordField passwordField;
    private JButton actualizarButton;
    private JButton cancelarButton;
    
    public ActualizarVendedorDialog(JFrame parent, String codigoVendedor) {
        super(parent, "Actualizar Vendedor", true);
        this.codigoVendedor = codigoVendedor;
        
        // Configurar el diálogo
        setSize(400, 250);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());
        
        // Panel principal con GridLayout
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Componentes del formulario
        panel.add(new JLabel("Código:"));
        JTextField codigoDisabledField = new JTextField(codigoVendedor);
        codigoDisabledField.setEnabled(false);
        panel.add(codigoDisabledField);
        
        panel.add(new JLabel("Nombre:"));
        nombreField = new JTextField("Juan Pérez"); // Valor precargado como ejemplo
        panel.add(nombreField);
        
        panel.add(new JLabel("Contraseña:"));
        passwordField = new JPasswordField();
        panel.add(passwordField);
        
        // Panel para los botones
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        
        actualizarButton = new JButton("Actualizar");
        actualizarButton.addActionListener(this);
        buttonPanel.add(actualizarButton);
        
        cancelarButton = new JButton("Cancelar");
        cancelarButton.addActionListener(this);
        buttonPanel.add(cancelarButton);
        
        // Agregar paneles al diálogo
        add(panel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == actualizarButton) {
            // Validar campos
            if (nombreField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                        "Por favor complete el nombre", 
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Aquí se procesaría la actualización del vendedor
            JOptionPane.showMessageDialog(this, 
                    "Vendedor actualizado exitosamente", 
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } else if (e.getSource() == cancelarButton) {
            dispose();
        }
    }
}