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

public class userInfo extends JPanel{
	
	protected JLabel labelUsuario; 
	protected JLabel labelTemp; 
	protected int tiempoRestante;
	
	public userInfo() {
		tiempoRestante = 100;
		
		this.setLayout(new BorderLayout());
		this.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
		this.setBackground(new Color(16,16,16));
		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		
		Font font = new Font("Arial", Font.BOLD, 13); 
	        
		
		labelUsuario = new JLabel("Usuario 1");
		labelUsuario.setForeground(Color.white);
		labelUsuario.setSize(10,10);
		labelUsuario.setFont(font);
		
		labelTemp = new JLabel("100");
		labelTemp.setForeground(Color.white);
		labelTemp.setFont(font);
		
		this.add(labelUsuario,BorderLayout.WEST);
		this.add(labelTemp,BorderLayout.EAST);
		
		
		 Timer timer = new Timer(1000, new ActionListener() { //Temporizador
	            @Override
	            public void actionPerformed(ActionEvent e) {
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
