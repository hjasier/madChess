package componentes;

import javax.swing.JPanel;
import javax.swing.JRootPane;

import juego.Boosts;
import utils.Audio;
import utils.Configuracion;
import utils.Escalador;
import utils.Session;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Component;
import java.awt.Rectangle;
import java.awt.color.ColorSpace;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class boostItem extends JPanel{
	
	public boostItem(JLabel descripcionLabel, String nombre, String descripcion, String icono) {

		setBackground(Configuracion.BACKGROUND);
		setLayout(null);
		setPreferredSize(Escalador.newDimension(30, 100));
		
		JPanel panel = new JPanel();
		panel.setBackground(Configuracion.BACKGROUND);
		panel.setBounds(0, Escalador.escalar(10), Escalador.escalar(400), Escalador.escalar(80));
		add(panel);
		panel.setLayout(null);
		
		
		JLabel lblNewLabel = new JLabel(nombre);
		lblNewLabel.setBounds(Escalador.newBounds(8, 0, 153, 80));
		panel.add(lblNewLabel);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		
		
		BButton usarButton = new BButton("Usar");
		usarButton.setBounds(new Rectangle(215, 20, 72, 35));
		
		panel.add(usarButton);
		ImageIcon iconoImg = new ImageIcon(getClass().getResource("../srcmedia/"+icono+".png"));
        Image imagenEscalada = iconoImg.getImage().getScaledInstance((int) (Escalador.escalar(35)), (int) (Escalador.escalar(35)), Image.SCALE_SMOOTH);
		
		JLabel lblNewLabel_1_1 = new JLabel("1/1");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setBounds(new Rectangle(334, 0, 28, 80));
		
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1 = new JLabel();
		lblNewLabel_1.setBounds(Escalador.escalar(110),Escalador.escalar(35)/2,Escalador.escalar(35),Escalador.escalar(35));
		
		
		lblNewLabel_1.setIcon(new ImageIcon(imagenEscalada));
		lblNewLabel_1.setForeground(Color.WHITE);
		
		panel.add(lblNewLabel_1);
		
		
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setBackground(new Color(48,48,48));
				descripcionLabel.setText(nombre);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				setBackground(Configuracion.BACKGROUND);
			}
			
			
		});
		
		
		usarButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				activarBoost(nombre);
			}
		});
	}
	
	private void activarBoost(String boost) {
		switch ((String) boost) {
			case "Mal Presagio":
				Boosts.boostMalPresagio();
			    break;
			case "Hielo":
				Boosts.boostHielo();
				break;
			case "Bomba":
				Boosts.boostBomba();
				break;
			case "Control":
				Boosts.boostControl();
			    break;
			case "Explosión":
				//DEBUG 
	        	System.out.println("DEBUGEANDO ANIMACIÓN");
	            Session.getPartida().getTablero().initAnimacionExplosion(Session.getPartida().getCasilla(0,(char)'A'));
	            Audio.play("explosion.wav");
				break;
		}
		
    }
}
