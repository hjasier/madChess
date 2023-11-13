package juego;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import objetos.Movimiento;

public class GestorSockets {
	private String serverIP = "localhost";
	private int PUERTO = 4444;
	private ObjectOutputStream output;
        

	public GestorSockets(){
        try {
            // Conectarse al servidor
            Socket socket = new Socket(serverIP, PUERTO);
            output = new ObjectOutputStream(socket.getOutputStream());

           
        } catch (Exception e) {
            System.out.println("Error al conectar al servidor");;
        }
	}
	public void postMoverPieza(Movimiento movimiento) {
		

        // Enviar el movimiento al servidor
        try {
			output.writeObject(movimiento);
			output.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        

    }
		
	
	
}
