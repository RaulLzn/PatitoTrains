package patitotrains.controller.routeManagementController;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import raul.Model.linkedlist.doubly.circular.LinkedList;
import raul.Model.util.Iterator.Iterator;
import patitotrains.model.Managers.RouteManager;
import patitotrains.model.domain.Route;
import patitotrains.model.domain.Station;
import patitotrains.model.domain.Train;
import patitotrains.view.routeManagementView.SearchRouteView;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class    EditRouteController implements Initializable{

    @FXML
    private BorderPane brdPaneApply;

    @FXML
    private BorderPane brdPaneCancel;

    @FXML
    private BorderPane brdPaneEdit;

    @FXML
    private Button btnAddStation;

    @FXML
    private Button btnApply;

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnGoBack;

    @FXML
    private ComboBox<String> cmbBoxStation;

    @FXML
    private ComboBox<String> cmbBoxTrain;

    @FXML
    private TableColumn<Station, Button> columnDelete;

    @FXML
    private TableColumn<Station, String> columnId;

    @FXML
    private TableColumn<Station, String> columnName;
    
    @FXML
    private TableView<Station> tableStations;

    @FXML
    private Label labelMessage;

    @FXML
    private Label lblArrivalHour;

    @FXML
    private Label lblArrivalMinute;

    @FXML
    private Label lblAssignedData;

    @FXML
    private Label lblFieldDistance;

    @FXML
    private Label lblFieldId;

    @FXML
    private RadioButton radBtnDisable;

    @FXML
    private RadioButton radBtnEnable;

    @FXML
    private SplitPane spl1;

    @FXML
    private SplitPane spltPaneActions;


    @FXML
    private TextField txtFieldDepartureHour;

    @FXML
    private TextField txtFieldDepartureMinute;

    @FXML
    private TextField txtFieldName;

    private RouteManager routeManager;

    private Route route;

    private LinkedList<Station> tempStations;
    
    public EditRouteController(RouteManager routeManager, Route route){
        this.routeManager = routeManager;
        this.route = route;
        tempStations = new LinkedList<>();
        tempStations.add(route.getStations());
    }

    public EditRouteController(Route route) throws NotBoundException, RemoteException {
        routeManager = new RouteManager();
        this.route = route;
        tempStations = new LinkedList<>();
        tempStations.add(route.getStations());
    }


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        cmbBoxStation.getItems().addAll(routeManager.getStationsNames());
        cmbBoxTrain.getItems().addAll(routeManager.getTrainsNames());
        spltPaneActions.getItems().remove(brdPaneCancel);
        spltPaneActions.getItems().remove(brdPaneApply);
        setValues();

        
    }

    @FXML
    void btnAddStationClicked(ActionEvent event) {
        boolean toCheck = validateCmbBox(cmbBoxStation);
        boolean toAdd = true;
        String message;

        if(toCheck){
            Station selectedStation = getSelectedStation();
            if(selectedStation != null){
                
                if(!tempStations.isEmpty()){
                    if(selectedStation.equals(tempStations.peekLast()) ){
                        message = "Seleccione una estacion diferente a la ultima en ser añadida.";
                        labelMessage.setText(message);
                        toAdd = false;
                    }
                   
                }
                
                if(toAdd){
                   

                    tempStations.add(selectedStation);
                    
                    setColumnsValues();
                    message = "Estacion " + selectedStation.getName() + " añadida a la ruta";
                    labelMessage.setText(message);
                }
            }

        }
    }


    @FXML
    void btnApplyClicked(ActionEvent event) {
        String message = "";
        boolean valuesFormatOk;
        boolean toEdit = false;


        String name;
        Train train;
        int departureHour;
        int departureMinute;
        boolean status;
        //Validate Format
        valuesFormatOk = validateFormat();

        //Domain Validation
        if(valuesFormatOk){
            toEdit = true;
            name = txtFieldName.getText();
            train = getSelectedTrain();
            departureHour = getDepartureHour();
            departureMinute = getDepartureMinute();
            status = getStatus();

            if(!routeManager.validateStopStations(tempStations)){
                toEdit = false;
                message = "Ingrese las estaciones de parada en un orden valido.";
            }

            if(!routeManager.validateMinute(departureMinute)){
                toEdit = false;
                message = "Ingrese un valor de minuto valido.";
                
            }

            if(!routeManager.validateHour(departureHour)){
                toEdit = false;
                message = "Ingrese un valor de hora valido.";
            }

            if(!train.getId().equals(route.getTrains().peek().getId())){
                if(routeManager.anyRouteHasTrain(train.getName())){
                    toEdit = false;
                    message = "Ya alguna ruta utiliza el tren seleccionado.";
                }
            }
            
            if(train.isDisabled()){
                toEdit = false;
                message = "El tren seleccionado esta deshabilitado.";
            }

            if(!name.equals(route.getName())){
                if(routeManager.existRouteByName(name)){
                    toEdit = false;
                    message = "Ya existe una ruta con ese nombre.";
                }
            }
            

            if(name.equals(route.getName()) && train.equals(route.getTrains().peek()) 
            && departureHour == route.getDepartureTime().getHour()
            && departureMinute == route.getDepartureTime().getMinute() && status == route.isDisabled() 
            && !haveChangeStations()
           ){
                toEdit = false;
                message = "No ha editado ningun campo.";

            }

            if(toEdit){
                Route newRoute = routeManager.constructRoute(name, train, departureHour, departureMinute, tempStations);
                newRoute.setId(route.getId());
                newRoute.setDisabled(status);

                if(routeManager.editRoute(route, newRoute)){
                    route = newRoute;
                    String dataMessage = "ID: " + route.getId() + " Hora de llegada: " + 
                    route.getArrivalTime().toString() + " Distancia:"  + route.getRouteDistance() + " km";

                    lblAssignedData.setText(dataMessage);
                    message = "Ruta " + route.getName() + " exitosamente editada.";
                    noEditMode();
                    setValues();

                }else{
                    message = "No fue posible editar la ruta";
                }
            }
            labelMessage.setText(message);
        }
        
    }


    @FXML
    void btnCancelClicked(ActionEvent event) {
        noEditMode();
        setValues();
        tempStations = new LinkedList<>();
        tempStations.add(route.getStations());
    }

    @FXML
    void btnEditClicked(ActionEvent event) {
        if(route.getTrains().peek().isOnJourney()){
            labelMessage.setText("No puede editar la ruta, puesto que en estos momentos esta en viaje.");
        }else{
            editMode();
        }

        
    }

    @FXML
    void btnGoBackClicked(ActionEvent event) throws IOException {
        SearchRouteView searchRouteView = new SearchRouteView();
        searchRouteView.start(event, routeManager);

    }

    @FXML
    void radBtnDisableClicked(ActionEvent event) {
        radBtnEnable.setSelected(false);
    }

    @FXML
    void radBtnEnableClicked(ActionEvent event) {
        radBtnDisable.setSelected(false);
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


    private void setValues(){
        tempStations = new LinkedList<>();
        tempStations.add(route.getStations());
        lblFieldId.setText(route.getId());
        txtFieldName.setText(route.getName());
        txtFieldDepartureHour.setText(Integer.toString(route.getDepartureTime().getHour()));
        txtFieldDepartureMinute.setText(Integer.toString(route.getDepartureTime().getMinute()));
        lblArrivalHour.setText(Integer.toString(route.getArrivalTime().getHour()));
        lblArrivalMinute.setText(Integer.toString(route.getArrivalTime().getMinute()));
        cmbBoxTrain.setValue(route.getTrains().peek().getName());
        lblFieldDistance.setText(Double.toString(route.getRouteDistance()));
        setDisalbeValue();
        setColumnsValues();
        
    }

    private void setDisalbeValue(){
        if(route.isDisabled()){
            radBtnDisable.setSelected(true);
            radBtnEnable.setSelected(false);
        }else{
            radBtnDisable.setSelected(false);
            radBtnEnable.setSelected(true);
        }


    }

    private void noEditMode(){

        spltPaneActions.getItems().remove(brdPaneCancel);
        spltPaneActions.getItems().remove(brdPaneApply);
        spltPaneActions.getItems().add(brdPaneEdit);

       
        txtFieldName.setEditable(false);
        txtFieldDepartureHour.setEditable(false);
        txtFieldDepartureMinute.setEditable(false);
        cmbBoxTrain.setDisable(true);
        cmbBoxStation.setDisable(true);
        btnAddStation.setDisable(true);
        tableStations.setDisable(true);

        radBtnDisable.setDisable(true);
        radBtnEnable.setDisable(true);

    }


    private void editMode(){
        spltPaneActions.getItems().remove(brdPaneEdit);
        spltPaneActions.getItems().add(brdPaneApply);
        spltPaneActions.getItems().add(brdPaneCancel);

        txtFieldName.setEditable(true);
        txtFieldDepartureHour.setEditable(true);
        txtFieldDepartureMinute.setEditable(true);
        cmbBoxTrain.setDisable(false);
        cmbBoxStation.setDisable(false);
        btnAddStation.setDisable(false);
        tableStations.setDisable(false);

        radBtnDisable.setDisable(false);
        radBtnEnable.setDisable(false);
    }

    private void setColumnsValues() {
        tableStations.getItems().clear();
        ObservableList<Station> stationsObservableList;
        stationsObservableList = FXCollections.observableArrayList();
        stationsObservableList.setAll(routeManager.stationsArray(tempStations));
        tableStations.setItems(stationsObservableList);

        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
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
                tempStations = new LinkedList<>();

                for(int ii = 0; ii < tableStations.getItems().size(); ii++){
                    tempStations.add(tableStations.getItems().get(ii));
                }

                
                
                
        
            });

            return new SimpleObjectProperty<Button>(buttonEdit);

        });
    }
    
    private boolean validateAddedStations(){
        if(tempStations.size() < 2){
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
    private boolean haveChangeStations(){
        Iterator<Station> tempIter = tempStations.iterator();
        Iterator<Station> routeIter = route.getStations().iterator();

        if(tempStations.size() != route.getStations().size()){
            return true;
        }
        while(tempIter.hasNext() && routeIter.hasNext()){
            if(!tempIter.next().equals(routeIter.next())){
                return true;
            }
        }

        return false;
    }
    private int getDepartureHour(){
        return Integer.parseInt(txtFieldDepartureHour.getText());
    }
    private int getDepartureMinute(){
        return Integer.parseInt(txtFieldDepartureMinute.getText());
    }
    private Station getSelectedStation(){
        return routeManager.getStationByName(cmbBoxStation.getSelectionModel().getSelectedItem());
    }
    private Train getSelectedTrain(){
        return routeManager.getTrainByName(cmbBoxTrain.getSelectionModel().getSelectedItem());
    }
    private boolean getStatus(){
        if(radBtnDisable.isSelected()){
            return true;
        }else{
            return false;
        }
    }

}

