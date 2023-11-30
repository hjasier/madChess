package ventanas;

import javax.swing.*;

import componentes.BButton;
import componentes.RButton;
import componentes.navBar;
import juego.Configuracion;
import juego.Escalador;
import juego.LogicaPartida;
import juego.Session;
import librerias.FontAwesome;
import librerias.IconFontSwing;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MadChessConfig extends JPanel {
    protected RButton loginBtn = new RButton("Volver");

    public MadChessConfig() {
        //--------------------- NAVBAR-------------------------------------------
        Icon icon = IconFontSwing.buildIcon(FontAwesome.BACKWARD, Escalador.escalarF(15));
        loginBtn.setIcon(icon);
        JPanel navBar = new navBar(loginBtn);
        //--------------------- NAVBAR-------------------------------------------

        setLayout(new BorderLayout());
        setBackground(Configuracion.BACKGROUND);

        // Añadir navbar en la parte superior (norte)
        add(navBar, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setPreferredSize(new Dimension(550, 400));
        centerPanel.setBackground(Configuracion.BACKGROUND);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 0, 10, 0); // Espaciado entre componentes

        // Panel para seleccionar 3 alters
        addTextPanel(centerPanel, gbc, "Selecciona tus 3 alters:");
        JPanel altersPanel = createImagePanel(6, 1, 6);
        gbc.gridy++;
        addCenteredComponent(centerPanel, gbc, altersPanel);

        // Restablece las restricciones
        gbc.gridy++;
        gbc.gridx = 0;

        // Panel para seleccionar 3 boosts
        addTextPanel(centerPanel, gbc, "Selecciona tus 3 boosts:");
        JPanel boostsPanel = createImagePanel(3, 1, 3);
        gbc.gridy++;
        addCenteredComponent(centerPanel, gbc, boostsPanel);

        // Restablece las restricciones
        gbc.gridy++;
        gbc.gridx = 0;

        BButton botonListo = new BButton("Listo");
        botonListo.setPreferredSize(Escalador.newDimension(100, 40));
        gbc.gridy++;
        addCenteredComponent(centerPanel, gbc, botonListo);
        
        // Alinea el panel central en el centro de la ventana
        add(centerPanel, BorderLayout.CENTER);
        
        
        botonListo.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
            	VentanaPrincipal ventana = Session.getVentana();
            	ventana.getCardLayout().show(ventana.getPanelPrincipal(), "JUEGO");
        		new LogicaPartida();
            	
            }
        });
    }

    private JPanel createImagePanel(int numImages, int rows, int cols) {
        JPanel panel = new JPanel(new GridLayout(rows, cols, 10, 10));
        panel.setBackground(Configuracion.BACKGROUND);
        panel.setBorder(null);

        for (int i = 1; i <= numImages; i++) {
            ImageIcon icon = new ImageIcon(getClass().getResource("../srcmedia/bb.png"));
            Image image = icon.getImage();
            Image newImage = image.getScaledInstance(70, 70, Image.SCALE_SMOOTH);
            ImageIcon newIcon = new ImageIcon(newImage);

            JLabel label = new JLabel(newIcon);
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setBorder(BorderFactory.createLineBorder(Configuracion.BACKGROUND));
            label.setCursor(new Cursor(Cursor.HAND_CURSOR));

            // Cambia de color al hacer clic
            label.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    label.setBorder(BorderFactory.createLineBorder(Color.blue));
                }
            });

            panel.add(label);
        }

        return panel;
    }

    private void addTextPanel(JPanel panel, GridBagConstraints gbc, String text) {
        JLabel label = new JLabel(text);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(label, gbc);
    }

    private void addCenteredComponent(JPanel panel, GridBagConstraints gbc, Component component) {
        JPanel centeredPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        centeredPanel.setBackground(Configuracion.BACKGROUND);
        centeredPanel.add(component);
        panel.add(centeredPanel, gbc);
    }
}
