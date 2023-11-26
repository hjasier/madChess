package piezas;

import java.util.ArrayList;

import objetos.Casilla;
import objetos.Pieza;

public class Caballo extends Pieza implements PiezaMustHave {
	
	public Caballo(Boolean isWhite, boolean isAlter) {
		super("n",isWhite);
		this.isAlter = isAlter;
	}

	

	@Override
	public ArrayList<Casilla> getCasillasDisponibles(Casilla curCasilla, ArrayList<Casilla> casillas) {
	    int casillaIndex = casillas.indexOf(curCasilla);

	    ArrayList<Casilla> casillasDisp = new ArrayList<>();

	    int fila = curCasilla.getFila();
	    char columna = curCasilla.getColumna();

	    // Posibles movimientos del caballo
	    int[][] movimientos = {
	        {-1, -2}, {-1, 2}, 
	      {1, -2}, 		{1, 2},
	      {-2, -1},		 {-2, 1}, 
	      	{2, -1},  {2, 1}
	    };
	    if (isAlter) {
	    	movimientos = new int[][] {
	    	      {-1,-2}, {-1,2}, 
	    	      {1, -2}, {1, 2},
	    	      {-2,-1}, {-2,1}, 
	    	      {2, -1}, {2, 1},
	    	      {0,-2},  {0, 2},
	    	      {2, 0},  {-2,0}
	    	    };
        }

	    for (int[] movimiento : movimientos) {
	        int nuevaFila = fila + movimiento[0];
	        char nuevaColumna = (char) (columna + movimiento[1]);

	        // Verificar que la casilla resultante esté dentro del tablero (filas 0 a 7 y columnas A a H)
	        if (nuevaFila >= 0 && nuevaFila <= 7 && nuevaColumna >= 'A' && nuevaColumna <= 'H') {
	            Casilla casillaDisp = casillas.get(nuevaFila * 8 + (nuevaColumna - 'A'));
	            if (casillaDisp.getPieza()!=null&&casillaDisp.getPieza().getIsWhite()==this.getIsWhite()) {continue;}
	            casillasDisp.add(casillaDisp);
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

	    // Posibles movimientos del caballo
	    int[][] movimientos = {
	        {-1, -2}, {-1, 2}, 
	      {1, -2}, 		{1, 2},
	      {-2, -1},		 {-2, 1}, 
	      	{2, -1},  {2, 1}
	    };

	    for (int[] movimiento : movimientos) {
	        int nuevaFila = fila + movimiento[0];
	        char nuevaColumna = (char) (columna + movimiento[1]);

	        // Verificar que la casilla resultante esté dentro del tablero (filas 0 a 7 y columnas A a H)
	        if (nuevaFila >= 0 && nuevaFila <= 7 && nuevaColumna >= 'A' && nuevaColumna <= 'H') {
	            Casilla casillaDisp = casillas.get(nuevaFila * 8 + (nuevaColumna - 'A'));
	            if (
                		casillaDisp.getPieza()!=null&&
                		casillaDisp.getPieza().getIsWhite()==this.getIsWhite())
                {
                	casillasDisp.add(casillaDisp);
                	continue;}
	            casillasDisp.add(casillaDisp);
	        }
	    }

	    return casillasDisp;
	}
}