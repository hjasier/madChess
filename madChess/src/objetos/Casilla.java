package objetos;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.io.Serializable;

import javax.swing.*;
import javax.swing.border.LineBorder;

import utils.Escalador;
import utils.Session;
import utils.Themes;
import utils.Themes.tableroThemes;


public class Casilla extends JPanel implements Serializable {
    private static final long serialVersionUID = 1L;
    protected Color initColor;
    protected Color color;
    protected Pieza pieza;
    protected int fila;
	protected char columna;
    protected int imgSize;
    
    protected boolean dragging = false;
    protected boolean iluminada = false;
    protected boolean isDisponible = false;
    protected boolean isHielo = false;
    protected boolean isMina = false;
    protected boolean isBlack = false;
    protected boolean disabled = false;

	public Casilla(Color color, int fila, int col) {
		this.color = color;
		this.initColor = color;
		this.fila = fila;
		this.columna = posicionToAlfabeto(col);
		this.isBlack = (fila + col) % 2 != 0;
	}

	
	 public int getFila() {
	  		return fila;
	  	}

	  	public char getColumna() {
	  		return columna;
	  	}
	  	
	  public String getPos() {
		  return (this.columna+""+this.getFila());
	  }

	
	public void setPieza(Pieza pieza) {
		this.pieza = pieza;
//		if(pieza.getIsBomberman()) {
//			this.color = Color.red;
//		}
		
		this.repaint();
		
		
	}
	
	
	private char posicionToAlfabeto(int columna) {
		return (char) ('A' + columna);
		
	}
	
	public void setDebugClr(Color color) {
		this.color = color;
		repaint();
		
	}


	// "iluminamos" sumando o restando valores al color RGB actual cuando se hoverea una casilla
	protected void iluminarCasilla(Boolean status) {
		if (status&&!iluminada || !status&&iluminada) {
			iluminada = status;
			int n = (status) ? 10 : -10;
			try {
				this.color = new Color( color.getRed()+n,color.getGreen()+n,color.getBlue()+n);
				this.paint(getGraphics());
			} catch (Exception e) {
				this.color = new Color( color.getRed()-n,color.getGreen()-n,color.getBlue()-n);
			}

		}
		
	}
	
	
	

	
	
	
	public void setHover(boolean status) {
		if (this.pieza!=null) {
			this.getParent().getParent().setCursor(new Cursor(Cursor.HAND_CURSOR));
		}else {			
			this.getParent().getParent().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			iluminarCasilla(status);
		}
		
	}
	
	
	public void setDragging(Boolean status) {
		this.dragging = status;
		repaint();
	}
	

	
	
	
	
	
	



	public Pieza getPieza() {
		return pieza;
	}


	@Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g; // para que se vea nice
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        super.paintComponent(g);
        
        
        g.setColor(this.color);
        int height = Math.min(getWidth(), getHeight());
        g.fillRect(0, 0, height, height); // xPos, yPos W , H
        

        imgSize = (int)(height / 2 * 1.70);
        int x = (getWidth() - imgSize) / 2;
        int y = (getHeight() - imgSize) / 2;
        
        //Img esquina sup izquieda
        if (isHielo ||(pieza!=null&& pieza.isBomberman)) {
            String src;
			if (!isHielo) {
                src = "/srcmedia/bomba.png";
            } else {
            	src = "/srcmedia/hielo.png";
            }

            Image nuevaImagen = new ImageIcon(getClass().getResource(src)).getImage();
            int nuevaImagenSize = 30;
            g.drawImage(nuevaImagen, 5, 5, nuevaImagenSize, nuevaImagenSize, null);
        }
        
        if (this.isDisponible) {
        	g.setColor(new Color(255, 201, 0 ));
        	if (this.pieza != null) {
            	final int radius1 = 80;//FIXME: Los circulos tendrían que tener relación al tamaño de la casilla
            	final int radius2 = 58;
            	g.fillOval(height/2 - radius1/2, height/2 - radius1/2,radius1,radius1);
            	g.setColor(this.color);
            	g.fillOval(height/2 - radius2/2, height/2 - radius2/2,radius2,radius2);
        	}
        	else {
            	final int radius = Escalador.escalar(25); //FIXME: En circulo tendría que tener relación al tamaño de la casilla
            	g.fillOval(height/2 - radius/2, height/2 - radius/2,radius,radius);	
        	}

        }
        
        
        if (this.pieza != null && !dragging) {	
        	Image img = this.pieza.getImg().getImage();
        	g.drawImage(img, x, y, imgSize, imgSize, null);
        }
        
        
        
    }


	public void setDisponible(boolean status) {
		isDisponible = status;
		repaint();
		
	}


	public void setPiezaOculto(Pieza newPieza) {
		this.pieza= newPieza;
	}


	public Boolean getIsHielo() {
		return isHielo;
	}


	public void setIsHielo(Boolean isHielo) {
		this.isHielo = isHielo;
		if(isHielo) {
			tableroThemes userTheme = Session.getCurrentUser().getPreferedTheme();
			Color[] curThemeColors = Themes.getTableroTheme(userTheme);
			if (this.isBlack) {
				this.color = curThemeColors[2];
			} else {
				this.color = curThemeColors[3];
			}
        	
        }else {
        	this.color = initColor;
        }
		repaint();
	}

	public Boolean getIsMina() {
		return isMina;
	}
	
	
	public void aninarMina() {
	        Thread colorChangeThread = new Thread(() -> {
	            try {
	                SwingUtilities.invokeLater(() -> {
	                    this.color = new Color(Math.min(color.getRed() + 100, 255), Math.min(color.getGreen() - 50, 255), color.getBlue());
	                    repaint();
	                });
	                
	                Thread.sleep(700);
	                
	                SwingUtilities.invokeLater(() -> {
	                    this.color = initColor;
	                    repaint();
	                });
	                
	                Thread.sleep(700);
	                
	                SwingUtilities.invokeLater(() -> {
	                	this.color = new Color(Math.min(color.getRed() + 100, 255), Math.min(color.getGreen() - 50, 255), color.getBlue());
	                    repaint();
	                });
	                
	                Thread.sleep(700);
	                
	                SwingUtilities.invokeLater(() -> {
	                    this.color = initColor;
	                    repaint();
	                });
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        });
	        colorChangeThread.start();

	}
	

	public void setDisabled(boolean b) {
		this.disabled = b;
		if (b) {
			this.color = new Color( color.getRed()/2,color.getGreen()/2,color.getBlue()/2);
		}
		else {
			this.color = initColor;
		}
		
		repaint();
	}


	public void reloadColor() {
		tableroThemes userTheme = Session.getCurrentUser().getPreferedTheme();
		Color[] curThemeColors = Themes.getTableroTheme(userTheme);
		if (this.isBlack) {
			this.color = curThemeColors[0];
		} else {
			this.color = curThemeColors[1];
		}
		this.initColor = color;
		setDisabled(disabled);
		setIsHielo(isHielo);
	}



	
	


	

	
	

}
