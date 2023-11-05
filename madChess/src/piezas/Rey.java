package piezas;

import java.awt.Color;
import java.util.ArrayList;

import objetos.Casilla;
import objetos.MetodosInterfaz;
import objetos.Pieza;

public class Rey extends Pieza implements MetodosInterfaz{

	public Rey(Boolean isWhite) {
		super("k",isWhite);
	}

	
	
	@Override
    public ArrayList<Casilla> getCasillasDisponibles(Casilla curCasilla, ArrayList<Casilla> casillas) {
        int casillaIndex = casillas.indexOf(curCasilla);
        ArrayList<Casilla> casillasDisp = new ArrayList<>();
        int fila = curCasilla.getFila();
        char columna = curCasilla.getColumna();

        // Posibles movimientos del rey: arriba, abajo, izquierda y derecha
        int[][] movimientos = {
        			
        		{-1, -1},{0, -1},{1, -1},
        		{-1, 0}, /**Rey**/{1, 0}, 
        		{-1, 1}, {0, 1}, {1, 1}
        };

        for (int[] movimiento : movimientos) { //Recorre cada movimiento posible desde la posicion de la torre
            int nuevaFila = fila + movimiento[0];
            char nuevaColumna = (char) (columna + movimiento[1]);

            // Verifica que la casilla resultante esté dentro del tablero (filas 0 a 7 y columnas A a H)
            if (nuevaFila >= 0 && nuevaFila <= 7 && nuevaColumna >= 'A' && nuevaColumna <= 'H') {
            	
                Casilla casillaDisp = casillas.get(nuevaFila * 8 + (nuevaColumna - 'A'));
                
                if (
                		casillaDisp.getPieza()!=null&&
                		casillaDisp.getPieza().getIsWhite()==this.getIsWhite()
                	) {continue;}
               
                casillasDisp.add(casillaDisp);

                
                
            }
        }

        int[][] movimientosEnroque = {    			
        		{0,1},{0,-1}
        };
        for (int[] movimiento : movimientosEnroque) { //Recorre cada movimiento posible desde la posicion de la torre
            int nuevaFila = fila + movimiento[0];
            char nuevaColumna = (char) (columna + movimiento[1]);
            Casilla prevCasilla = null;
            
            // Verifica que la casilla resultante esté dentro del tablero (filas 0 a 7 y columnas A a H)
            while (nuevaFila >= 0 && nuevaFila <= 7 && nuevaColumna >= 'A' && nuevaColumna <= 'H') {
            	
                Casilla casillaDisp = casillas.get(nuevaFila * 8 + (nuevaColumna - 'A'));
                Casilla prevprevCasilla = casillas.get(casillaIndex-2);
                
                if (
                		casillaDisp.getPieza()!=null&& // Si existe pieza
                		casillaDisp.getPieza().getIsWhite()==this.getIsWhite()&& // Si la pieza es tuya
                		!((casillaDisp.getPieza() instanceof Torre)&&!this.pMoved&&!casillaDisp.getPieza().getPMoved())) // Si existe pieza pero es rey y no se han movido de posición (Enroque)
                {break;}
                
                if (casillaDisp.getPieza() instanceof Torre) {
                	//System.out.println("Existo");
                	
                	if (casillaDisp.getColumna() == 'A'){
                		//System.out.println("estoy en la A");
                		casillasDisp.remove(prevCasilla);
                		casillasDisp.add(prevprevCasilla);
                		break;
                	}else;
                	casillasDisp.add(prevCasilla);
                	
                }
                
                prevCasilla = casillaDisp;
                
                

                // Avanzo a la dirección del movimiento
                nuevaFila += movimiento[0];
                nuevaColumna = (char) (nuevaColumna + movimiento[1]);
                if (casillaDisp.getPieza()!=null) {break;}
            }
        }
        
        ArrayList<Casilla> casillasAmenaza = casillasJaque(casillas);
        casillasDisp.removeAll(casillasAmenaza);
        
        if(casillasAmenaza.contains(curCasilla)){
        	System.out.println("Jaque");
        }
        if(casillasAmenaza.contains(curCasilla) && casillasDisp.size() == 0) {
        	System.out.println("Jaque Mate");
        	curCasilla.setDebugClr(Color.red);
        }
        
        return casillasDisp;
        
        
    }
	
	
	public ArrayList<Casilla> casillasJaque(ArrayList<Casilla> casillas) {
		ArrayList<Casilla> casillasAmenaza = new ArrayList<>();
		for(Casilla casilla : casillas) {
			
			Pieza piezaCasilla = casilla.getPieza();
			if(piezaCasilla != null && piezaCasilla.getIsWhite() != this.getIsWhite() ) {
				
					ArrayList<Casilla> casillasDisp = piezaCasilla.getCasillasCome(casilla, casillas);
					casillasAmenaza.addAll(casillasDisp);
					
				}
			}
		return casillasAmenaza;
	}
	
	
	
	public ArrayList<Casilla> getCasillasSimulacion(ArrayList<Casilla>  casillas) { //NO HACE FALTA
		ArrayList<Casilla> casillasSimulacion = new ArrayList<Casilla>();
		for (Casilla casilla : casillas) {
			Pieza newPieza = casilla.getPieza(); // NO LO DUPLICA ASI QUE LOS CAMBIOS EN PIEZA SI SE CAMBIAN EN EL ORIGINAL
			Casilla newCasilla = new Casilla(null, casilla.getFila(), (casilla.getColumna()-'A'));
			newCasilla.setPieza(newPieza);
		    casillasSimulacion.add(newCasilla);
		}
		return casillasSimulacion;
	}
    
    
	public ArrayList<Casilla> casillasFuturaAmenaza(Casilla CasillaRey,ArrayList<Casilla> casillasDisp,ArrayList<Casilla> casillas){//NO HACE FALTA
		ArrayList<Casilla> casillasFuturasAmenaza = new ArrayList<>();
		
		ArrayList<Casilla> casillasSimulacion = getCasillasSimulacion(casillas);
		
		

		Pieza newPieza = CasillaRey.getPieza();
		

		
		casillasSimulacion.get(casillas.indexOf(CasillaRey)).setPiezaOculto(null);
		
		
		
		for(Casilla casilla : casillasDisp) {
			
			casillasSimulacion.get(casillas.indexOf(casilla)).setPieza(newPieza);
			Pieza prevPieza = casilla.getPieza();

			
			ArrayList<Casilla> casillasAmenaza = casillasJaque(casillasSimulacion);
			
			

			
			for (Casilla c:casillas) {
				c.setDebugClr(Color.blue);
			}
			for (Casilla c:casillasAmenaza) {
				casillas.get(casillasSimulacion.indexOf(c)).setDebugClr(Color.red);
			}
			
			
			
			casillasSimulacion.get(casillas.indexOf(casilla)).setPieza(prevPieza);
			if(casillasAmenaza.contains(casilla)) {
				
				casillasFuturasAmenaza.add(casilla);
			}
			
		}
		System.out.println(casillasFuturasAmenaza.size());
		return casillasFuturasAmenaza;
	}



	@Override
	public ArrayList<Casilla> getCasillasCome(Casilla curCasilla, ArrayList<Casilla> casillas) {
	    int casillaIndex = casillas.indexOf(curCasilla);
	    ArrayList<Casilla> casillasDisp = new ArrayList<>();
	    int fila = curCasilla.getFila();
	    char columna = curCasilla.getColumna();

	    // Posibles movimientos del rey: arriba, abajo, izquierda, derecha y diagonales
	    int[][] movimientos = {
	        {-1, -1}, {0, -1}, {1, -1},
	        {-1, 0}, /* Rey */ {1, 0},
	        {-1, 1}, {0, 1}, {1, 1}
	    };

	    for (int[] movimiento : movimientos) { // Recorre cada movimiento posible desde la posición del rey
	        int nuevaFila = fila + movimiento[0];
	        char nuevaColumna = (char) (columna + movimiento[1]);

	        // Verifica que la casilla resultante esté dentro del tablero (filas 0 a 7 y columnas A a H)
	        if (nuevaFila >= 0 && nuevaFila <= 7 && nuevaColumna >= 'A' && nuevaColumna <= 'H') {
	            Casilla casillaDisp = casillas.get(nuevaFila * 8 + (nuevaColumna - 'A'));

	            
	                casillasDisp.add(casillaDisp);
	            
	        }
	    }

	    return casillasDisp;
	}

	
	
}
