package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

import componentes.*;


public class VentanaJuego extends JFrame {
    protected Tablero tablero = new Tablero();

    public VentanaJuego() {
        this.setSize(1000, 800);
        this.setLocationRelativeTo(null);



        this.add(tablero, BorderLayout.CENTER);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}



