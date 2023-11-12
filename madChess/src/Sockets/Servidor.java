package Sockets;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Servidor {
    private ServerSocket serverSocket;
    private List<ClienteHandler> clientes = new ArrayList<ClienteHandler>();
    
    
    private int PUERTO = 4444;
    
    public Servidor() {
        try {
            serverSocket = new ServerSocket(PUERTO);
            System.out.println("Servidor runeado en: " + PUERTO);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void iniciar() {
    	System.out.println("Esperando jugadores");
        while (true) {
            try {
                Socket clienteSocket = serverSocket.accept();
                System.out.println("Cliente conectado --> " + clienteSocket.getInetAddress().getHostAddress());

                ClienteHandler clienteHandler = new ClienteHandler(clienteSocket, clientes);
                clientes.add(clienteHandler);
                Thread clienteThread = new Thread(clienteHandler);
                clienteThread.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Servidor servidor = new Servidor();
        servidor.iniciar();
    }
}
