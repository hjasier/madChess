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
	private HashMap<String, ArrayList<ClienteHandler>> clientes;

    public ClienteHandler(Socket socket, HashMap<String, DatosPartida> partidas, HashMap<String, ArrayList<ClienteHandler>> clientes) {
        this.socket = socket;
        this.partidas = partidas;
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
    				checkDatosPartida(objRecibido); //setea la partida en el servidor
    			
    			}
    			
    			
    			else if (objRecibido.equals("postUser")) { //logea al user en el handler
    					Object user = input.readObject();
    					this.user = (Jugador) user; 
    					repost = false;
    			}
    			
    			
    				
    			else if (objRecibido.equals("joinGame")) { //cuando alguien se conecta a la partida
    					Object gameIDFuturo = input.readObject();
    					// de momento siempre va a joinear al jugador a la primera partida en partidas pk aún no hay ventana para elegir a cual 
    					
    				    String gameID = partidas.entrySet().iterator().next().getKey();
    				    
    					curPartida = partidas.get(gameID);
    					curPartida.setJugador(user);
    					clientes.get(curPartida.getGameId()).add(this);
    					
    					
    					//Envia la info a actualizar
    					output.writeObject("updateConfData"); 
    					output.writeObject(curPartida); 
    					
    					System.out.println("ENVIANDO USER JOIN A ");
    					System.out.println(clientes.get(curPartida.getGameId()));
    					//Enviamos la info al resto de jugadores tmb
    					updateConfData();
    					repost = false;
    			}
    			
    			
    			
    			if (repost) {
    				reenviarObjeto(objRecibido);	// Envía el mensaje al resto de clientes
    			}
    			
    			
    			
    			
    			
    			
    			
			} catch (IOException | ClassNotFoundException e) {}
			
		}
    }
	
	
	
	
	
	
	private void updateConfData() {
    	if (curPartida==null) {return;}
    	for (ClienteHandler cliente: clientes.get(curPartida.getGameId())) {
			ObjectOutputStream clientOutput = cliente.getOutput();
			if (clientOutput!=output) {  // Al cliente actual no, solo al resto
				try {
					System.out.println("Re-enviando objeto a :"+cliente.getUser().getNombre());
					clientOutput.writeObject("updateConfData");
					clientOutput.writeObject(curPartida);
					
					
				} catch (Exception e) {
		    		e.printStackTrace();
					System.out.println("Error al enviar de server a cliente");
		    		
				}
			}
		}
	}
	
	

    private void reenviarObjeto(Object objRecibido) {
    	if (curPartida==null) {return;}
    	for (ClienteHandler cliente: clientes.get(curPartida.getGameId())) {
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
			partidas.put(datos.getGameId(), datos);
			
			ArrayList<ClienteHandler> clientesPartida = new ArrayList<ClienteHandler>();
			clientesPartida.add(this);
			
			clientes.put(datos.getGameId(), clientesPartida);
		
			curPartida = datos;
			
			System.out.println("Registrando clientes en mapa");
			System.out.println(clientes);
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
