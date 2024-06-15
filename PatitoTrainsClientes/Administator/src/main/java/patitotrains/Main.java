package patitotrains;

import javafx.application.Application;
import javafx.stage.Stage;
import patitotrains.view.boardingPanelView.BoardingPanelView;
import patitotrains.view.loginView.LoginView;

import java.io.IOException;

public class Main extends Application{
    BoardingPanelView boradingApp = new BoardingPanelView();
    LoginView loginApp = new LoginView();
    public static void main(String[] args) throws IOException {
        
        Application.launch(args);
    }

    @Override
    public void start(@SuppressWarnings("exports") Stage arg0) throws Exception {
        Stage stage = new Stage();
        boradingApp.start(stage);
        loginApp.start(arg0);
       
    }
    
}
