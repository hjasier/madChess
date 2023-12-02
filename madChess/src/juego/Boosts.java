package juego;


import java.util.ArrayList;


import componentes.InfoMsg;
import objetos.Casilla;
import objetos.Jugador;
import objetos.Pieza;
import objetos.Tablero;
import utils.Session;
import ventanas.Juego;

public class Boosts {
		
		protected static String curBoost;
		public static ArrayList<Boost> boostActivos = new ArrayList<>();


		public static void checkBoosts(Casilla casilla, Jugador curJugador) {
			
			if (curBoost == null) {return;}
			switch(curBoost) {
				case "HIELO":
					//casilla.setDebugClr(Color.blue);
					Hielo boostHielo = new Hielo(casilla);
					setBloqueoCasillasAdyacentes(casilla,true);
					curBoost = null;
					boostActivos.add(boostHielo);
					break;
				case "PRESAGIO":
					String color = "Blancas";
					if (!curJugador.getIsWhite()) {
						color = "Negras";
					}
					MalPresagio boostMalPresagio = new MalPresagio(color);
					curBoost = null;
					boostActivos.add(boostMalPresagio);
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
	    	InfoMsg.alert("Selecciona la casilla central a la que aplicar el rango");
	    }
	    
	    public static void boostMalPresagio() {
		    curBoost = "PRESAGIO";
		    InfoMsg.alert("Ganas la partida si dura más de 20 movimientos");
			
		}
	    
	    public static void boostBomba() {
	        curBoost = "BOMBA";
	        InfoMsg.alert("Selecciona una casilla aliada para colocar la Bomba");
	        
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


class MalPresagio extends Boost{
	
	protected String color = null;
	
	public MalPresagio(String color){
		cont = 20;
		this.color = color;
		
	}
	@Override
	public void check() {
		cont--;
		
		
		if(cont==0) {
			InfoMsg.alert("Han pasado veinte turnos, las "+ color + " ganan la partida!");
		}else if (cont < 0){return;
		}else if(cont == 10 || cont == 5) {
			Session.getVentana().getPanelJuego().printMovimiento("En " + cont + " turnos acabará la partida ⚠️");
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
        	Casilla curCasilla = piezaBomba.getCasillaParent();
        	System.out.println(curCasilla.getPos());
        	Boosts.explotacionBomba(curCasilla);
        	
			Session.getVentana().getPanelJuego().printMovimiento("EXPLOTACION");
			Session.getPartida().getTablero().initAnimacionExplosion(curCasilla);
        	
        }else if (cont < 0){return;}    
        else {
        	Session.getVentana().getPanelJuego().printMovimiento("Movimiento para que explote: "+ cont);
        	
        }
    }

    
}

class Boost {
	protected int cont;

	public void check() {

	}
}
