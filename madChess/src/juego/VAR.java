package juego;

import java.util.ArrayList;

import objetos.Casilla;
import objetos.Movimiento;
import objetos.Pieza;
import objetos.Tablero;
import piezas.Alfil;
import piezas.Caballo;
import piezas.Peon;
import piezas.Reina;
import piezas.Rey;
import piezas.Torre;
import utils.Session;

public class VAR {
	
	private static DatosPartida datosPartida;
	private static Tablero tablero = Session.getVentana().getPanelJuego().getTablero();
	private static int curMov;
	
	
	public static void avanzarMov() {
		Movimiento newMov = datosPartida.getMovimientos().get(curMov+1);
		Casilla casillaOrigen = tablero.getCasilla(newMov.getCasillaSalida().getFila(),newMov.getCasillaSalida().getColumna());
		Casilla casillaDestino = tablero.getCasilla(newMov.getCasillaLlegada().getFila(),newMov.getCasillaLlegada().getColumna());
		
		Pieza piezaMovida = casillaOrigen.getPieza();
		Pieza piezaComida = casillaDestino.getPieza();// de alguna manera hay que guardarla para luego poder volverla a poner al retroceder
		
		casillaOrigen.setPieza(null);
		casillaDestino.setPieza(piezaMovida);
		
		curMov++;
		
	}

	public static void retrodecerMov() {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	public static void runInitialConfig() {
		curMov = 0;
		regenerarTablero();
		Session.getVentana().getPanelJuego().modoTempPonerbtns();
	}
	
	public static void regenerarTablero() { //esto es temp , luego ya se verá como se cambia
		ArrayList<Casilla> casillas = tablero.getCasillas();
		Rey reyBlack = new Rey(false,false);
		Rey reyWhite = new Rey(true,false);
		
		casillas.get(0).setPieza(new Torre(false,false));
		casillas.get(1).setPieza(new Caballo(false,false));
		casillas.get(2).setPieza(new Alfil(false,false));
		casillas.get(3).setPieza(new Reina(false,false));
		casillas.get(4).setPieza(reyBlack);
		casillas.get(5).setPieza(new Alfil(false,false));
		casillas.get(6).setPieza(new Caballo(false,false));
		casillas.get(7).setPieza(new Torre(false,false));

		for (int i = 8; i <= 15; i++) {
			casillas.get(i).setPieza(new Peon(false,false));
		}

		for (int i = 48; i <= 55; i++) {
			casillas.get(i).setPieza(new Peon(true,false)); 
		}
		
		casillas.get(56).setPieza(new Torre(true,false));
		casillas.get(57).setPieza(new Caballo(true,false));
		casillas.get(58).setPieza(new Alfil(true,false));
		casillas.get(59).setPieza(new Reina(true, false));
		casillas.get(60).setPieza(reyWhite);
		casillas.get(61).setPieza(new Alfil(true,false));
		casillas.get(62).setPieza(new Caballo(true,false));
		casillas.get(63).setPieza(new Torre(true,false));
    }
	
	
	
	

	public DatosPartida getDatosPartida() {
		return datosPartida;
	}

	public static void setDatosPartida(DatosPartida datos) {
		datosPartida = datos;
		runInitialConfig();
		
		
	}
	
	

}
