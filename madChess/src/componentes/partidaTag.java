package componentes;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import juego.DatosPartida;
import juego.VAR;
import utils.Configuracion;
import utils.Escalador;
import utils.Session;
import ventanas.Paneles;
import ventanas.VentanaPrincipal;

import javax.swing.JButton;
import java.awt.Rectangle;

public class partidaTag extends JPanel{
	public partidaTag(DatosPartida partida) {
		
		setPreferredSize(Escalador.newDimension(510, 72));
		setBackground(Configuracion.BACKGROUND);
		double escala = 1;
		
	    ImageIcon bgRes = new ImageIcon(getClass().getResource("/srcmedia/partidaTagBack.png"));
		
	    Image imgEscalada = bgRes.getImage().getScaledInstance((int) (Escalador.escalar(500) * escala), (int) (Escalador.escalar(67) * escala), Image.SCALE_SMOOTH);
	    setLayout(null);
	    JLabel bg = new JLabel();
	    
	    
	    bg.setBounds(Escalador.newBounds(0,5,500, 67));
	    
	    
	    bg.setIcon(new ImageIcon(imgEscalada));
	    
		this.add(bg);
		
		JLabel lblNewLabel = new JLabel(partida.getGameId());
		lblNewLabel.setBounds(Escalador.newBounds(28, 5, 113, 57));
		add(lblNewLabel);
		
		JButton btnNewButton = new JButton("VAR");
		btnNewButton.setBounds(Escalador.newBounds(259, 23, 85, 21));
		add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel(partida.getJugadores().get(1).getUsuario().getUsername());
		lblNewLabel_1.setBounds(Escalador.newBounds(105, 5, 174, 57));
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel(partida.getFechaIni().toLocaleString());
		lblNewLabel_2.setBounds(Escalador.newBounds(374, 5, 113, 57));
		add(lblNewLabel_2);
		
		
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaPrincipal ventana = Session.getVentana();
				VAR.setDatosPartida(partida);
				ventana.getCardLayout().show(ventana.getPanelPrincipal(), Paneles.JUEGO);
			}
		});
	}
}
