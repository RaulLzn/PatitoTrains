package upb.trainmanagement;

import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;
import upb.trainmanagement.view.SearchTrainView;

public class Main extends Application{
    SearchTrainView app = new SearchTrainView();
    public static void main(String[] args) throws IOException {
        
        Application.launch(args);

    }

    @Override
    public void start(@SuppressWarnings("exports") Stage arg0) throws Exception {
       app.start(arg0);
    }
    
}
