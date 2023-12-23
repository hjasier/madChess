package ventanas;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.formdev.flatlaf.FlatDarkLaf;

import Sockets.Servidor;
import componentes.EnumCardLayout;
import juego.DatosPartida;
import juego.LogicaPartida;
import objetos.Jugador;
import objetos.Usuario;
import utils.Configuracion;
import utils.Escalador;
import utils.Session;

public class VentanaPrincipal extends JFrame{
	JPanel panelPrincipal = new JPanel();
	
	
	Juego panelJuego = new Juego();
    EnumCardLayout cardLayout = new EnumCardLayout();
   
    Login panelLogin = new Login(this);
    
    ConfPLocal panelConfLocal = new ConfPLocal();
    
    ConfPOnline panelConfOnline =  new ConfPOnline(null);
    
    ListaPartidas panelListaPartidas = new ListaPartidas();
    
    Perfil panelPerfil = new Perfil();
    
    MadChessConfig panelMadChessConfig = new MadChessConfig();

    Register panelRegister = new Register();
    
    MenuInicio panelMenuInicio = new MenuInicio();
    
    MenuConfig panelMenuConfig = new MenuConfig();
    
    CambiarContraseña cambiarContraseña = new CambiarContraseña();
    
    ImageIcon logo = new ImageIcon(getClass().getResource("/srcmedia/appLogo.png"));

    PartidasJugadas partidas = new PartidasJugadas();
    
    
    // Obtener la imagen desde el ImageIcon
   
    
	public VentanaPrincipal() {
		
		
		
		this.setSize(Escalador.escalar(1000),Escalador.escalar(800));
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Image imagen = logo.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
		this.setIconImage(imagen);
		

		panelPrincipal.setLayout(cardLayout);
		
		//panelPrincipal.add(panelMenuConfig, "DEBUG");
		
		
		panelPrincipal.add(panelMenuInicio, Paneles.MENU_INICIO.name());
		panelPrincipal.add(panelJuego, Paneles.JUEGO.name());
		panelPrincipal.add(panelLogin, Paneles.LOGIN.name());
        panelPrincipal.add(panelRegister, Paneles.REGISTER.name());
        panelPrincipal.add(panelConfLocal, Paneles.CONFLOCAL.name());  
        panelPrincipal.add(panelConfOnline, Paneles.CONFONLINE.name());
        panelPrincipal.add(panelMadChessConfig, Paneles.CONFMADCHESS.name());
        
        
		panelPrincipal.add(panelListaPartidas, Paneles.LISTAPARTIDAS.name());
		panelPrincipal.add(panelPerfil, Paneles.PERFILUSUARIO.name());
		panelPrincipal.add(panelMenuConfig, Paneles.CONFIGMENU.name());
		panelPrincipal.add(cambiarContraseña, Paneles.CAMBIARCONTRASENYA.name());
		
		panelPrincipal.add(partidas, Paneles.PARTIDAS.name());


		
		
        
        panelMenuInicio.loginBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if (Session.getCurrentUser()==null) {
                	panelLogin.setRedirect(Paneles.MENU_INICIO);
                    cardLayout.show(panelPrincipal, Paneles.LOGIN);
            	}
            	else {
            		panelPerfil.reloadData();
            		cardLayout.show(panelPrincipal, Paneles.PERFILUSUARIO);
            	}

            }
        });
        
        
        
        
        

        
        

        
        
        
        
        //CONF ONLINE
        
        

        
        panelConfOnline.volverBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(panelPrincipal, Paneles.MENU_INICIO);
				
			}
		});
        
        
        
        
        //Config MADCHESS
       panelMadChessConfig.volverBtn.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			cardLayout.show(panelPrincipal, Paneles.MENU_INICIO);
			
		}
	});
        
        
        
        
        // Cambiar contraseña
       
       cambiarContraseña.volverBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(panelPrincipal, Paneles.PERFILUSUARIO);
				
			}
		});
        
        
        // CONF LOCAL

        panelConfLocal.volverBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(panelPrincipal, Paneles.MENU_INICIO);
				
			}
		});
        

        
        // LOGIN
        
        panelLogin.volverBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(panelPrincipal, Paneles.MENU_INICIO);
				
			}
		});
        

        panelRegister.volverBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent  e) {
				
				cardLayout.show(panelPrincipal, Paneles.LOGIN);
			}
		});
        
        
        // JUEGO
        
        panelJuego.backBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int respuesta = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que quieres salir, se terminará la partida con victoria para el oponente?", "Confirmar derrota", JOptionPane.YES_NO_OPTION);
                
                if (respuesta == JOptionPane.YES_OPTION) {
                    cardLayout.show(panelPrincipal, Paneles.MENU_INICIO);
                    //TODO: hacer que se termine la partida con derrota para el jugador
                } else {
                }
            }
        });
      
        
        
        
        panelListaPartidas.backBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent  e) {
				
				cardLayout.show(panelPrincipal, Paneles.MENU_INICIO);
			}
		});
        
        
        
        panelPerfil.backBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent  e) {
				
				cardLayout.show(panelPrincipal, Paneles.MENU_INICIO);
			}
		});
        
        
        
        
        
        this.add(panelPrincipal);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        Session.setVentana(this);
    
        
	}

	public void initGame() {
		cardLayout.show(panelPrincipal, Paneles.JUEGO);
        new LogicaPartida();
		
	}

	

	public void loginReturn(Usuario logedUser) {
		Paneles redirect = panelLogin.getRedirect();
		
		//En confonline el tipo "jugador" se gestiona en el servidor
		if (redirect==Paneles.CONFONLINE) {
			panelConfOnline.setUser1(logedUser);
		}
		
		
		else if (redirect==Paneles.CONFLOCAL) {
//			if (!Session.getDatosPartida().getJugadores().contains(logedUser)) {	
//				panelConfLocal.setUser(logedUser);
//			}
//			else {
//				alert("Ese usuario ya esta en la partida");
//				return;
//				
//			}
			panelConfLocal.setUser(logedUser);
		}
		else if (redirect==Paneles.MENU_INICIO) {
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

	public EnumCardLayout getCardLayout() {
		return cardLayout;
	}

	public JPanel getPanelPrincipal() {
		return panelPrincipal;
	}

	public void showPanel(Paneles panel) {
		cardLayout.show(panelPrincipal, panel);
	}

	public MadChessConfig getPanelMadChessConfig() {
		return panelMadChessConfig;
	}

	public PartidasJugadas getPartidas() {
		return partidas;
	}



	

}
