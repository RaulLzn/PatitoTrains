package patitotrains.view.routeManagementView;
import javafx.scene.Node;
import javafx.application.Application;
import javafx.event.Event;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import patitotrains.controller.routeManagementController.EditRouteController;
import patitotrains.model.Managers.RouteManager;
import patitotrains.model.domain.Route;
import patitotrains.view.interfaces.ViewInterface;

public class EditRouteView extends Application implements ViewInterface {
    public EditRouteView() {
        // Constructor vacío
    }

    public void start( @SuppressWarnings("exports") Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/routeManagement/EditRoute.fxml"));

        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Gestión de trenes - Añadir tren");
        primaryStage.show();

    }
    
    public void start(@SuppressWarnings("exports") Event event, @SuppressWarnings("exports") RouteManager routeManager, 
    @SuppressWarnings("exports") Route route) throws IOException{

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/routeManagement/EditRoute.fxml"));
        loader.setControllerFactory(controllerClass -> new EditRouteController(routeManager, route));
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

