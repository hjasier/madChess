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
		 
   		setBackground(colorFondo);
   		panelLogo.setBackground(colorFondo);
   		panelBtn.setBackground(colorFondo);


   		setLayout(new BorderLayout());
   		
   		
   		// Escalar la imagen
         Image imagenEscalada = imgFoto.getImage().getScaledInstance(Escalador.escalar(317), Escalador.escalar(114), Image.SCALE_SMOOTH);
         imgFoto = new ImageIcon(imagenEscalada);
         foto.setIcon(imgFoto);

         panelLogo.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
   		panelLogo.add(foto, BorderLayout.WEST);
   	
   		panelBtn.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));
   		panelBtn.add(Box.createRigidArea(Escalador.newDimension(0, 80)));
   		
   		if (btn!=null) {
   			panelBtn.add(btn);
   		}
   		
   		
           
   		add(panelLogo, BorderLayout.WEST);
   		add(panelBtn, BorderLayout.EAST);
           
   		
	}
}
