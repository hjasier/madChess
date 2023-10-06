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
	protected Color c1 = Color.white;
	protected Color c2 = Color.gray;
	
	
    public Tablero() {
    	
    	this.setMaximumSize(new Dimension(500,500));
    	int minSize = Math.min(getWidth(), getHeight());
    	int height = 100;
    	
    	this.setSize(600, 600);
    	
        this.setLayout(new GridLayout(8, 8,0,0));

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Color color = (row + col) % 2 == 0 ? c1 : c2; // Impar o par
                Casilla casilla = new Casilla(color,height);
                this.add(casilla);
            }
        }
    }
    
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //this.setSize(600, 600);
        Container parent = this.getParent();

        int minSize = Math.min(parent.getWidth(), parent.getHeight()) - 100;
        
        this.setSize(minSize,minSize);
        
        
    }
    
    
    
	
	

	
}
