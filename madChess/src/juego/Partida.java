package juego;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

import objetos.Boost;
import objetos.Jugador;

public class Partida {

	protected ArrayList<Jugador> jugadores; // Es un arraylist por que en el futuro queremos que puedan jugar hasta 4 por turnos etc..
	
	protected int gameId;
	protected Date fecha;
	int[][] curTablero = {  }; // ya se vera pero el tablero podría ser un array multidimentsional en el que hay referencias a objeto pieza
	
	
	
	protected HashMap<Boost, Boolean> boosts;



	public Partida(ArrayList<Jugador> jugadores, int gameId, Date fecha, int[][] curTablero,HashMap<Boost, Boolean> boosts) {
		super();
		this.jugadores = jugadores;
		this.gameId = gameId;
		this.fecha = fecha;
		this.curTablero = curTablero;
		this.boosts = boosts;
	}
	
	
	protected void loadInitPiezas() {
		// Función que crea el arraylist o hashmap (el que prefirais) multidimensional y setea en cada posición del array la pieza correspondiente
		
		//no tengo tiempo este finde, si alguien quiere intentarlo.. adelante
		
		
	}
	
	

	
	

}
