package objetos;

import java.io.Serializable;

public class Movimiento implements Serializable{
	
	protected Casilla casillaSalida;
	protected Casilla casillaLlegada;
	protected Pieza pieza;
	
	
	public Casilla getCasillaSalida() {
		return casillaSalida;
	}
	public void setCasillaSalida(Casilla casillaSalida) {
		this.casillaSalida = casillaSalida;
	}
	public Casilla getCasillaLlegada() {
		return casillaLlegada;
	}
	public void setCasillaLlegada(Casilla casillaLlegada) {
		this.casillaLlegada = casillaLlegada;
	}
	public Pieza getPieza() {
		return pieza;
	}
	public void setPieza(Pieza pieza) {
		this.pieza = pieza;
	}
	
	@Override
	public String toString() {
		return "Movimiento [casillaSalida=" + casillaSalida + ", casillaLlegada=" + casillaLlegada + ", pieza=" + pieza
				+ "]";
	}
	
	//Para las analiticas podríamos usar esta clase movimiento para tenerlo mas ordenado
	// Y para el online podemos enviar un Objeto tipo Movimiento serializado
	
}
