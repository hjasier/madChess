package piezas;

import java.util.ArrayList;
import java.util.Arrays;

import objetos.Casilla;
import objetos.Pieza;

public class Peon extends Pieza implements PiezaMustHave{
	
	public Peon(Boolean isWhite, boolean isAlter) {
		super("p",isWhite,isAlter);
	}

	
	
	@Override
    public ArrayList<Casilla> getCasillasDisponibles(Casilla curCasilla, ArrayList<Casilla> casillas) {
        int casillaIndex = casillas.indexOf(curCasilla);
        ArrayList<Casilla> casillasDisp = new ArrayList<>();
        int fila = curCasilla.getFila();
        char columna = curCasilla.getColumna();

        
        int[][] movimientos = {
        			
        		{-1, -1},
        		{-1, 0},
        		{-1, 0}, /**Peon**/
        		{-1, 1}
        };
        if (!pMoved || isAlter) {
            // Verificar si la casilla delante del peón está ocupada por una pieza contraria
            Casilla casillaUnaAdelante = casillas.get((fila + (this.getIsWhite() ? -1 : 1)) * 8 + (columna - 'A'));
            if (casillaUnaAdelante.getPieza() != null && casillaUnaAdelante.getPieza().getIsWhite() != this.getIsWhite()) {
                movimientos[1][0] = -1; // Si hay una pieza adelante, solo se permite el movimiento de una casilla
            } else {
                movimientos[1][0] = -2; // Si no hay pieza adelante, se permite el movimiento de dos casillas
            }
        }
        

        for (int[] movimiento : movimientos) {
            int nuevaFila = fila + (this.getIsWhite() ? movimiento[0] : -movimiento[0]); 
            char nuevaColumna = (char) (columna + (this.getIsWhite() ? movimiento[1] : -movimiento[1]));

            
            // Verifica que la casilla resultante esté dentro del tablero (filas 0 a 7 y columnas A a H)
            if (nuevaFila >= 0 && nuevaFila <= 7 && nuevaColumna >= 'A' && nuevaColumna <= 'H') {
            	
                Casilla casillaDisp = casillas.get(nuevaFila * 8 + (nuevaColumna - 'A'));
                
                if ((casillaDisp.getPieza() != null && casillaDisp.getPieza().getIsWhite() == this.getIsWhite()) ||
                	    (movimiento[0] == -1 && movimiento[1] == -1 && casillaDisp.getPieza() == null) ||
                	    (movimiento[0] == -1 && movimiento[1] == 1 && casillaDisp.getPieza() == null) ||
                	    (movimiento[0] == -2 && movimiento[1] == 0 && casillaDisp.getPieza() != null) ||
                	    (movimiento[0] == -1 && movimiento[1] == 0 && casillaDisp.getPieza() != null)) {
                		
                	    continue;
                	}
                
                casillasDisp.add(casillaDisp);

                
                
            }
        }

        return casillasDisp;
    }
	
	public ArrayList<Casilla> getCasillasCome(Casilla curCasilla, ArrayList<Casilla> casillas) { //Sirve para todo lo que rodea al tema jaque
        int casillaIndex = casillas.indexOf(curCasilla);
        ArrayList<Casilla> casillasDisp = new ArrayList<>();
        int fila = curCasilla.getFila();
        char columna = curCasilla.getColumna();

        int[][] movimientos = {
        {-1, -1},		  {-1, 1}};
        		/**Peon**/

        for (int[] movimiento : movimientos) {
            int nuevaFila = fila + (this.getIsWhite() ? movimiento[0] : -movimiento[0]); 
            char nuevaColumna = (char) (columna + (this.getIsWhite() ? movimiento[1] : -movimiento[1]));

            
            // Verifica que la casilla resultante esté dentro del tablero (filas 0 a 7 y columnas A a H)
            if (nuevaFila >= 0 && nuevaFila <= 7 && nuevaColumna >= 'A' && nuevaColumna <= 'H') {
            	
                Casilla casillaDisp = casillas.get(nuevaFila * 8 + (nuevaColumna - 'A'));
                
                
                casillasDisp.add(casillaDisp);

            }
        }

        return casillasDisp;
    }
}