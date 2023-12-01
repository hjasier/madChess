package objetos;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.ImageIcon;

import utils.Session;

public class Pieza implements Serializable {

	
	private String nombre;
	private ImageIcon img;
	private HashMap<String , Integer> movimientos;
	private Jugador propietario;
	private Boolean isWhite;
	protected Boolean pMoved = false;
	protected Boolean isAlter = false;
	protected Boolean isBomberman = false;
	
	public ArrayList<Casilla> getCasillasCome(Casilla curCasilla, ArrayList<Casilla> casillas) {

        return null;

    }
	
	
	
	public Pieza(String nombre, Boolean isWhite) {
		super();
		this.nombre = nombre;
		this.isWhite = isWhite;
		char isWhiteChar = isWhite ? 'w':'b';
		this.img = new ImageIcon(getClass().getResource("../srcmedia/" + isWhiteChar+nombre + ".png"));
	}

	
	public ImageIcon getImg() { 
		return this.img;
	}

	
	
	public Boolean getIsWhite() {
		return isWhite;
	}


	public ArrayList<Casilla> getCasillasDisponibles(Casilla curCasilla, ArrayList<Casilla> casillas) {

		return null;
		
	}


	public void setPMoved() {
		if (!this.pMoved) {
			this.pMoved = true;
		}
		
	}


	public boolean getPMoved() {
		return this.pMoved;
	}
	
	
	public boolean isAlter() {
		return isAlter;
	}


	public void setAlter(boolean isAlter) {
		this.isAlter = isAlter;
	}



	public Boolean getIsBomberman() {
		return isBomberman;
	}



	public void setIsBomberman(Boolean isBomberman) {
		this.isBomberman = isBomberman;
	}



	
	public Casilla getCasillaParent() {
        ArrayList<Casilla> casillas = Session.getPartida().getTablero().getCasillas();
        for (Casilla fila : casillas) {
            
                if (fila.getPieza() != null && fila.getPieza().equals(this)) {
                	return fila; // Si se encuentra la pieza, se devuelve la casilla
                
            }
        }
        return null; // Si no se encuentra la pieza, se devuelve null
    }
	
	
	
}
