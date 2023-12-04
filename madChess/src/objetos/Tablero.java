package objetos;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.*;


import javax.swing.*;
import javax.swing.Timer;

import juego.Boosts;
import piezas.Alfil;
import piezas.Caballo;
import piezas.Reina;
import piezas.Torre;
import utils.Audio;
import utils.Configuracion;
import utils.Session;



public class Tablero extends JPanel{
	private static final long serialVersionUID = 1L;
	protected Color c1 = new Color(234,236,209);
	protected Color c2 = new Color(80, 150, 167);
	protected ArrayList<Casilla> casillas = new ArrayList<Casilla>();


	protected JPanel casillasDiv = new JPanel();
	public JPanel tableroDiv = new JPanel(); //Panel por encima de las casillas
	public JLabel dragImg = new JLabel(); // Label que va a actuar como img dentro de tableroDiv
	private JLabel animacion = new JLabel(); // Label que va a actuar como img dentro de tableroDiv
	
	
	//Eventos mouse variables
	protected Casilla lastCasilla;
	protected Casilla curCasilla;


	public Casilla prevCasilla;
	public Boolean dragging = false;
	
	private JPanel promocionPanel;
	
	
	private JButton promReina = new JButton("Reina");
	private JButton promAlfil = new JButton("Alfil");
	private JButton promCaballo = new JButton("Caballo");
	private JButton promTorre = new JButton("Torre");
	private Jugador nowPlaying;

	
    public Tablero() { 

    	/*
    	 * DEFINICIONES DE LA VENTANA
    	 */
    	
    	this.setBackground(new Color(16,16,16)); //FIXME Solo devería estar declarado en un sitio
    	this.setLayout(null);
    	tableroDiv.setLayout( null );
        dragImg.setSize(100,100); 
    	
    	casillasDiv.setSize(this.getSize());
    	tableroDiv.setSize(this.getSize());
    	
    	casillasDiv.setLayout(new GridLayout(8, 8,0,0));
    	casillasDiv.setBackground(new Color(60,60,60));
    	
    	tableroDiv.setBackground(Color.magenta); 
    	tableroDiv.setOpaque(false); // Hace el panel de encima transparente
    	tableroDiv.add(dragImg);
    	tableroDiv.add(animacion);
    	
    	animacion.setVisible(false);
    	
    	
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
        	
        	
        	@Override
        	public void mouseMoved(MouseEvent e) {//Iluminamos la casilla
        		curCasilla = getCurCasilla(e);
                
                if (curCasilla!=prevCasilla) {
                	try {
                	prevCasilla.setHover(false);} catch (Exception e2) {}
                	
                	curCasilla.setHover(true);
                	prevCasilla = curCasilla;
                }
                
        	}
        	
		});
        
        tableroDiv.addMouseListener(new MouseAdapter() {
		
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		Boosts.checkBoosts(getCurCasilla(e),nowPlaying);
        		
        	}
        
        });
        
        
    }
    

	public void arrastrarPieza(MouseEvent e, ArrayList<Casilla> movimientosPosibles) {
		curCasilla = getCurCasilla(e);
		if (lastCasilla==null) {lastCasilla = prevCasilla;}
		
		if (lastCasilla!=curCasilla) { //Ilumina la casilla que esta debajo de la pieza
			lastCasilla.iluminarCasilla(false);
			lastCasilla = curCasilla;
			lastCasilla.iluminarCasilla(true);

		}
		
		if (prevCasilla.getPieza()!=null && !dragging ) {
			
			if ((nowPlaying==null || (nowPlaying.getIsWhite()!=prevCasilla.getPieza().getIsWhite()))&&!Configuracion.DEBUG_MODE) {return;}
			
			
			dragging = true;
			prevCasilla.setDragging(true);
			Image piezaImg = prevCasilla.getPieza().getImg().getImage();
    		int escala = prevCasilla.imgSize;
    		ImageIcon imgReEscalada = new ImageIcon(piezaImg.getScaledInstance(escala, escala, Image.SCALE_SMOOTH));
    		dragImg.setLocation(e.getX(),e.getY());
    		dragImg.setIcon(imgReEscalada);
    		
    		
    		for(Casilla casillaDisp: movimientosPosibles) {
    			casillaDisp.setDisponible(true);
    		}
		}
		
		
		int imgOffset = dragImg.getWidth()/2;
		dragImg.setLocation(e.getX()-imgOffset,e.getY()-imgOffset);
		
		
		if (Session.getDatosPartida().getModoDeJuego().equals("online")) {
			//Enviamos el movimiento al resto de clientes
			try {Session.getCtsConnection().postMouseDragged(e,prevCasilla);} catch (IOException e1) {e1.printStackTrace();}
	        
		}
		
	}
    
    
    public void arrastrarPieza(MouseEvent e) {
    	if (prevCasilla.getPieza()!=null) {
        	ArrayList<Casilla> casillasDisp = prevCasilla.getPieza().getCasillasDisponibles(prevCasilla,casillas);
        	arrastrarPieza(e,casillasDisp);
    	}

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

	public void initPromocion(Casilla promCasilla, MouseEvent e) { //En principio no son alter
		// FIXME : Para la versión final la pieza se tiene que promocionar en Partida no en Tablero
		promocionPanel.setLocation(e.getX(),e.getY());
		promocionPanel.setVisible(true);
		repaint();
		
	    ActionListener promocionActionListener = new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            Object source = e.getSource();
	            
	            if (source == promReina) {
	                promCasilla.setPieza(new Reina(promCasilla.getPieza().getIsWhite(),false));
	            } else if (source == promAlfil) {
	                promCasilla.setPieza(new Alfil(promCasilla.getPieza().getIsWhite(),false));
	            } else if (source == promCaballo) {
	                promCasilla.setPieza(new Caballo(promCasilla.getPieza().getIsWhite(),false));
	            } else if (source == promTorre) {
	                promCasilla.setPieza(new Torre(promCasilla.getPieza().getIsWhite(),false));
	            }

	            promocionPanel.setVisible(false);
	            
	            // Eliminar el ActionListener para que no se ejecute nuevamente
	            promReina.removeActionListener(this);
	            promAlfil.removeActionListener(this);
	            promCaballo.removeActionListener(this);
	            promTorre.removeActionListener(this);
	        }
	    };

	    promReina.addActionListener(promocionActionListener);
	    promAlfil.addActionListener(promocionActionListener);
	    promCaballo.addActionListener(promocionActionListener);
	    promTorre.addActionListener(promocionActionListener);
	}

	public void setCurPlayer(Jugador jugador) {
		this.nowPlaying = jugador;
	}
	
	public Casilla getPrevCasilla() {
		return prevCasilla;
	}

	private Pieza piezaDraggOnline;
	private Casilla casillaOnline;
	
	
	public void resetDraggPieza() {
		dragImg.setIcon(null);
		casillaOnline.setPieza(piezaDraggOnline);
	}

	public void setDragPieza(Casilla casillaLocal) {
		Image piezaImg = casillaLocal.getPieza().getImg().getImage();
		piezaDraggOnline = casillaLocal.getPieza();
		casillaOnline = casillaLocal;
		int escala = casillaLocal.imgSize;
		ImageIcon imgReEscalada = new ImageIcon(piezaImg.getScaledInstance(escala, escala, Image.SCALE_SMOOTH));
		casillaLocal.setPieza(null);
		dragImg.setIcon(imgReEscalada);
		
	}
	
	public void setDraggPiezaPos(String pos) {
		String[] xy = pos.split(";");
		 int x = Integer.parseInt(xy[0]);
		 int y = Integer.parseInt(xy[1]);
		dragImg.setLocation(x,y);
				
	}
	
    public void initAnimacionExplosion(Casilla casilla) {
    	
    	//AUDIO 
    	Audio.play("explosion.wav");
    	
        ImageIcon explosionIcon = new ImageIcon(getClass().getResource("../srcmedia/explosión.gif"));
        animacion.setIcon(explosionIcon);
        animacion.setSize(explosionIcon.getIconWidth(), explosionIcon.getIconHeight());
        animacion.setVisible(true);

        Point pos = getPosCasillaTablero(casilla);

        int x = pos.x - animacion.getWidth() / 2; //quitamos la mitad para centrar la pos de la img al centro 
        int y = pos.y - animacion.getHeight() / 2;
        
        animacion.setLocation(x, y);

        Timer timer = new Timer(550, e -> animacion.setVisible(false));//Para la animación
        timer.setRepeats(false); 
        timer.start();
    }

	
	public Point getPosCasillaTablero(Casilla casilla) {
        double tamanoCasilla = casillasDiv.getSize().getWidth() / 8;

        int fila = casilla.getFila();
        char columna = casilla.getColumna();

        int x = (int) (columna - 'A') * (int) tamanoCasilla;
        int y = fila * (int) tamanoCasilla;

        return new Point(x, y);
    }
	
	

	
}