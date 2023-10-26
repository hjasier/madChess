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

	
	/**
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
			if(casillaComible.getPieza() != null && casillaComible.getPieza().getIsWhite()!=this.getIsWhite()) {
				casillasDisp.add(casillaComible);
			}
		}
		
		return casillasDisp;
	
	}**/
	
	
	
	@Override
    public ArrayList<Casilla> getCasillasDisponibles(Casilla curCasilla, ArrayList<Casilla> casillas) {
        int casillaIndex = casillas.indexOf(curCasilla);
        ArrayList<Casilla> casillasDisp = new ArrayList<>();
        int fila = curCasilla.getFila();
        char columna = curCasilla.getColumna();

        // Posibles movimientos de la torre: arriba, abajo, izquierda y derecha
        int[][] movimientos = {
        			
        		{-1, -1},
        		{-1, 0},
        		{-1, 0}, /**Peon**/
        		{-1, 1}
        };
        if (!pMoved) {
            movimientos[1][0] = -2; // Cambia el movimiento de una casilla a dos casillas
            pMoved = true;
        }
        
     // Determina si la pieza es negra
        boolean isBlack = !this.getIsWhite();

        for (int[] movimiento : movimientos) {
            int nuevaFila = fila + (isBlack ? -movimiento[0] : movimiento[0]); //si es negra hace lo opuesto que para la blanca
            char nuevaColumna = (char) (columna + (isBlack ? -movimiento[1] : movimiento[1]));

            
            // Verifica que la casilla resultante esté dentro del tablero (filas 0 a 7 y columnas A a H)
            if (nuevaFila >= 0 && nuevaFila <= 7 && nuevaColumna >= 'A' && nuevaColumna <= 'H') {
            	
                Casilla casillaDisp = casillas.get(nuevaFila * 8 + (nuevaColumna - 'A'));
                
                if (casillaDisp.getPieza()!=null&&casillaDisp.getPieza().getIsWhite()==this.getIsWhite()) {continue;}
                else if (movimiento[0] == -1 && movimiento[1] == -1 && casillaDisp.getPieza()==null) {continue;} //mira si a la izquierda hay pieza para comer o no
                else if (movimiento[0] == -1 && movimiento[1] == 1 && casillaDisp.getPieza()==null) {continue;} //mira si a la derecha hay pieza para comer o no
                else if (movimiento[0] == -1 && movimiento[1] == 0 && casillaDisp.getPieza()!=null) {continue;} //cuando tiene una pieza de frente no se mueve
                
                casillasDisp.add(casillaDisp);

                
                
            }
        }

        return casillasDisp;
    }
	
}
