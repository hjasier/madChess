package piezas;

import java.util.ArrayList;

import objetos.Casilla;
import objetos.Pieza;

public class Alfil extends Pieza implements PiezaMustHave {
	protected boolean isAlter;
	
	public Alfil(Boolean isWhite, boolean isAlter) {
		super("b",isWhite);
		this.isAlter = isAlter;
	}
	
	public boolean alfilIsAlter() {
		return isAlter;
	}

	@Override
    public ArrayList<Casilla> getCasillasDisponibles(Casilla curCasilla, ArrayList<Casilla> casillas) {
        int casillaIndex = casillas.indexOf(curCasilla);
        ArrayList<Casilla> casillasDisp = new ArrayList<>();
        int fila = curCasilla.getFila();
        char columna = curCasilla.getColumna();

        // Posibles movimientos de la torre: arriba, abajo, izquierda y derecha
        int[][] movimientos = {
        			
        		{-1, -1},    {-1, 1},
        		      //Alfil//
        		{1, -1},    {1, 1}
        };

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
        			
        		{-1, -1},    {-1, 1},
        		      //Alfil//
        		{1, -1},    {1, 1}
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
