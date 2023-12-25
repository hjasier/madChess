package juego;

import java.util.ArrayList;
import java.util.HashMap;

import objetos.Boost;
import objetos.Casilla;
import objetos.Jugador;
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
import utils.utils;
import ventanas.Juego;

public class VAR {
	
	private static DatosPartida datosPartida;
	private static Tablero tablero = Session.getVentana().getPanelJuego().getTablero();
	private static int curMov;
	private static HashMap<Movimiento,Pieza> piezasComidas = new HashMap<Movimiento,Pieza>();
	
	public static void avanzarMov() {
		if (curMov == datosPartida.getMovimientos().size()-1) {
			utils.alert("Fin de la partida", "No hay más movimientos");
			return;
		}
		
		Movimiento newMov = datosPartida.getMovimientos().get(curMov+1);
		Casilla casillaOrigen = tablero.getCasilla(newMov.getCasillaSalida().getFila(),newMov.getCasillaSalida().getColumna());
		Casilla casillaDestino = tablero.getCasilla(newMov.getCasillaLlegada().getFila(),newMov.getCasillaLlegada().getColumna());
		
		Pieza piezaMovida = casillaOrigen.getPieza();
		piezasComidas.put(newMov, casillaDestino.getPieza());
		
		casillaOrigen.setPieza(null);
		casillaDestino.setPieza(piezaMovida);
		actualizarDatosVentana(newMov);
		actualizarBoostsActivos(newMov);
		curMov++;
		
	}



	public static void retrodecerMov() {
		if (curMov == -1) {
			utils.alert("Inicio de la partida", "No hay más movimientos");
			return;
		}
		
		Movimiento newMov = datosPartida.getMovimientos().get(curMov);
		Casilla casillaOrigen = tablero.getCasilla(newMov.getCasillaSalida().getFila(),newMov.getCasillaSalida().getColumna());
		Casilla casillaDestino = tablero.getCasilla(newMov.getCasillaLlegada().getFila(),newMov.getCasillaLlegada().getColumna());
		
		
		Pieza piezaMovida = casillaDestino.getPieza();
		Pieza piezaComida = piezasComidas.get(newMov);
		
		casillaOrigen.setPieza(piezaMovida);
		casillaDestino.setPieza(piezaComida);
		actualizarDatosVentana(newMov);
		actualizarBoostsActivos(newMov);
		curMov--;
		
	}
	
	
	
	
	public static void runInitialConfig() {
		curMov = -1;
		tablero.cargarPiezas(datosPartida.getJugadores());
		setearDatosVentana();
		Session.getVentana().getPanelJuego().modoTempPonerbtns();
		Session.getVentana().getPanelJuego().getPanelVAR().loadMovs(datosPartida.getMovimientos());
	}
	
	
	private static void actualizarDatosVentana(Movimiento newMov) {
		int i = 0;
		for (Jugador jugador : datosPartida.getJugadores()) {
			 Jugador jugadorMov = newMov.getJugador();
			if (jugador.getUsuario().getUsername()==jugadorMov.getUsuario().getUsername()) {
				if (i == 0) {
					Session.getVentana().getPanelJuego().getPanelUsuario().setTemp(jugadorMov.getTiempoRestante());
				} else {
					Session.getVentana().getPanelJuego().getPanelUsuario2().setTemp(jugadorMov.getTiempoRestante());
				}
			}
			i++;
		}
	}
	

	private static void actualizarBoostsActivos(Movimiento movimiento) {
		if (datosPartida.getTipoPartida() == partidaTipo.MADCHESS) {
			eliminarBoosts();
			ArrayList<Boost> boosts = movimiento.getBoostActivos();
			System.out.println("Boosts activos: "+boosts);
			for (Boost boost : boosts) {
				if (boost.getCont() >= 0) {
					boost.config();
					boost.check();
				}
			}
		}
		
	}
	
	

	private static void eliminarBoosts() {
		for (Casilla casilla : tablero.getCasillas()) {
			casilla.setIsHielo(false);
			Pieza pieza = casilla.getPieza();
			if (pieza != null) {
				pieza.setIsBomberman(false);
			}
			
			
		}
		
	}





	private static void setearDatosVentana() {
		Juego ventana = Session.getVentana().getPanelJuego();
		ventana.getPanelUsuario().setUsuario(datosPartida.getJugadores().get(0));
		ventana.getPanelUsuario2().setUsuario(datosPartida.getJugadores().get(1));
		
		ventana.getPanelUsuario().setTemp(datosPartida.getDuracionEstablecida());
		ventana.getPanelUsuario2().setTemp(datosPartida.getDuracionEstablecida());
	}

	public DatosPartida getDatosPartida() {
		return datosPartida;
	}

	public static void setDatosPartida(DatosPartida datos) {
		datosPartida = datos;
		runInitialConfig();
		
		
	}
	


	
	
	
	public static void irAMovimiento(int indx) {
		if (indx > curMov) {
			for (int i = curMov; i < indx; i++) {
				avanzarMov();
			} 
        }
		else if (indx < curMov) {
			for (int i = curMov; i > indx; i--) {
				retrodecerMov();
			}
		}
		
	}
	

}
