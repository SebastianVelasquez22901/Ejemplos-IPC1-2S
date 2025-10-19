package com.mycompany.interfazgrafica.vista.admin;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;  // Importación faltante
import java.awt.*;
import java.awt.event.ActionEvent;

public class GestionProductosPanel extends JPanel {
    
    private JTable tablaProductos;
    private DefaultTableModel modeloTabla;
    private JFrame parentFrame;
    
    public GestionProductosPanel(JFrame parentFrame) {
        this.parentFrame = parentFrame;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        initComponents();
    }
    
    private void initComponents() {
        // Panel de botones superiores
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        
        JButton crearBtn = new JButton("Crear Producto");
        crearBtn.addActionListener(e -> mostrarCrearProductoDialog());
        
        JButton cargarCSVBtn = new JButton("Cargar CSV");
        
        buttonPanel.add(crearBtn);
        buttonPanel.add(cargarCSVBtn);
        
        add(buttonPanel, BorderLayout.NORTH);
        
        // Tabla de productos
        String[] columnas = {"Código", "Nombre", "Categoría", "Stock", "Acciones"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 4; // Solo la columna de acciones es editable
            }
        };
        
        tablaProductos = new JTable(modeloTabla);
        tablaProductos.getTableHeader().setReorderingAllowed(false);
        
        // Agregar datos de ejemplo
        agregarDatosEjemplo();
        
        // Panel con botones de acción en la tabla
        tablaProductos.getColumnModel().getColumn(4).setCellRenderer(new ButtonRenderer());
        tablaProductos.getColumnModel().getColumn(4).setCellEditor(
                new ButtonEditor(new JCheckBox(), tablaProductos));
        
        JScrollPane scrollPane = new JScrollPane(tablaProductos);
        add(scrollPane, BorderLayout.CENTER);
    }
    
    private void agregarDatosEjemplo() {
        modeloTabla.addRow(new Object[]{"PR001", "Computadora", "Tecnología", "25", "Acciones"});
        modeloTabla.addRow(new Object[]{"PR002", "Pan", "Alimento", "100", "Acciones"});
        modeloTabla.addRow(new Object[]{"PR003", "Escoba", "Generales", "50", "Acciones"});
    }
    
    private void mostrarCrearProductoDialog() {
        CrearProductoDialog dialog = new CrearProductoDialog(parentFrame);
        dialog.setVisible(true);
    }
    
    // Clase para renderizar botones en la tabla
    class ButtonRenderer extends JPanel implements TableCellRenderer {
        private JButton editarBtn = new JButton("Editar");
        private JButton eliminarBtn = new JButton("Eliminar");
        private JButton detalleBtn = new JButton("Ver Detalle");
        
        public ButtonRenderer() {
            setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
            add(editarBtn);
            add(eliminarBtn);
            add(detalleBtn);
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
        private JButton detalleBtn;
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
            detalleBtn = new JButton("Ver Detalle");
            
            editarBtn.addActionListener((ActionEvent e) -> {
                int row = tabla.getEditingRow();
                String codigo = (String) tabla.getValueAt(row, 0);
                mostrarActualizarProductoDialog(codigo);
                fireEditingStopped();
            });
            
            eliminarBtn.addActionListener((ActionEvent e) -> {
                int row = tabla.getEditingRow();
                String codigo = (String) tabla.getValueAt(row, 0);
                String nombre = (String) tabla.getValueAt(row, 1);
                int opcion = JOptionPane.showConfirmDialog(
                        panel, 
                        "¿Está seguro de eliminar el producto " + nombre + " (" + codigo + ")?", 
                        "Confirmar eliminación", 
                        JOptionPane.YES_NO_OPTION);
                if (opcion == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(panel, "Producto eliminado correctamente");
                    modeloTabla.removeRow(row);
                }
                fireEditingStopped();
            });
            
            detalleBtn.addActionListener((ActionEvent e) -> {
                int row = tabla.getEditingRow();
                String categoria = (String) tabla.getValueAt(row, 2);
                String nombre = (String) tabla.getValueAt(row, 1);
                
                String detalle = "";
                if (categoria.equals("Tecnología")) {
                    detalle = "Meses de garantía: 12";
                } else if (categoria.equals("Alimento")) {
                    detalle = "Fecha de caducidad: 15/12/2025";
                } else if (categoria.equals("Generales")) {
                    detalle = "Material: Plástico";
                }
                
                JOptionPane.showMessageDialog(
                        panel,
                        "Detalles de " + nombre + ":\n" + detalle,
                        "Detalles del Producto",
                        JOptionPane.INFORMATION_MESSAGE);
                
                fireEditingStopped();
            });
            
            panel.add(editarBtn);
            panel.add(eliminarBtn);
            panel.add(detalleBtn);
        }
        
        private void mostrarActualizarProductoDialog(String codigo) {
            ActualizarProductoDialog dialog = new ActualizarProductoDialog(parentFrame, codigo);
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