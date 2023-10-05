package componentes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class Casilla extends JPanel {
    private static final long serialVersionUID = 1L;
    protected int height;
    protected Color color = Color.white;
    protected int xPos;
    protected int yPos;
    
        

	public Casilla(Color color, int xPos, int yPos , int height) {
		this.height = height;
		this.color = color;
		this.xPos = xPos;
		this.yPos = yPos;
		this.setPreferredSize(new Dimension(height, height));
		this.setBorder(new LineBorder(Color.BLACK));

	}
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        g.setColor(this.color);
        g.fillRect(this.xPos, this.yPos, this.height, this.height);
        
    }
	

}
