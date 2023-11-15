
package ventanas;

import componentes.BButton;
import componentes.FontAwesome;
import componentes.IconFontSwing;
import componentes.RButton;

import javax.swing.*;
import java.awt.*;

import javax.swing.*;
import java.awt.*;

public class VentanaLogin extends JPanel {
    protected ImageIcon imgFoto = new ImageIcon(getClass().getResource("../srcmedia/logo110x300.png"));

    protected BButton botonVolver;
    protected BButton botonLogin;
    protected BButton botonSignup;
    
	public VentanaLogin(VentanaPrincipal ventanaPrincipal) {
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
       

        
        JPanel panelContra = new JPanel();
       
        JLabel labelContra = new JLabel("Contraseña: ");
        JTextField textfieldContra = new JTextField(20);
        panelContra.add(labelContra);
        panelContra.add(textfieldContra);
       
        panelContra.setBackground(this.getBackground());
        
        JPanel panelUser = new JPanel();
        JLabel labelUsuario = new JLabel("Nombre de Usuario:");
        JTextField textfieldUser = new JTextField(20);
        panelUser.add(labelUsuario);
        panelUser.add(textfieldUser);
       
        panelUser.setBackground(this.getBackground());
        
        botonLogin = new BButton("Log In");
        botonVolver = new BButton("Volver");
        botonSignup = new BButton("Sign Up");
//        new BButton("Iniciar Partida");
//        new BButton("Volver");
        // Configuración del layout con BorderLayout
        setLayout(new BorderLayout());

        // Agregar el navBar en la posición NORTH
        add(navBar, BorderLayout.NORTH);

        // Agregar elementos al panel con GridBagLayout en el centro
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridBagLayout());
        contentPanel.setBackground(getBackground());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        
        gbc.gridx = 0;
        gbc.gridy = 0;
        contentPanel.add(panelUser, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        contentPanel.add(panelContra, gbc);
//        gbc.gridx = 1;
//        contentPanel.add(labelCodigoValor, gbc);
//
//        gbc.gridx = 0;
//        gbc.gridy = 1;
//        contentPanel.add(labelModoJuego, gbc);
//
//        gbc.gridx = 1;
//        contentPanel.add(comboModoJuego, gbc);
//
//        gbc.gridx = 0;
//        gbc.gridy = 2;
//        contentPanel.add(labelJugador1, gbc);
//
//        gbc.gridx = 1;
//        contentPanel.add(panelJugador1, gbc);
//
//        gbc.gridx = 0;
//        gbc.gridy = 3;
//        contentPanel.add(labelJugador2, gbc);
//
//        gbc.gridx = 1;
//        contentPanel.add(panelJugador2, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        contentPanel.add(botonLogin, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        contentPanel.add(botonSignup, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        contentPanel.add(botonVolver, gbc);
        
        // Agregar contentPanel en el centro
        add(contentPanel, BorderLayout.CENTER);

        // Configurar acción del botón Iniciar Partida
        botonLogin.addActionListener(e -> {
            // Aquí puedes agregar la lógica para iniciar la partida
            // Por ejemplo, obtener los valores seleccionados en los combo boxes y realizar acciones relacionadas.
        });
    }

}

