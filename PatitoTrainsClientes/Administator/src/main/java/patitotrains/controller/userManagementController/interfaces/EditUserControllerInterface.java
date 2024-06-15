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
public interface EditUserControllerInterface {
    /**
     * Método que inicializa la vista
     * @param arg0
     * @param arg1
     */
    void initialize(URL arg0, ResourceBundle arg1);
    /**
     * Método que se ejecuta al presionar el botón de editar
     * @param event Evento
     */
    void btnEditClicked(ActionEvent event);
    /**
     * Método que se ejecuta al presionar el botón de regresar
     * @param event Evento
     * @throws IOException
     */
    void btnGoBackClicked(ActionEvent event) throws IOException;
    /**
     * Método que se ejecuta al presionar el botón de deshabilitar
     * @param event Evento
     */
    void radBtnDisableClicked(ActionEvent event);
    /**
     * Método que se ejecuta al presionar el botón de habilitar
     * @param event Evento
     */
    void radBtnEnableClicked(ActionEvent event);
    /**
     * Método que se ejecuta al presionar el botón de aplicar
     * @param event Evento
     * @throws NotBoundException
     * @throws RemoteException
     */
    void btnApplyClicked(ActionEvent event) throws NotBoundException, RemoteException;
    /**
     * Método que se ejecuta al presionar el botón de cancelar
     * @param event Evento
     */
    void btnCancelClicked(ActionEvent event);
}
