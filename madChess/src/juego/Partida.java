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
import piezas.Alfil;
import piezas.Caballo;
import piezas.Peon;
import piezas.Reina;
import piezas.Rey;
import piezas.Torre;
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
		cargarPiezasTablero();
	}


	
	protected void cargarPiezasTablero() {

		casillas.get(0).setPieza(new Torre(false));
		casillas.get(1).setPieza(new Caballo(false));
		casillas.get(2).setPieza(new Alfil(false));
		casillas.get(3).setPieza(new Reina(false));
		casillas.get(4).setPieza(new Rey(false));
		casillas.get(5).setPieza(new Alfil(false));
		casillas.get(6).setPieza(new Caballo(false));
		casillas.get(7).setPieza(new Torre(false));

		for (int i = 8; i <= 15; i++) {
			casillas.get(i).setPieza(new Peon(false));
		}

		for (int i = 48; i <= 55; i++) {
			casillas.get(i).setPieza(new Peon(true));
		}
		
		casillas.get(56).setPieza(new Torre(true));
		casillas.get(57).setPieza(new Caballo(true));
		casillas.get(58).setPieza(new Alfil(true));
		casillas.get(59).setPieza(new Reina(true));
		casillas.get(60).setPieza(new Rey(true));
		casillas.get(61).setPieza(new Alfil(true));
		casillas.get(62).setPieza(new Caballo(true));
		casillas.get(63).setPieza(new Torre(true));
        }
	}



