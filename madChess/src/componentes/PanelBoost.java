package componentes;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelBoost extends JPanel {
    protected JPanel panelBoosts = new JPanel();
    protected JPanel panelDesc = new JPanel();

    protected JPanel panelBoost1 = new JPanel();
    protected JPanel panelBoost1View = new JPanel();
    protected JLabel labelBoost1Nombre;
    protected JLabel labelBoost1Icono;
    protected MButton botonBoost1;
    protected JLabel labelBoost1Cargas;

    protected JPanel panelBoost2 = new JPanel();
    protected JPanel panelBoost2View = new JPanel();
    protected JLabel labelBoost2Nombre;
    protected JLabel labelBoost2Icono;
    protected MButton botonBoost2;
    protected JLabel labelBoost2Cargas;

    
    protected JPanel panelBoost3 = new JPanel();
    protected JPanel panelBoost3View = new JPanel();
    protected JLabel labelBoost3Nombre;
    protected JLabel labelBoost3Icono;
    protected MButton botonBoost3;
    protected JLabel labelBoost3Cargas;
    
    protected JLabel iconoDesc;
    protected JLabel labelDesc;

    public PanelBoost() {
        this.setLayout(new BorderLayout());
        panelBoosts.setLayout(new BoxLayout(panelBoosts, BoxLayout.Y_AXIS));
        panelDesc.setLayout(new BorderLayout());
        panelBoost1.setLayout(new BorderLayout());
        
        
        
        panelDesc.setBackground(Color.GREEN);
        panelBoosts.setBackground(Color.RED);

        panelBoost1.setBackground(Color.ORANGE);
        panelBoost1View.setBackground(Color.BLUE);

        panelBoost2.setBackground(Color.pink);
        panelBoost2View.setBackground(Color.BLUE);
        
        MScrollPane scrollPane = new MScrollPane(panelBoosts);
        

        labelBoost1Nombre = new JLabel("Bomba");
        labelBoost1Icono = new JLabel("IconoB");
        botonBoost1 = new MButton("Usar");
        labelBoost1Cargas = new JLabel("1/1");

        labelBoost2Nombre = new JLabel("Hielo");
        labelBoost2Icono = new JLabel("IconoH");
        botonBoost2 = new MButton("Usar");
        labelBoost2Cargas = new JLabel("2/2");
        
        labelBoost3Nombre = new JLabel("Jamaicano");
        labelBoost3Icono = new JLabel("IconoJ");
        botonBoost3 = new MButton("Usar");
        labelBoost3Cargas = new JLabel("2/3");

        iconoDesc = new JLabel("Icono");
        labelDesc = new JLabel("Descripcion");
        labelDesc.setHorizontalAlignment(JLabel.CENTER);

        panelBoost1View.add(labelBoost1Nombre, BorderLayout.WEST);
        panelBoost1View.add(labelBoost1Icono, BorderLayout.CENTER);
        
        botonBoost1.setSize(new Dimension(90,60));
        
        panelBoost1.add(panelBoost1View, BorderLayout.WEST);
        panelBoost1.add(botonBoost1, BorderLayout.CENTER);
        panelBoost1.add(labelBoost1Cargas, BorderLayout.EAST);
        panelBoost1.setMaximumSize(new Dimension(getMaximumSize().width,250));
        
        panelBoost2View.add(labelBoost2Nombre, BorderLayout.WEST);
        panelBoost2View.add(labelBoost2Icono, BorderLayout.CENTER);

        panelBoost2.add(panelBoost2View, BorderLayout.WEST);
        panelBoost2.add(botonBoost2, BorderLayout.CENTER);
        panelBoost2.add(labelBoost2Cargas, BorderLayout.EAST);
        panelBoost2.setMaximumSize(new Dimension(getMaximumSize().width,250));
        //panelBoost2.setBorder(BorderFactory.createEmptyBorder(0,0,100,0));
        
        panelBoost3View.add(labelBoost3Nombre, BorderLayout.WEST);
        panelBoost3View.add(labelBoost3Icono, BorderLayout.CENTER);
        
        panelBoost3.add(panelBoost3View, BorderLayout.WEST);
        panelBoost3.add(botonBoost3, BorderLayout.CENTER);
        panelBoost3.add(labelBoost3Cargas, BorderLayout.EAST);
        panelBoost3.setMaximumSize(new Dimension(getMaximumSize().width,250));
        
        panelBoosts.add(Box.createRigidArea(new Dimension(0, 40)));
        panelBoosts.add(panelBoost1);
        panelBoosts.add(Box.createRigidArea(new Dimension(0, 40)));
        panelBoosts.add(panelBoost2);
        panelBoosts.add(Box.createRigidArea(new Dimension(0, 40)));
        panelBoosts.add(panelBoost3);
        panelBoosts.add(Box.createRigidArea(new Dimension(0, 40)));
        //panelBoosts.setPreferredSize(new Dimension(getMaximumSize().width,200));
        
        
        panelDesc.add(iconoDesc, BorderLayout.WEST);
        panelDesc.add(labelDesc, BorderLayout.CENTER);

        this.add(panelBoosts, BorderLayout.CENTER);
        this.add(panelDesc, BorderLayout.SOUTH);
    }
}
