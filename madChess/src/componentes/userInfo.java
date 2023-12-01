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

import utils.Escalador;

public class userInfo extends JPanel{
	
	protected JLabel labelUsuario; 
	protected JLabel labelTemp; 
	protected int tiempoRestante;
	protected boolean stopTimer;
	
	public userInfo() {		
		this.setLayout(new BorderLayout());
		this.setMaximumSize(new Dimension(Integer.MAX_VALUE, Escalador.escalar(35)));
		this.setBackground(new Color(16,16,16));
		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		
	        
		
		labelUsuario = new JLabel("user");
		labelUsuario.setForeground(Color.white);
		labelUsuario.setSize(10,10);
		
		
		labelTemp = new JLabel("100");
		labelTemp.setForeground(Color.white);
		
		
		this.add(labelUsuario,BorderLayout.WEST);
		this.add(labelTemp,BorderLayout.EAST);

	}




	public void setUsuario(String labelUsuario) {
		this.labelUsuario.setText(labelUsuario);
	}




	public void setTemp(int restante) {
		this.labelTemp.setText(restante+"");
		this.stopTimer = true;
		this.tiempoRestante = restante;
	}
	
	public void startTemp() {
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
	            labelTemp.setText(String.valueOf(tiempoRestante));

	            // Si el tiempo llega a 0, el temporizador para
	            if (tiempoRestante == 0) {
	                ((Timer) e.getSource()).stop();
	            }
	        }
	    });
	    timer.start();
	}

		


	       
	       
	    

}
