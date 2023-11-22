package Sockets;

import java.io.IOException;
import java.io.ObjectInputStream;

import juego.DatosPartida;
import juego.LogicaPartida;
import juego.Session;

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
                
                

                
                if (feedback.equals("updateConfData")) {
                    Object datosPartida = serverIn.readObject();
                    updatePartidaDatos(datosPartida);

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
