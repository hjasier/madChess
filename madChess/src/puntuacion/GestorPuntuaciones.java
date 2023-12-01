package puntuacion;


import java.util.HashMap;


import java.util.Map;

public class GestorPuntuaciones {
	// mapa( usuario , mapa(modo , puntuacion))
	private Map<String, Integer> mapaPuntuacionModo;
    private Map<String, Map<String, Integer>> mapaUsuarios;

    public GestorPuntuaciones() {
        this.mapaUsuarios = new HashMap<>();
    }


    public void agregarPartida(String usuario1, String usuario2, String modo, Resultado resultado) {

        Map<String, Integer> puntuacionesUsuario = new HashMap<String, Integer>();


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

        puntuacionUser1 += newPuntuacionUser1;
        puntuacionUser2 += newPuntuacionUser2;
        
        
        puntuacionesUsuario.put(modo, puntuacionUser1);
        puntuacionesUsuario.put(modo, puntuacionUser2);

        mapaUsuarios.put(usuario1, puntuacionesUsuario);
        mapaUsuarios.put(usuario2, puntuacionesUsuario);
        
        
        // empatar contra un jugador con elo mayor deberia ser bueno y empatar contra uno con elo mas bajo malo
        // ganar a un jugador con mas elo k tu deberia dar mas puntos y perder contra uno con elo menor quitar mas
        // ganar rapido deberia dar un bonus fijo
        // los puntos ganados o perdidos no deberian ser fijos deberian variar dependiendo de la diferencia entre ellos, 
        // no seria justo si la diferencia es mininima
        
        

    }


    
    
    public int getPuntuacionModo(String usuario, String modo) {
        Map<String, Integer> mapaPuntuacionModo = mapaUsuarios.get(usuario);
        if (mapaPuntuacionModo != null) {
            return mapaPuntuacionModo.get(modo);
        }
    else {
        	return 0;// Devuelve 0 si el usuario no existe en mapaUsuarios
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
//        gestor.agregarPartida("Jugador1", "Blitz", Resultado.VICTORIA_JUGADOR1);
//        gestor.agregarPartida("Jugador1", "Clasico", Resultado.EMPATE);
//        gestor.agregarPartida("Jugador2", "Blitz", Resultado.VICTORIA_JUGADOR2);
//
//        // Obtener puntuaciones
//        System.out.println("Puntuación de Jugador1 en Blitz: " + gestor.getPuntuacionModo("Jugador1", "Blitz"));
//        System.out.println("Puntuación de Jugador1 en Clasico: " + gestor.getPuntuacionModo("Jugador1", "Clasico"));
//        System.out.println("Puntuación de Jugador2 en Blitz: " + gestor.getPuntuacionModo("Jugador2", "Blitz"));
//    }
}
