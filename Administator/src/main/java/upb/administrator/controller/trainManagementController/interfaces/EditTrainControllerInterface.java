package upb.administrator.controller.trainManagementController.interfaces;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;

public interface EditTrainControllerInterface {
    void initialize(URL arg0, ResourceBundle arg1);
    void btnEditClicked(ActionEvent event);
    void btnGoBackClicked(ActionEvent event) throws IOException, Exception;
    void radBtnDisableClicked(ActionEvent event);
    void radBtnEnableClicked(ActionEvent event);
    void btnApplyClicked(ActionEvent event);
    void btnCancelClicked(ActionEvent event);
}