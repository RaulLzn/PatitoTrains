
package upb.trainmanagement.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class EditTrainView extends Application {

    public EditTrainView() {
        // Constructor vacío
    }

    public void start(@SuppressWarnings("exports") Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/EditTrain.fxml"));

        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Gestión de trenes - Añadir tren");
        primaryStage.show();



    }
        
    

    public static void main(String[] args) {
        launch();
    }
}