package componentes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.*;


public class Casilla extends JPanel {
    private static final long serialVersionUID = 1L;
    protected int height;
    protected Color color = Color.white;
    
        

	public Casilla(Color color, int height) {
		this.height = height;
		this.color = color;


	}


	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        g.setColor(this.color);
        //g.fillRect(this.xPos, this.yPos, this.height, this.height);
        int height = Math.min(getWidth(), getHeight());
        
        System.out.println(getWidth());
        g.fillRect(0, 0, height, height);
        
    }
	

}
