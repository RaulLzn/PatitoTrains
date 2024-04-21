package upb.ticketmanagement.view;

import javafx.application.Application;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.stage.Stage;
import upb.ticketmanagement.controller.SearchTicketController;
import upb.ticketmanagement.view.interfaces.ViewInterface;

public class SearchTicketView extends Application implements ViewInterface {
    public SearchTicketView() {
        
    }

    public void start(@SuppressWarnings("exports") Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/SearchTicket.fxml"));
        primaryStage.setMaximized(true);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
        
    public void start(@SuppressWarnings("exports") Event event) throws Exception {
        Stage stageBack;
        Scene sceneBack;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/SearchTicket.fxml"));
        loader.setControllerFactory(controllerClass -> new SearchTicketController());
        Parent root;
        root = loader.load();
        stageBack = (Stage) ((Node)event.getSource()).getScene().getWindow();
        sceneBack = new Scene(root);
        stageBack.setScene(sceneBack);
        stageBack.setMaximized(false);
        stageBack.setMaximized(true);
        stageBack.show();
       
        
    }
        

    public static void main(String[] args) {
        launch();
    }
}