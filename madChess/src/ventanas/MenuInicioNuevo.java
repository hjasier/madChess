package ventanas;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import componentes.RButton;
import componentes.navBar;
import librerias.FontAwesome;
import librerias.IconFontSwing;
import utils.Configuracion;
import utils.Escalador;

import java.awt.Color;
import java.awt.Image;
import java.awt.BorderLayout;

public class MenuInicioNuevo extends JPanel{
	RButton loginBtn = new RButton("Login");
	
	public MenuInicioNuevo() {
		setBackground(Configuracion.BACKGROUND);
		setLayout(new BorderLayout(0, 0));
		
		
		
		
  		//--------------------- NAVBAR-------------------------------------------
		
  		Icon icon = IconFontSwing.buildIcon(FontAwesome.USER_CIRCLE, Escalador.escalarF(15));
		
		loginBtn.setIcon(icon);
  		
  		navBar navBar = new navBar(loginBtn);
  		
  		this.add(navBar,BorderLayout.NORTH);
	  	//--------------------- NAVBAR-------------------------------------------	   
	  		
  		
  		
  		
  	
  		
  		
  		JPanel panel = new JPanel();
  		add(panel, BorderLayout.CENTER);
  		panel.setLayout(null);
  		
  		
  		
  		ImageIcon singleplayerft = new ImageIcon(getClass().getResource("../srcmedia/singleplayer.png"));
  		Image imagenEscalada = singleplayerft.getImage().getScaledInstance(Escalador.escalar(398), Escalador.escalar(309), Image.SCALE_SMOOTH);
  		
  		
  		JLabel singleplayerBack = new JLabel();
  		singleplayerBack.setIcon(new ImageIcon(imagenEscalada));
  		
  		
  		singleplayerBack.setBounds(219, 51, Escalador.escalar(398), Escalador.escalar(309));
  		singleplayerBack.setBackground(new Color(255, 0, 0));
  		
  	
        panel.add(singleplayerBack);
      		
  		
      		
	}
	
	

}
