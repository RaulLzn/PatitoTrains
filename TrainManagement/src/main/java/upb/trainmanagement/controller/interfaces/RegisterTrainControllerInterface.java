package upb.trainmanagement.controller.interfaces;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;

public interface RegisterTrainControllerInterface {
    void initialize(URL arg0, ResourceBundle arg1) throws IOException;

    void btnGoBackClicked(ActionEvent event) throws IOException;

    void btnAddClicked(ActionEvent event);
}
