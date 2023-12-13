package objetos;

import utils.Themes.piezasThemes;
import utils.Themes.tableroThemes;

public class Usuario {

	
	private String username;
	private String img_route;
	
	private int rank_madChess;
	private int rank_clasic;
	
	private tableroThemes preferedTheme;
	private piezasThemes preferedPiezaTheme;
	
	public Usuario(String username, String img_route, int rank_madChess, int rank_clasic, String preferedTheme,String preferedPiezaTheme) {
		super();
		this.username = username;
		this.img_route = img_route;
		this.rank_madChess = rank_madChess;
		this.rank_clasic = rank_clasic;
		this.preferedTheme = tableroThemes.valueOf(preferedTheme);
		this.preferedPiezaTheme = piezasThemes.valueOf(preferedPiezaTheme);
	}

	
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getImg_route() {
		return img_route;
	}

	public void setImg_route(String img_route) {
		this.img_route = img_route;
	}

	public int getRank_madChess() {
		return rank_madChess;
	}

	public void setRank_madChess(int rank_madChess) {
		this.rank_madChess = rank_madChess;
	}

	public int getRank_clasic() {
		return rank_clasic;
	}

	public void setRank_clasic(int rank_clasic) {
		this.rank_clasic = rank_clasic;
	}

	public tableroThemes getPreferedTheme() {
		return preferedTheme;
	}

	public void setPreferedTheme(tableroThemes preferedTheme) {
		this.preferedTheme = preferedTheme;
	}

	public piezasThemes getPreferedPiezaTheme() {
		return preferedPiezaTheme;
	}

	public void setPreferedPiezaTheme(piezasThemes preferedPiezaTheme) {
		this.preferedPiezaTheme = preferedPiezaTheme;
	}
	
	
	
}
