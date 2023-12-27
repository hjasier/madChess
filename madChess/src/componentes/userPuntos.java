package componentes;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import objetos.Casilla;
import objetos.Jugador;
import objetos.Pieza;
import piezas.Alfil;
import piezas.Caballo;
import piezas.Peon;
import piezas.Reina;
import piezas.Torre;
import utils.Configuracion;
import utils.Escalador;
import utils.utils;

public class userPuntos extends JPanel{
	double escala = 0.85;
	private JLabel puntosLabel = new JLabel("Ventaja");
	private JLabel piezaImgLabel = new JLabel();
	
	public userPuntos() {
	   
	    
	    setBackground(Configuracion.BACKGROUND);
	    setBounds(Escalador.escalar(0), Escalador.escalar(0), Escalador.escalar(190), Escalador.escalar(45));
	    setPreferredSize(Escalador.newDimension(250, 55));

	    
	    
	    // Cubierta gris
	    ImageIcon puntosTagBgft = new ImageIcon(getClass().getResource("/srcmedia/userTagBg.png"));
	    Image imagenEscalada = puntosTagBgft.getImage().getScaledInstance((int) (Escalador.escalar(237) * escala), (int) (Escalador.escalar(49) * escala), Image.SCALE_SMOOTH);
	    JLabel puntosTag = new JLabel();
	    puntosTag.setIcon(new ImageIcon(imagenEscalada));
	    puntosTag.setBounds(0, 0, (int) (Escalador.escalar(237)*escala), (int) (Escalador.escalar(45)*escala));

	    
	    
	    // imagen pieza
	    ImageIcon piezaImg = new ImageIcon(getClass().getResource("/srcmedia/piezas/bpDF.png"));
	    Image imgEscaladaPieza = piezaImg.getImage().getScaledInstance((int) (Escalador.escalar(30) * escala), (int) (Escalador.escalar(30) * escala), Image.SCALE_SMOOTH);
	    
	    piezaImgLabel.setIcon(new ImageIcon(imgEscaladaPieza));
	    piezaImgLabel.setBounds((int) (Escalador.escalar(14)*escala), (int) (Escalador.escalar(6)*escala), (int) (Escalador.escalar(30)*escala), (int) (Escalador.escalar(30)*escala));

	    
	    //puntosLabel
	    
	    puntosLabel.setForeground(Color.white);
	    puntosLabel.setBounds((int) (Escalador.escalar(55)*escala), (int) (Escalador.escalar(7)*escala), (int) (Escalador.escalar(100)*escala), (int) (Escalador.escalar(30)*escala));
	    
	    
	    
	    setLayout(null);
	    add(puntosTag);
	    add(piezaImgLabel);
	    add(puntosLabel);
	    

	}

	public void setPuntos(Jugador jugador, ArrayList<Pieza> piezasComidas) { 
		//Hay que crear un metodo que recoja las piezas comidas
		
		int[] puntosPieza = {1, 3, 3, 5, 9}; //Peon, Alfil, Caballo, Torre, Reina 
		int ventaja = 0;
		int ventajaOponente = 0;
		for (Pieza pieza : piezasComidas) {
			int valor = 0;
			if (pieza instanceof Peon) {
				valor = puntosPieza[0];
			}else if (pieza instanceof Alfil) {
				valor = puntosPieza[1];
			}else if (pieza instanceof Caballo) {
				valor = puntosPieza[2];
			}else if (pieza instanceof Torre) {
				valor = puntosPieza[3];
			}else if (pieza instanceof Reina) {
				valor = puntosPieza[4];
			}
			
			
			if (pieza.getIsWhite() == jugador.getIsWhite()) {
				ventaja += valor;
			}else {
				ventajaOponente += valor;
			}
		}
		
		if (ventaja <= ventajaOponente) {
			
			puntosLabel.setText(""); //Porque no hay ventaja
		}else {
			ventaja = ventaja - ventajaOponente;
			puntosLabel.setText("+" + ventaja);
		}
		
		
		
		
		
		
	}
}
