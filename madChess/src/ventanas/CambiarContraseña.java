package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import componentes.BButton;
import componentes.BTextField;
import componentes.RButton;
import componentes.navBar;
import database.GestorDB;
import juego.Boosts;
import librerias.FontAwesome;
import librerias.IconFontSwing;
import objetos.Jugador;
import utils.Configuracion;
import utils.Escalador;
import utils.Infos;
import utils.Session;
import utils.utils;

public class CambiarContraseña extends JPanel {

	protected JPanel navBar;
	protected JPanel opciones;
	protected RButton volverBtn = new RButton("Volver");
	
	protected BTextField inputContra1 = new BTextField("Nueva Contraseña",true);
	protected BTextField inputContra2 = new BTextField("Repite la Contraseña",true);
	protected BButton cambiarbtn = new BButton("Cambiar Contraseña");
	protected JLabel labelTitulo = new JLabel("Selecciona una nueva contraseña");
	protected JLabel errorLabel = new JLabel();
	protected Paneles redirect = Paneles.PERFILUSUARIO;
	
	public CambiarContraseña () {
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

		

		inputContra1.setMaximumSize(new Dimension(Escalador.escalar(200), Escalador.escalar(40)));
		inputContra2.setMaximumSize(new Dimension(Escalador.escalar(200), Escalador.escalar(40)));
		cambiarbtn.setMaximumSize(new Dimension(Escalador.escalar(200), Escalador.escalar(40)));
		
		
		
		labelTitulo.setForeground(new Color(236,236,236));
		errorLabel.setForeground(new Color(250,42,42));

		labelTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
		inputContra1.setAlignmentX(Component.CENTER_ALIGNMENT);
		inputContra2.setAlignmentX(Component.CENTER_ALIGNMENT);
		cambiarbtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		errorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		errorLabel.setVisible(false);
		
		
		opciones.add(Box.createRigidArea(Escalador.newDimension(0, 100))); 
		
		
		opciones.add(labelTitulo);
        opciones.add(Box.createRigidArea(Escalador.newDimension(0, 40))); // Espacio entre los botones
        opciones.add(inputContra1);
        opciones.add(Box.createRigidArea(Escalador.newDimension(0,10))); // Espacio entre los botones
        opciones.add(inputContra2);
        opciones.add(Box.createRigidArea(Escalador.newDimension(0,20))); // Espacio entre los botones
        opciones.add(cambiarbtn);
        opciones.add(Box.createRigidArea(Escalador.newDimension(0, 20))); // Espacio entre los botones
        
        opciones.add(Box.createRigidArea(Escalador.newDimension(0, 20))); // Espacio entre los botones
        opciones.add(errorLabel);
        
		this.add(navBarContainer, BorderLayout.NORTH);	
		this.add(opciones, BorderLayout.CENTER);

		
		
		// Enter Keylistener
		inputContra2.addKeyListener(new KeyAdapter() {
		    @Override
		    public void keyPressed(KeyEvent e) {
		        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
		            // Simular el clic en el botón "Iniciar Sesión"
		        	cambiarbtn.doClick();
		        }
		    }
		});
		
		
		
		

		cambiarbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String username = Session.getCurrentUser().getUsername(); 
				String passw = inputContra2.getText(); 

				cambiarbtn.setEnabled(false);

				boolean cambiada = true;

				if (inputContra1.getText().equals(inputContra2.getText())) {
				    boolean modificarContraseña = false;
				
				    if (cambiada) {
				        errorLabel.setVisible(false);
				        modificarContraseña = GestorDB.modificarContraseña(username, passw);
				    } else {
				        errorLabel.setVisible(true);
				        errorLabel.setText(Infos.ContraseyaNocambiada);
				    }
				
				    if (modificarContraseña) {
				        VentanaPrincipal ventanaPrincip = Session.getVentana();
				        ventanaPrincip.getCardLayout().show(ventanaPrincip.getPanelPrincipal(), redirect);
				        resetToDefault();
				        utils.alert(Infos.ContraseyaCambiada, "", "ok");
				    } else {
				        cambiarbtn.setEnabled(true);
				    }
				} 
				else {
				    cambiarbtn.setEnabled(true);
				    errorLabel.setVisible(true);
				    errorLabel.setText(Infos.ContraseyaNoCoincide);

			}
			}
			
		});
		
		
		
		
		
		
		
		
	}
	
	
	
	
	
	private void resetToDefault() {
		redirect = Paneles.PERFILUSUARIO;
		inputContra1.setText("");
		inputContra2.setText("");
		errorLabel.setVisible(false);
		cambiarbtn.setEnabled(true);
	}
	
	




	
}
	
	



