package patitotrains.view.boardingPanelView;


import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import patitotrains.view.interfaces.ViewInterface;



public class BoardingPanelView extends Application implements ViewInterface {

    public BoardingPanelView() {
        // Constructor vacío
    }

    public void start( @SuppressWarnings("exports") Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/boardingPanel/BoardingPanel.fxml"));

        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Panel de Abordaje");
        primaryStage.setMaximized(false);
        primaryStage.setMaximized(true);
        primaryStage.show();

    }
}
