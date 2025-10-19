package com.mycompany.interfazgrafica.vista;

import com.mycompany.interfazgrafica.vista.admin.AdminMainPanel;
import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    
    public MainFrame() {
        setTitle("Sancarlista Shop");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 700);
        setLocationRelativeTo(null);
        
        // Por defecto mostramos el panel de administrador
        setContentPane(new AdminMainPanel(this));
    }
}