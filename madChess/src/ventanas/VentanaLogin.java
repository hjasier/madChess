package ventanas;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class VentanaLogin extends JFrame{
	
	
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
	
	public VentanaLogin() {
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
		botonSignup = new JButton("Sign Up");
		panelBotones.add(botonLogin);
		panelBotones.add(botonSignup);
		
		botonLogin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String usuario = textfieldUsuario.getText();
				String contra = textfieldContrasenya.getText();
				textfieldUsuario.setText("");
				textfieldContrasenya.setText("");
				
				//hay que comparar con los usuarios existentes
			}
		});
		
		botonSignup.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String usuario = textfieldUsuario.getText();
				String contra = textfieldContrasenya.getText();
				textfieldUsuario.setText("");
				textfieldContrasenya.setText("");
				
				//hay que añadir a los usuarios existentes
			}
		});
		
		
		panelLogin.add(panelUsuario);
		panelLogin.add(panelContrasenya);
		panelLogin.add(panelBotones);
		this.add(panelLogin);
		
		this.setSize(500,200);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
       pack();
	}
}
