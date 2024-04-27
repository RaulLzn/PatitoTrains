package patitotrains.controller.routeManagementController;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import patitotrains.model.Managers.LoggerManager;
import patitotrains.model.Managers.RouteManager;
import patitotrains.model.domain.Route;
import patitotrains.model.domain.Station;
import patitotrains.model.domain.Train;
import patitotrains.view.routeManagementView.SearchRouteView;
import raul.Model.linkedlist.doubly.circular.LinkedList;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class RegisterRouteController implements Initializable{

    @FXML
    private Button btnAddStation;

    @FXML
    private Button btnGoBack;

    @FXML
    private Button btnRegister;

    @FXML
    private ComboBox<String> cmbBoxAddStation;

    @FXML
    private ComboBox<String> cmbBoxTrain;
    @FXML
    private TableView<Station> tableStations;

    @FXML
    private TableColumn<Station, Button> columnDelete;

    @FXML
    private TableColumn<Station, String> columnIdStation;

    @FXML
    private TableColumn<Station, String> columnName;

    @FXML
    private Label labelMessage;

    @FXML
    private Label lblAssignedData;

    @FXML
    private SplitPane spl1;

    

    @FXML
    private TextField txtFieldDepartureHour;

    @FXML
    private TextField txtFieldDepartureMinute;

    @FXML
    private TextField txtFieldName;

    private RouteManager routeManager;

    private LinkedList<Station> stopStations;



    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        cmbBoxAddStation.getItems().addAll(routeManager.getStationsNames());
        cmbBoxTrain.getItems().addAll(routeManager.getTrainsNames());
    }

    public RegisterRouteController(RouteManager routeManager){
        this.routeManager = routeManager;
        stopStations = new LinkedList<>();
    }

    public RegisterRouteController() throws NotBoundException, RemoteException {
        routeManager = new RouteManager();
        stopStations = new LinkedList<>();
    }


    @FXML
    void btnGoBackClicked(ActionEvent event) throws IOException {
        SearchRouteView searchRouteView = new SearchRouteView();
        searchRouteView.start(event, routeManager);

    }
    @FXML
    void btnRegisterClicked(ActionEvent event) throws NotBoundException, RemoteException {
        String message = "";
        boolean valuesFormatOk;
        boolean toAdd = false;

        String name;
        Train train;
        int departureHour;
        int departureMinute;

        //Validate Format
        valuesFormatOk = validateFormat();

        //Domain Validation
        if(valuesFormatOk){
            toAdd = true;
            name = txtFieldName.getText();
            train = getSelectedTrain();
            departureHour = getDepartureHour();
            departureMinute = getDepartureMinute();

            if(!routeManager.validateStopStations(stopStations)){
                toAdd = false;
                message = "Ingrese las estaciones de parada en un orden valido.";
            }

            if(!routeManager.validateMinute(departureMinute)){
                toAdd = false;
                message = "Ingrese un valor de minuto valido.";
                
            }

            if(!routeManager.validateHour(departureHour)){
                toAdd = false;
                message = "Ingrese un valor de hora valido.";
            }

            if(routeManager.anyRouteHasTrain(train.getName())){
                toAdd = false;
                message = "Ya alguna ruta utiliza el tren seleccionado.";
            }
            if(train.isDisabled()){
                toAdd = false;
                message = "El tren seleccionado esta deshabilitado.";
            }

            if(routeManager.existRouteByName(name)){
                toAdd = false;
                message = "Ya existe una ruta con ese nombre.";
            }

            if(toAdd){
                Route route = routeManager.constructRoute(name, train, departureHour, departureMinute, stopStations);
                if(routeManager.addRoute(route)){
                    String dataMessage = "ID: " + route.getId() + " Hora de llegada: " + 
                    route.getArrivalTime().toString() + " Distancia:"  + route.getRouteDistance() + " km";

                    lblAssignedData.setText(dataMessage);
                    message = "Ruta " + route.getName() + " exitosamente registrada.";
                    LoggerManager log = new LoggerManager();
                    log.logAction("Se ha registrado la ruta " + route.getName() + " con id " + route.getId() );
                    clear();;

                }else{
                    message = "No fue posible añadir la ruta";
                }
            }
            labelMessage.setText(message);
        }
        
           


    }
    
    @FXML
    void btnAddStationClicked(ActionEvent event) {
        boolean toCheck = validateCmbBox(cmbBoxAddStation);
        boolean toAdd = true;
        String message;

        if(toCheck){
            Station selectedStation = getSelectedStation();
            if(selectedStation != null){
                
                if(!stopStations.isEmpty()){
                    if(selectedStation.equals(stopStations.peekLast()) ){
                        message = "Seleccione una estacion diferente a la ultima en ser añadida.";
                        labelMessage.setText(message);
                        toAdd = false;
                    }
                   
                }
                
                if(toAdd){
                   

                    stopStations.add(selectedStation);
                    
                    setColumnsValues();
                    message = "Estacion " + selectedStation.getName() + " añadida a la ruta";
                    labelMessage.setText(message);
                }
            }

        }


    }

    private void clear(){
        txtFieldName.clear();;
        txtFieldDepartureHour.clear();
        txtFieldDepartureMinute.clear();
        cmbBoxAddStation.getSelectionModel().clearSelection();
        cmbBoxTrain.getSelectionModel().clearSelection();
        tableStations.getItems().clear();
        stopStations = new LinkedList<>();



    }

    private boolean validateFormat(){
        String message;

        if(!validateTxtName()){
            message = "Ingrese un valor en el apartado de nombre.";
            labelMessage.setText(message);
            return false;
        }
        if(!validateCmbBox(cmbBoxTrain)){
            message = "Seleccion un tren para la ruta.";
            labelMessage.setText(message);
            return false;
        }
        if(!validateTxtHour()){
            message = "Ingrese un valor en el apartado de hora de partida.";
            labelMessage.setText(message);
            return false;
        }

        if(!validateTxtMinute()){
            message = "Ingrese un valor en el apartado de minuto de partida.";
            labelMessage.setText(message);
            return false;
        }

        
        
        if(!validateAddedStations()){
            message = "Ingrese al menos dos estaciones.";
            labelMessage.setText(message);
            return false;
        }
        return true;

    }

    private boolean validateAddedStations(){
        if(stopStations.size() < 2){
            return false;
        }
        return true;


    }

   
  
    private boolean validateCmbBox(ComboBox<String> cmbBox){

        if(cmbBox.getSelectionModel().getSelectedItem() == null){
            return false;
        }

        return true;


    }

    private boolean validateTxtHour(){
        
        if(txtFieldDepartureHour.getText().isBlank()){
            return false;
        }
    
        try {
            Integer.parseInt(txtFieldDepartureHour.getText());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }

    }
    private boolean validateTxtMinute(){
        
        if(txtFieldDepartureMinute.getText().isBlank()){
            return false;
        }
    
        try {
            Integer.parseInt(txtFieldDepartureMinute.getText());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }

    }
    private boolean validateTxtName(){
        
        if(txtFieldName.getText().isBlank()){
            return false;
        }
        
        return true;
    }
    private int getDepartureHour(){
        return Integer.parseInt(txtFieldDepartureHour.getText());
    }
    private int getDepartureMinute(){
        return Integer.parseInt(txtFieldDepartureMinute.getText());
    }
    private Station getSelectedStation(){
        return routeManager.getStationByName(cmbBoxAddStation.getSelectionModel().getSelectedItem());
    }
    private Train getSelectedTrain(){
        return routeManager.getTrainByName(cmbBoxTrain.getSelectionModel().getSelectedItem());
    }

    private void setColumnsValues() {
        ObservableList<Station> stationsObservableList;
        stationsObservableList = FXCollections.observableArrayList();
        stationsObservableList.setAll(routeManager.stationsArray(stopStations));
        tableStations.setItems(stationsObservableList);

        columnIdStation.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnDelete.setCellValueFactory(cellData -> {
            Button buttonEdit;
    
            buttonEdit = new Button();
            buttonEdit.setText("Eliminar");

            buttonEdit.setOnAction(event -> {
                @SuppressWarnings("unchecked")
                TableRow<Station> row = (TableRow<Station>) buttonEdit.getParent().getParent();
                int rowIndex = row.getIndex();
                tableStations.getItems().remove(rowIndex);
                stopStations = new LinkedList<>();

                for(int ii = 0; ii < tableStations.getItems().size(); ii++){
                    stopStations.add(tableStations.getItems().get(ii));
                }

                
                
                
        
            });

            return new SimpleObjectProperty<Button>(buttonEdit);

        });
    }
   

}
