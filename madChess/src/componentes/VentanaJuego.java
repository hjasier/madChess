package componentes;

import javax.swing.*;

public class VentanaJuego {
	public static void main(String[] args) {
		JFrame pantalla = new JFrame("Mad Chess");
		pantalla.setSize(1600,1190);
		pantalla.setLocation(490,150); 
		
		
		Casilla casillaBlanca = new Casilla();
		pantalla.add(casillaBlanca);
		
		pantalla.setVisible(true);
	}
}
