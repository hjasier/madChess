package ventanas;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

import Sockets.ClientHandler;
import Sockets.Servidor;
import juego.DatosPartida;
import juego.LogicaPartida;
import juego.partidaTipo;
import librerias.FontAwesome;
import librerias.IconFontSwing;
import objetos.Jugador;
import objetos.Usuario;
import utils.Configuracion;
import utils.Escalador;
import utils.Session;
import componentes.*;

public class ConfPOnline extends JPanel {
    
    protected BButton botonIniciarPartida = new BButton("Iniciar Partida");
    protected RButton user1Btn = new RButton("Login");
    protected RButton user2Btn = new RButton("Esperando...");
    protected BLabel labelCodigoValor= new BLabel("CÓDIGO");
    protected JPanel navBar;
    protected JPanel opciones = new JPanel();
    
    
    protected Color colorFondo = Configuracion.BACKGROUND;
    
    protected JPanel opcionesenY = new JPanel();
    protected RButton volverBtn = new RButton("Volver");

    
	private DatosPartida datosPartida; 

    


	public ConfPOnline(Jugador user1) {
        
		user1Btn.setMaximumSize(new Dimension((int) Escalador.escalar(120), (int) Escalador.escalar(25)));
		user2Btn.setMaximumSize(new Dimension((int) Escalador.escalar(120), (int) Escalador.escalar(25)));
		
		
        setLayout(new BorderLayout());
        setBackground(colorFondo);

  		opciones.setBackground(colorFondo);
  		
  		
  		opciones.setLayout(new BoxLayout(opciones, BoxLayout.Y_AXIS));
  		
  	//--------------------- NAVBAR-------------------------------------------
  		Icon icon = IconFontSwing.buildIcon(FontAwesome.BACKWARD, Escalador.escalarF(15));
  		volverBtn.setIcon(icon);
  		
		JPanel navBarContainer = new JPanel(new FlowLayout());
		navBarContainer.add(new navBar(volverBtn));
        navBarContainer.setBackground(Configuracion.BACKGROUND);
      
  		
  		//--------------------- NAVBAR-------------------------------------------	   
      		
      		


    		
      		
		
  	    opcionesenY.setLayout(new BoxLayout(opcionesenY, BoxLayout.Y_AXIS));
        opcionesenY.setAlignmentX(Component.CENTER_ALIGNMENT);	
      		
      		
        user2Btn.setEnabled(false);
		
        
		
        JLabel labelCodigoPartida = new JLabel("Código de la partida:");
        JPanel opcionesenX = new JPanel();
		opcionesenX.setLayout(new BoxLayout(opcionesenX, BoxLayout.X_AXIS));
		
		opcionesenX.add(labelCodigoPartida);
		opcionesenX.add(Box.createRigidArea(new Dimension(5, 0)));
		opcionesenX.add(labelCodigoValor);
		opcionesenX.add(Box.createRigidArea(new Dimension(115, 0)));
		opcionesenX.setBackground(colorFondo);
		
		//BJRadioButton
		
		JLabel labelPrivacidad = new JLabel("Privacidad:");
		BJRadioButton publicoButton = new BJRadioButton("Pública");
		BJRadioButton privadoButton = new BJRadioButton("Privada");

		ButtonGroup privacidadButtons = new ButtonGroup(); // Agrupa los botones para que funcionen exclusivamente entre sí
		privacidadButtons.add(publicoButton);
		privacidadButtons.add(privadoButton);
		//privacidadButtons.setMaximumSize(new Dimension(200, 40));
		JPanel privacidadRowPanel = new JPanel();
		privacidadRowPanel.setLayout(new BoxLayout(privacidadRowPanel, BoxLayout.X_AXIS));
		privacidadRowPanel.add(labelPrivacidad);
		privacidadRowPanel.add(Box.createRigidArea(new Dimension(104, 0)));
		privacidadRowPanel.add(publicoButton);
		privacidadRowPanel.add(privadoButton);
		privacidadRowPanel.add(Box.createRigidArea(new Dimension(170, 0)));
		privacidadRowPanel.setBackground(colorFondo);
		
		
		
		
		
		// Panel para la segunda fila de componentes
		JLabel labelModoJuego = new JLabel("Modo de juego:");
		MComboBox<String> comboModoJuego = new MComboBox<>(new String[]{"Clásico", "madChess"});
		comboModoJuego.setMaximumSize(new Dimension(200, 40));
		JPanel secondRowPanel = new JPanel();
        secondRowPanel.setLayout(new BoxLayout(secondRowPanel, BoxLayout.X_AXIS));
        
        secondRowPanel.add(labelModoJuego);
        secondRowPanel.add(Box.createRigidArea(new Dimension(35, 0)));
        secondRowPanel.add(comboModoJuego);
        secondRowPanel.add(Box.createRigidArea(new Dimension(115, 0)));
        secondRowPanel.setBackground(colorFondo);
        
        
        

        JLabel labelJugador1 = new JLabel("Jugador 1:");
        

        JLabel labelNombre1 = new JLabel("Nombre");
        labelNombre1.setVisible(false);
        
        JPanel thirdRowPanel = new JPanel();
        thirdRowPanel.setLayout(new BoxLayout(thirdRowPanel, BoxLayout.X_AXIS));
        
        thirdRowPanel.add(labelJugador1);
        thirdRowPanel.add(Box.createRigidArea(new Dimension(55, 0)));
        thirdRowPanel.add(user1Btn);
        thirdRowPanel.add(labelNombre1);
        thirdRowPanel.add(Box.createRigidArea(new Dimension(115, 0)));
        //labelNombre1.setVisible(false);
        thirdRowPanel.setBackground(colorFondo);
        
        
        JLabel labelJugador2 = new JLabel("Jugador 2:");
  
        JLabel labelNombre2 = new JLabel("Nombre");
        labelNombre2.setVisible(false);
        
        JPanel fourthRowPanel = new JPanel();
        fourthRowPanel.setLayout(new BoxLayout(fourthRowPanel, BoxLayout.X_AXIS));
        fourthRowPanel.add(labelJugador2);
        fourthRowPanel.add(Box.createRigidArea(new Dimension(55, 0)));
        fourthRowPanel.add(user2Btn);
        fourthRowPanel.add(labelNombre2);
        fourthRowPanel.add(Box.createRigidArea(new Dimension(115, 0)));
        fourthRowPanel.setBackground(colorFondo);
        
        botonIniciarPartida = new BButton("Iniciar Partida");
        JPanel fifthRowPanel = new JPanel();
        fifthRowPanel.setLayout(new BoxLayout(fifthRowPanel, BoxLayout.X_AXIS));
        fifthRowPanel.add(botonIniciarPartida);
        fifthRowPanel.setBackground(colorFondo);
        
        botonIniciarPartida.setPreferredSize(new Dimension(Escalador.escalar(150), Escalador.escalar(35)));
        
     // Agregar los paneles al contenedor opcionesenY
        opcionesenY.add(Box.createRigidArea(new Dimension(0, Escalador.escalar(120))));
        opcionesenY.add(opcionesenX);
        opcionesenY.add(Box.createRigidArea(new Dimension(0,Escalador.escalar(40)))); // Espacio entre filas
        opcionesenY.add(privacidadRowPanel);
        opcionesenY.add(Box.createRigidArea(new Dimension(0, Escalador.escalar(10))));
        opcionesenY.add(secondRowPanel);
        opcionesenY.add(Box.createRigidArea(new Dimension(0, Escalador.escalar(20)))); // Espacio entre filas
        opcionesenY.add(thirdRowPanel);
        opcionesenY.add(Box.createRigidArea(new Dimension(0, Escalador.escalar(10))));// Espacio entre filas
        opcionesenY.add(fourthRowPanel);
        opcionesenY.add(Box.createRigidArea(new Dimension(0, Escalador.escalar(30)))); // Espacio entre filas
        opcionesenY.add(fifthRowPanel);
        opcionesenY.add(Box.createRigidArea(new Dimension(0, Escalador.escalar(5)))); // Espacio entre botones
        opcionesenY.add(Box.createVerticalGlue()); // Espacio en blanco abajo
        
        
        
        opcionesenY.setBackground(colorFondo);

        // Agregar el navBar en la posición NORTH
        add(navBarContainer, BorderLayout.NORTH);
        add(opcionesenY, BorderLayout.CENTER);
        
  
        comboModoJuego.addActionListener(new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent e) {
                String tipoPartida = comboModoJuego.getSelectedItem().toString();
                partidaTipo tipo;
                if (tipoPartida.equals("madChess")) {
                	tipo = partidaTipo.MADCHESS;
                }
                else {
                	tipo = partidaTipo.CLASICA;
                }
              
                
                Session.getDatosPartida().setTipoPartida(tipo);
                try {
					Session.getCtsConnection().postDatosPartida();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
            }
		});
        

        botonIniciarPartida.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
            	ArrayList<Jugador> jugadores = Session.getDatosPartida().getJugadores();
            	if (jugadores.size()>1&&jugadores.get(0).getUsuario().getUsername().equals(Session.getCurrentUser().getUsername())) {
            	try {
					Session.getCtsConnection().postInitGame();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            	Session.getVentana().initGame();
            	}
            	else {
            		JOptionPane.showMessageDialog(null, "No eres el admin o faltan jugadores", "Error", JOptionPane.ERROR_MESSAGE);
            	}
                
            }
        });
        
		user1Btn.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						VentanaPrincipal ventanaPrincip = Session.getVentana();
						ventanaPrincip.getPanelLogin().setRedirect("LOGIN");
						ventanaPrincip.getCardLayout().show(ventanaPrincip.getPanelPrincipal(), "CONFONLINE");
					}
				});
        
        
        
        
        
    }


	public void setUser1(Usuario user) {
		user1Btn.setText(user.getUsername());
		user1Btn.setEnabled(false);
	}
	
	public void setUser2(Usuario user) {
		user2Btn.setText(user.getUsername());
	}
	







	public void setDatosPartida(DatosPartida curDatosPartida) {
		this.datosPartida = curDatosPartida;
		
		labelCodigoValor.setText(curDatosPartida.getGameId());
		setUser1(curDatosPartida.getJugadores().get(0).getUsuario());
		try {
			setUser2(curDatosPartida.getJugadores().get(1).getUsuario());
		} catch (Exception e) {}
		
		
		
	}

}
