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
    protected RButton botonUser3 = new RButton("Esperando...");
    protected RButton botonUser4 = new RButton("Esperando...");
    protected JPanel jugador3Panel = new JPanel();
    protected JPanel jugador4Panel = new JPanel();
    
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
        opcionesenX.setLayout(new BoxLayout(opcionesenX, BoxLayout.Y_AXIS));

        opcionesenX.add(createRowPanel(labelCodigoPartida, labelCodigoValor));
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
		privacidadRowPanel.add(Box.createRigidArea(new Dimension(Escalador.escalar(104), Escalador.escalar(0))));
		privacidadRowPanel.add(publicoButton);
		privacidadRowPanel.add(privadoButton);
		privacidadRowPanel.add(Box.createRigidArea(new Dimension(Escalador.escalar(170), Escalador.escalar(0))));
		privacidadRowPanel.setBackground(colorFondo);
		
		
		
		
		
		// Panel para la segunda fila de componentes
		JLabel labelModoJuego = new JLabel("Modo de juego:");
		JComboBox<String> comboModoJuego = new JComboBox<>(new String[]{"Clásico", "madChess"});
		comboModoJuego.setMaximumSize(new Dimension(Escalador.escalar(150), Escalador.escalar(35)));

		JPanel secondRowPanel = createRowPanel(labelModoJuego, comboModoJuego);
		secondRowPanel.setBackground(colorFondo);
        
        
        

		JLabel labelJugador1 = new JLabel("Jugador 1:");
		JLabel labelNombre1 = new JLabel("Nombre");
		labelNombre1.setVisible(false);

		JPanel thirdRowPanel = createRowPanel(labelJugador1, user1Btn, labelNombre1);
		thirdRowPanel.setBackground(colorFondo);
        
        
		JLabel labelJugador2 = new JLabel("Jugador 2:");
		JLabel labelNombre2 = new JLabel("Nombre");
		labelNombre2.setVisible(false);

		JPanel fourthRowPanel = createRowPanel(labelJugador2, user2Btn, labelNombre2);
		fourthRowPanel.setBackground(colorFondo);
        


        JLabel labelJugador3 = new JLabel("Jugador 3:");
        botonUser3.setMaximumSize(new Dimension(Escalador.escalar(120), Escalador.escalar(25))); // Ajustar el tamaño máximo del botón
        botonUser3.setEnabled(false);
        JLabel labelNombre3 = new JLabel("Nombre");
        labelNombre3.setVisible(false);
        
        JLabel labelJugador4 = new JLabel("Jugador 4:");
        botonUser4.setMaximumSize(new Dimension(Escalador.escalar(120), Escalador.escalar(25))); // Ajustar el tamaño máximo del botón
        botonUser4.setEnabled(false);
        
        JLabel labelNombre4 = new JLabel("Nombre");
        labelNombre4.setVisible(false);
        
        
        
        jugador3Panel.setLayout(new BoxLayout(jugador3Panel, BoxLayout.X_AXIS));
        jugador3Panel.setBackground(colorFondo);
        jugador3Panel.add(createRowPanel(labelJugador3, botonUser3, labelNombre3));
        jugador3Panel.add(Box.createRigidArea(new Dimension(Escalador.escalar(0), Escalador.escalar(20)))); // Espacio vertical entre filas
        jugador3Panel.setVisible(false);

        jugador4Panel.setLayout(new BoxLayout(jugador4Panel, BoxLayout.X_AXIS));
        jugador4Panel.setBackground(colorFondo);
        jugador4Panel.add(createRowPanel(labelJugador4, botonUser4, labelNombre4));
        jugador4Panel.add(Box.createRigidArea(new Dimension(Escalador.escalar(0), Escalador.escalar(20)))); // Espacio vertical entre filas
        jugador4Panel.setVisible(false);
        
        
        botonIniciarPartida = new BButton("Iniciar Partida");
        JPanel fifthRowPanel = new JPanel();
        fifthRowPanel.setLayout(new BoxLayout(fifthRowPanel, BoxLayout.X_AXIS));
        fifthRowPanel.add(botonIniciarPartida);
        fifthRowPanel.setBackground(colorFondo);
        
        botonIniciarPartida.setPreferredSize(new Dimension(Escalador.escalar(150), Escalador.escalar(35)));
        
     // Agregar los paneles al contenedor opcionesenY
        opcionesenY.add(Box.createRigidArea(new Dimension(0, Escalador.escalar(120))));
        opcionesenY.add(opcionesenX);
       // opcionesenY.add(Box.createRigidArea(new Dimension(0,Escalador.escalar(40)))); // Espacio entre filas
        //opcionesenY.add(privacidadRowPanel);
        opcionesenY.add(Box.createRigidArea(new Dimension(0, Escalador.escalar(10))));
        opcionesenY.add(secondRowPanel);
        opcionesenY.add(Box.createRigidArea(new Dimension(0, Escalador.escalar(20)))); // Espacio entre filas
        opcionesenY.add(thirdRowPanel);
        opcionesenY.add(Box.createRigidArea(new Dimension(0, Escalador.escalar(10))));// Espacio entre filas
        opcionesenY.add(fourthRowPanel);
        opcionesenY.add(Box.createRigidArea(new Dimension(0, Escalador.escalar(10))));
        opcionesenY.add(jugador3Panel);
        opcionesenY.add(Box.createRigidArea(new Dimension(0, Escalador.escalar(10))));
        opcionesenY.add(jugador4Panel);
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
						ventanaPrincip.getPanelLogin().setRedirect(Paneles.LOGIN);
						ventanaPrincip.getCardLayout().show(ventanaPrincip.getPanelPrincipal(), Paneles.CONFONLINE);
					}
				});
        
        
        
        
        
    }


	public void setUser1(Usuario user) {
		user1Btn.setText(user.getUsername());
		user1Btn.setEnabled(false);
	}
	
	public void setUser2(Usuario user) {
		user2Btn.setText(user.getUsername());
		user2Btn.setEnabled(false);
	}
	public void setUser3(Usuario user) {
		botonUser3.setText(user.getUsername());
		botonUser3.setEnabled(false);
	}
	public void setUser4(Usuario user) {
		botonUser4.setText(user.getUsername());
		botonUser4.setEnabled(false);
	}
	




	


	public void setDatosPartida(DatosPartida curDatosPartida) {
		this.datosPartida = curDatosPartida;
		labelCodigoValor.setText(curDatosPartida.getGameId());
		setUser1(curDatosPartida.getJugadores().get(0).getUsuario());
		try {
			
			
			
			if (curDatosPartida.getPlayerNum() == 2) {
				if(curDatosPartida.getJugadores().size()>=2) {
				setUser2(curDatosPartida.getJugadores().get(1).getUsuario());
				}
				jugador3Panel.setVisible(false);
				jugador4Panel.setVisible(false);
			
			}
			else if (curDatosPartida.getPlayerNum() == 3) {
				if(curDatosPartida.getJugadores().size()>=2) {
				setUser3(curDatosPartida.getJugadores().get(2).getUsuario());
				}
				jugador3Panel.setVisible(true);
				jugador4Panel.setVisible(false);
						
			}

			else if (curDatosPartida.getPlayerNum() == 4) {
				if(curDatosPartida.getJugadores().size()>=3) {
				setUser4(curDatosPartida.getJugadores().get(3).getUsuario());
				}
				jugador3Panel.setVisible(true);
				jugador4Panel.setVisible(true);
				
				
			}


		} catch (Exception e) {}
		
		
		
	}
	
	
	private JPanel createRowPanel(Component... components) {
        JPanel rowPanel = new JPanel();
        rowPanel.setLayout(new BoxLayout(rowPanel, BoxLayout.X_AXIS));
        rowPanel.setBackground(colorFondo);
        for (Component component : components) {
            rowPanel.add(Box.createRigidArea(new Dimension(Escalador.escalar(15), 0)));
            rowPanel.add(component);
        }
        rowPanel.add(Box.createRigidArea(new Dimension(Escalador.escalar(15), 0)));
        return rowPanel;
    }

}
