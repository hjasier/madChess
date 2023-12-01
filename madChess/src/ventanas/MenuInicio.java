package ventanas;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import componentes.BButton;
import componentes.RButton;
import componentes.navBar;
import juego.DatosPartida;
import librerias.FontAwesome;
import librerias.IconFontSwing;
import utils.Configuracion;
import utils.Escalador;
import utils.Session;

import javax.swing.*;


public class MenuInicio extends JPanel {

	protected JPanel navBar;
	protected JPanel opciones = new JPanel();;	

	protected RButton loginBtn = new RButton("Login");
	protected BButton partidaLocal = new BButton("Partida Local");
	protected BButton crearPOnline = new BButton("Crear partida online");
	protected BButton joinPOnline = new BButton("Join partida online");
	
	protected BButton contPartida = new BButton("Continuar partida iniciada");

	
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
		
		contPartida.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		
		opciones.add(Box.createRigidArea(Escalador.newDimension(0, 150))); //en el medio 250
		
		opciones.add(partidaLocal);
        opciones.add(Box.createRigidArea(Escalador.newDimension(0, 10))); // Espacio entre los botones
        opciones.add(crearPOnline);
        opciones.add(Box.createRigidArea(Escalador.newDimension(0, 10))); // Espacio entre los botones
        opciones.add(joinPOnline);
        opciones.add(Box.createRigidArea(Escalador.newDimension(0, 30))); // Espacio entre los botones
        opciones.add(contPartida);
        
        
        

		this.add(navBar, BorderLayout.NORTH);	
		this.add(opciones, BorderLayout.CENTER);


		partidaLocal.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
            	VentanaPrincipal ventanaPrincip = Session.getVentana();
            	DatosPartida datos = new DatosPartida("local");
            	Session.setDatosPartida(datos);
            	ventanaPrincip.getPanelConfLocal().setDatosPartida(datos);
            	ventanaPrincip.getCardLayout().show(ventanaPrincip.getPanelPrincipal(), "CONFLOCAL");
            }
        });
        
        crearPOnline.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
            	VentanaPrincipal ventanaPrincip = Session.getVentana();
            	System.out.println("-----CONSOLA CREADOR------");
            	if (Session.getCurrentUser()!=null) {
            		
            		startServerCnx();
                	try {
						Session.getCtsConnection().createGame();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                	
                	ventanaPrincip.getCardLayout().show(ventanaPrincip.getPanelPrincipal(), "CONFONLINE");
                    
            	}
            	else {
            		ventanaPrincip.getCardLayout().show(ventanaPrincip.getPanelPrincipal(), "LOGIN");
            	}   
            }
        });
        
        joinPOnline.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
            	VentanaPrincipal ventanaPrincip = Session.getVentana();
            	System.out.println("-----CONSOLA JOINER------");
            	
				if (Session.getCurrentUser()!=null) {
	            	startServerCnx();
	            	try {
						Session.getCtsConnection().getListaPartidas();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
	            	
	            	ventanaPrincip.getCardLayout().show(ventanaPrincip.getPanelPrincipal(), "LISTAPARTIDAS");
                    
            	}
            	else {
            		ventanaPrincip.getCardLayout().show(ventanaPrincip.getPanelPrincipal(), "LOGIN");
            	}  
            }
        });

	}




	protected void startServerCnx() {
		try {
			Session.startServerCnx();
		} catch (ClassNotFoundException | IOException e1) {
			System.out.println("Error al conectarse con el server");
		}
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
	
	



