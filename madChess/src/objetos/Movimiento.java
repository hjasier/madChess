package objetos;

import java.io.Serializable;

public class Movimiento implements Serializable{
	
	protected Jugador movJugador;
	protected Casilla casillaSalida;
	protected Casilla casillaLlegada;
	protected Pieza pieza;
	protected Pieza piezaComida;
	protected String infoExtraMovimiento;
	
	public Movimiento(Casilla prevCasilla, Casilla curCasilla, Pieza piezaComida, Pieza piezaMovida) {
		this.casillaSalida = prevCasilla;
		this.casillaLlegada = curCasilla;
		this.pieza = piezaMovida;
		this.piezaComida = piezaComida;
	}

	public Movimiento() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Movimiento [casillaSalida=" + casillaSalida + ", casillaLlegada=" + casillaLlegada + ", pieza=" + pieza
				+ ", piezaComida=" + piezaComida + "]";
	}
	
	
	public Casilla getCasillaSalida() {
		return casillaSalida;
	}

	public Casilla getCasillaLlegada() {
		return casillaLlegada;
	}
	
	
}
