package Sockets;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;

import andosockets.ConfigCS;
import objetos.Jugador;
import objetos.Movimiento;

public class ClientServer {
	
	public ClientServer() {
        String serverIP = "localhost";
        int PUERTO = 4444; 

        try {
            // Conectarse al servidor
            Socket socket = new Socket(serverIP, PUERTO);
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            
            
            
            
            while (true) {
            	try {
            		Object feedback = input.readObject();
            		System.out.println("Objeto recibido del server --> "+feedback);
    			} catch (SocketTimeoutException e) {} 
            }


            

            
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}
