package database;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import utils.Session;
import objetos.Jugador;
import objetos.Usuario;

public class GestionDB {


   
	public static void crearTablaUsuario() {
	        String sql = "CREATE TABLE IF NOT EXISTS Usuario (\n"
	                + "    username VARCHAR(255) NOT NULL,\n"
	                + "    passw TEXT NOT NULL,\n"
	                + "    img_route TEXT NOT NULL,\n"
	                + "    rank_classic INTEGER NOT NULL, \n" 
	                + "    rank_mad INTEGER NOT NULL, \n"
	                + "    tablero_theme TEXT NOT NULL,\n"
	                + "    pieza_theme TEXT NOT NULL,\n"
	                + "    PRIMARY KEY(username(100))"	
	                + ");";

	        
	       
	        
	        try (Connection conn = ConexionDB.obtenerConexion();
	             PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            pstmt.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	
	public static void crearTablaPartida() {
        String sql = "CREATE TABLE IF NOT EXISTS Partida (\n"
        		+ "    gameId VARCHAR(255) NOT NULL,\n" 
                + "    FechaIni TEXT NOT NULL,\n"
                + "    FechaFin TEXT NOT NULL,\n"
                + "    modo TEXT ,\n"
                + "    movimiento LONGBLOB NOT NULL, \n"
                + "    PRIMARY KEY(gameId(100))"
                + ");";
        
        
        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	

	
	public static void crearTablaPartidaUsuario() {
        String sql = "CREATE TABLE IF NOT EXISTS PartidaUsuario (\n"
        		+ "    gameId VARCHAR(255) NOT NULL,\n" 
        		+ "    username VARCHAR(255) INT NOT NULL,\n" 
        		+ "    gainedRank INT NOT NULL,\n" 
        		+ "    PRIMARY KEY(gameId(100))"
        		+ "    FOREIGN KEY (gameId) REFERENCES Partida(gameId)"
        		+ "    FOREIGN KEY (username) REFERENCES Usuario(username)"
                + ");";
        
        
        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	

	
	
		
	public static boolean iniciarSesion(String username, String passw) {
	    String sql = "SELECT * FROM Usuario WHERE username = ?";

	    try (Connection conn = ConexionDB.obtenerConexion();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {

	        pstmt.setString(1, username);

	        try (ResultSet rs = pstmt.executeQuery()) {
	            if (rs.next()) {
	                String storedPassword = rs.getString("passw");

	                if (verificarContraseña(passw, storedPassword)) {
	                    // La contraseña es correcta, crear una instancia de Jugador
	                
	                    Usuario user = new Usuario(rs.getString("username"),rs.getString("img_route"),rs.getInt("rank_classic"),rs.getInt("rank_mad"),rs.getString("tablero_theme"),rs.getString("pieza_theme"));
	                    Session.getVentana().loginReturn(user);
	                    
	                    return true;
	                } else {
	                    System.out.println("Contraseña incorrecta");
	                }
	            } else {
	                System.out.println("Usuario no encontrado");
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false;
	}

    
    private static boolean verificarContraseña(String ingresada, String almacenada) {
    	//Aqui se desencriptaría y se combrobaría..
        return ingresada.equals(almacenada);  
    }

	 public static String insertarUsuario(String username, String passw) {
		    
		 	String img_route = "/default.png";
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
	 
	 
	 public static void insertarPartida(String gameId, String fechaIni, String FechaFin, String modo, byte[] movimiento, String username1, String username2, int rank1, int rank2) {
		    // Verificar si el usuario ya existe antes de insertar
		    if (!existePartida(gameId)) {
		        String sql1 = "INSERT INTO Partida(gameId, fechaIni, FechaFin, modo ) VALUES(?,?,?,?)";
		        String sql2 = "INSERT INTO PartidaUsuario(gameId, username, gainedRank) VALUES(?,?,?)";
		        
		        
		        try (Connection conn = ConexionDB.obtenerConexion();
		             PreparedStatement pstmt = conn.prepareStatement(sql1)) {
		            pstmt.setString(1, gameId);
		            pstmt.setString(2, fechaIni);
		            pstmt.setString(3, FechaFin);
		            pstmt.setString(4, modo);
		            pstmt.setBytes(5, movimiento);
		            
		            pstmt.executeUpdate();
		            System.out.println("Partida insertada correctamente.");
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		        
		        for (Jugador jugador : Session.getDatosPartida().getJugadores()) {
		        	String username  =jugador.getUsuario().getUsername(); 
		        	//rank
		        	int rank = 0;
		        	
		        	try (Connection conn = ConexionDB.obtenerConexion();
				             PreparedStatement pstmt = conn.prepareStatement(sql2)) {
				            pstmt.setString(1, gameId);
				            pstmt.setString(2, username);
				            pstmt.setInt(3, rank);
 
				            pstmt.executeUpdate();
				            System.out.println("Partida insertada correctamente.");
				        } catch (SQLException e) {
				            e.printStackTrace();
				        }
				}
		    } else {
		        System.out.println("La partida ya existe. No se puede insertar.");
		    }
		    
		}
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 //demas metodos
	
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
	 
	 
	 public static void modificarUsuario(String username, String passw, String img_route, int rank_classic, int rank_mad, String tablero_theme, String Pieza_Theme) {
		 if(existeUsuario(username)) {
			 String sql = "UPDATE Usuario SET username = ? , passw = ?, img_route = ?, rank_classic = ?, rank_mad = ?, tablero_theme = ?, Pieza_Theme, WHERE username = ?";
			 
			 try (Connection conn = ConexionDB.obtenerConexion();
		            PreparedStatement pstmt = conn.prepareStatement(sql)) {
		            pstmt.setString(1, username);
		            pstmt.setString(2, passw);
		            pstmt.setString(3, img_route);
		            pstmt.setInt(4, rank_classic);
		            pstmt.setInt(5, rank_mad);
		            pstmt.setString(6, tablero_theme);
		            pstmt.setString(7, Pieza_Theme);
		            
		            pstmt.executeUpdate();
		            System.out.println("Usuario modificado correctamente.");
		        } catch (Exception e) {
					e.printStackTrace();
				}
		 } else {
			 System.out.println("No se puede modificar un usuario inexistente");
		 }
	 }
	 
	 public static boolean  modificarContraseña(String username, String passw) {
		 if(existeUsuario(username)) {
			 String sql = "UPDATE Usuario SET username = ? , passw = ?, WHERE username = ?";
			 
			 try (Connection conn = ConexionDB.obtenerConexion();
		            PreparedStatement pstmt = conn.prepareStatement(sql)) {
		            pstmt.setString(1, username);
		            pstmt.setString(2, passw);

		            
		            pstmt.executeUpdate();
		            System.out.println("Contraseña modificada correctamente.");
		            return true;
		        } catch (Exception e) {
					e.printStackTrace();
				}
		 } else {
			 System.out.println("No se puede modificar una contraseña de un usuario inexistente");
		 }
		return false;
	 }
	 
	 public static void modificarPartida(String gameId, String fechaIni, String FechaFin) {
		 if(existeUsuario(gameId)) {
			 String sql = "UPDATE Partida SET gameId = ? , fechaIni = ?, FechaFin = ? WHERE username = ?";
			 
			 try (Connection conn = ConexionDB.obtenerConexion();
		            PreparedStatement pstmt = conn.prepareStatement(sql)) {
		            pstmt.setString(1, gameId);
		            pstmt.setString(2, fechaIni);
		            pstmt.setString(3, FechaFin);
		            
		            pstmt.executeUpdate();
		            System.out.println("Partida modificada correctamente.");
		        } catch (Exception e) {
					e.printStackTrace();
				}
		 } else {
			 System.out.println("No se puede modificar una partida inexistente");
		 }
	 }

	    public static void mostrarUsuarios() {
	        String sql = "SELECT * FROM Usuario";

	        try (Connection conn = ConexionDB.obtenerConexion();
	             PreparedStatement pstmt = conn.prepareStatement(sql);
	             ResultSet rs = pstmt.executeQuery()) {
	            while (rs.next()) {
	                System.out.println("Username: " + rs.getString("username") + "\tRank Classic: " + rs.getInt("rank_classic") + "\tRank Mad Chess: " + rs.getInt("rank_mad") + rs.getString("tablero_theme"));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
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
		
		
}