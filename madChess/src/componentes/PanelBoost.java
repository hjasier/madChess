package componentes;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import juego.Boosts;
import utils.Audio;
import utils.Configuracion;
import utils.Escalador;
import utils.Session;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelBoost extends JPanel {
    Color colorFondo = Configuracion.BACKGROUND;
    JLabel descripcionLabel = new JLabel("Descripción");
    
    
    public PanelBoost() {
        setLayout(new BorderLayout());
        setBackground(colorFondo);
        
        // Panel de elementos
        JPanel panelElementos = new JPanel();
        panelElementos.setLayout(new BoxLayout(panelElementos, BoxLayout.Y_AXIS));
        panelElementos.setBackground(colorFondo);

        boostItem boostMalPresagio = new boostItem(descripcionLabel,"Mal Presagio","Ganas en 20 movimientos","presagio");
        boostItem boostHielo = new boostItem(descripcionLabel,"Hielo","Congelas un area 3x3","hielo");
        boostItem boostBomba = new boostItem(descripcionLabel,"Bomba","Bomba en pieza","bomba");
        boostItem boostControl = new boostItem(descripcionLabel,"Control","Controlas una pieza enemiga","control");
        boostItem boostMina = new boostItem(descripcionLabel,"Mina","Bomba en casilla","mina");
        boostItem debug = new boostItem(descripcionLabel,"Explosión","Explosión","bomba");
        
        
        panelElementos.add(boostMalPresagio);
        panelElementos.add(boostHielo);
        panelElementos.add(boostBomba);
        panelElementos.add(boostControl);
        panelElementos.add(boostMina);
        panelElementos.add(debug);
        

        
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
	
	


    
    
    
    
}