package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.BoxLayout;
import javax.swing.*;

public class MenuInicio extends JPanel{

	protected JPanel navBar;
	protected JPanel opciones;
	protected JPanel panelLogo = new JPanel();
	protected JButton loginBtn = new JButton("Login");
	protected JLabel nombre = new JLabel("MadChess");
	protected JLabel foto = new JLabel("foto");
	protected JButton partidaLocal = new JButton("Partida Local");
	protected JButton crearPOnline = new JButton("Crear partida online");
	protected JButton joinPOnline = new JButton("Join partida online");
	
	protected Color colorTemp = new Color(16,16,16);
	
	public MenuInicio () {
		this.setLayout(new BorderLayout());
		navBar = new JPanel();
		opciones = new JPanel();
		
		
		navBar.setBackground(colorTemp);
		opciones.setBackground(colorTemp);
		panelLogo.setBackground(colorTemp);
		
		
		navBar.setLayout(new BorderLayout());
		opciones.setLayout(new BoxLayout(opciones, BoxLayout.Y_AXIS));
		
		
		panelLogo.add(foto, BorderLayout.WEST);
		panelLogo.add(nombre, BorderLayout.CENTER);
		
		
		navBar.add(panelLogo, BorderLayout.WEST);
		navBar.add(loginBtn, BorderLayout.EAST);
		
		
		partidaLocal.setAlignmentX(Component.CENTER_ALIGNMENT);
		crearPOnline.setAlignmentX(Component.CENTER_ALIGNMENT);
		joinPOnline.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		opciones.add(partidaLocal);
		opciones.add(crearPOnline);
		opciones.add(joinPOnline);
		
		
		
		this.add(navBar, BorderLayout.NORTH);	
		this.add(opciones, BorderLayout.CENTER);
		
		
		
		
	}
	
	
}


