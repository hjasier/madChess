package ventanas;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

import componentes.*;
import juego.VAR;
import juego.modoJuego;
import juego.partidaTipo;
import librerias.FontAwesome;
import librerias.IconFontSwing;
import objetos.*;
import utils.Configuracion;
import utils.Escalador;
import utils.Infos;
import utils.Session;


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
	protected JLabel labelEscribe;
	protected JPanel panelControlChat;
	protected JPanel panelDatosChat;
	protected JLabel configBtn;
	protected JLabel backBtn;
	protected JButton postMsgBtn;
	
	
	protected JPanel panelMensaje;

	protected MScrollPane scrollMovimientos;
	
	
	
	protected JPanel panelMovimentos;
	protected JTextPane areaMovimientos;
	protected JLabel labelMovimientos;
	
	protected Tablero tablero = new Tablero();
	protected Color colorFondo = Configuracion.BACKGROUND;
	
	protected JPanel panelBotones = new JPanel();
	protected JPanel panelContenedor = new JPanel();
	protected JPanel panelTabs = new JPanel();
	protected JPanel panelLabels = new JPanel();
	protected JLabel labelChat = new JLabel();
	protected JLabel labelBoost = new JLabel();
	protected JLabel labelVAR = new JLabel();
	
	protected JPanel panelAbajo = new JPanel();
	protected JPanel panelCardL = new JPanel();
	protected PanelBoost panelBoost = new PanelBoost();
	private varController panelVAR = new varController();
	protected StyledDocument styledAreaMovimientos;
	
	protected MScrollPane scrollChat;
	protected JPanel panelRetroceder;
	
	
	CardLayout cardLayout = new CardLayout();
	private JButton btnRetrocederMov =	new JButton("<-") ;
	private JButton btnAvanzarMov = new JButton("->") ;
	
	
	public Juego() {

	    // Panel principal con GridBagLayout
	    this.setLayout(new BorderLayout());
	    GridBagConstraints gbc = new GridBagConstraints();

	    // Paneles
	    panelJuego = new JPanel();
	    panelControles = new JPanel(new GridLayout(2,1));
	    panelAbajo.setLayout(new BorderLayout());

	    /*
	     * COLORES y FONDOS
	     */
	    this.setBackground(colorFondo);
	    panelJuego.setBackground(colorFondo);
	    panelControles.setBackground(colorFondo);

	    panelContenedor.setLayout(new BorderLayout());
	    panelContenedor.setBackground(colorFondo);

	    //Init icons
	    IconFontSwing.register(FontAwesome.getIconFont());
	    

	    panelContenedor.add(panelBotones, BorderLayout.NORTH);



	    panelContenedor.add(panelControles, BorderLayout.CENTER);
	    
		 
		 

	    this.add(panelJuego, BorderLayout.CENTER);

	    this.add(panelContenedor, BorderLayout.EAST);

	    
	    
	    // Configura el panelJuego con BoxLayout en la dirección vertical
	    panelJuego.setLayout(new BoxLayout(panelJuego, BoxLayout.Y_AXIS));


		panelUsuario = new userInfo();
		panelUsuario2 = new userInfo();
		
		panelJuego.add(panelUsuario);
		panelJuego.add(tablero);
		panelJuego.add(panelUsuario2);
		
	    
	    Icon backIcon = IconFontSwing.buildIcon(FontAwesome.CHEVRON_CIRCLE_LEFT, Escalador.escalar(20), Color.white);
	    Icon backIconHover = IconFontSwing.buildIcon(FontAwesome.CHEVRON_CIRCLE_LEFT, Escalador.escalar(20), Configuracion.HOVER);
	    
	    Icon configIcon = IconFontSwing.buildIcon(FontAwesome.COG, Escalador.escalar(20), Color.white);
	    Icon configIconHover = IconFontSwing.buildIcon(FontAwesome.COG, Escalador.escalar(20), Configuracion.HOVER);
	    
	    
		backBtn = new JLabel(backIcon);
		configBtn = new JLabel(configIcon);
		
	
		configBtn.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10)); // margenes

	    
	    panelBotones.setBackground(colorFondo);
	    panelBotones.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 10));
	    
	    panelBotones.add(backBtn);
	    panelBotones.add(configBtn);
	    
	    
	    panelMovimentos = new JPanel(new BorderLayout());
	    
	    panelChat = new JPanel( new BorderLayout());
	    panelControlChat = new JPanel(new GridLayout(2,1));
	    
	    areaChat = new JTextArea(5,2);
	    areaChat.setLineWrap(true); 
	    areaChat.setWrapStyleWord(true); 
	    
	    textfieldChat = new JTextField(0);
	    scrollChat = new MScrollPane(areaChat);

	    panelDatosChat = new JPanel();
	    textfieldUsuario = new JTextField(0);
	    


	    postMsgBtn = new JButton("Enviar");
	    
	    panelChat.add(labelChat, BorderLayout.NORTH);
	    
	    panelChat.add(scrollChat, BorderLayout.CENTER);
	    
	   
	    panelMensaje = new JPanel(new BorderLayout());
	    

	 
	    panelMensaje.add(textfieldChat);
	    
	 
	    panelControlChat.add(panelMensaje);
	    
	    panelControlChat.add(postMsgBtn);
	    
	  
	    panelChat.add(panelControlChat, BorderLayout.SOUTH);
	    

	    areaChat.setFont(new Font("Monospaced", Font.PLAIN, Escalador.escalar(14))); 
	    areaChat.setEditable(false);


	    
	    //panel tabs
	    
	    panelTabs.setLayout(new BoxLayout(panelTabs, BoxLayout.X_AXIS));
	    panelLabels.setLayout(new BoxLayout(panelLabels, BoxLayout.X_AXIS));
	    
	    labelChat = new JLabel();
	    
	    
	    Image imgChatResc = new ImageIcon(getClass().getResource("/srcmedia/labelChatR.png")).getImage().getScaledInstance(Escalador.escalar(68), Escalador.escalar(33), Image.SCALE_SMOOTH);
        
	    labelChat.setIcon(new ImageIcon(imgChatResc));
	    

	    
	    labelChat.setFont(labelChat.getFont().deriveFont(Font.BOLD, Escalador.escalar(12)));
	    labelChat.setCursor(new Cursor(Cursor.HAND_CURSOR));
	    
	    labelBoost = new JLabel();
	    Image imgBoostResc = new ImageIcon(getClass().getResource("/srcmedia/labelBoostsR.png")).getImage().getScaledInstance(Escalador.escalar(89), Escalador.escalar(33), Image.SCALE_SMOOTH);
	    labelBoost.setIcon(new ImageIcon(imgBoostResc));
        
	    
	    
	    labelBoost.setFont(labelBoost.getFont().deriveFont(Font.BOLD, Escalador.escalar(12)));
	    labelBoost.setCursor(new Cursor(Cursor.HAND_CURSOR));
	    
	    
	    labelVAR = new JLabel();
	    Image imgLabelBar = new ImageIcon(getClass().getResource("/srcmedia/varTab.png")).getImage().getScaledInstance(Escalador.escalar(68), Escalador.escalar(33), Image.SCALE_SMOOTH);
	    labelVAR.setIcon(new ImageIcon(imgLabelBar));
	    
	    panelLabels.add(labelChat);
	    panelLabels.add(Box.createRigidArea(new java.awt.Dimension(15,0)));
	    panelLabels.add(labelBoost);
	    panelLabels.setBackground(colorFondo);
	    
	    panelTabs.add(panelLabels);
	    panelTabs.setBackground(colorFondo);
	    // panel movimiento de las piezas
	    
	    labelMovimientos = new JLabel();
	  
	    ImageIcon imgFoto = new ImageIcon(getClass().getResource("/srcmedia/labelMovimientosR.png"));
	    Image imagenEscalada = imgFoto.getImage().getScaledInstance(Escalador.escalar(110), Escalador.escalar(33), Image.SCALE_SMOOTH);
        
	    labelMovimientos.setIcon(new ImageIcon(imagenEscalada));

	    areaMovimientos = new JTextPane();
	    areaMovimientos.setContentType("text/html");


	    //areaMovimientos.setFont(new Font("Monospaced", Font.PLAIN, Escalador.escalar(14))); 
	    areaMovimientos.setEditable(false);
	    styledAreaMovimientos = areaMovimientos.getStyledDocument();

	    areaMovimientos.setPreferredSize(new Dimension(Escalador.escalar(120),Integer.MAX_VALUE));
	    
	    
	    
	    scrollMovimientos = new MScrollPane(areaMovimientos);	
	    
	    
	    
	    JLabel labelRetrocederMov = new JLabel();
	    Image imgRetrocederMov = new ImageIcon(getClass().getResource("/srcmedia/labelBoostsR.png")).getImage().getScaledInstance(Escalador.escalar(89), Escalador.escalar(33), Image.SCALE_SMOOTH);
		labelRetrocederMov.setIcon(new ImageIcon(imgBoostResc));
	    
		
		JPanel movimientosTabs = new JPanel();
		movimientosTabs.setLayout(null);
		movimientosTabs.setBackground(Configuracion.BACKGROUND);
		
		panelRetroceder = new JPanel();
		panelRetroceder.setBackground(Configuracion.BACKGROUND);
		
		panelRetroceder.add(btnRetrocederMov);
		panelRetroceder.add(btnAvanzarMov);
		
		movimientosTabs.add(labelMovimientos);
		//movimientosTabs.add(panelRetroceder);
		
		labelMovimientos.setBounds(0, Escalador.escalar(0), Escalador.escalar(130), Escalador.escalar(50));
		panelRetroceder.setBounds(Escalador.escalar(180), Escalador.escalar(0), Escalador.escalar(200), Escalador.escalar(50));
		
		movimientosTabs.setPreferredSize(new Dimension(Escalador.escalar(290),Escalador.escalar(50)));
	    
	    
	    panelMovimentos.add(movimientosTabs, BorderLayout.NORTH);
	    
	    panelMovimentos.add(scrollMovimientos, BorderLayout.CENTER);
	    
	    
	    /*
	     * COLORES 
	     */
	    
	    areaChat.setBackground(colorFondo);
	    areaMovimientos.setBackground(colorFondo); 
	    
	    areaMovimientos.setForeground(Color.WHITE); //Color del texto
	    areaChat.setForeground(Color.WHITE);
	    

	    
	    
	    panelCardL.setLayout(cardLayout);
	    panelCardL.add(panelChat, "CHAT");
	    panelCardL.add(panelBoost, "BOOST");
	    panelCardL.add(panelVAR, "VAR");
	    
	    
	    panelAbajo.add(panelTabs, BorderLayout.NORTH);
	    panelAbajo.add(panelCardL, BorderLayout.CENTER);
	    
	    
	    
	    panelControles.add(panelMovimentos);
	    panelControles.add(panelAbajo);

	    
	    panelBotones.setPreferredSize(new Dimension(10,50 ));
	    panelBotones.setBorder(BorderFactory.createEmptyBorder(0,0,0,15));

	    labelMovimientos.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));
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
		});
        
        
        
        backBtn.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseEntered(MouseEvent e) {
        		// TODO Auto-generated method stub
        		
        		super.mouseEntered(e);
        		backBtn.setIcon(backIconHover);
        		backBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        	}
        	
        	@Override
        	public void mouseExited(MouseEvent e) {
        		// TODO Auto-generated method stub
        		super.mouseExited(e);
        		backBtn.setIcon(backIcon);
        	}
		});
        
        configBtn.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseEntered(MouseEvent e) {
        		// TODO Auto-generated method stub
        		
        		super.mouseEntered(e);
        		configBtn.setIcon(configIconHover);
        		configBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        	}
        	
        	@Override
        	public void mouseExited(MouseEvent e) {
        		// TODO Auto-generated method stub
        		super.mouseExited(e);
        		configBtn.setIcon(configIcon);
        	}
        	
        	@Override
			public void mouseClicked(MouseEvent e) {
				Session.getVentana().showPanel(Paneles.CONFIGMENU);
			}
		});

    
     // Envia el mensaje al presionar Enter
        textfieldChat.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    enviarMensaje();
                }
            }
        });
        
        
        postMsgBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enviarMensaje();
            }
        });
        
        
        labelChat.addMouseListener(new MouseAdapter() {

        	@Override
        	public void mouseClicked(MouseEvent e) {
        		
        		clickChat();
        	    
        	}
        	
        });
        
        labelBoost.addMouseListener(new MouseAdapter() {

        	@Override
        	public void mouseClicked(MouseEvent e) {
        		
        		clickBoost();
        	}
        });
        
        
        btnRetrocederMov.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		VAR.avanzarMov();
        	}
        });
       
        
        btnAvanzarMov.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		VAR.retrodecerMov();
        	}
        });
        
        
        
        
    }
	
	
	protected void clickChat() {
		cardLayout.show(panelCardL,"CHAT");
		
		Image imgChatResc = new ImageIcon(getClass().getResource("/srcmedia/labelChatSelectedR.png")).getImage().getScaledInstance(Escalador.escalar(68), Escalador.escalar(33), Image.SCALE_SMOOTH);
		Image imgBoostResc = new ImageIcon(getClass().getResource("/srcmedia/labelBoostsR.png")).getImage().getScaledInstance(Escalador.escalar(89), Escalador.escalar(33), Image.SCALE_SMOOTH);
        
		
		
		labelBoost.setIcon(new ImageIcon(imgBoostResc));
		labelChat.setIcon(new ImageIcon(imgChatResc));
		
		
	}


	protected void clickBoost() {
		cardLayout.show(panelCardL,"BOOST");
		
		Image imgChatResc = new ImageIcon(getClass().getResource("/srcmedia/labelChatR.png")).getImage().getScaledInstance(Escalador.escalar(68), Escalador.escalar(33), Image.SCALE_SMOOTH);
		Image imgBoostResc = new ImageIcon(getClass().getResource("/srcmedia/labelBoostsSelectedR.png")).getImage().getScaledInstance(Escalador.escalar(89), Escalador.escalar(33), Image.SCALE_SMOOTH);
        
		
		
		labelBoost.setIcon(new ImageIcon(imgBoostResc));
		labelChat.setIcon(new ImageIcon(imgChatResc));
	}


	private void enviarMensaje() { 
        String texto = textfieldChat.getText();        
        if (texto.equals("")) {
            areaChat.setForeground(Color.RED);
            areaChat.append("Introduce un texto válido\n");

            int tiempoEspera = 2000; // 2 segundos
            new Timer(tiempoEspera, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    areaChat.setForeground(Color.WHITE);
                }
            }).start();
            return;
        } 
        
        else {
            areaChat.append("『"+Session.getCurrentUser().getUsername()+"』 "+ " "+ texto + "\n");
            postMsg(texto);
        }
        
        textfieldChat.setText("");
        textfieldUsuario.setText("");
    }
	
	
	
	
	
	private void postMsg(String texto) {
		try {
			Session.getCtsConnection().postChatMsg(texto);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public Tablero getTablero() {
		return tablero;
	}
	

	
    public Tablero getTableroDiv() {
		return tablero;
	}

	
    
    
    
    public void printMovimiento(String infoTurno) {
        HTMLEditorKit kit = new HTMLEditorKit();
        HTMLDocument doc = (HTMLDocument) areaMovimientos.getDocument(); // Obtén el documento existente

        try {
            // Inserta un salto de línea antes de agregar el nuevo contenido
            if (doc.getLength() > 0) {
                kit.insertHTML(doc, doc.getLength(), "<br>", 0, 0, null);
            }

            // Ahora, agrega el nuevo contenido
            kit.insertHTML(doc, doc.getLength(), infoTurno, 0, 0, null);
        } catch (BadLocationException | IOException e) {
            e.printStackTrace();
        }
        
        areaMovimientos.setCaretPosition(areaMovimientos.getDocument().getLength());
    }
    
    
	public void setInterfaz() {
		if (Configuracion.DEBUG_MODE) {return;}
		
		modoJuego modoDeJuego = Session.getDatosPartida().getModoDeJuego();
		partidaTipo tipoPartida = Session.getDatosPartida().getTipoPartida();
		
		
		if (modoDeJuego == modoJuego.LOCAL) { //LOCAL
			panelLabels.remove(labelChat);
			clickBoost();
		}
		else { //ONLINE
			clickChat();
		}
		
		if (tipoPartida == partidaTipo.CLASICA) { //CLÁSICO
			panelLabels.remove(labelBoost);
		}
		else {
			clickBoost();
		}
		
		if (tipoPartida==partidaTipo.CLASICA && modoDeJuego == modoJuego.LOCAL) { 
			panelControles.remove(panelAbajo);
			panelControles.setLayout(new GridLayout(1,1));
		}
		

		
		
		
	}
	
	
	public void modoTempPonerbtns() {
		cardLayout.show(panelCardL,"VAR");
		panelLabels.remove(labelBoost);
		panelLabels.remove(labelChat);
		
		panelLabels.add(labelVAR);
	}

	public JLabel getBotonVolver() {
		return backBtn;
	}
    
	
	public userInfo getPanelUsuario() {
		return panelUsuario;
	}

	public userInfo getPanelUsuario2() {
		return panelUsuario2;
	}


	public void addChatMsg(String nombre, String msg) {
		areaChat.append(Infos.SEP+nombre+Infos.SEP2+ " "+ msg + "\n");
	}
	
	public void initWindow() {
		if (Configuracion.DEBUG_MODE) {
			printMovimiento("<b color='yellow'> ------------------------------------ <br> MODO DEBUG: ACTIVADO <br> ------------------------------------  </b>");

        }
        setInterfaz();
        
	}


	public void resetTextAreas() {
		areaMovimientos.setText("");
		areaChat.setText("");
	}


	public varController getPanelVAR() {
		return panelVAR;
	}


	public PanelBoost getPanelBoost() {
		return panelBoost;
	}

		
	

	


}




