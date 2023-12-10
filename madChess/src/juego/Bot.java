package juego;

import java.util.ArrayList;
import java.util.Collections;
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
	 * 		6. A cada movimiento contrario asignar una puntuación 
	 * 7. Elegir el movimiento con la puntuación más alta (en caso de ser el turno del jugador maximizador) o más baja (en caso contrario)
	 * 8. Realizar el movimiento elegido en el tablero original
	*/
	
	LogicaPartida partida = Session.getPartida();
	ArrayList<Casilla> tablero = partida.getTablero().getCasillas();
	ArrayList<Pieza> piezasBot = new ArrayList<Pieza>();
	ArrayList<Pieza> piezasPlayer = new ArrayList<Pieza>();
	Jugador botplayer;
	HashMap<Integer, int[]> posibilidadesPuntuadas = new HashMap<Integer, int[]>();
	
	int test = 0;
	
	
	public Bot(Jugador botplayer) {
		this.botplayer = botplayer;
	}
	
	public void calculaNuevoMovimiento(ArrayList<Casilla> estadoActual) {
		minimax(estadoActual, true, 3);
		System.out.println(posibilidadesPuntuadas);
		int[] mejorMovimiento = posibilidadesPuntuadas.get(Collections.max(posibilidadesPuntuadas.keySet()));
		ejecutarMovimiento(mejorMovimiento);
		posibilidadesPuntuadas.clear();
		System.out.println(posibilidadesPuntuadas);
		//partida.moverPiezaTablero(curCasilla, newCasilla);
		
	}

	
	private void ejecutarMovimiento(int[] mejorMovimiento) {
		Casilla casillaSalida = tablero.get(mejorMovimiento[0]);
		Casilla casillaLlegada = tablero.get(mejorMovimiento[1]);
		partida.moverPiezaTablero(casillaSalida, casillaLlegada);
	}

	private int minimax(ArrayList<Casilla> estadoActual, boolean turnoIsBot, int depth) {
	    if (checkFinPartida(estadoActual) || depth == 0) {
	    	int evalu = Evaluador.evaluarTablero(estadoActual);
	    	test +=1;
	    	System.out.println("Evaluando tablero --> "+evalu+"  "+test);
	        return evalu;
	    }

	    if (turnoIsBot) {
	        int maxEval = Integer.MIN_VALUE; //-infinito

	        for (Pieza pieza : calcularPiezasJugador(estadoActual, turnoIsBot)) {
	        	ArrayList<Casilla> casillasD = casillasDisponiblesPieza(pieza,estadoActual);
	            for (Casilla movimientoPosible :casillasD) {
	            	
	                ArrayList<Casilla> nuevoEstado = crearNuevoEstado(estadoActual, pieza.getCasillaParent(), movimientoPosible);
	                int eval = minimax(nuevoEstado, false, depth - 1);
	                System.out.println("Evaluando movimiento de pieza aliada --> "+eval);
	                maxEval = Math.max(maxEval, eval);
	                
					if (depth - 1 == 0) {
						System.out.println("Guardando movimeinto " + eval);//Solo va a guardar uno de cada es decir si dos movs tienen la misma puntuacion solo guardara el último
						int[] movimiento = {tablero.indexOf(pieza.getCasillaParent()),estadoActual.indexOf(movimientoPosible)};
						posibilidadesPuntuadas.put(eval, movimiento);
					}
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
