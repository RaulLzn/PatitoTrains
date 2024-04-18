package upb.administrator.controller.routeManagementController;

import java.net.URL;
import java.util.ResourceBundle;
import java.io.IOException;

import com.edu.upb.linkedList.doubly.LinkedList;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import upb.administrator.model.Managers.RouteManager;
import upb.administrator.model.domain.Route;
import upb.administrator.view.menuViews.MenuView;
import upb.administrator.view.routeManagementView.EditRouteView;
import upb.administrator.view.routeManagementView.RegisterRouteView;

public class SearchRouteController implements Initializable{

    @FXML
    private Button btnGoBack;

    @FXML
    private Button btnRegisterRoute;

    @FXML
    private TableColumn<Route, String> columnArrivalStation;

    @FXML
    private TableColumn<Route, String> columnDepartureStation;

    @FXML
    private TableColumn<Route, Button> columnEdit;

    @FXML
    private TableColumn<Route, String> columnId;

    @FXML
    private TableColumn<Route, String> columnIdTrain;

    @FXML
    private TableColumn<Route, String> columnName;

    @FXML
    private TableColumn<Route, String> columnState;

    @FXML
    private TableColumn<Route, String> columnTrainName;

    @FXML
    private TableView<Route> tableRoutes;

    @FXML
    private TextField txtFieldSearch;

    private RouteManager routeManager;

    public SearchRouteController(){
        routeManager = new RouteManager();
        routeManager.pullData();
    }

    public SearchRouteController(RouteManager routeManager){
        this.routeManager = routeManager;

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        ObservableList<Route> routesObservableList;


        routesObservableList = FXCollections.observableArrayList();
        
        txtFieldSearch.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                String input = txtFieldSearch.getText();
          
                LinkedList<Route> newRouteList = routeManager.searchRoutes(input);
                routesObservableList.setAll( routeManager.routeArray(newRouteList));
                tableRoutes.setItems(routesObservableList);
                
            }
        });

        routesObservableList.setAll(routeManager.routeArray());
        tableRoutes.setItems(routesObservableList);
        setColumnsValues();
       
    }

    private void setColumnsValues() {

        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnTrainName.setCellValueFactory(cellData -> {
         
            return new SimpleStringProperty( cellData.getValue().getTrains().peek().getName());

        });
        columnIdTrain.setCellValueFactory(cellData -> {
         
            return new SimpleStringProperty( cellData.getValue().getTrains().peek().getId());

        });
        columnArrivalStation.setCellValueFactory(cellData -> {
         
            return new SimpleStringProperty( cellData.getValue().getStations().peekLast().getName());

        });
        columnDepartureStation.setCellValueFactory(cellData -> {
         
            return new SimpleStringProperty( cellData.getValue().getStations().peek().getName());

        });
        columnState.setCellValueFactory(cellData -> {
            boolean disabled = cellData.getValue().isDisabled();
            String disabledAsString;

            if(disabled){
                disabledAsString = "Deshabilitado";
            }else{
                disabledAsString = "Habilitado";
            }

            return new SimpleStringProperty(disabledAsString);

        });

         
        columnEdit.setCellValueFactory(cellData -> {
            Button buttonEdit;
    
            buttonEdit = new Button();
            buttonEdit.setText("Editar");

            buttonEdit.setOnAction(event -> {

                try {
                    EditRouteView editView = new EditRouteView();
                    editView.start(event, routeManager, cellData.getValue());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                
        
            });

            return new SimpleObjectProperty<Button>(buttonEdit);

        });
        
    }

    @FXML
    void btnGoBackClicked(ActionEvent event) throws Exception {
        MenuView viewMenu = new MenuView();
        viewMenu.start(event);
    }

    @FXML
    void btnRegisterRouteClicked(ActionEvent event) throws Exception {
        RegisterRouteView registerRouteView = new RegisterRouteView();
        registerRouteView.start(event, routeManager); // cambiar a solo event
    }

    

}

