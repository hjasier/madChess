package utils;

import java.awt.Color;

public class Themes {

	public enum tableroThemes {
		MADCHESS, CLASSIC, WOOD, ALTER, CHECKERS
	}
	
	public enum piezasThemes {
		DF, P2, GLASS, P4, P5
	}
	
	

	public static Color[] getTableroTheme(tableroThemes theme) {
	    switch (theme) {
	        case MADCHESS:
	            return new Color[]{
	            		new Color(80, 150, 167, 255), 
	            		new Color(234, 236, 209, 255),
	            		
	            		new Color(80, 150, 167+30, 255),
	            		new Color(234, 236, 209+30, 255),
	            		};
	        case CLASSIC:
	            return new Color[]{
	            		new Color(77, 77, 77, 255), 
	            		new Color(236, 236, 236, 255),
	            		
	            		new Color(128, 140, 184, 255),
	            		new Color(202, 220, 255, 255),
	            		
	            		
	            		};
	        case WOOD:
	            return new Color[]{
	            		new Color(184, 135, 98, 255), 
	            		new Color(237, 215, 177, 255),
	            		
	            		new Color(128, 140, 184, 255),
	            		new Color(215, 219, 237, 255),
	            		
                		
	            		
	            		};
	        case ALTER:
	            return new Color[]{
	            		new Color(132, 119, 187, 255), 
	            		new Color(241, 241, 241, 255),
	            		
	            		new Color(152, 170, 232, 255),
	            		new Color(211, 223, 254, 255),
	            		

	            		};
	            
	        case CHECKERS:
	            return new Color[]{
	            		new Color(199, 76, 81, 255), 
	            		new Color(48, 49, 49, 255),
	            		
	            		new Color(199, 76, 81+30, 255),
	            		new Color(48, 49, 49+30, 255),
	            		
	            		
	            
	            		};
	        default:
	            return new Color[]{
	            		new Color(80, 150, 167, 255), 
	            		new Color(234, 236, 209, 255),
	            		
	            		new Color(80, 150, 167+30, 255),
	            		new Color(234, 236, 209+30, 255),
                		
	            		};
	    }
	}

	
	
	
	
	 
}
	