package juego;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import objetos.Boost;
import objetos.Casilla;
import objetos.Jugador;
import objetos.Pieza;
import piezas.Peon;
import ventanas.VentanaJuego;

public class Partida {

	protected ArrayList<Jugador> jugadores; // Es un arraylist por que en el futuro queremos que puedan jugar hasta 4 por turnos etc..
	
	protected int gameId;
	protected Date fecha;
	protected ArrayList<Casilla> casillas;
	protected HashMap<String,Pieza> piezas = new HashMap<String,Pieza>();
	
	protected HashMap<Boost, Boolean> boosts;



	public Partida(ArrayList<Jugador> jugadores, int gameId, Date fecha,HashMap<Boost, Boolean> boosts) {
		super();
		this.jugadores = jugadores;
		this.gameId = gameId;
		this.fecha = fecha;
		this.boosts = boosts;	
	}
	
	
	
	public Partida() {
		VentanaJuego ventana = new VentanaJuego();
		casillas = ventana.getTableroDiv().getCasillas();
		loadPiezas();
		cargarPiezasTablero();
	}


	/** 
	
	Primero rellemanos el hashmap de piezas en loadPiezas y luego metemos las piezas en sus 
	casillas correspondientes en la función cargarPiezasTablero() asi no repetimos dos veces los atributos
	 de piezas 
	
	*/
	protected void loadPiezas() {
		//Piezas
		
		piezas.put("bb",new Pieza("bb"));
		piezas.put("bk",new Pieza("bk"));
		piezas.put("bn",new Pieza("bn"));
		piezas.put("bp",new Pieza("bp"));
		piezas.put("bq",new Pieza("bq"));
		piezas.put("br",new Pieza("br"));
		
		piezas.put("wb",new Pieza("wb"));
		piezas.put("wk",new Pieza("wk"));
		piezas.put("wn",new Pieza("wn"));
		piezas.put("wp",new Peon("wp"));
		piezas.put("wq",new Pieza("wq"));
		piezas.put("wr",new Pieza("wr"));
		
	}
	
	protected void cargarPiezasTablero() {
		//negras segunda fila
		casillas.get(0).setPieza(piezas.get("br"));
		casillas.get(1).setPieza(piezas.get("bn"));
		casillas.get(2).setPieza(piezas.get("bb"));
		casillas.get(3).setPieza(piezas.get("bq"));
		casillas.get(4).setPieza(piezas.get("bk"));
		casillas.get(5).setPieza(piezas.get("bb"));
		casillas.get(6).setPieza(piezas.get("bn"));
		casillas.get(7).setPieza(piezas.get("br"));
		casillas.get(7).setPieza(piezas.get("br"));
		//negras primera fila
		for (int i = 8; i <= 15; i++) {
			casillas.get(i).setPieza(piezas.get("bp"));
		}
		//blancas primera fila
		for (int i = 48; i <= 55; i++) {
			casillas.get(i).setPieza(piezas.get("wp"));
		}
		
		//blancas segunda fila
		casillas.get(56).setPieza(piezas.get("wr"));
		casillas.get(57).setPieza(piezas.get("wn"));
		casillas.get(58).setPieza(piezas.get("wb"));
		casillas.get(59).setPieza(piezas.get("wq"));
		casillas.get(60).setPieza(piezas.get("wk"));
		casillas.get(61).setPieza(piezas.get("wb"));
		casillas.get(62).setPieza(piezas.get("wn"));
		casillas.get(63).setPieza(piezas.get("wr"));
        }
	}



