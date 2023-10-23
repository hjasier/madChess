package objetos;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ImageIcon;

public class Pieza {

	
	public String nombre;
	public ImageIcon img;
	public HashMap<String , Integer> movimientos;
	
	public Jugador propietario;
	
	

	public Pieza(String nombre) {
		super();
		this.nombre = nombre;
		 this.img = new ImageIcon(getClass().getResource("../src_piezas/" + nombre + ".png"));
	}


	public ImageIcon getImg() { 
		return this.img;
	}


	
		
	
}
