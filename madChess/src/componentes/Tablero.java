package componentes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.util.Iterator;

import javax.swing.JPanel;

public class Tablero extends JPanel{
	private static final long serialVersionUID = 1L;
	protected int height = 144;
	protected Color c1 = Color.white;
	protected Color c2 = Color.gray;
	
	
	public Tablero() {
		
		this.setLayout(new GridLayout(8,8)); //Grid de 8x8
		
		GridLayout gridLayout = (GridLayout) this.getLayout();
		gridLayout.setHgap(0);
		gridLayout.setVgap(0);
		
		for (int i = 0; i < 64; i++) {
			Casilla casilla = new Casilla(Color.red,0,0,100);
			this.add(casilla);
		}
		
		
		

	}
	

	
}
