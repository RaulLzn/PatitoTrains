package patitotrains.controller.userManagementController.interfaces;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;

public interface RegisterUserControllerInterface {
    void initialize(URL arg0, ResourceBundle arg1) throws IOException;

    void btnGoBackClicked(ActionEvent event) throws IOException;

    void btnAddClicked(ActionEvent event) throws NotBoundException, RemoteException;
}
