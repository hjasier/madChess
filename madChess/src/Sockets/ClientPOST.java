package Sockets;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import juego.DatosPartida;
import objetos.Boost;
import objetos.Casilla;
import objetos.Movimiento;
import utils.Configuracion;
import utils.Session;

public class ClientPOST {
	
	private ObjectOutputStream serverOut;
	private long lastMouseDraggedTime;
	private boolean piezaSent = false;
	
	public ClientPOST(ObjectOutputStream serverOut) throws IOException {
		this.serverOut = serverOut;
		postUser();
		
	}
	
	
	public void createGame(int playerNum) throws IOException {
		serverOut.writeObject("hostNewGame");
		serverOut.writeObject(playerNum);
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
    	if (Configuracion.MLTPLY_PIEZA_DRAG) {
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
    }
    
	public void postResetDragg() throws IOException {
		if (Configuracion.MLTPLY_PIEZA_DRAG) {
		serverOut.writeObject("resetDragg");
		piezaSent = false;
		}
	}
    
	public void postCasillas(ArrayList<Casilla> casillas) throws IOException {
		//este método ya no se usa
		serverOut.writeObject("updateCasillas");
		serverOut.writeObject(casillas);
		
	}


	public void postInitGame() throws IOException {
		serverOut.writeObject("initGame");
	}


	public void postDatosPartida() throws IOException {
		if (Session.getDatosPartida().getJugadores().get(0).getUsuario().getUsername().equals(Session.getCurrentUser().getUsername())) {
			serverOut.reset();
			serverOut.writeObject("reloadDatosPartida");
			System.out.println("ENVIANDO:"+Session.getDatosPartida().getTipoPartida());
			serverOut.writeObject(Session.getDatosPartida());	
		} 
		
	}


	public void postBoost(Boost boost) throws IOException {
		serverOut.reset();
		serverOut.writeObject("postBoost");
		serverOut.writeObject(boost);	
	}



	public void postReadyStatus() throws IOException {
		serverOut.writeObject("postReadyStatus");
	}


	public void postInitSelectMadConf() throws IOException {
		serverOut.writeObject("postInitSelectMadConf");
	}


	public void postMadSelects(ArrayList<Boolean> selectedAlters, ArrayList<Boolean> selectedBoosts) throws IOException {
		serverOut.writeObject("postMadSelects");
		serverOut.writeObject(selectedAlters);
		serverOut.writeObject(selectedBoosts);
		
		
	}
	
	




	
}
