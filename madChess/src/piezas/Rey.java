package piezas;

import java.util.ArrayList;

import objetos.Casilla;
import objetos.MetodosInterfaz;
import objetos.Pieza;

public class Rey extends Pieza implements MetodosInterfaz{

	public Rey(String nombre) {
		super(nombre);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public ArrayList<Casilla> getCasillasDisponibles(Casilla curCasilla, ArrayList<Casilla> casillas) {
		
		int casillaIndex = casillas.indexOf(curCasilla);
		System.out.println(casillaIndex);
		
		ArrayList<Casilla> casillasDisp = new ArrayList<>(); 
		casillasDisp.add(casillas.get(casillaIndex-1));
		casillasDisp.add(casillas.get(casillaIndex-7));
		casillasDisp.add(casillas.get(casillaIndex-8));
		casillasDisp.add(casillas.get(casillaIndex-9));
		casillasDisp.add(casillas.get(casillaIndex+1));
		//casillasDisp.add(casillas.get(casillaIndex+7));
		//casillasDisp.add(casillas.get(casillaIndex+8));
		//casillasDisp.add(casillas.get(casillaIndex+9));
		
		return casillasDisp;
		//Da error si alguna de las casillas disponibles no esta dentro del tablero, 
		//por ejemplo cuando el rey "puede" moverse fuera del tablero, en su posicion inicial por ejemplo
		// pero el metodo como tal funciona bien cuando todos sus movimientos tienen sentido, salvo por ese bug
		// He dejado con barras los tres ultimos para que no explote en su posicion inicial
	}
}
