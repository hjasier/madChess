package piezas;

import java.util.ArrayList;
import java.util.Arrays;

import objetos.Casilla;
import objetos.MetodosInterfaz;
import objetos.Pieza;

public class Peon extends Pieza implements MetodosInterfaz{
	
	public Peon(Boolean isWhite) {
		super("p",isWhite);
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
        if (!pMoved) {
            movimientos[1][0] = -2; // Cambia el movimiento de una casilla a dos casillas
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
                	    (movimiento[0] == -1 && movimiento[1] == 0 && casillaDisp.getPieza() != null)) {
                	    continue;
                	}
                casillasDisp.add(casillaDisp);

                
                
            }
        }

        return casillasDisp;
    }
	
}
