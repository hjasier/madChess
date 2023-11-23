package Sockets;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import juego.DatosPartida;
import juego.LogicaPartida;
import juego.Session;
import objetos.Casilla;
import objetos.Jugador;
import objetos.Movimiento;


public class ClientGET implements Runnable {
    private LogicaPartida partida;
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
                	
                	case "updateConfData":
                		Object datosPartida = serverIn.readObject();
                        updatePartidaDatos(datosPartida);
                        Session.setDatosPartida((DatosPartida) datosPartida);
                	case "chatMsg":
                		Jugador author = (Jugador) serverIn.readObject();
                		String msg = (String) serverIn.readObject();
                		
                		Session.getVentana().getPanelJuego().addChatMsg(author.getNombre(),msg);
                		
                	case "nuevoMov":
                		Movimiento movimiento = (Movimiento) serverIn.readObject();
                		//actualizarTablero(); igual al final pasamos el tablero nuevo en vez de la pieza ns
                		moverPiza(movimiento);
                		
                	case "updateCasillas":
                		ArrayList<Casilla> casillas = (ArrayList<Casilla>) serverIn.readObject();
                		Session.getPartida().setCasillas(casillas);
                }
                }
                
                
                
                
                
                
                
                
                
                
                
                
            } catch (IOException | ClassNotFoundException e) {
                // Manejar la excepción apropiadamente
                e.printStackTrace();
                break; //Si se rompe la conexión no se queda infinitamente intentando conectarse
            }
        }
    }

    private void moverPiza(Movimiento movimiento) {
		//Session.getPartida().moverPieza(null,null);
		
	}

	private void updatePartidaDatos(Object datosPartida) {
        Session.getVentana().getPanelConfOnline().setDatosPartida((DatosPartida) datosPartida);
    }


}
