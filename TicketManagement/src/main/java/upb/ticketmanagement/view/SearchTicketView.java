package upb.ticketmanagement.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
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
        
    

    public static void main(String[] args) {
        launch();
    }
}