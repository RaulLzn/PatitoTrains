
package upb.administrator.view.menuViews;
import javafx.scene.Node;
import javafx.application.Application;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import upb.administrator.view.interfaces.ViewInterface;



public class MenuView extends Application implements ViewInterface {

    public MenuView() {
        // Constructor vacío
    }

    public void start( @SuppressWarnings("exports") Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/menu/Menu.fxml"));

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