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
    
    
	public PanelDemo() {
		
		btn1 = new JButton("Login");
		btn2 = new JButton("Juego");


        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btn1);
        buttonPanel.add(btn2);

        this.add(buttonPanel, BorderLayout.NORTH);


        
        
        
        
	}
	
	
	public JButton getBtn1() {
		return btn1;
	}
	
	public JButton getBtn2() {
		return btn2;
	}
	
}
