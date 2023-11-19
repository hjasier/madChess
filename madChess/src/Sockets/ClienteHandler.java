package Sockets;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import andosockets.ConfigCS;
import juego.DatosPartida;
import objetos.Jugador;
import objetos.Movimiento;

public class ClienteHandler implements Runnable {
    private Socket socket;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    //private List<ClienteHandler> clientes;
    private HashMap<String, DatosPartida> partidas;
    private Jugador user;
	private DatosPartida curPartida;

    public ClienteHandler(Socket socket, HashMap<String, DatosPartida> partidas, List<ClienteHandler> clientes) {
        this.socket = socket;
        //this.clientes = clientes;
        this.partidas = partidas;
        
        try {
            output = new ObjectOutputStream(socket.getOutputStream());
            input = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	

	@Override
    public void run() {
		boolean finComunicacion = false;
		while(!finComunicacion) {  // Bucle de comunicación de tiempo real con el cliente
			try {
    			Object objRecibido = input.readObject();  // Espera a recibir petición de cliente (1) - se acaba con timeout
    			boolean repost = true;
    			
    			if (objRecibido==null) {  // Si se recibe un objeto nulo es un error
    	    		System.out.println("OObjeto null");
    			}
    			
    			else if(objRecibido.equals("fin")) { // Si se recibe fin se acaba el proceso con este cliente
	    			cerrarConexion();
    			}
    			
    			System.out.println("Objeto recibido --> "+objRecibido);
    			
    			
    			
    			
    			if (objRecibido instanceof DatosPartida) {
    				checkDatosPartida(objRecibido);
    				output.writeObject("confDatosPartida"); 
    			}
    			else {
    				if (objRecibido.equals("postUser")) {
    					Object user = input.readObject();
    					this.user = (Jugador) user; 
    					System.out.println("Seteando user --> "+user);
    					repost = false;
    				}
    				
    				else if (objRecibido.equals("joinGame")) {
    					Object gameIDFuturo = input.readObject();
    					// de momento siempre va a joinear al jugador a la primera partida en partidas pk aún no hay ventana para elegir a cual 
    					
    				    String gameID = partidas.entrySet().iterator().next().getKey();
    				    
    					System.out.println("Seteando partida con gameID: "+gameID+" a visitante");
    					curPartida = partidas.get(gameID);
    					curPartida.setJugador(user);
    					output.writeObject("conectadoAPartida"); 
    					output.writeObject(curPartida); 
    					repost = false;
    				}
    			}
    			
    			
    			if (repost) {
    				reenviarObjeto(objRecibido);	// Envía el mensaje al resto de clientes
    			}
    			
    			
    			
    			
    			
    			
    			
			} catch (IOException | ClassNotFoundException e) {}
			
		}
    }
	
	
	
	
	
	
	
	
	
	

    private void reenviarObjeto(Object objRecibido) {
    	if (curPartida==null) {return;}
    	ArrayList<ClienteHandler> clientes = curPartida.getClientes();
    	
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
	}


	private void checkDatosPartida(Object objRecibido) {
    	
		DatosPartida datos = (DatosPartida) objRecibido;
		if (!partidas.containsKey(datos.getGameId())) {
			System.out.println("Registrando partida en el servidor");
			partidas.put(datos.getGameId(), datos);
		}
		else {
			curPartida = partidas.get(datos.getGameId());
			curPartida.setTipoPartida(datos.getTipoPartida());//así con todas las opciones futuras , tiempo de partida,boosts...
		}
		System.out.println(partidas);
		
		
	}


	public void cerrarConexion() {
    	System.out.println("Conexión con el cliente terminada");
        try {
            socket.close();
            //clientes.remove(this);
            partidas.remove(curPartida);
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
	
}
