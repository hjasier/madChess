package piezas;

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
        
        //Jaque:
        //1: Recorrer los movimientos disponibles de todas las piezas, y quitarlos de los movimientos posibles del rey si coinciden. (Hecho)
        //2: si despues de que mueva el enemigo esta en una casilla disponible, tiene que o moverse, pieza que pueda denegar 
        //la casilla disponible en la que está el rey, acaba la partida
        
        
        //Jaque Mate:
        //Si el rey está amenazado, no tiene ninguna casilla a la que moverse y no hay ninguna pieza que pueda 
        //denegar la casilla disponible en la que está el rey, acaba la partida
        ArrayList<Casilla> casillasAmenaza = casillasReyAmenazado(curCasilla, casillas);
        casillasDisp.removeAll(casillasAmenaza);
        
        
        ArrayList<Casilla> casillasFuturaAmenaza = casillasFuturaAmenaza(curCasilla, casillasDisp);
        //casillasDisp.removeAll(casillasFuturaAmenaza);
        
        return casillasDisp;
    }
	
	
	public ArrayList<Casilla> casillasReyAmenazado(Casilla CasillaRey,ArrayList<Casilla> casillas) {
		ArrayList<Casilla> casillasAmenaza = new ArrayList<>();
		for(Casilla casilla : casillas) {
			
			Pieza piezaCasilla = casilla.getPieza();
			if(piezaCasilla != null && piezaCasilla.getIsWhite() != this.getIsWhite() && !(piezaCasilla instanceof Rey)) {
				ArrayList<Casilla> casillasDisp = piezaCasilla.getCasillasDisponibles(casilla, casillas);
				casillasAmenaza.addAll(casillasDisp);
			}
		}
		return casillasAmenaza;
	}
	
	
	
	public ArrayList<Casilla> casillasFuturaAmenaza(Casilla CasillaRey,ArrayList<Casilla> casillas){
		ArrayList<Casilla> casillasFuturasAmenaza = new ArrayList<>();
		Pieza newPieza = CasillaRey.getPieza();
		CasillaRey.setPiezaOculto(null);
		for(Casilla casilla : casillas) {
			Pieza prevPieza = casilla.getPieza();
			casilla.setPiezaOculto(newPieza);
			
			ArrayList<Casilla> casillasAmenaza = casillasReyAmenazado(casilla, casillas);
			casilla.setPiezaOculto(prevPieza);
			if(casillasAmenaza.contains(casilla)) {
				casillasFuturasAmenaza.add(casilla);
			}
			
		}
		CasillaRey.setPiezaOculto(newPieza);
		return casillasFuturasAmenaza;
	}
	
	
}
