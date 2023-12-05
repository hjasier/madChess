package componentes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import utils.Configuracion;
import utils.Escalador;

public class navBar extends JPanel{
	private Color colorFondo = Configuracion.BACKGROUND;
	protected JPanel panelBtn = new JPanel();
	protected JPanel panelLogo = new JPanel();
	protected ImageIcon imgFoto = new ImageIcon(getClass().getResource("../srcmedia/logo430x155.png"));
    protected JLabel foto = new JLabel();
	
    public navBar() {
    	
    	this(null);
    }
	public navBar(RButton btn) {
		 
   		JPanel panelBorder = new JPanel();
   		panelBorder.setBounds(0, 0, Escalador.escalar(1000), Escalador.escalar(124));
   		setBackground(colorFondo);
   		panelBorder.setBackground(colorFondo);
   		panelLogo.setBackground(colorFondo);
   		panelBtn.setBackground(colorFondo);


   		panelBorder.setLayout(new BorderLayout());
   		
   		
   		// Escalar la imagen
         Image imagenEscalada = imgFoto.getImage().getScaledInstance(Escalador.escalar(317), Escalador.escalar(114), Image.SCALE_SMOOTH);
         imgFoto = new ImageIcon(imagenEscalada);
         foto.setIcon(imgFoto);

         panelLogo.setBorder(BorderFactory.createEmptyBorder(0, Escalador.escalar(20), 0, 0));
   		panelLogo.add(foto, BorderLayout.WEST);
   	
   		panelBtn.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, Escalador.escalar(30)));
   		panelBtn.add(Box.createRigidArea(Escalador.newDimension(0, Escalador.escalar(40))));
   		
   		if (btn!=null) {
   			panelBtn.add(btn);
   		}
   		setLayout(null);
   		
   		
           
   		panelBorder.add(panelLogo, BorderLayout.WEST);
   		panelBorder.add(panelBtn, BorderLayout.EAST);
   		
   		
   		
   		//Línea	
   		
   		JLabel linea = new JLabel();
   		
   		ImageIcon imgLinea = new ImageIcon(getClass().getResource("../srcmedia/líneaNavBar.png"));
   		Image imagenEscaladaLinea = imgLinea.getImage().getScaledInstance(Escalador.escalar(1000), Escalador.escalar(225), Image.SCALE_SMOOTH);
   		imgLinea = new ImageIcon(imagenEscaladaLinea);
   		linea.setIcon(imgLinea);
   		linea.setBounds(0, Escalador.escalar(-87), Escalador.escalar(1000), Escalador.escalar(225));
   		
   		
   		//UserIcon
   		
   		JLabel userIcon = new JLabel();
   		ImageIcon imgUserIcon = new ImageIcon(getClass().getResource("../srcmedia/userIcon.png"));
   		Image imagenEscaladaUserIcon = imgUserIcon.getImage().getScaledInstance(Escalador.escalar(100), Escalador.escalar(100), Image.SCALE_SMOOTH);
   		imgUserIcon = new ImageIcon(imagenEscaladaUserIcon);
   		userIcon.setIcon(imgUserIcon);
   		userIcon.setBounds(Escalador.escalar(825), Escalador.escalar(20), Escalador.escalar(100), Escalador.escalar(100));
   		
   		
   		
   		
   		//this.add(userIcon);
   		this.add(linea);
   		this.add(panelBorder);
   		
   		
   		setPreferredSize(Escalador.newDimension(1000, 130));
   		
	}
}
