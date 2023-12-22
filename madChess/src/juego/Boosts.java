package juego;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import componentes.InfoMsg;
import objetos.Boost;
import objetos.Casilla;
import objetos.Jugador;
import objetos.Pieza;
import utils.Infos;
import utils.Session;
import utils.utils;


public class Boosts {
		
		protected static BOOSTS curBoost;
		protected static ArrayList<Boost> boostActivos = new ArrayList<>();
		protected static ArrayList<Boost> boostInactivos = new ArrayList<>();
		
		public enum BOOSTS {HIELO, PRESAGIO , BOMBA, CONTROL, MINA};
		
		
		private static Boost createBoost(BOOSTS boostType, Casilla casilla, Jugador curPlayer) {
		    switch (boostType) {
		        case HIELO:
		            return new Hielo(casilla);
		        case PRESAGIO:
		            return new MalPresagio(curPlayer);
		        case BOMBA:
		            return new Bomba(casilla.getPieza(), casilla);
		        default:
		            return null;
		    }
		}
		

		
		
		
		public static void checkBoosts(Casilla casilla, Jugador curPlayer) {
		    if (curBoost == null) {
		        return;
		    }

		    Boost boost = createBoost(curBoost, casilla, curPlayer);
		    if (boost != null) {
		        boost.config();
		        boostActivos.add(boost);
		        postBoostTS(boost);
		    }

		    curBoost = null;
		}
		
		




		public static void boostHielo() {
	        curBoost = BOOSTS.HIELO;
	    	utils.alert(Infos.HIELOINFO, "Boost Hielo", "hielo");
	    }

		



		public static void boostMalPresagio() {
		    curBoost = BOOSTS.PRESAGIO;
		    utils.alert(Infos.PRESAGIOINFO, "Boost Presagio", "presagio");
		    checkBoosts(null, Session.getPartida().getCurPlayer());
			
		}
	    
	    public static void boostBomba() {
	        curBoost = BOOSTS.BOMBA;
	        utils.alert(Infos.BOMBAINFO, "Boost BomberMan", "bomba");
	        
	    }
	    
	    public static void boostControl() {
	    	utils.alert(Infos.CONTROLINFO, "Boost Control", "control");
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
	    
	    
	    public static void explotarBomba(Casilla casilla) {
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
	    
	    
	    
	    
	    
		
	    private static void postBoostTS(Boost boost) {
	    	if (Session.getDatosPartida().getModoDeJuego() == modoJuego.ONLINE) {
			try {
				Session.getCtsConnection().postBoost(boost);
			} catch (IOException e) {
				e.printStackTrace();
				}
			}
		}
	    
		public static void getStcBoost(Boost boost) {
			boost.config();
	        boostActivos.add(boost);
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



		public static ArrayList<Boost> getBoostActivos() {
			return boostActivos;
		}
		public static ArrayList<Boost> getBoostInactivos() {
			return boostInactivos;
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
		}
	}
	
	@Override
	public void config() {
		Casilla casilla = Session.getPartida().getTablero().getCasilla(casillaCentral);
		Boosts.setBloqueoCasillasAdyacentes(casilla, true);
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
	protected Casilla casillaInicial;
	
    public Bomba(Pieza pieza,Casilla casillaInicial) {
        cont = 5; // Duración de la cuenta regresiva antes de la explosión
        this.piezaBomba = pieza;
        this.casillaInicial = casillaInicial;
        
    }

    
    
    @Override
    public void check() {
        cont--;
        if (cont == 0) {
        	Casilla curCasilla = piezaBomba.getCasillaParent();
        	System.out.println(curCasilla.getPos());
        	Boosts.explotarBomba(curCasilla);
        	
        	Session.getPartida().printMovimientoFormateado(Infos.BOOM);
			Session.getPartida().getTablero().initAnimacionExplosion(curCasilla);
        	
        }  
        else {
        	Session.getPartida().printMovimientoFormateado(Infos.CONTADOREXPLOSION + cont);
        }
    }
    
    @Override
	public void config() {
    	Pieza pieza = Session.getPartida().getTablero().getCasilla(casillaInicial).getPieza();
    	pieza.setIsBomberman(true);
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


