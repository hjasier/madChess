package ventanas;

import java.awt.BorderLayout;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import componentes.RButton;
import componentes.navBar;
import librerias.FontAwesome;
import librerias.IconFontSwing;
import utils.Configuracion;
import utils.Session;

public class Perfil extends JPanel{
	protected RButton backBtn = new RButton("Volver");
	protected navBar navBar;
	protected JLabel nombre = new JLabel();
	
	public Perfil() {
		this.setLayout(new BorderLayout());
		
  		//--------------------- NAVBAR-------------------------------------------
		Icon icon = IconFontSwing.buildIcon(FontAwesome.BACKWARD, 15);
		
		backBtn.setIcon(icon);
  		
  		navBar = new navBar(backBtn);
  		//--------------------- NAVBAR-------------------------------------------	   


  		this.setBackground(Configuracion.BACKGROUND);
  		
  		this.add(navBar,BorderLayout.NORTH);
  		
  		
  		
  		this.add(nombre,BorderLayout.CENTER);
  		
	}

	public void reloadData() {
		nombre.setText(Session.getCurrentUser().getNombre());
	} 
}
