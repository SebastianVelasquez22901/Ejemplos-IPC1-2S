/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.interfazgrafica;

import com.mycompany.interfazgrafica.vista.MainFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author SBGam
 */
public class InterfazGrafica {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
        });
    }
}