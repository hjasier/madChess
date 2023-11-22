package Sockets;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import juego.DatosPartida;
import ventanas.ConfPOnline;

public class Servidor {
    private ServerSocket serverSocket;
    private List<ClienteHandler> clientes = new ArrayList<ClienteHandler>();
    private HashMap<String, DatosPartida> partidas = new HashMap<String, DatosPartida>();
    
    private int PUERTO = 8005;
    private ConfPOnline panelConfOnline;
    
    public Servidor() {
        try {
            serverSocket = new ServerSocket(PUERTO);
            System.out.println("Servidor runeado en: " + PUERTO);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Servidor(ConfPOnline panelConfOnline) {
    	this();
		this.panelConfOnline = panelConfOnline;
		this.iniciar();
	}

	public void iniciar() {
    	System.out.println("Esperando jugadores");
        while (true) {
            try {
                Socket clienteSocket = serverSocket.accept();
                System.out.println("Cliente conectado --> " + clienteSocket);

                
                
                
                
                ClienteHandler clienteHandler = new ClienteHandler(clienteSocket,partidas, clientes);
                

                
                clientes.add(clienteHandler);
                Thread clienteThread = new Thread(clienteHandler);
                clienteThread.start();
                
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    

    private void actualizarConfigMenu(ClienteHandler cliente) {
		if (panelConfOnline!=null) {
			panelConfOnline.setUser2(cliente);
		}
		
	}

	public static void main(String[] args) {
        Servidor servidor = new Servidor();
        servidor.iniciar();
    }

}
