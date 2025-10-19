package com.mycompany.interfazgrafica.vista.admin;

import javax.swing.*;
import java.awt.*;

public class AdminMainPanel extends JPanel {
    
    private JFrame parentFrame;
    private JTabbedPane tabbedPane;
    
    public AdminMainPanel(JFrame parentFrame) {
        this.parentFrame = parentFrame;
        setLayout(new BorderLayout());
        
        // Panel superior con título
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(25, 118, 210));
        headerPanel.setPreferredSize(new Dimension(900, 60));
        headerPanel.setLayout(new BorderLayout());
        
        JLabel titleLabel = new JLabel("Módulo de Administración");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        headerPanel.add(titleLabel, BorderLayout.WEST);
        
        JButton logoutBtn = new JButton("Cerrar Sesión");
        logoutBtn.setFocusPainted(false);
        headerPanel.add(logoutBtn, BorderLayout.EAST);
        
        add(headerPanel, BorderLayout.NORTH);
        
        // Panel con pestañas
        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Gestión de Vendedores", new GestionVendedoresPanel(parentFrame));
        tabbedPane.addTab("Gestión de Productos", new GestionProductosPanel(parentFrame));
        
        add(tabbedPane, BorderLayout.CENTER);
        
        // Panel inferior con información del estudiante
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(new Color(25, 118, 210));
        footerPanel.setPreferredSize(new Dimension(900, 30));
        
        JLabel studentLabel = new JLabel("Estudiante: XXXX - Carnet: XXXXX");
        studentLabel.setForeground(Color.WHITE);
        footerPanel.add(studentLabel);
        
        add(footerPanel, BorderLayout.SOUTH);
    }
}