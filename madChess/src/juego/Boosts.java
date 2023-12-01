package juego;

import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.event.*;

import javax.swing.JOptionPane;

import objetos.Casilla;
import objetos.Pieza;
import objetos.Tablero;

public class Boosts {
	
		protected static String curBoost;
		public static ArrayList<Boost> boostActivos = new ArrayList<>();
		
	
		public static void checkBoosts(Casilla casilla) {
			
			if (curBoost == null) {return;}
			switch(curBoost) {
				case "HIELO":
					//casilla.setDebugClr(Color.blue);
					Hielo boostHielo = new Hielo(casilla);
					setBloqueoCasillasAdyacentes(casilla,true);
					curBoost = null;
					boostActivos.add(boostHielo);
					break;
				case "BOMBA":
				    Bomba boostBomba = new Bomba(casilla.getPieza(), casilla);
				    curBoost = null;
				    casilla.getPieza().setIsBomberman(true);
				    boostActivos.add(boostBomba);
				    break;
					
			}
			
				
		}
		
		
		
	    public static void boostHielo() {
	        curBoost = "HIELO";
	    	JOptionPane.showMessageDialog(null, "Selecciona una casilla");
	    }
	    
	    public static void boostBomba() {
	        curBoost = "BOMBA";
	        JOptionPane.showMessageDialog(null, "Selecciona una casilla aliada para colocar la Bomba");
	    }
			
	    
	    public static void setBloqueoCasillasAdyacentes(Casilla casilla,Boolean estado) {
	        int fila = casilla.getFila();
	        char columna = casilla.getColumna();
	        
	        // Array de desplazamientos para las casillas adyacentes
	        int[] dx = {0, 0, -1, 1,-1,1,-1,1,0};
	        int[] dy = {-1, 1, 0, 0,1,-1,-1,1,0};

	        for (int i = 0; i < 9; i++) {
	            int nuevaFila = fila + dx[i];
	            char nuevaColumna = (char) (columna + dy[i]);
	            
	            // Obtener la casilla adyacente si está dentro de los límites del tablero
	            Casilla adyacente = Session.getPartida().getCasilla(nuevaFila, nuevaColumna);
	            if (adyacente != null) {
	            	//adyacente.setDebugClr(Color.blue);
	            	adyacente.setIsHielo(estado); 
	            	}
	            }
	        }
	    
	    public static void explotacionBomba(Casilla casilla) {
	    	
	    	
	        int fila = casilla.getFila();
	        char columna = casilla.getColumna();
	        
	        // Array de desplazamientos para las casillas adyacentes
	        int[] dx = {0, 0, -1, 1,-1,1,-1,1,0};
	        int[] dy = {-1, 1, 0, 0,1,-1,-1,1,0};

	        for (int i = 0; i < 9; i++) {
	            int nuevaFila = fila + dx[i];
	            char nuevaColumna = (char) (columna + dy[i]);
	            
	            // Obtener la casilla adyacente si está dentro de los límites del tablero
	            Casilla adyacente = Session.getPartida().getCasilla(nuevaFila, nuevaColumna);
	            if (adyacente != null) {
	            	//adyacente.setDebugClr(Color.blue);
	            	adyacente.setPieza(null);
	            	}
	            }
	        }
	    
	    public static void boostMalPresagio () {
		System.out.println("Mal Presagio");
		
		
	}
	
	
	    public static void updateBoost() {
	    	
	    	for (Boost boost : boostActivos) {
	    		boost.check();
	    		
	    	}
	    }
	
}


class Hielo extends Boost{
	
	protected Casilla casillaCentral;
	
	public Hielo(Casilla casillaCentral){
		cont = 4;
		this.casillaCentral = casillaCentral;
	}
	@Override
	public void check() {
		cont--;
		if(cont==0) {
			Boosts.setBloqueoCasillasAdyacentes(casillaCentral, false);
//			Boosts.boostActivos.remove((Boost)this);
			
		}
	}
	
}


class Bomba extends Boost {

	protected Pieza piezaBomba;
    public Bomba(Pieza pieza,Casilla casillaCentral) {
        cont = 5; // Duración de la cuenta regresiva antes de la explosión
        this.piezaBomba = pieza;
    }

    
    
    @Override
    public void check() {
        cont--;
        if (cont == 0) {
        	Boosts.explotacionBomba(encontrarCasillaDePieza(piezaBomba));
        	
			Session.getVentana().getPanelJuego().printMovimiento("EXPLOTACION");
        	
        }else if (cont < 0){return;}    
        else {
        	Session.getVentana().getPanelJuego().printMovimiento("Movimiento para que explote: "+ cont);
        	
        }
    }

    private Casilla encontrarCasillaDePieza(Pieza pieza) {
        // Suponiendo que el tablero tiene un arreglo de casillas
        ArrayList<Casilla> casillas = Session.getPartida().getTablero().getCasillas();
        for (Casilla fila : casillas) {
            
                if (fila.getPieza() != null && fila.getPieza().equals(pieza)) {
                	return fila; // Si se encuentra la pieza, se devuelve la casilla
                
            }
        }
        return null; // Si no se encuentra la pieza, se devuelve null
    }
}

class Boost {
	protected int cont;

	public void check() {

	}
}
