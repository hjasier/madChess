package componentes;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

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

    protected JLabel iconoDesc;
    protected JLabel labelDesc;

    public PanelBoost() {
        this.setLayout(new BorderLayout());
        panelBoosts.setLayout(new BoxLayout(panelBoosts, BoxLayout.Y_AXIS));
        panelDesc.setLayout(new BorderLayout());

        panelDesc.setBackground(Color.GREEN);
        panelBoosts.setBackground(Color.RED);

        panelBoost1.setBackground(Color.ORANGE);
        panelBoost1View.setBackground(Color.BLUE);

        panelBoost2.setBackground(Color.pink);
        panelBoost2View.setBackground(Color.BLUE);

        labelBoost1Nombre = new JLabel("Bomba");
        labelBoost1Icono = new JLabel("IconoB");
        botonBoost1 = new MButton("Usar");
        labelBoost1Cargas = new JLabel("1/1");

        labelBoost2Nombre = new JLabel("Hielo");
        labelBoost2Icono = new JLabel("IconoH");
        botonBoost2 = new MButton("Usar");
        labelBoost2Cargas = new JLabel("2/2");

        iconoDesc = new JLabel("Icono");
        labelDesc = new JLabel("Descripcion");
        labelDesc.setHorizontalAlignment(JLabel.CENTER);

        panelBoost1View.add(labelBoost1Nombre, BorderLayout.WEST);
        panelBoost1View.add(labelBoost1Icono, BorderLayout.CENTER);

        panelBoost1.add(panelBoost1View, BorderLayout.WEST);
        panelBoost1.add(botonBoost1, BorderLayout.CENTER);
        panelBoost1.add(labelBoost1Cargas, BorderLayout.EAST);

        panelBoost2View.add(labelBoost2Nombre, BorderLayout.WEST);
        panelBoost2View.add(labelBoost2Icono, BorderLayout.CENTER);

        panelBoost2.add(panelBoost2View, BorderLayout.WEST);
        panelBoost2.add(botonBoost2, BorderLayout.CENTER);
        panelBoost2.add(labelBoost2Cargas, BorderLayout.EAST);

        panelBoosts.add(panelBoost1);
        panelBoosts.add(panelBoost2);

        panelDesc.add(iconoDesc, BorderLayout.WEST);
        panelDesc.add(labelDesc, BorderLayout.CENTER);

        this.add(panelBoosts, BorderLayout.CENTER);
        this.add(panelDesc, BorderLayout.SOUTH);
    }
}
