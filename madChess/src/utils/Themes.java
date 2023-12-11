package utils;

import java.awt.Color;

public class Themes {

	public enum tableroThemes {
		THEME1, THEME2, THEME3, THEME4, THEME5
	}
	
	public enum piezasThemes {
		THEME1, THEME2, THEME3, THEME4, THEME5
	}
	
	

	public static Color[] getTableroTheme(tableroThemes theme) {
	    switch (theme) {
	        case THEME1:
	            return new Color[]{new Color(80, 150, 167, 255), new Color(234, 236, 209, 255)};
	        case THEME2:
	            return new Color[]{new Color(77, 77, 77, 255), new Color(236, 236, 236, 255)};
	        case THEME3:
	            return new Color[]{new Color(184, 135, 98, 255), new Color(237, 215, 177, 255)};
	        case THEME4:
	            return new Color[]{new Color(132, 119, 187, 255), new Color(241, 241, 241, 255)};
	        case THEME5:
	            return new Color[]{new Color(199, 76, 81, 255), new Color(48, 49, 49, 255)};
	        default:
	            return new Color[]{new Color(80, 150, 167, 255), new Color(234, 236, 209, 255)};
	    }
	}

	
	
	
	
	 
}
	