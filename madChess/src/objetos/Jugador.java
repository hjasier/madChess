package objetos;

import piezas.Rey;

public class Jugador {
	
	private String nombre;
	private int rank;
	private String passw;
	private String img_route;
	private Boolean isWhite = false;
	private Rey rey;
	
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
	
	
	
	
	

}
