package juego;

import java.awt.Toolkit;
import java.awt.Dimension;

public class Escalador {

    public static double ESCALA = 1.0;
    
    public static int escalar(int valor) {
        return (int) (valor * ESCALA);
    }
    
    public static float escalarF(int valor) {
        return (float) (valor * ESCALA);
    }
    
    public static void setScreenSize() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        int resolucion = toolkit.getScreenResolution();
        ESCALA = (double) resolucion / 96.0;
        
    }
    
    public static Dimension newDimension(int x,int y) {
    	return new Dimension(escalar(x),escalar(y));
    }
    
}
