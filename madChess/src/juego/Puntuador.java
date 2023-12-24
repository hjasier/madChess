package juego;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import objetos.Jugador;

public class Puntuador {

	private static final int CORTA_DURACION = 60000;
    private static final int BONUS = 5;

    
    public void agregarPartida2(ArrayList<Jugador> listaJugadores, String modo, Resultado resultado, DatosPartida partida) {

    	 Date inicio = partida.getFechaIni();
         Date fin = partida.getFechaFin(); 

         long duracion = fin.getTime() - inicio.getTime();
    	
    	int[] puntuaciones = new int[listaJugadores.size()];

    	 for (int i = 0; i < listaJugadores.size(); i++) {
    	        puntuaciones[i] = getPuntuacionModo(listaJugadores.get(i), modo);
    	    }


    	 for (int i = 0; i < listaJugadores.size(); i++) {
    	        int newPuntuacion = 0;

    	        for (int j = 0; j < listaJugadores.size(); j++) {
    	            if (i != j) {
    	                int diffPuntuacion = puntuaciones[i] - puntuaciones[j];
    	                newPuntuacion += calcularNuevaPuntuacion(duracion, resultado, diffPuntuacion, j);
    	            }
    	        }

    	        actualizarPuntuacion(listaJugadores.get(i), modo, newPuntuacion);
    	  
    	 }         
     
    }
    

    private int calcularNuevaPuntuacion(long duracion, Resultado resultado, int diffPuntuacion, int numJugador) {
    	String condicion = "VICTORIA_JUGADOR" + numJugador;
        if (duracion < CORTA_DURACION) {
            if (diffPuntuacion > 75) {
                return (resultado.equals(condicion) ? (15 + BONUS) : (resultado == Resultado.EMPATE) ? -5 : (-30 - BONUS));
            } else if (diffPuntuacion < -75) {
                return (resultado.equals(condicion) ? (30 + BONUS) : (resultado == Resultado.EMPATE) ? 5 : (-15 - BONUS));
            } else {
                return (resultado.equals(condicion) ? (20 + BONUS) : (resultado == Resultado.EMPATE) ? 0 : (-20));
            }
        } else {
            if (diffPuntuacion > 75) {
                return (resultado.equals(condicion) ? 15 : (resultado == Resultado.EMPATE) ? -5 : (-30));
            } else if (diffPuntuacion < -75) {
                return (resultado.equals(condicion) ? 30 : (resultado == Resultado.EMPATE) ? 5 : (-15));
            } else {
                return (resultado.equals(condicion) ? 20 : (resultado == Resultado.EMPATE) ? 0 : (-20));
            }
        }
    }
    
   
    
    public void actualizarPuntuacion(Jugador jugador, String modo, int cambio) {
    	if(modo == "Classic") {
    		int anterior = getPuntuacionModo(jugador, modo);
    		
    		jugador.getUsuario().setRank_classic(anterior + cambio);
    		
    	} else if (modo == "MadChess") {
    		int anterior = getPuntuacionModo(jugador, modo);
    		jugador.getUsuario().setRank_madChess(anterior + cambio);
    	}
    	
    	
    }
    
    

    public int getPuntuacionModo(Jugador jugador, String modo) {
    	if(modo == "Classic") {
    		return jugador.getUsuario().getRank_classic();
    	

    	} else if (modo == "MadChess") {
    		return jugador.getUsuario().getRank_madChess();

    	}
		return 0;
    }
    

    
    
    
    public enum Resultado {
        VICTORIA_JUGADOR1,
        VICTORIA_JUGADOR2,
        VICTORIA_JUGADOR3,
        VICTORIA_JUGADOR4,
        EMPATE

    }

}
