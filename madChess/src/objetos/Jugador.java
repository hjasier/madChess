package objetos;

import java.io.Serializable;
import java.util.Date;

import juego.GestorSockets;
import piezas.Rey;

public class Jugador implements Serializable{
	
	private int rank;
	private String username;
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



	public Jugador(String username, String passw) {
		super();
		this.username = username;
		this.passw = passw;
	}
	
	
	
	public Jugador(String string) {
		// TODO Auto-generated constructor stub
	}


	@Override
	public String toString() {
		return "Jugador [rank=" + rank + ", username=" + username + ", passw=" + passw + ", img_route=" + img_route
				+ ", isWhite=" + isWhite + ", tiempoRestante=" + tiempoRestante + ", initTime=" + initTime + ", rey="
				+ rey + ", gestorSocket=" + gestorSocket + "]";
	}


	public Boolean getIsWhite() {
		return isWhite;
	}

	public void setIsWhite(Boolean isWhite) {
		this.isWhite = isWhite;
	}



	public Boolean checkPassword(String passwd) {
		return this.passw.equals(passwd);
	}



	public String getNombre() {
		return username;
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


	public int getRank() {
		return rank;
	}


	public void setRank(int rank) {
		this.rank = rank;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassw() {
		return passw;
	}


	public void setPassw(String passw) {
		this.passw = passw;
	}


	public String getImg_route() {
		return img_route;
	}


	public void setImg_route(String img_route) {
		this.img_route = img_route;
	}


	public void setGestorSocket(GestorSockets gestorSocket) {
		this.gestorSocket = gestorSocket;
	}
	
	
	
	
	

}
