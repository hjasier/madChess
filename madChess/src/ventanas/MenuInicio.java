package ventanas;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import componentes.RButton;
import componentes.navBar;
import juego.DatosPartida;
import librerias.FontAwesome;
import librerias.IconFontSwing;
import utils.Configuracion;
import utils.Escalador;
import utils.Session;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class MenuInicio extends JPanel {
    RButton loginBtn = new RButton("Login");

    public MenuInicio() {
        setBackground(Configuracion.BACKGROUND);
        setLayout(new BorderLayout());
        
      //--------------------- NAVBAR-------------------------------------------
  		Icon icon = IconFontSwing.buildIcon(FontAwesome.USER_CIRCLE, Escalador.escalarF(15));
		loginBtn.setIcon(icon);
  		
		JPanel navBarContainer = new JPanel(new FlowLayout());
		navBarContainer.add(new navBar(loginBtn));
        navBarContainer.setBackground(Configuracion.BACKGROUND);
      
  		
        this.add(navBarContainer, BorderLayout.NORTH);
        //--------------------- NAVBAR-------------------------------------------
        
        

        double escala = 0.7;

        JPanel panel = new JPanel();
        panel.setBackground(Configuracion.BACKGROUND);
        panel.setBounds(0, 0, Escalador.escalar(1000), Escalador.escalar(641));
        panel.setPreferredSize(Escalador.newDimension(1000,641));
        panel.setLayout(null);
        
        
        JPanel centeredPanel = new JPanel(new FlowLayout());
        
        centeredPanel.setBackground(Configuracion.BACKGROUND);

        centeredPanel.add(panel);


        // Agrega el nuevo panel centrado al panel principal usando BorderLayout
        add(centeredPanel, BorderLayout.CENTER);
        
        
        
        
        
        
        

        //-----------------SinglePlayer-----------------
        JPanel singlePanel = new JPanel();
        singlePanel.setBackground(Configuracion.BACKGROUND);
        singlePanel.setBounds(Escalador.escalar(82), Escalador.escalar(64), Escalador.escalar(310), Escalador.escalar(260));

        // SinglePLayerBack
        ImageIcon singleplayerft = new ImageIcon(getClass().getResource("../srcmedia/singleplayer.png"));
        Image imagenEscalada = singleplayerft.getImage().getScaledInstance((int) (Escalador.escalar(443) * escala), (int) (Escalador.escalar(362) * escala), Image.SCALE_SMOOTH);
        JLabel singleplayerBack = new JLabel();
        singleplayerBack.setIcon(new ImageIcon(imagenEscalada));
        singleplayerBack.setBounds(0, Escalador.escalar(5), Escalador.escalar(310), Escalador.escalar(253));

        // SinglePlayerLabel
        ImageIcon singleplayerLabel = new ImageIcon(getClass().getResource("../srcmedia/singleplayerLabel.png"));
        Image imgEscaladaSingleLabel = singleplayerLabel.getImage().getScaledInstance((int) (Escalador.escalar(369) * escala), (int) (Escalador.escalar(236) * escala), Image.SCALE_SMOOTH);
        JLabel singlePlayerLabel = new JLabel();
        singlePlayerLabel.setIcon(new ImageIcon(imgEscaladaSingleLabel));
        singlePlayerLabel.setBounds(Escalador.escalar(30), Escalador.escalar(70), Escalador.escalar(252), Escalador.escalar(165));

        singlePanel.setLayout(null);
        singlePanel.add(singlePlayerLabel);
        singlePanel.add(singleplayerBack);
        //-----------------FIN SinglePlayer-----------------
        
        
        
        
        
        
        
        //-----------------Lista de Partidas-----------------
        JPanel partidasPanel = new JPanel();
        partidasPanel.setBackground(Configuracion.BACKGROUND);
        
       
        partidasPanel.setBounds(Escalador.escalar(80), Escalador.escalar(362), (int) (Escalador.escalar(443) * escala), (int) (Escalador.escalar(293) * escala));


        // Partidas ONline Back
        ImageIcon partidasOnlineFt = new ImageIcon(getClass().getResource("../srcmedia/partidasonline.png"));
        Image imgPOEscalada = partidasOnlineFt.getImage().getScaledInstance((int) (Escalador.escalar(443) * escala), (int) (Escalador.escalar(293) * escala), Image.SCALE_SMOOTH);
        JLabel partidasonlineBack = new JLabel();
        partidasonlineBack.setIcon(new ImageIcon(imgPOEscalada));
        partidasonlineBack.setBounds(0, 0, (int) (Escalador.escalar(443) * escala), (int) (Escalador.escalar(293) * escala));

        // Partidas Online Label
        ImageIcon partidasOnlineLabel = new ImageIcon(getClass().getResource("../srcmedia/partidasonlineLabel.png"));
        Image imgPartidaOnlineLabel = partidasOnlineLabel.getImage().getScaledInstance((int) (Escalador.escalar(360) * escala), (int) (Escalador.escalar(201) * escala), Image.SCALE_SMOOTH);
        JLabel partidaOnlineLabel = new JLabel();
        partidaOnlineLabel.setIcon(new ImageIcon(imgPartidaOnlineLabel));
        partidaOnlineLabel.setBounds(Escalador.escalar(30), Escalador.escalar(53), (int) (Escalador.escalar(363) * escala), (int) (Escalador.escalar(201) * escala));
        
        partidasPanel.setLayout(null);
        partidasPanel.add(partidaOnlineLabel);
        partidasPanel.add(partidasonlineBack);
        //-----------------FIN SinglePlayer-----------------
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        

        //-----------------Multiplayer-----------------
        JPanel multiPanel = new JPanel();
        multiPanel.setBackground(Configuracion.BACKGROUND);
        multiPanel.setBounds(Escalador.escalar(501), Escalador.escalar(64), Escalador.escalar(407), Escalador.escalar(263));

        // MultiplayerBack
        ImageIcon multiplayer = new ImageIcon(getClass().getResource("../srcmedia/multiplayer.png"));
        Image imgEscaladaMulti = multiplayer.getImage().getScaledInstance((int) (Escalador.escalar(568) * escala), (int) (Escalador.escalar(362) * escala), Image.SCALE_SMOOTH);
        JLabel multiplayerBack = new JLabel();
        multiplayerBack.setBounds(Escalador.escalar(5), Escalador.escalar(5), Escalador.escalar(397), Escalador.escalar(253));
        multiplayerBack.setIcon(new ImageIcon(imgEscaladaMulti));

        // MultiplayerLabel 1
        ImageIcon multiplayerLabel = new ImageIcon(getClass().getResource("../srcmedia/multiplayerLabel1.png"));
        Image imgEscaladaMultiLabel = multiplayerLabel.getImage().getScaledInstance((int) (Escalador.escalar(162) * escala), (int) (Escalador.escalar(215) * escala), Image.SCALE_SMOOTH);
        JLabel multiplayerLabelBack = new JLabel();
        multiplayerLabelBack.setIcon(new ImageIcon(imgEscaladaMultiLabel));
        multiplayerLabelBack.setBounds(Escalador.escalar(25), Escalador.escalar(84), Escalador.escalar(113), Escalador.escalar(150));

        // MultiplayerLabel 2
        ImageIcon multiplayerLabel2 = new ImageIcon(getClass().getResource("../srcmedia/multiplayerLabel2.png"));
        Image imgEscaladaMultiLabel2 = multiplayerLabel2.getImage().getScaledInstance((int) (Escalador.escalar(162) * escala), (int) (Escalador.escalar(215) * escala), Image.SCALE_SMOOTH);
        JLabel multiplayerLabelBack2 = new JLabel();
        multiplayerLabelBack2.setIcon(new ImageIcon(imgEscaladaMultiLabel2));
        multiplayerLabelBack2.setBounds(Escalador.escalar(147), Escalador.escalar(84), Escalador.escalar(113), Escalador.escalar(150));

        // MultiplayerLabel 3
        ImageIcon multiplayerLabel3 = new ImageIcon(getClass().getResource("../srcmedia/multiplayerLabel3.png"));
        Image imgEscaladaMultiLabel3 = multiplayerLabel3.getImage().getScaledInstance((int) (Escalador.escalar(162) * escala), (int) (Escalador.escalar(215) * escala), Image.SCALE_SMOOTH);
        JLabel multiplayerLabelBack3 = new JLabel();
        multiplayerLabelBack3.setIcon(new ImageIcon(imgEscaladaMultiLabel3));
        multiplayerLabelBack3.setBounds(Escalador.escalar(268), Escalador.escalar(84), Escalador.escalar(113), Escalador.escalar(150));

        multiPanel.setLayout(null);
        multiPanel.add(multiplayerLabelBack);
        multiPanel.add(multiplayerLabelBack2);
        multiPanel.add(multiplayerLabelBack3);
        multiPanel.add(multiplayerBack);
        //-----------------FIN Multiplayer-----------------

        
        
        
        
        //-----------------LocalPlay----------------------
        JPanel localPanel = new JPanel();
        localPanel.setBackground(Configuracion.BACKGROUND);
        localPanel.setBounds(Escalador.escalar(501), Escalador.escalar(362), Escalador.escalar(419), Escalador.escalar(219));

        // LocalPlayBack
        ImageIcon localplay = new ImageIcon(getClass().getResource("../srcmedia/localplay.png"));
        Image imgEscaladaLocal = localplay.getImage().getScaledInstance((int) (Escalador.escalar(568) * escala), (int) (Escalador.escalar(293) * escala), Image.SCALE_SMOOTH);
        JLabel localBack = new JLabel();
        localBack.setIcon(new ImageIcon(imgEscaladaLocal));
        localBack.setBounds(Escalador.escalar(10), Escalador.escalar(10), (int) (Escalador.escalar(568) * escala), (int) (Escalador.escalar(293) * escala));

        // LocalPlayLabel 1
        ImageIcon localplayLabel = new ImageIcon(getClass().getResource("../srcmedia/localplayLabel1.png"));
        Image imgEscaladaLocalLabel = localplayLabel.getImage().getScaledInstance((int) (Escalador.escalar(154) * escala), (int) (Escalador.escalar(174) * escala), Image.SCALE_SMOOTH);
        JLabel localplayLabelBack = new JLabel();
        localplayLabelBack.setIcon(new ImageIcon(imgEscaladaLocalLabel));
        localplayLabelBack.setBounds(Escalador.escalar(43), Escalador.escalar(78), (int) (Escalador.escalar(154) * escala), (int) (Escalador.escalar(174) * escala));

        // LocalPlayLabel 2
        ImageIcon localplayLabel2 = new ImageIcon(getClass().getResource("../srcmedia/localplayLabel2.png"));
        Image imgEscaladaLocalLabel2 = localplayLabel2.getImage().getScaledInstance((int) (Escalador.escalar(154) * escala), (int) (Escalador.escalar(174) * escala), Image.SCALE_SMOOTH);
        JLabel localplayLabelBack2 = new JLabel();
        localplayLabelBack2.setIcon(new ImageIcon(imgEscaladaLocalLabel2));
        localplayLabelBack2.setBounds(Escalador.escalar(160), Escalador.escalar(78), (int) (Escalador.escalar(154) * escala), (int) (Escalador.escalar(174) * escala));

        // LocalPlayLabel 3
        ImageIcon localplayLabel3 = new ImageIcon(getClass().getResource("../srcmedia/localplayLabel3.png"));
        Image imgEscaladaLocalLabel3 = localplayLabel3.getImage().getScaledInstance((int) (Escalador.escalar(154) * escala), (int) (Escalador.escalar(174) * escala), Image.SCALE_SMOOTH);
        JLabel localplayLabelBack3 = new JLabel();
        localplayLabelBack3.setIcon(new ImageIcon(imgEscaladaLocalLabel3));
        localplayLabelBack3.setBounds(Escalador.escalar(277), Escalador.escalar(78), (int) (Escalador.escalar(154) * escala), (int) (Escalador.escalar(174) * escala));

        localPanel.setLayout(null);
        localPanel.add(localplayLabelBack);
        localPanel.add(localplayLabelBack2);
        localPanel.add(localplayLabelBack3);
        localPanel.add(localBack);
        

        //-----------------FIN LocalPlay-----------------
        
        
        
        
        
        panel.add(singlePanel);
        panel.add(partidasPanel);
        panel.add(localPanel);
        panel.add(multiPanel);
        
        
        
      //----------------- FIN PanelCentral -----------------
        
        
        
      //-----------------Hovers-----------------
        agregarHoverEffect(multiplayerLabelBack, escala, "../srcmedia/multiplayerLabel1.png");
        agregarHoverEffect(multiplayerLabelBack2, escala, "../srcmedia/multiplayerLabel2.png");
        agregarHoverEffect(multiplayerLabelBack3, escala, "../srcmedia/multiplayerLabel3.png");
        
        agregarHoverEffect(localplayLabelBack, escala, "../srcmedia/localplayLabel1.png");
        agregarHoverEffect(localplayLabelBack2, escala, "../srcmedia/localplayLabel2.png");
        agregarHoverEffect(localplayLabelBack3, escala, "../srcmedia/localplayLabel3.png");
        
        agregarHoverEffect(singlePlayerLabel, escala, "../srcmedia/singleplayerLabel.png");
        
        agregarHoverEffect(partidaOnlineLabel, escala, "../srcmedia/partidasonlineLabel.png");
        //-----------------FIN Hovers-----------------

        
        //-----------------Clicks-----------------
        singlePlayerLabel.addMouseListener(new MouseAdapter() {
        	
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		VentanaPrincipal ventanaPrincip = Session.getVentana();
            	DatosPartida datos = new DatosPartida("local");
            	Session.setDatosPartida(datos);
            	ventanaPrincip.getPanelConfLocal().setDatosPartida(datos);
            	ventanaPrincip.getCardLayout().show(ventanaPrincip.getPanelPrincipal(), "CONFLOCAL");
        	}

        });
        
        multiplayerLabelBack.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent e) {
            	VentanaPrincipal ventanaPrincip = Session.getVentana();
            	System.out.println("-----CONSOLA CREADOR------");
            	if (Session.getCurrentUser()!=null) {
            		
            		startServerCnx();
                	try {
    					Session.getCtsConnection().createGame();
    				} catch (IOException e1) {
    					// TODO Auto-generated catch block
    					e1.printStackTrace();
    				}
                	
                	ventanaPrincip.getCardLayout().show(ventanaPrincip.getPanelPrincipal(), "CONFONLINE");
                    
            	}
            	else {
            		ventanaPrincip.getCardLayout().show(ventanaPrincip.getPanelPrincipal(), "LOGIN");
            	}   
            }
        });
        
        partidaOnlineLabel.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent e) {
            	VentanaPrincipal ventanaPrincip = Session.getVentana();
            	System.out.println("-----CONSOLA JOINER------");
            	
    			if (Session.getCurrentUser()!=null) {
                	startServerCnx();
                	try {
    					Session.getCtsConnection().getListaPartidas();
    				} catch (IOException e1) {
    					e1.printStackTrace();
    				}
                	
                	ventanaPrincip.getCardLayout().show(ventanaPrincip.getPanelPrincipal(), "LISTAPARTIDAS");
                    
            	}
            	else {
            		ventanaPrincip.getCardLayout().show(ventanaPrincip.getPanelPrincipal(), "LOGIN");
            	}  
            }
        });
        
        //-----------------FIN Clicks-----------------
        


       
    
    

}




protected void startServerCnx() {
	try {
		Session.startServerCnx();
	} catch (ClassNotFoundException | IOException e1) {
		System.out.println("Error al conectarse con el server");
	}
}




public void setLoged(boolean b) {
	if (b) {
		loginBtn.setText("Perfil");
	}
	else {
		loginBtn.setText("Login");
	}
	
	
	
	
}

    
    
   



	private void agregarHoverEffect(JLabel label, double escala, String normalPath) {
        String hoverPath = normalPath.replace(".png", "Hover.png");

        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ImageIcon imgLabel = new ImageIcon(getClass().getResource(hoverPath));
                Image imgReescalada = imgLabel.getImage().getScaledInstance(
                        label.getWidth(),
                        label.getHeight(),
                        Image.SCALE_SMOOTH
                );
                label.setIcon(new ImageIcon(imgReescalada));
                label.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ImageIcon imgLabel = new ImageIcon(getClass().getResource(normalPath));
                Image imgReescalada = imgLabel.getImage().getScaledInstance(
                        label.getWidth(),
                        label.getHeight(),
                        Image.SCALE_SMOOTH
                );
                label.setIcon(new ImageIcon(imgReescalada));
            }
        });
    }


}



