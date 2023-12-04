package componentes;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import juego.Boosts;
import utils.Audio;
import utils.Configuracion;
import utils.Escalador;
import utils.Session;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelBoost extends JPanel {
    Color colorFondo = Configuracion.BACKGROUND;

    public PanelBoost() {
        setLayout(new BorderLayout());
        setBackground(colorFondo);
        
        // Panel de elementos
        JPanel panelElementos = new JPanel();
        panelElementos.setLayout(new BoxLayout(panelElementos, BoxLayout.Y_AXIS));
        panelElementos.setBackground(colorFondo);

        // Agrega elementos al panel de elementos (puedes agregar más según tus necesidades)
        for (int i = 1; i <= 10; i++) {
            JPanel elementoPanel = crearElementoPanel("Boost" + i, "icono ", i);
            panelElementos.add(elementoPanel);
        }

        MScrollPane scrollPane = new MScrollPane(panelElementos);
        scrollPane.setBorder(null);
        add(scrollPane, BorderLayout.CENTER);

        // Panel Descripción
        JPanel panelDescripcion = new JPanel(new BorderLayout());

        
        JLabel iconoLabel = new JLabel("Icono");
        iconoLabel.setHorizontalAlignment(JLabel.LEFT);
        panelDescripcion.add(iconoLabel, BorderLayout.WEST);

        JLabel descripcionLabel = new JLabel("Descripción");
        descripcionLabel.setHorizontalAlignment(JLabel.CENTER);
        
        panelDescripcion.add(descripcionLabel, BorderLayout.CENTER);

        panelDescripcion.setBackground(colorFondo);
        add(panelDescripcion, BorderLayout.SOUTH);
    }

    private JPanel crearElementoPanel(String nombreBoost, String nombreIcono, int boostIndex) {
        JPanel elementoPanel = new JPanel();
        elementoPanel.setBackground(colorFondo);
        elementoPanel.setLayout(new BorderLayout());

        // Panel izquierdo con nombre del Boost y nombre del Icono
        JPanel panelIzquierdo = new JPanel();
        panelIzquierdo.setLayout(new GridLayout(1, 2));
        panelIzquierdo.setBackground(colorFondo);
        JLabel boostLabel = new JLabel(nombreBoost);
        JLabel iconoLabel = new JLabel(nombreIcono);
        panelIzquierdo.add(boostLabel);
        panelIzquierdo.add(iconoLabel);

        elementoPanel.add(panelIzquierdo, BorderLayout.WEST);

        // Panel para el botón con FlowLayout
        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        BButton usarButton = new BButton("Usar");
        panelBoton.setBackground(colorFondo);
        usarButton.setPreferredSize(new Dimension(Escalador.escalar(80), Escalador.escalar(30)));
        panelBoton.add(usarButton);
        elementoPanel.add(panelBoton, BorderLayout.CENTER);

        // Label a la derecha
        JLabel defaultLabel = new JLabel("1/3");
        elementoPanel.add(defaultLabel, BorderLayout.EAST);

        // Configurar ActionListener para el botón
        usarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica cuando se hace clic en el botón "Usar"
                onUsarButtonClick(boostIndex); // Ejecuta la función específica del botón

            }
        });

        return elementoPanel;
        
    }
    
    private void onUsarButtonClick(int boostIndex) {
        if (boostIndex == 1) {
            Boosts.boostHielo();
        } else if(boostIndex == 2){
           Boosts.boostMalPresagio();
        }else if(boostIndex == 3){
            Boosts.boostBomba();
        }else if(boostIndex == 4){
            Boosts.boostControl();
        }
        else if(boostIndex == 5){
        	//DEBUG 
        	System.out.println("DEBUGEANDO ANIMACIÓN");
            Session.getPartida().getTablero().initAnimacionExplosion(Session.getPartida().getCasilla(0,(char)'A'));
            Audio.play("explosion.wav");
        }
    }
}