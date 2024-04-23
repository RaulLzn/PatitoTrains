package patitotrains.controller.userManagementController.interfaces;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;

public interface EditUserControllerInterface {
    void initialize(URL arg0, ResourceBundle arg1);
    void btnEditClicked(ActionEvent event);
    void btnGoBackClicked(ActionEvent event) throws IOException;
    void radBtnDisableClicked(ActionEvent event);
    void radBtnEnableClicked(ActionEvent event);
    void btnApplyClicked(ActionEvent event);
    void btnCancelClicked(ActionEvent event);
}
