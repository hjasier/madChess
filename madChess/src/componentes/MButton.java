package componentes;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/*
 * BOTONES CUADRADOS OSCUROS COMO CHAT	
 */

public class MButton extends JButton {
    public MButton(String text) {
        super(text);
        initButtonStyle();
    }

    public MButton(Icon icon) {
    	super(icon);
        initButtonStyle();
	}

	private void initButtonStyle() {
        // Configurar el fondo y el color de fuente
        setBackground(new Color(36, 36, 36)); // Fondo oscuro
        setForeground(Color.WHITE); // Texto en color blanco

        // Crear un borde redondeado personalizado
        LineBorder roundedBorder = new LineBorder(Color.WHITE, 2, true);
        setBorder(roundedBorder);

        // Efecto de sombra en el texto
        setFocusPainted(false);

        // Efecto de sombra en el botón
        setBorderPainted(false);

        // Efecto de resaltado al pasar el ratón
        setContentAreaFilled(false);

        // Establecer un efecto de fondo al hacer clic
        setOpaque(true);
        
        this.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mousePressed(MouseEvent e) {
        		// TODO Auto-generated method stub
        		super.mousePressed(e);
        		setBackground(new Color(24, 24, 24)); 
        	}

            public void mouseReleased(MouseEvent e) {
                setBackground(new Color(36, 36, 36)); // Restaura el fondo al soltar el botón
            }
           
		});
    }
}

