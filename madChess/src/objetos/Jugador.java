package objetos;

import java.awt.Color;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

import Sockets.ClientPOST;
import piezas.Rey;
import utils.Themes.piezasThemes;
import utils.Themes.tableroThemes;

public class Jugador implements Serializable{
	
	//Cosas del juegador
	private Boolean isWhite = false; 
	private int tiempoRestante; //tiempo restante del jugador en segundos
	private Rey rey; //la pieza rey del jugador
	protected int[] boosts; //los boosts que tiene el jugador si es madChess
	private Color playColor; //color del usuario en movimeintos, chat etc..
	private Date initTime; //cuando ha empezado el movimiento
	
	private Usuario usuario;
	
	
	//Cosas a mover a clase usuario
	private tableroThemes preferedTheme;
	private piezasThemes preferedPiezaTheme;
	private int userId;
	private int rank;
	private String username;
	private String img_route;
	
	


	public Jugador(int _id, String username, String passw) {
		super();
		this.userId = _id;
		this.username = username;
	}
	
	public Jugador(String username , int rank, String img_route, String preferedTheme) {
		super();
		this.rank = rank;
		this.username = username;
		this.img_route = img_route;
		//this.preferedTheme = tableroThemes.valueOf(preferedTheme);
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


	public Rey getRey() {
		return rey;
	}



	public void setRey(Rey rey) {
		this.rey = rey;
	}
	
	public Date getInitTime() {
		return initTime;
	}



	public void setInitTime(Date initTime) {
		this.initTime = initTime;
	}




	public void setIsWhite(Boolean isWhite) {
		this.isWhite = isWhite;
	}



	public Boolean getIsWhite() {
		return isWhite;
	}
	

	public Color getPlayColor() {
		return playColor;
	}

	public void setPlayColor(Color playColor) {
		this.playColor = playColor;
	}

	public String getNombre() {
		return this.username;
	}


	public tableroThemes getPreferedTheme() {
		return preferedTheme;
	}

	public void setPreferedTheme(tableroThemes preferedTheme) {
		this.preferedTheme = preferedTheme;
	}

	public void setPreferedPiezaTheme(piezasThemes preferedPiezaTheme) {
		this.preferedPiezaTheme = preferedPiezaTheme;
	}

	public piezasThemes getPreferedPiezaTheme() {
		return preferedPiezaTheme;
	}

	public int[] getBoosts() {
		return boosts;
	}

	public void setBoosts(int[] boosts) {
		this.boosts = boosts;
	}

	
	

}
