package ventanas;

import java.awt.Color;

import javax.swing.JFrame;

import componentes.*;


public class VentanaJuego {
    public static void main(String[] args) {
        JFrame pantalla = new JFrame("Mad Chess");
        pantalla.setSize(1600, 1190);
        pantalla.setLocation(490, 150);

        Tablero t = new Tablero();
        pantalla.add(t);

        pantalla.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pantalla.setVisible(true);
    }
}
