package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import madChess.Keys;

public class ConexionDB {

    // Método para obtener una conexión a la base de datos
    public static Connection obtenerConexion() throws SQLException {
        return DriverManager.getConnection(Keys.DBURL, Keys.DBUSER, Keys.DBPASSWORD);
    }

    // Método para cerrar una conexión
    public static void cerrarConexion(Connection conexion) {
        if (conexion != null) {
            try {
                conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}