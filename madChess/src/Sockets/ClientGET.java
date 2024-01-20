package Sockets;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import juego.Boosts;
import juego.DatosPartida;
import juego.LogicaPartida;
import objetos.Boost;
import objetos.Casilla;
import objetos.Jugador;
import objetos.Movimiento;
import objetos.Tablero;
import objetos.Usuario;
import utils.Configuracion;
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

                		Session.setDatosPartida(datosPartida);  
                		Session.getVentana().getPanelConfOnline().setDatosPartida(datosPartida);
                        
                        break;
                	case "chatMsg":
                		Usuario author = (Usuario) serverIn.readObject();
                		String msg = (String) serverIn.readObject();
                		Session.getVentana().getPanelJuego().addChatMsg(author.getUsername(),msg);
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
                		if (Configuracion.MLTPLY_PIEZA_DRAG) {
                			Casilla casilla = (Casilla) serverIn.readObject();
                    		Session.getPartida().setDragPieza(casilla);
                		}
                		break;
                	case "resetDragg":
                		if (Configuracion.MLTPLY_PIEZA_DRAG) {
                			Session.getPartida().getTablero().resetDraggPieza();
                		}
                		break;
               
						
                	case "postBoost":
                		Boost boost = (Boost) serverIn.readObject();
                		Boosts.getStcBoost(boost);
                		break;
                	case "initSelectMadConf":
						Session.getVentana().initSelectMadConf();
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
