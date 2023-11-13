package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.border.Border;

import componentes.MButton;
import componentes.MScrollPane;
import componentes.userInfo;
import objetos.*;


public class Juego extends JPanel {
	private static final long serialVersionUID = 1L;
	protected JPanel panelJuego;
	protected JPanel panelControles;
	protected userInfo panelUsuario;
	protected userInfo panelUsuario2;
	
	
	protected JPanel panelChat;
	protected JTextArea areaChat;
	protected JTextField textfieldChat;
	protected JTextField textfieldUsuario;
	protected JLabel labelChat;
	protected JLabel labelEscribe;
	protected JPanel panelControlChat;
	protected JPanel panelDatosChat;
	protected MButton botonEnviar;
	protected MButton botonVolver;

	protected JPanel panelMensaje;

	protected JScrollPane scrollMovimientos;
	
	
	
	protected JPanel panelMovimentos;
	protected JTextArea areaMovimientos;
	protected JLabel labelMovimientos;
	
	protected Tablero tablero = new Tablero();
	protected Color colorFondo = new Color(16,16,16);
	
	protected boolean vacio = false;
	
	public Juego() {

	    // Panel principal con GridBagLayout
	    this.setLayout(new GridBagLayout());
	    GridBagConstraints gbc = new GridBagConstraints();

	    // Paneles
	    panelJuego = new JPanel();
	    panelControles = new JPanel(new GridLayout(2,1));
	    

	    /*
	     * COLORES y FONDOS
	     */
	    this.setBackground(colorFondo);
	    panelJuego.setBackground(colorFondo);
	    panelControles.setBackground(colorFondo);

	    
	    
	    // PanelJuego
	    gbc.gridx = 0;
	    gbc.gridy = 0;
	    gbc.weightx = 1.0; //En panel del juego va a ocupar todo lo que pueda
	    gbc.weighty = 1.0; // Para que ocupen todo lo que puedan en vertical
	    gbc.fill = GridBagConstraints.BOTH;
	    this.add(panelJuego, gbc);

	    // PanelControles
	    gbc.gridx = 1;
	    gbc.weightx = 0.3; // El espacio que va a ocupar el panelControles
	    this.add(panelControles, gbc);

	    
	    
	    // Configura el panelJuego con BoxLayout en la dirección vertical
	    panelJuego.setLayout(new BoxLayout(panelJuego, BoxLayout.Y_AXIS));

	    // Agrega algunos elementos a panelJuego (por ejemplo, botones o etiquetas)


	   panelUsuario = new userInfo();
	   panelUsuario2 = new userInfo();
	   panelJuego.add(panelUsuario);
	   panelJuego.add(tablero);
	   panelJuego.add(panelUsuario2);
	
	    
	    

	    
	    // panel controles, movimiento y chat
	    
	    //panel chat
	    panelMovimentos = new JPanel(new BorderLayout());
	    
	    panelChat = new JPanel( new BorderLayout());
	    panelControlChat = new JPanel(new GridLayout(2,1));
	    
	    labelChat = new JLabel("CHAT");
	    areaChat = new JTextArea(5,2);
	    areaChat.setLineWrap(true); 
	    areaChat.setWrapStyleWord(true); 
	    
	    textfieldChat = new JTextField(0);
	    MScrollPane scrollChat = new MScrollPane(areaChat);

	    panelDatosChat = new JPanel();
	    textfieldUsuario = new JTextField(0);
	    

	    
        botonVolver = new MButton("Volver");
	    
        
	    botonEnviar = new MButton("Enviar");

        // Para enviar el mensaje al presionar Enter
        textfieldChat.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    enviarMensaje(); //El metodo esta al final
                }
            }
        });
        
        botonVolver.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// Cerrar la ventana actual
		        JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(Juego.this);
		        currentFrame.dispose();
		        // Volver al menu principal de inicio
		        VentanaPrincipal v1 = new VentanaPrincipal();
			}
		});
        
        botonEnviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enviarMensaje();
            }
        });

	    
	    panelChat.add(labelChat, BorderLayout.NORTH);
	    panelChat.add(scrollChat, BorderLayout.CENTER);
	    
	   
	    panelMensaje = new JPanel(new BorderLayout());
	    

	 
	    panelMensaje.add(textfieldChat);
	    
	 
	    panelControlChat.add(panelMensaje);
	    
	    panelControlChat.add(botonEnviar);
	    
	  
	    panelChat.add(panelControlChat, BorderLayout.SOUTH);
	    

	    areaChat.setFont(new Font("Monospaced", Font.PLAIN, 14)); 
	    areaChat.setEditable(false);


	    
	    // panel movimiento de las piezas
	    
	    labelMovimientos = new JLabel("MOVIMIENTOS");

	    areaMovimientos = new JTextArea(5,2);
	    areaMovimientos.setLineWrap(true); 
	    areaMovimientos.setWrapStyleWord(true); 

	    areaMovimientos.setFont(new Font("Monospaced", Font.PLAIN, 14)); 
	    areaMovimientos.setEditable(false);
	    
	    scrollMovimientos = new JScrollPane(areaMovimientos);	
	    
	    
	    
	    //panelMovimentos.add(labelMovimientos, BorderLayout.NORTH);
	    panelMovimentos.add(botonVolver, BorderLayout.NORTH);
	    panelMovimentos.add(scrollMovimientos, BorderLayout.CENTER);
	    
	    
	    /*
	     * COLORES 
	     */
	    
	    areaChat.setBackground(colorFondo);
	    areaMovimientos.setBackground(colorFondo); 
	    
	    areaMovimientos.setForeground(Color.WHITE); //Color del texto
	    areaChat.setForeground(Color.WHITE);
	    
	    
	    
	    panelControles.add(panelMovimentos);
	    panelControles.add(panelChat);
	    
	    labelMovimientos.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));
	    labelChat.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));
	    panelControles.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	    
	    panelChat.setBackground(colorFondo);
	    panelMovimentos.setBackground(colorFondo);
	    labelMovimientos.setForeground(Color.white);
	    labelChat.setForeground(Color.white);
        
	    
	    Border bordeInput = BorderFactory.createLineBorder(new Color(36, 36, 36), 2, true);
	    textfieldChat.setBorder(bordeInput);
	    textfieldChat.setBackground(colorFondo);
	    textfieldChat.setForeground(Color.white);
	    
	    
	    scrollMovimientos.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
	    scrollChat.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
       

        tablero.recalcularTamanyo();
        this.addComponentListener(new ComponentAdapter() {
        	@Override
        	public void componentResized(ComponentEvent e) {
        		 tablero.recalcularTamanyo();
        	}
        	//FIXME: Al maximizar la ventana también tendría que funcionar
		});
        
        


    
        
        
        
    }
	
	private void enviarMensaje() { //Si se manda un mensaje sin poner texto, se pone el texto en rojo y luego blanco de nuevo, a parte del mensaje de aviso
        String texto = textfieldChat.getText();
        String usuario = "user1";

        if (texto.equals("")) {
            areaChat.setForeground(Color.RED);
            areaChat.append("Introduce un texto válido\n");

            // Restaura el color original (blanco) después de 2 segundos
            int tiempoEspera = 2000; // 2 segundos
            new Timer(tiempoEspera, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    areaChat.setForeground(Color.WHITE);
                }
            }).start();
        } else {
            areaChat.append("<"+usuario+"> "+ " "+ texto + "\n");
        }
        textfieldChat.setText("");
        textfieldUsuario.setText("");
    }
	
	public Tablero getTablero() {
		return tablero;
	}
	

	
    public Tablero getTableroDiv() {
		return tablero;
	}

	
    public void setNewMovimiento(String movimiento) {
    	areaMovimientos.append(movimiento+ "\n");
    }

	public void setInterfaz(int modoDeJuego) {
		if (modoDeJuego==0) {
			panelControles.remove(panelChat);
			panelControles.setLayout(new GridLayout(1,1));
			/*
			 * FIXME: Cuando limpiemos el código estaria guay que directamente haya un mapa con ejemplo modo 0 --> chat:false,nsq:true... 
			 * y esta función solo setea los valores a uno de ese mapa en vez de eliminarlo una vez ya cargado...
			 */
		}
		
	}
    

}




