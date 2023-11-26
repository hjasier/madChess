package ventanas;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

import componentes.BButton;
import componentes.RButton;
import componentes.navBar;
import juego.Configuracion;
import juego.Escalador;
import librerias.FontAwesome;
import librerias.IconFontSwing;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class MenuInicio extends JPanel {

	protected JPanel navBar;
	protected JPanel opciones = new JPanel();;	

	protected RButton loginBtn = new RButton("Login");
	protected BButton partidaLocal = new BButton("Partida Local");
	protected BButton crearPOnline = new BButton("Crear partida online");
	protected BButton joinPOnline = new BButton("Join partida online");
	


	
	public MenuInicio () {
		this.setLayout(new BorderLayout());
		
		
		
  		//--------------------- NAVBAR-------------------------------------------
  		Icon icon = IconFontSwing.buildIcon(FontAwesome.USER_CIRCLE, Escalador.escalarF(15));
		loginBtn.setIcon(icon);
  		
  		navBar = new navBar(loginBtn);
  		//--------------------- NAVBAR-------------------------------------------	   
      		
      		

		opciones.setBackground(Configuracion.BACKGROUND);
		opciones.setLayout(new BoxLayout(opciones, BoxLayout.Y_AXIS));
		

		
		
		partidaLocal.setAlignmentX(Component.CENTER_ALIGNMENT);
		crearPOnline.setAlignmentX(Component.CENTER_ALIGNMENT);
		joinPOnline.setAlignmentX(Component.CENTER_ALIGNMENT);

		
		opciones.add(Box.createRigidArea(new java.awt.Dimension(0, Escalador.escalar(150)))); //en el medio 250
		
		opciones.add(partidaLocal);
        opciones.add(Box.createRigidArea(new java.awt.Dimension(0, Escalador.escalar(10)))); // Espacio entre los botones
        opciones.add(crearPOnline);
        opciones.add(Box.createRigidArea(new java.awt.Dimension(0, Escalador.escalar(10)))); // Espacio entre los botones
        opciones.add(joinPOnline);
        


		this.add(navBar, BorderLayout.NORTH);	
		this.add(opciones, BorderLayout.CENTER);




	}




	public void setLoged(boolean b) {
		if (b) {
			loginBtn.setText("Perfil");
		}
		else {
			loginBtn.setText("Login");
		}
		
		
		
		
	}


}
	
	



