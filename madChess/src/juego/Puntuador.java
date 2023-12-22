package juego;

import java.util.Date;
import objetos.Jugador;

public class Puntuador {

	private static final int CORTA_DURACION = 60000;
    private static final int BONUS = 5;


    public void agregarPartida(Jugador usuario1, Jugador usuario2, String modo, Resultado resultado, DatosPartida partida) {

        int puntuacionUser1 = getPuntuacionModo(usuario1, modo);
        int puntuacionUser2 = getPuntuacionModo(usuario2, modo);
        
          
        int newPuntuacionUser1 = 0;
        int newPuntuacionUser2 = 0;

        
        Date inicio = partida.getFechaIni();
        Date fin = partida.getFechaFin(); 

        long duracion = fin.getTime() - inicio.getTime();
        
        if (duracion < CORTA_DURACION) {
        	//victoria veloz
            if (puntuacionUser1 > puntuacionUser2 + 75) { 
            	//jugador 1 es mucho mejor + rapido
            	newPuntuacionUser1 = (resultado == Resultado.VICTORIA_JUGADOR1) ? (15 + BONUS) : (resultado == Resultado.EMPATE) ? -5  : (-30 - BONUS);
                newPuntuacionUser2 = (resultado == Resultado.VICTORIA_JUGADOR2) ? (30 + BONUS) : (resultado == Resultado.EMPATE) ? 5  : (-15 - BONUS);
            } else if (puntuacionUser2 > puntuacionUser1 + 75) {
            	//jugador 2 es mejor + rapido
            	newPuntuacionUser1 = (resultado == Resultado.VICTORIA_JUGADOR1) ? (30 + BONUS) : (resultado == Resultado.EMPATE) ? 5  : (-15 - BONUS);
                newPuntuacionUser2 = (resultado == Resultado.VICTORIA_JUGADOR2) ? (15 + BONUS) : (resultado == Resultado.EMPATE) ? -5  : (-30 - BONUS);
            } else { //la diferencia entre ellos no es notable
            	newPuntuacionUser1 = (resultado == Resultado.VICTORIA_JUGADOR1) ? (20 + BONUS) : (resultado == Resultado.EMPATE) ? 0  : (-20);
                newPuntuacionUser2 = (resultado == Resultado.VICTORIA_JUGADOR2) ? (20 + BONUS) : (resultado == Resultado.EMPATE) ? 0  : (-20);
            }
            
        } else {
        	if (puntuacionUser1 > puntuacionUser2 + 75) { 
            	//jugador 1 es mucho mejor + rapido
            	newPuntuacionUser1 = (resultado == Resultado.VICTORIA_JUGADOR1) ? (15) : (resultado == Resultado.EMPATE) ? -5  : (-30);
                newPuntuacionUser2 = (resultado == Resultado.VICTORIA_JUGADOR2) ? (30) : (resultado == Resultado.EMPATE) ? 5  : (-15);
            } else if (puntuacionUser2 > puntuacionUser1 + 75) {
            	//jugador 2 es mejor + rapido
            	newPuntuacionUser1 = (resultado == Resultado.VICTORIA_JUGADOR1) ? (30) : (resultado == Resultado.EMPATE) ? 5  : (-15);
                newPuntuacionUser2 = (resultado == Resultado.VICTORIA_JUGADOR2) ? (15) : (resultado == Resultado.EMPATE) ? -5  : (-30);
            } else { //la diferencia entre ellos no es notable
            	newPuntuacionUser1 = (resultado == Resultado.VICTORIA_JUGADOR1) ? (20) : (resultado == Resultado.EMPATE) ? 0  : (-20);
                newPuntuacionUser2 = (resultado == Resultado.VICTORIA_JUGADOR2) ? (20) : (resultado == Resultado.EMPATE) ? 0  : (-20);
            }
        }
        
        
        actualizarPuntuacion(usuario1, modo, newPuntuacionUser1);
        actualizarPuntuacion(usuario2, modo, newPuntuacionUser2);

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
        EMPATE

    }

}
