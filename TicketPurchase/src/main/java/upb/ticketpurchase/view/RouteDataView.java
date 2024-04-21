package upb.ticketpurchase.view;
import java.io.IOException;

import com.edu.upb.array.Array;

import javafx.scene.Node;
import javafx.application.Application;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import upb.ticketpurchase.controller.RouteDataController;
import upb.ticketpurchase.model.domain.Passenger;
import upb.ticketpurchase.view.interfaces.ViewInterface;

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
        
    public void start(@SuppressWarnings("exports") Event event, @SuppressWarnings("exports") Passenger passenger, Array<Double> weights) throws IOException{

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/RouteData.fxml"));
        loader.setControllerFactory(controllerClass -> new RouteDataController(passenger, weights));
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