package ventanas;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import componentes.RButton;
import componentes.navBarNew;
import librerias.FontAwesome;
import librerias.IconFontSwing;
import utils.Configuracion;
import utils.Escalador;

import java.awt.Image;

public class MenuInicioNuevo extends JPanel {
    RButton loginBtn = new RButton("Login");

    public MenuInicioNuevo() {
        setBackground(Configuracion.BACKGROUND);

        //--------------------- NAVBAR-------------------------------------------

        Icon icon = IconFontSwing.buildIcon(FontAwesome.USER_CIRCLE, Escalador.escalarF(15));
        loginBtn.setLocation(Escalador.escalar(787), Escalador.escalar(101));
        loginBtn.setIcon(icon);
        setLayout(null);

        navBarNew navBarNew = new navBarNew(loginBtn);
        navBarNew.setBounds(0, 0, Escalador.escalar(1000), Escalador.escalar(156));

        this.add(navBarNew);
        //--------------------- NAVBAR-------------------------------------------

        double escala = 0.7;

        JPanel panel = new JPanel();
        panel.setBackground(Configuracion.BACKGROUND);
        panel.setBounds(0, Escalador.escalar(159), Escalador.escalar(1000), Escalador.escalar(641));
        add(panel);
        panel.setLayout(null);

        //-----------------SinglePlayer-----------------
        JPanel singlePanel = new JPanel();
        singlePanel.setBounds(Escalador.escalar(113), Escalador.escalar(69), Escalador.escalar(310), Escalador.escalar(260));

        // SinglePLayerBack
        ImageIcon singleplayerft = new ImageIcon(getClass().getResource("../srcmedia/singleplayer.png"));
        Image imagenEscalada = singleplayerft.getImage().getScaledInstance((int) (Escalador.escalar(443) * escala), (int) (Escalador.escalar(362) * escala), Image.SCALE_SMOOTH);
        JLabel singleplayerBack = new JLabel();
        singleplayerBack.setIcon(new ImageIcon(imagenEscalada));
        singleplayerBack.setBounds(0, Escalador.escalar(5), Escalador.escalar(310), Escalador.escalar(253));

        // SinglePlayerLabel
        ImageIcon singleplayerLabel = new ImageIcon(getClass().getResource("../srcmedia/singleplayerLabel.png"));
        Image imgEscaladaSingleLabel = singleplayerLabel.getImage().getScaledInstance((int) (Escalador.escalar(369) * escala), (int) (Escalador.escalar(236) * escala), Image.SCALE_SMOOTH);
        JLabel singlePlayerLabel = new JLabel();
        singlePlayerLabel.setIcon(new ImageIcon(imgEscaladaSingleLabel));
        singlePlayerLabel.setBounds(Escalador.escalar(30), Escalador.escalar(70), Escalador.escalar(252), Escalador.escalar(165));

        singlePanel.setLayout(null);
        singlePanel.add(singlePlayerLabel);
        singlePanel.add(singleplayerBack);
        //-----------------FIN SinglePlayer-----------------

        //-----------------Multiplayer-----------------
        JPanel multiPanel = new JPanel();
        multiPanel.setBounds(Escalador.escalar(536), Escalador.escalar(69), Escalador.escalar(407), Escalador.escalar(263));

        // MultiplayerBack
        ImageIcon multiplayer = new ImageIcon(getClass().getResource("../srcmedia/multiplayer.png"));
        Image imgEscaladaMulti = multiplayer.getImage().getScaledInstance((int) (Escalador.escalar(568) * escala), (int) (Escalador.escalar(362) * escala), Image.SCALE_SMOOTH);
        JLabel multiplayerBack = new JLabel();
        multiplayerBack.setBounds(Escalador.escalar(5), Escalador.escalar(5), Escalador.escalar(397), Escalador.escalar(253));
        multiplayerBack.setIcon(new ImageIcon(imgEscaladaMulti));

        // MultiplayerLabel 1
        ImageIcon multiplayerLabel = new ImageIcon(getClass().getResource("../srcmedia/multiplayerLabel1.png"));
        Image imgEscaladaMultiLabel = multiplayerLabel.getImage().getScaledInstance((int) (Escalador.escalar(162) * escala), (int) (Escalador.escalar(215) * escala), Image.SCALE_SMOOTH);
        JLabel multiplayerLabelBack = new JLabel();
        multiplayerLabelBack.setIcon(new ImageIcon(imgEscaladaMultiLabel));
        multiplayerLabelBack.setBounds(Escalador.escalar(25), Escalador.escalar(84), Escalador.escalar(113), Escalador.escalar(150));

        // MultiplayerLabel 2
        ImageIcon multiplayerLabel2 = new ImageIcon(getClass().getResource("../srcmedia/multiplayerLabel2.png"));
        Image imgEscaladaMultiLabel2 = multiplayerLabel2.getImage().getScaledInstance((int) (Escalador.escalar(162) * escala), (int) (Escalador.escalar(215) * escala), Image.SCALE_SMOOTH);
        JLabel multiplayerLabelBack2 = new JLabel();
        multiplayerLabelBack2.setIcon(new ImageIcon(imgEscaladaMultiLabel2));
        multiplayerLabelBack2.setBounds(Escalador.escalar(147), Escalador.escalar(84), Escalador.escalar(113), Escalador.escalar(150));

        // MultiplayerLabel 3
        ImageIcon multiplayerLabel3 = new ImageIcon(getClass().getResource("../srcmedia/multiplayerLabel3.png"));
        Image imgEscaladaMultiLabel3 = multiplayerLabel3.getImage().getScaledInstance((int) (Escalador.escalar(162) * escala), (int) (Escalador.escalar(215) * escala), Image.SCALE_SMOOTH);
        JLabel multiplayerLabelBack3 = new JLabel();
        multiplayerLabelBack3.setIcon(new ImageIcon(imgEscaladaMultiLabel3));
        multiplayerLabelBack3.setBounds(Escalador.escalar(268), Escalador.escalar(84), Escalador.escalar(113), Escalador.escalar(150));

        multiPanel.setLayout(null);
        multiPanel.add(multiplayerLabelBack);
        multiPanel.add(multiplayerLabelBack2);
        multiPanel.add(multiplayerLabelBack3);
        multiPanel.add(multiplayerBack);
        //-----------------FIN Multiplayer-----------------

        //-----------------LocalPlay----------------------
        JPanel localPanel = new JPanel();
        localPanel.setBounds(Escalador.escalar(536), Escalador.escalar(360), Escalador.escalar(419), Escalador.escalar(219));

        // LocalPlayBack
        ImageIcon localplay = new ImageIcon(getClass().getResource("../srcmedia/localplay.png"));
        Image imgEscaladaLocal = localplay.getImage().getScaledInstance((int) (Escalador.escalar(568) * escala), (int) (Escalador.escalar(293) * escala), Image.SCALE_SMOOTH);
        JLabel localBack = new JLabel();
        localBack.setIcon(new ImageIcon(imgEscaladaLocal));
        localBack.setBounds(Escalador.escalar(10), Escalador.escalar(10), (int) (Escalador.escalar(568) * escala), (int) (Escalador.escalar(293) * escala));

        // LocalPlayLabel 1
        ImageIcon localplayLabel = new ImageIcon(getClass().getResource("../srcmedia/localplayLabel1.png"));
        Image imgEscaladaLocalLabel = localplayLabel.getImage().getScaledInstance((int) (Escalador.escalar(154) * escala), (int) (Escalador.escalar(174) * escala), Image.SCALE_SMOOTH);
        JLabel localplayLabelBack = new JLabel();
        localplayLabelBack.setIcon(new ImageIcon(imgEscaladaLocalLabel));
        localplayLabelBack.setBounds(Escalador.escalar(43), Escalador.escalar(78), (int) (Escalador.escalar(154) * escala), (int) (Escalador.escalar(174) * escala));

        // LocalPlayLabel 2
        ImageIcon localplayLabel2 = new ImageIcon(getClass().getResource("../srcmedia/localplayLabel2.png"));
        Image imgEscaladaLocalLabel2 = localplayLabel2.getImage().getScaledInstance((int) (Escalador.escalar(154) * escala), (int) (Escalador.escalar(174) * escala), Image.SCALE_SMOOTH);
        JLabel localplayLabelBack2 = new JLabel();
        localplayLabelBack2.setIcon(new ImageIcon(imgEscaladaLocalLabel2));
        localplayLabelBack2.setBounds(Escalador.escalar(160), Escalador.escalar(78), (int) (Escalador.escalar(154) * escala), (int) (Escalador.escalar(174) * escala));

        // LocalPlayLabel 3
        ImageIcon localplayLabel3 = new ImageIcon(getClass().getResource("../srcmedia/localplayLabel3.png"));
        Image imgEscaladaLocalLabel3 = localplayLabel3.getImage().getScaledInstance((int) (Escalador.escalar(154) * escala), (int) (Escalador.escalar(174) * escala), Image.SCALE_SMOOTH);
        JLabel localplayLabelBack3 = new JLabel();
        localplayLabelBack3.setIcon(new ImageIcon(imgEscaladaLocalLabel3));
        localplayLabelBack3.setBounds(Escalador.escalar(277), Escalador.escalar(78), (int) (Escalador.escalar(154) * escala), (int) (Escalador.escalar(174) * escala));

        localPanel.setLayout(null);
        localPanel.add(localplayLabelBack);
        localPanel.add(localplayLabelBack2);
        localPanel.add(localplayLabelBack3);
        localPanel.add(localBack);

        //-----------------FIN LocalPlay-----------------

        panel.add(singlePanel);
        panel.add(localPanel);
        panel.add(multiPanel);
    }
}
