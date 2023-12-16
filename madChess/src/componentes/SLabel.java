package componentes;

import javax.swing.*;

import utils.Escalador;

import java.awt.*;
import java.io.IOException;

public class SLabel extends JLabel {

    public SLabel() {
        // Establecer la fuente y tamaño por defecto
        try {
            String nombreFont = "/srcmedia/unsh.ttf";
            Font f = Font.createFont(Font.TRUETYPE_FONT, BLabel.class.getResourceAsStream(nombreFont));
            f = f.deriveFont(Font.PLAIN, Escalador.escalarF(25));
            setFont(f);
        } catch (FontFormatException | IOException e) {
            System.out.println("Error al cargar la fuente");
        }

        // Establecer color por defecto blanco
        setForeground(Color.WHITE);
    }

    public SLabel(String text) {
        // Llamar al constructor predeterminado
        this();
        // Establecer el texto proporcionado
        setText(text);
    }

    @Override
    protected void paintComponent(Graphics g) {
        // Llamar al método paintComponent de la clase base
        super.paintComponent(g);

        // Obtener el color del texto
        Color textColor = getForeground();

        // Obtener la posición del texto
        int x = 0;
        int y = g.getFontMetrics().getAscent();

        // Configurar el contorno
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.magenta);
        g2d.setFont(getFont());

        // Dibujar el contorno del texto
        g2d.drawString(getText(), x - 1, y);
        g2d.drawString(getText(), x + 1, y);
        g2d.drawString(getText(), x, y - 1);
        g2d.drawString(getText(), x, y + 1);
    }
}
