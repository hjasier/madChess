package utils;

import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.Rectangle;

public class Escalador {

    public static double ESCALA = 1.0;
    
    public static int escalar(int valor) {
        return (int) (valor * ESCALA);
    }
    
    public static float escalarF(float valor) {
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

	public static Rectangle newBounds(int i, int j, int k, int l) {
		// TODO Auto-generated method stub
		return new Rectangle(escalar(i),escalar(j),escalar(k),escalar(l));
	}
    
}
