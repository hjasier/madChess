package ventanas;

import java.awt.*;

import javax.swing.*;

public class GUITestSimplificado extends JFrame{


    public GUITestSimplificado() {
        
        this.setSize(600,600);
        
        
        
        Panel fondo = new Panel();
        fondo.setBackground(Color.blue);
        
        fondo.setLayout(null);
        

        Panel divEncima = new Panel();
        divEncima.setBackground(Color.red);
        divEncima.setSize(300,500);
        divEncima.setLayout(null);
        
        
        
        Panel divVerde = new Panel();
        divVerde.setBackground(Color.green);
        
        divVerde.setSize(200,300);
        
        
        divEncima.add(divVerde);
        

        
        
        this.add(fondo);
        
        fondo.add(divEncima);
        
        
        
        
        this.setVisible(true);
        
        

    }       


    
    
    public static void main(String[] args){
        new GUITestSimplificado();
    }
}