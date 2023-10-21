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
	
	protected Casilla curCasilla;
	
	protected Casilla prevCasilla;
	
	
    public Tablero() {    	
    	this.setSize(600, 600);
    	this.setLayout(null);
    	
    	
    	casillasDiv.setSize(this.getSize());
    	tableroDiv.setSize(this.getSize());
    	
    	
    	
    	casillasDiv.setLayout(new GridLayout(8, 8,0,0));
    	
    	
    	
    	casillasDiv.setBackground(new Color(60,60,60));
    	
    	
    	tableroDiv.setBackground(Color.magenta); 
    	tableroDiv.setOpaque(false); // Hace el panel de encima transparente
    	
    	dragImg.setIcon(null);
    	tableroDiv.add(dragImg);
    	

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
       
        tableroDiv.addMouseMotionListener(new MouseAdapter() {
        	
        	
        	
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
        		
        			prevCasilla.setDragging(true);
        		
        		
        		

        		Image piezaImg = prevCasilla.getPieza().getImg().getImage();
        		int escala = prevCasilla.imgSize;
        		ImageIcon imgReEscalada = new ImageIcon(piezaImg.getScaledInstance(escala, escala, Image.SCALE_SMOOTH));
        		
        		
        		dragImg.setIcon(imgReEscalada);

        		
				dragImg.setLocation(e.getX(),e.getY());
        		
        				
        	}
        	
        	
        	
        	
        	
        	
        	

		});
        
       
        tableroDiv.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseReleased(MouseEvent e) {
        		System.out.println("Mouse released");
        		
        		if(prevCasilla != null) {
        		Pieza pieza= prevCasilla.getPieza();
        		prevCasilla.eliminarPieza();
        		curCasilla = getCurCasilla(e);
        		curCasilla.setPieza(pieza);
        		dragImg.setIcon(null);
        		
        	}
        		
        }
        
        });

        
        
    }
    
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