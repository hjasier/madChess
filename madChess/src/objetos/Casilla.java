package objetos;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.*;
import javax.swing.border.LineBorder;


public class Casilla extends JPanel {
    private static final long serialVersionUID = 1L;
    protected Color color = Color.white;
    
        

	public Casilla(Color color) {
		this.color = color;
		this.setBorder(new LineBorder(Color.GREEN, 2)); // BORDE TEMPORAL PARA DEBUG
        this.setOpaque(true);
		
		
		
        this.addMouseMotionListener(new MouseAdapter() {
        	
        	
    	/**
    	 * 
    	 * NO se por que mouseEntered y todo eso no funciona, si conseguis que tire avisad, la idea es es que se pueda saber en que casilla esta el cursor 
    	 */
        	
		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			System.out.println("cursor in en la casilla");
		}
		
		@Override
			public void mouseMoved(MouseEvent e) {
				iluminarCasilla(); 
			}
		
		

        });
	}


	
	protected void iluminarCasilla() {
		this.color = new Color( color.getRed()+5,color.getGreen()+5,color.getBlue()+5);
		this.paint(getGraphics());
		
	}



	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        g.setColor(this.color);
        int height = Math.min(getWidth(), getHeight());
        g.fillRect(0, 0, height, height); // xPos, yPos W , H
        
    }
	

}
