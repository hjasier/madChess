package ventanas;

import javax.swing.*;

import componentes.BButton;
import componentes.RButton;
import componentes.navBar;
import juego.LogicaPartida;
import librerias.FontAwesome;
import librerias.IconFontSwing;
import objetos.Jugador;
import utils.Configuracion;
import utils.Escalador;
import utils.Session;
import utils.utils;

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
    
    private ArrayList<Jugador>  players;
    private Jugador  curPlayer;
    private JPanel altersPanel;
    private JPanel boostsPanel;
    private BButton botonListo;

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
        altersPanel = createImagePanel(alterImgsWhite.length, 1, alterImgsWhite.length,alterImgsWhite,selectedAlters);
        gbc.gridy++;
        addCenteredComponent(centerPanel, gbc, altersPanel);

        // Restablece las restricciones
        gbc.gridy++;
        gbc.gridx = 0;

        // Panel para seleccionar 3 boosts
        String[] boostImgs = {"bomba","hielo","presagio","control","mina"};
        addTextPanel(centerPanel, gbc, "Selecciona tus 3 boosts:");
        boostsPanel = createImagePanel(boostImgs.length, 1, boostImgs.length,boostImgs,selectedBoosts);
        gbc.gridy++;
        addCenteredComponent(centerPanel, gbc, boostsPanel);

        // Restablece las restricciones
        gbc.gridy++;
        gbc.gridx = 0;

        botonListo = new BButton("Listo");
        botonListo.setPreferredSize(Escalador.newDimension(100, 40));
        gbc.gridy++;
        addCenteredComponent(centerPanel, gbc, botonListo);
        
       

        // Alinea el panel central en el centro de la ventana
        add(centerPanel, BorderLayout.CENTER);
        
        
        botonListo.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
            	VentanaPrincipal ventana = Session.getVentana();
            	
            	if (players.indexOf(curPlayer) == players.size()-1) {
            		//Si es el ultimo jugador
            		ventana.getCardLayout().show(ventana.getPanelPrincipal(), "JUEGO");
            		new LogicaPartida();
            	}
            	else {
            		curPlayer = players.get(players.indexOf(curPlayer)+1);
            		resetSelections();
            		
            		
            		
            		
            	}
            		
        		
            	
            }

			
        });
    }

    
    
    protected void resetSelections() {
		selectedAlters.clear();
        selectedBoosts.clear();
        resetOpacity(altersPanel);
        resetOpacity(boostsPanel);
        
        if (players.indexOf(curPlayer) == players.size()-1) {            			
			botonListo.setText("Empezar");
		}
		else {
			botonListo.setText("Siguiente");
		}
        
		
	}
    
    private void resetOpacity(JPanel panel) {
        Component[] components = panel.getComponents();
        for (Component component : components) {
            if (component instanceof JLabel) {
                JLabel label = (JLabel) component;
                ImageIcon defaultIcon = (ImageIcon) label.getIcon();
                label.setIcon(new ImageIcon(utils.adjustImageOpacity(defaultIcon.getImage(), 0.2)));
            }
        }
    }

	private JPanel createImagePanel(int numImages, int rows, int cols, String[] imageNames, ArrayList<String> selectedItems) {
        JPanel panel = new JPanel(new GridLayout(rows, cols, 10, 10));
        panel.setBackground(Configuracion.BACKGROUND);
        panel.setBorder(null);

        for (int i = 0; i < numImages; i++) {
            String imageName = imageNames[i];
            
            ImageIcon originalIcon = new ImageIcon(getClass().getResource("/srcmedia/" + imageName + ".png"));
            Image originalImage = originalIcon.getImage();

            // Reducir el tamaño de la imagen a 70x70
            Image scaledImage = originalImage.getScaledInstance(70, 70, Image.SCALE_SMOOTH);

            // Crear un nuevo ImageIcon con la imagen escalada
            ImageIcon scaledIcon = new ImageIcon(scaledImage);

            ImageIcon defaultIcon = new ImageIcon(utils.adjustImageOpacity(scaledIcon.getImage(), 0.2));
            ImageIcon illuminatedIcon = new ImageIcon(utils.adjustImageOpacity(scaledIcon.getImage(), 1.0));

            JLabel label = new JLabel(defaultIcon);
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setBorder(BorderFactory.createLineBorder(Configuracion.BACKGROUND));
            label.setCursor(new Cursor(Cursor.HAND_CURSOR));

            label.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (selectedItems.contains(imageName)) {
                        // Deselecciona el elemento
                        selectedItems.remove(imageName);
                        label.setIcon(defaultIcon);
                    } else if (selectedItems.size() < 3) {
                        // Selecciona el elemento solo si no ha alcanzado el límite de 3
                        selectedItems.add(imageName);
                        label.setIcon(illuminatedIcon);
                    }
                    System.out.println(selectedItems);
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

	public void setPlayers(ArrayList<Jugador> players) {
		this.players = players;
		curPlayer = players.get(0);
		resetSelections();
		}

    

    
    
    
}
