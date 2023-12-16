package objetos;

import utils.Themes.piezasThemes;
import utils.Themes.tableroThemes;

public class Usuario {

	
	private String username;
	private String img_route;
	
	private int rank_madChess;
	private int rank_classic;
	
	private tableroThemes preferedTheme;
	private piezasThemes preferedPiezaTheme;
	
	public Usuario(String username, String img_route, int rank_madChess, int rank_classic, String preferedTheme,String preferedPiezaTheme) {
		super();
		this.username = username;
		this.img_route = img_route;
		this.rank_madChess = rank_madChess;
		this.rank_classic = rank_classic;
		this.preferedTheme = tableroThemes.valueOf(preferedTheme);
		this.preferedPiezaTheme = piezasThemes.valueOf(preferedPiezaTheme);
	}

	
	
	public Usuario(String userdebugname) {
		this.username = userdebugname;
		this.img_route = "https://media.madchess.online/media/test.png";
		this.rank_madChess = 0;
		this.rank_classic = 0;
		this.preferedTheme = tableroThemes.CLASSIC;
		this.preferedPiezaTheme = piezasThemes.DF;
		
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

	public int getRank_classic() {
		return rank_classic;
	}

	public void setRank_classic(int rank_clasic) {
		this.rank_classic = rank_clasic;
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
