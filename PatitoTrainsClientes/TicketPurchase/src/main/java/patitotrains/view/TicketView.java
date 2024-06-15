package patitotrains.view;

import javafx.application.Application;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import patitotrains.controller.TicketController;
import patitotrains.model.domain.Luggage;
import patitotrains.model.domain.Ticket;
import patitotrains.view.interfaces.ViewInterface;
import raul.Model.array.Array;

import java.io.IOException;

public class TicketView extends Application implements ViewInterface {
    public TicketView() {
        
    }

    public void start(@SuppressWarnings("exports") Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Ticket.fxml"));
        primaryStage.setMaximized(true);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
        
    @SuppressWarnings("exports")
    public void start(Event event, Ticket ticket, Array<Luggage> luggagesArray, Array<String> wagons) throws IOException{

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Ticket.fxml"));
        loader.setControllerFactory(controllerClass -> new TicketController(ticket, luggagesArray, wagons));
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