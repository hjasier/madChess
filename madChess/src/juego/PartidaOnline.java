package juego;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import objetos.Boost;
import objetos.Jugador;
import objetos.Movimiento;


public class PartidaOnline extends Partida{
 
    // Sockets
    private Socket socket; 
    private ObjectInputStream input;
    private ObjectOutputStream output;

	public PartidaOnline(ArrayList<Jugador> jugadores, int gameId, Date fecha, HashMap<Boost, Boolean> boosts) {
		super(jugadores, gameId, fecha, boosts);
		
		
        try {
            this.input = new ObjectInputStream(socket.getInputStream());
            this.output = new ObjectOutputStream(socket.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        
       
    }


    public void enviarMovimiento(Movimiento movimiento) {
        try {
            output.writeObject(movimiento);
            output.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Movimiento recibirMovimiento() {
        try {
            return (Movimiento) input.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}