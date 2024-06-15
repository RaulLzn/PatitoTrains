package patitotrains.view.routeManagementView;

import javafx.application.Application;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import patitotrains.controller.routeManagementController.SearchRouteController;
import patitotrains.model.Managers.RouteManager;
import patitotrains.view.interfaces.ViewInterface;

import java.io.IOException;

public class SearchRouteView extends Application implements ViewInterface {
    public SearchRouteView() {
        
    }

    public void start( @SuppressWarnings("exports") Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/routeManagement/SearchRoute.fxml"));
        primaryStage.setMaximized(true);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setMaximized(false);
        primaryStage.setMaximized(true);
        primaryStage.show();
    }


    public void start( @SuppressWarnings("exports") Event event, @SuppressWarnings("exports") RouteManager routeManager) throws IOException  {
        Stage stageBack;
        Scene sceneBack;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/routeManagement/SearchRoute.fxml"));
        loader.setControllerFactory(controllerClass -> new SearchRouteController( routeManager));
        Parent root =loader.load();
        stageBack = (Stage) ((Node)event.getSource()).getScene().getWindow();
        sceneBack = new Scene(root);
        stageBack.setScene(sceneBack);
        stageBack.setMaximized(false);
        stageBack.setMaximized(true);
        stageBack.show();

    }

    public void start(@SuppressWarnings("exports") Event event) throws Exception{
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        start(stage);
    }
        
    

    public static void main(String[] args) {
        launch();
    }
}