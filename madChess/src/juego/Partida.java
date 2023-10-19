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
		System.out.println(casillas);
	}


	/** 
	protected void loadPieza(Casilla casilla,Pieza pieza) {
		
		casilla.setPieza(pieza);
		
	}
	*/
	
	protected void loadPiezas() {

		
		casillas.get(0).setPieza(new Pieza("bb"));
		
		
		
		
		
		//Soy Giova pruebo esto a ver que os parece, probando el arraylist multidimensional:
		
		/**
		int filas = 8; 
	    int columnas = 8; 
	    curTablero = new Pieza[filas][columnas]; // Dice de hacer curTablero que sea en vez de un int un obejto Pieza, que pensais?

	    // Rellenar el tablero con piezas iniciales
	    for (int fila = 0; fila < filas; fila++) {
	        for (int columna = 0; columna < columnas; columna++) {
	            curTablero[fila][columna] = new Pieza(); // Hay que crear instancias válidas de Pieza :0
	        }
	    }
	    
	    */
		
	}
	
	

	
	

}
