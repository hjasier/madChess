package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import objetos.*;


public class VentanaJuegoTesteos extends JFrame {
	private static final long serialVersionUID = 1L;
	protected JPanel panelJuego;
	protected JPanel panelControles;
	
	protected TableroTests tablero = new TableroTests();
	
	public VentanaJuegoTesteos() {
	    int[] ventanaSize = {1000, 800};
	    this.setSize(ventanaSize[0], ventanaSize[1]);
	    this.setLocationRelativeTo(null);

	    // Panel principal con GridBagLayout
	    JPanel fondo = new JPanel(new GridBagLayout());
	    GridBagConstraints gbc = new GridBagConstraints();

	    // Paneles
	    JPanel panelJuego = new JPanel();
	    JPanel panelControles = new JPanel();

	    // Colores
	    fondo.setBackground(Color.red);
	    panelJuego.setBackground(Color.blue);
	    panelControles.setBackground(Color.green);

	    // PanelJuego
	    gbc.gridx = 0;
	    gbc.gridy = 0;
	    gbc.weightx = 1.0; //En panel del juego va a ocupar todo lo que pueda
	    gbc.weighty = 1.0; // Para que ocupen todo lo que puedan en vertical
	    gbc.fill = GridBagConstraints.BOTH;
	    fondo.add(panelJuego, gbc);

	    // PanelControles
	    gbc.gridx = 1;
	    gbc.weightx = 0.5; // El espacio que va a ocupar el panelControles
	    fondo.add(panelControles, gbc);

	    
	    
	    // Configura el panelJuego con BoxLayout en la dirección vertical
	    panelJuego.setLayout(new BoxLayout(panelJuego, BoxLayout.Y_AXIS));

	    // Agrega algunos elementos a panelJuego (por ejemplo, botones o etiquetas)


	    
	    panelJuego.add(new JLabel("Nombre1"));
	    panelJuego.add(tablero);
	    panelJuego.add(new JLabel("Nombre2"));
	
	    this.add(fondo);
	    
	    System.out.println(panelJuego.getSize());
	    
	    
        
        


       
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        

        tablero.recalcularTamanyo();
        
    
        
        
        
    }
	

}




