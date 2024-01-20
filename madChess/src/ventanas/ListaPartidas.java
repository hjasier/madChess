package ventanas;

import librerias.FontAwesome;
import librerias.IconFontSwing;
import utils.Configuracion;
import utils.Escalador;
import utils.Session;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

import javax.swing.border.*;

import componentes.BButton;
import componentes.MButton;
import componentes.MScrollPane;
import componentes.RButton;
import componentes.navBar;
import juego.DatosPartida;

public class ListaPartidas extends JPanel {
    private Color colorFondo = Configuracion.BACKGROUND;
    private JPanel listaPanel = new JPanel();
    protected RButton backBtn = new RButton("Volver");;
    private static volatile boolean rotate = true;
    private ExecutorService executorService;
    private Timer rotationTimer;
    
    public ListaPartidas() {
        setLayout(new BorderLayout());
        setBackground(colorFondo);

      //--------------------- NAVBAR-------------------------------------------
  		Icon icon = IconFontSwing.buildIcon(FontAwesome.BACKWARD, Escalador.escalarF(15));
  		backBtn.setIcon(icon);
  		
		JPanel navBarContainer = new JPanel(new FlowLayout());
		navBarContainer.add(new navBar(backBtn));
        navBarContainer.setBackground(Configuracion.BACKGROUND);
        add(navBarContainer, BorderLayout.NORTH);
        // --------------------- NAVBAR -------------------------------------------
        
        

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(colorFondo);

        // Label centrado y texto "Recargar"
        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.X_AXIS));
        labelPanel.setBackground(colorFondo);

        JLabel labelListaPartidas = new JLabel("Lista de partidas:");
        Icon recargarIcon = IconFontSwing.buildIcon(FontAwesome.REFRESH, Escalador.escalar(15), new Color(220,220,220));
        JLabel labelRecargar = new JLabel(recargarIcon);
        labelRecargar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        
        labelPanel.add(Box.createRigidArea(new Dimension(300, 0))); // Espacio a la izquierda del label
        labelPanel.add(labelListaPartidas);
        labelPanel.add(Box.createHorizontalGlue());
        labelPanel.add(labelRecargar);
        labelPanel.add(Box.createRigidArea(new Dimension(300, 0))); // Espacio a la derecha del label

        centerPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Espacio vertical superior
        centerPanel.add(labelPanel);
        
        listaPanel.setLayout(new BoxLayout(listaPanel, BoxLayout.Y_AXIS));
        listaPanel.setBackground(colorFondo);
        listaPanel.setBorder(new EmptyBorder(20,300,20,300));


        MScrollPane scrollPane = new MScrollPane(listaPanel);
        scrollPane.setBorder(null);
        
        centerPanel.add(scrollPane);

        // Agregar el centro en la posición CENTER
        add(centerPanel, BorderLayout.CENTER);
        
        executorService = Executors.newSingleThreadExecutor();
        
        labelRecargar.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseEntered(MouseEvent e) {
        		labelRecargar.setBackground(Color.red);
        	}
        	@Override
        	public void mouseExited(MouseEvent e) {
        		labelRecargar.setBackground(Color.white);
        		
        	}
        	@Override
            public void mouseClicked(MouseEvent e) {
                rotate = true; // Start rotation
                rotateLabel();
            }
        	
//        	private void rotateLabel() {
//        	    executorService.execute(() -> {
//        	        try {
//        	            while (rotate) {
//        	                Thread.sleep(50); // Ajusta la duración del sueño según sea necesario
//
//        	                SwingUtilities.invokeLater(() -> {
//        	                    try {
//        	                        AffineTransform transform = AffineTransform.getRotateInstance(Math.toRadians(5),
//        	                                labelRecargar.getWidth() / 2.0, labelRecargar.getHeight() / 2.0);
//        	                        labelRecargar.setIcon(new ImageIcon(((ImageIcon) labelRecargar.getIcon()).getImage().getScaledInstance(labelRecargar.getWidth(), labelRecargar.getHeight(), Image.SCALE_DEFAULT)));
//        	                        ((Graphics2D) labelRecargar.getGraphics()).drawImage(((ImageIcon) labelRecargar.getIcon()).getImage(), transform, null);
//
//        	                        labelRecargar.repaint();
//        	                    } catch (RejectedExecutionException e) {
//        	                     
//        	                        //e.printStackTrace(); 
//        	                    }
//        	                });
//        	            }
//        	        } catch (InterruptedException ex) {
//        	            
//        	            //ex.printStackTrace(); 
//        	        }
//        	    });
//        	}
        	private void rotateIcon() {
        	    ImageIcon icon = (ImageIcon) labelRecargar.getIcon();
        	    Image image = icon.getImage();
        	    int width = labelRecargar.getWidth();
        	    int height = labelRecargar.getHeight();

        	    BufferedImage rotatedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        	    Graphics2D g2d = rotatedImage.createGraphics();

        	    // Crea una imagen transparente con el tamaño de la etiqueta
        	    BufferedImage transparentImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        	    Graphics2D tg2d = transparentImage.createGraphics();
        	    tg2d.setColor(new Color(0, 0, 0, 0));
        	    tg2d.fillRect(0, 0, width, height);

        	    // Gira la imagen transparente
        	    AffineTransform transform = AffineTransform.getRotateInstance(Math.toRadians(5), width / 2.0, height / 2.0);
        	    tg2d.setTransform(transform);
        	    tg2d.drawImage(image, 0, 0, null);

        	    // Dibuja el icono sobre la imagen girada
        	    g2d.drawImage(transparentImage, 0, 0, null);
        	    g2d.dispose();
        	    tg2d.dispose();

        	    ImageIcon rotatedIcon = new ImageIcon(rotatedImage);
        	    labelRecargar.setIcon(rotatedIcon);
        	    labelRecargar.repaint();
        	}

        	private void rotateLabel() {
        	    int delay = 50; // Ajusta según sea necesario
        	    int rotationDuration = 3000; // Duración de la rotación en milisegundos (3 segundos)
        	    int[] rotationTime = {0}; // Utilizar un array para almacenar el valor mutable

        	    rotationTimer = new Timer(delay, e -> {
        	        if (rotate) {
        	            rotateIcon();
        	            rotationTime[0] += delay;

        	            if (rotationTime[0] >= rotationDuration) {
        	                stopRotation();
        	            }
        	        } else {
        	            stopRotation();
        	            resetIcon();
        	        }
        	    });
        	    rotationTimer.start();
        	}

        	private void stopRotation() {
        	    rotationTimer.stop();
        	    rotate = false;
        	}
        	public void resetIcon() {
        	    try {
        	        AffineTransform transform = AffineTransform.getRotateInstance(Math.toRadians(0),
        	                labelRecargar.getWidth() / 2.0, labelRecargar.getHeight() / 2.0);
        	        labelRecargar.setIcon(new ImageIcon(((ImageIcon) labelRecargar.getIcon()).getImage().getScaledInstance(labelRecargar.getWidth(), labelRecargar.getHeight(), Image.SCALE_DEFAULT)));
        	        ((Graphics2D) labelRecargar.getGraphics()).drawImage(((ImageIcon) labelRecargar.getIcon()).getImage(), transform, null);
        	        
        	        labelRecargar.repaint();
        	    } catch (RejectedExecutionException e) {
        	        // Manejar la excepción si es necesario
        	    }
        	}
        	

		});
        
        
    }

    public void reLoadCurrentGames(HashMap<String, DatosPartida> games) {
    	System.out.println(games.keySet());
    	
    	listaPanel.removeAll();
    	
    	listaPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        for (String gameID : games.keySet()) {  
            JPanel partidaPanel = createPartidaPanel(gameID, games.get(gameID).getJugadores().get(0).getUsuario().getUsername());
            listaPanel.add(partidaPanel);
            listaPanel.add(Box.createRigidArea(Escalador.newDimension(0, 10)));
        }
        listaPanel.revalidate();
        listaPanel.repaint();
        
    }
    
    
    private JPanel createPartidaPanel(String codigo, String jugador) {
        JPanel partidaPanel = new JPanel();
        partidaPanel.setLayout(new BoxLayout(partidaPanel, BoxLayout.X_AXIS));
        partidaPanel.setBackground(colorFondo);
        partidaPanel.setBorder(new RoundedBorder(10, colorFondo));

        // Código de partida
        JLabel codigoLabel = new JLabel(codigo);
        codigoLabel.setForeground(Color.WHITE);

        // Botón "Join"
        BButton joinButton = new BButton("Unirme");

        // Nombre de jugador
        JLabel jugadorLabel = new JLabel(jugador);
        jugadorLabel.setForeground(Color.WHITE);

        // Configurar elementos en el panel de la partida
        partidaPanel.add(Box.createRigidArea(Escalador.newDimension(10,0)));
        partidaPanel.add(codigoLabel);
        partidaPanel.add(Box.createRigidArea(Escalador.newDimension(10,0)));
        partidaPanel.add(jugadorLabel);
        partidaPanel.add(Box.createHorizontalGlue());
        partidaPanel.add(joinButton);
        partidaPanel.add(Box.createRigidArea(Escalador.newDimension(10,0)));

        
        
        joinButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			  try {
				Session.getCtsConnection().joinGame(codigo);
				Session.getVentana().getCardLayout().show(Session.getVentana().getPanelPrincipal(), Paneles.CONFONLINE);;
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				
			}
		});
        
        
        return partidaPanel;
    }

    
    
    	
    // Clase para bordes redondeados
    private class RoundedBorder implements Border {
        private int radius;
        private Color color;

        public RoundedBorder(int radius, Color color) {
            this.radius = radius;
            this.color = color;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.setColor(color);
            g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(0, 0, 0, 0);
        }

        @Override
        public boolean isBorderOpaque() {
            return true;
        }
    }
    
//    public void detenerRotacion() {
//        rotate = false;
//        executorService.shutdownNow(); // Detener el hilo de rotación
//    }
    public void detenerRotacion() {
        rotate = false;
        if (rotationTimer != null) {
            rotationTimer.stop();
        }
    }
    
}
