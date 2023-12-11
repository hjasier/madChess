package componentes;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import objetos.Jugador;
import utils.Configuracion;
import utils.Escalador;

public class userTag extends JPanel{
	
	double escala = 0.85;
	JLabel userLabel = new JLabel("user");
	
	public userTag() {
	   
	    
	    setBackground(Configuracion.BACKGROUND);
	    setBounds(Escalador.escalar(0), Escalador.escalar(0), Escalador.escalar(190), Escalador.escalar(45));
	    setPreferredSize(Escalador.newDimension(250, 55));

	    
	    
	    // userTagBg
	    ImageIcon userTagBgft = new ImageIcon(getClass().getResource("../srcmedia/userTagBg.png"));
	    Image imagenEscalada = userTagBgft.getImage().getScaledInstance((int) (Escalador.escalar(237) * escala), (int) (Escalador.escalar(49) * escala), Image.SCALE_SMOOTH);
	    JLabel userTagBg = new JLabel();
	    userTagBg.setIcon(new ImageIcon(imagenEscalada));
	    userTagBg.setBounds(0, 0, (int) (Escalador.escalar(237)*escala), (int) (Escalador.escalar(45)*escala));

	    
	    
	    // userFoto
	    ImageIcon userFoto = new ImageIcon(getClass().getResource("../srcmedia/userIcon.png"));
	    Image imgEscaladaUser = userFoto.getImage().getScaledInstance((int) (Escalador.escalar(30) * escala), (int) (Escalador.escalar(30) * escala), Image.SCALE_SMOOTH);
	    JLabel userFotoLabel = new JLabel();
	    userFotoLabel.setIcon(new ImageIcon(imgEscaladaUser));
	    userFotoLabel.setBounds((int) (Escalador.escalar(14)*escala), (int) (Escalador.escalar(7)*escala), (int) (Escalador.escalar(30)*escala), (int) (Escalador.escalar(30)*escala));

	    
	    //userLabel
	    
	    userLabel.setForeground(Color.white);
	    userLabel.setBounds((int) (Escalador.escalar(55)*escala), (int) (Escalador.escalar(7)*escala), (int) (Escalador.escalar(100)*escala), (int) (Escalador.escalar(30)*escala));
	    
	    
	    
	    setLayout(null);
	    add(userFotoLabel);
	    add(userLabel);
	    add(userTagBg);

	}

	public void setUser(Jugador jugador) {
		
		userLabel.setText(jugador.getNombre());
	}
    
    
    

}
