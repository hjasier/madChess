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
import java.util.Random;

import objetos.Jugador;
import componentes.*;
import juego.Configuracion;
import librerias.FontAwesome;
import librerias.IconFontSwing;

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
	protected RButton volverBtn = new RButton("Volver");
	
	protected BTextField inputNombre = new BTextField("Nombre de usuario",false);
	protected BTextField inputContra = new BTextField("Contraseña",true);
	protected BButton loginBtn = new BButton("Iniciar Sesión");
	protected JLabel registerLabel = new JLabel("¿No tienes cuenta? Registrate aquí");
	protected JLabel labelTitulo = new JLabel("Inicia sesión para continuar");
	
	private HashMap<String, Jugador> users = new HashMap<String, Jugador>();
	protected String redirect = "MENUINICIO";
	
	public Login (VentanaPrincipal ventanaPrincipal) {
		this.setLayout(new BorderLayout());
		
		cargarUsuarios("users.dat");
		
		opciones = new JPanel();

		
		opciones.setBackground(Configuracion.BACKGROUND);
		opciones.setLayout(new BoxLayout(opciones, BoxLayout.Y_AXIS));
		
		
        
		
		
  		//--------------------- NAVBAR-------------------------------------------
		Icon icon = IconFontSwing.buildIcon(FontAwesome.BACKWARD, 15);
		volverBtn.setIcon(icon);
  		
  		navBar = new navBar(volverBtn);
  		//--------------------- NAVBAR-------------------------------------------	   


		
		
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
        opciones.add(Box.createRigidArea(new java.awt.Dimension(0, 20))); // Espacio entre los botones
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
					ventanaPrincipal.loginReturn(redirect,expectedUser);
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
				
				int  newUserId = 10000 + new Random().nextInt(90000); // de momento no comprobamos si el id existe pk no hay base de datos y es absurdo, ademas de que probablemente no se acabe creando aquí al user
				
				users.put(username, new Jugador(newUserId,username,passw)); // si ya hay un user lo sobreescribe pero nos da igual pk todo esto es temp
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








	public void setRedirect(String string) {
		this.redirect = string;
	}


}
	
	



