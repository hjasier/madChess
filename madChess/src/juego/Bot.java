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
	
	
	public Bot(Jugador botplayer) {
		this.botplayer = botplayer;
	}
	
	public void calculaNuevoMovimiento(ArrayList<Casilla> estadoActual) {
		minimax(estadoActual, true, 3);
		//partida.moverPiezaTablero(curCasilla, newCasilla);
		
	}

	
	private int minimax(ArrayList<Casilla> estadoActual, boolean turnoIsBot, int depth) {
	    if (checkFinPartida(estadoActual) || depth == 0) {
	        return Evaluador.evaluarTablero(estadoActual);
	    }

	    if (turnoIsBot) {
	        int maxEval = Integer.MIN_VALUE; //-infinito

	        for (Pieza pieza : calcularPiezasJugador(estadoActual, turnoIsBot)) {
	            for (Casilla movimientoPosible : casillasDisponiblesPieza(pieza,estadoActual)) {
	                ArrayList<Casilla> nuevoEstado = crearNuevoEstado(estadoActual, pieza.getCasillaParent(), movimientoPosible);
	                int eval = minimax(nuevoEstado, false, depth - 1);
	                System.out.println("Evaluando movimiento de pieza aliada --> "+eval);
	                maxEval = Math.max(maxEval, eval);
	            }
	        }

	        return maxEval;
	    } else {
	        int minEval = Integer.MAX_VALUE; //infinito

	        for (Pieza pieza : calcularPiezasJugador(estadoActual, turnoIsBot)) {
	            for (Casilla movimientoPosible : casillasDisponiblesPieza(pieza,estadoActual)) {
	                ArrayList<Casilla> nuevoEstado = crearNuevoEstado(estadoActual, pieza.getCasillaParent(), movimientoPosible);
	                int eval = minimax(nuevoEstado, true, depth - 1);
	                System.out.println("Evaluando movimiento de pieza enemiga --> "+eval);
	                minEval = Math.min(minEval, eval);
	            }
	        }

	        return minEval;
	    }
	}
	





	private ArrayList<Casilla> casillasDisponiblesPieza(Pieza pieza, ArrayList<Casilla> estadoActual) {
		
		
		Casilla casillaPiezaEnEstado = estadoActual.get(partida.getTablero().getCasillas().indexOf(pieza.getCasillaParent()));
		
		return pieza.getCasillasDisponibles(casillaPiezaEnEstado, estadoActual);
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
		Casilla casillaPiezaEnEstado = estadoActual.get(partida.getTablero().getCasillas().indexOf(casillaParent));
		
		moverPiezaSimulada(estadoActual,tableroCopia,casillaPiezaEnEstado, movimientoPosible);
		return tableroCopia;
	}




	private boolean checkFinPartida(ArrayList<Casilla> estadoActual) {
	    boolean reyBlancoPresente = false;
	    boolean reyNegroPresente = false;

	    for (Casilla casilla : estadoActual) {
	        Pieza pieza = casilla.getPieza();
	        if (pieza != null && pieza instanceof Rey) {
	            if (pieza.getIsWhite()) {
	                reyBlancoPresente = true;
	            } else {
	                reyNegroPresente = true;
	            }
				if (reyBlancoPresente && reyNegroPresente) {
					return false;
				}
	        }
	    }

	    return !reyBlancoPresente || !reyNegroPresente;
	}





	
	




	private void moverPiezaSimulada(ArrayList<Casilla>  estadoPasado , ArrayList<Casilla> simulacion,Casilla prevCasilla, Casilla newCasilla) {
		Pieza piezaSeMueve = prevCasilla.getPieza();
		int indexPrevCasilla = estadoPasado.indexOf(prevCasilla);
		int indexNewCasilla = estadoPasado.indexOf(newCasilla);
		
		Casilla prevCasillaSimulada = simulacion.get(indexPrevCasilla);
		Casilla newCasilalSimulada = simulacion.get(indexNewCasilla);
		prevCasillaSimulada.setPieza(null);
		newCasilalSimulada.setPieza(piezaSeMueve);
		
	}







	
	


}
