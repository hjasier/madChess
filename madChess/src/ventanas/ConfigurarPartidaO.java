package ventanas;

import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.synth.SynthLabelUI;

import componentes.BButton;
import componentes.BTextField;
import componentes.RButton;

public class ConfigurarPartidaO extends JPanel {
    protected ImageIcon imgFoto = new ImageIcon(getClass().getResource("../srcmedia/logo110x300.png"));
    protected BButton botonIniciarPartida = new BButton("Iniciar Partida");
    protected BButton botonVolver = new BButton("Volver");
    protected RButton botonUser1 = new RButton("Login");
    protected RButton botonUser2 = new RButton("Login");
    protected BTextField labelCodigoValor= new BTextField("",false);
    protected JPanel navBar = new JPanel();
    protected JLabel foto = new JLabel(imgFoto);
    protected Color colorTemp = new Color(16, 16, 16);
    protected JPanel panelLogo = new JPanel();
    protected JPanel opcionesenY = new JPanel();
    
    
    public ConfigurarPartidaO() {
        
        setLayout(new BorderLayout());
        setBackground(colorTemp);

        navBar.setLayout(new BorderLayout());
        navBar.setBackground(Color.red);
//colorTemp
        opcionesenY.setLayout(new BoxLayout(opcionesenY, BoxLayout.Y_AXIS));
        opcionesenY.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        Image imagenEscalada = imgFoto.getImage().getScaledInstance(300, 110, Image.SCALE_SMOOTH);
        imgFoto = new ImageIcon(imagenEscalada);
        foto.setIcon(imgFoto);

        panelLogo.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
		panelLogo.add(foto, BorderLayout.WEST);
        panelLogo.setBackground(colorTemp);

        navBar.add(panelLogo, BorderLayout.WEST);
		navBar.setMaximumSize(new Dimension(navBar.getMaximumSize().width,100));
		
		
        // Elementos de la interfaz
		
		

        
        
		
        JLabel labelCodigoPartida = new JLabel("Código de la partida:");
        labelCodigoValor.setMaximumSize(new Dimension(200, 40));
        JPanel opcionesenX = new JPanel();
		opcionesenX.setLayout(new BoxLayout(opcionesenX, BoxLayout.X_AXIS));
		opcionesenX.add(labelCodigoPartida);
		opcionesenX.add(labelCodigoValor);
		
		
		
		// Panel para la segunda fila de componentes
		JLabel labelModoJuego = new JLabel("Modo de juego:");
		JComboBox<String> comboModoJuego = new JComboBox<>(new String[]{"Clásico", "madChess"});
		comboModoJuego.setMaximumSize(new Dimension(200, 40));
		JPanel secondRowPanel = new JPanel();
        secondRowPanel.setLayout(new BoxLayout(secondRowPanel, BoxLayout.X_AXIS));
        secondRowPanel.add(labelModoJuego);
        secondRowPanel.add(comboModoJuego);
        
        
        
        

        JLabel labelJugador1 = new JLabel("Jugador 1:");
        
        botonUser1 = new RButton("Login");
        JLabel labelNombre1 = new JLabel("Nombre");
        labelNombre1.setVisible(false);
        
        JPanel thirdRowPanel = new JPanel();
        thirdRowPanel.setLayout(new BoxLayout(thirdRowPanel, BoxLayout.X_AXIS));
        thirdRowPanel.add(labelJugador1);
        thirdRowPanel.add(botonUser1);
        thirdRowPanel.add(labelNombre1);
        //labelNombre1.setVisible(false);
        
        
        
        JLabel labelJugador2 = new JLabel("Jugador 2:");
        botonUser2 = new RButton("Login");
        JLabel labelNombre2 = new JLabel("Nombre");
        labelNombre2.setVisible(false);
        
        JPanel fourthRowPanel = new JPanel();
        fourthRowPanel.setLayout(new BoxLayout(fourthRowPanel, BoxLayout.X_AXIS));
        fourthRowPanel.add(labelJugador2);
        fourthRowPanel.add(botonUser2);
        fourthRowPanel.add(labelNombre2);
        
        botonIniciarPartida = new BButton("Iniciar Partida");
        botonVolver = new BButton("Volver");
        
        
     // Agregar los paneles al contenedor opcionesenY
        opcionesenY.add(Box.createVerticalGlue()); // Espacio en blanco arriba
        opcionesenY.add(opcionesenX);
        opcionesenY.add(Box.createRigidArea(new Dimension(0, 10))); // Espacio entre filas
        opcionesenY.add(secondRowPanel);
        opcionesenY.add(Box.createRigidArea(new Dimension(0, 10))); // Espacio entre filas
        opcionesenY.add(thirdRowPanel);
        opcionesenY.add(Box.createRigidArea(new Dimension(0, 10))); // Espacio entre filas
        opcionesenY.add(fourthRowPanel);
        opcionesenY.add(Box.createRigidArea(new Dimension(0, 10))); // Espacio entre filas
        opcionesenY.add(botonIniciarPartida);
        opcionesenY.add(Box.createRigidArea(new Dimension(0, 5))); // Espacio entre botones
        opcionesenY.add(botonVolver);
        opcionesenY.add(Box.createVerticalGlue()); // Espacio en blanco abajo
        
        
        
        

        // Agregar el navBar en la posición NORTH
        add(navBar, BorderLayout.NORTH);
        add(opcionesenY, BorderLayout.CENTER);
        
  
        
        opcionesenY.setBackground(colorTemp);

        // Agregar opcionesenY en el centro

        // Configurar acción del botón Iniciar Partida
        botonIniciarPartida.addActionListener(e -> {
            // Lógica para iniciar la partida
        });
    }
}
