package patitotrains.view.trainManagementViews;
import javafx.scene.Node;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import patitotrains.controller.trainManagementController.SearchTrainController;
import patitotrains.model.Managers.TrainManager;
import patitotrains.view.interfaces.ViewInterface;

public class SearchTrainView extends Application implements ViewInterface {
    public SearchTrainView() {
        
    }

    public void start( @SuppressWarnings("exports") Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/trainManagement/SearchTrain.fxml"));
        primaryStage.setMaximized(true);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setMaximized(false);
        primaryStage.setMaximized(true);
        primaryStage.show();
    }
    public void start( @SuppressWarnings("exports") Event event, @SuppressWarnings("exports") TrainManager trainManager) throws IOException {
        Stage stageBack;
        Scene sceneBack;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/trainManagement/SearchTrain.fxml"));
        loader.setControllerFactory(controllerClass -> new SearchTrainController( trainManager));
        Parent root =loader.load();
        stageBack = (Stage) ((Node)event.getSource()).getScene().getWindow();
        sceneBack = new Scene(root);
        stageBack.setScene(sceneBack);
        stageBack.setMaximized(false);
        stageBack.setMaximized(true);
        stageBack.show();

    }
    public void start(@SuppressWarnings("exports") Event event) throws Exception{
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        start(stage);
    }
        
    

    public static void main(String[] args) {
        launch();
    }
}