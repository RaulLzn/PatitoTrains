
package upb.usermanagement.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import upb.usermanagement.view.interfaces.ViewInterface;

public class RegisterTrainView extends Application implements ViewInterface{

    public RegisterTrainView() {
    }

    public void start(@SuppressWarnings("exports") Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/RegisterTrain.fxml"));
        Parent root =loader.load();
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Gestión de trenes - Añadir tren");
        primaryStage.show();
    }
        
    

    public static void main(String[] args) {
        launch();
    }
}