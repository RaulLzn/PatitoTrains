
package patitotrains.view.trainManagementViews;
import javafx.scene.Node;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import patitotrains.controller.trainManagementController.EditTrainController;
import patitotrains.model.Managers.TrainManager;
import patitotrains.model.domain.Train;
import patitotrains.view.interfaces.ViewInterface;


public class EditTrainView extends Application implements ViewInterface {

    public EditTrainView() {
        // Constructor vacío
    }

    public void start( @SuppressWarnings("exports") Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/trainManagement/EditTrain.fxml"));

        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Gestión de trenes - Añadir tren");
        primaryStage.show();

    }
    
    public void start(@SuppressWarnings("exports") Event event, @SuppressWarnings("exports") TrainManager trainManager, @SuppressWarnings("exports") Train train) throws IOException{

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/trainManagement/EditTrain.fxml"));
        loader.setControllerFactory(controllerClass -> new EditTrainController(train, trainManager));
        Parent root;
        Scene sceneEdit;
        root = loader.load();
        sceneEdit = new Scene(root);
        Stage stageEdit;
        stageEdit = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stageEdit.setScene(sceneEdit);
        stageEdit.setMaximized(false);
        stageEdit.setMaximized(true);
        stageEdit.show();
        
    }
    
    public static void main(String[] args) {
        launch();
    }
}