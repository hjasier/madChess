package ventanas;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import componentes.BButton;
import componentes.FontAwesome;
import componentes.IconFontSwing;
import objetos.Jugador;
import componentes.*;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Login extends JPanel {

	protected JPanel navBar;
	protected JPanel opciones;
	protected JPanel panelLogo = new JPanel();
	protected JPanel panelLogin = new JPanel();
	
	protected JLabel nombre = new JLabel("madChess");
	
	protected RButton volverBtn = new RButton("Volver");

	
	
	protected ImageIcon imgFoto = new ImageIcon(getClass().getResource("../srcmedia/logo110x300.png"));
	protected JLabel foto = new JLabel(imgFoto);
	protected Color colorTemp = new Color(16,16,16);
	


	protected BTextField inputNombre = new BTextField("Nombre de usuario",false);
	protected BTextField inputContra = new BTextField("Contraseña",true);
	protected BButton loginBtn = new BButton("Iniciar Sesión");
	protected JLabel registerLabel = new JLabel("¿No tienes cuenta? Registrate aquí");
	protected JLabel labelTitulo = new JLabel("Inicia sesión para continuar");
	
	private HashMap<String, Jugador> users = new HashMap<String, Jugador>();
	
	public Login (VentanaPrincipal ventanaPrincipal) {
		this.setLayout(new BorderLayout());
		navBar = new JPanel();
		opciones = new JPanel();
		
		//--------------------- NAVBAR-------------------------------------------

		navBar.setBackground(colorTemp);
		opciones.setBackground(colorTemp);
		panelLogo.setBackground(colorTemp);
		panelLogin.setBackground(colorTemp);

		nombre.setForeground(new Color(255, 248, 210));
		nombre.setFont(getFont());
		String nombreFont = "/srcmedia/unsh.ttf"; 
	
		

		
		try {
			Font f = Font.createFont( Font.TRUETYPE_FONT, MenuInicio.class.getResourceAsStream(nombreFont) );
			f = f.deriveFont( Font.PLAIN, 38 );
			nombre.setFont(f);
		} catch (FontFormatException | IOException e) {
			System.out.println("Error al cargar fuente");
		}
		
		
		
		
		navBar.setLayout(new BorderLayout());
		opciones.setLayout(new BoxLayout(opciones, BoxLayout.Y_AXIS));
		
		// Escalar la imagen
        Image imagenEscalada = imgFoto.getImage().getScaledInstance(300, 110, Image.SCALE_SMOOTH);
        imgFoto = new ImageIcon(imagenEscalada);
        foto.setIcon(imgFoto);

        panelLogo.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
		panelLogo.add(foto, BorderLayout.WEST);
		//panelLogo.add(nombre, BorderLayout.CENTER);

		panelLogin.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));
		panelLogin.add(Box.createRigidArea(new java.awt.Dimension(0, 80)));
		panelLogin.add(volverBtn);
        
		navBar.add(panelLogo, BorderLayout.WEST);
		navBar.add(panelLogin, BorderLayout.EAST);
        
		Icon icon = IconFontSwing.buildIcon(FontAwesome.BACKWARD, 15);
		volverBtn.setIcon(icon);
		
		//---------------------FIN NAVBAR-------------------------------------------
		

		
		
		
		//---------------------body-------------------------------------------
		
		

		
		
		opciones.setLayout(new BoxLayout(opciones, BoxLayout.Y_AXIS));

		
		inputNombre.setMaximumSize(new Dimension(200, 40));
		inputContra.setMaximumSize(new Dimension(200, 40));
		
		registerLabel.setForeground(new Color(213,213,213));
		labelTitulo.setForeground(new Color(236,236,236));
		
		registerLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));		
		
		labelTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
		inputNombre.setAlignmentX(Component.CENTER_ALIGNMENT);
		inputContra.setAlignmentX(Component.CENTER_ALIGNMENT);
		loginBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		registerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		
		
		
		opciones.add(Box.createRigidArea(new java.awt.Dimension(0, 100))); 
		
		
		opciones.add(labelTitulo);
        opciones.add(Box.createRigidArea(new java.awt.Dimension(0, 40))); // Espacio entre los botones
		opciones.add(inputNombre);
        opciones.add(Box.createRigidArea(new java.awt.Dimension(0, 10))); // Espacio entre los botones
        opciones.add(inputContra);
        opciones.add(Box.createRigidArea(new java.awt.Dimension(0, 20))); // Espacio entre los botones
        opciones.add(loginBtn);
        opciones.add(Box.createRigidArea(new java.awt.Dimension(0, 10))); // Espacio entre los botones
        opciones.add(registerLabel);


		this.add(navBar, BorderLayout.NORTH);	
		this.add(opciones, BorderLayout.CENTER);



		loginBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String username = inputNombre.getText();
				String passw = inputContra.getText();
				
				
				//método temporal para "logear" usuarios antes de configrara la base de datos final
				
				Jugador expectedUser = users.get(username);
				
				if (expectedUser!=null&&expectedUser.checkPassword(passw)) {
					System.out.println("Logeado como "+ expectedUser);
					ventanaPrincipal.mainMenu();
					inputNombre.setText("");
					inputContra.setText("");
				}
				else {
					
					JOptionPane.showMessageDialog(null, "Contraseña o usuario incorrectos", "Error de autenticación", JOptionPane.ERROR_MESSAGE);

				}
				
			
			}
		});
		
		registerLabel.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				String username = inputNombre.getText();
				String passw = inputContra.getText();
				
				if (!username.equals("")&&!passw.equals(""));
				
				users.put(username, new Jugador(username,passw)); // si ya hay un user lo sobreescribe pero nos da igual pk todo esto es temp
				guardarUsuarios("users.dat");
			}
		});
	}
	
	
	
	
	
	
	

    public void guardarUsuarios(String filePath) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filePath))) {
            outputStream.writeObject(users);
            System.out.println("Fichero de usuarios actualizado " + filePath);
            JOptionPane.showMessageDialog(null, "Fichero Actualizado", "Error de autenticación", JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cargarUsuarios(String filePath) {
        HashMap<String, Jugador> hashMap = new HashMap<>();

        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filePath))) {
            Object obj = inputStream.readObject();
            if (obj instanceof HashMap) {
                // Verificar que el objeto leído sea realmente un HashMap
                hashMap = (HashMap<String, Jugador>) obj;
                System.out.println("Fichero de usuarios cargado --> " + filePath);
            } else {
                System.out.println("Error al cargar el fichero de usuarios temporales");
            }
        } catch (IOException | ClassNotFoundException e) {
        	System.out.println("No se han cargado los usuarios");
        }

        users = hashMap;
    }


}
	
	



