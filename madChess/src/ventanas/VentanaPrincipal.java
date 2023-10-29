package ventanas;

import java.awt.CardLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class VentanaPrincipal extends JFrame{


	
	/* LA IDEA ES QUE SOLO EXISTA UN JFRAME Y EL RESTO:
	 *  (lOGIN, JUEGO, PARTIDA ..ETC)
	 *  
	 *  SEAN PANELES QUE INSERTAMOS EN ESA VENTANA PRINCIPAL, ASÍ , NO HAY QUE ABRIR Y CERRAR CENTANAS AL LOGEARSE POR EJEMPLO O AL CLICKAR 
	 *  EN EMPEZAR UNA PARTIDA, SI NO QUE CAMBIA EL CONTENIDO DE LA VENTANNA, Y SI HACE FALTA EL TAMAÑO
	 *  
	 *  
	 * 
	 */
	VentanaJuegoTesteos panelJuego = new VentanaJuegoTesteos();
	
    JPanel panelPrincipal = new JPanel();
    CardLayout cardLayout = new CardLayout();
    
    JPanel panelLogin = new VentanaLogin();
    
	public VentanaPrincipal() {
		
		
		this.setLocationRelativeTo(null);
		this.setSize(1000,800);
		
		panelPrincipal.setLayout(cardLayout);
		

		
		
        panelPrincipal.add(panelJuego, "PANELJUEGO");
        panelPrincipal.add(panelLogin, "PANELLOGIN");

        
        
        
        
        
        
        
        
        
        
        
        this.add(panelPrincipal);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        
        
        
        
        
        
        
        

        
        
        
        
	}
		
}
