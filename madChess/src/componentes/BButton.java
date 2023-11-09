package componentes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BButton extends JButton {

    private static final Color DEFAULT_COLOR = new Color(220, 220, 220);
    private static final Color HOVER_COLOR = new Color(180, 180, 180);

    public BButton(String text) {
        super(text);

        setPreferredSize(new Dimension(150, 40));
        setBackground(DEFAULT_COLOR);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        Dimension buttonSize = new Dimension(200, 50);
        setMaximumSize(buttonSize);

        // Quitar bordes del botón y del texto
        setBorderPainted(false);
        setContentAreaFilled(false);

        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(HOVER_COLOR);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(DEFAULT_COLOR);
            }
        });
    }
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        int height = getHeight();
        int width = getWidth();
        int arc = 20; // Ajusta el radio de las esquinas redondeadas

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (getModel().isArmed()) {
            g2d.setColor(HOVER_COLOR);
        } else {
            g2d.setColor(getBackground());
        }

        g2d.fillRoundRect(0, 0, width, height, arc, arc);
        super.paintComponent(g2d);
        g2d.dispose();
    }
}
