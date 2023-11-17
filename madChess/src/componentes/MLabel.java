package componentes;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MLabel extends JLabel {

    public MLabel() {
        // Establecer la fuente y tamaño por defecto
        try {
            String nombreFont = "/srcmedia/unsr.ttf";
            Font f = Font.createFont(Font.TRUETYPE_FONT, MLabel.class.getResourceAsStream(nombreFont));
            f = f.deriveFont(Font.PLAIN, 16);
            setFont(f);
        } catch (FontFormatException | IOException e) {
            System.out.println("Error al cargar la fuente");
        }

        // Establecer color por defecto blanco
        setForeground(Color.WHITE);
    }

    public MLabel(String text) {
        // Llamar al constructor predeterminado
        this();
        // Establecer el texto proporcionado
        setText(text);
    }
}
