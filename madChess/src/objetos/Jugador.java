package objetos;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
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
	private ArrayList<Boolean> boosts = new ArrayList<Boolean>(Arrays.asList(false, false, false, false, false, false)); //los boosts que tiene el jugador si es madChess
	private ArrayList<Boolean> alters = new ArrayList<Boolean>(Arrays.asList(false, false, false, false, false,false)); //los alters que tiene el jugador si es madChess
	private Color playColor; //color del usuario en movimeintos, chat etc..
	private Date initTime; //cuando ha empezado el movimiento
	
	private Usuario usuario;
	
	

	public Jugador(Usuario usuario) {
		this.usuario = usuario;
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

	

	public ArrayList<Boolean> getBoosts() {
		return boosts;
	}

	public void setBoosts(ArrayList<Boolean> arrayList) {
		this.boosts = arrayList;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	public void setAlters(ArrayList<Boolean> selectedAlters) {
		this.alters = selectedAlters;
		
	}

	public ArrayList<Boolean> getAlters() {
		return alters;
	}
	

}
