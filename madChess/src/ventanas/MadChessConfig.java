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
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class MadChessConfig extends JPanel {
    private ArrayList<String> selectedAlters = new ArrayList<>();
    private ArrayList<String> selectedBoosts = new ArrayList<>();
    
    protected RButton volverBtn = new RButton("Volver");
    
    private ArrayList<Jugador>  players;
    private Jugador  curPlayer;
    private JPanel altersPanel;
    private JPanel boostsPanel;
    private BButton botonListo;
    
    private String[] alterImgsWhite = {"wbDFALTER", "wkDFALTER", "wnDFALTER", "wpDFALTER", "wqDFALTER", "wrDFALTER"};
    private String[] boostImgs = {"bomba","hielo","presagio","control","mina"};
    
    
    
    class ImageLabel extends JLabel {
        private String imageName;

        public ImageLabel(String imageName, ImageIcon defaultIcon) {
            super(defaultIcon);
            this.imageName = imageName;
            setHorizontalAlignment(SwingConstants.CENTER);
            setBorder(BorderFactory.createLineBorder(Configuracion.BACKGROUND));
            setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

        public String getImageName() {
            return imageName;
        }
    }
    
    
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
        
        enum piezasAlter {ALFIL,REY,CABALLO,PEON,REINA,TORRE};
        
		
        addTextPanel(centerPanel, gbc, "Selecciona tus 3 alters:");
        altersPanel = createImagePanel(alterImgsWhite.length, 1, alterImgsWhite.length,"/srcmedia/piezas/",alterImgsWhite,selectedAlters);
        gbc.gridy++;
        addCenteredComponent(centerPanel, gbc, altersPanel);

        // Restablece las restricciones
        gbc.gridy++;
        gbc.gridx = 0;

        // Panel para seleccionar 3 boosts
        
        addTextPanel(centerPanel, gbc, "Selecciona tus 3 boosts:");
        boostsPanel = createImagePanel(boostImgs.length, 1, boostImgs.length,"/srcmedia/",boostImgs,selectedBoosts);
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
            	
            	
            	
            	if (!Session.getDatosPartida().isOnline()) {
        		curPlayer.setAlters(getSelectedAlters());
            	curPlayer.setBoosts(getSelectedBoosts());
            		
            	if (players.indexOf(curPlayer) == players.size()-1) {
            		//Si es el ultimo jugador
            		ventana.getCardLayout().show(ventana.getPanelPrincipal(), Paneles.JUEGO);
            		new LogicaPartida();
            	}
            	else {
            		curPlayer = players.get(players.indexOf(curPlayer)+1);
            		resetSelections();

            		
            	}
        		
            	}
            	else {
            		try {
						Session.getCtsConnection().postMadSelects(getSelectedAlters(), getSelectedBoosts());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
            		
            		
            		if (Session.getDatosPartida().getJugadores().get(0).getUsuario().getUsername().equals(Session.getCurrentUser().getUsername())) {
            			
						if (Session.getDatosPartida().getJugadoresListos().size() == Session.getDatosPartida().getJugadores().size()) {
							ventana.getCardLayout().show(ventana.getPanelPrincipal(), Paneles.JUEGO);
							new LogicaPartida();
							try {
								Session.getCtsConnection().postInitGame();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						} else {
							System.out.println("Esperando a que los jugadores se preparen");
						}
            		}
            		else {//no admin
            			System.out.println("respuesta enviada");
            		}
            	}
            }

			
        });
    }

    
    
    protected void resetSelections() {
        resetOpacity(altersPanel, selectedAlters);
        resetOpacity(boostsPanel, selectedBoosts);
		selectedAlters.clear();
        selectedBoosts.clear();
       
        
        if (players.indexOf(curPlayer) == players.size()-1) {            			
			botonListo.setText("Empezar");
		}
		else {
			botonListo.setText("Siguiente");
		}
        
		
	}
    
    private void resetOpacity(JPanel panel, ArrayList<String> selected) {
        Component[] components = panel.getComponents();
        for (Component component : components) {
            if (component instanceof ImageLabel) {
                ImageLabel label = (ImageLabel) component;
                ImageIcon defaultIcon = (ImageIcon) label.getIcon();
                
                // Verificar si la imagen está seleccionada
                String imageName = label.getImageName();
                if (selected.contains(imageName)) {
                    label.setIcon(new ImageIcon(utils.adjustImageOpacity(defaultIcon.getImage(), 0.2)));
                }
            }
        }
    }

    private JPanel createImagePanel(int numImages, int rows, int cols,String ruta, String[] imageNames, ArrayList<String> selectedItems) {
        JPanel panel = new JPanel(new GridLayout(rows, cols, 10, 10));
        panel.setBackground(Configuracion.BACKGROUND);
        panel.setBorder(null);

        for (int i = 0; i < numImages; i++) {
            String imageName = imageNames[i];
            
            ImageIcon originalIcon = new ImageIcon(getClass().getResource(ruta + imageName + ".png"));
            Image originalImage = originalIcon.getImage();

            // Reducir el tamaño de la imagen a 70x70
            Image scaledImage = originalImage.getScaledInstance(70, 70, Image.SCALE_SMOOTH);

            // Crear un nuevo ImageIcon con la imagen escalada
            ImageIcon scaledIcon = new ImageIcon(scaledImage);

            ImageIcon defaultIcon = new ImageIcon(utils.adjustImageOpacity(scaledIcon.getImage(), 0.2));
            ImageIcon illuminatedIcon = new ImageIcon(utils.adjustImageOpacity(scaledIcon.getImage(), 1.0));

            ImageLabel label = new ImageLabel(imageName, defaultIcon);

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

    
    private ArrayList<Boolean> selectedABool(ArrayList<String> selectedEls,String[] options) {
    	ArrayList<Boolean> stringABool = new ArrayList<Boolean>(Arrays.asList(false, false, false, false, false, false));
    	for (int i = 0; i < options.length; i++) {
			if (selectedEls.contains(options[i])) {
				stringABool.set(i, true);
			} else {
				stringABool.set(i, false);
			}
    	}
    	return stringABool;
    }
    
	public ArrayList<Boolean> getSelectedAlters() {
		return selectedABool(selectedAlters, alterImgsWhite);
	}
	
	public ArrayList<Boolean> getSelectedBoosts() {
		return selectedABool(selectedBoosts, boostImgs);
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
