package ventanas;

import java.awt.Color;
import java.awt.Panel;

import javax.swing.JFrame;
import javax.swing.JPanel;


import objetos.*;


public class VentanaJuego extends JFrame {
	private static final long serialVersionUID = 1L;
	protected Tablero tableroDiv = new Tablero();
	protected JPanel panelDerecha = new JPanel(); 
    protected JPanel panelAbajo = new JPanel(); 
    
    public VentanaJuego() {
    	int[] ventanaSize = {800,800};

    	
        this.setSize(ventanaSize[0], ventanaSize[1]);
        this.setMinimumSize(new java.awt.Dimension(450, 450));
        this.setBackground(new Color(60,60,60));
        this.setLocationRelativeTo(null);

        
        Panel fondo = new Panel();
        fondo.setBackground(this.getBackground());
        fondo.setLayout(null);
        fondo.add(tableroDiv);
        

        this.add(fondo);

       
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        
    }
    
    
    public Tablero getTableroDiv() {
		return tableroDiv;
	}

}




