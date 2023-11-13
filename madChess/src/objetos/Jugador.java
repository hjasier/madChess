package objetos;

import java.util.Date;

import juego.GestorSockets;
import piezas.Rey;

public class Jugador {
	
	private String nombre;
	private int rank;
	private String passw;
	private String img_route;
	private Boolean isWhite = false;
	private int tiempoRestante;
	private Date initTime; 
	private Rey rey;
	private GestorSockets gestorSocket;
	
	
	
	public Jugador(GestorSockets gestorSocket) {
		super();
		this.gestorSocket = gestorSocket;
	}

	
	public GestorSockets getGestorSocket() {
		return gestorSocket;
	}

	public void setGestorSocket() {
		if(gestorSocket == null) {
		this.gestorSocket = new GestorSockets();
		}else {
			System.out.println("Ya existe un gestor");
		}
	}



	public Jugador(String nombre) {
		super();
		this.nombre = nombre;
		
	}
	
	
	
	public Boolean getIsWhite() {
		return isWhite;
	}

	public void setIsWhite(Boolean isWhite) {
		this.isWhite = isWhite;
	}



	private Boolean checkPassword(String passwd) {
		return (this.passw == passwd);
	}



	public String getNombre() {
		return nombre;
	}



	public Rey getRey() {
		return rey;
	}



	public void setRey(Rey rey) {
		this.rey = rey;
	}



	public Date getInitTime() {
		return initTime;
	}



	public void setInitTime(Date now) {
		this.initTime = now;
	}



	public void setTiempoRestante(int tiempoRestante) {
		this.tiempoRestante = tiempoRestante;
	}



	public int getTiempoRestante() {
		return tiempoRestante;
	}
	
	
	
	
	

}
