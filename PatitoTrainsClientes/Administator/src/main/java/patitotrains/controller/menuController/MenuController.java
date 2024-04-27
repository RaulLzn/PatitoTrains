package patitotrains.controller.menuController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import patitotrains.view.loginView.LoginView;
import patitotrains.view.routeManagementView.SearchRouteView;
import patitotrains.view.trainManagementViews.SearchTrainView;
import patitotrains.view.userManagementViews.SearchUserView;

public class MenuController {

    @FXML
    private Button btnBack;

    @FXML
    private AnchorPane paneTrainManagement;

    @FXML
    private AnchorPane panelRouteManagement;

    @FXML
    private AnchorPane panelUserManagement;


    public MenuController(){

    }

    
    @FXML
    void btnBackClicked(ActionEvent event) throws Exception {
        LoginView loginView = new LoginView();
        loginView.start(event);
    }


    @FXML
    void panelRouteManagementClicked(MouseEvent event) throws Exception {
        SearchRouteView viewRoute = new SearchRouteView();
        viewRoute.start( event);

    }

    @FXML
    void panelTrainManagementClicked(MouseEvent event) throws Exception {
        SearchTrainView viewTrain = new SearchTrainView();
        viewTrain.start( event);

    }

    @FXML
    void panelUserManagementClicked(MouseEvent event) throws Exception {
        SearchUserView viewUser = new SearchUserView();
        viewUser.start( event);
    }

}
