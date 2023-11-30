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
import juego.Escalador;
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
    
    MadChessConfig panelMadChessConfig = new MadChessConfig();

    Register panelRegister = new Register();
    
	public VentanaPrincipal() {
		
		this.setSize(Escalador.escalar(1000),Escalador.escalar(800));
		this.setLocationRelativeTo(null);
		
		
		
		panelPrincipal.setLayout(cardLayout);
		
		//panelPrincipal.add(panelRegister, "DEBUG");
		
		
		panelPrincipal.add(panelMenuInicio, "MENUINICIO");
        panelPrincipal.add(panelJuego, "JUEGO");
        panelPrincipal.add(panelLogin, "LOGIN");
        panelPrincipal.add(panelRegister, "REGISTER");
        panelPrincipal.add(panelConfLocal, "CONFLOCAL");  
        panelPrincipal.add(panelConfOnline, "CONFONLINE");
        panelPrincipal.add(panelMadChessConfig, "CONFMADCHESS");
        
        
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
        
        

        
        

        
        

        
        
        
        
        //CONF ONLINE
        
        

        
        panelConfOnline.volverBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(panelPrincipal, "MENUINICIO");
				
			}
		});
        
        

       
        
        
        
        
        
       

        
        
        // CONF LOCAL

        panelConfLocal.volverBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(panelPrincipal, "MENUINICIO");
				
			}
		});
        

        
        // LOGIN
        
        panelLogin.volverBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(panelPrincipal, "MENUINICIO");
				
			}
		});
        

        panelRegister.volverBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent  e) {
				
				cardLayout.show(panelPrincipal, "LOGIN");
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
        new LogicaPartida();
		
	}

	

	public void loginReturn(Jugador logedUser) {
		String redirect = panelLogin.getRedirect();
		
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
