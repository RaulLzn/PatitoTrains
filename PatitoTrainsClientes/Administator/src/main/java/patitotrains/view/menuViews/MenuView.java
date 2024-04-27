
package patitotrains.view.menuViews;
import javafx.scene.Node;
import javafx.application.Application;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import patitotrains.controller.menuController.MenuController;
import patitotrains.view.interfaces.ViewInterface;


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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/menu/Menu.fxml"));
        loader.setControllerFactory(controllerClass -> new MenuController());
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