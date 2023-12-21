package ventanas;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Random;

import objetos.Jugador;
import utils.Configuracion;
import utils.Escalador;
import utils.Session;
import componentes.*;
import database.GestorDB;
import librerias.FontAwesome;
import librerias.IconFontSwing;

import javax.swing.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Register extends JPanel {

    protected JPanel navBar;
    protected JPanel opciones;
    protected RButton volverBtn = new RButton("Volver");

    protected BTextField inputNombre = new BTextField("Nombre de usuario", false);
    protected BTextField inputContra = new BTextField("Contraseña", true);
    protected BButton registerBtn = new BButton("Registrarse");
    protected JLabel loginLabel = new JLabel("¿Ya tienes una cuenta? Inicia sesión aquí");
    protected JLabel labelTitulo = new JLabel("Regístrate para crear una cuenta");

    protected Paneles redirect = Paneles.MENU_INICIO;

    public Register() {
        this.setLayout(new BorderLayout());


        opciones = new JPanel();

        opciones.setBackground(Configuracion.BACKGROUND);
        opciones.setLayout(new BoxLayout(opciones, BoxLayout.Y_AXIS));

		//--------------------- NAVBAR-------------------------------------------
  		Icon icon = IconFontSwing.buildIcon(FontAwesome.BACKWARD, Escalador.escalarF(15));
  		volverBtn.setIcon(icon);
  		
		JPanel navBarContainer = new JPanel(new FlowLayout());
		navBarContainer.add(new navBar(volverBtn));
        navBarContainer.setBackground(Configuracion.BACKGROUND);
      
  		
  		//--------------------- NAVBAR-------------------------------------------	   
      		


        opciones.setLayout(new BoxLayout(opciones, BoxLayout.Y_AXIS));

        inputNombre.setMaximumSize(new Dimension(Escalador.escalar(200), Escalador.escalar(40)));
        inputContra.setMaximumSize(new Dimension(Escalador.escalar(200), Escalador.escalar(40)));
        registerBtn.setMaximumSize(new Dimension(Escalador.escalar(200), Escalador.escalar(40)));

        loginLabel.setForeground(new Color(213, 213, 213));
        labelTitulo.setForeground(new Color(236, 236, 236));

        loginLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        labelTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        inputNombre.setAlignmentX(Component.CENTER_ALIGNMENT);
        inputContra.setAlignmentX(Component.CENTER_ALIGNMENT);
        registerBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        opciones.add(Box.createRigidArea(Escalador.newDimension(0, 100)));
        opciones.add(labelTitulo);
        opciones.add(Box.createRigidArea(Escalador.newDimension(0, 40))); // Espacio entre los botones
        opciones.add(inputNombre);
        opciones.add(Box.createRigidArea(Escalador.newDimension(0, 10))); // Espacio entre los botones
        opciones.add(inputContra);
        opciones.add(Box.createRigidArea(Escalador.newDimension(0, 20))); // Espacio entre los botones
        opciones.add(registerBtn);
        opciones.add(Box.createRigidArea(Escalador.newDimension(0, 20))); // Espacio entre los botones
        opciones.add(loginLabel);

        this.add(navBarContainer, BorderLayout.NORTH);
        this.add(opciones, BorderLayout.CENTER);

        registerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = inputNombre.getText();
                String passw = inputContra.getText();

                // Temporary method to "register" users before configuring the final database

                if (username.equals("") && passw.equals("")) {
                	return;
                } ;

//                int newUserId = 10000 + new Random().nextInt(90000);
//
//                users.put(username, new Jugador(newUserId, username, passw));
//                guardarUsuarios("users.dat");
//                JOptionPane.showMessageDialog(null, "Usuario registrado exitosamente", "Registro exitoso", JOptionPane.INFORMATION_MESSAGE);
                String returndata = GestorDB.insertarUsuario(username, passw);
                
                JOptionPane.showMessageDialog(null, returndata, "Información", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        
        
        loginLabel.addMouseListener(new MouseAdapter() {
					
					@Override
					public void mouseClicked(MouseEvent e) {
						VentanaPrincipal ventanaPrincip = Session.getVentana();
						ventanaPrincip.getCardLayout().show(ventanaPrincip.getPanelPrincipal(), Paneles.LOGIN);
					}
		});

        
    }



}
