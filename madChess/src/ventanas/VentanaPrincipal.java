package ventanas;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import juego.Partida;
import objetos.Jugador;

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
	JPanel panelPrincipal = new JPanel();
	
	
	Juego panelJuego = new Juego();
    CardLayout cardLayout = new CardLayout();
   
    Login panelLogin = new Login();
    PanelDemo panelDemo = new PanelDemo();
    
	public VentanaPrincipal() {
		
		this.setSize(1000,800);
		this.setLocationRelativeTo(null);
		
		
		panelPrincipal.setLayout(cardLayout);
		

		

		panelPrincipal.add(panelDemo, "PANELDEMO");
		
		

		
        panelPrincipal.add(panelJuego, "PANELJUEGO");
        panelPrincipal.add(panelLogin, "PANELLOGIN");
        
        
        
        panelDemo.btn1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelPrincipal, "PANELLOGIN");
            }
        });

        panelDemo.btn2.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelPrincipal, "PANELJUEGO");
                new Partida(panelJuego,0);
            }
        });
        
        
        
        
        
        
        
        
        
        
        this.add(panelPrincipal);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        
        
        
        
        
        
        
        

        
        
        
        
	}
		
}
