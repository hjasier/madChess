package piezas;

import java.util.ArrayList;

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
		int[] casillasDispIndex = new int[]{8,16}; //SETEAMOS AQUÍ LAS CASILLAS 
		for (int casillaDispIndx : casillasDispIndex) {
			
			if (this.getIsWhite()) {
				casillaDispIndx = casillaDispIndx*-1;
			}			
			casillasDisp.add(casillas.get(casillaIndex+casillaDispIndx));
			
		}
		return casillasDisp;
		
	}
}
