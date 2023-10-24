package ventanas;

import java.awt.Color;
import java.awt.Panel;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
        

        try {
            BufferedImage wPic = ImageIO.read(this.getClass().getResource("../src_piezas/bbb.jpg"));
            JLabel wIcon = new JLabel(new ImageIcon(wPic));
             this.add(wIcon);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
       
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        
    }
    
    
    public Tablero getTableroDiv() {
		return tableroDiv;
	}

}




