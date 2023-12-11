package objetos;

import java.awt.Color;
import java.io.Serializable;
import java.util.Date;

import Sockets.ClientPOST;
import piezas.Rey;
import utils.Themes.piezasThemes;
import utils.Themes.tableroThemes;

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
	private tableroThemes preferedTheme;
	private Color playColor;
	private piezasThemes preferedPiezaTheme;
	


	public Jugador(int _id, String username, String passw) {
		super();
		this.userId = _id;
		this.username = username;
		this.passw = passw;
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

	
	

}
