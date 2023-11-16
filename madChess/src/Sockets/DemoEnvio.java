package Sockets;

import java.io.ObjectOutputStream;
import java.net.Socket;

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

           //mov nuevo con todo en null para probar si llega
            Movimiento movimiento = new Movimiento();
            
            //Enviar usuario
            output.writeObject(new Jugador("test2","test"));
            output.flush();

            // Enviar el movimiento al servidor
            output.writeObject(movimiento);
            output.flush();



            // Cerrar la conexión
            socket.close();

            System.out.println("Movimiento enviado -->" + movimiento);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
