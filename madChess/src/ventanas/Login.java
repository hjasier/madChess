package ventanas;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import objetos.Jugador;

public class Login extends JPanel{
	
	
	protected JPanel panelLogin;
	protected JPanel panelUsuario;
	protected JLabel labelUsuario;
	protected JTextField textfieldUsuario;
	protected JPanel panelContrasenya;
	protected JLabel labelContrasenya;
	protected JTextField textfieldContrasenya;
	protected JPanel panelBotones;
	protected JButton botonLogin;
	protected JButton botonSignup;
	private HashMap<String, Jugador> users = new HashMap<String, Jugador>();
	
	public Login(VentanaPrincipal ventanaPrincipal) {
		
		cargarUsuarios("users.dat");
		
		panelLogin = new JPanel(new GridLayout(3,1));
		
		panelUsuario = new JPanel(new GridLayout(2,1));
		labelUsuario = new JLabel("Usuario:");
		textfieldUsuario = new JTextField();
		
		panelUsuario.add(labelUsuario);
		panelUsuario.add(textfieldUsuario);
		
		
		panelContrasenya = new JPanel(new GridLayout(2,1));
		labelContrasenya = new JLabel("Password");
		textfieldContrasenya = new JTextField();
		
		panelContrasenya.add(labelContrasenya);
		panelContrasenya.add(textfieldContrasenya);
		
		panelBotones = new JPanel( new GridLayout(1,2));
		botonLogin = new JButton("Log In");
		botonSignup = new JButton("Registro");
		panelBotones.add(botonLogin);
		panelBotones.add(botonSignup);
		
		botonLogin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String username = textfieldUsuario.getText();
				String passw = textfieldContrasenya.getText();
				
				
				//método temporal para "logear" usuarios antes de configrara la base de datos final
				
				Jugador expectedUser = users.get(username);
				
				if (expectedUser!=null&&expectedUser.checkPassword(passw)) {
					System.out.println("Logeado como "+ expectedUser);
					ventanaPrincipal.mainMenu();
					textfieldUsuario.setText("");
					textfieldContrasenya.setText("");
				}
				else {
					
					JOptionPane.showMessageDialog(null, "Contraseña o usuario incorrectos", "Error de autenticación", JOptionPane.ERROR_MESSAGE);

				}
				
//			
			}
		});
		
		botonSignup.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String username = textfieldUsuario.getText();
				String passw = textfieldContrasenya.getText();
				
				if (!username.equals("")&&!passw.equals(""));
				
				users.put(username, new Jugador(username,passw)); // si ya hay un user lo sobreescribe pero nos da igual pk todo esto es temp
				guardarUsuarios("users.dat");
				
			}
		});
		
		
		panelLogin.add(panelUsuario);
		panelLogin.add(panelContrasenya);
		panelLogin.add(panelBotones);
		
		this.add(panelLogin);
	}

    public void guardarUsuarios(String filePath) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filePath))) {
            outputStream.writeObject(users);
            System.out.println("Fichero de usuarios actualizado " + filePath);
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
