package puntuacion;


import java.util.HashMap;


import java.util.Map;

public class GestorPuntuaciones {
	// mapa( usuario , mapa(modo , puntuacion))
    private Map<String, Map<String, Integer>> mapaUsuarios;

    public GestorPuntuaciones() {
        this.mapaUsuarios = new HashMap<>();
    }


    public void agregarPartida(String usuario1, String usuario2, String modo, Resultado resultado) {

        


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

        actualizarPuntuaciones(usuario1, usuario2, modo, puntuacionUser1, puntuacionUser2, newPuntuacionUser1, newPuntuacionUser2);

        // empatar contra un jugador con elo mayor deberia ser bueno y empatar contra uno con elo mas bajo malo
        // ganar a un jugador con mas elo k tu deberia dar mas puntos y perder contra uno con elo menor quitar mas
        // ganar rapido deberia dar un bonus fijo
        // los puntos ganados o perdidos no deberian ser fijos deberian variar dependiendo de la diferencia entre ellos, 
        // no seria justo si la diferencia es mininima

    }

    
    public void actualizarPuntuaciones(String usuario1, String usuario2, String modo, int puntuacionUser1, int puntuacionUser2, int newPuntuacionUser1, int newPuntuacionUser2) {
    		puntuacionUser1 += newPuntuacionUser1;
    		puntuacionUser2 += newPuntuacionUser2;
    		
    		insertarMapaUsuario(usuario1, modo, puntuacionUser1);
    		insertarMapaUsuario(usuario2, modo, puntuacionUser2);
    		
    		System.out.println("Las puntuaciones se han actualizado");
    }
   
    
    public void insertarMapaUsuario(String usuario, String modo, int puntuacion) {
    	if (mapaUsuarios.get(usuario) != null) { //el usuario ya tenia puntuaciones en otros modos por lo que no podemos perder eso
    		Map<String, Integer> mapaPuntos = mapaUsuarios.get(usuario);
    		if(modo == "classic") {
    			int mad = mapaPuntos.get("madchess");
    			Map<String, Integer> puntuacionesUsuario = new HashMap<String, Integer>();
    			puntuacionesUsuario.put(modo, puntuacion);
    			puntuacionesUsuario.put("madchess", mad);
    			
    		}else if (modo == "madchess") {
    			int classic = mapaPuntos.get("classic");
    			Map<String, Integer> puntuacionesUsuario = new HashMap<String, Integer>();
    			puntuacionesUsuario.put(modo, puntuacion);
    			puntuacionesUsuario.put("classic", classic);
		
    		}
    	}else { 	//el usuario no tenia puntuaciones y no hay problema
    	Map<String, Integer> puntuacionesUsuario = new HashMap<String, Integer>();
    	puntuacionesUsuario.put(modo, puntuacion);
    	mapaUsuarios.put(usuario, puntuacionesUsuario);
    
    	}
    }
  
    
    public int getPuntuacionModo(String usuario, String modo) {
        Map<String, Integer> mapaModosUsuario = mapaUsuarios.get(usuario);
        if (mapaModosUsuario != null) {
            return mapaModosUsuario.get(modo);
        }else { // el usuario no existe en mapaUsuarios por lo que devolvemos 0 y despues se inserta con el metodo actualizar
    		return 0; 
        }
    }
    

    public enum Resultado {
        VICTORIA_JUGADOR1,
        VICTORIA_JUGADOR2,
        EMPATE

    }
//  ejemplo
//    public static void main(String[] args) {
//
//        GestorPuntuaciones gestor = new GestorPuntuaciones();
//
//        // Agregar partidas y actualizar puntuaciones
//        gestor.agregarPartida("Jugador1", "MadChess", Resultado.VICTORIA_JUGADOR1);
//        gestor.agregarPartida("Jugador1", "Clasico", Resultado.EMPATE);
//        gestor.agregarPartida("Jugador2", "MadChess", Resultado.VICTORIA_JUGADOR2);
//
//        // Obtener puntuaciones
//        System.out.println("Puntuación de Jugador1 en MadChess: " + gestor.getPuntuacionModo("Jugador1", "MadChess"));
//        System.out.println("Puntuación de Jugador1 en Clasico: " + gestor.getPuntuacionModo("Jugador1", "Clasico"));
//        System.out.println("Puntuación de Jugador2 en MadChess: " + gestor.getPuntuacionModo("Jugador2", "MadChess"));
//    }
}
