package patitotrains.model.trazabilidad;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.Remote;
import java.time.LocalDateTime;

/**
 * Clase que se encarga de registrar las acciones realizadas por los usuarios
 */
public class ActionLogger implements Remote {
    private static final String LOG_FILE = "Logger.txt";

    /**
     * Método que registra una acción en el archivo de registro
     * @param action Acción a registrar
     * @return Verdadero si la acción se registró correctamente, falso en caso contrario
     */
    public boolean logAction(String action) {
        String timestamp = LocalDateTime.now().toString();
        String logMessage = timestamp + ": " + action;

        try (PrintWriter writer = new PrintWriter(new FileWriter(LOG_FILE, true))) {
            writer.println(logMessage);
            return true;
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo de registro: " + e.getMessage());
            return false;
        }
    }
}
