package objetos;

import java.io.Serializable;
import java.util.Date;

import Sockets.ClientPOST;
import piezas.Rey;

public class Jugador implements Serializable{
	
	private int userId;
	private int rank;
	private String username;
	private String passw;
	private String img_route;
	private Boolean isWhite = false;
	private int tiempoRestante;
	private Date initTime; 
	private Rey rey;
	private int preferedTheme;
	


	public Jugador(int _id, String username, String passw) {
		super();
		this.userId = _id;
		this.username = username;
		this.passw = passw;
	}
	
	public Jugador(String username , int rank, String img_route, int preferedTheme) {
		super();
		this.rank = rank;
		this.username = username;
		this.img_route = img_route;
		this.preferedTheme = preferedTheme;
	}

	public Jugador(String string) {
		this.username = string;
	}


	public int getUserId() {
		return userId;
	}


	public int getTiempoRestante() {
		return tiempoRestante;
	}



	public void setTiempoRestante(int tiempoRestante) {
		this.tiempoRestante = tiempoRestante;
	}



	public Date getInitTime() {
		return initTime;
	}



	public void setInitTime(Date initTime) {
		this.initTime = initTime;
	}



	public Rey getRey() {
		return rey;
	}



	public void setRey(Rey rey) {
		this.rey = rey;
	}



	public void setIsWhite(Boolean isWhite) {
		this.isWhite = isWhite;
	}



	public Boolean getIsWhite() {
		return isWhite;
	}


	public Boolean checkPassword(String passwd) {
		return this.passw.equals(passwd);
	}



	public String getNombre() {
		return this.username;
	}



	
	

}
