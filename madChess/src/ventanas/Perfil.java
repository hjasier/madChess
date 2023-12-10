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
  		navBar.setBounds(0, 0, 994, 141);
  		//--------------------- NAVBAR-------------------------------------------	
  		double escala = 0.7;

  		
  		
  		//-----------------Foto y Nombre perfil-----------------
  		
  		JPanel ftyNombrePanel = new JPanel();
  		ftyNombrePanel.setBackground(Configuracion.BACKGROUND);
  		ftyNombrePanel.setBounds(78, 119, 858, 225);
  		ftyNombrePanel.setLayout(null);
  		
  		// Foto perfil
  		ImageIcon fotoPerfilft = new ImageIcon(getClass().getResource("../srcmedia/bbb.jpg"));
  		Image imgEscaladafotoPerfil = fotoPerfilft.getImage().getScaledInstance((int) (Escalador.escalar(200) * escala), (int) (Escalador.escalar(200) * escala), Image.SCALE_SMOOTH);
  		JLabel fotoPerfil = new JLabel();
  		fotoPerfil.setBounds(235, 75, 140, 140);
  		fotoPerfil.setIcon(new ImageIcon(imgEscaladafotoPerfil));
  		nombre.setForeground(new Color(255, 255, 255));
  		
  		// Nombre perfil  FIXME: hay que hacer que salga el nombre del logeado
  		nombre.setText("Giova");
  		nombre.setBounds(434, 75, 140, 140);
  		
  		
  		
  		ftyNombrePanel.add(nombre);
  		ftyNombrePanel.add(fotoPerfil);
  		//-----------------FIN Foto y Nombre perfil----------------- 		
  		
  		
  		//-----------------Rango y Rankings-----------------
        JPanel singlePanel = new JPanel();
        singlePanel.setBackground(Configuracion.BACKGROUND);
        singlePanel.setBounds(107, 361, 829, 184);

        // PanelRankingRango
        ImageIcon PanelRankingRangoft = new ImageIcon(getClass().getResource("../srcmedia/PanelRankingRango.png"));
        Image imagenEscalada = PanelRankingRangoft.getImage().getScaledInstance((int) (Escalador.escalar(978) * escala), (int) (Escalador.escalar(252) * escala), Image.SCALE_SMOOTH);
        JLabel PanelRankingRango = new JLabel();
  		PanelRankingRango.setBounds(52, -45, 967, 270);
  		PanelRankingRango.setIcon(new ImageIcon(imagenEscalada));
        
  		// Rangopng
  		ImageIcon Rangopngft = new ImageIcon(getClass().getResource("../srcmedia/Rangopng.png"));
  		Image imgEscaladaRangopngft = Rangopngft.getImage().getScaledInstance((int) (Escalador.escalar(149) * escala), (int) (Escalador.escalar(37) * escala), Image.SCALE_SMOOTH);
  		JLabel Rangopng = new JLabel();
  		Rangopng.setIcon(new ImageIcon(imgEscaladaRangopngft));
  		Rangopng.setBounds(157, -15, 183, 73);
        
  		// Clasico1
        ImageIcon Clasico1ft = new ImageIcon(getClass().getResource("../srcmedia/Clasico.png"));
        Image imgEscaladaClasico1ft = Clasico1ft.getImage().getScaledInstance((int) (Escalador.escalar(224) * escala), (int) (Escalador.escalar(75) * escala), Image.SCALE_SMOOTH);
        JLabel Clasico1 = new JLabel();
        Clasico1.setIcon(new ImageIcon(imgEscaladaClasico1ft));
        Clasico1.setBounds(69, 65, 257, 157);
        
        // madChess1
        ImageIcon madChess1ft = new ImageIcon(getClass().getResource("../srcmedia/madChess.png"));
        Image imgEscaladamadChess1ft = madChess1ft.getImage().getScaledInstance((int) (Escalador.escalar(224) * escala), (int) (Escalador.escalar(75) * escala), Image.SCALE_SMOOTH);
        JLabel madChess1 = new JLabel();
        madChess1.setIcon(new ImageIcon(imgEscaladamadChess1ft));
        madChess1.setBounds(229, 77, 192, 133);
  		
        // Ranking
        ImageIcon Rankingft = new ImageIcon(getClass().getResource("../srcmedia/Ranking.png"));
        Image imgEscaladaSingleLabel = Rankingft.getImage().getScaledInstance((int) (Escalador.escalar(188) * escala), (int) (Escalador.escalar(37) * escala), Image.SCALE_SMOOTH);
        JLabel Ranking = new JLabel();
  		Ranking.setBounds(488, -15, 215, 73);
  		Ranking.setIcon(new ImageIcon(imgEscaladaSingleLabel));
        
  		// Clasico2
        ImageIcon Clasico2ft = new ImageIcon(getClass().getResource("../srcmedia/Clasico.png"));
        Image imgEscaladaClasico2ft = Clasico2ft.getImage().getScaledInstance((int) (Escalador.escalar(224) * escala), (int) (Escalador.escalar(75) * escala), Image.SCALE_SMOOTH);
        JLabel Clasico2 = new JLabel();
        Clasico2.setIcon(new ImageIcon(imgEscaladaClasico2ft));
        Clasico2.setBounds(401, 65, 257, 157);
        
        // madChess2
        ImageIcon madChess2ft = new ImageIcon(getClass().getResource("../srcmedia/madChess.png"));
        Image imgEscaladamadChess2ft = madChess2ft.getImage().getScaledInstance((int) (Escalador.escalar(224) * escala), (int) (Escalador.escalar(75) * escala), Image.SCALE_SMOOTH);
        JLabel madChess2 = new JLabel();
        madChess2.setIcon(new ImageIcon(imgEscaladamadChess2ft));
        madChess2.setBounds(555, 77, 192, 133);
        
        
  		
        
        
        singlePanel.setLayout(null);
        singlePanel.add(Rangopng);
        singlePanel.add(Ranking);        
        singlePanel.add(Clasico1);   
        singlePanel.add(madChess1);
        singlePanel.add(Clasico2);   
        singlePanel.add(madChess2);
        singlePanel.add(PanelRankingRango);
        
        //-----------------FIN Rango y Rankings-----------------
        
        
        
        //-----------------Botones-----------------
  		
  		JPanel Botones = new JPanel();
  		Botones.setBackground(Configuracion.BACKGROUND);
  		Botones.setBounds(78, 555, 858, 225);
  		Botones.setLayout(null);
        
  		// Boton Ver mis partidas
  		BButton vermisPartidas = new BButton("Ver mís partidas");
  		vermisPartidas.setBounds(326, 10, 183, 47);
  		
  		// Ajustes label
  		BLabel lblNewLabel = new BLabel("Ajustes :");
  		lblNewLabel.setForeground(new Color(255, 255, 255));
  		lblNewLabel.setBounds(386, 85, 137, 35);
  		
  		
  		// Boton Cambiar contraseña
  		BButton contraCambiar = new BButton("Cambiar Contraseña");
  		contraCambiar.setBounds(326, 128, 183, 47);
  		
  		
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
