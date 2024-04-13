
package upb.administrator.view.trainManagementViews;
import javafx.scene.Node;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import upb.administrator.controller.trainManagementController.RegisterTrainController;
import upb.administrator.model.Managers.TrainManager;
import upb.administrator.view.interfaces.ViewInterface;

public class RegisterTrainView extends Application implements ViewInterface{

    public RegisterTrainView() {
    }

    public void start( @SuppressWarnings("exports") Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/trainManagement/RegisterTrain.fxml"));
        Parent root =loader.load();
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Gestión de trenes - Añadir tren");
        primaryStage.show();
    }
    public void start(@SuppressWarnings("exports") Event event, @SuppressWarnings("exports") TrainManager trainManager) throws IOException{
        Stage stageRegister;
        Scene sceneRegister;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/trainManagement/RegisterTrain.fxml"));
        loader.setControllerFactory(controllerClass -> new RegisterTrainController( trainManager));
        Parent root =loader.load();
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