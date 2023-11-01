package objetos;

public class Jugador {
	
	private String nombre;
	private int rank;
	private String passw;
	private String img_route;
	private Boolean isWhite = false;
	
	public Jugador(String nombre) {
		super();
		this.nombre = nombre;
		
	}
	
	
	
	public Boolean getIsWhite() {
		return isWhite;
	}

	public void setIsWhite(Boolean isWhite) {
		this.isWhite = isWhite;
	}



	private Boolean checkPassword(String passwd) {
		return (this.passw == passwd);
	}



	public String getNombre() {
		return nombre;
	}
	
	
	
	
	

}
