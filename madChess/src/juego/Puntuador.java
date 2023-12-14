package juego;


import java.util.HashMap;


import java.util.Map;

import objetos.Jugador;

public class Puntuador {
	// mapa( usuario , mapa(modo , puntuacion))
    private static Map<String, Map<String, Integer>> mapaUsuarios;

    public Puntuador() {
        this.mapaUsuarios = new HashMap<>();
    }


    public void agregarPartida(Jugador usuario1, Jugador usuario2, String modo, Resultado resultado) {

        
    	
    	
    	
    	
        int puntuacionUser1 = getPuntuacionModo(usuario1, modo);
        int puntuacionUser2 = getPuntuacionModo(usuario2, modo);
        
             
        int newPuntuacionUser1 = 0;
        int newPuntuacionUser2 = 0;

        if (puntuacionUser1 > puntuacionUser2) {
        	newPuntuacionUser1 = (resultado == Resultado.VICTORIA_JUGADOR1) ? 35 : (resultado == Resultado.EMPATE) ? 15 : -50;
            newPuntuacionUser2 = (resultado == Resultado.VICTORIA_JUGADOR2) ? 50 : (resultado == Resultado.EMPATE) ? 20 : -35;
        } else {
        	newPuntuacionUser1 = (resultado == Resultado.VICTORIA_JUGADOR1) ? 50 : (resultado == Resultado.EMPATE) ? 20 : -35;
            newPuntuacionUser2 = (resultado == Resultado.VICTORIA_JUGADOR2) ? 35 : (resultado == Resultado.EMPATE) ? 15 : -50;
        }

        
        actualizarPuntuacion(usuario1, modo, newPuntuacionUser1);
        actualizarPuntuacion(usuario2, modo, newPuntuacionUser2);
        
        //actualizarPuntuaciones(usuario1, usuario2, modo, puntuacionUser1, puntuacionUser2, newPuntuacionUser1, newPuntuacionUser2);

        // empatar contra un jugador con elo mayor deberia ser bueno y empatar contra uno con elo mas bajo malo
        // ganar a un jugador con mas elo k tu deberia dar mas puntos y perder contra uno con elo menor quitar mas
        // ganar rapido deberia dar un bonus fijo
        // los puntos ganados o perdidos no deberian ser fijos deberian variar dependiendo de la diferencia entre ellos, 
        // no seria justo si la diferencia es mininima

    }

    
    
   
    
    public void actualizarPuntuacion(Jugador jugador, String modo, int cambio) {
    	if(modo == "Classic") {
    		int anterior = getPuntuacionModo(jugador, modo);
    		jugador.setRankClassic(anterior + cambio);
    	} else if (modo == "MadChess") {
    		int anterior = getPuntuacionModo(jugador, modo);
    		jugador.setRankMad(anterior + cambio);
    	}
    	
    	
    }
    
    

    public int getPuntuacionModo(Jugador usuario, String modo) {
    	if(modo == "Classic") {
    		return usuario.getRankClassic();

    	} else if (modo == "MadChess") {
    		return usuario.getRankClassic();

    	}
		return 0;
    }
    

    
    
    

  
    

    

    public enum Resultado {
        VICTORIA_JUGADOR1,
        VICTORIA_JUGADOR2,
        EMPATE

    }

}
