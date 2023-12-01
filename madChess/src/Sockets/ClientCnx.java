package Sockets;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import utils.Configuracion;

public class ClientCnx {
	protected Socket socket;
	protected ObjectOutputStream serverOut;
	protected ObjectInputStream serverIn;

	public ClientCnx() {
        try {
            // Conectarse al servidor
            socket = new Socket(Configuracion.HOST, Configuracion.PUERTO);
            serverOut = new ObjectOutputStream(socket.getOutputStream());
            serverIn = new ObjectInputStream(socket.getInputStream());
            
            System.out.println("Conectado al servidor");
                  
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al conectarse al servidor");
        }
    }
	
	
    @Override
	public String toString() {
		return "ClientCnx [serverOut=" + serverOut + ", serverIn=" + serverIn + "]";
	}


	public ObjectOutputStream getServerOut() {
		return serverOut;
	}
	public ObjectInputStream getServerIn() {
		return serverIn;
	}
}
