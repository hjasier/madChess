package objetos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.ImageIcon;

public class Pieza {

	
	public String nombre;
	public ImageIcon img;
	public HashMap<String , Integer> movimientos;
	
	public Jugador propietario;
	protected ArrayList<Casilla> casillas;
	
	

	public Pieza(String nombre) {
		super();
		this.nombre = nombre;
		this.img = new ImageIcon(getClass().getResource("../src_piezas/" + nombre + ".png"));
	}
	
	public Pieza(String nombre, ArrayList<Casilla> casillas) {
		super();
		this.nombre = nombre;
		this.img = new ImageIcon(getClass().getResource("../src_piezas/" + nombre + ".png"));
		this.casillas = casillas;
	}

	public ImageIcon getImg() { 
		return this.img;
	}

	
	public ArrayList<Casilla> getCasillasDisponibles(Casilla curCasilla) {

		int casillaIndex = casillas.indexOf(curCasilla);
		System.out.println(casillaIndex);
		
		ArrayList<Casilla> casillasDisp = new ArrayList<>(); 
		casillasDisp.add(casillas.get(casillaIndex-8));
		casillasDisp.add(casillas.get(casillaIndex-16));
		
		return casillasDisp;
		
	}
	
		
	
}
