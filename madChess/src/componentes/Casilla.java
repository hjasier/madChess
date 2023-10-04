package componentes;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Casilla extends JPanel {
	
	public void paint(Graphics grafico) {	
		super.paint(grafico);
		Graphics2D grafico2 = (Graphics2D) grafico;
		grafico2.fillRect(0, 0, 75, 75); //una casilla
	}
}
