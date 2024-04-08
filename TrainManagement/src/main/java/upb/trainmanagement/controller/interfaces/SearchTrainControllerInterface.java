package upb.trainmanagement.controller.interfaces;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;

public interface SearchTrainControllerInterface {
    void btnRegisterTrainClicked(ActionEvent event) throws IOException;
    void initialize(URL arg0, ResourceBundle arg1);
    
}
