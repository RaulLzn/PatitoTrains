
package patitotrains.view;


import javafx.application.Application;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import patitotrains.controller.ViewMoreController;
import patitotrains.model.domain.Ticket;
import patitotrains.model.domain.TicketManager;
import patitotrains.view.interfaces.ViewInterface;


public class ViewMoreView extends Application implements ViewInterface {

    public ViewMoreView() {
        // Constructor vacío
    }

    public void start(@SuppressWarnings("exports") Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ViewMore.fxml"));

        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Gestión de boletos - Ver mas");
        primaryStage.show();

    }

    @SuppressWarnings("exports")
    public void start(Event event, Ticket ticket, TicketManager ticketManager) throws Exception {

     
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ViewMore.fxml"));
        loader.setControllerFactory(controllerClass -> new ViewMoreController(ticket,  ticketManager));
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