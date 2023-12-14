package juego;


import java.util.ArrayList;
import java.util.Random;

import componentes.InfoMsg;
import objetos.Casilla;
import objetos.Jugador;
import objetos.Pieza;
import objetos.Tablero;
import utils.Infos;
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
	    	InfoMsg.alert(Infos.HIELOINFO);
	    }
	    
	    public static void boostMalPresagio() {
		    curBoost = "PRESAGIO";
		    InfoMsg.alert(Infos.PRESAGIOINFO);
		    checkBoosts(null, Session.getPartida().getCurPlayer());
			
		}
	    
	    public static void boostBomba() {
	        curBoost = "BOMBA";
	        InfoMsg.alert(Infos.BOMBAINFO);
	        
	    }
	    
	    public static void boostControl() {
	        InfoMsg.alert(Infos.CONTROLINFO);
	        Pieza pieza = getPiezaAleatoria();
	        Control boostControl = new Control(pieza, Session.getPartida().getCurPlayer());
			curBoost = null;
			pieza.setAlterColor();
			pieza.getCasillaParent().repaint();
			boostActivos.add(boostControl);
	        
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
	    
	    
	    
	    public static ArrayList<Pieza> getCasillasEnemigas(ArrayList<Casilla> casillas, Jugador nowPlaying){
			ArrayList<Pieza> piezas = new ArrayList<>();
			for(Casilla casilla : casillas ) {
				if (casilla.getPieza() != null && casilla.getPieza().getIsWhite() != nowPlaying.getIsWhite()) {
					piezas.add(casilla.getPieza());
				}
			}
			
			return piezas;
		}
	    
	    public static Pieza getPiezaAleatoria() {
	    	ArrayList<Pieza> piezas = getCasillasEnemigas(Session.getPartida().getTablero().getCasillas(), Session.getPartida().getCurPlayer());
	    	int numeroAleatorio = new Random().nextInt(piezas.size());
	    	Pieza pieza = piezas.get(numeroAleatorio);
	    	return pieza;
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
			InfoMsg.alert("Han pasado veinte turnos, "+ player.getUsuario().getUsername() + " gana");
		}
		else if (cont < 0){return;}
		else if(cont == 10 || cont == 5) {
			Session.getPartida().printMovimientoFormateado("En " + cont + " "+Infos.FINPARTIDACONTADOR);
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
        	
        	Session.getPartida().printMovimientoFormateado(Infos.BOOM);
			Session.getPartida().getTablero().initAnimacionExplosion(curCasilla);
        	
        }  
        else {
        	Session.getPartida().printMovimientoFormateado(Infos.CONTADOREXPLOSION + cont);
        }
    }

    
}

class Control extends Boost{
	
	protected Jugador player;
	protected Pieza pieza;
	public Control(Pieza pieza, Jugador player){
		cont = 4;
		this.pieza = pieza;
		this.player = player;
		
		
	}
	
	@Override
	public void check() {
		cont--;
		
		if(cont==0) {
			Session.getPartida().printMovimientoFormateado("Han pasado 4 turnos, "+ player.getUsuario().getUsername() + " pierde el control");
			pieza.setAlterColor();
			pieza.getCasillaParent().repaint();
		}   
	}  
	   
}      

class Boost {
	protected int cont;
	public void check(){}
}
