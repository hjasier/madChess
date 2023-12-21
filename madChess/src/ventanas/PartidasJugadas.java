package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import componentes.MScrollPane;
import componentes.partidaTag;
import database.GestorDB;
import juego.DatosPartida;
import utils.Configuracion;
import utils.Escalador;
import utils.Session;

import javax.swing.BoxLayout;

public class PartidasJugadas extends JPanel{
	
	JScrollPane scrollPane;
	JPanel panelElementos = new JPanel();
	
	public PartidasJugadas() {
		
		 setLayout(null);
		
		 scrollPane = new JScrollPane(panelElementos);
		 scrollPane.setBounds(Escalador.newBounds(300, 100, 500, 500));
	     scrollPane.setBorder(null);
	     add(scrollPane);
	     
	     
	     scrollPane.setPreferredSize(new Dimension(Escalador.newDimension(500, 400)));
	     panelElementos.setLayout(new BoxLayout(panelElementos, BoxLayout.Y_AXIS));
	     
	     
	     

	     
	     
	}
	
	public void cargarPartidas(){
		new Thread(() -> {
			System.out.println("Geteando partidas");
			List<DatosPartida> partidas = GestorDB.getPartidas(Session.getCurrentUser().getUsername());
			System.out.println(partidas);
			
			for (DatosPartida partida : partidas) {
				System.out.println("Añadiendo partida");
				panelElementos.add(new partidaTag(partida));
				
			}
			panelElementos.revalidate();
			panelElementos.repaint();
	    }).start();
		
		
	}
	
	
    

    
    
		
		
		
}
