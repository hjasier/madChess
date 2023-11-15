package ventanas;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
   
//    Login panelLogin = new Login(this);
    VentanaLogin panelLogin = new VentanaLogin(this);
    
    MenuInicio  panelMenuInicio = new MenuInicio();
    
    ConfigurarPartidaLocal panelConfLocal = new ConfigurarPartidaLocal();
    
	public VentanaPrincipal() {
		
		this.setSize(1000,800);
		this.setLocationRelativeTo(null);
		
		
		panelPrincipal.setLayout(cardLayout);
		
		panelPrincipal.add(panelMenuInicio, "MENUINICIO");
		
		
		
        panelPrincipal.add(panelJuego, "PANELJUEGO");
        panelPrincipal.add(panelLogin, "PANELLOGIN");
        
        panelPrincipal.add(panelConfLocal, "PANELCONFLOCAL");
        
        
        
        
        
        panelMenuInicio.loginBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelPrincipal, "PANELLOGIN");
            }
        });

        panelMenuInicio.partidaLocal.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelPrincipal, "PANELCONFLOCAL");
                
            }
        });
        
        
        
        
        panelMenuInicio.crearPOnline.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelPrincipal, "PANELJUEGO");
                new Partida(panelJuego,1);
                
            }
        });
        
        
        
        
        
        
        
        panelConfLocal.botonIniciarPartida.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelPrincipal, "PANELJUEGO");
                new Partida(panelJuego,0);
            }
        });
        panelConfLocal.botonVolver.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(panelPrincipal, "MENUINICIO");
				
			}
		});
        
        
        
        
        
        
        panelConfLocal.botonUser1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(panelPrincipal, "PANELLOGIN");
				
			}
		});
        panelConfLocal.botonUser2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(panelPrincipal, "PANELLOGIN");
				
			}
		});
        
        
        
        
        panelLogin.botonVolver.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(panelPrincipal, "MENUINICIO");
				
			}
		});
        
        
        
        
        
        panelJuego.backBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent  e) {
				
				cardLayout.show(panelPrincipal, "MENUINICIO");
			}
		});
      
        
        
        
        
        
        
        
        
        
        
        
        
        
        this.add(panelPrincipal);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        
        
        
        
        
        
        
        

        
        
        
        
	}

	public void mainMenu() {
		cardLayout.show(panelPrincipal, "MENUINICIO");
		
	}
		
}
