package ventanas;

import javax.swing.*;

import componentes.BButton;
import componentes.RButton;
import componentes.navBar;
import juego.LogicaPartida;
import librerias.FontAwesome;
import librerias.IconFontSwing;
import utils.Configuracion;
import utils.Escalador;
import utils.Session;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class MadChessConfig extends JPanel {
    private ArrayList<String> selectedAlters = new ArrayList<>();
    private ArrayList<String> selectedBoosts = new ArrayList<>();

    protected RButton volverBtn = new RButton("Volver");

    public MadChessConfig() {

       		
		//--------------------- NAVBAR-------------------------------------------
  		Icon icon = IconFontSwing.buildIcon(FontAwesome.BACKWARD, Escalador.escalarF(15));
  		volverBtn.setIcon(icon);
  		
		JPanel navBarContainer = new JPanel(new FlowLayout());
		navBarContainer.add(new navBar(volverBtn));
        navBarContainer.setBackground(Configuracion.BACKGROUND);
      
  		
  		//--------------------- NAVBAR-------------------------------------------	   
      		
       

        setLayout(new BorderLayout());
        setBackground(Configuracion.BACKGROUND);

        // Añadir navbar en la parte superior (norte)
        add(navBarContainer, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setPreferredSize(new Dimension(550, 400));
        centerPanel.setBackground(Configuracion.BACKGROUND);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 0, 10, 0); // Espaciado entre componentes

        // Panel para seleccionar 3 alters
        String[] alterImgsBlack = {"bb", "bk", "bn", "bp", "bq", "br"};
        String[] alterImgsWhite = {"wb", "wk", "wn", "wp", "wq", "wr"};
        
        
        addTextPanel(centerPanel, gbc, "Selecciona tus 3 alters:");
        JPanel altersPanel = createImagePanel(6, 1, 6,alterImgsWhite,selectedAlters);
        gbc.gridy++;
        addCenteredComponent(centerPanel, gbc, altersPanel);

        // Restablece las restricciones
        gbc.gridy++;
        gbc.gridx = 0;

        // Panel para seleccionar 3 boosts
        String[] boostImgs = {"bomba","hielo","bb"};
        addTextPanel(centerPanel, gbc, "Selecciona tus 3 boosts:");
        JPanel boostsPanel = createImagePanel(3, 1, 3,boostImgs,selectedBoosts);
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

    private JPanel createImagePanel(int numImages, int rows, int cols, String[] imageNames, ArrayList<String> selectedItems) {
        JPanel panel = new JPanel(new GridLayout(rows, cols, 10, 10));
        panel.setBackground(Configuracion.BACKGROUND);
        panel.setBorder(null);

        for (int i = 0; i < numImages; i++) {
            String imageName = imageNames[i];

            ImageIcon icon = new ImageIcon(getClass().getResource("../srcmedia/" + imageName + ".png"));
            Image image = icon.getImage();
            Image newImage = image.getScaledInstance(70, 70, Image.SCALE_SMOOTH);
            ImageIcon newIcon = new ImageIcon(newImage);

            JLabel label = new JLabel(newIcon);
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setBorder(BorderFactory.createLineBorder(Configuracion.BACKGROUND));
            label.setCursor(new Cursor(Cursor.HAND_CURSOR));

            label.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                	
                    if (selectedItems.contains(imageName)) {
                        // Deselecciona el elemento
                        selectedItems.remove(imageName);
                        Image currentImage = newIcon.getImage();
                        Image newImage = adjustImageOpacity(currentImage, 1);
                        ImageIcon adjustedIcon = new ImageIcon(newImage);
                        label.setIcon(adjustedIcon);
                    } else if (selectedItems.size() < 3) {
                        // Selecciona el elemento solo si no ha alcanzado el límite de 3
                        selectedItems.add(imageName);

                        // Ajusta la opacidad
                        Image currentImage = newIcon.getImage();
                        Image newImage = adjustImageOpacity(currentImage, 0.2);
                        ImageIcon adjustedIcon = new ImageIcon(newImage);
                        label.setIcon(adjustedIcon);
                    }
                    System.out.println(selectedItems);
                }
            });
            
            

            panel.add(label);
        }

        return panel;
    }

    
    private Image adjustImageOpacity(Image image, double opacity) {
        BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) opacity));
        g2d.drawImage(image, 0, 0, null);
        g2d.dispose();
        return bufferedImage;
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
