package ventanas;

import javax.swing.*;
import componentes.DibujaTablero;

public class VentanaJuego {
	public static void main(String[] args) {
		JFrame pantalla = new JFrame("Mad Chess");
		pantalla.setSize(600,600);
		pantalla.setLocation(490,150); 
		
		
		DibujaTablero tablero = new DibujaTablero();
		pantalla.add(tablero);
		
		pantalla.setVisible(true);
	}
}