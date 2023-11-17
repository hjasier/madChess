package Sockets;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.List;

import andosockets.ConfigCS;
import objetos.Jugador;
import objetos.Movimiento;

public class ClienteHandler implements Runnable {
    private Socket socket;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private List<ClienteHandler> clientes;
    
    private Jugador user;

    public ClienteHandler(Socket socket, List<ClienteHandler> clientes) {
        this.socket = socket;
        this.clientes = clientes;

        try {
            output = new ObjectOutputStream(socket.getOutputStream());
            input = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Jugador getUser() {
		return user;
	}
    
    

	public ObjectOutputStream getOutput() {
		return output;
	}
	
	

	@Override
    public void run() {
		boolean finComunicacion = false;
		while(!finComunicacion) {  // Bucle de comunicación de tiempo real con el cliente
			try {
    			Object objRecibido = input.readObject();  // Espera a recibir petición de cliente (1) - se acaba con timeout
    			if (objRecibido==null) {  // Si se recibe un objeto nulo es un error
    	    		System.out.println("OObjeto null");
    			}
    			
    			else if(objRecibido.equals("fin")) { // Si se recibe fin se acaba el proceso con este cliente
	    			cerrarConexion();
    			}
    			
    			System.out.println("Objeto recibido --> "+objRecibido);
    			
    			output.writeObject("confRecibido"); // Confirma al cliente
    			
    			
    			
    			// Envía el mensaje al resto de clientes
    			for (ClienteHandler cliente: clientes) {
    				ObjectOutputStream clientOutput = cliente.getOutput();
    				if (clientOutput!=output) {  // Al cliente actual no, solo al resto
    					try {
    						System.out.println("Re-enviando objeto al cliente");
    						clientOutput.writeObject(objRecibido);
    						clientOutput.writeObject(user);
	    					
	    					
    					} catch (Exception e) {
    			    		e.printStackTrace();
    						System.out.println("Error al enviar de server a cliente");
    			    		
    					}
    				}
    			}
    			
    			
			} catch (IOException | ClassNotFoundException e) {}
			
		}
    }

    public void cerrarConexion() {
    	System.out.println("Conexión con el cliente terminada");
        try {
            socket.close();
            clientes.remove(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
