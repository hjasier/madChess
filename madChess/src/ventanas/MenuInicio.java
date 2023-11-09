package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.*;

public class MenuInicio extends JPanel {

    protected JPanel navBar;
    protected JPanel opciones;
    protected JButton loginBtn = new JButton("Login");
    protected JLabel nombre = new JLabel("MadChess");
    protected JButton partidaLocal = new JButton("Partida Local");
    protected JButton crearPOnline = new JButton("Crear partida online");
    protected JButton joinPOnline = new JButton("Join partida online");

    private ImageIcon imgFoto = new ImageIcon(getClass().getResource("../src_piezas/imagenMenuInicio.png"));
    protected JLabel fondoImagen = new JLabel(imgFoto);

    protected Color colorTemp = new Color(16, 16, 16);

    public MenuInicio() {
        this.setLayout(new BorderLayout());

        navBar = new JPanel();
        opciones = new JPanel();

        navBar.setBackground(colorTemp);
        opciones.setBackground(colorTemp);

        navBar.setLayout(new BorderLayout());
        opciones.setLayout(new BoxLayout(opciones, BoxLayout.Y_AXIS));
        fondoImagen.setLayout(new BorderLayout());


        navBar.add(nombre, BorderLayout.NORTH);
        navBar.add(fondoImagen, BorderLayout.CENTER);
        

        partidaLocal.setAlignmentX(Component.CENTER_ALIGNMENT);
        crearPOnline.setAlignmentX(Component.CENTER_ALIGNMENT);
        joinPOnline.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginBtn.setAlignmentX(Component.CENTER_ALIGNMENT);        
        
        opciones.add(partidaLocal);
        opciones.add(crearPOnline);
        opciones.add(joinPOnline);
        opciones.add(loginBtn);

        
        
        this.add(navBar, BorderLayout.CENTER);
        this.add(opciones, BorderLayout.SOUTH);

    }
}