
package upb.ticketmanagement.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import upb.ticketmanagement.view.interfaces.ViewInterface;


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
        
    
    public static void main(String[] args) {
        launch();
    }
}