package juego;

import java.io.IOException;

import Sockets.ClientCnx;
import Sockets.ClientGET;
import Sockets.ClientPOST;
import objetos.Jugador;

public class Session {
    private static Jugador currentUser;
    private static ClientCnx clientCnx;
    private static ClientPOST ctsConnection;
    private static ClientGET stcConnection;
    
    
    
    public static ClientPOST getCtsConnection() {
		return ctsConnection;
	}

	public static void setCurrentUser(Jugador user) {
        currentUser = user;
    }

    public static Jugador getCurrentUser() {
        return currentUser;
    }
    
	public static ClientGET getStcConnection() {
		return stcConnection;
	}


	
    
    public static void startServerCnx() throws ClassNotFoundException, IOException {
    	if (clientCnx==null) {
    		clientCnx = new ClientCnx();
    		System.out.println(clientCnx.toString());
    		ctsConnection = new ClientPOST(clientCnx.getServerOut());
    		stcConnection = new ClientGET(clientCnx.getServerIn());
    	}
    }


    
    
}
