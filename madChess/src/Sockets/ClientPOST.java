package Sockets;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import juego.DatosPartida;
import juego.Session;
import objetos.Casilla;
import objetos.Movimiento;

public class ClientPOST {
	
	private ObjectOutputStream serverOut;
	private long lastMouseDraggedTime;
	private boolean piezaSent = false;
	
	public ClientPOST(ObjectOutputStream serverOut) throws IOException {
		this.serverOut = serverOut;
		postUser();
		
	}
	
	
	public void createGame() throws IOException {
		serverOut.writeObject("hostNewGame");
	}
	
	public void postUser() throws IOException {
		serverOut.writeObject("postUser");
		serverOut.writeObject(Session.getCurrentUser());
	}
	
	
	public void joinGame(String gameId) throws IOException {
		serverOut.writeObject("joinGame");
		serverOut.writeObject(gameId);
	}
	
	public void setGameSettings(DatosPartida curDatosPartida) throws IOException {
		serverOut.writeObject(curDatosPartida);
	}

	public void postChatMsg(String msgText) throws IOException {
		//Mensaje msg =  new Mensaje(Session.getCurrentUser().geti,);
		serverOut.writeObject("chatMsg");
		serverOut.writeObject(msgText);
	}

	public void getListaPartidas() throws IOException {
		serverOut.writeObject("getCurGames");
	}
	

	public void postPiezaMov(Movimiento movimiento) throws IOException {
		serverOut.writeObject("piezaMov");
		serverOut.writeObject(movimiento);
		
	}
	
    public void postMouseDragged(MouseEvent e,Casilla casilla) throws IOException {
        long currentTime = System.currentTimeMillis();
        if (!piezaSent) {
        	serverOut.writeObject("setDraggPieza");
            serverOut.writeObject(casilla);
            piezaSent = true;
        }
        if (currentTime - lastMouseDraggedTime >= 10) {
            serverOut.writeObject("mouseDragged");
            serverOut.writeObject(e.getX() + ";" + e.getY());
            lastMouseDraggedTime = currentTime;
        } else {}
    }
    
	public void postResetDragg() throws IOException {
		serverOut.writeObject("resetDragg");
		piezaSent = false;
	}
    
	public void postCasillas(ArrayList<Casilla> casillas) throws IOException {
		//este método ya no se usa
		serverOut.writeObject("updateCasillas");
		serverOut.writeObject(casillas);
		
	}


	public void postInitGame() throws IOException {
		serverOut.writeObject("initGame");
	}


	
}
