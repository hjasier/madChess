package ventanas;

import javax.swing.JFrame;

public class VentanaPrincipal extends JFrame{


	
	/* LA IDEA ES QUE SOLO EXISTA UN JFRAME Y EL RESTO:
	 *  (lOGIN, JUEGO, PARTIDA ..ETC)
	 *  
	 *  SEAN PANELES QUE INSERTAMOS EN ESA VENTANA PRINCIPAL, ASÍ , NO HAY QUE ABRIR Y CERRAR CENTANAS AL LOGEARSE POR EJEMPLO O AL CLICKAR 
	 *  EN EMPEZAR UNA PARTIDA, SI NO QUE CAMBIA EL CONTENIDO DE LA VENTANNA, Y SI HACE FALTA EL TAMAÑO
	 *  
	 *  
	 * 
	 */
	
	public VentanaPrincipal() {
		
		VentanaJuegoTesteos ventanaJuego = new VentanaJuegoTesteos();
		this.setLocationRelativeTo(null);
		this.setSize(1000,800);
	
		
		this.add(ventanaJuego.getPanel());
		
		
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
	}
		
}
