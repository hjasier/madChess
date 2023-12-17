package ventanas;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Random;

import objetos.Jugador;
import utils.Configuracion;
import utils.Escalador;
import utils.Session;
import componentes.*;
import database.GestorDB;
import librerias.FontAwesome;
import librerias.IconFontSwing;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Login extends JPanel {

	protected JPanel navBar;
	protected JPanel opciones;
	protected RButton volverBtn = new RButton("Volver");
	
	protected BTextField inputNombre = new BTextField("Nombre de usuario",false);
	protected BTextField inputContra = new BTextField("Contraseña",true);
	protected BButton loginBtn = new BButton("Iniciar Sesión");
	protected JLabel registerLabel = new JLabel("¿No tienes cuenta? Registrate aquí");
	protected JLabel labelTitulo = new JLabel("Inicia sesión para continuar");
	protected JLabel errorLabel = new JLabel("Contraseña o usuario incorrectos");

	protected String redirect = "MENUINICIO";
	
	public Login (VentanaPrincipal ventanaPrincipal) {
		this.setLayout(new BorderLayout());

		
		opciones = new JPanel();

		
		opciones.setBackground(Configuracion.BACKGROUND);
		opciones.setLayout(new BoxLayout(opciones, BoxLayout.Y_AXIS));
		
		
        
		
		
		//--------------------- NAVBAR-------------------------------------------
  		Icon icon = IconFontSwing.buildIcon(FontAwesome.BACKWARD, Escalador.escalarF(15));
  		volverBtn.setIcon(icon);
  		
		JPanel navBarContainer = new JPanel(new FlowLayout());
		navBarContainer.add(new navBar(volverBtn));
        navBarContainer.setBackground(Configuracion.BACKGROUND);
      
  		
  		//--------------------- NAVBAR-------------------------------------------	   
      		

		
		
		opciones.setLayout(new BoxLayout(opciones, BoxLayout.Y_AXIS));

		
		inputNombre.setMaximumSize(new Dimension(Escalador.escalar(200), Escalador.escalar(40)));
		inputContra.setMaximumSize(new Dimension(Escalador.escalar(200), Escalador.escalar(40)));
		loginBtn.setMaximumSize(new Dimension(Escalador.escalar(200), Escalador.escalar(40)));
		
		
		registerLabel.setForeground(new Color(213,213,213));
		labelTitulo.setForeground(new Color(236,236,236));
		errorLabel.setForeground(new Color(250,42,42));

		registerLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));		
		
		labelTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
		inputNombre.setAlignmentX(Component.CENTER_ALIGNMENT);
		inputContra.setAlignmentX(Component.CENTER_ALIGNMENT);
		loginBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		registerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		errorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		errorLabel.setVisible(false);
		
		
		opciones.add(Box.createRigidArea(Escalador.newDimension(0, 100))); 
		
		
		opciones.add(labelTitulo);
        opciones.add(Box.createRigidArea(Escalador.newDimension(0, 40))); // Espacio entre los botones
		opciones.add(inputNombre);
        opciones.add(Box.createRigidArea(Escalador.newDimension(0,10))); // Espacio entre los botones
        opciones.add(inputContra);
        opciones.add(Box.createRigidArea(Escalador.newDimension(0,20))); // Espacio entre los botones
        opciones.add(loginBtn);
        opciones.add(Box.createRigidArea(Escalador.newDimension(0, 20))); // Espacio entre los botones
        opciones.add(registerLabel);
        opciones.add(Box.createRigidArea(Escalador.newDimension(0, 20))); // Espacio entre los botones
        opciones.add(errorLabel);

		this.add(navBarContainer, BorderLayout.NORTH);	
		this.add(opciones, BorderLayout.CENTER);

		
		
		// Enter Keylistener
		inputContra.addKeyListener(new KeyAdapter() {
		    @Override
		    public void keyPressed(KeyEvent e) {
		        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
		            // Simular el clic en el botón "Iniciar Sesión"
		            loginBtn.doClick();
		        }
		    }
		});
		
		registerLabel.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				VentanaPrincipal ventanaPrincip = Session.getVentana();
				ventanaPrincip.getCardLayout().show(ventanaPrincip.getPanelPrincipal(), "REGISTER");
			}
		});
		

		loginBtn.addActionListener(new ActionListener() {
			
			
			
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String username = inputNombre.getText();
				String passw = inputContra.getText(); //FIXME : las passwords no se gettean así

				
				loginBtn.setEnabled(false);
				boolean loginSucces = GestorDB.iniciarSesion(username, passw);
				if (loginSucces) {
					VentanaPrincipal ventanaPrincip = Session.getVentana();
					ventanaPrincip.getCardLayout().show(ventanaPrincip.getPanelPrincipal(), redirect);
					resetToDefault();
				}
				else {
					loginBtn.setEnabled(true);
					errorLabel.setVisible(true);
				}
				
				
			
			}
		});
		
		
		
		
		
		
		
		
	}
	
	
	
	
	
	private void resetToDefault() {
		redirect = "MENUINICIO";
		inputNombre.setText("");
		inputContra.setText("");
		errorLabel.setVisible(false);
		loginBtn.setEnabled(true);
	}
	
	



	public void setRedirect(String string) {
		this.redirect = string;
	}


	public String getRedirect() {
		return redirect;
	}

	
	
}
	
	



