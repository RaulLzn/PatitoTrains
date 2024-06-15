package patitotrains.view.routeManagementView;
import java.io.IOException;
import javafx.scene.Node;
import javafx.application.Application;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import patitotrains.controller.routeManagementController.RegisterRouteController;
import patitotrains.model.Managers.RouteManager;
import patitotrains.view.interfaces.ViewInterface;

public class RegisterRouteView extends Application implements ViewInterface {
    public RegisterRouteView() {
        
    }

    public void start( @SuppressWarnings("exports") Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/routeManagement/RegisterRoute.fxml"));
        primaryStage.setMaximized(true);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setMaximized(false);
        primaryStage.setMaximized(true);
        primaryStage.show();
    }
    public void start(@SuppressWarnings("exports") Event event, @SuppressWarnings("exports") RouteManager routeManager) throws IOException{
        Stage stageRegister;
        Scene sceneRegister;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/routeManagement/RegisterRoute.fxml"));
        loader.setControllerFactory(controllerClass -> new RegisterRouteController( routeManager));
        Parent root =loader.load();
        stageRegister = (Stage) ((Node)event.getSource()).getScene().getWindow();
        sceneRegister = new Scene(root);
        stageRegister.setScene(sceneRegister);
        stageRegister.setMaximized(false);
        stageRegister.setMaximized(true);
        stageRegister.show();

    }

    public void start(@SuppressWarnings("exports") Event event) throws Exception{
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        start(stage);
    }
        
    

    public static void main(String[] args) {
        launch();
    }
}