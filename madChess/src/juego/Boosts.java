package juego;

import java.awt.event.MouseAdapter;
import java.awt.Color;
import java.awt.event.*;

import javax.swing.JOptionPane;

import objetos.Casilla;
import objetos.Tablero;

public class Boosts {
	
		protected static String curBoost;
	
		public static void checkBoosts(Casilla casilla) {
			
			if (curBoost == null) {return;}
			switch(curBoost) {
				case "HIELO":
					//casilla.setDebugClr(Color.blue);
					bloquearCasillasAdyacentes(casilla);
					curBoost = null;
					break;
					
			}
			
				
		}
		
		
		
	    public static void boostHielo() {
	        curBoost = "HIELO";
	    	JOptionPane.showMessageDialog(null, "Selecciona una casilla");
	    }
			
	    
	    private static void bloquearCasillasAdyacentes(Casilla casilla) {
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
	            	adyacente.setDebugClr(Color.blue);
	            }}
	        }
	
	
public static void boostMismoDestino () {
		
		System.out.println("Mismo Destino");
		
	}
	
	
	
	
}
