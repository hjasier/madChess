package componentes;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Tablero extends JPanel{
	private static final long serialVersionUID = 1L;
	protected Color c1 = new Color(234,236,209);
	protected Color c2 = new Color(80, 150, 167);
	
	
    public Tablero() {    	
    	this.setSize(600, 600);
    	
        this.setLayout(new GridLayout(8, 8,0,0));

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) { 
            	
            	// Para cada pos de columa dentro de cada una de las filas creamos una casilla , si es par blanca y si no negra
                Color color = (row + col) % 2 == 0 ? c1 : c2;
                Casilla casilla = new Casilla(color);
                this.add(casilla);
            }
        }
    }
    
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // igual hay que hacer un debouncer ns la verdad
        Container ventana = this.getParent();
        int minSize = Math.min(ventana.getWidth(), ventana.getHeight()) - 100;
        this.setSize(minSize,minSize);
        
        
    }
    
    
    
	
	

	
}
