package com.mycompany.interfazgrafica.vista.admin;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;  // Importación faltante
import java.awt.*;
import java.awt.event.ActionEvent;

public class GestionVendedoresPanel extends JPanel {
    
    private JTable tablaVendedores;
    private DefaultTableModel modeloTabla;
    private JFrame parentFrame;
    
    public GestionVendedoresPanel(JFrame parentFrame) {
        this.parentFrame = parentFrame;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        initComponents();
    }
    
    private void initComponents() {
        // Panel de botones superiores
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        
        JButton crearBtn = new JButton("Crear Vendedor");
        crearBtn.addActionListener(e -> mostrarCrearVendedorDialog());
        
        JButton cargarCSVBtn = new JButton("Cargar CSV");
        
        buttonPanel.add(crearBtn);
        buttonPanel.add(cargarCSVBtn);
        
        add(buttonPanel, BorderLayout.NORTH);
        
        // Tabla de vendedores
        String[] columnas = {"Código", "Nombre", "Género", "Ventas Confirmadas", "Acciones"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 4; // Solo la columna de acciones es editable
            }
        };
        
        tablaVendedores = new JTable(modeloTabla);
        tablaVendedores.getTableHeader().setReorderingAllowed(false);
        
        // Agregar datos de ejemplo
        agregarDatosEjemplo();
        
        // Panel con botones de acción en la tabla
        tablaVendedores.getColumnModel().getColumn(4).setCellRenderer(new ButtonRenderer());
        tablaVendedores.getColumnModel().getColumn(4).setCellEditor(
                new ButtonEditor(new JCheckBox(), tablaVendedores));
        
        JScrollPane scrollPane = new JScrollPane(tablaVendedores);
        add(scrollPane, BorderLayout.CENTER);
    }
    
    private void agregarDatosEjemplo() {
        modeloTabla.addRow(new Object[]{"VE001", "Juan Pérez", "Masculino", "15", "Acciones"});
        modeloTabla.addRow(new Object[]{"VE002", "María López", "Femenino", "23", "Acciones"});
        modeloTabla.addRow(new Object[]{"VE003", "Carlos Ruiz", "Masculino", "8", "Acciones"});
    }
    
    private void mostrarCrearVendedorDialog() {
        CrearVendedorDialog dialog = new CrearVendedorDialog(parentFrame);
        dialog.setVisible(true);
    }
    
    // Clase para renderizar botones en la tabla
    class ButtonRenderer extends JPanel implements TableCellRenderer {
        private JButton editarBtn = new JButton("Editar");
        private JButton eliminarBtn = new JButton("Eliminar");
        
        public ButtonRenderer() {
            setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
            add(editarBtn);
            add(eliminarBtn);
        }
        
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            return this;
        }
    }
    
    // Clase para manejar la edición (click) en los botones
    class ButtonEditor extends DefaultCellEditor {
        private JPanel panel;
        private JButton editarBtn;
        private JButton eliminarBtn;
        private String label;
        private JTable tabla;
        private boolean isPushed;
        
        public ButtonEditor(JCheckBox checkBox, JTable tabla) {
            super(checkBox);
            this.tabla = tabla;
            
            panel = new JPanel();
            panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
            
            editarBtn = new JButton("Editar");
            eliminarBtn = new JButton("Eliminar");
            
            editarBtn.addActionListener((ActionEvent e) -> {
                int row = tabla.getEditingRow();
                String codigo = (String) tabla.getValueAt(row, 0);
                mostrarActualizarVendedorDialog(codigo);
                fireEditingStopped();
            });
            
            eliminarBtn.addActionListener((ActionEvent e) -> {
                int row = tabla.getEditingRow();
                String codigo = (String) tabla.getValueAt(row, 0);
                String nombre = (String) tabla.getValueAt(row, 1);
                int opcion = JOptionPane.showConfirmDialog(
                        panel, 
                        "¿Está seguro de eliminar al vendedor " + nombre + " (" + codigo + ")?", 
                        "Confirmar eliminación", 
                        JOptionPane.YES_NO_OPTION);
                if (opcion == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(panel, "Vendedor eliminado correctamente");
                    modeloTabla.removeRow(row);
                }
                fireEditingStopped();
            });
            
            panel.add(editarBtn);
            panel.add(eliminarBtn);
        }
        
        private void mostrarActualizarVendedorDialog(String codigo) {
            ActualizarVendedorDialog dialog = new ActualizarVendedorDialog(parentFrame, codigo);
            dialog.setVisible(true);
        }
        
        @Override
        public Component getTableCellEditorComponent(JTable table, Object value,
                boolean isSelected, int row, int column) {
            isPushed = true;
            return panel;
        }
        
        @Override
        public Object getCellEditorValue() {
            isPushed = false;
            return "Acciones";
        }
        
        @Override
        public boolean stopCellEditing() {
            isPushed = false;
            return super.stopCellEditing();
        }
    }
}