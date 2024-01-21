package componentes;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import juego.Boosts;
import objetos.Jugador;
import utils.Audio;
import utils.Configuracion;
import utils.Escalador;
import utils.Session;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class PanelBoost extends JPanel {
    private Color colorFondo = Configuracion.BACKGROUND;
    private JLabel descripcionLabel = new JLabel("Descripción");
    private boostItem boostMalPresagio = new boostItem(descripcionLabel,"Mal Presagio","Ganas en 20 movimientos","presagio");
    private boostItem boostHielo = new boostItem(descripcionLabel,"Hielo","Congelas un area 3x3","hielo");
    private boostItem boostBomba = new boostItem(descripcionLabel,"Bomba","Bomba en pieza","bomba");
    private boostItem boostControl = new boostItem(descripcionLabel,"Control","Controlas una pieza enemiga","control");
    private boostItem boostMina = new boostItem(descripcionLabel,"Mina","Bomba en casilla","mina");
    private boostItem debug = new boostItem(descripcionLabel,"Explosión","Explosión","bomba");
    
    private ArrayList<boostItem> boosts = new ArrayList<>(Arrays.asList(boostBomba, boostHielo, boostMalPresagio, boostControl, boostMina ,debug));
    JPanel panelElementos = new JPanel();
    
    
    public PanelBoost() {
        setLayout(new BorderLayout());
        setBackground(colorFondo);

        
        panelElementos.setLayout(new BoxLayout(panelElementos, BoxLayout.Y_AXIS));
        panelElementos.setBackground(colorFondo);

        

        
        MScrollPane scrollPane = new MScrollPane(panelElementos);
        scrollPane.setBorder(null);
        add(scrollPane, BorderLayout.CENTER);

        // Panel Descripción
        JPanel panelDescripcion = new JPanel(new BorderLayout());

        
        JLabel iconoLabel = new JLabel();
        
        ImageIcon iconoImg = new ImageIcon(getClass().getResource("/srcmedia/infoIcon.png"));
        Image iconoImgEscalada = iconoImg.getImage().getScaledInstance((int) (Escalador.escalar(15)), (int) (Escalador.escalar(15)), Image.SCALE_SMOOTH);
		
        iconoLabel.setIcon(new ImageIcon(iconoImgEscalada));
        
        
        
        
        iconoLabel.setHorizontalAlignment(JLabel.LEFT);
        panelDescripcion.add(iconoLabel, BorderLayout.WEST);

        
        descripcionLabel.setHorizontalAlignment(JLabel.CENTER);
        
        panelDescripcion.add(descripcionLabel, BorderLayout.CENTER);

        panelDescripcion.setBackground(colorFondo);
        add(panelDescripcion, BorderLayout.SOUTH);
        
        
		
		
        
    }


	public JLabel getDescripcionLabel() {
		return descripcionLabel;
	}


	public void reloadBoosts(Jugador curPlayer) {
		System.out.println(curPlayer.getBoosts());
		panelElementos.removeAll();
		int i= 0;
		for (boolean boost : curPlayer.getBoosts()) {
			if (boost || Configuracion.DEBUG_MODE) {
				panelElementos.add(boosts.get(i));
		        
			}
			i++;
		}
		
		if ((Session.getDatosPartida().isOnline()||Session.getDatosPartida().isBotGame()))  {
			boolean enable = true;
			if (curPlayer.getUsuario().equals(Session.getCurrentUser().getUsername())) {
				enable = true;
			}
			else {
				enable = false;
			}
			for (boostItem boost : boosts) {
				boost.setButtonEnabled(enable);
			}
		}
	}
	
	


    
    
    
    
}