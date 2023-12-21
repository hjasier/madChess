package componentes;

import javax.swing.JPanel;

import utils.Escalador;

import javax.swing.BoxLayout;
import javax.swing.JButton;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JScrollPane;

import juego.VAR;

import java.awt.Color;

public class varController extends JPanel{
	
	public varController() {

		setLayout(null);
		
		
		JButton btnAvanzar = new JButton("Avanzar");
		btnAvanzar.setBounds(Escalador.newBounds(155, 10, 159, 21));
		add(btnAvanzar);
		
		JButton btnRetroceder = new JButton("Retroceder");
		btnRetroceder.setBounds(Escalador.newBounds(29, 10, 127, 21));
		add(btnRetroceder);
		
		JButton btnReanudarPartida = new JButton("Reanudar partida");
		btnReanudarPartida.setBounds(Escalador.newBounds(29, 41, 285, 21));
		add(btnReanudarPartida);
		
		JPanel movimientos = new JPanel();
		
		JScrollPane scrollPane = new JScrollPane(movimientos);
		scrollPane.setBounds(Escalador.newBounds(35, 85, 279, 176));
		scrollPane.setBorder(null);
		
		
		movimientos.setLayout(new BoxLayout(movimientos, BoxLayout.Y_AXIS));
		movimientos.setBackground(Color.red);
		
		add(scrollPane);
		
		for (int i = 0; i < 12; i++) {
			JPanel nPanel = new JPanel();
			nPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
			nPanel.add(new movimientoItem());
            movimientos.add(nPanel);
        }
		
		
		btnAvanzar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				VAR.avanzarMov();
				
			}
		});
		
		btnRetroceder.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				VAR.retrodecerMov();
				
			}
		});
		
		
		
	}
}
