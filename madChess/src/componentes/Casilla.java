package componentes;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

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
	}



	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(color);
        g.fillRect(xPos, yPos, height, height);
    }
}
