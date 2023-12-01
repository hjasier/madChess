package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import objetos.Jugador;
import utils.Session;

public class GestionBd {


   
	public static void crearTablaUsuario() {
	        String sql = "CREATE TABLE IF NOT EXISTS Usuario (\n"
	                + "    username VARCHAR(255) NOT NULL,\n"
	                + "    passw TEXT NOT NULL,\n"
	                + "    img_route TEXT NOT NULL,\n"
	                + "    ranking INTEGER NOT NULL, \n" 
	                + "    PRIMARY KEY(username(100))"	
	                + ");";

	        
	        
	        
	        try (Connection conn = ConexionBd.obtenerConexion();
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
                + "    ranking INTEGER NOT NULL, \n" 
                + "    PRIMARY KEY(gameId(100))"
                + ");";
        
        
        try (Connection conn = ConexionBd.obtenerConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
		
	public static boolean iniciarSesion(String username, String passw) {
	    String sql = "SELECT * FROM Usuario WHERE username = ?";

	    try (Connection conn = ConexionBd.obtenerConexion();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {

	        pstmt.setString(1, username);

	        try (ResultSet rs = pstmt.executeQuery()) {
	            if (rs.next()) {
	                String storedPassword = rs.getString("passw");

	                if (verificarContraseña(passw, storedPassword)) {
	                    // La contraseña es correcta, crear una instancia de Jugador
	                    Jugador jugador = new Jugador(rs.getString("username"),rs.getInt("ranking"),rs.getString("img_route"),0);
	                    Session.getVentana().loginReturn(jugador);
	                    
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
		 	int rank = 0;
		 	
		    if (existeUsuario(username)) {
		       return "Error, El usuario ya existe";
		    }
		    
		    String sql = "INSERT INTO Usuario(username, passw, img_route , ranking ) VALUES(?,?,?,?)";

	        try (Connection conn = ConexionBd.obtenerConexion();
	             PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            pstmt.setString(1, username);
	            pstmt.setString(2, passw);
	            pstmt.setString(3, img_route);
	            pstmt.setInt(4, rank);
	            
	            pstmt.executeUpdate();
	            return "Usuario insertado correctamente.";
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
			return "Ha sucedido un error durante el registro";
		}
	 
	 
	 public static void insertarPartida(String gameId, String fechaIni, String FechaFin) {
		    // Verificar si el usuario ya existe antes de insertar
		    if (!existePartida(gameId)) {
		        String sql = "INSERT INTO Partida(gameId, fechaIni, FechaFin ) VALUES(?,?,?)";

		        try (Connection conn = ConexionBd.obtenerConexion();
		             PreparedStatement pstmt = conn.prepareStatement(sql)) {
		            pstmt.setString(1, gameId);
		            pstmt.setString(2, fechaIni);
		            pstmt.setString(3, FechaFin);
		            
		            pstmt.executeUpdate();
		            System.out.println("Partida insertada correctamente.");
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    } else {
		        System.out.println("La partida ya existe. No se puede insertar.");
		    }
		}
	 
	 
	
	 public static void eliminarUsuario(String username) {
		 if(existeUsuario(username)) {
			 String sql = "DELETE FROM Usuario WHERE username = ? ";
			 
			 try (Connection conn = ConexionBd.obtenerConexion();
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
			 
			 try (Connection conn = ConexionBd.obtenerConexion();
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
	 
	 
	 public static void modificarUsuario(String username, String passw, String img_route, int rank) {
		 if(existeUsuario(username)) {
			 String sql = "UPDATE Usuario SET username = ? , passw = ?, img_route = ?, ranking = ? WHERE username = ?";
			 
			 try (Connection conn = ConexionBd.obtenerConexion();
		            PreparedStatement pstmt = conn.prepareStatement(sql)) {
		            pstmt.setString(1, username);
		            pstmt.setString(2, passw);
		            pstmt.setString(3, img_route);
		            pstmt.setInt(4, rank);
		            
		            pstmt.executeUpdate();
		            System.out.println("Usuario modificado correctamente.");
		        } catch (Exception e) {
					e.printStackTrace();
				}
		 } else {
			 System.out.println("No se puede modificar un usuario inexistente");
		 }
	 }
	 
	 public static void modificarPartida(String gameId, String fechaIni, String FechaFin) {
		 if(existeUsuario(gameId)) {
			 String sql = "UPDATE Partida SET gameId = ? , fechaIni = ?, FechaFin = ? WHERE username = ?";
			 
			 try (Connection conn = ConexionBd.obtenerConexion();
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

	        try (Connection conn = ConexionBd.obtenerConexion();
	             PreparedStatement pstmt = conn.prepareStatement(sql);
	             ResultSet rs = pstmt.executeQuery()) {
	            while (rs.next()) {
	                System.out.println("Username: " + rs.getString("Username") + "\tRank: " + rs.getInt("ranking"));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    

	   
		public static boolean existeUsuario(String username) {
	        String sql = "SELECT * FROM Usuario WHERE username = ?";

	        try (Connection conn = ConexionBd.obtenerConexion();
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

	        try (Connection conn = ConexionBd.obtenerConexion();
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