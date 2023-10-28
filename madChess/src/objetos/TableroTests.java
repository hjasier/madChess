package objetos;

import java.awt.*;
import java.awt.event.*;
import java.util.*;


import javax.swing.*;



public class TableroTests extends JPanel{
	private static final long serialVersionUID = 1L;
	protected Color c1 = new Color(234,236,209);
	protected Color c2 = new Color(80, 150, 167);
	protected ArrayList<Casilla> casillas = new ArrayList<Casilla>();


	protected JPanel casillasDiv = new JPanel();
	public JPanel tableroDiv = new JPanel(); //Panel por encima de las casillas
	public JLabel dragImg = new JLabel(); // Label que va a actuar como img dentro de tableroDiv
	
	//Eventos mouse variables
	protected Casilla curCasilla;
	public Casilla prevCasilla;
	public Boolean dragging = false;
	
	public ArrayList<Casilla> casillasDisp;
	
    public TableroTests() { 

    	/*
    	 * DEFINICIONES DE LA VENTANA
    	 */
    	
    	this.setBackground(Color.pink);
    	this.setLayout(null);
    	
    	
    	casillasDiv.setSize(this.getSize());
    	tableroDiv.setSize(this.getSize());
    	
    	casillasDiv.setLayout(new GridLayout(8, 8,0,0));
    	casillasDiv.setBackground(new Color(60,60,60));
    	
    	tableroDiv.setBackground(Color.magenta); 
    	tableroDiv.setOpaque(false); // Hace el panel de encima transparente
    	tableroDiv.add(dragImg);

    	
        this.add(tableroDiv);
        this.add(casillasDiv);
        
        
    	/*
    	 * CREACIÓN DEL TABLERO
    	 */
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) { 
            	
            	// Para cada pos de columa dentro de cada una de las filas creamos una casilla , si es par blanca y si no negra
                Color color = (row + col) % 2 == 0 ? c1 : c2;
                Casilla casilla = new Casilla(color,row,col);
                casillasDiv.add(casilla);
                casillas.add(casilla);
            }
        }
        
  
        
        
    }

    

	
	public void recalcularTamanyo() {
        Container ventana = this.getParent();
        int minSize = Math.min(ventana.getWidth(), ventana.getHeight());
        
        System.out.println(minSize);
        minSize = (minSize < 400) ? 400 : minSize;
        this.setSize(minSize,minSize);
        this.casillasDiv.setSize(this.getSize());
        this.tableroDiv.setSize(this.getSize());
        this.setMaximumSize(new Dimension(Integer.MAX_VALUE, minSize)); 
        this.repaint();
	}
    
	

	
}