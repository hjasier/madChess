package ventanas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class test extends JFrame{
    JPanel panelPrincipal = new JPanel();
    CardLayout cardLayout = new CardLayout();
    
    
	public test() {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 300);
        this.setVisible(true);
        
        
        

        panelPrincipal.setLayout(cardLayout);

    
        JPanel homePanel = new JPanel();
        homePanel.add(new JLabel("Página de inicio"));

        JPanel menu2Panel = new JPanel();
        menu2Panel.add(new JLabel("Contenido de Menu2"));
        
        
        
        

        panelPrincipal.add(homePanel, "Home");
        panelPrincipal.add(menu2Panel, "TEST");
        

        this.add(panelPrincipal);
        
        
        
        
        
        
        
        
        

        
        JButton homeButton = new JButton("Home");
        JButton menu2Button = new JButton("Menu2");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(homeButton);
        buttonPanel.add(menu2Button);

        this.add(buttonPanel, BorderLayout.NORTH);

        homeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelPrincipal, "Home");
            }
        });

        menu2Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelPrincipal, "TEST");
            }
        });
        
        
        

        
        
        
        
       
	}
	
    public static void main(String[] args) {
    	new test();

    }
}
