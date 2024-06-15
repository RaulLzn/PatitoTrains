package patitotrains.view;

import javafx.application.Application;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import patitotrains.controller.RouteDataController;
import patitotrains.model.domain.Passenger;
import patitotrains.view.interfaces.ViewInterface;
import raul.Model.array.Array;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class RouteDataView extends Application implements ViewInterface {
    public RouteDataView() {
        
    }

    public void start(@SuppressWarnings("exports") Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/RouteData.fxml"));
        primaryStage.setMaximized(true);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
        
    public void start(@SuppressWarnings("exports") Event event, @SuppressWarnings("exports") Passenger passenger, Array<Integer> weights) throws IOException{

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/RouteData.fxml"));
        loader.setControllerFactory(controllerClass -> {
            try {
                return new RouteDataController(passenger, weights);
            } catch (NotBoundException e) {
                throw new RuntimeException(e);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        });
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