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
        
             
        if (puntuacionUser1 > puntuacionUser2) { //usuario 1 tiene el elo mas alto por lo que gana poco y el usuario 2 pierde mas
        	 if (resultado == Resultado.VICTORIA_JUGADOR1) {
        		 puntuacionUser1 += 35;
        		 puntuacionUser2 -= 35;
             } else if (resultado == Resultado.VICTORIA_JUGADOR2) {
            	 puntuacionUser1 -= 50;
        		 puntuacionUser2 += 50;
             } else if (resultado == Resultado.EMPATE) {
            	 puntuacionUser1 += 15;
            	 puntuacionUser2 += 20;
             }
        } else { //usuario 2 tiene el elo mas alto
        	if (resultado == Resultado.VICTORIA_JUGADOR1) {
       		 puntuacionUser1 += 50;
       		 puntuacionUser2 -= 50;
            } else if (resultado == Resultado.VICTORIA_JUGADOR2) {
           	 puntuacionUser1 -= 35;
       		 puntuacionUser2 += 35;
            } else if (resultado == Resultado.EMPATE) {
           	 puntuacionUser1 += 20;
           	 puntuacionUser2 += 15;
            }
        }
        
        
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
//        System.out.println("Puntuación de Jugador1 en Blitz: " + gestor.obtenerPuntuacion("Jugador1", "Blitz"));
//        System.out.println("Puntuación de Jugador1 en Clasico: " + gestor.obtenerPuntuacion("Jugador1", "Clasico"));
//        System.out.println("Puntuación de Jugador2 en Blitz: " + gestor.obtenerPuntuacion("Jugador2", "Blitz"));
//    }
}
