package ventanas;

import componentes.*;
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

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfPLocal extends JPanel {
    protected BButton botonIniciarPartida = new BButton("Iniciar Partida");
    protected RButton volverBtn = new RButton("Volver");
    protected RButton botonUser1 = new RButton("Login");
    protected RButton botonUser2 = new RButton("Login");
    protected Color colorFondo = Configuracion.BACKGROUND;
    protected JPanel navBar;
    
    protected DatosPartida datos;
	private BLabel gameId;

    public ConfPLocal() {
        setBackground(colorFondo);

       
  		//--------------------- NAVBAR-------------------------------------------
  		Icon icon = IconFontSwing.buildIcon(FontAwesome.BACKWARD, Escalador.escalarF(15));
  		volverBtn.setIcon(icon);
  		
		JPanel navBarContainer = new JPanel(new FlowLayout());
		navBarContainer.add(new navBar(volverBtn));
        navBarContainer.setBackground(Configuracion.BACKGROUND);
      
  		
  		//--------------------- NAVBAR-------------------------------------------	   
      		
        
        

        // Elementos de la interfaz
        JLabel labelCodigoPartida = new JLabel("Código de la partida:");
        gameId = new BLabel("");
        
        

        JLabel labelModoJuego = new JLabel("Modo de juego:");
        JComboBox<String> comboModoJuego = new JComboBox<>(new String[]{"Clásico", "madChess"});
        comboModoJuego.setMaximumSize(new Dimension(Escalador.escalar(150), Escalador.escalar(35)));

        JLabel labelJugador1 = new JLabel("Jugador 1:");
        botonUser1 = new RButton("Login");
        botonUser1.setMaximumSize(new Dimension(Escalador.escalar(120), Escalador.escalar(25)));

        JLabel labelNombre1 = new JLabel("Nombre");
        labelNombre1.setVisible(false);

        JLabel labelJugador2 = new JLabel("Jugador 2:");
        botonUser2 = new RButton("Login");
        botonUser2.setMaximumSize(new Dimension(Escalador.escalar(120), Escalador.escalar(25))); // Ajustar el tamaño máximo del botón

        JLabel labelNombre2 = new JLabel("Nombre");
        labelNombre2.setVisible(false);

        botonIniciarPartida = new BButton("Iniciar Partida");
        botonIniciarPartida.setPreferredSize(new Dimension(Escalador.escalar(150), Escalador.escalar(35)));
        
        // Configuración del layout con BoxLayout en el eje Y
        setLayout(new BorderLayout());

        // Panel principal para organizar los elementos en el centro
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(getBackground());

        // Configurar elementos en el panel principal
        centerPanel.add(Box.createRigidArea(new Dimension(0, 120)));
        centerPanel.add(createRowPanel(labelCodigoPartida, gameId));
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Espacio vertical entre filas
        centerPanel.add(createRowPanel(labelModoJuego, comboModoJuego));
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Espacio vertical entre filas
        centerPanel.add(createRowPanel(labelJugador1, botonUser1, labelNombre1));
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Espacio vertical entre filas
        centerPanel.add(createRowPanel(labelJugador2, botonUser2, labelNombre2));
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Espacio vertical entre filas
        centerPanel.add(createRowPanel(botonIniciarPartida));
        centerPanel.add(Box.createVerticalGlue()); // Espacio en blanco abajo

        // Agregar el navBar en la posición NORTH
        add(navBarContainer, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        
        
        
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
                
                datos.setTipoPartida(tipo);
            }
		});

        // ACTION LISTENERS
        
        
		
        botonIniciarPartida.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
            	VentanaPrincipal ventana = Session.getVentana();
            	
				if (datos.getJugadores().isEmpty()) {
//					JOptionPane.showMessageDialog(ventana, "No hay jugadores en la partida");
//					return;
					datos.setJugador(new Usuario("userDebug1"));
					datos.setJugador(new Usuario("userDebug2"));
					
				}
            	
            	
            	if (!(datos.getTipoPartida()==partidaTipo.MADCHESS)) {
            		
            		ventana.getCardLayout().show(ventana.getPanelPrincipal(), Paneles.JUEGO);
            		new LogicaPartida();
            	}
            	else {
            		ventana.getPanelMadChessConfig().setPlayers(datos.getJugadores());
            		ventana.getCardLayout().show(ventana.getPanelPrincipal(), Paneles.CONFMADCHESS);
            	}
            }
        });
        
        

        botonUser1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaPrincipal ventana = Session.getVentana();
				ventana.getPanelLogin().setRedirect(Paneles.CONFLOCAL);
				ventana.getCardLayout().show(ventana.getPanelPrincipal(), Paneles.LOGIN);
				
			}
		});
        botonUser2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaPrincipal ventana = Session.getVentana();
				ventana.getPanelLogin().setRedirect(Paneles.CONFLOCAL);
				ventana.getCardLayout().show(ventana.getPanelPrincipal(), Paneles.LOGIN);
			}
		});
        
        
    }


	private JPanel createRowPanel(Component... components) {
        JPanel rowPanel = new JPanel();
        rowPanel.setLayout(new BoxLayout(rowPanel, BoxLayout.X_AXIS));
        rowPanel.setBackground(colorFondo);
        for (Component component : components) {
            rowPanel.add(Box.createRigidArea(new Dimension(15, 0)));
            rowPanel.add(component);
        }
        rowPanel.add(Box.createRigidArea(new Dimension(15, 0)));
        return rowPanel;
    }
	
	
    public void setDatosPartida(DatosPartida datos) {
		this.datos = datos;
		gameId.setText(datos.getGameId());
	}


	public void setUser(Usuario logedUser) {
		if (datos.getJugadores().isEmpty()) {
			botonUser1.setText(logedUser.getUsername());
			botonUser1.setEnabled(false);
		}
		else {
			botonUser2.setText(logedUser.getUsername());
			botonUser2.setEnabled(false);
		}
		datos.setJugador(logedUser);
	}

    
    
    
    
    
}
