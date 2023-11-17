package piezas;

import java.awt.Color;
import java.lang.reflect.Array;
import java.util.ArrayList;

import objetos.Casilla;
import objetos.Pieza;

public class Rey extends Pieza implements PiezaMustHave{
	protected Boolean isAmenezado = false;
	
	public Rey(Boolean isWhite) {
		super("k",isWhite);
	}

	
	
	public Boolean getIsAmenezado() {
		return isAmenezado;
	}



	public void setIsAmenezado(Boolean isAmenezado) {
		this.isAmenezado = isAmenezado;
	}



	@Override
    public ArrayList<Casilla> getCasillasDisponibles(Casilla curCasilla, ArrayList<Casilla> casillas) {
        int casillaIndex = casillas.indexOf(curCasilla);
        int fila = curCasilla.getFila();
        char columna = curCasilla.getColumna();

        ArrayList<Casilla> casillasDisp = casillasDisp(fila,columna,casillas);

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
        
        
        if(casillasAmenaza.contains(curCasilla) && casillasDisp.size() == 0) {
        	System.out.println("Jaque Mate");
        	curCasilla.setDebugClr(Color.red);
        }
        
        return casillasDisp;
        
        
    }
	
	
	private ArrayList<Casilla> casillasDisp(int fila, char columna, ArrayList<Casilla> casillas) {
		ArrayList<Casilla> casillasDisp = new ArrayList<>();
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
	
	//metodo temporal pk volver a repetir esto es marronero
	public ArrayList<Pieza> piezasAmenaza(Casilla curCasilla,ArrayList<Casilla> casillas) {
		ArrayList<Casilla> casillasDisp = casillasDisp(curCasilla.getFila(),curCasilla.getColumna(),casillas);
		ArrayList<Casilla> casillasAmenazadasTotales = casillasJaque(casillas);
		
		ArrayList<Casilla> casillasAmenazadasRey = new ArrayList<>(casillasDisp);
		casillasAmenazadasRey.retainAll(casillasAmenazadasTotales);
		
		
		
		ArrayList<Pieza> piezasAmenaza = new ArrayList<>();
		for(Casilla casilla : casillas) {
			
			Pieza piezaCasilla = casilla.getPieza();
			if(piezaCasilla != null && piezaCasilla.getIsWhite() != this.getIsWhite() ) {
				
				
					ArrayList<Casilla> casillasCome = piezaCasilla.getCasillasCome(casilla, casillas);
					
					if (checkContains(casillasAmenazadasRey,casillasCome)) {//Si algun elemento de piezaCome esta en casillasdisp
						piezasAmenaza.add(piezaCasilla);
						System.out.println(casilla.getColumna() + " "+ casilla.getFila());
					}
					
				}
			}
		return piezasAmenaza;
	}
	
	private boolean checkContains(ArrayList<Casilla> c1, ArrayList<Casilla> c2) {
		for (Casilla casilla:c2) {
			if (c1.contains(casilla)){return true;};
		}
		return false;
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



	public boolean reCheckJaqueStatus(Casilla curReyCasilla, ArrayList<Casilla> casillas) {
		ArrayList<Casilla> casillasAmenaza = casillasJaque(casillas);
		isAmenezado = casillasAmenaza.contains(curReyCasilla);
//		String color = getIsWhite()? "blanco":"negro";
//       	System.out.println("Rey "+color+" amenzadado: "+isAmenezado);
       	return this.isAmenezado;
	}





	
	
}
