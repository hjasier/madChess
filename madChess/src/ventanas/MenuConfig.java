package ventanas;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.logging.Handler;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import componentes.RButton;
import componentes.navBar;
import librerias.FontAwesome;
import librerias.IconFontSwing;
import utils.Configuracion;
import utils.Escalador;
import utils.Session;
import utils.Themes.piezasThemes;
import utils.Themes.tableroThemes;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;

public class MenuConfig extends JPanel{
	protected RButton volverBtn = new RButton("Volver");
	
	public MenuConfig() {
		setBackground(Configuracion.BACKGROUND);
		setLayout(new BorderLayout());
		
		
		
		//--------------------- NAVBAR-------------------------------------------
  		Icon icon = IconFontSwing.buildIcon(FontAwesome.BACKWARD, Escalador.escalarF(15));
  		volverBtn.setIcon(icon);
  		
		JPanel navBarContainer = new JPanel(new FlowLayout());
		navBarContainer.add(new navBar(volverBtn));
        navBarContainer.setBackground(Configuracion.BACKGROUND);
      
  		
		volverBtn.addActionListener(e -> {
			Session.getVentana().showPanel("JUEGO");
		});
  		//--------------------- NAVBAR-------------------------------------------	   
        
        
      		
        double escala = 0.8;         
        
        JPanel panel = new JPanel();
        panel.setBackground(Configuracion.BACKGROUND);
        
        panel.setLayout(null);

       
        //Labels
        ImageIcon labelsImg = new ImageIcon(getClass().getResource("../srcmedia/tablerosLabels.png"));
        Image imgLabelsR = labelsImg.getImage().getScaledInstance((int) (Escalador.escalar(956) * escala), (int) (Escalador.escalar(36) * escala), Image.SCALE_SMOOTH);

        
        //Label tablero
        ImageIcon tableroImg = new ImageIcon(getClass().getResource("../srcmedia/labelTablero.png"));
        Image imgTableroR = tableroImg.getImage().getScaledInstance((int) (Escalador.escalar(177) * escala), (int) (Escalador.escalar(51) * escala), Image.SCALE_SMOOTH);
        
        
        
        
        
        // Tablero1
        ImageIcon tablero1Img = new ImageIcon(getClass().getResource("../srcmedia/tablero1.png"));
        Image imgReescaladatablero1 = tablero1Img.getImage().getScaledInstance((int) (Escalador.escalar(150) * escala), (int) (Escalador.escalar(150) * escala), Image.SCALE_SMOOTH);

        
        // Tablero2
        ImageIcon tablero2Img = new ImageIcon(getClass().getResource("../srcmedia/tablero2.png"));
        Image imgReescaladatablero2 = tablero2Img.getImage().getScaledInstance((int) (Escalador.escalar(150) * escala), (int) (Escalador.escalar(150) * escala), Image.SCALE_SMOOTH);
        
        
        // Tablero3
        ImageIcon tablero3Img = new ImageIcon(getClass().getResource("../srcmedia/tablero3.png"));
        Image imgReescaladatablero3 = tablero3Img.getImage().getScaledInstance((int) (Escalador.escalar(150) * escala), (int) (Escalador.escalar(150) * escala), Image.SCALE_SMOOTH);
        
        // Tablero4
        ImageIcon tablero4Img = new ImageIcon(getClass().getResource("../srcmedia/tablero4.png"));
        Image imgReescaladatablero4 = tablero4Img.getImage().getScaledInstance((int) (Escalador.escalar(150) * escala), (int) (Escalador.escalar(150) * escala), Image.SCALE_SMOOTH);
        
        // Tablero5
        ImageIcon tablero5Img = new ImageIcon(getClass().getResource("../srcmedia/tablero5.png"));
        Image imgReescaladatablero5 = tablero5Img.getImage().getScaledInstance((int) (Escalador.escalar(150) * escala), (int) (Escalador.escalar(150) * escala), Image.SCALE_SMOOTH);
        
        
        //-----------------FIN PanelTableros-----------------
        
        
        //-----------------PanelPiezas-----------------
        JPanel panelPiezas = new JPanel();
        panelPiezas.setBackground(Configuracion.BACKGROUND);
        panelPiezas.setBounds(Escalador.escalar(80), Escalador.escalar(335), Escalador.escalar(828), Escalador.escalar(281));
        panelPiezas.setLayout(null);
        
        //Labels
        ImageIcon labelsImgPiezas = new ImageIcon(getClass().getResource("../srcmedia/bb.png"));
        Image imgLabelsPiezas = labelsImgPiezas.getImage().getScaledInstance((int) (Escalador.escalar(956) * escala), (int) (Escalador.escalar(36) * escala), Image.SCALE_SMOOTH);
        JLabel labelsFtPiezas = new JLabel();
        labelsFtPiezas.setIcon(new ImageIcon(imgLabelsPiezas));
        labelsFtPiezas.setBounds(Escalador.escalar(32), Escalador.escalar(211), (int) (Escalador.escalar(956)*escala), (int) (Escalador.escalar(36)*escala));
        
        //Label piezas
        ImageIcon piezasImg = new ImageIcon(getClass().getResource("../srcmedia/labelPiezas.png"));
        Image imgPiezas = piezasImg.getImage().getScaledInstance((int) (Escalador.escalar(177) * escala), (int) (Escalador.escalar(51) * escala), Image.SCALE_SMOOTH);
        JLabel labelPiezas = new JLabel();
        labelPiezas.setIcon(new ImageIcon(imgPiezas));
        labelPiezas.setBounds(Escalador.escalar(339), Escalador.escalar(10), (int) (Escalador.escalar(177)*escala), (int) (Escalador.escalar(51)*escala));
        
        
        
        // Pieza1
        ImageIcon pieza1Img = new ImageIcon(getClass().getResource("../srcmedia/wn.png"));
        Image imgReescaladaPieza1 = pieza1Img.getImage().getScaledInstance((int) (Escalador.escalar(75) * escala), (int) (Escalador.escalar(75) * escala), Image.SCALE_SMOOTH);
        JLabel pieza1Ft = new JLabel();
        pieza1Ft.setIcon(new ImageIcon(imgReescaladaPieza1));
        pieza1Ft.setBounds(Escalador.escalar(263), Escalador.escalar(104), (int)(Escalador.escalar(75)*escala), (int) (Escalador.escalar(75)*escala));
        
        
        // Pieza2
        ImageIcon pieza2Img = new ImageIcon(getClass().getResource("../srcmedia/bbP2.png"));
        Image imgReescaladaPieza2 = pieza2Img.getImage().getScaledInstance((int) (Escalador.escalar(75) * escala), (int) (Escalador.escalar(75) * escala), Image.SCALE_SMOOTH);
        JLabel pieza2Ft = new JLabel();
        pieza2Ft.setIcon(new ImageIcon(imgReescaladaPieza2));
        pieza2Ft.setBounds(Escalador.escalar(363), Escalador.escalar(110), (int)(Escalador.escalar(75)*escala), (int) (Escalador.escalar(75)*escala));
        
        
        
        panelPiezas.add(labelsFtPiezas);
        panelPiezas.add(labelPiezas);
        panelPiezas.add(pieza1Ft);
        panelPiezas.add(pieza2Ft);
        	
        
        
        
        

        

        panel.add(panelPiezas);
        
        //-----------------PanelTableros-----------------
        JPanel panelTableros = new JPanel();
        panelTableros.setBounds(Escalador.escalar(80), Escalador.escalar(10), Escalador.escalar(828), Escalador.escalar(281));
        
        panel.add(panelTableros);
        panelTableros.setBackground(Configuracion.BACKGROUND);
        panelTableros.setLayout(null);
        JLabel labelsFt = new JLabel();
        labelsFt.setIcon(new ImageIcon(imgLabelsR));
        labelsFt.setBounds(Escalador.escalar(32), Escalador.escalar(211), (int) (Escalador.escalar(956)*escala), (int) (Escalador.escalar(36)*escala));
        JLabel labeltablero = new JLabel();
        labeltablero.setIcon(new ImageIcon(imgTableroR));
        labeltablero.setBounds(Escalador.escalar(339), Escalador.escalar(10), (int) (Escalador.escalar(177)*escala), (int) (Escalador.escalar(51)*escala));
        JLabel tablero1Ft = new JLabel();
        tablero1Ft.setIcon(new ImageIcon(imgReescaladatablero1));
        tablero1Ft.setBounds(Escalador.escalar(49), Escalador.escalar(69), (int)(Escalador.escalar(150)*escala), (int) (Escalador.escalar(150)*escala));
        JLabel tablero2Ft = new JLabel();
        tablero2Ft.setIcon(new ImageIcon(imgReescaladatablero2));
        tablero2Ft.setBounds(Escalador.escalar(200), Escalador.escalar(69), (int)(Escalador.escalar(150)*escala), (int) (Escalador.escalar(150)*escala));
        JLabel tablero3Ft = new JLabel();
        tablero3Ft.setIcon(new ImageIcon(imgReescaladatablero3));
        tablero3Ft.setBounds(Escalador.escalar(350), Escalador.escalar(69), (int)(Escalador.escalar(150)*escala), (int) (Escalador.escalar(150)*escala));
        JLabel tablero4Ft = new JLabel();
        tablero4Ft.setIcon(new ImageIcon(imgReescaladatablero4));
        tablero4Ft.setBounds(Escalador.escalar(504), Escalador.escalar(69), (int)(Escalador.escalar(150)*escala), (int) (Escalador.escalar(150)*escala));
        JLabel tablero5Ft = new JLabel();
        tablero5Ft.setIcon(new ImageIcon(imgReescaladatablero5));
        tablero5Ft.setBounds(Escalador.escalar(655), Escalador.escalar(69), (int)(Escalador.escalar(150)*escala), (int) (Escalador.escalar(150)*escala));
        
        
        
        
        
        panelTableros.add(labelsFt);
        panelTableros.add(labeltablero);
        
        panelTableros.add(tablero1Ft);
        panelTableros.add(tablero2Ft);
        panelTableros.add(tablero3Ft);
        panelTableros.add(tablero4Ft);
        panelTableros.add(tablero5Ft);
        
        
        
        
        JPanel centeredPanel = new JPanel(new FlowLayout());
        
        centeredPanel.setBackground(Configuracion.BACKGROUND);

        centeredPanel.add(panel);
        panel.setBounds(0, 0, Escalador.escalar(1000), Escalador.escalar(641));
        panel.setPreferredSize(Escalador.newDimension(1000,641));

        // Agrega el nuevo panel centrado al panel principal usando BorderLayout
        add(navBarContainer,BorderLayout.NORTH);
        add(centeredPanel, BorderLayout.CENTER);
        
        
        
        
        //CLICKS
        
        setearTablero(tablero1Ft,tableroThemes.THEME1);
        setearTablero(tablero2Ft,tableroThemes.THEME2);
        setearTablero(tablero3Ft,tableroThemes.THEME3);
        setearTablero(tablero4Ft,tableroThemes.THEME4);
        setearTablero(tablero5Ft,tableroThemes.THEME5);
        
        
        
        setearPieza(pieza1Ft,null);
        setearPieza(pieza2Ft,piezasThemes.P2);
        
        
	}

	private void setearPieza(JLabel pieza1Ft, piezasThemes theme1) {
		pieza1Ft.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				pieza1Ft.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// Elimina el borde de los demás elementos
				Component[] components = pieza1Ft.getParent().getComponents();
				for (Component component : components) {
					if (component instanceof JLabel && !component.equals(pieza1Ft)) {
						((JLabel) component).setBorder(null);
					}
				}

				pieza1Ft.setBorder(BorderFactory.createLineBorder(new Color(255, 229, 0), 5));
				Session.getCurrentUser().setPreferedPiezaTheme(theme1);
				Session.getVentana().getPanelJuego().getTablero().reloadPiezasImagen();
			}
		});
		
	}

	private void setearTablero(JLabel label, tableroThemes theme) {
        label.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseEntered(MouseEvent e) {
        		label.setCursor(new Cursor(Cursor.HAND_CURSOR));
        	}
        	
            @Override
            public void mouseClicked(MouseEvent e) {
            	// Elimina el borde de los demás elementos
                Component[] components = label.getParent().getComponents();
                for (Component component : components) {
                    if (component instanceof JLabel && !component.equals(label)) {
                        ((JLabel) component).setBorder(null);
                    }
                }

                
                label.setBorder(BorderFactory.createLineBorder(new Color(255,229,0), 5));
                Session.getCurrentUser().setPreferedTheme(theme);
                Session.getVentana().getPanelJuego().getTablero().reloadCasillasColor();
            }
        });
    }
	
	

    
	
	
	
}
