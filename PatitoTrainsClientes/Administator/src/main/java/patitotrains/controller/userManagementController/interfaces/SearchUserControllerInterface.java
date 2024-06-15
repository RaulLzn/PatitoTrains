package patitotrains.controller.userManagementController.interfaces;

import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Interfaz que contiene los métodos de la vista de búsqueda de usuarios
 */
public interface SearchUserControllerInterface {
    /**
     * Método que se ejecuta al presionar el botón de buscar
     * @param event Evento
     */
    void btnRegisterUserClicked(ActionEvent event) throws IOException;

    /**
     * Método que se ejecuta al presionar el botón de regresar
     */
    void initialize(URL arg0, ResourceBundle arg1);
}
