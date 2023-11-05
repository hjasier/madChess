package objetos;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.ImageIcon;

public class Pieza {

	
	private String nombre;
	private ImageIcon img;
	private HashMap<String , Integer> movimientos;
	private Jugador propietario;
	private Boolean isWhite;
	protected Boolean pMoved = false;
	
	
	public ArrayList<Casilla> getCasillasCome(Casilla curCasilla, ArrayList<Casilla> casillas) {

        return null;

    }
	

	
	public Pieza(String nombre, Boolean isWhite) {
		super();
		this.nombre = nombre;
		this.isWhite = isWhite;
		char isWhiteChar = isWhite ? 'w':'b';
		this.img = new ImageIcon(getClass().getResource("../src_piezas/" + isWhiteChar+nombre + ".png"));
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
	
		
	
}
