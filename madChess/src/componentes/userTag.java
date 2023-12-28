package componentes;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import objetos.Jugador;
import utils.Configuracion;
import utils.Escalador;
import utils.Session;
import utils.utils;

public class userTag extends JPanel{
	
	double escala = 0.85;
	private JLabel userLabel = new JLabel("user");
	private JLabel userFotoLabel = new JLabel();
	
	public userTag() {
	   
	    
	    setBackground(Configuracion.BACKGROUND);
	    setBounds(Escalador.escalar(0), Escalador.escalar(0), Escalador.escalar(190), Escalador.escalar(45));
	    setPreferredSize(Escalador.newDimension(210, 55));

	    
	    
	    // userTagBg
	    ImageIcon userTagBgft = new ImageIcon(getClass().getResource("/srcmedia/userTagBg.png"));
	    Image imagenEscalada = userTagBgft.getImage().getScaledInstance((int) (Escalador.escalar(237) * escala), (int) (Escalador.escalar(49) * escala), Image.SCALE_SMOOTH);
	    JLabel userTagBg = new JLabel();
	    userTagBg.setIcon(new ImageIcon(imagenEscalada));
	    userTagBg.setBounds(0, 0, (int) (Escalador.escalar(237)*escala), (int) (Escalador.escalar(45)*escala));

	    
	    
	    // userFoto
	    ImageIcon userFoto = new ImageIcon(getClass().getResource("/srcmedia/userIcon.png"));
	    Image imgEscaladaUser = userFoto.getImage().getScaledInstance((int) (Escalador.escalar(30) * escala), (int) (Escalador.escalar(30) * escala), Image.SCALE_SMOOTH);
	    
	    userFotoLabel.setIcon(new ImageIcon(imgEscaladaUser));
	    userFotoLabel.setBounds((int) (Escalador.escalar(14)*escala), (int) (Escalador.escalar(6)*escala), (int) (Escalador.escalar(30)*escala), (int) (Escalador.escalar(30)*escala));

	    
	    //userLabel
	    
	    userLabel.setForeground(Color.white);
	    userLabel.setBounds((int) (Escalador.escalar(55)*escala), (int) (Escalador.escalar(7)*escala), (int) (Escalador.escalar(100)*escala), (int) (Escalador.escalar(30)*escala));
	    
	    
	    
	    setLayout(null);
	    add(userFotoLabel);
	    add(userLabel);
	    add(userTagBg);

	}

	public void setUser(Jugador jugador) {
		userLabel.setText(jugador.getUsuario().getUsername());
		
		
		Thread cargaImg = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                	BufferedImage img = utils.getRoundImg(jugador.getUsuario().getImg_route(), (int) (Escalador.escalar(30)* escala));	
            		userFotoLabel.setIcon(new ImageIcon(img));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
		
		cargaImg.start();
	}
    
    
    

}
