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
    protected JPanel panelDerecha = new JPanel(); 
    protected JPanel panelAbajo = new JPanel(); 
    public VentanaJuego() {
    	int[] ventanaSize = {800,800};

        this.setSize(ventanaSize[0], ventanaSize[1]);
        this.setLocationRelativeTo(null);

        JPanel div = new JPanel(new BorderLayout());

        panelDerecha.setBackground(Color.red);
        panelAbajo.setBackground(Color.blue);
        div.add(tablero, BorderLayout.CENTER);
        div.add(panelDerecha, BorderLayout.EAST);
        div.add(panelAbajo, BorderLayout.SOUTH);

        this.setContentPane(div);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}




