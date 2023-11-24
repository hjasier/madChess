package ventanas;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

import Sockets.Servidor;
import juego.Configuracion;
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
    
    ListaPartidas panelListaPartidas = new ListaPartidas();
    
    Perfil panelPerfil = new Perfil();
    //no paneles

    
	public VentanaPrincipal() {
		
		this.setSize(1250,1000);
		this.setLocationRelativeTo(null);
		
		
		
		panelPrincipal.setLayout(cardLayout);
		
		
		panelPrincipal.add(panelMenuInicio, "MENUINICIO");
        panelPrincipal.add(panelJuego, "JUEGO");
        panelPrincipal.add(panelLogin, "LOGIN");
        panelPrincipal.add(panelConfLocal, "CONFLOCAL");  
        panelPrincipal.add(panelConfOnline, "CONFONLINE");
        
		panelPrincipal.add(panelListaPartidas, "LISTAPARTIDAS");
		panelPrincipal.add(panelPerfil, "PERFILUSUARIO");
        
        
        
        panelMenuInicio.loginBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if (Session.getCurrentUser()==null) {
                	panelLogin.setRedirect("MENUINICIO");
                    cardLayout.show(panelPrincipal, "LOGIN");
            	}
            	else {
            		panelPerfil.reloadData();
            		cardLayout.show(panelPrincipal, "PERFILUSUARIO");
            	}

            }
        });
        
        

        panelMenuInicio.partidaLocal.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
            	DatosPartida datos = new DatosPartida("local");
            	Session.setDatosPartida(datos);
            	panelConfLocal.setDatosPartida(datos);
                cardLayout.show(panelPrincipal, "CONFLOCAL");
            }
        });
        
        panelMenuInicio.crearPOnline.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
            	System.out.println("-----CONSOLA CREADOR------");
            	if (Session.getCurrentUser()!=null) {
            		
            		startServerCnx();
                	DatosPartida curDatosPartida = new DatosPartida("online");
                	curDatosPartida.setJugador(Session.getCurrentUser());
                	Session.setDatosPartida(curDatosPartida);
                	panelConfOnline.setDatosPartida(curDatosPartida);
                    cardLayout.show(panelPrincipal, "CONFONLINE");
                    try {
						Session.getCtsConnection().setGameSettings(curDatosPartida);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
            	}
            	else {
            		cardLayout.show(panelPrincipal, "LOGIN");
            	}

                
                
                
                

               
                
            }
        });
        
        panelMenuInicio.joinPOnline.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                System.out.println("-----CONSOLA JOINER------");
            	startServerCnx();
            	try {
					Session.getCtsConnection().getListaPartidas();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
            	
                cardLayout.show(panelPrincipal, "LISTAPARTIDAS");
                
                
            }
        });
        

        
        

        
        
        
        
        //CONF ONLINE
        
        
        panelConfOnline.botonIniciarPartida.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
            	ArrayList<Jugador> jugadores = Session.getDatosPartida().getJugadores();
            	if (jugadores.size()>1&&jugadores.get(0).getNombre().equals(Session.getCurrentUser().getNombre())) {
            	try {
					Session.getCtsConnection().postInitGame();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                initGame();
            	}
            	else {
            		alert("No eres el admin o faltan jugadores");
            	}
                
            }
        });
        
        panelConfOnline.volverBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(panelPrincipal, "MENUINICIO");
				
			}
		});
        
        

        panelConfOnline.user1Btn.addActionListener(new ActionListener() {
			
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
                new LogicaPartida(panelJuego);
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
      
        
        
        
        panelListaPartidas.backBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent  e) {
				
				cardLayout.show(panelPrincipal, "MENUINICIO");
			}
		});
        
        
        
        panelPerfil.backBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent  e) {
				
				cardLayout.show(panelPrincipal, "MENUINICIO");
			}
		});
        
        
        
        
        
        this.add(panelPrincipal);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        Session.setVentana(this);
        
        
        
        
        
        
        

        
      
        
        
	}

	public void initGame() {
		cardLayout.show(panelPrincipal, "JUEGO");
        new LogicaPartida(panelJuego);
		
	}

	protected void startServerCnx() {
		try {
			Session.startServerCnx();
		} catch (ClassNotFoundException | IOException e1) {
			System.out.println("Error al conectarse con el server");
		}
	}

	public void loginReturn(String redirect,Jugador logedUser) {
		
		
		if (redirect=="CONFONLINE") {
			panelConfOnline.setUser1(logedUser);
		}
		else if (redirect=="CONFLOCAL") {
			if (!Session.getDatosPartida().getJugadores().contains(logedUser)) {	
				panelConfLocal.setUser(logedUser);
			}
			else {
				alert("Ese usuario ya esta en la partida");
				return;
				
			}
		}
		else if (redirect=="MENUINICIO") {
			Session.setCurrentUser(logedUser);
			panelMenuInicio.setLoged(true);
		}
		
		cardLayout.show(panelPrincipal, redirect);
	}

	private void alert(String string) {
		JOptionPane.showMessageDialog(null, string, "Info", JOptionPane.ERROR_MESSAGE);
	}

	public Juego getPanelJuego() {
		return panelJuego;
	}

	public Login getPanelLogin() {
		return panelLogin;
	}

	public MenuInicio getPanelMenuInicio() {
		return panelMenuInicio;
	}

	public ConfPLocal getPanelConfLocal() {
		return panelConfLocal;
	}

	public ConfPOnline getPanelConfOnline() {
		return panelConfOnline;
	}

	public ListaPartidas getPanelListaPartidas() {
		return panelListaPartidas;
	}

	public CardLayout getCardLayout() {
		return cardLayout;
	}

	public JPanel getPanelPrincipal() {
		return panelPrincipal;
	}


	

}
