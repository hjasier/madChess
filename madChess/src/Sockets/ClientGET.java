package Sockets;

import java.io.IOException;
import java.io.ObjectInputStream;

import juego.DatosPartida;
import juego.LogicaPartida;
import juego.Session;
import objetos.Jugador;

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
                }
                }
                
                
                
                
                
                
                
                
                
                
                
                
            } catch (IOException | ClassNotFoundException e) {
                // Manejar la excepción apropiadamente
                e.printStackTrace();
            }
        }
    }

    private void updatePartidaDatos(Object datosPartida) {
        Session.getVentana().getPanelConfOnline().setDatosPartida((DatosPartida) datosPartida);
    }


}
