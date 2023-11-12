package Sockets;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import objetos.Movimiento;

public class ClienteHandler implements Runnable {
    private Socket socket;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private List<ClienteHandler> clientes;

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

    @Override
    public void run() {
        try {
            while (true) {
               
            	if (input.readObject() instanceof Movimiento) {
            		System.out.println("Objeto recibido de tipo MOV");
            		Movimiento movimiento = (Movimiento) input.readObject();
            		System.out.println(movimiento.toString());
            	}
            	

           
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
    }

    public void cerrarConexion() {
        try {
            socket.close();
            clientes.remove(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
