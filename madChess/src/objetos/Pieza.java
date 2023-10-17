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
		 this.img = new ImageIcon("../src_piezas/" + nombre + ".png");
		 
		 System.out.println("../src_piezas/" + nombre + ".png");
	}


	public ImageIcon getImg() { //giova : que hace esto? osea pillo que tiene que ver con las imagenes, pero que hace en concreto?
		return img;
	}


	
		
	
}
