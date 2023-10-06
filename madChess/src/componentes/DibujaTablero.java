package componentes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class DibujaTablero extends JPanel {

	private static final long serialVersionUID = 1L;

	int originX = 55;
	int originY = 45;
	int size = 60;
	
	public void paint(Graphics g) {
		super.paintChildren(g);
		
		Graphics2D g2 = (Graphics2D) g;	
		drawBoard(g2);
	}
	
	private void drawBoard(Graphics2D g2) {
		for (int j = 0; j < 4; j++) {
			for (int i = 0; i < 4; i++) {
				drawSquare(g2, 0 + 2 * i, 0 + 2 * j, true);
				drawSquare(g2, 1 + 2 * i, 1 + 2 * j, true);
				
				drawSquare(g2, 1 + 2 * i, 0 + 2 * j, false);
				drawSquare(g2, 0 + 2 * i, 1 + 2 * j, false);
			}
		}
	}
	
	private void drawSquare(Graphics2D g2, int col, int row, boolean light) {
		g2.setColor(light ? Color.white : Color.gray);
		g2.fillRect(originX + col * size, originY + row * size,size, size);
	}
}
