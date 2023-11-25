package juego;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;


import objetos.Casilla;
import objetos.Jugador;
import objetos.Movimiento;
import objetos.Pieza;
import objetos.Tablero;
import piezas.Alfil;
import piezas.Caballo;
import piezas.Peon;
import piezas.Reina;
import piezas.Rey;
import piezas.Torre;
import ventanas.Juego;

public class LogicaPartida {
	private Boolean DEBUG_MODE = Configuracion.DEBUG_MODE; // Si activado, no se tiene en cuenta el orden de los turnos ni a donde se puede mover una pieza

	protected ArrayList<Casilla> casillas;
	protected HashMap<String,Pieza> piezas = new HashMap<String,Pieza>();
	private HashMap<Pieza, ArrayList<Casilla>> piezasDefensa;
	protected Tablero tablero;
	protected Juego ventana;
	private Jugador curPlayer; //Jugador actual
	private Rey reyWhite;
	private Rey reyBlack;
	protected ArrayList<Jugador> jugadores;

	protected DatosPartida datosPartida;
	
	
	public LogicaPartida(Juego ventana) {
		Session.setPartida(this);
		
		this.ventana = ventana;
		this.datosPartida = Session.getDatosPartida();
		if (datosPartida.jugadores!=null) {this.jugadores = datosPartida.jugadores;}
		this.tablero = ventana.getTablero();
		
		
		casillas = tablero.getCasillas();
		resetearVentana();
		
		
		
        tablero.tableroDiv.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseReleased(MouseEvent e) {
        		Casilla curCasilla = tablero.getCurCasilla(e);
        		moverPiezaTablero(tablero.prevCasilla,curCasilla,e);
        		tablero.dragging = false;	
        		
        	}
        	
        	
        
        });
        
        tablero.tableroDiv.addMouseMotionListener(new MouseAdapter() {
        	
            @Override
            public void mouseDragged(MouseEvent e) {
                Casilla prevCasilla = tablero.getPrevCasilla();
                Casilla curCasilla = tablero.getCurCasilla(e);
                
                
                if (!checkJaque() || prevCasilla.getPieza() instanceof Rey) {
                    tablero.arrastrarPieza(e);
                } 
                else {
                	if (piezasDefensa.containsKey(prevCasilla.getPieza())) {
                		ArrayList<Casilla> movimientosPosibles = piezasDefensa.get(prevCasilla.getPieza());
                		tablero.arrastrarPieza(e,movimientosPosibles);
                	}
                	
               }
                
            }
		});
        
        
        

        initPlayers();
        
        ventana.initWindow();
        

        
        printMovimiento("*/* "+curPlayer.getNombre()+" empieza la partida con blancas */*");
		
        
        
	}
	





	private void initPlayers() {
		int initTime = 600;
		
		if (jugadores.isEmpty()) {
			jugadores.add(new Jugador("Potzon"));       
			jugadores.add(new Jugador("erGiova"));		
		}
		
		Jugador user1 = jugadores.get(0);
		Jugador user2 = jugadores.get(1);
		
		
		user1.setIsWhite(true);
		user1.setRey(reyWhite);
		user2.setRey(reyBlack);
        
        curPlayer = user1;
        tablero.setCurPlayer(curPlayer);
        
        
		for (Jugador player:jugadores) {
			player.setTiempoRestante(initTime);
		}
		
		iniciarTemporizador();
		
		ventana.getPanelUsuario().setUsuario(user2.getNombre());
		ventana.getPanelUsuario2().setUsuario(user1.getNombre());
		
		ventana.getPanelUsuario().setTemp(initTime);
		ventana.getPanelUsuario2().setTemp(initTime);
		
		ventana.getPanelUsuario2().startTemp();
	}





	protected void moverPiezaTablero(Casilla prevCasilla,Casilla curCasilla, MouseEvent e) {
		
		if(prevCasilla != null && prevCasilla.getPieza()!=null) { //Confirmamos que estamos arrastrando una pieza
			ArrayList<Casilla> casillasDisp = prevCasilla.getPieza().getCasillasDisponibles(prevCasilla, casillas);
			
			if (prevCasilla.getPieza().getIsWhite()!=curPlayer.getIsWhite()&&!DEBUG_MODE) {return;} // Si no es tu turno y mueves..
			if (casillasDisp==null) {return;}
			
			if (prevCasilla != curCasilla && (casillasDisp.contains(curCasilla)||DEBUG_MODE)){ // Si la casilla esta entre las disponibles y no es la casilla de la que sale
       		Pieza pieza= prevCasilla.getPieza();
       		Pieza piezaComida = null;
       		
       		int casillaInx = casillas.indexOf(curCasilla);
       		
       	
       		if (checkJaque() && !checkJaqueMoveValid(prevCasilla,curCasilla)){
       			printMovimiento("¡Proteje al rey! 😡");
       			return;
       		}
       		
       		if (checkEnroqueCorto(pieza,curCasilla)) {
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
       		

       		
       		prevCasilla.setPieza(null); // Quitamos la pieza de la casilla anterior y la metemos en la nueva
       		curCasilla.setPieza(pieza);
       		
       		pieza.setPMoved(); //Seteamos el piezaMoved en true
       		
       		guardarMovimiento(prevCasilla,curCasilla,piezaComida,pieza);//Guardamos el movimiento y imprimimos
       			
       		setNextPlayer();// Cambiamos el jugador y paramos su temporizador
    		
       		checkReyInJaque();
    		
			}
			 
			prevCasilla.setDragging(false);
			tablero.dragImg.setIcon(null); // Borramos la img del panel superior

    		for(Casilla casillaDisp: casillasDisp) {
    			casillaDisp.setDisponible(false); //Borramos los puntos de las casillas
    		} 

    		       
		}
		
	}


	public void moverPiezaOnline(Casilla casillaSalida,Casilla casillaLlegada) {
		
		System.out.println(casillaSalida.toString());
		casillaSalida = getCasilla(casillaSalida.getFila(), casillaSalida.getColumna());
		casillaLlegada = getCasilla(casillaLlegada.getFila(), casillaLlegada.getColumna());
		
		casillaLlegada.setPieza(casillaSalida.getPieza());
		casillaSalida.setPieza(null);		
	}

	private boolean checkJaqueMoveValid(Casilla prevCasilla,Casilla newCasilla) {
		
		
		return (
				piezasDefensa!=null&&
				(((piezasDefensa.containsKey(prevCasilla.getPieza())&&
				  piezasDefensa.get(prevCasilla.getPieza()).contains(newCasilla))||
				prevCasilla.getPieza() instanceof Rey))	
				);
	}





	protected ArrayList<Casilla> getCasillasSimulacion(ArrayList<Casilla>  casillas) { //NO HACE FALTA
		ArrayList<Casilla> casillasSimulacion = new ArrayList<Casilla>();
		for (Casilla casilla : casillas) {
			Pieza newPieza = casilla.getPieza(); // NO LO DUPLICA ASI QUE LOS CAMBIOS EN PIEZA SI SE CAMBIAN EN EL ORIGINAL
			Casilla newCasilla = new Casilla(null, casilla.getFila(), (casilla.getColumna()-'A'));
			newCasilla.setPieza(newPieza);
		    casillasSimulacion.add(newCasilla);
		}
		return casillasSimulacion;
	}


	protected HashMap<Pieza, ArrayList<Casilla>> getPiezasDefensa() {
		HashMap<Pieza, ArrayList<Casilla>> piezasDefensa = new HashMap<Pieza, ArrayList<Casilla>>();
		ArrayList<Casilla> casillasSimulacion = getCasillasSimulacion(casillas);//Crea una copia del arraylist casillas
		Casilla casillaRey = getCasillaPieza(curPlayer.getRey());
		for (Casilla casilla:casillas) {
			Pieza pieza = casilla.getPieza();//Geteamos la pieza
			if (pieza!=null&&pieza.getIsWhite()==curPlayer.getIsWhite()&&!(pieza instanceof Rey)) {
				
				ArrayList<Casilla> casillasDispPieza = pieza.getCasillasDisponibles(casilla, casillas);//Todos las casillas a las que se podría mover antes de la restricción
				ArrayList<Casilla> casillasSalva = new ArrayList<Casilla>();
				for (Casilla movimientoPosible:casillasDispPieza) {
					//Simulamos el movimiento y comprobamos si salvaría al rey
					if (checkSimulacion(casillasSimulacion,casilla,movimientoPosible,casillaRey)) {
						//La casilla salvaría al rey 
						casillasSalva.add(movimientoPosible);
						
					}
				}
				if (casillasSalva.size()>0) {	
					piezasDefensa.put(pieza, casillasSalva);
				}
			}
		}
		
		curPlayer.getRey().reCheckJaqueStatus(casillaRey, casillas); //Volvemos a setear true la amenaza del rey por que la simulación no duplica las piezas solo las casillas
		
		return piezasDefensa;
	}





	private boolean checkSimulacion(ArrayList<Casilla> simulacion,Casilla prevCasilla, Casilla movimiento, Casilla casillaRey) {
		Pieza piezaSeMueve = prevCasilla.getPieza();
		int indexPrevCasilla = casillas.indexOf(prevCasilla);
		int indexNewCasilla = casillas.indexOf(movimiento);
		int indexCasillaRey = casillas.indexOf(casillaRey);
		
		Casilla prevCasillaSimulada = simulacion.get(indexPrevCasilla);
		Casilla newCasilalSimulada = simulacion.get(indexNewCasilla);
		Casilla casillaReySimulada = simulacion.get(indexCasillaRey);
		Pieza prevPieza = newCasilalSimulada.getPieza();
		
		prevCasillaSimulada.setPieza(null);
		newCasilalSimulada.setPieza(piezaSeMueve);
		
		Rey reyASalvar = (Rey) casillaReySimulada.getPieza();
		Boolean isAmenazado = reyASalvar.reCheckJaqueStatus(casillaReySimulada, simulacion);
		
		//Restablecemos la simulación a como estaba
		prevCasillaSimulada.setPieza(piezaSeMueve);
		newCasilalSimulada.setPieza(prevPieza);
		
		return !isAmenazado;
	}





	protected Casilla getCasillaPieza(Pieza pieza) {
		for (Casilla casilla: casillas) {
			if (casilla.getPieza()==pieza) {
				return casilla;
			}
			
		}
		return null;
	}
	
    public Casilla getCasilla(int fila, char columna) {
        for (Casilla casilla : casillas) {
            if (casilla.getFila() == fila && casilla.getColumna() == columna) {
                return casilla;
            }
        }
        return null;
    }





	private void checkReyInJaque() {
		Casilla curReyCasilla = null;
		for (Casilla casilla:casillas) {
			if (casilla.getPieza()==curPlayer.getRey()) {
				curReyCasilla = casilla;
				break;
			}
		}
		if (curPlayer.getRey().reCheckJaqueStatus(curReyCasilla,casillas)) {
			piezasDefensa = getPiezasDefensa();
		}
		else{
			piezasDefensa = null;
		}
		
	}





	private boolean checkJaque() {
		return (
				curPlayer.getRey().reCheckJaqueStatus(getCasillaPieza(curPlayer.getRey()), casillas)
				);
	}





	private void setNextPlayer() {
		pararTemporizador();
		
		if (datosPartida.getModoDeJuego().equals("local")) {
			int newIndex = (jugadores.indexOf(curPlayer)+1 >= jugadores.size())? 0:jugadores.indexOf(curPlayer)+1;
			tablero.setCurPlayer(jugadores.get(newIndex));
			curPlayer = jugadores.get(newIndex);
		}
		
		
		
		iniciarTemporizador();
		
		
	}





	private void iniciarTemporizador() {
		Date now = new Date();
		curPlayer.setInitTime(now);
		
		
	    if (jugadores.get(0)==curPlayer) {
	    	ventana.getPanelUsuario2().startTemp();
	    }
	    else {
	    	ventana.getPanelUsuario().startTemp();
	    }
	}





	private void pararTemporizador() {
		Date now = new Date();
		Date initFecha = curPlayer.getInitTime(); 	
	    long segundos =  (now.getTime()-initFecha.getTime())/1000;
	    int restantes = (int) (curPlayer.getTiempoRestante()-segundos);
	    curPlayer.setTiempoRestante(restantes);
	    
	    System.out.println("Tiempo restante "+curPlayer.getNombre()+" --> "+restantes+" segundos");
	    
	    //ya se hara mejor
	    if (jugadores.get(0)==curPlayer) {
	    	ventana.getPanelUsuario2().setTemp(restantes);
	    }
	    else {
	    	ventana.getPanelUsuario().setTemp(restantes);
	    }
	    
	
	}





	private void guardarMovimiento(Casilla prevCasilla, Casilla curCasilla, Pieza piezaComida, Pieza pieza) {
		
		Movimiento movimiento = new Movimiento(prevCasilla,curCasilla,piezaComida,pieza);
		
		//Aquí guardaríamos el movimiento en la base de datos, con su user etc para las analíticas
		String extra = (piezaComida==null) ? " ":" 💀";
		printMovimiento("<"+curPlayer.getNombre()+"> "+prevCasilla.getPos()+" ⏩ "+curCasilla.getPos()+extra);
		
		if (datosPartida.getModoDeJuego().equals("online")) {
			try {
				Session.getCtsConnection().postPiezaMov(movimiento);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


		
	}
	
	
	private void printMovimiento(String movimiento) {
		ventana.printMovimiento(movimiento);
	}
	

	
	//CHECKS BOOLEANOS
	
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
	

	
	
	
	public void setCasillas(ArrayList<Casilla> casillas) {
		this.casillas = casillas;
	}





	//init del tablero
	protected void resetearVentana() {
		limpiarTablero();
		//limpiarChat();
		//limpiarMovimientos();
		cargarPiezasTablero();
	}
	
	protected void cargarPiezasTablero() { //En principio no hay alters
		reyBlack = new Rey(false);
		reyWhite = new Rey(true);
		
		casillas.get(0).setPieza(new Torre(false));
		casillas.get(1).setPieza(new Caballo(false, false));
		casillas.get(2).setPieza(new Alfil(false));
		casillas.get(3).setPieza(new Reina(false));
		casillas.get(4).setPieza(reyBlack);
		casillas.get(5).setPieza(new Alfil(false));
		casillas.get(6).setPieza(new Caballo(false, false));
		casillas.get(7).setPieza(new Torre(false));

		for (int i = 8; i <= 15; i++) {
			casillas.get(i).setPieza(new Peon(false, false));
		}

		for (int i = 48; i <= 55; i++) {
			casillas.get(i).setPieza(new Peon(true, false)); 
		}
		
		casillas.get(56).setPieza(new Torre(true));
		casillas.get(57).setPieza(new Caballo(true, false));
		casillas.get(58).setPieza(new Alfil(true));
		casillas.get(59).setPieza(new Reina(true));
		casillas.get(60).setPieza(reyWhite);
		casillas.get(61).setPieza(new Alfil(true));
		casillas.get(62).setPieza(new Caballo(true, false));
		casillas.get(63).setPieza(new Torre(true));
        
		//System.out.println(Session.getDatosPartida().getModoDeJuego());
		//System.out.println(Session.getDatosPartida().getJugadores().get(0));
		
		
		
		if (Session.getDatosPartida().getModoDeJuego().equals("online")&&Session.getDatosPartida().getJugadores().get(0).equals(Session.getCurrentUser())) {
			
			try {
				Session.getCtsConnection().postCasillas(casillas);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}






	private void limpiarTablero() {
		for (Casilla casilla:casillas) {
			casilla.setPieza(null);
		}
	}
	
	}



