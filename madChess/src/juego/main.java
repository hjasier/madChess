package juego;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Toolkit;
import java.io.IOException;

import javax.swing.UIManager;

import utils.Audio;
import utils.Configuracion;
import utils.Escalador;
import ventanas.VentanaPrincipal;

public class main {

	public static void main(String[] args) {
		loadScreenConf();
		setDefaultFont();
		VentanaPrincipal v1 = new VentanaPrincipal();
		
		Audio.play("notify.wav"); //temp
	}
	


	private static void setDefaultFont() {
        try {
            String nombreFont = "/srcmedia/unsr.ttf";
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, main.class.getResourceAsStream(nombreFont));
            customFont = customFont.deriveFont(Font.PLAIN, Escalador.escalarF(16));

            Configuracion.CUSTOMFONT = customFont;
            // Configurar la fuente por defecto
            UIManager.put("Label.font", customFont);
            UIManager.put("Label.foreground", new Color(210,210,210));
            
            
            customFont = customFont.deriveFont(Font.BOLD, Escalador.escalarF(15));
            UIManager.put("Button.font", customFont);
            
            UIManager.put("ComboBox.font", customFont);
            
            
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }
    
    private static void loadScreenConf() {
    	System.setProperty("sun.java2d.uiScale", "1");
		Escalador.setScreenSize();
	}


}
