
package ventanas;

import componentes.BButton;
import componentes.RButton;
import librerias.FontAwesome;
import librerias.IconFontSwing;
import objetos.Jugador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import javax.swing.*;
import java.awt.*;

public class LoginAntiguo extends JPanel {
    protected ImageIcon imgFoto = new ImageIcon(getClass().getResource("../srcmedia/logo110x300.png"));

    protected RButton botonVolver;
    protected BButton botonLogin;
    protected RButton botonSignup;
    
	private HashMap<String, Jugador> users = new HashMap<String, Jugador>();
    
	public LoginAntiguo(VentanaPrincipal ventanaPrincipal) {
        JPanel navBar = new JPanel();
        JLabel foto = new JLabel(imgFoto);
        Color colorTemp = new Color(16, 16, 16);
        JPanel panelLogo = new JPanel();
        setBackground(colorTemp);
        
        navBar.setLayout(new BorderLayout());
        navBar.setBackground(getBackground());

        foto.setIcon(imgFoto);

        panelLogo.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        panelLogo.add(foto, BorderLayout.WEST);
        panelLogo.setBackground(getBackground());

        navBar.add(panelLogo, BorderLayout.WEST);

        
        
        // Elementos de la interfaz
       
        JLabel labelInicio = new JLabel("Inicia sesión para continuar: ");
        
        Font fuente = labelInicio.getFont();
        labelInicio.setFont(new Font(fuente.getName(), Font.PLAIN, 15));
        labelInicio.setForeground(Color.white);
        
        
        JLabel labelFinal = new JLabel("¿No tienes cuenta? Registrate aquí: ");
        JPanel panelContra = new JPanel();
       
        JLabel labelContra = new JLabel("Contraseña:                        ");
        labelContra.setFont(new Font(fuente.getName(), Font.PLAIN, 16));
        labelContra.setForeground(Color.white);
        
        JPasswordField textfieldContra = new JPasswordField(15);
        textfieldContra.setFont(new Font(fuente.getName(), Font.PLAIN, 16));
        textfieldContra.setForeground(Color.white);
        
        textfieldContra.setBackground(this.getBackground());
        panelContra.add(labelContra);
        panelContra.add(textfieldContra);
       
        panelContra.setBackground(this.getBackground());
        
        JPanel panelUser = new JPanel();
        JLabel labelUsuario = new JLabel("Nombre de Usuario:          ");
        labelUsuario.setFont(new Font(fuente.getName(), Font.PLAIN, 16));
        labelUsuario.setForeground(Color.white);
        
        JTextField textfieldUser = new JTextField(15);
        textfieldUser.setFont(new Font(fuente.getName(), Font.PLAIN, 16));
        textfieldUser.setForeground(Color.white);
        
        textfieldUser.setBackground(this.getBackground());
        panelUser.add(labelUsuario);
        panelUser.add(textfieldUser);
       
        panelUser.setBackground(this.getBackground());
        
        botonLogin = new BButton("Log In");
        botonVolver = new RButton("Volver");
        botonSignup = new RButton("aquí");

  
        
        setLayout(new BorderLayout());


        add(navBar, BorderLayout.NORTH);


        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridBagLayout());
        contentPanel.setBackground(getBackground());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        contentPanel.add(labelInicio, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        contentPanel.add(panelUser, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        contentPanel.add(panelContra, gbc);


        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        contentPanel.add(botonLogin, gbc);
        
   
  
        //
        
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        contentPanel.add(labelFinal, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        contentPanel.add(botonSignup, gbc);
        
        
     
        this.add(botonVolver, BorderLayout.SOUTH);
        
        add(contentPanel, BorderLayout.CENTER);
        
        
        
        
        
        
        
        
        botonLogin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String username = textfieldUser.getText();
				String passw = textfieldContra.getText();
				
				
				//método temporal para "logear" usuarios antes de configrara la base de datos final
				
				Jugador expectedUser = users.get(username);
				
				if (expectedUser!=null&&expectedUser.checkPassword(passw)) {
					System.out.println("Logeado como "+ expectedUser);
					
					textfieldUser.setText("");
					textfieldContra.setText("");
				}
				else {
					
					JOptionPane.showMessageDialog(null, "Contraseña o usuario incorrectos", "Error de autenticación", JOptionPane.ERROR_MESSAGE);

				}
				
			
			}
		});
		
		botonSignup.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String username = textfieldUser.getText();
				String passw = textfieldContra.getText();
				
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


