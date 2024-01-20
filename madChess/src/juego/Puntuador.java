package juego;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import database.GestorDB;
import objetos.Jugador;
import objetos.Usuario;

public class Puntuador {

	private static final int CORTA_DURACION = 60000;
    private static final int BONUS = 5;
   
    private static final int victoria_facil = 5;
    private static final int victoria_dificil = 15;
    private static final int victoria = 10;
    private static final int derrota_facil = -15;
    private static final int derrota_dificil = -5;
    private static final int derrota = -10;

    
    public void cambiarPuntuaciones(DatosPartida partida) {
    	partidaTipo tipo = partida.getTipoPartida();
    	ArrayList<Jugador> listaJugadores = partida.getJugadores();
    	ArrayList<Jugador> listaGanadores = partida.getGanadores();
    	
    	int max = diferenciaPuntuaciones(partida, "max");
    	int min = diferenciaPuntuaciones(partida, "min");
    	
    	Date inicio = partida.getFechaIni();
        Date fin = partida.getFechaFin(); 
        long duracion = fin.getTime() - inicio.getTime();
        
        for (Jugador jugador : listaJugadores) {
        	int puntos = getPuntuacion(jugador, tipo);
        	if (duracion < CORTA_DURACION) { //partida rapida
        		if(listaGanadores.contains(jugador)) { // ese jugador ha ganado
            		if(puntos== max) { // gana con ventaja
            			actualizarPuntuacion(jugador, tipo, victoria_facil + BONUS);
            		}else if (puntos== min) { //gana con desventaja
            		
            			actualizarPuntuacion(jugador, tipo, victoria_dificil+ BONUS);
            		}else { // gana sinmas
            			actualizarPuntuacion(jugador, tipo, victoria+ BONUS);
            		}
      		
            	}else { //ha perdido
            		if(puntos== max) { // pierde con ventaja
            			actualizarPuntuacion(jugador, tipo, derrota_facil- BONUS);
            		}else if (puntos== min) { //pierde con desventaja
            		
            			actualizarPuntuacion(jugador, tipo, derrota_dificil- BONUS);
            		}else { // pierde sinmas
            			actualizarPuntuacion(jugador, tipo, derrota- BONUS);
            		}
            	}
        	}else {
        		if(listaGanadores.contains(jugador)) { // ese jugador ha ganado
            		if(puntos== max) { // gana con ventaja
            			actualizarPuntuacion(jugador, tipo, victoria_facil);
            		}else if (puntos== min) { //gana con desventaja
            		
            			actualizarPuntuacion(jugador, tipo, victoria_dificil);
            		}else { // gana sinmas
            			actualizarPuntuacion(jugador, tipo, victoria);
            		}
      		
            	}else { //ha perdido
            		if(puntos== max) { // pierde con ventaja
            			actualizarPuntuacion(jugador, tipo, derrota_facil);
            		}else if (puntos== min) { //pierde con desventaja
            		
            			actualizarPuntuacion(jugador, tipo, derrota_dificil);
            		}else { // pierde sinmas
            			actualizarPuntuacion(jugador, tipo, derrota);
            		}
            	}
        	}
        	
        }

        
    }
    
    
    public int diferenciaPuntuaciones(DatosPartida partida, String eleccion) {
    	
    	partidaTipo tipo = partida.getTipoPartida();
    	ArrayList<Jugador> listaJugadores = partida.getJugadores();

    	int max = Integer.MIN_VALUE;
    	int min = Integer.MAX_VALUE;

    	for (Jugador jugador : listaJugadores) {
    	 int puntuacion = getPuntuacion(jugador, tipo);
         if (puntuacion > max) {
             max = puntuacion;
         }
         if (puntuacion < min) {
             min = puntuacion;
         }
     }
    	if ("max".equals(eleccion)) {
            return max;
        } else {
            return min;
        }
    }

    
    
    
   
    
    public void actualizarPuntuacion(Jugador jugador, partidaTipo tipo, int cambio) {
    	int anterior = getPuntuacion(jugador, tipo);
    	if("CLASICA".equals(tipo.name())) {	
    		jugador.getUsuario().setRank_classic(anterior - cambio);
    		
    	} else if ("MADCHESS".equals(tipo.name())) {		
    		jugador.getUsuario().setRank_madChess(anterior - cambio);
    	}
        	Usuario usuario = jugador.getUsuario();
        	GestorDB.modificarUsuario(usuario);
		
        
    }
    
    

    public int getPuntuacion(Jugador jugador, partidaTipo tipo) {
    	if("CLASICA".equals(tipo.name())) {
    		return jugador.getUsuario().getRank_classic();
    	

    	} else if ("MADCHESS".equals(tipo.name())) {
    		return jugador.getUsuario().getRank_madChess();

    	}
		return 0;
    }
    

    public static int getPosicionLadder(String eleccion, String username) {
        String[] listaJugadores = GestorDB.getTodasPuntuaciones(eleccion);

        int posicion = -1;
        for (int i = 0; i < listaJugadores.length; i++) {
            if (listaJugadores[i].equals(username)) {
                posicion = i + 1; // Sumar 1 porque las posiciones suelen empezar desde 1, no desde 0
                break;
            }
        }

        if (posicion != -1) {
            return posicion;
        } else {
            return -1;
        }
    }
    
    

}
    



