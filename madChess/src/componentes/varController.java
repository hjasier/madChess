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
import java.util.ArrayList;

import javax.swing.JScrollPane;

import juego.VAR;
import objetos.Movimiento;

import java.awt.Color;

public class varController extends JPanel{
	
	JPanel movimientos = new JPanel();
	
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
		
		
		
		JScrollPane scrollPane = new JScrollPane(movimientos);
		scrollPane.setBounds(Escalador.newBounds(35, 85, 279, 176));
		scrollPane.setBorder(null);
		
		
		movimientos.setLayout(new BoxLayout(movimientos, BoxLayout.Y_AXIS));
		
		add(scrollPane);
		
		
		
		
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

	public void loadMovs(ArrayList<Movimiento> arrayList) {
		JPanel panelSalida = new JPanel();
		panelSalida.setLayout(new FlowLayout(FlowLayout.CENTER));
		panelSalida.add(new movimientoItem(null,-1));
		movimientos.add(panelSalida);
		
		
		for (Movimiento movimiento : arrayList) {
			JPanel nPanel = new JPanel();
			nPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
			nPanel.add(new movimientoItem(movimiento,arrayList.indexOf(movimiento)));
			movimientos.add(nPanel);
		}
		
	}
}
