package componentes;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

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
	private int ventaja = 0;
	private int ventajaOponente = 0;
	double escala = 0.85;
	private JLabel puntosLabel = new JLabel("");
	private JLabel piezaImgLabel = new JLabel();
	
	
	JPanel panelGeneral = new JPanel();
	
	
	public userPuntos() {
	   
	    
	    setBackground(Configuracion.BACKGROUND);
	    setBounds(Escalador.escalar(0), Escalador.escalar(0), Escalador.escalar(335), Escalador.escalar(45));
	    setPreferredSize(Escalador.newDimension(335, 45));

	    
	    
	    // Cubierta gris
	    ImageIcon puntosTagBgft = new ImageIcon(getClass().getResource("/srcmedia/userTagBg.png"));
	    Image imagenEscalada = puntosTagBgft.getImage().getScaledInstance((int) (Escalador.escalar(335) * escala), (int) (Escalador.escalar(49) * escala), Image.SCALE_SMOOTH);
	    JLabel puntosTag = new JLabel();
	    puntosTag.setIcon(new ImageIcon(imagenEscalada));
	    puntosTag.setBounds(0, 0, (int) (Escalador.escalar(335)*escala), (int) (Escalador.escalar(45)*escala));

	    
	    

	    
	    puntosLabel.setForeground(Color.white);
	    puntosLabel.setBounds(Escalador.newBounds(12,-1, 55, 45));

	    MScrollPane scroll = new MScrollPane(panelGeneral);
	    scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	    scroll.setBorder(null);
        
	    panelGeneral.setLayout(new BoxLayout(panelGeneral, BoxLayout.X_AXIS));
	    scroll.setBounds( Escalador.newBounds(40,5, 230, 25));
	    panelGeneral.setBackground(Configuracion.BACKGROUND);
	    
	    
	    panelGeneral.setSize(Escalador.newDimension(230, 25));
	    
	    setLayout(null);
	    add(puntosTag);
	    add(puntosLabel);
	    add(scroll);
	    panelGeneral.add(piezaImgLabel);
	    
	    
	    
	    
	    
	    
	    
	    
	}
	public void addPieza(Pieza pieza) {
		JLabel imgLabel = new JLabel();
		
		ImageIcon piezaImg = pieza.getImg();
		
		Image imgEscaladaPieza = piezaImg.getImage().getScaledInstance((int) (Escalador.escalar(30) * escala), (int) (Escalador.escalar(30) * escala), Image.SCALE_SMOOTH);
	    
		imgLabel.setIcon(new ImageIcon(imgEscaladaPieza));
		
	    panelGeneral.add(imgLabel);
	    repaint();
    }

	public void setPuntos(Jugador jugador, Pieza pieza) { 
		
		
		int valor = getValor(pieza);
		
		if (pieza.getIsWhite() == jugador.getIsWhite()) {
			ventaja += valor;
			
			addPieza(pieza);
			
		}else {
			ventajaOponente += valor;
		}
		
		if (ventaja <= ventajaOponente) {
			
			puntosLabel.setText(""); //Porque no hay ventaja
		}else {
			ventaja = ventaja - ventajaOponente;
			puntosLabel.setText("+" + ventaja);
		}
		
		
		
		
		
		
	}
	private int getValor(Pieza pieza) {
		int[] puntosPieza = {1, 3, 3, 5, 9}; //Peon, Alfil, Caballo, Torre, Reina 
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
		
		return valor;
	}
	
	public void resetPuntos() {
		panelGeneral.removeAll();
	}
}
