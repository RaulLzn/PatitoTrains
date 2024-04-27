package patitotrains.model.trazabilidad;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.Remote;
import java.time.LocalDateTime;

public class ActionLogger implements Remote {
    private static final String LOG_FILE = "Logger.txt";

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
