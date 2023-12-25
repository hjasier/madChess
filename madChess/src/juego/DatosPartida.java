package juego;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

import Sockets.ClientHandler;
import objetos.Jugador;
import objetos.Movimiento;
import objetos.Usuario;

public class DatosPartida implements Serializable{

	protected modoJuego modoDeJuego;//Online o local
	protected partidaTipo tipoPartida = partidaTipo.CLASICA; //Normal o madChess
	protected Date fechaIni;
	protected Date fechaFin;
	protected String gameId;
	protected int duracionEstablecida = 600;
	protected ArrayList<Jugador> jugadores = new ArrayList<Jugador>(); // Es un arraylist por que en el futuro queremos que puedan jugar hasta 4 por turnos etc..
	protected ArrayList<Jugador> ganadores = new ArrayList<Jugador>(); // arraylist de el ganador o los ganadores dependiendo la cantidad de la modalidad
	protected ArrayList<Movimiento> movimientos = new ArrayList<Movimiento>();
	protected Boolean isTerminada = false;
	private boolean isReplay = false;
	

	
	
	
	public DatosPartida(modoJuego modoDeJuego) {
		super();
		this.modoDeJuego = modoDeJuego;
		setFechaIni();
		setFechaFin();
		setGameId();
	}
	
	
	
	public void addMovimiento(Movimiento movimiento) {
		if (movimiento != null) {
			movimientos.add(movimiento);
		}
		else {
			System.out.println("ERROR AL GUARDAR EL MOVIMIENTO");
		}
		
		System.out.println(movimientos.size());
	}

	public void setModoDeJuego(modoJuego modoDeJuego) {
		this.modoDeJuego = modoDeJuego;
	}
	
	public modoJuego getModoDeJuego() {
		return modoDeJuego;
	}

	public boolean isOnline() {
		return modoDeJuego == modoJuego.ONLINE;
	}
	
	public String getGameId() {
		return gameId;
	}


	private void setFechaIni() {
		if (this.fechaIni==null) {
			this.fechaIni = new Date(); 
		}
		
	}
	private void setFechaFin() {
		if (this.fechaFin==null) {
			this.fechaFin = new Date(); 
		}
		
	}

	
	
	public Date getFechaIni() {
		return fechaIni;
	}



	public void setFechaIni(Date fechaIni) {
		this.fechaIni = fechaIni;
	}



	public Date getFechaFin() {
		return fechaFin;
	}



	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}



	public ArrayList<Jugador> getGanadores() {
		return ganadores;
	}



	public ArrayList<Movimiento> getMovimientos() {
		return movimientos;
	}



	public partidaTipo getTipoPartida() {
		return tipoPartida;
	}



	public void setTipoPartida(partidaTipo tipoPartida) {
		this.tipoPartida = tipoPartida;
	}



	public ArrayList<Jugador> getJugadores() {
		return jugadores;
	}
	
	public void setJugador(Usuario logedUser) {
		this.jugadores.add(new Jugador(logedUser));
	}
	
	
	
    public Boolean getIsTerminada() {
		return isTerminada;
	}



	public void setIsTerminada(Boolean isTerminada) {
		this.isTerminada = isTerminada;
	}



	private void setGameId() {
		//TODO: que compruebe en la db que no se ha usado ya ese código y en caso contrario que se vuelva a llamar
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



	public boolean isReplay() {
		return isReplay ;
	}



	public void setMovientos(byte[] bs) {
		try {
			movimientos = deserializarMovimientos(bs);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static ArrayList<Movimiento> deserializarMovimientos(byte[] serializedData) throws IOException, ClassNotFoundException {
        try (ByteArrayInputStream bis = new ByteArrayInputStream(serializedData);
             ObjectInputStream ois = new ObjectInputStream(bis)) {

            return (ArrayList<Movimiento>) ois.readObject();
        }
    }
	
	public void setGameId(String gameId) {
        this.isReplay = true;
        this.gameId = gameId;
    }



	public int getDuracionEstablecida() {
		return duracionEstablecida;
	}



	public void setDuracionEstablecida(int duracionEstablecida) {
		this.duracionEstablecida = duracionEstablecida;
	}
	
	

}
