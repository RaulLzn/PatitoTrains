package upb.ticketmanagement;

import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;
import upb.ticketmanagement.view.SearchTicketView;

public class Main extends Application{
    SearchTicketView app = new SearchTicketView();
    public static void main(String[] args) throws IOException {
        
        Application.launch(args);

    }

    @Override
    public void start(@SuppressWarnings("exports") Stage arg0) throws Exception {
       app.start(arg0);
    }
    
}
