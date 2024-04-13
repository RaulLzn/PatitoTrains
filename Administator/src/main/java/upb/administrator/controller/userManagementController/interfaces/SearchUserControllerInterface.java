package upb.administrator.controller.userManagementController.interfaces;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;

public interface SearchUserControllerInterface {
    void btnRegisterUserClicked(ActionEvent event) throws IOException;
    void initialize(URL arg0, ResourceBundle arg1);
}
