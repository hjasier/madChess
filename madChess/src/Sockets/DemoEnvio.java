package Sockets;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;

import objetos.Jugador;
import objetos.Movimiento;

public class DemoEnvio {

    public static void main(String[] args) {
        String serverIP = "localhost";
        int PUERTO = 4444; 

        try {
            // Conectarse al servidor
            Socket socket = new Socket(serverIP, PUERTO);
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            
           //mov nuevo con todo en null para probar si llega
            Movimiento movimiento = new Movimiento();
            
          

            // Enviar el movimiento al servidor
            output.writeObject(movimiento);
            output.flush();
            System.out.println("Movimiento enviado -->" + movimiento);
            
            while (true) {
            	try {
            		Object feedback = input.readObject();  // Devuelve mensaje de servidor o null cuando se cierra la comunicación
            		System.out.println(feedback);
    			} catch (SocketTimeoutException e) {} 
            }


            

            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
