package objetos;

public class Jugador {
	
	private String nombre;
	private int rank;
	private String passw;
	private String img_route;
	
	
	public Jugador(String nombre, int rank, String passw) {
		super();
		this.nombre = nombre;
		this.rank = rank;
		this.passw = passw;
	}
	
	
	private Boolean checkPassword(String passwd) {
		return (this.passw == passwd);
	}
	
	
	
	
	

}
