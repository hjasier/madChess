package componentes;

import javax.swing.*;

import utils.Configuracion;
import utils.Escalador;

import java.awt.*;
import java.io.IOException;

public class BLabel extends JLabel {

    public BLabel() {
        // Establecer la fuente y tamaño por defecto
        try {
            String nombreFont = "/srcmedia/unsh.ttf";
            Font f = Font.createFont(Font.TRUETYPE_FONT, BLabel.class.getResourceAsStream(nombreFont));
            f = f.deriveFont(Font.PLAIN, Escalador.escalarF(18));
            setFont(f);
        } catch (FontFormatException | IOException e) {
            System.out.println("Error al cargar la fuente");
        }

        // Establecer color por defecto blanco
        setForeground(Color.WHITE);
    }

    public BLabel(String text) {
        // Llamar al constructor predeterminado
        this();
        // Establecer el texto proporcionado
        setText(text);
    }
}
