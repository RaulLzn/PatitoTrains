package upb.administrator.view.userManagementViews;
import upb.administrator.controller.userManagementController.SearchUserController;
import upb.administrator.model.Managers.UserManager;
import upb.administrator.view.interfaces.ViewInterface;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.stage.Stage;

public class SearchUserView extends Application implements ViewInterface {
    public SearchUserView() {
        
    }

    public void start(@SuppressWarnings("exports") Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/userManagement/SearchUser.fxml"));
        primaryStage.setMaximized(true);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setMaximized(false);
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    public void start( @SuppressWarnings("exports") Event event, @SuppressWarnings("exports") UserManager userManager) throws IOException{
        Stage stageBack;
        Scene sceneBack;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/userManagement/SearchUser.fxml"));
        loader.setControllerFactory(controllerClass -> new SearchUserController( userManager));
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