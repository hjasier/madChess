package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import objetos.*;


public class Juego extends JPanel {
	private static final long serialVersionUID = 1L;
	protected JPanel panelJuego;
	protected JPanel panelControles;
	
	protected JLabel labelUsuario1;
	protected JLabel labelUsuario2;
	
	
	protected JPanel panelChat;
	protected JTextArea areaChat;
	protected JTextField textfieldChat;
	protected JTextField textfieldUsuario;
	protected JLabel labelChat;
	protected JLabel labelEscribe;
	protected JButton botonEnviar;
	protected JPanel panelControlChat;
	protected JPanel panelDatosChat;
	protected JPanel panelUsuario;
	protected JPanel panelMensaje;
	protected JLabel labelUsuario;
	protected JScrollPane scrollMovimientos;
	
	protected JComboBox<String> comboUsuarios;
	
	
	protected JPanel panelMovimentos;
	protected JTextArea areaMovimientos;
	protected JLabel labelMovimientos;
	
	protected Tablero tablero = new Tablero();
	protected Color colorFondo = new Color(16,16,16);
	
	public Juego() {

	    // Panel principal con GridBagLayout
	    this.setLayout(new GridBagLayout());
	    GridBagConstraints gbc = new GridBagConstraints();

	    // Paneles
	    JPanel panelJuego = new JPanel();
	    JPanel panelControles = new JPanel(new GridLayout(2,1));

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


	   labelUsuario1 = new JLabel("Usuario 1");
	   labelUsuario2 = new JLabel("Usuario 2");
	    panelJuego.add(labelUsuario1);
	    panelJuego.add(tablero);
	    panelJuego.add(labelUsuario2);
	
	    

	    
	    // panel controles, movimiento y chat
	    
	    //panel chat
	    panelMovimentos = new JPanel(new BorderLayout());
	    
	    panelChat = new JPanel( new BorderLayout());
	    panelControlChat = new JPanel(new GridLayout(2,1));
	    
	    labelChat = new JLabel("CHAT");
	    areaChat = new JTextArea(5,2);
	    textfieldChat = new JTextField(0);
	    JScrollPane scrollChat = new JScrollPane(areaChat);
	    labelUsuario = new JLabel("Usuario:");
	    labelEscribe = new JLabel("Mensaje:");
	    panelDatosChat = new JPanel( new GridLayout(1,2));
	    textfieldUsuario = new JTextField(0);
	    
	    comboUsuarios = new JComboBox<String>();
	    
	    String contenidoUsuario1 = labelUsuario1.getText();
        String contenidoUsuario2 = labelUsuario2.getText();

        // Crear un array y agregar los contenidos de los label
        String[] contenidoUsuarios = {contenidoUsuario1, contenidoUsuario2};
	    
	    comboUsuarios = new JComboBox<String>(contenidoUsuarios);
	    
	    
	    botonEnviar = new JButton("Enviar");
	    botonEnviar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String texto = textfieldChat.getText();
				String usuario = (String) comboUsuarios.getSelectedItem();
                areaChat.append(usuario + ": " + texto + "\n"); // Agrega el texto al JTextArea
                textfieldChat.setText(""); // Limpia el JTextField después de agregar el texto
                textfieldUsuario.setText("");
				
			}
		});
	    
	    panelChat.add(labelChat, BorderLayout.NORTH);
	    panelChat.add(scrollChat, BorderLayout.CENTER);
	    
	    panelUsuario = new JPanel(new BorderLayout());
	    panelMensaje = new JPanel(new BorderLayout());
	    
	    panelUsuario.add(comboUsuarios);

	    panelMensaje.add(labelEscribe, BorderLayout.WEST);
	    panelMensaje.add(textfieldChat , BorderLayout.CENTER);
	    
	    panelDatosChat.add(panelUsuario);
	    panelDatosChat.add(panelMensaje);
	    
	    
	    
	    panelControlChat.add(panelDatosChat);
	    
	    panelControlChat.add(botonEnviar);
	    
	    panelChat.add(panelControlChat, BorderLayout.SOUTH);
	    

	    areaChat.setFont(new Font("Monospaced", Font.PLAIN, 14)); 
	    areaChat.setEditable(false);
	    
	    
	    // panel movimiento de las piezas
	    
	    labelMovimientos = new JLabel("MOVIMIENTOS");

	    areaMovimientos = new JTextArea(5,2);
	    areaMovimientos.setLineWrap(true); // Habilitar la envoltura automática de texto
	    areaMovimientos.setWrapStyleWord(true); 

	    areaMovimientos.setFont(new Font("Monospaced", Font.PLAIN, 14)); 
	    areaMovimientos.setEditable(false);
	    
	    scrollMovimientos = new JScrollPane(areaMovimientos);	
	    
	    
	    panelMovimentos.add(labelMovimientos, BorderLayout.NORTH);
	    panelMovimentos.add(scrollMovimientos, BorderLayout.CENTER);
	    
	    
	    /*
	     * COLORES 
	     */
	    
	    areaChat.setBackground(Color.BLACK); 
	    areaChat.setForeground(Color.WHITE); 
	    areaMovimientos.setBackground(Color.BLACK); 
	    areaMovimientos.setForeground(Color.WHITE); 
	    
	    
	    
	    panelControles.add(panelMovimentos);
	    panelControles.add(panelChat);
        
        
	    

       

        tablero.recalcularTamanyo();
        this.addComponentListener(new ComponentAdapter() {
        	@Override
        	public void componentResized(ComponentEvent e) {
        		 tablero.recalcularTamanyo();
        	}
        	//FIXME: Al maximizar la ventana también tendría que funcionar
		});
        
        


    
        
        
        
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
    

}




