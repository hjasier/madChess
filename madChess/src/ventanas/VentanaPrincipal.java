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
import juego.DatosPartida;
import juego.LogicaPartida;
import juego.Session;
import objetos.Jugador;

public class VentanaPrincipal extends JFrame{
	JPanel panelPrincipal = new JPanel();
	
	
	Juego panelJuego = new Juego();
    CardLayout cardLayout = new CardLayout();
   
    Login panelLogin = new Login(this);
    
    MenuInicio  panelMenuInicio = new MenuInicio();
    
    ConfPLocal panelConfLocal = new ConfPLocal();
    
    ConfPOnline panelConfOnline =  new ConfPOnline(null);
    
    //no paneles
    
    DatosPartida curDatosPartida;
    
	public VentanaPrincipal() {
		
		this.setSize(1000,800);
		this.setLocationRelativeTo(null);
		
		
		panelPrincipal.setLayout(cardLayout);
		
		
		panelPrincipal.add(panelMenuInicio, "MENUINICIO");
        panelPrincipal.add(panelJuego, "JUEGO");
        panelPrincipal.add(panelLogin, "LOGIN");
        panelPrincipal.add(panelConfLocal, "CONFLOCAL");  
        panelPrincipal.add(panelConfOnline, "CONFONLINE");
        
        
        
        panelMenuInicio.loginBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if (Session.getCurrentUser()==null) {
                	panelLogin.setRedirect("MENUINICIO");
                    cardLayout.show(panelPrincipal, "LOGIN");
            	}
            	else {
            		panelPrincipal.add(new Perfil(), "PERFILUSUARIO");
            		cardLayout.show(panelPrincipal, "PERFILUSUARIO");
            	}

            }
        });
        
        

        panelMenuInicio.partidaLocal.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
            	curDatosPartida = new DatosPartida("local");
            	panelConfLocal.setDatosPartida(curDatosPartida);
                cardLayout.show(panelPrincipal, "CONFLOCAL");
            }
        });
        
        panelMenuInicio.crearPOnline.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
            	
            	if (Session.getCurrentUser()!=null) {
                	curDatosPartida = new DatosPartida("online");
                	panelConfOnline.setDatosPartida(curDatosPartida);
                	panelConfOnline.setUser1(Session.getCurrentUser());
                    cardLayout.show(panelPrincipal, "CONFONLINE");
            	}
            	else {
            		cardLayout.show(panelPrincipal, "LOGIN");
            	}

                
                
                
                
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
                
                cardLayout.show(panelPrincipal, "CONFONLINE");
                
                
            }
        });
        
        

        
        
        
        
        //CONF ONLINE
        
        
        panelConfOnline.botonIniciarPartida.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelPrincipal, "JUEGO");
                new LogicaPartida(panelJuego,curDatosPartida);
                
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
				panelLogin.setRedirect("CONFONLINE");
				cardLayout.show(panelPrincipal, "LOGIN");
			}
		});
        
        
        
        
        
       

        
        
        // CONF LOCAL
        
        panelConfLocal.botonIniciarPartida.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelPrincipal, "JUEGO");
                new LogicaPartida(panelJuego,curDatosPartida);
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
				panelLogin.setRedirect("CONFLOCAL");
				cardLayout.show(panelPrincipal, "LOGIN");
				
			}
		});
        panelConfLocal.botonUser2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				panelLogin.setRedirect("CONFLOCAL");
				cardLayout.show(panelPrincipal, "LOGIN");
				
			}
		});
        
        
        
        
        
        // LOGIN
        
        panelLogin.volverBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(panelPrincipal, "MENUINICIO");
				
			}
		});
        

        
        
        
        // JUEGO
        
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
		
		
		if (redirect=="CONFONLINE") {
			panelConfOnline.setUser1(logedUser);
		}
		else if (redirect=="CONFLOCAL") {
			panelConfLocal.setUser(logedUser);
		}
		else if (redirect=="MENUINICIO") {
			Session.setCurrentUser(logedUser);
			panelMenuInicio.setLoged(true);
		}
		
		cardLayout.show(panelPrincipal, redirect);
	}
	

}
