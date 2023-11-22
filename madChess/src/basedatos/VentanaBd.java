package basedatos;

import javax.swing.*;

import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaBd extends JFrame {

    private JTextField tfUsername, tfImg, tfRank;
    private JPasswordField tfPassw;
    public VentanaBd() {
        // Configuración básica de la ventana
        super("Ventana de Prueba BD");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);

        // Crear componentes
        tfUsername = new JTextField(20);
        tfPassw = new JPasswordField(20);
        tfImg = new JTextField(20);
        tfRank = new JTextField(20);
        JButton btnInsertar = new JButton("Insertar Usuario");
        JButton btnMostrar = new JButton("Mostrar Usuarios");
        JButton btnEliminar = new JButton("Eliminar Usuario");
        JButton btnModificar = new JButton("Modificar Usuario");

        JPanel panelBotones = new JPanel(new GridLayout(2,2));
        // Configurar el diseño de la ventana
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        // Agregar componentes a la ventana
        add(new JLabel("Username:"));
        add(tfUsername);
        add(new JLabel("Password:"));
        add(tfPassw);
        add(new JLabel("Image Route:"));
        add(tfImg);
        add(new JLabel("Rank:"));
        add(tfRank);
        panelBotones.add(btnInsertar);
        panelBotones.add(btnMostrar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnModificar);
        add(panelBotones);
        pack();
        // Configurar acciones para los botones
        btnInsertar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertarUsuarioDesdeVentana();
            }
        });

        btnMostrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarUsuariosDesdeVentana();
            }
        });
        
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	eliminarUsuarioDesdeVentana();
            }
        });
        
        btnModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	modificarUsuarioDesdeVentana();
            }
        });
    }

    private void insertarUsuarioDesdeVentana() {
        String username = tfUsername.getText();
        char[] pass = tfPassw.getPassword();
        String passw = "" + pass;
        String img = tfImg.getText();
        String strrank = tfRank.getText();
        int rank = Integer.parseInt(strrank);
        GestionBd.insertarUsuario(username, passw, img, rank);
        tfUsername.setText("");
        tfPassw.setText("");
    }

    private void mostrarUsuariosDesdeVentana() {
    	GestionBd.mostrarUsuarios();
    }
    
    private void eliminarUsuarioDesdeVentana() {
        String username = tfUsername.getText();
        GestionBd.eliminarUsuarios(username);
        tfUsername.setText("");
        tfPassw.setText("");
    }

    private void modificarUsuarioDesdeVentana() {
    	 String username = tfUsername.getText();
         char[] pass = tfPassw.getPassword();
         String passw = "" + pass;
         String img = tfImg.getText();
         String strrank = tfRank.getText();
         int rank = Integer.parseInt(strrank);
         GestionBd.modificarUsuarios(username, passw, img, rank);
        tfUsername.setText("");
        tfPassw.setText("");
    }
    
    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new VentanaBd().setVisible(true);
                
                GestionBd.crearTablaUsuario();

                GestionBd.insertarUsuario("admin", "admin","C:/ruta", 39);

            }
        });
    }
}
