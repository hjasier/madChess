package componentes;
import javax.swing.*;
import javax.swing.plaf.basic.*;

import utils.Configuracion;
import utils.Escalador;

import java.awt.*;

public class MComboBox<T> extends JComboBox<T> {
    public MComboBox(T[] items) {
        super(items);
        setUI(new ModernComboBoxUI());
        setRenderer(new ModernComboBoxRenderer<>());
        setForeground(Color.BLACK); // Color del texto
        setBackground(Color.WHITE); // Color de fondo
        setPreferredSize(new Dimension(Escalador.escalar(150), Escalador.escalar(30))); // Tamaño preferido
        
    }

    private static class ModernComboBoxUI extends BasicComboBoxUI {
        @Override
        protected JButton createArrowButton() {
            return new BasicArrowButton(BasicArrowButton.SOUTH) {
                @Override
                public void paintTriangle(Graphics g, int x, int y, int size, int direction, boolean isEnabled) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2.setColor(Color.BLACK); // Color del icono
                    int[] xPoints = {x, x + size, x + size / 2};
                    int[] yPoints;
                    if (direction == NORTH) {
                        yPoints = new int[]{y + size, y + size, y};
                    } else {
                        yPoints = new int[]{y, y, y + size};
                    }
                    g2.fillPolygon(xPoints, yPoints, 3);
                    g2.dispose();
                }
            };
        }

        @Override
        protected ListCellRenderer<Object> createRenderer() {
            return new ModernComboBoxRenderer<>();
        }

        @Override
        protected ComboPopup createPopup() {
            BasicComboPopup popup = (BasicComboPopup) super.createPopup();
            popup.getList().setBackground(Color.GRAY); // Color de fondo de la lista desplegable
            return popup;
        }
    }

    private static class ModernComboBoxRenderer<E> extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            // Ajustes de estilo para cada elemento del ComboBox
            label.setHorizontalAlignment(SwingConstants.CENTER); // Centrar el texto
            label.setForeground(Color.BLACK); // Color del texto
            label.setOpaque(true);
            label.setBackground(Color.white); // Color de fondo para cada elemento
            label.setBorder(BorderFactory.createCompoundBorder(label.getBorder(), BorderFactory.createEmptyBorder(5,0,0,0)));//Mrgen arriba praa la fuente cambiada
            if (isSelected) {
                label.setBackground(Color.LIGHT_GRAY); // Color de fondo cuando está seleccionado
            }
            return label;
        }
    }
}
