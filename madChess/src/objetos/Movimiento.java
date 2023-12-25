package objetos;

import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;

import juego.Boosts;


public class Movimiento implements Serializable{
	
	protected Jugador jugador;
	protected Casilla casillaSalida;
	protected Casilla casillaLlegada;
	protected Pieza pieza;
	protected Pieza piezaComida;
	private Date initTurno;
	private Date endTurno;
	private ArrayList<Boost> boostActivos = new ArrayList<>();
	
	
	
	
	
	public Movimiento(Jugador jugador, Casilla casillaSalida, Casilla casillaLlegada, Pieza pieza, Pieza piezaComida) {
		super();
		this.jugador = jugador;
		this.casillaSalida = casillaSalida;
		this.casillaLlegada = casillaLlegada;
		this.pieza = pieza;
		this.piezaComida = piezaComida;
		this.initTurno = jugador.getInitTime();
		this.endTurno = new Date();
		this.boostActivos = crearDuplicadoBoosts();
	}
	
	
	
	private ArrayList<Boost> crearDuplicadoBoosts() {
		return new ArrayList<>(Boosts.getBoostActivos());
	}



	public Jugador getJugador() {
		return jugador;
	}
	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}
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
	public Pieza getPiezaComida() {
		return piezaComida;
	}
	public void setPiezaComida(Pieza piezaComida) {
		this.piezaComida = piezaComida;
	}
	public Date getInitTurno() {
		return initTurno;
	}
	public void setInitTurno(Date initTurno) {
		this.initTurno = initTurno;
	}
	public Date getEndTurno() {
		return endTurno;
	}
	public void setEndTurno(Date endTurno) {
		this.endTurno = endTurno;
	}



	public ArrayList<Boost> getBoostActivos() {
		return boostActivos;
	}



	public void setBoostActivos(ArrayList<Boost> boostActivos) {
		this.boostActivos = boostActivos;
	}
	
	
	
	

	
	
	
}
