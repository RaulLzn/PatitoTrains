package patitotrains.controller.userManagementController.interfaces;

import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

/**
 * Interfaz que contiene los métodos de la vista de edición de usuarios
 */
public interface RegisterUserControllerInterface {
    /**
     * Método que inicializa la vista
     * @param arg0
     * @param arg1
     * @throws IOException
     */
    void initialize(URL arg0, ResourceBundle arg1) throws IOException;

    /**
     * Método que se ejecuta al presionar el botón de regresar
     * @param event Evento
     * @throws IOException
     */
    void btnGoBackClicked(ActionEvent event) throws IOException;

    /**
     * Método que se ejecuta al presionar el botón de añadir
     * @param event Evento
     * @throws NotBoundException
     * @throws RemoteException
     */
    void btnAddClicked(ActionEvent event) throws NotBoundException, RemoteException;
}
