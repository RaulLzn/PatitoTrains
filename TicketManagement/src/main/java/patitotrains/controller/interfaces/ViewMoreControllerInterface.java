package patitotrains.controller.interfaces;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;

public interface ViewMoreControllerInterface {
    void initialize(URL arg0, ResourceBundle arg1) throws IOException;

    void btnGoBackClicked(ActionEvent event) throws IOException;


}
