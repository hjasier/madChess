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
		 this.img = new ImageIcon(".../componentes.piezas/" + nombre + ".png");
		 
		 System.out.println(".../componentes.piezas/" + nombre + ".png");
	}


	public ImageIcon getImg() {
		return img;
	}


	
	
		
	
	
}
