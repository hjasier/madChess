package componentes;

import java.util.HashMap;
import java.util.Map;

public class Pieza {
//	protected String nombre;
	//
	protected Casilla[] despalzamientoDefault;
	
	//en el mapa las claves son los nombres de las piezas y los valores mapas que contienen la refe a la imagen y la cantidad 
	protected HashMap<String, Map<String, Integer>> mapPiezas;
	
	
	public Pieza() {
		mapPiezas = new HashMap<String, Map<String,Integer>>();
	}
	

	//metodos para gestionar las piezas del mapa 
	
	public void AgregarPiezas(String nombre, String refe_imagen, Integer cantidad) {
		
		Map<String, Integer> infoPieza = new HashMap<>();
		
		infoPieza.put(refe_imagen, cantidad);
		
		mapPiezas.put(nombre, infoPieza);
        
	}
	
	public boolean BuscarPieza(String nombre) {
		 if (mapPiezas.containsKey(nombre)) {
	           return true;
	        } else {
	        	System.out.println("La pieza no se ha encontrado ");
	        	return false;	       
	        }
	 }
	 
	public void EditarPieza(String nombre, String refe_imagen, Integer cantidad) {
		if (BuscarPieza(nombre)) {
			 AgregarPiezas(nombre, refe_imagen, cantidad);		 
		}
	}

	public void EliminarPieza(String titulo) {
		mapPiezas.remove(titulo);
	}
}
