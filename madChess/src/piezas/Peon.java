package piezas;

import java.util.ArrayList;
import java.util.Arrays;

import objetos.Casilla;
import objetos.MetodosInterfaz;
import objetos.Pieza;

public class Peon extends Pieza implements MetodosInterfaz{
	
	protected Boolean pMoved = false;
	
	public Peon(Boolean isWhite) {
		super("p",isWhite);
	}

	
	
	@Override
	public ArrayList<Casilla> getCasillasDisponibles(Casilla curCasilla, ArrayList<Casilla> casillas) {
		
		int casillaIndex = casillas.indexOf(curCasilla);
		
		
		ArrayList<Casilla> casillasDisp = new ArrayList<>(); 
		ArrayList<Integer> casillasDispIndex = new ArrayList<>(Arrays.asList(8)); //SETEAMOS AQUÍ LAS CASILLAS
		if(!pMoved) {
			casillasDispIndex.add(16);
			pMoved = true;
		}
		for (int casillaDispIndx : casillasDispIndex) {
			
			if (this.getIsWhite()) {
				casillaDispIndx = casillaDispIndx*-1;
			}			
			Casilla casillaDisp = casillas.get(casillaIndex+casillaDispIndx);
			if(casillaDisp.getPieza() == null) {
				casillasDisp.add(casillaDisp);
			}
		}
		ArrayList<Integer> casillasComiblesIndex = new ArrayList<>(Arrays.asList());
		if(curCasilla.getColumna()!= 'A') {
			casillasComiblesIndex.add(9);			
		}
		if(curCasilla.getColumna()!= 'H') {
			casillasComiblesIndex.add(7);
		}
		for (int casillaComibleIndex : casillasComiblesIndex) {
			
			if (this.getIsWhite()) {
				casillaComibleIndex = casillaComibleIndex*-1;
			}			
			Casilla casillaComible = casillas.get(casillaIndex+casillaComibleIndex);
			if(casillaComible.getPieza() != null) {
				casillasDisp.add(casillaComible);
			}
		}
		
		return casillasDisp;
	
	}
}
