package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import objetos.*;


public class VentanaJuego extends JFrame {
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




