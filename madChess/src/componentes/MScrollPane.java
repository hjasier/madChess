package componentes;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

public class MScrollPane extends JScrollPane {

	
    public MScrollPane(Component view) {
        super(view);
        customizeScrollBarUI();
    }

    
    public MScrollPane() {
        // Llama al constructor de JScrollPane para configurar el comportamiento de desplazamiento.
        super();
        customizeScrollBarUI();
    }

    private void customizeScrollBarUI() {
        getVerticalScrollBar().setUI(new ModernScrollBarUI());
        getHorizontalScrollBar().setUI(new ModernScrollBarUI());
    }

    private class ModernScrollBarUI extends BasicScrollBarUI {
        private static final int SCROLLBAR_WIDTH = 5; // Ancho de la barra de desplazamiento (ajusta este valor)
        private static final int THUMB_WIDTH = 5; // Ancho del pulgar (ajusta este valor)

        @Override
        protected void paintTrack(Graphics g, JComponent c, java.awt.Rectangle trackBounds) {
            g.setColor(new Color(16,16,16)); // Color del fondo de la barra
            g.fillRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height);
        }

        @Override
        protected void paintThumb(Graphics g, JComponent c, java.awt.Rectangle thumbBounds) {
            if (thumbBounds.isEmpty() || !scrollbar.isEnabled()) {
                return;
            }

            int w = THUMB_WIDTH; // Ancho del pulgar
            int h = thumbBounds.height; // Altura del pulgar (mantén la altura original)

            g.setColor(new Color(128, 128, 128)); // Color del fondo del pulgar
            g.fillRect(THUMB_WIDTH+5, thumbBounds.y, w, h);

       
        }

        @Override
        protected JButton createDecreaseButton(int orientation) {
            return createZeroButton();
        }

        @Override
        protected JButton createIncreaseButton(int orientation) {
            return createZeroButton();
        }

        private JButton createZeroButton() {
            JButton button = new JButton();
            button.setPreferredSize(new java.awt.Dimension(0, 0));
            button.setMinimumSize(new java.awt.Dimension(0, 0));
            button.setMaximumSize(new java.awt.Dimension(0, 0));
            return button;
        }
    }

}
