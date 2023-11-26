package piezas;

import java.util.ArrayList;

import objetos.Casilla;

public interface PiezaMustHave {
	public ArrayList<Casilla> getCasillasDisponibles(Casilla curCasilla, ArrayList<Casilla> casillas);
	
	public ArrayList<Casilla> getCasillasCome(Casilla curCasilla, ArrayList<Casilla> casillas);
	
	
	
}