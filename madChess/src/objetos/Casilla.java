package objetos;

import java.awt.Color;
import java.awt.Image;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;

import javax.swing.*;
import javax.swing.border.LineBorder;


public class Casilla extends JPanel {
    private static final long serialVersionUID = 1L;
    protected Color color = Color.white;
    protected int fila;
	protected char columna;
    protected Pieza pieza;
    
   

	public Casilla(Color color, int fila, int c) {
		this.color = color;
		this.fila = fila;
		this.columna = posicionToAlfabeto(c);
		this.pieza = new Pieza("bb");

		
		//this.setBorder(new LineBorder(Color.GREEN, 2)); // BORDE TEMPORAL PARA DEBUG
   
		
		//codigo para probar printear las piezas:
		
		
		
		
		
		
		/**
		 * GESTIÓN DE EVENTOS DE CURSOR EN CASILLA
		 * */
		
		
		
    
		
        this.addMouseListener(new MouseAdapter() {
        	
		@Override
		public void mouseEntered(MouseEvent e) {
			iluminarCasilla(true);
		}
		@Override
			public void mouseExited(MouseEvent e) {
			iluminarCasilla(false);
		}
		
		@Override
        public void mouseClicked(MouseEvent e) {
            System.out.println("Mouse clickeado en: Fila: " + fila + ", Columna: " + columna);
        }
		
		
		
        });
        
        this.addMouseMotionListener(new MouseMotionAdapter() {
        	@Override
        	public void mouseMoved(MouseEvent e) {
        		//System.out.println("mouse en X:"+e.getX()+" Y:"+e.getY());
        	}
		});
	}

	
	 public int getFila() {
	  		return fila;
	  	}

	  	public char getColumna() {
	  		return columna;
	  	}

	
	public void setPieza(Pieza pieza) {
		this.pieza = pieza;
		System.out.println(pieza);
		//pintar pieza
		
	}
	
	
	private char posicionToAlfabeto(int columna) {
		return (char) ('A' + columna);
		
	}


	// "iluminamos" sumando o restando valores al color RGB actual cuando se hoverea una casilla
	protected void iluminarCasilla(Boolean status) {
		int n = status ? 10 : -10;
		this.color = new Color( color.getRed()+n,color.getGreen()+n,color.getBlue()+n);
		this.paint(getGraphics());
	}
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	



	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        g.setColor(this.color);
        int height = Math.min(getWidth(), getHeight());
        g.fillRect(0, 0, height, height); // xPos, yPos W , H
        
        Image img = this.pieza.getImg().getImage();
        g.drawImage(img, 0, 0, this);
        
    }
	

}
