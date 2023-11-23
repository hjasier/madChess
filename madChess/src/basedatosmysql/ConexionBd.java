package basedatosmysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBd {
    private static final String URL = "jdbc:mysql://ynu.h.filess.io:3307/madChess_shelfmetal";
    private static final String USER = "madChess_shelfmetal";
    private static final String PASSWORD = "9cb17400b7e86c5df7a1a682069d3a561639c208";

    // Método para obtener una conexión a la base de datos
    public static Connection obtenerConexion() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
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