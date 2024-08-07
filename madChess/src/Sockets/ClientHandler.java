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

import juego.DatosPartida;
import juego.modoJuego;
import objetos.Boost;
import objetos.Jugador;
import objetos.Usuario;
import objetos.Movimiento;

public class ClientHandler implements Runnable {
    private Socket socket;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    //private List<ClienteHandler> clientes;
    private HashMap<String, DatosPartida> partidas;
    private Usuario user;
	private DatosPartida curPartida;
	private HashMap<String, ArrayList<ClientHandler>> clientes;

    public ClientHandler(Socket socket, HashMap<String, DatosPartida> partidas, HashMap<String, ArrayList<ClientHandler>> clientes) {
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
    			        this.user = (Usuario) user;
    			        break;
    			        
    			    case "hostNewGame":
    			    	DatosPartida datosPartida = new DatosPartida(modoJuego.ONLINE);
    			    	int playerNum = (int) input.readObject();
    			    	datosPartida.setPlayerNum(playerNum);
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
    			    	
    			    case "setDraggPieza":
    			    	Object casilla = input.readObject();
    			    	reenviar2Datos("setDraggPieza",casilla);
    			    	break;
                	case "mouseDragged":
                		String pos = (String) input.readObject();
    			    	reenviar2Datos("mouseDragged",pos);
    			    	break;
                	case "resetDragg":
                		repost = true;
                		break;
                	
					case "reloadDatosPartida":
						DatosPartida newDatos = (DatosPartida) input.readObject();
						updateDatosPartida(newDatos);
						reenviar2Datos("updateConfData",curPartida);
					    break;
                		
					case "postBoost":
						Object boost = input.readObject();
						reenviar2Datos("postBoost", boost);
						break;
						
					case "postMadSelects":
						ArrayList<Boolean> alters = (ArrayList<Boolean>) input.readObject();
						ArrayList<Boolean> boosts = (ArrayList<Boolean>) input.readObject();
						updatePlayerSelects(alters,boosts);
						reenviar2Datos("updateConfData",curPartida);
						break;
						
						
					case "postInitSelectMadConf":
						reenviarObjetoRaw("initSelectMadConf");
						break;
						
    			    
				}
    				
    				
    			
    			

    			
    			
    				
   
    			
    			
    			if (repost) {
    				reenviarObjetoRaw(objRecibido);	// Envía el mensaje al resto de clientes
    			}
    			
    			
    			
    			
    			
    			
    			
			} catch (IOException | ClassNotFoundException e) {}
			
		}
    }
	
	
	
	
	private void updatePlayerSelects(ArrayList<Boolean> alters, ArrayList<Boolean> boosts) {
		Jugador jugador = curPartida.getJugador(this.user);
		
		jugador.setAlters(alters);
		jugador.setBoosts(boosts);
		
		if (!curPartida.getJugadoresListos().contains(jugador)) {
			curPartida.addJugadorListo(jugador);
		}
	}


	private void updateDatosPartida(DatosPartida newDatos) {
		System.out.println("CAMBIANDO PARTIDA A :"+newDatos.getTipoPartida());
		curPartida.setTipoPartida(newDatos.getTipoPartida());
		//mas cosas
	}


	private void reenviar2Datos(String preDato,Object dato) {
    	if (curPartida==null) {return;}
    	for (ClientHandler cliente: clientes.get(curPartida.getGameId())) {
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
    	for (ClientHandler cliente: clientes.get(curPartida.getGameId())) {
			ObjectOutputStream clientOutput = cliente.getOutput();
			if (clientOutput!=output) {  // Al cliente actual no, solo al resto
				try {
					clientOutput.reset();
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
    	for (ClientHandler cliente: clientes.get(curPartida.getGameId())) {
			ObjectOutputStream clientOutput = cliente.getOutput();
			if (clientOutput!=output) {  // Al cliente actual no, solo al resto
				try {
					clientOutput.reset();
					clientOutput.writeObject(objRecibido);					
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
			
			ArrayList<ClientHandler> clientesPartida = new ArrayList<ClientHandler>();
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
    
    
    public Usuario getUser() {
		return user;
	}
    
   
	public ObjectOutputStream getOutput() {
		return output;
	}
	
}
