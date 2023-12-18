package database;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import juego.DatosPartida;
import utils.Session;
import objetos.Jugador;
import objetos.Movimiento;
import objetos.Usuario;

public class GestorDB {
	
	
	
	//--------------------------------------------------------
	//Para crear tablas las creais en el workbench, aquí no
	//--------------------------------------------------------
	
	
	
	public static boolean iniciarSesion(String username, String passw) {
	    String sql = "SELECT * FROM Usuario WHERE username = ? AND passw = ?";

	    try (Connection conn = ConexionDB.obtenerConexion();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {

	        pstmt.setString(1, username);
	        pstmt.setString(2, passw);

	        try (ResultSet rs = pstmt.executeQuery()) {
	            if (rs.next()) {
	                // El usuario con la contraseña proporcionada existe
	                Usuario user = new Usuario(
	                        rs.getString("username"),
	                        rs.getString("img_route"),
	                        rs.getInt("rank_classic"),
	                        rs.getInt("rank_mad"),
	                        rs.getString("tablero_theme"),
	                        rs.getString("pieza_theme"));

	                Session.getVentana().loginReturn(user);

	                return true;
	            } else {
	                System.out.println("Usuario o contraseña incorrectos");
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false;
	}

    


	 public static String insertarUsuario(String username, String passw) {
		    
		 	String img_route = "https://media.madchess.online/media/test.png";
		 	int rank_classic = 0;
		 	int rank_mad = 0;
		 	String tablero_theme = "MADCHESS";
		 	String pieza_theme = "DF";
		 	
		    if (existeUsuario(username)) {
		       return "Error, El usuario ya existe";
		    }
		  
		    String sql = "INSERT INTO Usuario(username, passw, img_route , rank_classic, rank_mad, tablero_theme , pieza_theme) VALUES(?,?,?,?,?,?,?)";

	        try (Connection conn = ConexionDB.obtenerConexion();
	             PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            pstmt.setString(1, username);
	            pstmt.setString(2, passw);
	            pstmt.setString(3, img_route);
	            pstmt.setInt(4, rank_classic);
	            pstmt.setInt(5, rank_mad);
	            pstmt.setString(6 ,tablero_theme);
	            pstmt.setString(7 ,pieza_theme);
	            
	            pstmt.executeUpdate();
	            return "Usuario insertado correctamente.";
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
			return "Ha sucedido un error durante el registro";
		}
	 
	 
	    public static void insertarPartida(DatosPartida datosPartida) {
	        String sql = "INSERT INTO Partida (gameId, modoDeJuego, tipoPartida, fechaIni, fechaFin, movsraw) VALUES (?, ?, ?, ?, ?, ?)";

	        try (Connection conn = ConexionDB.obtenerConexion(); 
	             PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        	
	        	System.out.println(datosPartida.getTipoPartida());
	            pstmt.setString(1, datosPartida.getGameId());
	            pstmt.setString(2, datosPartida.getModoDeJuego().toString());
	            pstmt.setString(3, datosPartida.getTipoPartida().toString());
	            pstmt.setObject(4, datosPartida.getFechaIni());
	            pstmt.setObject(5, datosPartida.getFechaFin());
	            
	            // Serializar la lista de movimientos
	            byte[] movimientosSerializados = serializarMovimientos(datosPartida.getMovimientos());
	            pstmt.setBytes(6, movimientosSerializados);
	            
	            pstmt.executeUpdate();
	            
	            insertarRelacionesJugadores(conn, datosPartida.getGameId(), datosPartida.getJugadores());
	            insertarRelacionesGanadores(conn, datosPartida.getGameId(), datosPartida.getGanadores());
	            
	            
	            

	        } catch (SQLException | IOException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    private static byte[] serializarMovimientos(ArrayList<Movimiento> movimientos) throws IOException {
	        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
	             ObjectOutputStream oos = new ObjectOutputStream(bos)) {

	            oos.writeObject(movimientos);
	            return bos.toByteArray();

	        }
	    }
	    
	    private static void insertarRelacionesJugadores(Connection conn, String gameId, ArrayList<Jugador> jugadores) throws SQLException {
	        String sql = "INSERT INTO PartidaJugador (partidaId, username) VALUES (?, ?)";
	        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            for (Jugador jugador : jugadores) {
	                pstmt.setString(1, gameId);
	                pstmt.setString(2, jugador.getUsuario().getUsername());
	                pstmt.executeUpdate();
	            }
	        }
	    }

	    private static void insertarRelacionesGanadores(Connection conn, String gameId, ArrayList<Jugador> ganadores) throws SQLException {
	        String sql = "INSERT INTO PartidaGanador (partidaId, username) VALUES (?, ?)";
	        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            for (Jugador ganador : ganadores) {
	                pstmt.setString(1, gameId);
	                pstmt.setString(2, ganador.getUsuario().getUsername());
	                pstmt.executeUpdate();
	            }
	        }
	    }
	 
	    
	    
	    
	    public static void modificarImagenUsuario(String username, String img_route) {
		    if (existeUsuario(username)) {
		        String sql = "UPDATE Usuario SET img_route = ? WHERE username = ?";

		        try (Connection conn = ConexionDB.obtenerConexion();
		             PreparedStatement pstmt = conn.prepareStatement(sql)) {
		            pstmt.setString(1, img_route);
		            pstmt.setString(2, username);

		            pstmt.executeUpdate();
		            System.out.println("Imagen de usuario modificada correctamente.");
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		    } else {
		        System.out.println("No se puede modificar la imagen de un usuario inexistente");
		    }
		}
	    
	    public static boolean modificarContraseña(String username, String newPassw) {
		    if (existeUsuario(username)) {
		        String sql = "UPDATE Usuario SET passw = ? WHERE username = ?";
		        
		        try (Connection conn = ConexionDB.obtenerConexion();
		             PreparedStatement pstmt = conn.prepareStatement(sql)) {
		            pstmt.setString(1, newPassw);
		            pstmt.setString(2, username);

		            pstmt.executeUpdate();
		            return true;
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		    } else {
		        System.out.println("No se puede modificar la contraseña de un usuario inexistente.");
		    }
		    return false;
		}


		public static boolean existeUsuario(String username) {
	        String sql = "SELECT * FROM Usuario WHERE username = ?";

	        try (Connection conn = ConexionDB.obtenerConexion();
	             PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            pstmt.setString(1, username);
	            ResultSet rs = pstmt.executeQuery();
	            return rs.next();
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
	    
	    
	    
		public static boolean existePartida(String gameId) {
	        String sql = "SELECT * FROM Partida WHERE gameId = ?";

	        try (Connection conn = ConexionDB.obtenerConexion();
	             PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            pstmt.setString(1, gameId);
	            ResultSet rs = pstmt.executeQuery();
	            return rs.next();
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	 

	 public static void eliminarUsuario(String username) {
		 if(existeUsuario(username)) {
			 String sql = "DELETE FROM Usuario WHERE username = ? ";
			 
			 try (Connection conn = ConexionDB.obtenerConexion();
		            PreparedStatement pstmt = conn.prepareStatement(sql)) {
				 
				 	pstmt.setString(1, username);
		            pstmt.executeUpdate();
		            System.out.println("Usuario eliminado correctamente.");
		        } catch (Exception e) {
				e.printStackTrace();
			}
		 } else {
			 System.out.println("No se puede eliminar un usuario inexistente");
		 }
	 }
	 
	 
	 
	 public static void eliminarPartida(String gameId) {
		 if(existeUsuario(gameId)) {
			 String sql = "DELETE FROM Partida WHERE gameId = ? ";
			 
			 try (Connection conn = ConexionDB.obtenerConexion();
		            PreparedStatement pstmt = conn.prepareStatement(sql)) {
		            
				 pstmt.setString(1, gameId);
		            pstmt.executeUpdate();
		            System.out.println("Partida eliminada correctamente.");
		        } catch (Exception e) {
				e.printStackTrace();
			}
		 } else {
			 System.out.println("No se puede eliminar una partida inexistente");
		 }
	 }
	 
	 
	 public static void modificarUsuario(String username, String img_route, int rank_classic, int rank_mad, String tablero_theme, String Pieza_Theme) {
		 if(existeUsuario(username)) {
			 String sql = "UPDATE Usuario SET username = ? , img_route = ?, rank_classic = ?, rank_mad = ?, tablero_theme = ?, pieza_Theme = ? WHERE username = ?";
			 
			 try (Connection conn = ConexionDB.obtenerConexion();
		            PreparedStatement pstmt = conn.prepareStatement(sql)) {
		            pstmt.setString(1, username);
		            pstmt.setString(2, img_route);
		            pstmt.setInt(3, rank_classic);
		            pstmt.setInt(4, rank_mad);
		            pstmt.setString(5, tablero_theme);
		            pstmt.setString(6, Pieza_Theme);
		            pstmt.setString(7, username);

		            
		            pstmt.executeUpdate();
		            System.out.println("Usuario modificado correctamente.");
		        } catch (Exception e) {
					e.printStackTrace();
				}
		 } else {
			 System.out.println("No se puede modificar un usuario inexistente");
		 }
	 }
	 
	 
	 
	 
		
		
		
}