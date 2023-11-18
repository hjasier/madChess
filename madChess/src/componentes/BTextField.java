package componentes;

import javax.swing.*;

import org.w3c.dom.Text;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BTextField extends JPasswordField  {

    private static final Color DEFAULT_COLOR = new Color(169, 169, 169);
    private static final Color HOVER_COLOR = new Color(220, 220, 220);
    private static final Color TEXT_COLOR = new Color(240, 240, 240);
    private String placeholder;
    
    
    public BTextField(String placeholder, boolean isPassword) {
    	this.placeholder = placeholder;
    	
    	if (!isPassword) {
            setEchoChar((char) 0); 
        }
    	
        setPreferredSize(new Dimension(200, 100));
        setBackground(DEFAULT_COLOR);

        setFont(new Font("Arial", Font.PLAIN, 14));
        setForeground(TEXT_COLOR); // Color del texto de placeholder
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setCursor(new Cursor(Cursor.TEXT_CURSOR));
        

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(HOVER_COLOR);
                setForeground(Color.gray);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(DEFAULT_COLOR);
                setForeground(TEXT_COLOR);
            }
        });
    }
/*
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        int height = getHeight();
        int width = getWidth();
        int arc = 20; // Ajusta el radio de las esquinas redondeadas

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (isFocusOwner() || !getText().isEmpty()) {
            g2d.setColor(HOVER_COLOR);
        } else {
            g2d.setColor(getBackground());
        }

        g2d.fillRoundRect(0, 0, width, height, arc, arc);
        super.paintComponent(g2d);
        g2d.dispose();
    }*/

    @Override
    protected void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);

        if(getText().isEmpty() && ! (FocusManager.getCurrentKeyboardFocusManager().getFocusOwner() == this)){
            Graphics2D g2 = (Graphics2D)g.create();
            g2.setBackground(Color.gray);
            g2.drawString(placeholder, 25, 25); //figure out x, y from font's FontMetrics and size of component.
            g2.dispose();
        }
      }
   

}
