package objetos;

import java.awt.*;
import java.awt.event.*;
import java.util.*;


import javax.swing.*;



public class Tablero extends JPanel{
	private static final long serialVersionUID = 1L;
	protected Color c1 = new Color(234,236,209);
	protected Color c2 = new Color(80, 150, 167);
	protected ArrayList<Casilla> casillas = new ArrayList<Casilla>();


	protected JPanel casillasDiv = new JPanel();
	protected JPanel tableroDiv = new JPanel(); //Panel por encima de las casillas
	protected JLabel dragImg = new JLabel(); // Label que va a actuar como img dentro de tableroDiv
	
	//Eventos mouse variables
	protected Casilla curCasilla;
	protected Casilla prevCasilla;
	protected Boolean dragging = false;
	
	
    public Tablero() { 

    	/*
    	 * DEFINICIONES DE LA VENTANA
    	 */
    	
    	this.setSize(600, 600);
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
        
        
        
        
		/*
		 * GESTIÓN DE EVENTOS DEL MOUSE
		 */

       
        tableroDiv.addMouseMotionListener(new MouseAdapter() {
        	protected Casilla lastCasilla;
        	
        	
        	@Override
        	public void mouseMoved(MouseEvent e) {
        		curCasilla = getCurCasilla(e);
                
                if (curCasilla!=prevCasilla) {
                	try {
                	prevCasilla.setHover(false);} catch (Exception e2) {}
                	
                	curCasilla.setHover(true);
                	prevCasilla = curCasilla;
                }
                
        	}
        	
        	
        	@Override
        	public void mouseDragged(MouseEvent e) {
        		
        		
        		curCasilla = getCurCasilla(e);
        		if (lastCasilla==null) {lastCasilla = prevCasilla;}
        		
        		if (lastCasilla!=curCasilla) { //Ilumina la casilla que esta debajo de la pieza
        			lastCasilla.iluminarCasilla(false);
        			lastCasilla = curCasilla;
        			lastCasilla.iluminarCasilla(true);

        		}
        		
        		if (prevCasilla.getPieza()!=null && !dragging) {
        			dragging = true;
        			System.out.println("RUNNING FR");
        			prevCasilla.setDragging(true);
        			Image piezaImg = prevCasilla.getPieza().getImg().getImage();
            		int escala = prevCasilla.imgSize;
            		ImageIcon imgReEscalada = new ImageIcon(piezaImg.getScaledInstance(escala, escala, Image.SCALE_SMOOTH));
            		dragImg.setLocation(e.getX(),e.getY());
            		dragImg.setIcon(imgReEscalada);
            		
            		ArrayList<Casilla> casillasDisp = prevCasilla.getPieza().getCasillasDisponibles(prevCasilla,casillas);
            		for(Casilla casillaDisp: casillasDisp) {
            			casillaDisp.setDisponible();
            		}
        		}
        		
        		
        		int imgOffset = dragImg.getWidth()/2;
				dragImg.setLocation(e.getX()-imgOffset,e.getY()-imgOffset);

        	}
		});
        
       
        tableroDiv.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseReleased(MouseEvent e) {
        		curCasilla = getCurCasilla(e);
        		if(prevCasilla != null && prevCasilla.getPieza()!=null) {
        			 if (prevCasilla != curCasilla){
                		Pieza pieza= prevCasilla.getPieza();
                		prevCasilla.setPieza(null);
                		curCasilla.setPieza(pieza);
        			}
        			prevCasilla.setDragging(false);
        			
        			 dragImg.setIcon(null);

        			 
        	}
        		dragging = false;
        		
        }
        
        });

        
        
    }
    
    /*
     * MÉTODOS DEL TABLERO 
     */
    
    
    protected Casilla getCurCasilla(MouseEvent e) {
        double tamanyoCasilla = casillasDiv.getSize().getWidth()/8;
        int curCasillaCol = (int) (e.getX()/tamanyoCasilla);
        int curCasillaRow = (int) (e.getY()/tamanyoCasilla);
        curCasilla = getCasilla(curCasillaRow, posicionToAlfabeto(curCasillaCol));
		return curCasilla;
	}
    
    

	protected Casilla getCasilla(int fila ,char columna) {
    	for (Casilla casilla : casillas) {
    		if (columna == casilla.getColumna() && fila == casilla.getFila()) {
    			return casilla;
    		}
    	}
		return null;
    	
    }
    
	private char posicionToAlfabeto(int columna) {
		return (char) ('A' + columna);
		
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