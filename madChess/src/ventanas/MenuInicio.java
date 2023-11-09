package ventanas;

import java.awt.*;
import java.awt.Color;
import java.awt.Component;
import java.awt.Image;

import javax.swing.BoxLayout;

import componentes.BButton;
import componentes.RButton;
import javax.swing.*;

public class MenuInicio extends JPanel {

	protected JPanel navBar;
	protected JPanel opciones;
	protected JPanel panelLogo = new JPanel();
	protected JPanel panelLogin = new JPanel();
	protected RButton loginBtn = new RButton("Login");
	protected JLabel nombre = new JLabel("MadChess");
	
	protected BButton partidaLocal = new BButton("Partida Local");
	protected BButton crearPOnline = new BButton("Crear partida online");
	protected BButton joinPOnline = new BButton("Join partida online");
	protected ImageIcon imgFoto = new ImageIcon(getClass().getResource("../src_piezas/imagenMenuInicio.png"));
	protected JLabel foto = new JLabel(imgFoto);
	protected Color colorTemp = new Color(16,16,16);
	
	
	public MenuInicio () {
		this.setLayout(new BorderLayout());
		navBar = new JPanel();
		opciones = new JPanel();


		navBar.setBackground(colorTemp);
		opciones.setBackground(colorTemp);
		panelLogo.setBackground(colorTemp);
		panelLogin.setBackground(colorTemp);

		
		
		navBar.setLayout(new BorderLayout());
		opciones.setLayout(new BoxLayout(opciones, BoxLayout.Y_AXIS));
		
		// Escalar la imagen
        Image imagenEscalada = imgFoto.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        imgFoto = new ImageIcon(imagenEscalada);
        foto.setIcon(imgFoto);

		panelLogo.add(foto, BorderLayout.WEST);
		panelLogo.add(nombre, BorderLayout.CENTER);

		panelLogin.add(Box.createRigidArea(new java.awt.Dimension(0, 80)));
		panelLogin.add(loginBtn);
        
		navBar.add(panelLogo, BorderLayout.WEST);
		navBar.add(panelLogin, BorderLayout.EAST);

		
        
        
		
		partidaLocal.setAlignmentX(Component.CENTER_ALIGNMENT);
		crearPOnline.setAlignmentX(Component.CENTER_ALIGNMENT);
		joinPOnline.setAlignmentX(Component.CENTER_ALIGNMENT);

		
		opciones.add(Box.createRigidArea(new java.awt.Dimension(0, 200))); //en el medio 250
		
		opciones.add(partidaLocal);
        opciones.add(Box.createRigidArea(new java.awt.Dimension(0, 10))); // Agregar espacio entre los botones
        opciones.add(crearPOnline);
        opciones.add(Box.createRigidArea(new java.awt.Dimension(0, 10))); // Agregar espacio entre los botones
        opciones.add(joinPOnline);



		this.add(navBar, BorderLayout.NORTH);	
		this.add(opciones, BorderLayout.CENTER);




	}


}
	
	



