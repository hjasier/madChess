package juego;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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
		//Movimientos
        Map<String, Integer> brMov = Map.ofEntries(
                Map.entry("arriba", 1),
                Map.entry("down", 0),
                Map.entry("derecha", 0),
                Map.entry("izq", 0),
                Map.entry("diagonal", 0)
            );
        
        
		
		
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
		piezas.put("wp",new Pieza("wp"));
		piezas.put("wq",new Pieza("wq"));
		piezas.put("wr",new Pieza("wr"));
		
	}
	
	protected void cargarPiezasTablero() {
		//Se puede hacer un random entre uno y dos, depende del resultado establece que lado va cada color
		//Negras
		casillas.get(0).setPieza(piezas.get("br"));
		casillas.get(1).setPieza(piezas.get("bn"));
		casillas.get(2).setPieza(piezas.get("bb"));
		casillas.get(3).setPieza(piezas.get("bq"));
		casillas.get(4).setPieza(piezas.get("bk"));
		casillas.get(5).setPieza(piezas.get("bb"));
		casillas.get(6).setPieza(piezas.get("bn"));
		casillas.get(7).setPieza(piezas.get("br"));
		casillas.get(8).setPieza(piezas.get("bp"));
		casillas.get(9).setPieza(piezas.get("bp"));
		casillas.get(10).setPieza(piezas.get("bp"));
		casillas.get(11).setPieza(piezas.get("bp"));
		casillas.get(12).setPieza(piezas.get("bp"));
		casillas.get(13).setPieza(piezas.get("bp"));
		casillas.get(14).setPieza(piezas.get("bp"));
		casillas.get(15).setPieza(piezas.get("bp"));
		//Blancas
		casillas.get(48).setPieza(piezas.get("wp"));
		casillas.get(49).setPieza(piezas.get("wp"));
		casillas.get(50).setPieza(piezas.get("wp"));
		casillas.get(51).setPieza(piezas.get("wp"));
		casillas.get(52).setPieza(piezas.get("wp"));
		casillas.get(53).setPieza(piezas.get("wp"));
		casillas.get(54).setPieza(piezas.get("wp"));
		casillas.get(55).setPieza(piezas.get("wp"));
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
