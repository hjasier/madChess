package utils;

import java.io.IOException;

import Sockets.ClientCnx;
import Sockets.ClientGET;
import Sockets.ClientPOST;
import juego.DatosPartida;
import juego.LogicaPartida;
import objetos.Jugador;
import objetos.Usuario;
import ventanas.VentanaPrincipal;

public class Session {
    private static Usuario currentUser;
    private static ClientCnx clientCnx;
    private static ClientPOST ctsConnection;
    private static ClientGET stcConnection;
    private static VentanaPrincipal ventana;
    private static DatosPartida datosPartida;
    private static LogicaPartida partida;
	
    
    public static ClientPOST getCtsConnection() {
		return ctsConnection;
	}

	public static void setCurrentUser(Usuario user) {
        currentUser = user;
    }

    public static void setVentana(VentanaPrincipal ventana) {
		Session.ventana = ventana;
	}

	public static VentanaPrincipal getVentana() {
		return ventana;
	}

	public static Usuario getCurrentUser() {
        return currentUser;
    }
    
	public static ClientGET getStcConnection() {
		return stcConnection;
	}
	
    
    public static DatosPartida getDatosPartida() {
		return datosPartida;
	}
    
	public static void setDatosPartida(DatosPartida datosPartida) {
		Session.datosPartida = datosPartida;
	}
	

	public static LogicaPartida getPartida() {
		return partida;
	}

	public static void setPartida(LogicaPartida partida) {
		Session.partida = partida;
	}

	public static void startServerCnx() throws ClassNotFoundException, IOException {
    	if (clientCnx==null) {
    		clientCnx = new ClientCnx();
    		ctsConnection = new ClientPOST(clientCnx.getServerOut());
            Thread clientGetLoop = new Thread(new ClientGET(clientCnx.getServerIn()));
            clientGetLoop.start();

    	}
    }

 
    
    
}
