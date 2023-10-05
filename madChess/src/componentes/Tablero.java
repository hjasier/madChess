package componentes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Tablero extends JPanel{
	protected int height = 144;

	public void paint(Graphics grafico) {	
		super.paint(grafico);
		
		System.out.println("askdfjlasdf");

		for(int x= 0; x < 8; x++) { 
			int y = 0;
			for(int i= 0; i < 8; i++) { 
				new Casilla(Color.white,10,10,height);<z>
			}
			
			
		}
		

		
	}
	
}
