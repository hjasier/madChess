package piezas;

import java.util.ArrayList;

import objetos.Casilla;
import objetos.MetodosInterfaz;
import objetos.Pieza;

public class Peon extends Pieza implements MetodosInterfaz{

	public Peon(String nombre) {
		super(nombre);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public ArrayList<Casilla> getCasillasDisponibles(Casilla curCasilla, ArrayList<Casilla> casillas) {
		
		int casillaIndex = casillas.indexOf(curCasilla);
		System.out.println(casillaIndex);
		
		ArrayList<Casilla> casillasDisp = new ArrayList<>(); 
		casillasDisp.add(casillas.get(casillaIndex-8));
		casillasDisp.add(casillas.get(casillaIndex-16));
		
		return casillasDisp;
	}
}
