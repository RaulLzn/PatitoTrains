package upb.administrator.controller.menuController;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import upb.administrator.view.trainManagementViews.SearchTrainView;
import upb.administrator.view.userManagementViews.SearchUserView;

public class MenuController {

    @FXML
    private AnchorPane paneTrainManagement;

    @FXML
    private AnchorPane panelRouteManagement;

    @FXML
    private AnchorPane panelUserManagement;

    private SearchTrainView viewTrain;

    private SearchUserView viewUser;

    public MenuController(){
        viewTrain = new SearchTrainView();
        viewUser = new SearchUserView();
    }

    @FXML
    void panelRouteManagementClicked(MouseEvent event) {
        

    }

    @FXML
    void panelTrainManagementClicked(MouseEvent event) throws Exception {
        viewTrain.start( event);

    }

    @FXML
    void panelUserManagementClicked(MouseEvent event) throws Exception {
        viewUser.start( event);
    }

}
