package ventanas;

import java.awt.BorderLayout;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import componentes.RButton;
import componentes.navBar;
import juego.Configuracion;
import juego.Session;
import librerias.FontAwesome;
import librerias.IconFontSwing;

public class Perfil extends JPanel{
	protected RButton volverBtn = new RButton("Volver");
	protected navBar navBar;
	
	public Perfil() {
		this.setLayout(new BorderLayout());
		
  		//--------------------- NAVBAR-------------------------------------------
		Icon icon = IconFontSwing.buildIcon(FontAwesome.BACKWARD, 15);
		
		volverBtn.setIcon(icon);
  		
  		navBar = new navBar(volverBtn);
  		//--------------------- NAVBAR-------------------------------------------	   


  		this.setBackground(Configuracion.BACKGROUND);
  		
  		this.add(navBar,BorderLayout.NORTH);
		this.add(new JLabel(Session.getCurrentUser().getNombre()),BorderLayout.CENTER);
	} 
}
