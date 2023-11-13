package objetos;

import java.io.Serializable;

public class Movimiento implements Serializable{
	
	protected Casilla casillaSalida;
	protected Casilla casillaLlegada;
	protected Pieza pieza;
	protected Pieza piezaComida;
	
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
	
	
	
	//Para las analiticas podríamos usar esta clase movimiento para tenerlo mas ordenado
	// Y para el online podemos enviar un Objeto tipo Movimiento serializado
	
}
