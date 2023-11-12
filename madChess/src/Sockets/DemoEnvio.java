package Sockets;

import java.io.ObjectOutputStream;
import java.net.Socket;

import objetos.Movimiento;

public class DemoEnvio {

    public static void main(String[] args) {
        String serverIP = "localhost"; // Cambia esto a la dirección IP del servidor
        int PUERTO = 4444; // Cambia esto al puerto del servidor

        try {
            // Conectarse al servidor
            Socket socket = new Socket(serverIP, PUERTO);
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());

           //mov nuevo con todo en null para probar si llega
            Movimiento movimiento = new Movimiento();
            
           

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
