package Sockets;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Date;

import juego.DatosPartida;
import juego.Session;

public class ClientPOST {
	
	private ObjectOutputStream serverOut;
	public ClientPOST(ObjectOutputStream serverOut) throws IOException {
		this.serverOut = serverOut;
		postUser();
		
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
		serverOut.writeObject(Session.getCurrentUser());
		serverOut.writeObject(msgText);
	}

	
	public class Mensaje {
		private String gameId;
		private int userId;
		private Date date;
		
	}
	
}
