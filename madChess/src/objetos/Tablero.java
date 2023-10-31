package objetos;

import java.awt.*;
import java.awt.event.*;
import java.util.*;


import javax.swing.*;

import piezas.Alfil;
import piezas.Caballo;
import piezas.Reina;
import piezas.Torre;



public class Tablero extends JPanel{
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
	
	private JPanel promocionPanel;
	
	
	private JButton promReina = new JButton("Reina");
	private JButton promAlfil = new JButton("Alfil");
	private JButton promCaballo = new JButton("Caballo");
	private JButton promTorre = new JButton("Torre");
	
	
	
    public Tablero() { 

    	/*
    	 * DEFINICIONES DE LA VENTANA
    	 */
    	
    	this.setBackground(new Color(16,16,16)); //FIXME Solo devería estar declarado en un sitio
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
        
        
        
  
        
        promocionPanel = new JPanel();
        promocionPanel.setLayout(new GridLayout(4,1));
        promocionPanel.add(promReina);
        promocionPanel.add(promAlfil);
        promocionPanel.add(promCaballo);
        promocionPanel.add(promTorre);
        promocionPanel.setVisible(false);
        
        tableroDiv.add(promocionPanel);
        
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
        			prevCasilla.setDragging(true);
        			Image piezaImg = prevCasilla.getPieza().getImg().getImage();
            		int escala = prevCasilla.imgSize;
            		ImageIcon imgReEscalada = new ImageIcon(piezaImg.getScaledInstance(escala, escala, Image.SCALE_SMOOTH));
            		dragImg.setLocation(e.getX(),e.getY());
            		dragImg.setIcon(imgReEscalada);
            		
            		casillasDisp = prevCasilla.getPieza().getCasillasDisponibles(prevCasilla,casillas);
            		for(Casilla casillaDisp: casillasDisp) {
            			casillaDisp.setDisponible(true);
            		}
        		}
        		
        		
        		int imgOffset = dragImg.getWidth()/2;
				dragImg.setLocation(e.getX()-imgOffset,e.getY()-imgOffset);

        	}
		});
        

        
        
    }
    
    /*
     * MÉTODOS DEL TABLERO 
     */
    
    
    public Casilla getCurCasilla(MouseEvent e) {
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

	
	public void recalcularTamanyo() {
        Container ventana = this.getParent();
        int minSize = Math.min(ventana.getWidth(), ventana.getHeight());
        minSize = (minSize < 400) ? 400 : minSize;
        this.setSize(minSize,minSize);
        this.casillasDiv.setSize(this.getSize());
        this.tableroDiv.setSize(this.getSize());
        this.setMaximumSize(new Dimension(Integer.MAX_VALUE, minSize)); 
        this.repaint();
	}

	public void initPromocion(Casilla promCasilla, MouseEvent e) {
		// FIXME : Para la versión final la pieza se tiene que promocionar en Partida no en Tablero
		promocionPanel.setLocation(e.getX(),e.getY());
		promocionPanel.setVisible(true);
		repaint();
		
		promReina.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				promCasilla.setPieza(new Reina(promCasilla.getPieza().getIsWhite()));
				promocionPanel.setVisible(false);
			}
		});
		
		promAlfil.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				promCasilla.setPieza(new Alfil(promCasilla.getPieza().getIsWhite()));
				promocionPanel.setVisible(false);
			}
		});
		
		promCaballo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				promCasilla.setPieza(new Caballo(promCasilla.getPieza().getIsWhite()));
				promocionPanel.setVisible(false);
			}
		});
		
		promTorre.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				promCasilla.setPieza(new Torre(promCasilla.getPieza().getIsWhite()));
				promocionPanel.setVisible(false);
			}
		});
	}
    
	

	
}