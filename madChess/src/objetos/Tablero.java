package objetos;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.MouseInputAdapter;

public class Tablero extends JPanel{
	private static final long serialVersionUID = 1L;
	protected Color c1 = new Color(234,236,209);
	protected Color c2 = new Color(80, 150, 167);
	
	
	
    public Tablero() {    	
    	this.setSize(600, 600);
    	
        this.setLayout(new GridLayout(8, 8,0,0));
        
        this.setBorder(new LineBorder(Color.RED, 2)); // BORDE TEMPORAL PARA DEBUG

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) { 
            	
            	// Para cada pos de columa dentro de cada una de las filas creamos una casilla , si es par blanca y si no negra
                Color color = (row + col) % 2 == 0 ? c1 : c2;
                Casilla casilla = new Casilla(color);
                this.add(casilla);
            }
        }
        
        
     // Agregar un MouseMotionListener para seguir el movimiento del cursor
        this.addMouseMotionListener(new MouseAdapter() {
        	
		
		
		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			System.out.println("test");
		}

        });
        

        
        
    }
    
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // igual hay que hacer un debouncer ns la verdad
        Container ventana = this.getParent();
        int minSize = Math.min(ventana.getWidth(), ventana.getHeight()) - 100;
        
        minSize = (minSize < 400) ? 400 : minSize; // Asi el tamanyo minimo es 400
        this.setSize(minSize,minSize);
        
        
    }
    
    
    
	
	

	
}
