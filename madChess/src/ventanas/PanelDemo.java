package ventanas;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelDemo extends JPanel{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JButton btn1;
    public JButton btn2;
    public JButton btn3;
    public JButton btn4;
    
	public PanelDemo() {
		
		btn1 = new JButton("Login");
		btn2 = new JButton("Juego");
		
		btn3 = new JButton("Crear partida online");
		btn4 = new JButton("Join partida online");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btn1);
        buttonPanel.add(btn2);
        
        buttonPanel.add(btn3);
        buttonPanel.add(btn4);
        

        this.add(buttonPanel, BorderLayout.NORTH);


        
        
        
        
	}
	
	
	public JButton getBtn1() {
		return btn1;
	}
	
	public JButton getBtn2() {
		return btn2;
	}
	
}
