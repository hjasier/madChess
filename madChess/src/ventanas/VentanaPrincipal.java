package ventanas;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

import Sockets.Servidor;
import juego.Partida;
import objetos.Jugador;

public class VentanaPrincipal extends JFrame{
	JPanel panelPrincipal = new JPanel();
	
	
	Juego panelJuego = new Juego();
    CardLayout cardLayout = new CardLayout();
   
    Login panelLogin = new Login(this);
    
    MenuInicio  panelMenuInicio = new MenuInicio();
    
    ConfPLocal panelConfLocal = new ConfPLocal();
    
    ConfPOnline panelConfOnline =  new ConfPOnline(null);
    
	public VentanaPrincipal() {
		
		this.setSize(1000,800);
		this.setLocationRelativeTo(null);
		
		
		panelPrincipal.setLayout(cardLayout);
		
		
		panelPrincipal.add(panelMenuInicio, "MENUINICIO");
        panelPrincipal.add(panelJuego, "PANELJUEGO");
        panelPrincipal.add(panelLogin, "PANELLOGIN");
        panelPrincipal.add(panelConfLocal, "PANELCONFLOCAL");  
        panelPrincipal.add(panelConfOnline, "PANELCONFONLINE");
        
        
        
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
                cardLayout.show(panelPrincipal, "PANELCONFONLINE");
                
                
                
                if (panelConfOnline.getServer()==null) {
                	Thread server = new Thread(new Runnable() {@Override
                	public void run() {
                		panelConfOnline.setServer(new Servidor(null));
                		
                	}
					});
                	server.start();
                	
                }
               
                
            }
        });
        
        panelMenuInicio.joinPOnline.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                
                cardLayout.show(panelPrincipal, "PANELCONFONLINE");
                
                
            }
        });
        
        

        
        
        
        
        //CONF ONLINE
        
        
        panelConfOnline.botonIniciarPartida.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelPrincipal, "PANELJUEGO");
                new Partida(panelJuego,1);
                
            }
        });
        
        panelConfOnline.volverBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(panelPrincipal, "MENUINICIO");
				
			}
		});
        
        

        panelConfOnline.botonUser1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(panelPrincipal, "PANELLOGIN");
				panelLogin.setRedirect("PANELCONFONLINE");
				
			}
		});
        
        
        
        
        
       

        
        
        // CONF LOCAL
        
        panelConfLocal.botonIniciarPartida.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelPrincipal, "PANELJUEGO");
                new Partida(panelJuego,0);
            }
        });
        panelConfLocal.volverBtn.addActionListener(new ActionListener() {
			
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
        
        
        
        
        
        //PANEL LOGIN
        
        panelLogin.volverBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(panelPrincipal, "MENUINICIO");
				
			}
		});
        

        
        
        
        //PANEL JUEGO
        
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

	public void loginReturn(String redirect,Jugador logedUser) {
		cardLayout.show(panelPrincipal, redirect);
		
		if (redirect=="PANELCONFONLINE") {
			panelConfOnline.setUser1(logedUser);
		}
		
	}
	

}
