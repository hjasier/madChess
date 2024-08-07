package ventanas;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import componentes.BLabel;
import componentes.BButton;
import componentes.RButton;
import componentes.SLabel;
import componentes.navBar;
import juego.Puntuador;
import librerias.FontAwesome;
import librerias.IconFontSwing;
import utils.Configuracion;
import utils.Escalador;
import utils.Session;
import utils.utils;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

public class Perfil extends JPanel{
	protected RButton backBtn = new RButton("Volver");
	protected navBar navBar;
	protected BLabel nombre = new BLabel();
	private SLabel valormadchess1;
	private SLabel valorClasico1;
	private SLabel valormadchess2;
	private SLabel valorClasico2;
	private JLabel fotoPerfil;
	
	
	public Perfil() {
		
		
		//--------------------- NAVBAR-------------------------------------------
  		Icon icon = IconFontSwing.buildIcon(FontAwesome.BACKWARD, Escalador.escalarF(15));
  		backBtn.setIcon(icon);
  		this.setLayout(new BorderLayout());
		JPanel navBarContainer = new JPanel(new FlowLayout());
		navBarContainer.add(new navBar(backBtn));
        navBarContainer.setBackground(Configuracion.BACKGROUND);
      
  		
  		//--------------------- NAVBAR-------------------------------------------	
  		
  		this.setPreferredSize(Escalador.newDimension(1000,641));
        
        JPanel panel = new JPanel();
        panel.setBackground(Configuracion.BACKGROUND);
        panel.setBounds(0,0, Escalador.escalar(1000), Escalador.escalar(641));
        panel.setPreferredSize(Escalador.newDimension(1000,641));
        panel.setLayout(null);
        
  		
  		double escala = 0.7;

  		
  		//-----------------Foto y Nombre perfil-----------------
  		JPanel arribaftyNombre = new JPanel();
  		arribaftyNombre.setLayout(new BorderLayout());
  		arribaftyNombre.setBounds(Escalador.newBounds(35, 35, 858, 160));
  		
  		JPanel ftyNombrePanel = new JPanel();
  		ftyNombrePanel.setBackground(Configuracion.BACKGROUND);
  		ftyNombrePanel.setBounds(Escalador.newBounds(10, 35, 858, 160));
  		ftyNombrePanel.setLayout(null);
  		
  		

                
  		// Foto perfil FIXME: hay que hacer que salga la doto del usuario logeado
  		
  		
  		fotoPerfil = new JLabel();
  		fotoPerfil.setBounds(Escalador.escalar(245), Escalador.escalar(10), Escalador.escalar(140), Escalador.escalar(140));
  		
  		nombre.setForeground(new Color(255, 255, 255));
  		
  		// Nombre perfil  
  		//nombre.setText("Giova");
  		nombre.setBounds(Escalador.escalar(430), Escalador.escalar(10),Escalador.escalar(140), Escalador.escalar(140));
  		
  		
  		
  		ftyNombrePanel.add(nombre);
  		ftyNombrePanel.add(fotoPerfil);
  		arribaftyNombre.add(ftyNombrePanel, BorderLayout.CENTER);
  		
  		//-----------------FIN Foto y Nombre perfil----------------- 		
  		
  		
  		//-----------------Rango y Rankings-----------------
        JPanel singlePanel = new JPanel();
        singlePanel.setBackground(Configuracion.BACKGROUND);
        singlePanel.setBounds(Escalador.escalar(95), Escalador.escalar(205), Escalador.escalar(829), Escalador.escalar(184));

        // PanelRankingRango
        ImageIcon PanelRankingRangoft = new ImageIcon(getClass().getResource("/srcmedia/PanelRankingRango.png"));
        Image imagenEscalada = PanelRankingRangoft.getImage().getScaledInstance((int) (Escalador.escalar(978) * escala), (int) (Escalador.escalar(252) * escala), Image.SCALE_SMOOTH);
        JLabel PanelRankingRango = new JLabel();
  		PanelRankingRango.setBounds(Escalador.escalar(52), Escalador.escalar(-45), Escalador.escalar(967), Escalador.escalar(270));
  		PanelRankingRango.setIcon(new ImageIcon(imagenEscalada));
        
  		// Rangopng
  		ImageIcon Rangopngft = new ImageIcon(getClass().getResource("/srcmedia/Rangopng.png"));
  		Image imgEscaladaRangopngft = Rangopngft.getImage().getScaledInstance((int) (Escalador.escalar(149)*0.7 * escala), (int) (Escalador.escalar(37)*0.7 * escala), Image.SCALE_SMOOTH);
  		JLabel Rangopng = new JLabel();
  		Rangopng.setIcon(new ImageIcon(imgEscaladaRangopngft));
  		Rangopng.setBounds(Escalador.escalar(157), Escalador.escalar(-5), Escalador.escalar(183), Escalador.escalar(73));
        
  		// Clasico1
        ImageIcon Clasico1ft = new ImageIcon(getClass().getResource("/srcmedia/Clasico.png"));
        Image imgEscaladaClasico1ft = Clasico1ft.getImage().getScaledInstance((int) (Escalador.escalar(224) * escala), (int) (Escalador.escalar(75) * escala), Image.SCALE_SMOOTH);
        JLabel Clasico1 = new JLabel();
        Clasico1.setIcon(new ImageIcon(imgEscaladaClasico1ft));
        Clasico1.setBounds(Escalador.escalar(69), Escalador.escalar(65), Escalador.escalar(257), Escalador.escalar(157));
        
        // madChess1
        ImageIcon madChess1ft = new ImageIcon(getClass().getResource("/srcmedia/madChess.png"));
        Image imgEscaladamadChess1ft = madChess1ft.getImage().getScaledInstance((int) (Escalador.escalar(224) * escala), (int) (Escalador.escalar(75) * escala), Image.SCALE_SMOOTH);
        JLabel madChess1 = new JLabel();
        madChess1.setIcon(new ImageIcon(imgEscaladamadChess1ft));
        madChess1.setBounds(Escalador.escalar(229), Escalador.escalar(77), Escalador.escalar(192), Escalador.escalar(133));
  		
        // Ranking
        ImageIcon Rankingft = new ImageIcon(getClass().getResource("/srcmedia/Ranking.png"));
        Image imgEscaladaSingleLabel = Rankingft.getImage().getScaledInstance((int) (Escalador.escalar(188)*0.7 * escala), (int) (Escalador.escalar(37)*0.7 * escala), Image.SCALE_SMOOTH);
        JLabel Ranking = new JLabel();
  		Ranking.setBounds(Escalador.escalar(488), Escalador.escalar(-5), Escalador.escalar(215), Escalador.escalar(73));
  		Ranking.setIcon(new ImageIcon(imgEscaladaSingleLabel));
        
  		// Clasico2
        ImageIcon Clasico2ft = new ImageIcon(getClass().getResource("/srcmedia/Clasico.png"));
        Image imgEscaladaClasico2ft = Clasico2ft.getImage().getScaledInstance((int) (Escalador.escalar(224) * escala), (int) (Escalador.escalar(75) * escala), Image.SCALE_SMOOTH);
        JLabel Clasico2 = new JLabel();
        Clasico2.setIcon(new ImageIcon(imgEscaladaClasico2ft));
        Clasico2.setBounds(Escalador.escalar(401), Escalador.escalar(65), Escalador.escalar(257), Escalador.escalar(157));
        
        // madChess2
        ImageIcon madChess2ft = new ImageIcon(getClass().getResource("/srcmedia/madChess.png"));
        Image imgEscaladamadChess2ft = madChess2ft.getImage().getScaledInstance((int) (Escalador.escalar(224) * escala), (int) (Escalador.escalar(75) * escala), Image.SCALE_SMOOTH);
        JLabel madChess2 = new JLabel();
        madChess2.setIcon(new ImageIcon(imgEscaladamadChess2ft));
        madChess2.setBounds(Escalador.escalar(555), Escalador.escalar(77), Escalador.escalar(192), Escalador.escalar(133));
        
        //rango valor Clasico1
        valorClasico1 = new SLabel("230");
        valorClasico1.setForeground(new Color(255, 255, 255));
        valorClasico1.setBounds(Escalador.escalar(128), Escalador.escalar(77), Escalador.escalar(81), Escalador.escalar(36));
        
        //rango valor madchess1
        valormadchess1 = new SLabel("230");
        valormadchess1.setForeground(new Color(255, 255, 255));
        valormadchess1.setBounds(Escalador.escalar(288), Escalador.escalar(77), Escalador.escalar(81), Escalador.escalar(36));
        
       //ranking valor Clasico2
        valorClasico2 = new SLabel("#2");
        valorClasico2.setForeground(new Color(255, 255, 255));
        valorClasico2.setBounds(Escalador.escalar(458), Escalador.escalar(77), Escalador.escalar(81), Escalador.escalar(36));
  		
        //ranking valor madchess2
        valormadchess2 = new SLabel("#40");
        valormadchess2.setForeground(new Color(255, 255, 255));
        valormadchess2.setBounds(Escalador.escalar(618), Escalador.escalar(77), Escalador.escalar(81), Escalador.escalar(36));
        
        singlePanel.setLayout(null);
        singlePanel.add(Rangopng);
        singlePanel.add(Ranking);        
        singlePanel.add(Clasico1);   
        singlePanel.add(madChess1);
        singlePanel.add(Clasico2);   
        singlePanel.add(madChess2);
        singlePanel.add(PanelRankingRango);
        singlePanel.add(valorClasico1);
        singlePanel.add(valormadchess1);
        singlePanel.add(valorClasico2);
        singlePanel.add(valormadchess2);
        //-----------------FIN Rango y Rankings-----------------
        
        
        
        //-----------------Botones-----------------
  		
  		JPanel Botones = new JPanel();
  		Botones.setBackground(Configuracion.BACKGROUND);
  		Botones.setBounds(Escalador.escalar(78), Escalador.escalar(555), Escalador.escalar(894), Escalador.escalar(225));
  		Botones.setLayout(null);
  		
  		

  		
  	
  		panel.add(singlePanel);
  		
  		
  		panel.add(arribaftyNombre);
  		panel.add(Botones);
  		
  		
  		
        
        JPanel centeredPanel = new JPanel(new FlowLayout());
        
        centeredPanel.setBackground(Configuracion.BACKGROUND);

        centeredPanel.add(panel);
        
  		// Boton Ver mis partidas
  		BButton vermisPartidas = new BButton("Ver mís partidas");
  		vermisPartidas.setBounds(new Rectangle(Escalador.escalar(406), Escalador.escalar(404), Escalador.escalar(183), Escalador.escalar(47)));
  		panel.add(vermisPartidas);
  		
  		
  		// Boton Cambiar contraseña
  		JButton contraCambiar = new JButton("Cambiar Contraseña");
  		contraCambiar.setBounds(new Rectangle(Escalador.escalar(406), Escalador.escalar(505), Escalador.escalar(183), Escalador.escalar(47)));
  		panel.add(contraCambiar);
  		contraCambiar.setCursor(new Cursor(Cursor.HAND_CURSOR));
  		
  		
  		// Ajustes label
  		BLabel ajusteslbl = new BLabel("Ajustes :");
  		ajusteslbl.setForeground(new Color(255, 255, 255));
  		ajusteslbl.setBounds(Escalador.escalar(462), Escalador.escalar(465), Escalador.escalar(137), Escalador.escalar(35));
  		
  		// Log out con el icono invertido
  		Icon logoutIcon = IconFontSwing.buildIcon(FontAwesome.SIGN_OUT, 18);

  		int iconWidth = logoutIcon.getIconWidth();
  		int iconHeight = logoutIcon.getIconHeight();
  		BufferedImage image = new BufferedImage(iconWidth, iconHeight, BufferedImage.TYPE_INT_ARGB);
  		Graphics g = image.createGraphics();
  		logoutIcon.paintIcon(null, g, 0, 0);
  		g.dispose();

  		AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
  		tx.translate(-image.getWidth(null), 0);
  		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);

  			// Aplicar la transformación a la imagen para invertirla horizontalmente
  		BufferedImage flippedImage = op.filter(image, null);

  			// Crear un nuevo icono a partir de la imagen invertida
  		Icon flippedIcon = new ImageIcon(flippedImage);
  		panel.add(ajusteslbl);
  		
  		// Crear el botón con el texto y el icono invertido
  		RButton logOutBtn = new RButton(" Log-out");
  		logOutBtn.setBounds(Escalador.escalar(880), Escalador.escalar(0), Escalador.escalar(85), Escalador.escalar(25));
  		panel.add(logOutBtn);
  		logOutBtn.setIcon(flippedIcon);
  		logOutBtn.setHorizontalTextPosition(SwingConstants.RIGHT);
  		//-----------------FIN Botones-----------------        
        

        add(navBarContainer,BorderLayout.NORTH);
        add(centeredPanel, BorderLayout.CENTER);
  		
  		
  		
        logOutBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	Session.setCurrentUser(null);
                VentanaPrincipal ventanaPrincipal = Session.getVentana();             
                // Cambiar a la pantalla de inicio de sesión (Login)
                ventanaPrincipal.getCardLayout().show(ventanaPrincipal.getPanelPrincipal(), Paneles.LOGIN);
            }
        });
  		
        contraCambiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	VentanaPrincipal ventanaPrincipal = Session.getVentana();    
                ventanaPrincipal.getCardLayout().show(ventanaPrincipal.getPanelPrincipal(), Paneles.CAMBIARCONTRASENYA);
            }
        });
        
        fotoPerfil.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                Image curImg = ((ImageIcon) fotoPerfil.getIcon()).getImage();
                Image imgHover = utils.adjustImageOpacity(curImg, 0.3);
                fotoPerfil.setIcon(new ImageIcon(imgHover));
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // Cambiar a Cursor.DEFAULT_CURSOR al entrar
            }

            @Override
            public void mouseExited(MouseEvent e) {
                BufferedImage imagenRedonda = utils.getRoundImg(Session.getCurrentUser().getImg_route(), Escalador.escalar(85));
                fotoPerfil.setIcon(new ImageIcon(imagenRedonda));
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Seleccionar Foto");

                // Mostrar el explorador de archivos y esperar a que el usuario elija un archivo
                int userSelection = fileChooser.showOpenDialog(Perfil.this);
                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    // Obtener el archivo seleccionado
                    File selectedFile = fileChooser.getSelectedFile();
                    // Aquí puedes manejar el archivo seleccionado (por ejemplo, mostrar la ruta)
                    
                    utils.uploadFile(selectedFile);
                }
            }

        });

		
		
		vermisPartidas.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaPrincipal ventanaPrincipal = Session.getVentana();  
				ventanaPrincipal.getPartidas().cargarPartidas();
                ventanaPrincipal.getCardLayout().show(ventanaPrincipal.getPanelPrincipal(), Paneles.PARTIDAS);
			}
		});
  		
	}

	public void reloadData() {
		nombre.setText(Session.getCurrentUser().getUsername());
		
		Thread cargaPerfil = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                	valormadchess1.setText(Session.getCurrentUser().getRank_madChess()+"");
            		valorClasico1.setText(Session.getCurrentUser().getRank_classic()+"");
            		valormadchess2.setText("#"+ Puntuador.getPosicionLadder("rank_mad", Session.getCurrentUser().getUsername()));
            		valorClasico2.setText("#"+ Puntuador.getPosicionLadder("rank_classic", Session.getCurrentUser().getUsername() ));
                } catch (Exception e) {
                    System.out.println("Error al cargar el perfil");
                }
            }
        });
		
		
        Thread cargaImg = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                	BufferedImage imagenRedonda = utils.getRoundImg(Session.getCurrentUser().getImg_route(),Escalador.escalar(85));
            		fotoPerfil.setIcon(new ImageIcon(imagenRedonda));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        cargaImg.start();
        cargaPerfil.start();
		
	} 
}
