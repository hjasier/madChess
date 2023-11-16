package ventanas;

import java.awt.*;
import javax.swing.*;

import Sockets.ClienteHandler;
import Sockets.Servidor;
import componentes.BButton;
import componentes.BTextField;
import componentes.FontAwesome;
import componentes.IconFontSwing;
import componentes.RButton;
import objetos.Jugador;
import componentes.BJcomboBox;
import componentes.BJRadioButton;

public class ConfPOnline extends JPanel {
    protected ImageIcon imgFoto = new ImageIcon(getClass().getResource("../srcmedia/logo110x300.png"));
    protected BButton botonIniciarPartida = new BButton("Iniciar Partida");
    protected RButton botonUser1 = new RButton("Login");
    protected JLabel labelUser2 = new JLabel("Esperando...");
    protected BTextField labelCodigoValor= new BTextField("ZNSAD",false);
    protected JPanel navBar = new JPanel();
    protected JPanel opciones = new JPanel();
    protected JPanel panelLogin = new JPanel();
    protected JLabel foto = new JLabel(imgFoto);
    protected Color colorTemp = new Color(16, 16, 16);
    protected JPanel panelLogo = new JPanel();
    protected JPanel opcionesenY = new JPanel();
    protected RButton volverBtn = new RButton("Volver");
    
    
    private Jugador user1;
    private Jugador user2;
    private String idPartida;
    
    private Servidor server;
    
    


	public ConfPOnline(Jugador user1) {
        
    	if (user1!=null) {
    		this.user1 = user1;
    		JLabel botonUser1 = new JLabel("hjasier");
    	}
    	
    	
    	
        setLayout(new BorderLayout());
        setBackground(colorTemp);

      //--------------------- NAVBAR-------------------------------------------
    

      		navBar.setBackground(colorTemp);
      		opciones.setBackground(colorTemp);
      		panelLogo.setBackground(colorTemp);
      		panelLogin.setBackground(colorTemp);


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
      		panelLogin.add(volverBtn);
              
      		navBar.add(panelLogo, BorderLayout.WEST);
      		navBar.add(panelLogin, BorderLayout.EAST);
              
      		Icon icon = IconFontSwing.buildIcon(FontAwesome.BACKWARD, 15);
      		volverBtn.setIcon(icon);
      		
      		
            

            opcionesenY.setLayout(new BoxLayout(opcionesenY, BoxLayout.Y_AXIS));
            opcionesenY.setAlignmentX(Component.CENTER_ALIGNMENT);
            

    		
      		//---------------------FIN NAVBAR-------------------------------------------
		
      		
      		
      		
        // Elementos de la interfaz
		
		labelCodigoValor.setEditable(false);
		labelCodigoValor.setFocusable(false);
		
        
		
        JLabel labelCodigoPartida = new JLabel("Código de la partida:");
        labelCodigoValor.setMaximumSize(new Dimension(200, 40));
        JPanel opcionesenX = new JPanel();
		opcionesenX.setLayout(new BoxLayout(opcionesenX, BoxLayout.X_AXIS));
		
		opcionesenX.add(labelCodigoPartida);
		opcionesenX.add(Box.createRigidArea(new Dimension(5, 0)));
		opcionesenX.add(labelCodigoValor);
		opcionesenX.add(Box.createRigidArea(new Dimension(115, 0)));
		opcionesenX.setBackground(colorTemp);
		
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
		privacidadRowPanel.setBackground(colorTemp);
		
		
		
		
		
		// Panel para la segunda fila de componentes
		JLabel labelModoJuego = new JLabel("Modo de juego:");
		BJcomboBox<String> comboModoJuego = new BJcomboBox<>(new String[]{"Clásico", "madChess"});
		comboModoJuego.setMaximumSize(new Dimension(200, 40));
		JPanel secondRowPanel = new JPanel();
        secondRowPanel.setLayout(new BoxLayout(secondRowPanel, BoxLayout.X_AXIS));
        
        secondRowPanel.add(labelModoJuego);
        secondRowPanel.add(Box.createRigidArea(new Dimension(35, 0)));
        secondRowPanel.add(comboModoJuego);
        secondRowPanel.add(Box.createRigidArea(new Dimension(115, 0)));
        secondRowPanel.setBackground(colorTemp);
        
        
        

        JLabel labelJugador1 = new JLabel("Jugador 1:");
        

        JLabel labelNombre1 = new JLabel("Nombre");
        labelNombre1.setVisible(false);
        
        JPanel thirdRowPanel = new JPanel();
        thirdRowPanel.setLayout(new BoxLayout(thirdRowPanel, BoxLayout.X_AXIS));
        
        thirdRowPanel.add(labelJugador1);
        thirdRowPanel.add(Box.createRigidArea(new Dimension(55, 0)));
        thirdRowPanel.add(botonUser1);
        thirdRowPanel.add(labelNombre1);
        thirdRowPanel.add(Box.createRigidArea(new Dimension(115, 0)));
        //labelNombre1.setVisible(false);
        thirdRowPanel.setBackground(colorTemp);
        
        
        JLabel labelJugador2 = new JLabel("Jugador 2:");
  
        JLabel labelNombre2 = new JLabel("Nombre");
        labelNombre2.setVisible(false);
        
        JPanel fourthRowPanel = new JPanel();
        fourthRowPanel.setLayout(new BoxLayout(fourthRowPanel, BoxLayout.X_AXIS));
        fourthRowPanel.add(labelJugador2);
        fourthRowPanel.add(Box.createRigidArea(new Dimension(55, 0)));
        fourthRowPanel.add(labelUser2);
        fourthRowPanel.add(labelNombre2);
        fourthRowPanel.add(Box.createRigidArea(new Dimension(115, 0)));
        fourthRowPanel.setBackground(colorTemp);
        
        botonIniciarPartida = new BButton("Iniciar Partida");
        JPanel fifthRowPanel = new JPanel();
        fifthRowPanel.setLayout(new BoxLayout(fifthRowPanel, BoxLayout.X_AXIS));
        fifthRowPanel.add(botonIniciarPartida);
        fifthRowPanel.setBackground(colorTemp);
        

        
        
     // Agregar los paneles al contenedor opcionesenY
        opcionesenY.add(Box.createVerticalGlue()); // Espacio en blanco arriba
        opcionesenY.add(opcionesenX);
        opcionesenY.add(Box.createRigidArea(new Dimension(0, 10))); // Espacio entre filas
        opcionesenY.add(privacidadRowPanel);
        opcionesenY.add(Box.createRigidArea(new Dimension(0, 10)));
        opcionesenY.add(secondRowPanel);
        opcionesenY.add(Box.createRigidArea(new Dimension(0, 20))); // Espacio entre filas
        opcionesenY.add(thirdRowPanel);
        opcionesenY.add(Box.createRigidArea(new Dimension(0, 10))); // Espacio entre filas
        opcionesenY.add(fourthRowPanel);
        opcionesenY.add(Box.createRigidArea(new Dimension(0, 30))); // Espacio entre filas
        opcionesenY.add(fifthRowPanel);
        opcionesenY.add(Box.createRigidArea(new Dimension(0, 5))); // Espacio entre botones
        opcionesenY.add(Box.createVerticalGlue()); // Espacio en blanco abajo
        
        
        
        opcionesenY.setBackground(colorTemp);

        // Agregar el navBar en la posición NORTH
        add(navBar, BorderLayout.NORTH);
        add(opcionesenY, BorderLayout.CENTER);
        
  
        
        

        botonIniciarPartida.addActionListener(e -> {
            // Lógica para iniciar la partida
        });
    }


	public void setUser1(Jugador user) {
		this.user1 = user;
		botonUser1.setText(user.getNombre());
		botonUser1.setFocusable(false);
	}
	
    public Servidor getServer() {
		return server;
	}


	public void setServer(Servidor server) {
		this.server = server;
	}


	public void setUser2(ClienteHandler cliente) {
		this.user2 = cliente.getUser();
		labelUser2.setText(user2.getNombre());
		
	}

}
