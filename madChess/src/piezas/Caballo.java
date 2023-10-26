package piezas;

import java.util.ArrayList;

import objetos.Casilla;
import objetos.MetodosInterfaz;
import objetos.Pieza;

public class Caballo extends Pieza implements MetodosInterfaz {

	public Caballo(Boolean isWhite) {
		super("n",isWhite);
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

	    for (int[] movimiento : movimientos) {
	        int nuevaFila = fila + movimiento[0];
	        char nuevaColumna = (char) (columna + movimiento[1]);

	        // Verificar que la casilla resultante esté dentro del tablero (filas 0 a 7 y columnas A a H)
	        if (nuevaFila >= 0 && nuevaFila <= 7 && nuevaColumna >= 'A' && nuevaColumna <= 'H') {
	        	System.out.println("nueva fila-->"+nuevaFila+"nuevaColr--> "+nuevaColumna);
	            Casilla casillaDisp = casillas.get(nuevaFila * 8 + (nuevaColumna - 'A'));
	            casillasDisp.add(casillaDisp);
	        }
	    }

	    return casillasDisp;
	}
}