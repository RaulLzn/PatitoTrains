
package patitotrains.view.userManagementViews;
import patitotrains.controller.userManagementController.RegisterUserController;
import patitotrains.model.Managers.UserManager;
import patitotrains.view.interfaces.ViewInterface;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;

public class RegisterUserView extends Application implements ViewInterface {

    public RegisterUserView() {
    }

    public void start(@SuppressWarnings("exports") Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/userManagement/RegisterUser.fxml"));
        Parent root =loader.load();
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Gestión de trenes - Añadir tren");
        primaryStage.show();
    }

    @SuppressWarnings("exports")
    public void start(Event event, UserManager userManager) throws IOException{
        Stage stageRegister;

        Scene sceneRegister;
    

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/userManagement/RegisterUser.fxml"));
        loader.setControllerFactory(controllerClass -> new RegisterUserController( userManager));
        Parent root;
       
        root = loader.load();
        stageRegister = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        sceneRegister = new Scene(root);
        
        stageRegister.setScene(sceneRegister);
        stageRegister.setMaximized(false);
        stageRegister.setMaximized(true);
        stageRegister.show();
    }
    

    public static void main(String[] args) {
        launch();
    }
}