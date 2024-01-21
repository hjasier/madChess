package utils;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

public class Configuracion {
	
	public static final int DURACION_PARTIDA = 600;

	
	//CDN 
	public static final String UPLOAD_URL = "https://media.madchess.online/";
	public static final String UPLOAD_DIR = "media/";
	
	//SOCKETS
	
	public static final String VERSION = "V2.4"; 
	public static final int PUERTO = 6968; 
	
	public static final String HOST = "144.24.197.228"; 
	//public static final String HOST = "localhost"; 
	
	
	public static final boolean MLTPLY_PIEZA_DRAG = false;
	
	//STYLE
	
	public static final Color BACKGROUND = new Color(28,28,28);
	public static final Color HOVER = new Color(255,87,50);
	public static final Font DEFAULTFONT = new Font("Dialog", Font.PLAIN, 12);
	public static final ArrayList<Color> COLORESJUGADORES = new ArrayList<>(List.of(
			new Color(255,100,17),
			new Color(236,146,225),
			Color.magenta,
			Color.yellow
	    ));
	public static Font CUSTOMFONT;
	
	
	//MODOS DEBUG
	
	public static final boolean DEBUG_MODE = false;
	public static final boolean LOGIN_DEBUG = false;
	public static final boolean BOT_DEBUG = false;
	public static final boolean DB_DEBUG = false; // en true no se guarda nada en la DB
	

}
