package componentes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import objetos.Jugador;
import utils.Configuracion;
import utils.Escalador;

public class userInfo extends JPanel{
	
	protected JLabel labelUsuario; 
	protected timerTag labelTemp; 
	protected int tiempoRestante;
	protected boolean stopTimer;
	private userTag userTag;
	
	public userInfo() {	
		
		
		
		this.setLayout(new BorderLayout());
		this.setMaximumSize(new Dimension(Integer.MAX_VALUE, Escalador.escalar(55)));
		this.setBackground(Configuracion.BACKGROUND);
		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		
	        
		
		
		userTag = new userTag();
		
		
		
		
		
		labelTemp = new timerTag();
		
		labelTemp.setForeground(Color.white);
		
		
		this.add(userTag,BorderLayout.WEST);
		this.add(labelTemp,BorderLayout.EAST);

	}


	public void resizePanel(int tableroSize) {
		
		this.setMaximumSize(new Dimension(Integer.MAX_VALUE, Escalador.escalar(55)));
	}

	public void setUsuario(Jugador jugador) {
		userTag.setUser(jugador);
	}




	public void setTemp(int restante) {
		this.labelTemp.setTime(restante+"");
		this.stopTimer = true;
		this.tiempoRestante = restante;
		labelTemp.setON(false);
	}
	
	public void startTemp() {
		labelTemp.setON(true);
		stopTimer = false;
	    Timer timer = new Timer(1000, new ActionListener() { // Temporizador
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            // Si stopTimer es true, detener el temporizador
	            if (stopTimer) {
	                ((Timer) e.getSource()).stop();
	                return;
	            }

	            // Cambia el tiempo en labelTemp
	            tiempoRestante--;
	            labelTemp.setTime(String.valueOf(tiempoRestante));

	            // Si el tiempo llega a 0, el temporizador para
	            if (tiempoRestante == 0) {
	                ((Timer) e.getSource()).stop();
	            }
	        }
	    });
	    timer.start();
	}

		


	       
	       
	    

}
