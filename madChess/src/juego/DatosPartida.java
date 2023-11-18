package juego;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import objetos.Jugador;

public class DatosPartida {

	protected String modoDeJuego;
	protected Date fecha;
	protected String gameId;
	protected ArrayList<Jugador> jugadores = new ArrayList<Jugador>(); // Es un arraylist por que en el futuro queremos que puedan jugar hasta 4 por turnos etc..
	
	
	
	
	
	
	
	public DatosPartida(String modoDeJuego) {
		super();
		this.modoDeJuego = modoDeJuego;
		setFecha();
		setGameId();
	}
	
	
	public String getModoDeJuego() {
		return modoDeJuego;
	}


	public String getGameId() {
		return gameId;
	}


	private void setFecha() {
		this.fecha = new Date(); 
	}


	public ArrayList<Jugador> getJugadores() {
		return jugadores;
	}
	
	public void setJugador(Jugador jugador) {
		this.jugadores.add(jugador);
	}
	
	
    private void setGameId() {
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
