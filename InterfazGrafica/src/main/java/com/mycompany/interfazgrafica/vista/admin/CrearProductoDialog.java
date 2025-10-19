package com.mycompany.interfazgrafica.vista.admin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class CrearProductoDialog extends JDialog implements ActionListener {
    
    private JTextField codigoField;
    private JTextField nombreField;
    private JComboBox<String> categoriaComboBox;
    private JLabel atributoLabel;
    private JTextField atributoField;
    private JButton crearButton;
    private JButton cancelarButton;
    
    public CrearProductoDialog(JFrame parent) {
        super(parent, "Crear Producto", true);
        
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
        
        panel.add(new JLabel("Categoría:"));
        categoriaComboBox = new JComboBox<>(new String[]{"Tecnología", "Alimento", "Generales"});
        categoriaComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    updateAtributoLabel();
                }
            }
        });
        panel.add(categoriaComboBox);
        
        atributoLabel = new JLabel("Meses de garantía:");
        panel.add(atributoLabel);
        
        atributoField = new JTextField();
        panel.add(atributoField);
        
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
    
    private void updateAtributoLabel() {
        String categoriaSeleccionada = (String) categoriaComboBox.getSelectedItem();
        if (categoriaSeleccionada.equals("Tecnología")) {
            atributoLabel.setText("Meses de garantía:");
        } else if (categoriaSeleccionada.equals("Alimento")) {
            atributoLabel.setText("Fecha de caducidad:");
        } else if (categoriaSeleccionada.equals("Generales")) {
            atributoLabel.setText("Material:");
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == crearButton) {
            // Validar campos
            if (codigoField.getText().isEmpty() || nombreField.getText().isEmpty() || 
                atributoField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                        "Por favor complete todos los campos", 
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Aquí se procesaría la creación del producto
            JOptionPane.showMessageDialog(this, 
                    "Producto creado exitosamente", 
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } else if (e.getSource() == cancelarButton) {
            dispose();
        }
    }
}