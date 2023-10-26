package ventanas;

import java.awt.Color;
import java.awt.Panel;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import objetos.*;


public class VentanaJuego extends JFrame {
	private static final long serialVersionUID = 1L;
	protected Tablero tablero = new Tablero();
	
    public VentanaJuego() {
    	int[] ventanaSize = {800,800};

    	
        this.setSize(ventanaSize[0], ventanaSize[1]);
        this.setMinimumSize(new java.awt.Dimension(450, 450));
        this.setBackground(new Color(60,60,60));
        this.setLocationRelativeTo(null);

        
        Panel fondo = new Panel();
        fondo.setBackground(this.getBackground());
        fondo.setLayout(null);
        
        fondo.add(tablero);
        

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




