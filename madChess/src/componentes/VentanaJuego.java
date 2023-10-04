package componentes;

import javax.swing.*;

public class VentanaJuego {
	public static void main(String[] args) {
		JFrame ventanaJuego = new JFrame("Mad Chess");
		ventanaJuego.setSize(1600,900);
		
		
		Casilla casilla1 = new Casilla();
		ventanaJuego.add(casilla1);
		
		ventanaJuego.setVisible(true);
	}
}
