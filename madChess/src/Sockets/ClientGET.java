package Sockets;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import juego.DatosPartida;
import juego.LogicaPartida;
import objetos.Casilla;
import objetos.Jugador;
import objetos.Movimiento;
import objetos.Tablero;
import utils.Session;


public class ClientGET implements Runnable {
    private ObjectInputStream serverIn;

    public ClientGET(ObjectInputStream serverIn) {
        this.serverIn = serverIn;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Object feedback = serverIn.readObject();
                System.out.println("Recibido desde servidor --> " + feedback);
                
                if (!(feedback instanceof String)) {
                	
                }
                else {
                switch ((String) feedback) {
                	
                
                	case "initGame":
                		Session.getVentana().initGame();
                		break;
                	case "updateConfData":	
                		DatosPartida datosPartida = (DatosPartida) serverIn.readObject();
                		
                		System.out.println(datosPartida.getJugadores());
                		
                		Session.setDatosPartida(datosPartida);  
                		Session.getVentana().getPanelConfOnline().setDatosPartida(datosPartida);
                        
                        break;
                	case "chatMsg":
                		Jugador author = (Jugador) serverIn.readObject();
                		String msg = (String) serverIn.readObject();
                		Session.getVentana().getPanelJuego().addChatMsg(author.getUsuario().getUsername(),msg);
                		break;
                	case "nuevoMov":
                		Movimiento movimiento = (Movimiento) serverIn.readObject();
                		Session.getPartida().moverPiezaOnline(movimiento.getCasillaSalida(),movimiento.getCasillaLlegada());
                		break;
                		
                	case "updateCasillas":
                		ArrayList<Casilla> casillas = (ArrayList<Casilla>) serverIn.readObject();
                		Session.getPartida().setCasillas(casillas);
                		break;
                		
                	case "reloadGamesList":
                		HashMap<String, DatosPartida> games = (HashMap<String, DatosPartida>) serverIn.readObject();
                		Session.getVentana().getPanelListaPartidas().reLoadCurrentGames(games);
                		break;
                		
                	case "mouseDragged":
                		String pos = (String) serverIn.readObject();
                		Session.getPartida().getTablero().setDraggPiezaPos(pos);
                		break;

                	case "setDraggPieza":
                		Casilla casilla = (Casilla) serverIn.readObject();
                		Session.getPartida().setDragPieza(casilla);
                		break;
                	case "resetDragg":
                		Session.getPartida().getTablero().resetDraggPieza();
                		break;
                	
                	case "reloadDatosPartida":
						DatosPartida newDatos = (DatosPartida) serverIn.readObject();
						System.out.println("Recibido reload data");
						break;
                }
            }
                
                
                
                
                
                
                
                
                
                
                
                
            } catch (IOException | ClassNotFoundException e) {
                // Manejar la excepción apropiadamente
                e.printStackTrace();
                break; //Si se rompe la conexión no se queda infinitamente intentando conectarse
            }
        }
    }

 


}
