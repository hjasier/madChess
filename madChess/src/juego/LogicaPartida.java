package juego;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;

import javax.swing.*;
import javax.swing.text.*;

import componentes.InfoMsg;
import componentes.userInfo;
import componentes.userPuntos;

import java.awt.*;
import objetos.Casilla;
import objetos.Jugador;
import objetos.Movimiento;
import objetos.Pieza;
import objetos.Tablero;
import objetos.Usuario;
import piezas.Alfil;
import piezas.Caballo;
import piezas.Peon;
import piezas.Reina;
import piezas.Rey;
import piezas.Torre;
import utils.Audio;
import utils.Configuracion;
import utils.Escalador;
import utils.Infos;
import utils.Session;
import ventanas.Juego;
import database.GestorDB;

/*
 * VAMOS A USAR ESTA CLASE LÓGICAPARTIDA COMO UN OBJETO TABLERO PARA NO TENER QUE USAR LA CLASE TABLERO QUE TIENE FRONTEND
 */

public class LogicaPartida {
	private Boolean DEBUG_MODE = Configuracion.DEBUG_MODE; // Si activado, no se tiene en cuenta el orden de los turnos ni a donde se puede mover una pieza
	protected ArrayList<Casilla> casillas;
	protected ArrayList<Casilla> casillasDiponibles;
	protected ArrayList<Pieza> piezasComidas = new ArrayList<>();
	protected HashMap<String,Pieza> piezas = new HashMap<String,Pieza>();
	private HashMap<Pieza, ArrayList<Casilla>> piezasDefensa;
	protected Tablero tablero;
	protected Juego ventana;
	private Jugador curPlayer; //Jugador actual
	protected ArrayList<Jugador> jugadores;
	protected Bot bot;
	

	protected DatosPartida datosPartida;
	
	private ArrayList<String> infoTurno = new ArrayList<String>();
	
	protected Color secondaryColor = new Color(236,212,146);
	
	
	
	public LogicaPartida() {
		Session.setPartida(this);
		
		this.ventana = Session.getVentana().getPanelJuego();
		this.datosPartida = Session.getDatosPartida();
		if (datosPartida.jugadores!=null) {this.jugadores = datosPartida.jugadores;}
		this.tablero = ventana.getTablero();
		casillas = tablero.getCasillas();
		initPlayers();
		resetearVentana();
		
	        
		this.ventana.getTablero().tableroDiv.add(tablero.promocionPanel);
	        
		
		
        tablero.tableroDiv.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseReleased(MouseEvent e) {
        		if (datosPartida.getIsTerminada()) {return;};
        		resetMouseSocket();
        		Casilla curCasilla = tablero.getCurCasilla(e);
        		moverPiezaTablero(tablero.prevCasilla,curCasilla);
        		tablero.dragging = false;	
        		
        	}
        	
        	
        
        });
        
        tablero.tableroDiv.addMouseMotionListener(new MouseAdapter() {
        	
            @Override
            public void mouseDragged(MouseEvent e) {
                Casilla prevCasilla = tablero.getPrevCasilla();
                Casilla curCasilla = tablero.getCurCasilla(e);
                
                
                if(prevCasilla.getIsHielo()) {return;}
                if (datosPartida.getIsTerminada()) {return;};
                
                if (DEBUG_MODE||(!checkJaque() || prevCasilla.getPieza() instanceof Rey)) {
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
        
        
        

        
        
        ventana.initWindow();
        ventana.getPanelBoost().reloadBoosts(curPlayer);
        
        

        //addInfo(Infos.NAMESEP+" "+curPlayer.getUsuario().getUsername()+" empieza la partida con blancas "+Infos.NAMESEP, new Color(236,212,146), true,false);
        addInfo(Infos.PIEZAS+" 🐸", new Color(236,212,146), true,false);
        printMovimiento();
		
        
        
	}
	












	private void initPlayers() {
		int initTime = 600;

		
		if (datosPartida.isBotGame()) {
			datosPartida.setJugador(new Usuario("BOT"));
			bot = new Bot(datosPartida.getJugadores().get(1));
		}
		
		Jugador user1 = jugadores.get(0);
		Jugador user2 = jugadores.get(1);
		
		
		user1.setIsWhite(true);
		
		
		
		user1.setPlayColor(new Color(255,100,17));
		user2.setPlayColor(new Color(236,146,225));
		
		
        curPlayer = user1;
        tablero.setCurPlayer(curPlayer);
        
        
		for (Jugador player:jugadores) {
			player.setTiempoRestante(initTime);
		}
		
		iniciarTemporizador();
		
		ventana.getPanelUsuario().setUsuario(user2);
		ventana.getPanelUsuario2().setUsuario(user1);
		
		ventana.getPanelUsuario().setTemp(initTime);
		ventana.getPanelUsuario2().setTemp(initTime);
		
		ventana.getPanelUsuario2().startTemp();
	}

	
	
	

	protected void moverPiezaTablero(Casilla prevCasilla, Casilla curCasilla) {
	    if (validarArrastrePieza(prevCasilla)) {
	        Pieza pieza = prevCasilla.getPieza();
	        Pieza piezaComida = null;
	        ArrayList<Casilla> casillasDisp = pieza.getCasillasDisponibles(prevCasilla, casillas);

	        if (!cumpleCondicionesMovimiento(prevCasilla, casillasDisp)) {
	            return;
	        }
	        
	        if (prevCasilla != curCasilla && (casillasDisp.contains(curCasilla)||DEBUG_MODE)){ // Si la casilla está entre las disponibles y no es la casilla de la que sale
	        	esMovimientoEspecial(prevCasilla, curCasilla, pieza);//Si hay algún movimeinto especial se ejecuta antes de mover la pieza normal
	        	movimientoEstandar(prevCasilla, curCasilla, pieza, piezaComida);
	        }
	        resetAgarrarPieza(prevCasilla, casillasDisp);

	        
	    }
	}
	
	

	private void movimientoEstandar(Casilla prevCasilla, Casilla curCasilla, Pieza pieza, Pieza piezaComida) {
		if (curCasilla.getPieza() != null) {
	        piezaComida = curCasilla.getPieza();
	    }
	    moverPieza(prevCasilla, curCasilla, null, pieza);
	    checkAlters(prevCasilla, curCasilla, pieza, piezaComida);
	    initEventosPosMovimiento(prevCasilla, curCasilla, piezaComida, pieza);
	}





	
	private void esMovimientoEspecial(Casilla prevCasilla, Casilla curCasilla, Pieza pieza) {
	    
	    if (checkEnroqueCorto(pieza, curCasilla)) {
	        ejecutarEnroque(prevCasilla, curCasilla, 1, Infos.ENROQUE_CORTO);
	    } else if (checkEnroqueLargo(pieza, curCasilla)) {
	    	ejecutarEnroque(prevCasilla, curCasilla, -2, Infos.ENROQUE_LARGO);
	    } else if (checkPromotion(pieza, curCasilla)) {
	    	ejecutarPromocion(curCasilla);
	    }
	}
	


	private void ejecutarEnroque(Casilla prevCasilla, Casilla curCasilla, int casillaIndx, String mensaje) {
		int torreIndx= casillas.indexOf(curCasilla)+casillaIndx;
	    Pieza torre = casillas.get(torreIndx).getPieza();
	    casillas.get(casillas.indexOf(curCasilla) - (1*(casillaIndx/Math.abs(casillaIndx)))).setPieza(torre);//Ponemos la torre en su nueva posicion
	    casillas.get(torreIndx).setPieza(null);//Borramos la torre de donde esta ahora
	    addInfo(mensaje, secondaryColor, false, false);
	}



	private void ejecutarPromocion(Casilla curCasilla) {
		tablero.initPromocion(curCasilla);
		addInfo(Infos.PROMOCION,secondaryColor,false,false);
	}



	
	
	private void resetAgarrarPieza(Casilla prevCasilla, ArrayList<Casilla> casillasDisp) {
		prevCasilla.setDragging(false); //Mientras dragging es true , la pieza sigue en la casilla pero no se dibuja
		tablero.dragImg.setIcon(null); // Borramos la img del panel superior

		for(Casilla casillaDisp: casillasDisp) {//Borramos los puntos de las casillas
			casillaDisp.setDisponible(false); 
		} 
		
	}




	private void moverPieza(Casilla prevCasilla, Casilla curCasilla, Pieza piezaAnt, Pieza pieza) {
   		prevCasilla.setPieza(piezaAnt); // Quitamos la pieza de la casilla anterior y la metemos en la nueva
   		curCasilla.setPieza(pieza);
   		pieza.setPMoved(); //Seteamos el piezaMoved en true
	}






	private void initEventosPosMovimiento(Casilla prevCasilla, Casilla curCasilla, Pieza piezaComida, Pieza pieza) {
		newInfoMov(prevCasilla,curCasilla,piezaComida,pieza);
		
		new Thread(() -> {
	        guardarMovimiento(prevCasilla, curCasilla, piezaComida, pieza);
	    }).start();
		
		initPiezaAccion(piezaComida);
		
   		setNextPlayer();// Cambiamos el jugador y paramos su temporizador
   		checkFinPartida();
   		Boosts.updateBoost();
   		checkReyInJaque();
		ventana.getPanelBoost().reloadBoosts(curPlayer);
		
		
	}
	
	
	public void initPiezaAccion(Pieza piezaComida) {
		if(piezaComida == null) {
			Audio.play("move-self.wav");
		}else {
			addPiezaComida(piezaComida);
			
			Audio.play("capture.wav");
		}
	}

	
	public void addPiezaComida(Pieza pieza) {
		
	    ventana.getPanelUsuario2().setPuntos(jugadores.get(1), pieza);
	    	
	    ventana.getPanelUsuario().setPuntos(jugadores.get(0), pieza);
	    
	  
		
	}



	private void checkAlters(Casilla prevCasilla, Casilla curCasilla, Pieza pieza, Pieza piezaComida) {
		if(pieza instanceof Alfil && ((Alfil) pieza).isAlter() && piezaComida != null) {
            // Revertimos el movimiento
    		prevCasilla.setPieza(pieza);
    	    curCasilla.setPieza(null);
   		}
		if(pieza instanceof Reina && ((Reina) pieza).isAlter() && piezaComida != null) {
			ArrayList<Casilla> casillasIntermedias = calcularCasillasIntermedias(prevCasilla,curCasilla);
			String kills = Infos.KILL;
			for(Casilla casilla : casillasIntermedias ) {
				if (casilla.getPieza()!=null){
					casilla.setPieza(null);
					kills+=Infos.KILL;
				}
			}
			if (!casillasIntermedias.isEmpty()) {addInfo(" "+kills); }
		}
		
		if(pieza instanceof Torre && ((Torre) pieza).isAlter()) {
			ArrayList<Casilla> casillasAfectadas = casillasAfectadas(prevCasilla,curCasilla);
			String kills = Infos.ONDA_EXPANSIVA;
			for(Casilla casilla : casillasAfectadas ) {
				if(casilla.getPieza()!= null)
				{kills+=Infos.KILL;
				killPieza(casilla);
				}
			}
			addInfo(" "+kills);
			
		}
		
		
		//Miramos si el rey del oponente queda en jaque
		int newIndex = (jugadores.indexOf(curPlayer)+1 >= jugadores.size())? 0:jugadores.indexOf(curPlayer)+1;
		Jugador oponente = jugadores.get(newIndex);
		
		Casilla curReyCasilla = getCasillaPieza(oponente.getRey());
		if (oponente.getRey().reCheckJaqueStatus( curReyCasilla, casillas) && oponente.getRey().reyIsAlter()) {
			prevCasilla.setPieza(pieza);
    	    curCasilla.setPieza(null);
    	    oponente.getRey().setReyIsAlter(false);
		}
	
	}


	private void killPieza(Casilla casilla) {
		casilla.setPieza(null);
		//tablero.animateAsync(casilla,"kill4",0.3,0.7);
		//tablero.animateAsync(casillas.get(0),"kill4",1,1);
	}



	public void moverPiezaOnline(Casilla casillaSalida,Casilla casillaLlegada) {
		casillaSalida = getCasilla(casillaSalida.getFila(), casillaSalida.getColumna());
		casillaLlegada = getCasilla(casillaLlegada.getFila(), casillaLlegada.getColumna());
		moverPiezaTablero(casillaSalida, casillaLlegada);
	}

	
	protected void resetMouseSocket() {
		if (datosPartida.isOnline()) {
			try {
				Session.getCtsConnection().postResetDragg();} 
			catch (IOException e1) {
				e1.printStackTrace();
				}
    	}
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
			Pieza pieza = casilla.getPieza();
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





	





	private void setNextPlayer() {
		pararTemporizador();
		
		int newIndex = (jugadores.indexOf(curPlayer)+1 >= jugadores.size())? 0:jugadores.indexOf(curPlayer)+1;
		tablero.setCurPlayer(jugadores.get(newIndex));
		curPlayer = jugadores.get(newIndex);
		
		if (curPlayer.getUsuario().getUsername().equals("BOT")) {
		    Thread botThread = new Thread(new Runnable() {
		        @Override
		        public void run() {bot.calculaNuevoMovimiento(casillas);}
		    });
		    botThread.start();
		}
		
		
		iniciarTemporizador();
		
		
	}





	private void iniciarTemporizador() {
		Date now = new Date();
		curPlayer.setInitTime(now);
		
		
	    if (jugadores.get(0)==curPlayer) {//jugador podría tener referencia al panel y no tener q hacer esto
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
	    
	    //FIXME: ESTO NO FUNCIONARÍA CON MÁS DE 2 JUGADORES
	    if (jugadores.get(0)==curPlayer) {
	    	ventana.getPanelUsuario2().setTemp(restantes);
	    }
	    else {
	    	ventana.getPanelUsuario().setTemp(restantes);
	    }
	    
	
	}

	
    private ArrayList<Casilla> calcularCasillasIntermedias(Casilla casillaInicio, Casilla casillaFin) {
        ArrayList<Casilla> casillas = new ArrayList<Casilla>();

        int columnDiff = casillaFin.getColumna() - casillaInicio.getColumna();
        int rowDiff = casillaFin.getFila() - casillaInicio.getFila();

        int minMoves = Math.max(Math.abs(columnDiff), Math.abs(rowDiff));

        char currentColumn = casillaInicio.getColumna();
        int currentRow = casillaInicio.getFila();

        for (int i = 1; i < minMoves; i++) {
            currentColumn += Integer.compare(columnDiff, 0);
            currentRow += Integer.compare(rowDiff, 0);
            
            Casilla casilla = getCasilla(currentRow,currentColumn);
            casillas.add(casilla);
        }

        return casillas;
    }

    
    
    
    //FIXME : SIMPLIFICAD ESTO POR FAVOR
    private ArrayList<Casilla> casillasAfectadas(Casilla origen, Casilla destino) {
        int[][] direcciones = {
            {0, 1},   // derecha (0)
            {0, -1},  // izquierda (1)
            {-1, 0},  // abajo (2)
            {-1, -1},  // diagonal inferior izquierda (3)
            {-1, 1},  // diagonal inferior derecha (4)
            {1, 0},   // arriba (5)
            {1, 1},   // diagonal superior derecha (6)
            {1, -1}  // diagonal superior izquierda (7)
        };
        int[][] direccionesLaterales = {
                {0, 1},   // derecha (0)
                {1, 1},   // diagonal superior derecha (1)
                {-1, 1},  // diagonal inferior derecha (2)
                
                {-1, -1},  // diagonal inferior izquierda (3)
                {0, -1},  // izquierda (4)
                {1, -1}  // diagonal superior izquierda (5)
            };
        int[][] direccionesDiagonales = {
        		
                //(aunque parezca contradictorio funciona asi)
        		//diagonal arriba dercha 
        		{0, 1},   // derecha (0)
        		{-1, 0},  // abajo (1)
        		{-1, 1},  // diagonal inferior derecha (2)
                
                //diagonal arriba izquierda
        		{-1, 0},  // abajo (3)
                {0, -1},  // izquierda (4)
                {-1, -1},  // diagonal inferior izquierda (5)
                
                //diagonal abajo derecha
                {1, 0},   // arriba (6)
                {1, 1},   // diagonal superior derecha (7)
                {0, 1},   // derecha (8)
                
              //diagonal abajo izquierda
                {1, 0},   // arriba (9)
                {0, -1},  // izquierda (10)
                {1, -1}  // diagonal superior izquierda (11)
            };

        ArrayList<Casilla> afectadas = new ArrayList<>();

        int diferenciaFilas = destino.getFila() - origen.getFila();
        int diferenciaColumnas = destino.getColumna() - origen.getColumna();
        
        // Si el movimiento es hacia arriba
        if (diferenciaFilas == -1 && diferenciaColumnas == 0) {
            // Agregar las casillas superiores a la casilla destino
            for (int i = 5; i <= 7; i++) { // Las direcciones correspondientes a arriba
                int nuevaFila = destino.getFila() - direcciones[i][0];
                char nuevaColumna = (char) (destino.getColumna() - direcciones[i][1]);

                // Verificar si la nueva casilla está dentro del tablero
                if (nuevaFila >= 0 && nuevaFila <= 7 && nuevaColumna >= 'A' && nuevaColumna <= 'H') {
                    afectadas.add(getCasilla(nuevaFila, nuevaColumna));
                }
            }
        } 
        // Si el movimiento es hacia abajo
        else if(diferenciaFilas == 1 && diferenciaColumnas == 0){
        	// Agregar las casillas superiores a la casilla destino
            for (int i = 2; i <= 4; i++) { // Las direcciones correspondientes a arriba
                int nuevaFila = destino.getFila() - direcciones[i][0];
                char nuevaColumna = (char) (destino.getColumna() - direcciones[i][1]);

                // Verificar si la nueva casilla está dentro del tablero
                if (nuevaFila >= 0 && nuevaFila <= 7 && nuevaColumna >= 'A' && nuevaColumna <= 'H') {
                    afectadas.add(getCasilla(nuevaFila, nuevaColumna));
                }
            }
        }
        // Si el movimiento es hacia la derecha
        else if (diferenciaFilas == 0 && diferenciaColumnas == 1){
        	// Agregar las casillas superiores a la casilla destino
            for (int i = 0; i <= 2; i++) { // Las direcciones correspondientes a arriba
                int nuevaFila = destino.getFila() + direccionesLaterales[i][0];
                char nuevaColumna = (char) (destino.getColumna() + direccionesLaterales[i][1]);

                // Verificar si la nueva casilla está dentro del tablero
                if (nuevaFila >= 0 && nuevaFila <= 7 && nuevaColumna >= 'A' && nuevaColumna <= 'H') {
                    afectadas.add(getCasilla(nuevaFila, nuevaColumna));
                }
            }
        }
        // Si el movimiento es hacia la izquierda
        else if (diferenciaFilas == 0 && diferenciaColumnas == -1){
        	// Agregar las casillas superiores a la casilla destino
            for (int i = 3; i <= 5; i++) { // Las direcciones correspondientes a arriba
                int nuevaFila = destino.getFila() + direccionesLaterales[i][0];
                char nuevaColumna = (char) (destino.getColumna() + direccionesLaterales[i][1]);

                // Verificar si la nueva casilla está dentro del tablero
                if (nuevaFila >= 0 && nuevaFila <= 7 && nuevaColumna >= 'A' && nuevaColumna <= 'H') {
                    afectadas.add(getCasilla(nuevaFila, nuevaColumna));
                }
            }
        }
        // Si el movimiento es hacia arriba derecha
        else if (diferenciaFilas == -1 && diferenciaColumnas == 1){
        	// Agregar las casillas superiores a la casilla destino
            for (int i = 0; i <= 2; i++) { // Las direcciones correspondientes a arriba
                int nuevaFila = destino.getFila() + direccionesDiagonales[i][0];
                char nuevaColumna = (char) (destino.getColumna() + direccionesDiagonales[i][1]);

                // Verificar si la nueva casilla está dentro del tablero
                if (nuevaFila >= 0 && nuevaFila <= 7 && nuevaColumna >= 'A' && nuevaColumna <= 'H') {
                    afectadas.add(getCasilla(nuevaFila, nuevaColumna));
                }
            }
        }
        // Si el movimiento es hacia arriba izquierda
        else if (diferenciaFilas == -1 && diferenciaColumnas == -1){
        	// Agregar las casillas superiores a la casilla destino
            for (int i = 3; i <= 5; i++) { // Las direcciones correspondientes a arriba
                int nuevaFila = destino.getFila() + direccionesDiagonales[i][0];
                char nuevaColumna = (char) (destino.getColumna() + direccionesDiagonales[i][1]);

                // Verificar si la nueva casilla está dentro del tablero
                if (nuevaFila >= 0 && nuevaFila <= 7 && nuevaColumna >= 'A' && nuevaColumna <= 'H') {
                    afectadas.add(getCasilla(nuevaFila, nuevaColumna));
                }
            }
        }
        // Si el movimiento es hacia abajo derecha
        else if (diferenciaFilas == 1 && diferenciaColumnas == 1){
        	// Agregar las casillas superiores a la casilla destino
            for (int i = 6; i <= 8; i++) { // Las direcciones correspondientes a arriba
                int nuevaFila = destino.getFila() + direccionesDiagonales[i][0];
                char nuevaColumna = (char) (destino.getColumna() + direccionesDiagonales[i][1]);

                // Verificar si la nueva casilla está dentro del tablero
                if (nuevaFila >= 0 && nuevaFila <= 7 && nuevaColumna >= 'A' && nuevaColumna <= 'H') {
                    afectadas.add(getCasilla(nuevaFila, nuevaColumna));
                }
            }
        }
        // Si el movimiento es hacia abajo izquierda
        else if (diferenciaFilas == 1 && diferenciaColumnas == -1){
        	// Agregar las casillas superiores a la casilla destino
            for (int i = 9; i <= 11; i++) { // Las direcciones correspondientes a arriba
                int nuevaFila = destino.getFila() + direccionesDiagonales[i][0];
                char nuevaColumna = (char) (destino.getColumna() + direccionesDiagonales[i][1]);

                // Verificar si la nueva casilla está dentro del tablero
                if (nuevaFila >= 0 && nuevaFila <= 7 && nuevaColumna >= 'A' && nuevaColumna <= 'H') {
                    afectadas.add(getCasilla(nuevaFila, nuevaColumna));
                }
            }
        }
        	
        	

        return afectadas;
    }
    
    

    
    

	private void guardarMovimiento(Casilla prevCasilla, Casilla curCasilla, Pieza piezaComida, Pieza pieza) {
		
		Movimiento movimiento = new Movimiento(curPlayer,prevCasilla,curCasilla,pieza,piezaComida);
		
		
		
		
		if (datosPartida.isOnline()) { //FIXME: ESTO NO DEBERÍA ESTAR AQUI
			try {
				Session.getCtsConnection().postPiezaMov(movimiento);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			datosPartida.addMovimiento(movimiento);
		}


		
	}
	
	private void newInfoMov(Casilla prevCasilla, Casilla curCasilla, Pieza piezaComida, Pieza pieza) {
		String extra = (piezaComida==null) ? " ":" "+Infos.KILL;
		
		String info = (prevCasilla.getPos()+" "+Infos.MOVE+" "+curCasilla.getPos()+extra);
		
		
		addInfo(info,Color.white,false,true);
		addInfo(Infos.NAMESEP+curPlayer.getUsuario().getUsername()+Infos.NAMESEP+" ",curPlayer.getPlayColor(),false,true);
		
		printMovimiento();
	}
	
	private void initPartidaAcabada() {
		
		addInfo(Infos.PARTIDA_TERMINADA,Color.yellow,true,true);
		printMovimiento();
		
		pararTemporizador();//parar temporizador del último jugador
		
		datosPartida.setIsTerminada(true);
		for (Casilla casilla : casillas) {
			casilla.setDisabled(true);
		}
		
		 Puntuador puntuador = new Puntuador();
		 puntuador.cambiarPuntuaciones(datosPartida);
		//TODO: desabilitar los botones de madChess tmb
		
		if (Configuracion.DB_DEBUG) {
			//Guardar datos de la partida en la db
			GestorDB.insertarPartida(datosPartida);
		}
	}




	
	
	
	
	
	
	
	
	
	
	
	//----------------- CHECKS BOOLEANOS -----------------
	
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
				(!curPieza.getIsWhite())&& curCasilla.getFila() == 7)&&(!curPieza.getIsBoosted());	
	}
	
	private boolean checkJaqueMoveValid(Casilla prevCasilla,Casilla newCasilla) {
		return (
				piezasDefensa!=null&&
				(((piezasDefensa.containsKey(prevCasilla.getPieza())&&
				  piezasDefensa.get(prevCasilla.getPieza()).contains(newCasilla))||
				prevCasilla.getPieza() instanceof Rey))	
				);
	}
	

	protected void checkFinPartida() {
		if(checkReyIsAlive()) {
			initPartidaAcabada();
			InfoMsg.alert(Infos.PARTIDA_TERMINADA_ALERT);
			
		}
	}
	
	protected boolean checkReyIsAlive() {
		return curPlayer.getRey().getCasillaParent() == null;
	}
	
	
	private boolean cumpleCondicionesMovimiento(Casilla prevCasilla,ArrayList<Casilla> casillasDisp) {
		return (
				!(prevCasilla.getIsHielo())&&
                !(prevCasilla.getPieza().getIsWhite()!=curPlayer.getIsWhite()&&!DEBUG_MODE)&&
                !(casillasDisp==null)
				);
	}
	
	private boolean validarArrastrePieza(Casilla prevCasilla) {
	    return prevCasilla != null && prevCasilla.getPieza() != null;
	}
	
	private boolean checkJaque() {
		return (
				curPlayer.getRey().reCheckJaqueStatus(getCasillaPieza(curPlayer.getRey()), casillas)
				);
	}
	
	//-----------------FIN CHECKS-----------------
	
	
	
	
	
	
	





	
	//-----------------GETTERS / SETTERS -----------------
	
	
	public void setCasillas(ArrayList<Casilla> casillas) {
		this.casillas = casillas;
	}

	
	public void setDragPieza(Casilla casilla) {
		Casilla casillaLocal = getCasilla(casilla.getFila(), casilla.getColumna());
		tablero.setDragPieza(casillaLocal);
	}

	

	public Tablero getTablero() {
		return tablero;
	}
	
	public Jugador getCurPlayer() {
		return curPlayer;
	}

	
	
	//-----------------FIN GETTERS / SETTERS -----------------
	
	

	
	
	
	
	//-----------------INIT DEL TABLERO -----------------
	
	protected void resetearVentana() {
		limpiarTablero();
		Session.getVentana().getPanelJuego().resetTextAreas();
		tablero.cargarPiezas(jugadores);
	}
	

	


	private void limpiarTablero() {
		for (Casilla casilla:casillas) {
			casilla.setPieza(null);
			casilla.setDisabled(false);
		}
	}
	
	//-----------------FIN INIT DEL TABLERO -----------------



	
	//----------------- FORMATEADOR DE MOVIMIENTOS -----------------
	
	public static String joinHtml(ArrayList<String> htmlList) {
        StringBuilder resultHtml = new StringBuilder();
        for (String htmlFragment : htmlList) {
            String modifiedFragment = htmlFragment.replace("<html>", "<text>");
            modifiedFragment = modifiedFragment.replace("</html>", "</text>");

            resultHtml.append(modifiedFragment);
        }
        return "<html>" + resultHtml.toString() + "</html>";
    }
	
	private void printMovimiento() {
		ventana.printMovimiento(joinHtml(infoTurno));
		infoTurno = new ArrayList<String>();
	}
	
	public void printMovimientoFormateado(String info) {
		ArrayList<String> recup = infoTurno;
		infoTurno = new ArrayList<String>();
		addInfo(info);
		printMovimiento();
		infoTurno = recup;
	}
	
	private void addInfo(String text) {
		addInfo(text,Color.white, false,false);
	}
	
	private void addInfo(String text, Color color, boolean bold, boolean post) {
	    StringBuilder styledText = new StringBuilder("<html>");
	    String fontName = "Arial";
	    int fontSize = Escalador.escalar(5);
	    if (bold) {
	        styledText.append("<b>");
	    }

	    styledText.append("<font color='").append(toHexString(color)).append("'");

	    // Agregar nombre de la fuente y tamaño si están presentes
	    if (fontName != null && !fontName.isEmpty()) {
	        styledText.append(" face='").append(fontName).append("'");
	    }
	    if (fontSize > 0) {
	        styledText.append(" size='").append(fontSize).append("'");
	    }

	    styledText.append(">");
	    styledText.append(text);
	    styledText.append("</font>");

	    if (bold) {
	        styledText.append("</b>");
	    }

	    styledText.append("</html>");

	    if (!post) {
	        infoTurno.add(styledText.toString());
	    } else {
	    	infoTurno.add(0, styledText.toString());
	    }
	}

	
	
	
	private String toHexString(Color color) {
	    return String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
	}
	
	//-----------------FIN FORMATEADOR DE MOVIMIENTOS -----------------



	
	
	
	}



