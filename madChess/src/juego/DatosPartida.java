package juego;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import Sockets.ClienteHandler;
import objetos.Jugador;
import objetos.Movimiento;

public class DatosPartida implements Serializable{

	protected String modoDeJuego;
	protected Date fecha;
	protected String gameId;
	protected ArrayList<Jugador> jugadores = new ArrayList<Jugador>(); // Es un arraylist por que en el futuro queremos que puedan jugar hasta 4 por turnos etc..
	protected ArrayList<Movimiento> movimientos;
	protected String tipoPartida;
	protected ArrayList<ClienteHandler> clientes; 
	
	
	
	
	public DatosPartida(String modoDeJuego) {
		super();
		this.modoDeJuego = modoDeJuego;
		setFecha();
		setGameId();
	}
	
	
	
	public ArrayList<ClienteHandler> getClientes() {
		return clientes;
	}


	public void setClientes(ArrayList<ClienteHandler> clientes) {
		this.clientes = clientes;
	}


	public void setModoDeJuego(String modoDeJuego) {
		this.modoDeJuego = modoDeJuego;
	}
	
	public String getModoDeJuego() {
		return modoDeJuego;
	}


	public String getGameId() {
		return gameId;
	}


	private void setFecha() {
		if (this.fecha==null) {
			this.fecha = new Date(); 
		}
		
	}

	
	public String getTipoPartida() {
		return tipoPartida;
	}



	public void setTipoPartida(String tipoPartida) {
		this.tipoPartida = tipoPartida;
	}



	public ArrayList<Jugador> getJugadores() {
		return jugadores;
	}
	
	public void setJugador(Jugador jugador) {
		this.jugadores.add(jugador);
	}
	
	
    private void setGameId() {
    	if (this.gameId!=null) {return;}
        StringBuilder gameID = new StringBuilder();
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        // Generar la combinación aleatoria
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            int indice = random.nextInt(chars.length());
            char caracterAleatorio = chars.charAt(indice);
            gameID.append(caracterAleatorio);
        }
        
        this.gameId = gameID.toString();
    }
	

}
