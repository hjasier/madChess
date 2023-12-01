package componentes;

import javax.swing.*;

import utils.Configuracion;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InfoMsg extends JDialog {

    public InfoMsg(String message) {
        setUndecorated(true);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setBackground(Configuracion.BACKGROUND);
        panel.setBorder(BorderFactory.createLineBorder(new Color(36,36,36), 5));
        panel.setLayout(new BorderLayout());

        JLabel label = new JLabel("<html><font color='white'>" + message + "</font></html>");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(label, BorderLayout.CENTER);

        BButton closeButton = new BButton("Cerrar");
        closeButton.setMargin(null);
        
        closeButton.setBackground(Color.WHITE);
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        // Utiliza un nuevo panel para centrar el botón
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(Configuracion.BACKGROUND);
        buttonPanel.add(closeButton);

        // Añade el panel del botón al panel principal
        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel, BorderLayout.CENTER);

        // Ajusta el tamaño de la ventana
        int width = 300;  // Cambia el valor según sea necesario
        int height = 150; // Cambia el valor según sea necesario
        setSize(width, height);

        setLocationRelativeTo(null);
    }

    public static void alert(String message) {
        InfoMsg dialog = new InfoMsg(message);
        dialog.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            alert("Texto de ejemplo..");
        });
    }
}
