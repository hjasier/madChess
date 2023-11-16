package ventanas;

import componentes.BButton;
import componentes.FontAwesome;
import componentes.IconFontSwing;
import componentes.RButton;

import javax.swing.*;
import java.awt.*;

import javax.swing.*;
import java.awt.*;

public class ConfigurarPartidaLocal extends JPanel {
    protected ImageIcon imgFoto = new ImageIcon(getClass().getResource("../srcmedia/logo110x300.png"));
    protected BButton botonIniciarPartida = new BButton("Inicar Partida");
    protected BButton botonVolver = new BButton("Volver");
    protected RButton botonUser1 = new RButton("Login");
    protected RButton botonUser2 = new RButton("Login");
    
	public ConfigurarPartidaLocal() {
        JPanel navBar = new JPanel();
        JLabel foto = new JLabel(imgFoto);
        Color colorTemp = new Color(16, 16, 16);
        JPanel panelLogo = new JPanel();
        setBackground(colorTemp);
        
        navBar.setLayout(new BorderLayout());
        navBar.setBackground(getBackground());

        foto.setIcon(imgFoto);

        panelLogo.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        panelLogo.add(foto, BorderLayout.WEST);
        panelLogo.setBackground(getBackground());

        navBar.add(panelLogo, BorderLayout.WEST);

        
        
        // Elementos de la interfaz
        JLabel labelCodigoPartida = new JLabel("Código de la partida:");
        JLabel labelCodigoValor = new JLabel("32423");

        JLabel labelModoJuego = new JLabel("Modo de juego:");
        JComboBox<String> comboModoJuego = new JComboBox<>(new String[]{"Clásico", "madChess"});

        JLabel labelJugador1 = new JLabel("Jugador 1:");
        JPanel panelJugador1 = new JPanel();
        botonUser1 = new RButton("Login");
        JLabel labelNombre1 = new JLabel("Nombre");
        labelNombre1.setVisible(false);
        panelJugador1.add(botonUser1);
        panelJugador1.add(labelNombre1);
        panelJugador1.setBackground(this.getBackground());

        JLabel labelJugador2 = new JLabel("Jugador 2:");
        JPanel panelJugador2 = new JPanel();
        botonUser2 = new RButton("Login");
        JLabel labelNombre2 = new JLabel("Nombre");
        labelNombre2.setVisible(false);
        panelJugador2.add(botonUser2);
        panelJugador2.add(labelNombre2);
        panelJugador2.setBackground(this.getBackground());
        
        botonIniciarPartida = new BButton("Iniciar Partida");
        botonVolver = new BButton("Volver");
//        new BButton("Iniciar Partida");
//        new BButton("Volver");
        // Configuración del layout con BorderLayout
        setLayout(new BorderLayout());

        // Agregar el navBar en la posición NORTH
        add(navBar, BorderLayout.NORTH);

        // Agregar elementos al panel con GridBagLayout en el centro
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridBagLayout());
        contentPanel.setBackground(getBackground());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        contentPanel.add(labelCodigoPartida, gbc);

        gbc.gridx = 1;
        contentPanel.add(labelCodigoValor, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        contentPanel.add(labelModoJuego, gbc);

        gbc.gridx = 1;
        contentPanel.add(comboModoJuego, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        contentPanel.add(labelJugador1, gbc);

        gbc.gridx = 1;
        contentPanel.add(panelJugador1, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        contentPanel.add(labelJugador2, gbc);

        gbc.gridx = 1;
        contentPanel.add(panelJugador2, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        contentPanel.add(botonIniciarPartida, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        contentPanel.add(botonVolver, gbc);
        
        // Agregar contentPanel en el centro
        add(contentPanel, BorderLayout.CENTER);

        // Configurar acción del botón Iniciar Partida
        botonIniciarPartida.addActionListener(e -> {
            // Aquí puedes agregar la lógica para iniciar la partida
            // Por ejemplo, obtener los valores seleccionados en los combo boxes y realizar acciones relacionadas.
        });
    }

}

