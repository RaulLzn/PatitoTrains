
package patitotrains.view.loginView;
import javafx.scene.Node;
import javafx.application.Application;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import patitotrains.view.interfaces.ViewInterface;



public class LoginView extends Application implements ViewInterface {

    public LoginView() {
        // Constructor vacío
    }

    public void start( @SuppressWarnings("exports") Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/login/Login.fxml"));

        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Menu Views");
        primaryStage.setMaximized(false);
        primaryStage.setMaximized(true);
        primaryStage.show();

    }
    public void start(@SuppressWarnings("exports") Event event) throws Exception{
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        start( stage);
    }
        
    
    public static void main(String[] args) {
        launch();
    }
}