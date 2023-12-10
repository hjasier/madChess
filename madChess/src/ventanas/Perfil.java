package ventanas;

import java.awt.BorderLayout;
import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import componentes.BLabel;
import componentes.BButton;
import componentes.RButton;
import componentes.navBar;
import librerias.FontAwesome;
import librerias.IconFontSwing;
import utils.Configuracion;
import utils.Escalador;
import utils.Session;
import java.awt.Color;
import javax.swing.JButton;

public class Perfil extends JPanel{
	protected RButton backBtn = new RButton("Volver");
	protected navBar navBar;
	protected BLabel nombre = new BLabel();
	
	public Perfil() {
		
  		//--------------------- NAVBAR-------------------------------------------
		Icon icon = IconFontSwing.buildIcon(FontAwesome.BACKWARD, 15);
		
		backBtn.setIcon(icon);
  		
  		navBar = new navBar(backBtn);
  		navBar.setBounds(Escalador.escalar(0), Escalador.escalar(0), Escalador.escalar(994), Escalador.escalar(141));
  		//--------------------- NAVBAR-------------------------------------------	
  		double escala = 0.7;

  		
  		
  		//-----------------Foto y Nombre perfil-----------------
  		
  		JPanel ftyNombrePanel = new JPanel();
  		ftyNombrePanel.setBackground(Configuracion.BACKGROUND);
  		ftyNombrePanel.setBounds(Escalador.escalar(78), Escalador.escalar(119), Escalador.escalar(858), Escalador.escalar(225));
  		ftyNombrePanel.setLayout(null);
  		
  		// Foto perfil FIXME: hay que hacer que salga la doto del usuario logeado
  		ImageIcon fotoPerfilft = new ImageIcon(getClass().getResource("../srcmedia/bbb.jpg"));
  		Image imgEscaladafotoPerfil = fotoPerfilft.getImage().getScaledInstance((int) (Escalador.escalar(200) * escala), (int) (Escalador.escalar(200) * escala), Image.SCALE_SMOOTH);
  		JLabel fotoPerfil = new JLabel();
  		fotoPerfil.setBounds(Escalador.escalar(235), Escalador.escalar(75), Escalador.escalar(140), Escalador.escalar(140));
  		fotoPerfil.setIcon(new ImageIcon(imgEscaladafotoPerfil));
  		nombre.setForeground(new Color(255, 255, 255));
  		
  		// Nombre perfil  
  		//nombre.setText("Giova");
  		nombre.setBounds(Escalador.escalar(434), Escalador.escalar(75),Escalador.escalar(140), Escalador.escalar(140));
  		
  		
  		
  		ftyNombrePanel.add(nombre);
  		ftyNombrePanel.add(fotoPerfil);
  		//-----------------FIN Foto y Nombre perfil----------------- 		
  		
  		
  		//-----------------Rango y Rankings-----------------
        JPanel singlePanel = new JPanel();
        singlePanel.setBackground(Configuracion.BACKGROUND);
        singlePanel.setBounds(Escalador.escalar(107), Escalador.escalar(361), Escalador.escalar(829), Escalador.escalar(184));

        // PanelRankingRango
        ImageIcon PanelRankingRangoft = new ImageIcon(getClass().getResource("../srcmedia/PanelRankingRango.png"));
        Image imagenEscalada = PanelRankingRangoft.getImage().getScaledInstance((int) (Escalador.escalar(978) * escala), (int) (Escalador.escalar(252) * escala), Image.SCALE_SMOOTH);
        JLabel PanelRankingRango = new JLabel();
  		PanelRankingRango.setBounds(Escalador.escalar(52), Escalador.escalar(-45), Escalador.escalar(967), Escalador.escalar(270));
  		PanelRankingRango.setIcon(new ImageIcon(imagenEscalada));
        
  		// Rangopng
  		ImageIcon Rangopngft = new ImageIcon(getClass().getResource("../srcmedia/Rangopng.png"));
  		Image imgEscaladaRangopngft = Rangopngft.getImage().getScaledInstance((int) (Escalador.escalar(149) * escala), (int) (Escalador.escalar(37) * escala), Image.SCALE_SMOOTH);
  		JLabel Rangopng = new JLabel();
  		Rangopng.setIcon(new ImageIcon(imgEscaladaRangopngft));
  		Rangopng.setBounds(Escalador.escalar(157), Escalador.escalar(-15), Escalador.escalar(183), Escalador.escalar(73));
        
  		// Clasico1
        ImageIcon Clasico1ft = new ImageIcon(getClass().getResource("../srcmedia/Clasico.png"));
        Image imgEscaladaClasico1ft = Clasico1ft.getImage().getScaledInstance((int) (Escalador.escalar(224) * escala), (int) (Escalador.escalar(75) * escala), Image.SCALE_SMOOTH);
        JLabel Clasico1 = new JLabel();
        Clasico1.setIcon(new ImageIcon(imgEscaladaClasico1ft));
        Clasico1.setBounds(Escalador.escalar(69), Escalador.escalar(65), Escalador.escalar(257), Escalador.escalar(157));
        
        // madChess1
        ImageIcon madChess1ft = new ImageIcon(getClass().getResource("../srcmedia/madChess.png"));
        Image imgEscaladamadChess1ft = madChess1ft.getImage().getScaledInstance((int) (Escalador.escalar(224) * escala), (int) (Escalador.escalar(75) * escala), Image.SCALE_SMOOTH);
        JLabel madChess1 = new JLabel();
        madChess1.setIcon(new ImageIcon(imgEscaladamadChess1ft));
        madChess1.setBounds(Escalador.escalar(229), Escalador.escalar(77), Escalador.escalar(192), Escalador.escalar(133));
  		
        // Ranking
        ImageIcon Rankingft = new ImageIcon(getClass().getResource("../srcmedia/Ranking.png"));
        Image imgEscaladaSingleLabel = Rankingft.getImage().getScaledInstance((int) (Escalador.escalar(188) * escala), (int) (Escalador.escalar(37) * escala), Image.SCALE_SMOOTH);
        JLabel Ranking = new JLabel();
  		Ranking.setBounds(Escalador.escalar(488), Escalador.escalar(-15), Escalador.escalar(215), Escalador.escalar(73));
  		Ranking.setIcon(new ImageIcon(imgEscaladaSingleLabel));
        
  		// Clasico2
        ImageIcon Clasico2ft = new ImageIcon(getClass().getResource("../srcmedia/Clasico.png"));
        Image imgEscaladaClasico2ft = Clasico2ft.getImage().getScaledInstance((int) (Escalador.escalar(224) * escala), (int) (Escalador.escalar(75) * escala), Image.SCALE_SMOOTH);
        JLabel Clasico2 = new JLabel();
        Clasico2.setIcon(new ImageIcon(imgEscaladaClasico2ft));
        Clasico2.setBounds(Escalador.escalar(401), Escalador.escalar(65), Escalador.escalar(257), Escalador.escalar(157));
        
        // madChess2
        ImageIcon madChess2ft = new ImageIcon(getClass().getResource("../srcmedia/madChess.png"));
        Image imgEscaladamadChess2ft = madChess2ft.getImage().getScaledInstance((int) (Escalador.escalar(224) * escala), (int) (Escalador.escalar(75) * escala), Image.SCALE_SMOOTH);
        JLabel madChess2 = new JLabel();
        madChess2.setIcon(new ImageIcon(imgEscaladamadChess2ft));
        madChess2.setBounds(Escalador.escalar(555), Escalador.escalar(77), Escalador.escalar(192), Escalador.escalar(133));
        
        //rango valor Clasico1
        BLabel valorClasico1 = new BLabel("230");
        valorClasico1.setForeground(new Color(255, 255, 255));
        valorClasico1.setBounds(Escalador.escalar(128), Escalador.escalar(77), Escalador.escalar(81), Escalador.escalar(36));
  		
        //rango valor madchess1
        BLabel valormadchess1 = new BLabel("230");
        valormadchess1.setForeground(new Color(255, 255, 255));
        valormadchess1.setBounds(Escalador.escalar(288), Escalador.escalar(77), Escalador.escalar(81), Escalador.escalar(36));
        
       //ranking valor Clasico2
        BLabel valorClasico2 = new BLabel("#2");
        valorClasico2.setForeground(new Color(255, 255, 255));
        valorClasico2.setBounds(Escalador.escalar(458), Escalador.escalar(77), Escalador.escalar(81), Escalador.escalar(36));
  		
        //ranking valor madchess2
        BLabel valormadchess2 = new BLabel("#40");
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
  		Botones.setBounds(Escalador.escalar(78), Escalador.escalar(555), Escalador.escalar(858), Escalador.escalar(225));
  		Botones.setLayout(null);
        
  		// Boton Ver mis partidas
  		BButton vermisPartidas = new BButton("Ver mís partidas");
  		vermisPartidas.setBounds(Escalador.escalar(326), Escalador.escalar(10), Escalador.escalar(183), Escalador.escalar(47));
  		
  		// Ajustes label
  		BLabel lblNewLabel = new BLabel("Ajustes :");
  		lblNewLabel.setForeground(new Color(255, 255, 255));
  		lblNewLabel.setBounds(Escalador.escalar(386), Escalador.escalar(85), Escalador.escalar(137), Escalador.escalar(35));
  		
  		
  		// Boton Cambiar contraseña
  		BButton contraCambiar = new BButton("Cambiar Contraseña");
  		contraCambiar.setBounds(Escalador.escalar(326), Escalador.escalar(128), Escalador.escalar(183), Escalador.escalar(47));
  		
  		
  		Botones.add(vermisPartidas);
  		Botones.add(lblNewLabel);
  		Botones.add(contraCambiar);
  		//-----------------FIN Botones-----------------        
        
  		this.setBackground(Configuracion.BACKGROUND);
  		setLayout(null);
  		
  		this.add(navBar);
  		
  		this.add(singlePanel);
  		
  		
  		this.add(ftyNombrePanel);
  		this.add(Botones);
  		
  		
  		
  		
  		
  		
  		
  		
  		
	}

	public void reloadData() {
		nombre.setText(Session.getCurrentUser().getNombre());
	} 
}
