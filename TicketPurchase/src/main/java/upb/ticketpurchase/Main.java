package upb.ticketpurchase;

import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;
import upb.ticketpurchase.view.WelcomePageView;

public class Main extends Application{
    WelcomePageView app = new WelcomePageView();
    public static void main(String[] args) throws IOException {
        
        Application.launch(args);

    }

    @Override
    public void start(@SuppressWarnings("exports") Stage arg0) throws Exception {
       app.start(arg0);
    }
    
}
