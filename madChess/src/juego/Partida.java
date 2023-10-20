package juego;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

import objetos.Boost;
import objetos.Casilla;
import objetos.Jugador;
import objetos.Pieza;
import ventanas.VentanaJuego;

public class Partida {

	protected ArrayList<Jugador> jugadores; // Es un arraylist por que en el futuro queremos que puedan jugar hasta 4 por turnos etc..
	
	protected int gameId;
	protected Date fecha;
	int[][] curTablero = {  }; // ya se vera pero el tablero podría ser un array multidimentsional en el que hay referencias a objeto pieza
	protected ArrayList<Casilla> casillas;
	protected HashMap<String,Pieza> piezas = new HashMap<String,Pieza>();
	
	protected HashMap<Boost, Boolean> boosts;



	public Partida(ArrayList<Jugador> jugadores, int gameId, Date fecha, int[][] curTablero,HashMap<Boost, Boolean> boosts) {
		super();
		this.jugadores = jugadores;
		this.gameId = gameId;
		this.fecha = fecha;
		this.curTablero = curTablero;
		this.boosts = boosts;
		
		
	}
	
	
	
	public Partida() {
		VentanaJuego ventana = new VentanaJuego();
		casillas = ventana.getTableroDiv().getCasillas();
		loadPiezas();
		cargarPiezasTablero();
		System.out.println(casillas);
	}


	/** 
	
	Primero rellemanos el hashmap de piezas en loadPiezas y luego metemos las piezas en sus 
	casillas correspondientes en la función cargarPiezasTablero() asi no repetimos dos veces los atributos
	 de piezas 
	
	*/
	protected void loadPiezas() {
		piezas.put("bb",new Pieza("bb"));
		piezas.put("bk",new Pieza("bk"));
		piezas.put("bn",new Pieza("bn"));

		
	}
	
	protected void cargarPiezasTablero() {

		
		casillas.get(0).setPieza(piezas.get("bb"));

		
		
	}
	
	

	
	

}
