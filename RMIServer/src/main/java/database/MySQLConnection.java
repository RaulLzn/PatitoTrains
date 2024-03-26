package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {
    // Método para obtener una conexión a la base de datos
    public static Connection getConnection() throws SQLException {
        // Establecer la información de conexión
        String url = "jdbc:mysql://10.30.32.3:3306/nombre_basedatos";
        String user = "root";
        String password = "842963";

        // Intentar establecer la conexión
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("Conexión exitosa a la base de datos.");
            return connection;
        } catch (SQLException e) {
            // Capturar cualquier error de conexión
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
            throw e;
        }
    }
}

