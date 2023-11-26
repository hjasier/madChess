package piezas;

import java.util.ArrayList;

import objetos.Casilla;
import objetos.Pieza;

public class Reina extends Pieza implements PiezaMustHave {

	public Reina(Boolean isWhite, boolean isAlter) {
		super("q",isWhite);
		this.isAlter = isAlter;
	}

	@Override
    public ArrayList<Casilla> getCasillasDisponibles(Casilla curCasilla, ArrayList<Casilla> casillas) {
        int casillaIndex = casillas.indexOf(curCasilla);
        ArrayList<Casilla> casillasDisp = new ArrayList<>();
        int fila = curCasilla.getFila();
        char columna = curCasilla.getColumna();

        // Posibles movimientos de la torre: arriba, abajo, izquierda y derecha
        int[][] movimientos = {
        			
        		{-1, -1},{0, -1},{1, -1},
        		{-1, 0}, /**Reina**/{1, 0}, 
        		{-1, 1}, {0, 1}, {1, 1}
        };
        if(isAlter) {
        	boolean lateral = false; // Flag para movimientos laterales
    	    int contador = 0; // Contador para controlar las piezas comestibles

    	    for (int[] movimiento : movimientos) {
    	        int nuevaFila = fila + movimiento[0];
    	        char nuevaColumna = (char) (columna + movimiento[1]);

    	        // Restablecer el contador y el flag al cambiar de dirección
//    	        if (movimiento[1] != 0) {
//    	            lateral = true;
//    	            contador = 0;
//    	        } else {
//    	            lateral = false;
//    	        }
    	        
    	     // Verifica si el movimiento actual es lateral (izquierda o derecha)
    	        boolean esMovimientoLateral = (movimiento[0] == 0 && (movimiento[1] == -1 || movimiento[1] == 1));

    	        // Restablecer el contador al cambiar de dirección
    	        if (!esMovimientoLateral) {
    	            contador = 0;
    	        }

    	        // Verifica que la casilla resultante esté dentro del tablero
    	        while (nuevaFila >= 0 && nuevaFila <= 7 && nuevaColumna >= 'A' && nuevaColumna <= 'H') {

    	            Casilla casillaDisp = casillas.get(nuevaFila * 8 + (nuevaColumna - 'A'));

    	            if (casillaDisp.getPieza() != null) {
    	                if (casillaDisp.getPieza().getIsWhite() == this.getIsWhite()) {
    	                    break; // Si es una pieza del mismo color, terminamos el movimiento
    	                } else if (esMovimientoLateral && contador < 2) {
    	                    casillasDisp.add(casillaDisp);
    	                    contador++;

    	                    // Verifica si aún se pueden comer más piezas en el lateral
    	                    if (contador == 1) {
    	                        int siguienteFila = nuevaFila + movimiento[0];
    	                        char siguienteColumna = (char) (nuevaColumna + movimiento[1]);

    	                        // Verifica si la siguiente casilla contiene una pieza enemiga
    	                        if (siguienteFila >= 0 && siguienteFila <= 7 &&
    	                            siguienteColumna >= 'A' && siguienteColumna <= 'H') {
    	                            Casilla siguienteCasilla = casillas.get(siguienteFila * 8 + (siguienteColumna - 'A'));
    	                            if (contador <= 2 &&
    	                            	siguienteCasilla.getPieza() != null &&
    	                                siguienteCasilla.getPieza().getIsWhite() != this.getIsWhite()) {
    	                                casillasDisp.add(siguienteCasilla);
    	                                
    	                            }else {break;}
    	                            
    	                        }
    	                        
    	                    }
    	                } else if (esMovimientoLateral && contador >= 2) {
    	                    break; // Si ya se han comido dos piezas en el lateral, terminamos el movimiento
    	                } else {
    	                    casillasDisp.add(casillaDisp);
    	                }
    	            }

    	            
    	            // Avanzar en la dirección del movimiento
    	            nuevaFila += movimiento[0];
    	            nuevaColumna = (char) (nuevaColumna + movimiento[1]);
    	            
    	            
    	        }
    	    }}
        
        for (int[] movimiento : movimientos) { //Recorre cada movimiento posible desde la posicion de la torre
            int nuevaFila = fila + movimiento[0];
            char nuevaColumna = (char) (columna + movimiento[1]);

            // Verifica que la casilla resultante esté dentro del tablero (filas 0 a 7 y columnas A a H)
            while (nuevaFila >= 0 && nuevaFila <= 7 && nuevaColumna >= 'A' && nuevaColumna <= 'H') {
            	
                Casilla casillaDisp = casillas.get(nuevaFila * 8 + (nuevaColumna - 'A'));
                
                if (casillaDisp.getPieza()!=null&&casillaDisp.getPieza().getIsWhite()==this.getIsWhite()) {break;}
                casillasDisp.add(casillaDisp);

                // Avanzo a la dirección del movimiento
                nuevaFila += movimiento[0];
                nuevaColumna = (char) (nuevaColumna + movimiento[1]);
                if (casillaDisp.getPieza()!=null) {break;}
            }
        }

        return casillasDisp;
    }
	
	@Override
	public ArrayList<Casilla> getCasillasCome(Casilla curCasilla, ArrayList<Casilla> casillas) {
		int casillaIndex = casillas.indexOf(curCasilla);
        ArrayList<Casilla> casillasDisp = new ArrayList<>();
        int fila = curCasilla.getFila();
        char columna = curCasilla.getColumna();

        // Posibles movimientos de la torre: arriba, abajo, izquierda y derecha
        int[][] movimientos = {
        			
        		{-1, -1},{0, -1},{1, -1},
        		{-1, 0}, /**Reina**/{1, 0}, 
        		{-1, 1}, {0, 1}, {1, 1}
        };

        for (int[] movimiento : movimientos) { //Recorre cada movimiento posible desde la posicion de la torre
            int nuevaFila = fila + movimiento[0];
            char nuevaColumna = (char) (columna + movimiento[1]);

            // Verifica que la casilla resultante esté dentro del tablero (filas 0 a 7 y columnas A a H)
            while (nuevaFila >= 0 && nuevaFila <= 7 && nuevaColumna >= 'A' && nuevaColumna <= 'H') {
            	
                Casilla casillaDisp = casillas.get(nuevaFila * 8 + (nuevaColumna - 'A'));
                
                if (
                		casillaDisp.getPieza()!=null&&
                		casillaDisp.getPieza().getIsWhite()==this.getIsWhite())
                {
                	casillasDisp.add(casillaDisp);
                	break;}
                
                casillasDisp.add(casillaDisp);

                // Avanzo a la dirección del movimiento
                nuevaFila += movimiento[0];
                nuevaColumna = (char) (nuevaColumna + movimiento[1]);
                if (casillaDisp.getPieza()!=null) {
                	if(casillaDisp.getPieza() instanceof Rey) {
                		continue;
                	}
                	break;}
            }
        }

        return casillasDisp;
    }

}
