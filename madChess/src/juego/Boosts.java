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
		public static ArrayList<Boost> boostInactivos = new ArrayList<>();

		public static void checkBoosts(Casilla casilla, Jugador curPlayer) {
			
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
					MalPresagio boostMalPresagio = new MalPresagio(curPlayer);
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
		    checkBoosts(null, Session.getPartida().getCurPlayer());
			
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



		private static void removeBoost(Boost boost) {
			//igual sería interesante guardar en que ronda, que elementos ha matado etc para las analíticas
			boostInactivos.add(boost);
			boostActivos.remove(boost);
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
	protected Jugador player;
	
	public MalPresagio(Jugador curPlayer){
		cont = 20;
		player = curPlayer;
		
	}
	
	@Override
	public void check() {
		cont--;
		
		
		if(cont==0) {
			InfoMsg.alert("Han pasado veinte turnos, "+ player.getNombre() + " gana");
		}else if (cont < 0){return;
		}else if(cont == 10 || cont == 5) {
			Session.getPartida().printMovimientoFormateado("En " + cont + " turnos acabará la partida ⚠️");
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
        	
        	Session.getPartida().printMovimientoFormateado("💥💥BOOOOOM!!!💥💥");
			Session.getPartida().getTablero().initAnimacionExplosion(curCasilla);
        	
        }else if (cont < 0){return;}    
        else {
        	Session.getPartida().printMovimientoFormateado("Explosión en: "+ cont);
        }
    }

    
}

class Boost {
	protected int cont;

	public void check() {}
	
	
}
