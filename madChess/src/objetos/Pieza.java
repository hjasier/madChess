package objetos;

import java.util.ArrayList;
import javax.swing.ImageIcon;

public class Pieza {

	
	protected String nombre;
	protected ImageIcon img;
	protected ArrayList<Casilla> movimientos;
	
	protected Jugador propietario;
	
	protected Casilla initPos;

	
	/**
	 * 
	 * El resto de atributos posicion etc los añadimos cuando empecemos con la lógica
	 * 
	 * */
	
	
	public Pieza(String nombre) {
		super();
		this.nombre = nombre;
		 this.img = new ImageIcon(getClass().getResource("../src_piezas/" + nombre + ".png"));
	}


	public ImageIcon getImg() { 
		return this.img;
	}


	
		
	
}
