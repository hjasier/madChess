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

        // Posibles movimientos de la torre: arriba, abajo, izquierda y derecha
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
        
        
        return casillasDisp;
    }
}
