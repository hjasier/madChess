package objetos;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.ImageIcon;

import utils.Session;
import utils.Themes.piezasThemes;

public class Pieza implements Serializable {

	
	private String nombre;
	private ImageIcon img;
	private HashMap<String , Integer> movimientos;
	private Jugador propietario;
	private Boolean isWhite;
	protected Boolean pMoved = false;
	protected Boolean isAlter = false;
	protected Boolean isBomberman = false;
	protected Boolean isBoosted = false;



	public ArrayList<Casilla> getCasillasCome(Casilla curCasilla, ArrayList<Casilla> casillas) {

        return null;

    }
	
	
	
	public Pieza(String nombre, Boolean isWhite, boolean isAlter) {
		super();
		this.nombre = nombre;
		this.isWhite = isWhite;
		this.isAlter = isAlter;
		setColor();
		}

	
	public ImageIcon getImg() { 
		return this.img;
	}

	public void setColor() {
		char isWhiteChar = isWhite ? 'w':'b';
		piezasThemes piezaTheme = Session.getCurrentUser().getPreferedPiezaTheme();
		String piezaThemeName = "DF";
		if (piezaTheme != null) {
			piezaThemeName = piezaTheme.name();
		}
		if (isAlter) {
			piezaThemeName += "ALTER";
		}
		
		this.img = new ImageIcon(getClass().getResource("/srcmedia/piezas/" + isWhiteChar+nombre+piezaThemeName+ ".png"));
	
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

	public Boolean getIsBoosted() {
		return isBoosted;
	}



	public void setIsBoosted(Boolean isBoosted) {
		this.isBoosted = isBoosted;
	}
	
	public void setAlterColor() {
		this.isWhite = !this.isWhite;
		setColor();
	}

	
	public Casilla getCasillaParent() {
		//PARA MEJORAR LA EFICIENCIA DEL BOT HABRÍA QUE HACER QUE CADA VEZ QUE MOVAMOS UNA PIEZA SE ACTUALICE SU CASILLA PARENT
		// Y LA FUNCIÓN SOLO DEVUELVA EL VALOR DE LA VARIABLE
		
        ArrayList<Casilla> casillas = Session.getPartida().getTablero().getCasillas();
        for (Casilla fila : casillas) {
            
                if (fila.getPieza() != null && fila.getPieza().equals(this)) {
                	return fila; // Si se encuentra la pieza, se devuelve la casilla
                
            }
        }
        return null; // Si no se encuentra la pieza, se devuelve null
    }



	public void reloadImg() {
		char isWhiteChar = isWhite ? 'w':'b';
		piezasThemes piezaTheme = Session.getCurrentUser().getPreferedPiezaTheme();
		String piezaThemeName = "";
		if (piezaTheme != null) {
			piezaThemeName = piezaTheme.name();
		}
		if (isAlter) {
			piezaThemeName += "ALTER";
		}
		this.img = new ImageIcon(getClass().getResource("/srcmedia/piezas/"+ isWhiteChar+nombre+piezaThemeName+ ".png"));
	}



	
	
	
	
}
