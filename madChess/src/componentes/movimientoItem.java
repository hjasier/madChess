package componentes;

import java.awt.Component;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import utils.Escalador;
import utils.Session;
import ventanas.Paneles;
import ventanas.VentanaPrincipal;

import javax.swing.JButton;

public class movimientoItem extends JPanel{
	public movimientoItem() {
		
		setPreferredSize(Escalador.newDimension(210, 45));
		setAlignmentX(Component.CENTER_ALIGNMENT);

		double escala = 1;
		
	    ImageIcon bgRes = new ImageIcon(getClass().getResource("/srcmedia/partidaTagBack.png"));
		
	    Image imgEscalada = bgRes.getImage().getScaledInstance((int) (Escalador.escalar(200) * escala), (int) (Escalador.escalar(40) * escala), Image.SCALE_SMOOTH);
	    setLayout(null);
	    JLabel bg = new JLabel();
	    
	    
	    bg.setBounds(Escalador.newBounds(0,0,209, 44));
	    
	    
	    bg.setIcon(new ImageIcon(imgEscalada));
	    
		this.add(bg);
		
		JLabel lblNewLabel = new JLabel("H1 --> C5");
		lblNewLabel.setBounds(Escalador.newBounds(10, 10, 102, 17));
		add(lblNewLabel);
		
		JButton btnNewButton = new JButton("IR");
		btnNewButton.setBounds(Escalador.newBounds(128, 8, 52, 21));
		add(btnNewButton);
		
		
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaPrincipal ventana = Session.getVentana();
				ventana.getCardLayout().show(ventana.getPanelPrincipal(), Paneles.JUEGO);
			}
		});
	}
}
