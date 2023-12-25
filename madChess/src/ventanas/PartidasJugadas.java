package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import componentes.MScrollPane;
import componentes.RButton;
import componentes.navBar;
import componentes.partidaTag;
import database.GestorDB;
import juego.DatosPartida;
import librerias.FontAwesome;
import librerias.IconFontSwing;
import utils.Configuracion;
import utils.Escalador;
import utils.Session;

import javax.swing.BoxLayout;
import javax.swing.Icon;

public class PartidasJugadas extends JPanel{
	
	JScrollPane scrollPane;
	JPanel panelElementos = new JPanel();
	protected RButton volverBtn = new RButton("Volver");
	
	public PartidasJugadas() {
		
		//--------------------- NAVBAR-------------------------------------------
  		Icon icon = IconFontSwing.buildIcon(FontAwesome.BACKWARD, Escalador.escalarF(15));
  		volverBtn.setIcon(icon);
  		
		JPanel navBarContainer = new JPanel(new FlowLayout());
		navBarContainer.add(new navBar(volverBtn));
        navBarContainer.setBackground(Configuracion.BACKGROUND);
        panelElementos.setBackground(Configuracion.BACKGROUND);
  		
  		//--------------------- NAVBAR-------------------------------------------	   
		
		 setLayout(new BorderLayout());
		
		 scrollPane = new JScrollPane(panelElementos);
		 scrollPane.setBounds(Escalador.newBounds(300, 100, 500, 500));
	     scrollPane.setBorder(null);
	     
	     add(navBarContainer, BorderLayout.NORTH);
	     add(scrollPane, BorderLayout.CENTER);
	     
	     
	     scrollPane.setPreferredSize(new Dimension(Escalador.newDimension(500, 400)));
	     panelElementos.setLayout(new BoxLayout(panelElementos, BoxLayout.Y_AXIS));
	     
	     
	     
	     volverBtn.addActionListener(new ActionListener() {
				
				
				
				
				@Override
				public void actionPerformed(ActionEvent e) {
					VentanaPrincipal ventanaPrincip = Session.getVentana();
					ventanaPrincip.getCardLayout().show(ventanaPrincip.getPanelPrincipal(), Paneles.PERFILUSUARIO);
				
				}
			});
	     
	     
	}
	
	public void cargarPartidas(){
		new Thread(() -> {
			
			
			System.out.println("Geteando partidas");
			mostrarCargando();
			List<DatosPartida> partidas = GestorDB.getPartidas(Session.getCurrentUser().getUsername());
			System.out.println(partidas);
			panelElementos.removeAll();
			if (partidas.size() == 0) {
				JPanel panelCentrado = new JPanel();
				panelCentrado.setBackground(Configuracion.BACKGROUND);
				panelCentrado.setLayout(new FlowLayout(FlowLayout.CENTER));
				panelCentrado.add(new JLabel("No se han encontrado partidas"));
				panelElementos.add(panelCentrado);
			
			}
			for (DatosPartida partida : partidas) {
				JPanel panelCentrado = new JPanel();
				panelCentrado.setBackground(Configuracion.BACKGROUND);
				panelCentrado.setLayout(new FlowLayout(FlowLayout.CENTER));
				panelCentrado.add(new partidaTag(partida));
				panelElementos.add(panelCentrado);
				
			}
			panelElementos.revalidate();
			panelElementos.repaint();
	    }).start();
		
		
	}

	private void mostrarCargando() {
		panelElementos.removeAll();
		JPanel panelCentrado = new JPanel();
		panelCentrado.setBackground(Configuracion.BACKGROUND);
		panelCentrado.setLayout(new FlowLayout(FlowLayout.CENTER));
		panelCentrado.add(new JLabel("Cargando partidas..."));
		panelElementos.add(panelCentrado);
	}
	
	
    

    
    
		
		
		
}
