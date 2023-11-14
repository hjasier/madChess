package ventanas;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

import componentes.BButton;
import componentes.FontAwesome;
import componentes.IconFontSwing;
import componentes.RButton;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class MenuInicio extends JPanel {

	protected JPanel navBar;
	protected JPanel opciones;
	protected JPanel panelLogo = new JPanel();
	protected JPanel panelLogin = new JPanel();
	
	protected JLabel nombre = new JLabel("madChess");
	
	protected RButton loginBtn = new RButton("Login");
	protected BButton partidaLocal = new BButton("Partida Local");
	protected BButton crearPOnline = new BButton("Crear partida online");
	protected BButton joinPOnline = new BButton("Join partida online");
	
	
	protected ImageIcon imgFoto = new ImageIcon(getClass().getResource("../srcmedia/logo110x300.png"));
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

		nombre.setForeground(new Color(255, 248, 210));
		nombre.setFont(getFont());
		String nombreFont = "/srcmedia/unsh.ttf"; 
	
		

		
		try {
			Font f = Font.createFont( Font.TRUETYPE_FONT, MenuInicio.class.getResourceAsStream(nombreFont) );
			f = f.deriveFont( Font.PLAIN, 38 );
			nombre.setFont(f);
		} catch (FontFormatException | IOException e) {
			System.out.println("Error al cargar fuente");
		}
		
		
		
		
		navBar.setLayout(new BorderLayout());
		opciones.setLayout(new BoxLayout(opciones, BoxLayout.Y_AXIS));
		
		// Escalar la imagen
        Image imagenEscalada = imgFoto.getImage().getScaledInstance(300, 110, Image.SCALE_SMOOTH);
        imgFoto = new ImageIcon(imagenEscalada);
        foto.setIcon(imgFoto);

        panelLogo.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
		panelLogo.add(foto, BorderLayout.WEST);
		//panelLogo.add(nombre, BorderLayout.CENTER);

		panelLogin.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));
		panelLogin.add(Box.createRigidArea(new java.awt.Dimension(0, 80)));
		panelLogin.add(loginBtn);
        
		navBar.add(panelLogo, BorderLayout.WEST);
		navBar.add(panelLogin, BorderLayout.EAST);
        
		Icon icon = IconFontSwing.buildIcon(FontAwesome.USER_CIRCLE, 15);
		loginBtn.setIcon(icon);
		
		
		
		
		
		partidaLocal.setAlignmentX(Component.CENTER_ALIGNMENT);
		crearPOnline.setAlignmentX(Component.CENTER_ALIGNMENT);
		joinPOnline.setAlignmentX(Component.CENTER_ALIGNMENT);

		
		opciones.add(Box.createRigidArea(new java.awt.Dimension(0, 200))); //en el medio 250
		
		opciones.add(partidaLocal);
        opciones.add(Box.createRigidArea(new java.awt.Dimension(0, 10))); // Espacio entre los botones
        opciones.add(crearPOnline);
        opciones.add(Box.createRigidArea(new java.awt.Dimension(0, 10))); // Espacio entre los botones
        opciones.add(joinPOnline);
        


		this.add(navBar, BorderLayout.NORTH);	
		this.add(opciones, BorderLayout.CENTER);




	}


}
	
	



