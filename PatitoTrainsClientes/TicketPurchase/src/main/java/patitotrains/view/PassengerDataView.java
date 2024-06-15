package patitotrains.view;
import javafx.scene.Node;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import patitotrains.view.interfaces.ViewInterface;


public class PassengerDataView extends Application implements ViewInterface {

    public PassengerDataView() {
        // Constructor vacío
    }

    public void start(@SuppressWarnings("exports") Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/PassengerData.fxml"));

        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Datos del Pasajero");
        primaryStage.show();

    }

    public void start(@SuppressWarnings("exports") Event event) throws IOException{

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/PassengerData.fxml"));
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