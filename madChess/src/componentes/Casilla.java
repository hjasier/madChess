package componentes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Iterator;

import javax.swing.JPanel;

public class Casilla extends JPanel {
	
	public void paint(Graphics grafico) {	
		super.paint(grafico);
		
		Graphics2D casillaBlanca = (Graphics2D) grafico;
		casillaBlanca.setColor(Color.white);
		for(int i= 0; i < 4; i++) { 										//blancas
			casillaBlanca.fillRect(2 * i * 144, 0, 144, 144);
			casillaBlanca.fillRect((1 + 2 * i) * 144, 144, 144, 144);
			casillaBlanca.fillRect(2 * i * 144, 144 * 2, 144, 144);
			casillaBlanca.fillRect((1 + 2 * i) * 144, 144 * 3, 144, 144);
			casillaBlanca.fillRect(2 * i * 144, 144 * 4, 144, 144);
			casillaBlanca.fillRect((1 + 2 * i) * 144, 144 * 5, 144, 144);
			casillaBlanca.fillRect(2 * i * 144, 144 * 6, 144, 144);
			casillaBlanca.fillRect((1 + 2 * i) * 144, 144 * 7, 144, 144);
		}
		
		casillaBlanca.setColor(Color.gray);
		for(int i= 0; i < 4; i++) { 										//negras
			casillaBlanca.fillRect((1 + 2 * i)* 144, 0 , 144, 144);
			casillaBlanca.fillRect(2 * i * 144, 144, 144, 144);
			casillaBlanca.fillRect((1 + 2 * i)* 144, 144 * 2, 144, 144);
			casillaBlanca.fillRect(2 * i * 144, 144 * 3, 144, 144);
			casillaBlanca.fillRect((1 + 2 * i)* 144, 144 * 4, 144, 144);
			casillaBlanca.fillRect(2 * i * 144, 144 * 5, 144, 144);
			casillaBlanca.fillRect((1 + 2 * i)* 144, 144 * 6, 144, 144);
			casillaBlanca.fillRect(2 * i * 144, 144 * 7, 144, 144);
			
		}
		
	}
}
