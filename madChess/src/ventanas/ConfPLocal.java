package ventanas;

import componentes.*;
import juego.Configuracion;
import juego.DatosPartida;
import librerias.FontAwesome;
import librerias.IconFontSwing;
import objetos.Jugador;

import javax.swing.*;
import java.awt.*;

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
        Icon icon = IconFontSwing.buildIcon(FontAwesome.BACKWARD, 15);
        volverBtn.setIcon(icon);
        navBar = new navBar(volverBtn);
        //--------------------- NAVBAR-------------------------------------------

        // Elementos de la interfaz
        JLabel labelCodigoPartida = new JLabel("Código de la partida:");
        gameId = new BLabel("");
        
        

        JLabel labelModoJuego = new JLabel("Modo de juego:");
        MComboBox<String> comboModoJuego = new MComboBox<>(new String[]{"Clásico", "madChess"});
        comboModoJuego.setMaximumSize(new Dimension(150, 25));

        JLabel labelJugador1 = new JLabel("Jugador 1:");
        botonUser1 = new RButton("Login");
        botonUser1.setMaximumSize(new Dimension(100, 25)); // Ajustar el tamaño máximo del botón

        JLabel labelNombre1 = new JLabel("Nombre");
        labelNombre1.setVisible(false);

        JLabel labelJugador2 = new JLabel("Jugador 2:");
        botonUser2 = new RButton("Login");
        botonUser2.setMaximumSize(new Dimension(100, 25)); // Ajustar el tamaño máximo del botón

        JLabel labelNombre2 = new JLabel("Nombre");
        labelNombre2.setVisible(false);

        botonIniciarPartida = new BButton("Iniciar Partida");

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
        add(navBar, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
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


	public void setUser(Jugador logedUser) {
		if (datos.getJugadores().isEmpty()) {
			botonUser1.setText(logedUser.getNombre());
			botonUser1.setEnabled(false);
		}
		else {
			botonUser2.setText(logedUser.getNombre());
			botonUser2.setEnabled(false);
		}
		datos.setJugador(logedUser);
	}

    
    
    
    
    
}
