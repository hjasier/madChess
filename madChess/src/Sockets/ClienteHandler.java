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
    			boolean repost = false;
    			
    			if (objRecibido==null) {  // Si se recibe un objeto nulo es un error
    	    		System.out.println("Objeto null");
    			}
    			
    			else if(objRecibido.equals("fin")) { // Si se recibe fin se acaba el proceso con este cliente
	    			cerrarConexion();
    			}
    			
    			System.out.println("Objeto recibido --> "+objRecibido);
    			
    			
    			
				switch ((String) objRecibido) {
					
					
    			    case "postUser":
    			        Object user = input.readObject();
    			        this.user = (Jugador) user;
    			        break;
    			        
    			    case "hostNewGame":
    			    	DatosPartida datosPartida = new DatosPartida("online");
    			    	datosPartida.setJugador(this.user);
    			    	
    			    	checkDatosPartida(datosPartida);
    			    	
    			    	output.writeObject("updateConfData"); 
    					output.writeObject(curPartida); 
    					break;
    			    	
    			   
    			    case "joinGame":
    			    	Object gameID = input.readObject();
    			    	
    					curPartida = partidas.get(gameID);
    					curPartida.setJugador(this.user);
    					clientes.get(gameID).add(this);
    					System.out.println(curPartida.getJugadores());
    					
    					//Envia la info de la partida al nuevo jugador
    					output.reset();
    					output.writeObject("updateConfData"); 
    					output.writeObject(curPartida); 
    					
    				
    					//Envia la info al resto de jugadores tmb
    					reenviar2Datos("updateConfData",curPartida);
    					break;
    					
    			    case "initGame":
    			    	repost = true;
    			    	break;
    			    	
    			    case "chatMsg":
    			    	Object msg = input.readObject();
    			    	reenviar3Datos("chatMsg",this.user,msg);
    			    	break;
    			    	
    			    	
    			    case "piezaMov":
    			    	Object movimiento = input.readObject();
    			    	reenviar2Datos("nuevoMov",movimiento);
    			    	break;
    			    	
    			    case "updateCasillas":
    			    	Object casillas = input.readObject();
    			    	reenviar2Datos("updateCasillas",casillas);
    			    	break;
    			    	
    			    case "getCurGames":
    			    	output.writeObject("reloadGamesList"); 
    			    	output.writeObject(partidas);
    			    	break;
				}
    				
    				
    			
    			

    			
    			
    				
   
    			
    			
    			if (repost) {
    				reenviarObjetoRaw(objRecibido);	// Envía el mensaje al resto de clientes
    			}
    			
    			
    			
    			
    			
    			
    			
			} catch (IOException | ClassNotFoundException e) {}
			
		}
    }
	
	
	
	private void reenviar2Datos(String preDato,Object dato) {
    	if (curPartida==null) {return;}
    	for (ClienteHandler cliente: clientes.get(curPartida.getGameId())) {
			ObjectOutputStream clientOutput = cliente.getOutput();
			if (clientOutput!=output) {  // Al cliente actual no, solo al resto
				try {
					clientOutput.reset();
					clientOutput.writeObject(preDato);
					clientOutput.writeObject(dato);
					
					
				} catch (Exception e) {
		    		e.printStackTrace();
					System.out.println("Error al enviar de server a cliente");
		    		
				}
			}
		}
	}
	private void reenviar3Datos(String preDato,Object dato,Object dato2) {
    	if (curPartida==null) {return;}
    	for (ClienteHandler cliente: clientes.get(curPartida.getGameId())) {
			ObjectOutputStream clientOutput = cliente.getOutput();
			if (clientOutput!=output) {  // Al cliente actual no, solo al resto
				try {
					clientOutput.writeObject(preDato);
					clientOutput.writeObject(dato);
					clientOutput.writeObject(dato2);
					
				} catch (Exception e) {
		    		e.printStackTrace();
					System.out.println("Error al enviar de server a cliente");
		    		
				}
			}
		}
	}
	
	

    private void reenviarObjetoRaw(Object objRecibido) {
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


	private void checkDatosPartida(DatosPartida datos) {
		if (!partidas.containsKey(datos.getGameId())) {
			partidas.put(datos.getGameId(), datos);
			
			ArrayList<ClienteHandler> clientesPartida = new ArrayList<ClienteHandler>();
			clientesPartida.add(this);
			
			clientes.put(datos.getGameId(), clientesPartida);
		
			curPartida = datos;
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
