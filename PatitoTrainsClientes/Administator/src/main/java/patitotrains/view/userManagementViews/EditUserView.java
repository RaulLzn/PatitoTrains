
package patitotrains.view.userManagementViews;
import javafx.scene.Node;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import patitotrains.controller.userManagementController.EditUserController;
import patitotrains.model.Managers.UserManager;
import patitotrains.model.domain.User;
import patitotrains.view.interfaces.ViewInterface;


public class EditUserView extends Application implements ViewInterface {

    public EditUserView() {
        // Constructor vacío
    }

    public void start(@SuppressWarnings("exports") Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/userManagement/EditTrain.fxml"));

        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Gestión de trenes - Añadir tren");
        primaryStage.show();

    }

    @SuppressWarnings("exports")
    public void start(Event event, User user, UserManager userManager) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/userManagement/EditUser.fxml"));
        loader.setControllerFactory(controllerClass -> new EditUserController(user,  userManager));
        Parent root;
        Scene sceneView;
        root = loader.load();
        sceneView = new Scene(root);
        Stage stageView;
        stageView = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stageView.setScene(sceneView);
        stageView.setMaximized(false);
        stageView.setMaximized(true);
        stageView.show();
    
    }
    
    public static void main(String[] args) {
        launch();
    }
}