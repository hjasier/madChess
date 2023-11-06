package juego;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import javax.swing.JPanel;

import objetos.Boost;
import objetos.Casilla;
import objetos.Jugador;
import objetos.Pieza;
import objetos.Tablero;
import piezas.Alfil;
import piezas.Caballo;
import piezas.Peon;
import piezas.Reina;
import piezas.Rey;
import piezas.Torre;
import ventanas.OLDVentanaJuego;
import ventanas.Juego;

public class Partida {

	protected ArrayList<Jugador> jugadores = new ArrayList<Jugador>(); // Es un arraylist por que en el futuro queremos que puedan jugar hasta 4 por turnos etc..
	
	protected int gameId;
	protected Date fecha;
	protected ArrayList<Casilla> casillas;
	protected HashMap<String,Pieza> piezas = new HashMap<String,Pieza>();
	
	protected HashMap<Boost, Boolean> boosts;
	protected Tablero tablero;
	protected Juego ventana;
	
	private Jugador nextPlayer;
	
	private int modoDeJuego;
	
	private Boolean DEBUG_MODE = true; // Si activado, no se tiene en cuenta el orden de los turnos ni a donde se puede mover una pieza

	private Rey reyWhite;

	private Rey reyBlack;
	
	/*
	 * MODOS DE JUEGO:
	 * 
	 * 0 --> NORMAL LOCAL 
	 * 1 --> NORMAL ONLINE
	 */
	
	public Partida(ArrayList<Jugador> jugadores, int gameId, Date fecha,HashMap<Boost, Boolean> boosts) {
		super();
		this.jugadores = jugadores;
		this.gameId = gameId;
		this.fecha = fecha;
		this.boosts = boosts;	
	}
	
	
	
	
	
	public Partida(Juego ventana,int modoDeJuego) {
		this.ventana = ventana;
		this.modoDeJuego = modoDeJuego;
		
		this.tablero = ventana.getTablero();
		
		casillas = tablero.getCasillas();
		cargarPiezasTablero();
		
		
		
        tablero.tableroDiv.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseReleased(MouseEvent e) {
        		Casilla curCasilla = tablero.getCurCasilla(e);
        		moverPiezaTablero(tablero.prevCasilla,curCasilla,tablero.casillasDisp,e);
        		tablero.dragging = false;	
        	}
        	
        	
        
        });
        
        tablero.tableroDiv.addMouseMotionListener(new MouseAdapter() {
        	
            @Override
            public void mouseDragged(MouseEvent e) {
                Casilla prevCasilla = tablero.getPrevCasilla();
                Casilla curCasilla = tablero.getCurCasilla(e);
                tablero.arrastrarPieza(e);
                
                if (!checkJaque()) {
                    tablero.arrastrarPieza(e);
                } 
                else {
                	tablero.arrastrarPieza(e);
                	
                	ArrayList<Pieza> piezasAmenaza = nextPlayer.getRey().piezasAmenaza(getCasillaPieza(nextPlayer.getRey()), casillas);
                	System.out.println(piezasAmenaza);
                	
                	/*
                	
                	ArrayList<Pieza> piezasProtege = nextPlayer.getRey().getPiezasProtege(casillas);
                    printMovimiento("REY EN JAQUE , movimientos restringidos");
                    if (piezasProtege.contains(prevCasilla.getPieza())) {
                        tablero.arrastrarPieza(e);
                    }
                    else{
                    	System.out.println("solo puedes mover piezas que protegan al rey");
                    }   
                    */             }
                
                //si comprobamos que el rey esta en jaque 
                // y si la pieza esta entre las piezasDisponibles para desamenazaar al rey entonces dejamos arrastrar
            }
		});
        
        
        
        /*
         * HashMap temporal de jugadores
         */
        
        jugadores.add(new Jugador("Potzon"));       
        jugadores.add(new Jugador("erGiova"));
        
        
        jugadores.get(0).setIsWhite(true);
        jugadores.get(0).setRey(reyWhite);
        jugadores.get(1).setRey(reyBlack);
        
        nextPlayer = jugadores.get(0);
        tablero.setCurPlayer(nextPlayer);
        
        
        tablero.DEBUG_MODE = DEBUG_MODE;
        
        if (DEBUG_MODE) {
        	printMovimiento("--------------------");
        	printMovimiento("MODO DEBUG: ACTIVADO");
        	printMovimiento("--------------------");
        }
        else {
        	ventana.setInterfaz(modoDeJuego);
        }
        printMovimiento("*/* "+nextPlayer.getNombre()+" empieza la partida con blancas */*");
		
        
        
	}
	




	protected Casilla getCasillaPieza(Pieza pieza) {
		for (Casilla casilla: casillas) {
			if (casilla.getPieza()==pieza) {
				return casilla;
			}
			
		}
		return null;
	}





	protected void moverPiezaTablero(Casilla prevCasilla,Casilla curCasilla,ArrayList<Casilla> casillasDisp, MouseEvent e) {
		
		
		if(prevCasilla != null && prevCasilla.getPieza()!=null) { //Confirmamos que estamos arrastrando una pieza
			
			if (prevCasilla.getPieza().getIsWhite()!=nextPlayer.getIsWhite()&&!DEBUG_MODE) {return;} // Si no es tu turno y mueves..
			if (casillasDisp==null) {return;}
			
			if (prevCasilla != curCasilla && (casillasDisp.contains(curCasilla)||DEBUG_MODE)){ // Si la casilla esta entre las disponibles y no es la casilla de la que sale
       		Pieza pieza= prevCasilla.getPieza();
       		Pieza piezaComida = null;
       		
       		int casillaInx = casillas.indexOf(curCasilla);
       		
       		if (checkJaque()) {
       			//return;//Si hay jaque y la pieza moviendose no es rey
       		}
       		
       		else if (checkEnroqueCorto(pieza,curCasilla)) {
       			Pieza torre = casillas.get(casillaInx+1).getPieza();
       			casillas.get(casillaInx-1).setPieza(torre);
       			casillas.get(casillaInx+1).setPieza(null);//Borramos la torre de donde esta ahora
       			printMovimiento("Enroque largo");
       			
       		}
       		else if (checkEnroqueLargo(pieza,curCasilla)) {
       			Pieza torre = casillas.get(casillaInx-2).getPieza();
       			casillas.get(casillaInx+1).setPieza(torre);
       			casillas.get(casillaInx-2).setPieza(null);
       			printMovimiento("Enroque largo");
       		}
       		else if (checkPromotion(pieza,curCasilla)) {
       			tablero.initPromocion(curCasilla,e);
       			printMovimiento("Pieza promocionada ");
       			// FIXME : Para la versión final la pieza se tiene que promocionar en Partida no en Tablero
       		}
  
       		else{ // Si no se cumple ninguno de los casos especiales entonces miramos si esta comiendo una pieza o simplemente moviendose
       			
       			if (curCasilla.getPieza()!=null) {
           			//entonces estamos comiento una pieza
           			piezaComida = curCasilla.getPieza();
           		}

       		}
       		
       		
       		
       		// Quitamos la pieza de la casilla anterior y la metemos en la nueva
       		prevCasilla.setPieza(null); 
       		curCasilla.setPieza(pieza);
       		
       		pieza.setPMoved(); //Seteamos el piezaMoved en true
       		guardarMovimiento(prevCasilla,curCasilla,piezaComida);//Guardamos el movimiento y imprimimos
       		
       		
       		
       		setNextPlayer();// Cambiamos el jugador
    		
       		
       		checkReyInJaque();
    		
			}
			 
			prevCasilla.setDragging(false);
			tablero.dragImg.setIcon(null); // Borramos la img del panel superior

    		for(Casilla casillaDisp: tablero.casillasDisp) {
    			casillaDisp.setDisponible(false);
    		} 
    		
    		

    		       
		}
		
	}









	private void checkReyInJaque() {
		Casilla curReyCasilla = null;
		for (Casilla casilla:casillas) {
			if (casilla.getPieza()==nextPlayer.getRey()) {
				curReyCasilla = casilla;
				break;
			}
		}
		nextPlayer.getRey().reCheckJaqueStatus(curReyCasilla,casillas);
		
	}





	private boolean checkJaque() {
		return (
				nextPlayer.getRey().getIsAmenezado()
				);
	}





	private void setNextPlayer() {
		if (modoDeJuego==0) {
			int newIndex = (jugadores.indexOf(nextPlayer)+1 >= jugadores.size())? 0:jugadores.indexOf(nextPlayer)+1;
			tablero.setCurPlayer(jugadores.get(newIndex));
			nextPlayer = jugadores.get(newIndex);
		}
		else if (modoDeJuego==1) {
			
		}
		

		
	}





	private void guardarMovimiento(Casilla prevCasilla, Casilla curCasilla, Pieza piezaComida) {
		//Aquí guardaríamos el movimiento en la base de datos, con su user etc para las analíticas
		String extra = (piezaComida==null) ? " ":" Pieza comida";
		printMovimiento("<"+nextPlayer.getNombre()+"> "+prevCasilla.getPos()+" --> "+curCasilla.getPos()+extra);
		
		
	}
	
	private void printMovimiento(String movimiento) {
		ventana.setNewMovimiento(movimiento);
	}
	





	protected boolean checkEnroqueCorto(Pieza curPieza,Casilla curCasilla) {
	
		return(
				
				(curPieza instanceof Rey)&&
				!(curPieza.getPMoved())&&
				((curCasilla == casillas.get(62))||(curCasilla == casillas.get(6)))
				
				
				);
	}
	
	protected boolean checkEnroqueLargo(Pieza curPieza,Casilla curCasilla) {
		return(
				
				(curPieza instanceof Rey)&&
				!(curPieza.getPMoved())&&
				((curCasilla == casillas.get(2))||(curCasilla == casillas.get(58)))
				
				
				);
	}
	
	
	protected boolean checkPromotion(Pieza curPieza, Casilla curCasilla) {
		return (curPieza instanceof Peon)&&
				(curPieza.getIsWhite()&& curCasilla.getFila() == 0||
				(!curPieza.getIsWhite())&& curCasilla.getFila() == 7);	
	}
	

	
	protected void cargarPiezasTablero() {
		reyBlack = new Rey(false);
		reyWhite = new Rey(true);
		
		casillas.get(0).setPieza(new Torre(false));
		casillas.get(1).setPieza(new Caballo(false));
		casillas.get(2).setPieza(new Alfil(false));
		casillas.get(3).setPieza(new Reina(false));
		casillas.get(4).setPieza(reyBlack);
		casillas.get(5).setPieza(new Alfil(false));
		casillas.get(6).setPieza(new Caballo(false));
		casillas.get(7).setPieza(new Torre(false));

		for (int i = 8; i <= 15; i++) {
			casillas.get(i).setPieza(new Peon(false));
		}

		for (int i = 48; i <= 55; i++) {
			casillas.get(i).setPieza(new Peon(true));
		}
		
		casillas.get(56).setPieza(new Torre(true));
		casillas.get(57).setPieza(new Caballo(true));
		casillas.get(58).setPieza(new Alfil(true));
		casillas.get(59).setPieza(new Reina(true));
		casillas.get(60).setPieza(reyWhite);
		casillas.get(61).setPieza(new Alfil(true));
		casillas.get(62).setPieza(new Caballo(true));
		casillas.get(63).setPieza(new Torre(true));
        }
	}



