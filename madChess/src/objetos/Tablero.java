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
import java.awt.event.MouseMotionAdapter;
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


	protected JPanel casillasDiv = new JPanel();
	protected JPanel tableroDiv = new JPanel(); //Panel por encima de las casillas
	
	
	
    public Tablero() {    	
    	this.setSize(600, 600);
    	this.setLayout(null);
    	
    	
    	
    	
    	
    	
    	casillasDiv.setLayout(new GridLayout(8, 8,0,0));
    	tableroDiv.setLayout(null);
    	
    	
    	casillasDiv.setBackground(new Color(60,60,60));
    	
    	
    	tableroDiv.setBackground(Color.magenta); 
    	tableroDiv.setOpaque(false); // Hace el panel de encima transparente
    	
    	


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
        
        
        tableroDiv.addMouseMotionListener(new MouseMotionAdapter() {
        	@Override
        	public void mouseMoved(MouseEvent e) {
        		
        		System.out.println("mouse en X:"+e.getX()+" Y:"+e.getY());
                double tamanyoCasilla = casillasDiv.getSize().getWidth()/8;
                int curCasillaCol = (int) (e.getX()/tamanyoCasilla);
                int curCasillaRow = (int) (e.getY()/tamanyoCasilla);
                System.out.println("casilla --> "+"Columna "+curCasillaCol+ " Fila "+curCasillaRow);
        	}
		});
        
        
        
        
      /**
       * GESTIÓN DE EVENTOS DE MOUSE	  
       */
        
        /**
        this.addMouseMotionListener(new MouseMotionAdapter() {
        	@Override
        	public void mouseMoved(MouseEvent e) {
        		System.out.println("[DIV THIS] mouse en X:"+e.getX()+" Y:"+e.getY());
        	}
		});
        
        tableroDiv.addMouseMotionListener(new MouseMotionAdapter() {
        	@Override
        	public void mouseMoved(MouseEvent e) {
        		dispatchEvent(e); // Pasa la func al padre this
        	}
		});
        
        casillasDiv.addMouseMotionListener(new MouseMotionAdapter() {
        	@Override
        	public void mouseMoved(MouseEvent e) {
        		dispatchEvent(e); // Pasa la func al padre this
        	}
		});
        
        */
        


        
        
    }
    
    public Casilla getCasilla(char columna, int fila ) {
    	for (Casilla casilla : casillas) {
    		if (columna == casilla.getColumna() && fila == casilla.getFila()) {
    			return casilla;
    		}
    	}
		return null;
    	
    }
    
	public ArrayList<Casilla> getCasillas() {
		return casillas;
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
