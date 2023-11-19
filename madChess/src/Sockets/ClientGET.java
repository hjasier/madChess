package Sockets;

import java.io.IOException;
import java.io.ObjectInputStream;
import juego.LogicaPartida;


public class ClientGET implements Runnable {
	private LogicaPartida partida;
	private ObjectInputStream serverIn;
	
	
	public ClientGET(ObjectInputStream serverIn) throws ClassNotFoundException, IOException {
		this.serverIn = serverIn;
	}


	@Override
	public void run() {
		while (true) {
        	try {
        		Object feedback = serverIn.readObject(); 
        		System.out.println("Recibido desde servidor --> "+feedback);
        		
        		if (feedback.equals("conectadoAPartida")) {
        			Object datosPartida = serverIn.readObject(); 
        			updatePartidaDatos(datosPartida);
        			
        		}
        		
        		
        		
        		
        		
			} catch (IOException | ClassNotFoundException e) {} 
        }
		
	}


	private void updatePartidaDatos(Object datosPartida) {
		// de alguna manera hay que llegar a la ventana de config y cambiar los valores de la ventana
	}



	
	

}
