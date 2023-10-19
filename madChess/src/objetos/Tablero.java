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
import java.awt.Panel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
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
	protected ArrayList<Casilla> casillas = new ArrayList<Casilla>();


	protected Panel casillasDiv = new Panel();
	protected Panel tableroDiv = new Panel(); //Panel por encima de las casillas
	
	
	
    public Tablero() {    	
    	this.setSize(600, 600);
    	this.setLayout(null);
    	this.setBackground(Color.blue);
    	
    	
    	
    	casillasDiv.setLayout(new GridLayout(8, 8,0,0));
    	casillasDiv.setSize(this.getSize());
    	
    	tableroDiv.setBackground(Color.magenta);
    	tableroDiv.setSize(this.getSize());


        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) { 
            	
            	// Para cada pos de columa dentro de cada una de las filas creamos una casilla , si es par blanca y si no negra
                Color color = (row + col) % 2 == 0 ? c1 : c2;
                Casilla casilla = new Casilla(color,row,col);
                casillasDiv.add(casilla);
                casillas.add(casilla);
            }
        }
        
        

        
        this.add(tableroDiv);
        this.add(casillasDiv);


        
        
    }
    
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // igual hay que hacer un debouncer ns la verdad
        Container ventana = this.getParent();
        int minSize = Math.min(ventana.getWidth(), ventana.getHeight()) - 100;
        
        minSize = (minSize < 400) ? 400 : minSize; // Asi el tamanyo minimo es 400
        this.setSize(minSize,minSize);
        this.casillasDiv.setSize(this.getSize());
        this.tableroDiv.setSize(this.getSize());
        
    }
    
    
    
	
	

	
}
