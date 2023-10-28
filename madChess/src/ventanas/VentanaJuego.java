package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import objetos.*;


public class VentanaJuego extends JFrame {
	private static final long serialVersionUID = 1L;
	protected JPanel panelJuego;
	protected JPanel panelControles;
	
	protected Tablero tablero = new Tablero();
	
    public VentanaJuego() {
    	int[] ventanaSize = {800,800};

    	
        this.setSize(ventanaSize[0], ventanaSize[1]);
        this.setMinimumSize(new java.awt.Dimension(450, 450));
        this.setBackground(new Color(60,60,60)); //                                 FONDO DE LA VENTANA
        this.setLocationRelativeTo(null);

        
        Panel fondo = new Panel();
        fondo.setBackground(this.getBackground());
        fondo.setLayout(new FlowLayout());
        
        panelJuego = new JPanel();
        panelControles = new JPanel();
        
        panelJuego.setLayout(null);
        panelControles.setLayout(new GridLayout());
        

        panelJuego.add(tablero);
        
        fondo.add(panelJuego);
        this.add(fondo);

       
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        

        

        
        
        
        tablero.recalcularTamanyo();
        this.addComponentListener(new ComponentAdapter() {
        	@Override
        	public void componentResized(ComponentEvent e) {
        		tablero.recalcularTamanyo();
        	}
        	//FIXME: Al maximizar la ventana también tendría que funcionar
		});
        
        
        
    }
    
    
    public Tablero getTableroDiv() {
		return tablero;
	}

}




