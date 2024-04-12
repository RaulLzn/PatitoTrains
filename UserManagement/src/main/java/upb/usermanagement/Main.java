package upb.usermanagement;

import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;
import upb.usermanagement.view.SearchUserView;

public class Main extends Application{
    SearchUserView app = new SearchUserView();
    public static void main(String[] args) throws IOException {
        
        Application.launch(args);

    }

    @Override
    public void start(@SuppressWarnings("exports") Stage arg0) throws Exception {
       app.start(arg0);
    }
    
}
