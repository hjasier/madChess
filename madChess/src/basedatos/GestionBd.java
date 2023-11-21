package basedatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GestionBd {






   
	public static void crearTablaUsuario() {
	        String sql = "CREATE TABLE IF NOT EXISTS Usuario (\n"
	                + "    username TEXT PRIMARY KEY,\n"
	                + "    passw TEXT NOT NULL,\n"
	                + "    img_route TEXT,\n"
	                + "    rank INT DEFAULT 0 NOT NULL\n"
	                + ");";

	        try (Connection conn = ConexionBd.obtenerConexion();
	             PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            pstmt.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	 public static void insertarUsuario(String username, String passw, String img_route, int rank) {
		    // Verificar si el usuario ya existe antes de insertar
		    if (!existeUsuario(username)) {
		        String sql = "INSERT INTO Usuario(username, passw, img_route , rank ) VALUES(?,?,?,?)";

		        try (Connection conn = ConexionBd.obtenerConexion();
		             PreparedStatement pstmt = conn.prepareStatement(sql)) {
		            pstmt.setString(1, username);
		            pstmt.setString(2, passw);
		            pstmt.setString(3, img_route);
		            pstmt.setInt(4, rank);
		            
		            pstmt.executeUpdate();
		            System.out.println("Usuario insertado correctamente.");
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    } else {
		        System.out.println("El usuario ya existe. No se puede insertar.");
		    }
		}
	 
	 
	
	 public static void eliminarUsuarios(String username) {
		 if(existeUsuario(username)) {
			 String sql = "DELETE FROM Usuario WHERE username = ? ";
			 
			 try (Connection conn = ConexionBd.obtenerConexion();
		            PreparedStatement pstmt = conn.prepareStatement(sql)) {
		            
		            pstmt.executeUpdate();
		            System.out.println("Usuario eliminado correctamente.");
		        } catch (Exception e) {
				e.printStackTrace();
			}
		 } else {
			 System.out.println("No se puede eliminar un usuario inexistente");
		 }
	 }
	 
	 
	 public static void modificarUsuarios(String username, String passw, String img_route, int rank) {
		 if(existeUsuario(username)) {
			 String sql = "UPDATE Usuario SET username = ? , passw = ? WHERE username = ?";
			 
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

	    public static void mostrarUsuarios() {
	        String sql = "SELECT * FROM Usuario";

	        try (Connection conn = ConexionBd.obtenerConexion();
	             PreparedStatement pstmt = conn.prepareStatement(sql);
	             ResultSet rs = pstmt.executeQuery()) {
	            while (rs.next()) {
	                System.out.println("Username: " + rs.getString("Username") + "\tRank: " + rs.getInt("rank"));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    public static void mostrarUsuariosPassw() {
	        String sql = "SELECT * FROM Usuario";

	        try (Connection conn = ConexionBd.obtenerConexion();
	             PreparedStatement pstmt = conn.prepareStatement(sql);
	             ResultSet rs = pstmt.executeQuery()) {
	            while (rs.next()) {
	                System.out.println("Username: " + rs.getString("Username") + "\tPassword: " + rs.getString("passw"));
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
}
