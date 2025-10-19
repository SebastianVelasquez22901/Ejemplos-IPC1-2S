package com.mycompany.interfazgrafica.vista.admin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActualizarProductoDialog extends JDialog implements ActionListener {
    
    private String codigoProducto;
    private JTextField nombreField;
    private JLabel categoriaValueLabel;
    private JLabel atributoLabel;
    private JTextField atributoField;
    private JButton actualizarButton;
    private JButton cancelarButton;
    
    public ActualizarProductoDialog(JFrame parent, String codigoProducto) {
        super(parent, "Actualizar Producto", true);
        this.codigoProducto = codigoProducto;
        
        // Configurar el diálogo
        setSize(400, 300);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());
        
        // Panel principal con GridLayout
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Componentes del formulario
        panel.add(new JLabel("Código:"));
        JTextField codigoDisabledField = new JTextField(codigoProducto);
        codigoDisabledField.setEnabled(false);
        panel.add(codigoDisabledField);
        
        panel.add(new JLabel("Nombre:"));
        nombreField = new JTextField("Computadora"); // Valor precargado como ejemplo
        panel.add(nombreField);
        
        panel.add(new JLabel("Categoría:"));
        categoriaValueLabel = new JLabel("Tecnología"); // Valor fijo, no se puede cambiar
        panel.add(categoriaValueLabel);
        
        atributoLabel = new JLabel("Meses de garantía:");
        panel.add(atributoLabel);
        
        atributoField = new JTextField("12"); // Valor precargado como ejemplo
        panel.add(atributoField);
        
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
            if (nombreField.getText().isEmpty() || atributoField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                        "Por favor complete todos los campos", 
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Aquí se procesaría la actualización del producto
            JOptionPane.showMessageDialog(this, 
                    "Producto actualizado exitosamente", 
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } else if (e.getSource() == cancelarButton) {
            dispose();
        }
    }
}