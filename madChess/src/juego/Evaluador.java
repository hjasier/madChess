package juego;

import java.util.ArrayList;

import objetos.Casilla;
import objetos.Pieza;
import piezas.Alfil;
import piezas.Caballo;
import piezas.Peon;
import piezas.Reina;
import piezas.Rey;
import piezas.Torre;

public class Evaluador {

	/*
	 * evaluarTablero() 
	 * 
	 * @param estadoActual Estado de casillas que representan un tablero con las posiciones de las piezas en el mismo
	 * @return int con una puntuación 
	 *  
	 *  La puntuación es positiva si el jugador beneficiado es blanco y negativa si es el negro
	 * 
	 * INFO : El bot siempre juega como color NEGRO
	 */
    public static int evaluarTablero(ArrayList<Casilla> estadoActual) {
        int evaluacionTotal = 0;

        // Importancia de cada factor
        int pesoEstructuraPeones = 1;
        int pesoSeguridadRey = 2;
        int pesoJaqueMate = 100;
        int pesoPiezasRestantes = 2;
        int pesoPartidaGanada = 100;
        

        evaluacionTotal += pesoEstructuraPeones * evaluarEstructuraPeones(estadoActual);
        evaluacionTotal += pesoSeguridadRey * evaluarSeguridadRey(estadoActual);
        evaluacionTotal += pesoJaqueMate * evaluarJaqueMate(estadoActual);
        evaluacionTotal += pesoPiezasRestantes * evaluarPiezasRestantes(estadoActual);
        pesoPartidaGanada += pesoPartidaGanada * evaluarPartidaGanada(estadoActual);
        
        return evaluacionTotal;
    }
    
    

	private static int evaluarJaqueMate(ArrayList<Casilla> estadoActual) {
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
					break;
				}
	        }
	    }
	    
		if (!reyBlancoPresente) {
			System.out.println("Jaque Mate al jugador Blanco");
			return 50;
		} else if (!reyNegroPresente){
			return -50;
		}
		else {
			return 0;
		}
	    
    }

    /*
     * Evalua como de avanzados están los peones en el tablero
     */
    private static int evaluarEstructuraPeones(ArrayList<Casilla> estadoActual) {
        int valorEstructuraPeones = 0;

        for (Casilla casilla : estadoActual) {
            Pieza pieza = casilla.getPieza();
            if (pieza != null && pieza.getIsWhite() && pieza instanceof Peon) {
                // Aumentar la puntuación si los peones blancos están en filas avanzadas
                valorEstructuraPeones += 8 - (casilla.getFila()+1);
            } else if (pieza != null && !pieza.getIsWhite() && pieza instanceof Peon) {
                // Disminuir la puntuación si los peones negros están en filas avanzadas
                valorEstructuraPeones += -1*casilla.getFila();
            }
        }

        return valorEstructuraPeones;
    }
    

    /*
     * Evalua los reyes esta amenzadados (directamente) no indirecta
     */
    private static int evaluarSeguridadRey(ArrayList<Casilla> estadoActual) {
        int valorSeguridadRey = 0;

        for (Casilla casilla : estadoActual) {
            Pieza pieza = casilla.getPieza();
            if (pieza != null && pieza instanceof Rey) {
                // Aumentar la puntuación si el rey blanco está en una posición segura
                valorSeguridadRey += evaluarSeguridadReyEnCasilla((Rey) pieza, casilla,estadoActual);
            } 
        }
        return valorSeguridadRey;

    }

    private static int evaluarSeguridadReyEnCasilla(Rey rey, Casilla casillaRey, ArrayList<Casilla> estadoActual) {
        boolean isAmenazado = rey.reCheckJaqueStatus(casillaRey, estadoActual);
        
        if (isAmenazado && !rey.getIsWhite()) {
            // Si el rey está amenazado y es del bot
            return 100;
        } else if (isAmenazado && rey.getIsWhite()) {
            // Si el rey está amenazado y es del jugador
            return -100;
        } else {
            return 0;
        }
    }
    
	/*
	 * Evalua si la partida ha terminado
	 */
    private static int evaluarPartidaGanada(ArrayList<Casilla> estadoActual) {
    	boolean reyBlancoPresente = false;
	    boolean reyNegroPresente = false;
	    int valorPartidaGanada = 0;

	    for (Casilla casilla : estadoActual) {
	        Pieza pieza = casilla.getPieza();
	        if (pieza != null && pieza instanceof Rey) {
	            if (pieza.getIsWhite()) {
	                reyBlancoPresente = true;
	            } else {
	                reyNegroPresente = true;
	            }
				
	            
	        }
	    }

	    if (!reyBlancoPresente) {
	    	valorPartidaGanada = -999;
	    }
		else if (!reyNegroPresente) {
			valorPartidaGanada = 999;
		} 
	    
	    return valorPartidaGanada;
	}
    
    
    
    
    private static int evaluarPiezasRestantes(ArrayList<Casilla> estadoActual) {
    	int valorPiezasRestantes = 0;
    	
    	for (Casilla casilla : estadoActual) {
            Pieza pieza = casilla.getPieza();
            if (pieza != null && pieza.getIsWhite()) {
            	valorPiezasRestantes += getValor(pieza);
            }
			else if (pieza != null && !pieza.getIsWhite()) {
				valorPiezasRestantes -= getValor(pieza);
			}
    	}
    	
    	return valorPiezasRestantes;
    }
    	
    
    
    
	private static int getValor(Pieza pieza) {
		int[] puntosPieza = {10, 30, 30, 50, 90}; //Peon, Alfil, Caballo, Torre, Reina 
		int valor = 0;
		if (pieza instanceof Peon) {
			valor = puntosPieza[0];
		}else if (pieza instanceof Alfil) {
			valor = puntosPieza[1];
		}else if (pieza instanceof Caballo) {
			valor = puntosPieza[2];
		}else if (pieza instanceof Torre) {
			valor = puntosPieza[3];
		}else if (pieza instanceof Reina) {
			valor = puntosPieza[4];
		}
		
		return valor;
	}
	
	

    
}
