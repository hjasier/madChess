package componentes;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import objetos.Jugador;
import utils.Configuracion;
import utils.Escalador;
import java.awt.Dimension;

public class timerTag extends JPanel{
	
	double escala = 0.75;
	JLabel userLabel = new JLabel("");
	JLabel userTagBg = new JLabel();
	
	JLabel userFotoLabel = new JLabel();
	
	
	public timerTag() {
	   
	    
	    setBackground(Configuracion.BACKGROUND);
	    setBounds(0,0, Escalador.escalar(190), Escalador.escalar(45));
	    setPreferredSize(Escalador.newDimension(150, 50));

	    
	    
	    // timerTagBg
	    ImageIcon userTagBgft = new ImageIcon(getClass().getResource("../srcmedia/timerTag.png"));
	    Image imagenEscalada = userTagBgft.getImage().getScaledInstance((int) (Escalador.escalar(146) * escala), (int) (Escalador.escalar(49) * escala), Image.SCALE_SMOOTH);
	    
	    userTagBg.setIcon(new ImageIcon(imagenEscalada));
	    userTagBg.setBounds(0, 0, (int) (Escalador.escalar(146)*escala), (int) (Escalador.escalar(45)*escala));

	    
	    
	    // fotoGif
	    ImageIcon userFoto = new ImageIcon(getClass().getResource("../srcmedia/relojGifInv.png"));
	    Image imgEscaladaUser = userFoto.getImage().getScaledInstance((int) (Escalador.escalar(30) * escala), (int) (Escalador.escalar(30) * escala), Image.SCALE_SMOOTH);
	    
	    userFotoLabel.setIcon(new ImageIcon(imgEscaladaUser));
	    userFotoLabel.setBounds((int) (Escalador.escalar(14)*escala), (int) (Escalador.escalar(7)*escala), (int) (Escalador.escalar(30)*escala), (int) (Escalador.escalar(30)*escala));

	    
	    //userLabel
	    
	    userLabel.setForeground(Color.white);
	    userLabel.setBounds((int) (Escalador.escalar(70)*escala), (int) (Escalador.escalar(10)*escala), (int) (Escalador.escalar(100)*escala), (int) (Escalador.escalar(30)*escala));
	    
	    
	    
	    setLayout(null);
	    add(userFotoLabel);
	    add(userLabel);
	    add(userTagBg);

	}

	public void setTime(String time) {
		userLabel.setText(time);
	}
	
	public void setON(boolean isOn) {
	    // timerTagBg
		ImageIcon userTagBgft;
		ImageIcon userFoto;
		if (isOn) {
			userTagBgft = new ImageIcon(getClass().getResource("../srcmedia/timerTagON.png"));
			userFoto = new ImageIcon(getClass().getResource("../srcmedia/relojGifInv.gif"));
		}
		else {
			userTagBgft = new ImageIcon(getClass().getResource("../srcmedia/timerTag.png"));
			userFoto = new ImageIcon(getClass().getResource("../srcmedia/relojGifInv.png"));
		}
		userFoto = new ImageIcon(getClass().getResource("../srcmedia/relojGifInv.png")); // el gif no va ns pk
		Image imagenEscalada = userTagBgft.getImage().getScaledInstance((int) (Escalador.escalar(146) * escala), (int) (Escalador.escalar(49) * escala), Image.SCALE_SMOOTH);
	    userTagBg.setIcon(new ImageIcon(imagenEscalada));
	    
	  
	    Image imgEscaladaUser = userFoto.getImage().getScaledInstance((int) (Escalador.escalar(30) * escala), (int) (Escalador.escalar(30) * escala), Image.SCALE_SMOOTH);

	    userFotoLabel.setIcon(new ImageIcon(imgEscaladaUser));
  
	}
    
    
    

}
