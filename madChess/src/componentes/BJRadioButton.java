package componentes;
import javax.swing.*;
import javax.swing.plaf.basic.BasicRadioButtonUI;
import java.awt.*;

public class BJRadioButton extends JRadioButton {
    private boolean selected = false;

    public BJRadioButton(String text) {
        super(text);
        setUI(new ModernRadioButtonUI());
        setForeground(Color.WHITE); // Color del texto
        setOpaque(false); // Hace el botón transparente para el fondo
        setFocusPainted(false); // Elimina el borde de enfoque
        setPreferredSize(new Dimension(150, 40)); // Tamaño del botón
        setHorizontalTextPosition(SwingConstants.CENTER);
        setVerticalTextPosition(SwingConstants.BOTTOM);
        setForeground(new Color(17,17,17));
    }

    private static class ModernRadioButtonUI extends BasicRadioButtonUI {
        private static final Dimension SIZE = new Dimension(18, 18); // Tamaño del círculo
        private static final Dimension INNER_SIZE = new Dimension(12, 12); // Tamaño del círculo interno

        @Override
        public void installUI(JComponent c) {
            super.installUI(c);
            AbstractButton button = (AbstractButton) c;
            button.setOpaque(false); // Hace el botón transparente para el fondo
            button.setIcon(new Icon() {
                @Override
                public void paintIcon(Component c, Graphics g, int x, int y) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2.setColor(Color.WHITE); // Color del círculo exterior (blanco) por defecto
                    g2.fillOval(x, y, SIZE.width, SIZE.height);
                    if (((AbstractButton) c).isSelected()) {
                        g2.setColor(Color.LIGHT_GRAY); // Color del círculo interno (gris)
                        int xOffset = (SIZE.width - INNER_SIZE.width) / 2;
                        int yOffset = (SIZE.height - INNER_SIZE.height) / 2;
                        g2.fillOval(x + xOffset, y + yOffset, INNER_SIZE.width, INNER_SIZE.height);
                    }
                    g2.dispose();
                }

                @Override
                public int getIconWidth() {
                    return SIZE.width;
                }

                @Override
                public int getIconHeight() {
                    return SIZE.height;
                }
            });
        }
    }
}
// letras y botones en una fila

//import javax.swing.*;
//import javax.swing.plaf.basic.BasicRadioButtonUI;
//import java.awt.*;
//
//public class BJRadioButton extends JRadioButton {
//    public BJRadioButton(String text) {
//        super(text);
//        setUI(new ModernRadioButtonUI());
//        setForeground(Color.WHITE); // Color del texto
//        setOpaque(false); // Hace el botón transparente para el fondo
//        setFocusPainted(false); // Elimina el borde de enfoque
//        setPreferredSize(new Dimension(150, 40)); // Tamaño del botón
//    }
//
//    private static class ModernRadioButtonUI extends BasicRadioButtonUI {
//        private static final Dimension SIZE = new Dimension(18, 18); // Tamaño del círculo
//        private static final Dimension INNER_SIZE = new Dimension(12, 12); // Tamaño del círculo interno
//
//        @Override
//        public void installUI(JComponent c) {
//            super.installUI(c);
//            AbstractButton button = (AbstractButton) c;
//            button.setOpaque(false); // Hace el botón transparente para el fondo
//            button.setLayout(new BorderLayout()); // Usar BorderLayout para el texto arriba
//
//            button.setIcon(new Icon() {
//                @Override
//                public void paintIcon(Component c, Graphics g, int x, int y) {
//                    Graphics2D g2 = (Graphics2D) g.create();
//                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//                    g2.setColor(Color.WHITE); // Color del círculo exterior (blanco) por defecto
//                    g2.fillOval(x, y, SIZE.width, SIZE.height);
//                    if (((AbstractButton) c).isSelected()) {
//                        g2.setColor(Color.LIGHT_GRAY); // Color del círculo interno (gris)
//                        int xOffset = (SIZE.width - INNER_SIZE.width) / 2;
//                        int yOffset = (SIZE.height - INNER_SIZE.height) / 2;
//                        g2.fillOval(x + xOffset, y + yOffset, INNER_SIZE.width, INNER_SIZE.height);
//                    }
//                    g2.dispose();
//                }
//
//                @Override
//                public int getIconWidth() {
//                    return SIZE.width;
//                }
//
//                @Override
//                public int getIconHeight() {
//                    return SIZE.height;
//                }
//            });
//        }
//    }
//}


