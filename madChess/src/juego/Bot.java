package juego;

import java.util.ArrayList;
import java.util.HashMap;

import objetos.Casilla;
import objetos.Jugador;
import objetos.Pieza;
import objetos.Tablero;
import piezas.Rey;
import utils.Session;

public class Bot {
	
	/*
	 * ESKEMA
	 * 
	 * 1. Obtener el tablero de ajedrez en el estado actual
	 * 2. Calcular todos los movimientos posibles para el color actual
	 * 3. Para cada movimiento posible:
	 * 		4. Hacer el movimiento en una copia del tablero
	 * 		5. Calcular todos los movimientos posibles para el color contrario
	 * 		6. A cada movimiento contrario asignar una puntuación basada en una función de evaluación
	 * 7. Elegir el movimiento con la puntuación más alta (en caso de ser el turno del jugador maximizador) o más baja (en caso contrario)
	 * 8. Realizar el movimiento elegido en el tablero original
	*/
	
	LogicaPartida partida = Session.getPartida();
	ArrayList<Casilla> tablero = partida.getTablero().getCasillas();
	ArrayList<Pieza> piezasBot = new ArrayList<Pieza>();
	ArrayList<Pieza> piezasPlayer = new ArrayList<Pieza>();
	Jugador botplayer;
	
	
	public Bot() {
		getPiezasPlayers();
		
	}
	
	

		
	private void getPiezasPlayers() {
		for (Casilla casilla:tablero) {
			if ((casilla.getPieza()!=null)&&casilla.getPieza().getIsWhite() == botplayer.getIsWhite()) {
				piezasBot.add(casilla.getPieza());
			}
			else if(casilla.getPieza()!=null) {
				piezasPlayer.add(casilla.getPieza());
			}
		}
	}
	

	
//	public class BotMov {
//		public Pieza pieza;
//		public Casilla casilla;
//
//		public BotMov(Pieza pieza, Casilla casilla) {
//			this.pieza = pieza;
//			this.casilla = casilla;
//		}
//	}
//
//	private BotMov getNextMov() {
//		for (Pieza pieza : piezasBot) {
//			ArrayList<Casilla>  outcome = getPosOutcome(pieza);
//			
//		}
//		//get el mejor entre todos los outcomes
//		return null;
//	}

	
	private int minimax(ArrayList<Casilla> estadoActual, boolean turnoIsBot, int depth) {
	    if (checkFinPartida(estadoActual) || depth == 0) {
	        return Evaluador.evaluarTablero(estadoActual);
	    }

	    if (turnoIsBot) {
	        int maxEval = Integer.MIN_VALUE;

	        for (Pieza pieza : calcularPiezasJugador(estadoActual, turnoIsBot)) {
	            for (Casilla movimientoPosible : pieza.getCasillasDisponibles(pieza.getCasillaParent(), estadoActual)) {
	                ArrayList<Casilla> nuevoEstado = crearNuevoEstado(estadoActual, pieza.getCasillaParent(), movimientoPosible);
	                int eval = minimax(nuevoEstado, false, depth - 1);
	                maxEval = Math.max(maxEval, eval);
	            }
	        }

	        return maxEval;
	    } else {
	        int minEval = Integer.MAX_VALUE;

	        for (Pieza pieza : calcularPiezasJugador(estadoActual, turnoIsBot)) {
	            for (Casilla movimientoPosible : pieza.getCasillasDisponibles(pieza.getCasillaParent(), estadoActual)) {
	                ArrayList<Casilla> nuevoEstado = crearNuevoEstado(estadoActual, pieza.getCasillaParent(), movimientoPosible);
	                int eval = minimax(nuevoEstado, true, depth - 1);
	                minEval = Math.min(minEval, eval);
	            }
	        }

	        return minEval;
	    }
	}
	





	private ArrayList<Pieza> calcularPiezasJugador(ArrayList<Casilla> estadoActual, boolean curPlayerIsBot) {
		ArrayList<Pieza> piezas = new ArrayList<Pieza>();
		for (Casilla casilla:estadoActual) {
			if ((casilla.getPieza()!=null)&&(
					curPlayerIsBot&&casilla.getPieza().getIsWhite() == botplayer.getIsWhite()||
					!curPlayerIsBot&&casilla.getPieza().getIsWhite() != botplayer.getIsWhite()
					)) {
				piezas.add(casilla.getPieza());
			}
		}
		return piezas;
	}




	private ArrayList<Casilla> crearNuevoEstado(ArrayList<Casilla> estadoActual, Casilla casillaParent,Casilla movimientoPosible) {
		ArrayList<Casilla> tableroCopia = partida.getCasillasSimulacion(estadoActual);
		moverPiezaSimulada(tableroCopia,casillaParent, movimientoPosible);
		return tableroCopia;
	}




	private boolean checkFinPartida(ArrayList<Casilla> estadoActual) {
		return false;
	}




//	private ArrayList<Casilla> getPosOutcome(Pieza pieza) {
//		ArrayList<Casilla> movsPosibles = pieza.getCasillasDisponibles(pieza.getCasillaParent(),tablero);
//		ArrayList<ArrayList<Casilla>> outcomesPosibles = new ArrayList<ArrayList<Casilla>>();
//		
//		for (Casilla movimientoPosible : movsPosibles) {
//			
//			ArrayList<Casilla> tableroCopia = partida.getCasillasSimulacion(tablero);
//			moverPiezaSimulada(tableroCopia,pieza.getCasillaParent(),movimientoPosible);//Movemos la pieza a esa posibilidad
//			int[] puntuaciónMovimiento = evaluarTablero(tableroCopia); //Calculamos la puntuación de ese movimiento
//			
//			outcomesPosibles.add(tableroCopia);//REVISAR
//			
//			//Calculamos cada una de las respuestas posibles a ese movimiento y a cada respuesta le damos un valor
//			for (Pieza piezaAMover : piezasPlayer) {
//				ArrayList<Casilla> movsPosiblesPlayer = piezaAMover.getCasillasDisponibles(piezaAMover.getCasillaParent(), tableroCopia); //para esa pieza a donde se puede mover
//				
//				//para cada una de las casillas a la que esa pieza se puede mover calculamos la puntuación
//				for (Casilla movimientoPosibleRespuesta : movsPosiblesPlayer) {
//					
//					ArrayList<Casilla> tableroCopiaCopia = partida.getCasillasSimulacion(tableroCopia); //Creamos la copia de la copia del tablero
//					moverPiezaSimulada(tableroCopiaCopia, piezaAMover.getCasillaParent(), movimientoPosibleRespuesta);
//					int[] puntuaciónMovimientoRespuesta = evaluarTablero(tableroCopiaCopia);
//
//					outcomesPosibles.add(tableroCopiaCopia);//REVISAR
//				}
//			}
//		
//		}
//		
//		return null;
//	}

	
	





//
//
//
//	private void calcularMovimientosContrarios(ArrayList<Casilla> tableroCopia) {
//		for (Pieza pieza : piezasPlayer) {
//			ArrayList<Casilla> outcome = pieza.getCasillasDisponibles(pieza.getCasillaParent(), tableroCopia);
//			
//		}
//		
//	}




	private void moverPiezaSimulada(ArrayList<Casilla> simulacion,Casilla prevCasilla, Casilla newCasilla) {
		Pieza piezaSeMueve = prevCasilla.getPieza();
		int indexPrevCasilla = tablero.indexOf(prevCasilla);
		int indexNewCasilla = tablero.indexOf(newCasilla);
		
		Casilla prevCasillaSimulada = simulacion.get(indexPrevCasilla);
		Casilla newCasilalSimulada = simulacion.get(indexNewCasilla);
		prevCasillaSimulada.setPieza(null);
		newCasilalSimulada.setPieza(piezaSeMueve);
		
	}


	
	


}
