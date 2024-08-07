package objetos;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.swing.*;
import javax.swing.Timer;

import juego.Boosts;
import juego.DatosPartida;
import juego.modoJuego;
import piezas.Alfil;
import piezas.Caballo;
import piezas.Peon;
import piezas.Reina;
import piezas.Rey;
import piezas.Torre;
import utils.Audio;
import utils.Configuracion;
import utils.Escalador;
import utils.Session;
import utils.Themes.piezasThemes;
import utils.Themes.tableroThemes;



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
	
	public JPanel promocionPanel;
	
	
	public JButton promReina = new JButton("♔ Reina");
	public JButton promAlfil = new JButton("♗ Alfil");
	public JButton promCaballo = new JButton("♘ Caballo");
	public JButton promTorre = new JButton("♖ Torre");
	public Jugador nowPlaying;

	
    public Tablero() { 

    	/*
    	 * DEFINICIONES DE LA VENTANA
    	 */
    	
    	this.setBackground(Configuracion.BACKGROUND); //FIXME Solo devería estar declarado en un sitio
    	this.setLayout(null);
    	tableroDiv.setLayout( null );
        dragImg.setSize(100,100); 
    	
    	casillasDiv.setSize(this.getSize());
    	tableroDiv.setSize(this.getSize());
    	
    	casillasDiv.setLayout(new GridLayout(8, 8,0,0));
    	casillasDiv.setBackground(Configuracion.BACKGROUND); //Borde alrededor del tablero
    	
    	
    	tableroDiv.setOpaque(false); // Hace el panel de encima transparente
    	tableroDiv.add(dragImg);
    	tableroDiv.add(animacion);
    	
    	animacion.setVisible(false);
    	

        promocionPanel = new JPanel();
        promocionPanel.setLayout(new GridLayout(4,1));
        promocionPanel.add(promReina);
        promocionPanel.add(promAlfil);
        promocionPanel.add(promCaballo);
        promocionPanel.add(promTorre);
        
        promocionPanel.setVisible(false);
        
        this.add(promocionPanel);
        
        
    	
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
			
			
			if ((nowPlaying==null || !(nowPlaying.getIsWhite().equals(prevCasilla.getPieza().getIsWhite()))) &&!Configuracion.DEBUG_MODE) {
				return;}
			
			if (Session.getDatosPartida().isOnline()&&!nowPlaying.getUsuario().getUsername().equals(Session.getCurrentUser().getUsername())){ 
				return;
				}
			
			
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
		
		
		if (Session.getDatosPartida().getModoDeJuego().equals(modoJuego.ONLINE)) {
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
    
    

	public Casilla getCasilla(int fila ,char columna) {
    	for (Casilla casilla : casillas) {
    		if (columna == casilla.getColumna() && fila == casilla.getFila()) {
    			return casilla;
    		}
    	}
		return null;
    }
	
	public Casilla getCasilla(Casilla cas) {
		if (casillas.contains(cas)) {return cas;}
		
		char columna = cas.getColumna();
		int fila = cas.getFila();
		
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
		int tamanyoUserInfo = Escalador.escalar(55)*2;
		
        Container ventana = this.getParent();
        int minSize = Math.min(ventana.getWidth(), ventana.getHeight()-tamanyoUserInfo);
        minSize = (minSize < 400) ? 400 : minSize;
        this.setSize(minSize,minSize);
        this.casillasDiv.setSize(this.getSize());
        this.tableroDiv.setSize(this.getSize());
        this.setMaximumSize(new Dimension(Integer.MAX_VALUE, minSize)); 
        this.repaint();
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
    	
        ImageIcon explosionIcon = new ImageIcon(getClass().getResource("/srcmedia/explosión.gif"));
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
	

	public void animateAsync(Casilla casilla, String source, double sizeFactor, double speedFactor) {
	    Thread animationThread = new Thread(new Runnable() {
	        @Override
	        public void run() {
	            animate(casilla, source, sizeFactor, speedFactor);
	        }
	    });

	    animationThread.start();
	}
	

	private void animate(Casilla casilla,String source,double sizeFactor,double speedFactor) {
		sizeFactor = Escalador.escalarF((float) sizeFactor);
		
		JLabel animacion = new JLabel();
        ImageIcon animacionImg = new ImageIcon(getClass().getResource("/srcmedia/"+source+".gif"));
        
        animacion.setIcon(animacionImg);
        animacion.setSize((int) (animacionImg.getIconWidth()*sizeFactor),(int) (animacionImg.getIconHeight()*sizeFactor));
        animacion.setVisible(true);

        Point pos = getPosCasillaTablero(casilla);

        int x = pos.x - animacion.getWidth() / 2; //quitamos la mitad para centrar la pos de la img al centro 
        int y = pos.y - animacion.getHeight() / 2;
        
        animacion.setLocation(x, y);

        tableroDiv.add(animacion);
        
        int tiempo;
        
        try {
			tiempo = getDuracionGif(source);
		} catch (IOException e) {
			tiempo = 550;
		}
        System.out.println("Animando -->"+source+"--> "+tiempo+" segundos");
        
        Timer timer = new Timer((int) tiempo, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                animacion.setVisible(false);
                ((Timer) e.getSource()).stop(); // Detiene el temporizador después de la animación
            }
        });
        timer.setRepeats(false); 
        timer.start();
	}
	
	public int getDuracionGif(String source) throws IOException {
        File archivoGIF = new File(getClass().getResource("/srcmedia/" + source + ".gif").getFile());

        ImageInputStream imageInputStream = ImageIO.createImageInputStream(archivoGIF);
        Iterator<ImageReader> imageReaders = ImageIO.getImageReaders(imageInputStream);
        int numeroDeFotogramas = 0;
        
        if (imageReaders.hasNext()) {
            ImageReader imageReader = imageReaders.next();
            imageReader.setInput(imageInputStream);
            numeroDeFotogramas = imageReader.getNumImages(true);
        }

        
		return numeroDeFotogramas*120;
    }
	
	
	
	
	public void reloadCasillasColor() {
		for (Casilla casilla : casillas) {
			casilla.reloadColor();
		}
	}


	public void reloadPiezasImagen() {
		for (Casilla casilla : casillas) {
			if (casilla.getPieza() != null) {
				casilla.getPieza().reloadImg();
			}
		}
	}
	
	
	public void loadUserPreferences() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				reloadCasillasColor();
				reloadPiezasImagen();
			}
		}).start();
	}


	public void initPromocion(Casilla promCasilla) { //En principio no son alter
		promocionPanel.setPreferredSize(new Dimension(Escalador.escalar(50),Escalador.escalar(100)));
		if(promCasilla.getPieza() == null) {
			if(promCasilla.getFila()==0) 
			{promocionPanel.setBounds(getPosCasillaTablero(promCasilla).x,getPosCasillaTablero(promCasilla).y, promCasilla.getWidth(), Escalador.escalar(100));
	        }else if (promCasilla.getFila()==7) 
	        {promocionPanel.setBounds(getPosCasillaTablero(promCasilla).x,getPosCasillaTablero(promCasilla).y-25, promCasilla.getWidth(), Escalador.escalar(100));
	        }
		}else {
	        if(!promCasilla.getPieza().getIsWhite()) 
	        {promocionPanel.setBounds(getPosCasillaTablero(promCasilla).x,getPosCasillaTablero(promCasilla).y, promCasilla.getWidth(), Escalador.escalar(100));
	        }else if (promCasilla.getPieza().getIsWhite())
	        {promocionPanel.setBounds(getPosCasillaTablero(promCasilla).x,getPosCasillaTablero(promCasilla).y-25, promCasilla.getWidth(), Escalador.escalar(100));
	        }
        }
		promocionPanel.setVisible(true);
		
		
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
	    
	    repaint();
	}


	public void cargarPiezas(ArrayList<Jugador> jugadores) {
		Jugador playerWhite = jugadores.get(0);
		Jugador playerBlack = jugadores.get(1); // tal vez hay q revisar esto cuando se juegue con más jugadores
		
		System.out.println(playerWhite.getAlters());
		System.out.println(playerBlack.getAlters());
		
		Rey reyBlack = new Rey(false,playerBlack.getAlters().get(1));
		Rey reyWhite = new Rey(true,playerWhite.getAlters().get(1));
		
		
		System.out.println("LISTA DE JUGADORES : "+jugadores.toString());
		if (jugadores.size() >= 3) {
			System.out.println(jugadores.get(2).toString());
			jugadores.get(2).setRey(reyWhite);
			
        }
		
		if (jugadores.size() == 4) {
			jugadores.get(3).setRey(reyBlack);
        }
		
		
		playerWhite.setRey(reyWhite);
		playerBlack.setRey(reyBlack);
		
		//black
		casillas.get(0).setPieza(new Torre(false,playerBlack.getAlters().get(5)));
		casillas.get(1).setPieza(new Caballo(false,playerBlack.getAlters().get(2)));
		casillas.get(2).setPieza(new Alfil(false,playerBlack.getAlters().get(0)));
		casillas.get(3).setPieza(new Reina(false,playerBlack.getAlters().get(4)));
		casillas.get(4).setPieza(reyBlack);
		casillas.get(5).setPieza(new Alfil(false,playerBlack.getAlters().get(0)));
		casillas.get(6).setPieza(new Caballo(false,playerBlack.getAlters().get(2)));
		casillas.get(7).setPieza(new Torre(false,playerBlack.getAlters().get(5)));
		//peones black
		for (int i = 8; i <= 15; i++) {
			casillas.get(i).setPieza(new Peon(false,playerBlack.getAlters().get(3)));
		}
		
		
		//white
		casillas.get(56).setPieza(new Torre(true,playerWhite.getAlters().get(5)));
		casillas.get(57).setPieza(new Caballo(true,playerWhite.getAlters().get(2)));
		casillas.get(58).setPieza(new Alfil(true,playerWhite.getAlters().get(0)));
		casillas.get(59).setPieza(new Reina(true, playerWhite.getAlters().get(4)));
		casillas.get(60).setPieza(reyWhite);
		casillas.get(61).setPieza(new Alfil(true,playerWhite.getAlters().get(0)));	
		casillas.get(62).setPieza(new Caballo(true,playerWhite.getAlters().get(2)));
		casillas.get(63).setPieza(new Torre(true,playerWhite.getAlters().get(5)));
		
		for (int i = 48; i <= 55; i++) {
			casillas.get(i).setPieza(new Peon(true,playerBlack.getAlters().get(3)));
		}
	}


	

	

	
}