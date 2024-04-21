package upb.ticketpurchase.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.edu.upb.array.Array;
import com.edu.upb.linkedList.doubly.LinkedList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SplitPane;
import javafx.util.StringConverter;
import upb.ticketpurchase.model.domain.Lugagge;
import upb.ticketpurchase.model.domain.Passenger;
import upb.ticketpurchase.model.domain.Route;
import upb.ticketpurchase.model.domain.Station;
import upb.ticketpurchase.model.domain.Ticket;
import upb.ticketpurchase.model.domain.Train;
import upb.ticketpurchase.model.domain.types.SeatType;
import upb.ticketpurchase.model.manager.PurchaseManager;
import upb.ticketpurchase.view.PassengerDataView;
import upb.ticketpurchase.view.TicketView;

public class RouteDataController implements Initializable{

    @FXML
    private Button btnCalculate;

    @FXML
    private Button btnGenerateTicket;

    @FXML
    private Button btnGoBack;

    @FXML
    private ComboBox<Station> cmbBoxArrivalStation;

    @FXML
    private ComboBox<Station> cmbBoxDepartureStation;

    @FXML
    private ComboBox<Route> cmbBoxRoute;

    @FXML
    private ComboBox<SeatType> cmbBoxSeatType;

    @FXML
    private Label lblMessage;

    @FXML
    private Label lblValue;

    @FXML
    private ListView<String> listStations;

    @FXML
    private ListView<String> listTrains;

    @FXML
    private RadioButton radBtnPersoRoute;

    @FXML
    private RadioButton radBtnPreRoute;

    @FXML
    private SplitPane spl1;
    @FXML
    private SplitPane splArrival;

    @FXML
    private SplitPane splDeparture;

    @FXML
    private SplitPane splRoute;

    @FXML
    private SplitPane splActions;


    private PurchaseManager purchaseManager;

    private Passenger passenger;

    private Array<Double> weight;


    public RouteDataController(@SuppressWarnings("exports") Passenger passenger, Array<Double> weight){
        this.passenger = passenger;
        this.weight = weight;
        purchaseManager = new PurchaseManager();
        purchaseManager.pullRoutes();
        purchaseManager.pullSeatTypes();

    }
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        setComboBoxArrival();
        setComboBoxDeparture();
        setComboBoxRoutes();
        setComboBoxSeatType();
        splActions.getItems().remove(splRoute);
        splActions.getItems().remove(splArrival);
        splActions.getItems().remove(splDeparture);
        preRouteMode();
        radBtnPreRoute.setSelected(true);
        
    }
    @FXML
    void btnGenerateTicketClicked(ActionEvent event) throws IOException {
        
        if(radBtnPreRoute.isSelected()){
           
            generatePreTicket(event);

        }else{  
            generatePersoTicket(event);

        }


     }
        
     
    private void generatePersoTicket(Event event) throws IOException{
        boolean valuesOk;

        valuesOk = validatePersoRoute();

        if(valuesOk){
            Array<Lugagge> luggages = new Array<>(2);
            Route route = new Route("PersonalRoute", "PersonalRoute", 
            null, null, null, null, 0);

            LinkedList<Station> stations = new LinkedList<>();
            Array<Station> personalPath = purchaseManager.getPersonalPath(cmbBoxDepartureStation.getValue(), cmbBoxArrivalStation.getValue());
            stations.add( personalPath);
            route.setStations(stations);
            route.setRouteDistance(purchaseManager.calPersoPathDistance(personalPath));

            LinkedList<String> trainNames = new LinkedList<>();
            trainNames.add( purchaseManager.getNamesTrainsPersonalPath(personalPath));
            LinkedList<Train> trains = purchaseManager.getTrainsByNames(trainNames);
            route.setTrains(trains);

            route.setDepartureTime(purchaseManager.getDepartureTimeRouteByTrain(trains.peek()));
            route.setArrivalTime(purchaseManager.getArrivalTimeRouteByTrain(trains.peekLast()));


            Ticket ticket = new Ticket( purchaseManager.ticketId(),
             passenger, calPersonalValue(), cmbBoxSeatType.getValue(), route);

            if(weight.get(0) != null){
                luggages.add( new Lugagge("N/A", weight.get(0), ticket.getId()));
            }    
            if(weight.get(1) != null){
                luggages.add(new Lugagge("N/A", weight.get(1), ticket.getId()));
            }  
              
            TicketView ticketView = new TicketView();
            ticketView.start(event, ticket, luggages);

        }

    }


    

    private void generatePreTicket(Event event) throws IOException{
        boolean valuesOk;

        valuesOk = validatePreRoute();

        if(valuesOk){
            Array<Lugagge> luggages = new Array<>(2);
            Route route = cmbBoxRoute.getValue();
            Ticket ticket = new Ticket( purchaseManager.ticketId(),
             passenger, calPreValue(), cmbBoxSeatType.getValue(), route);

            if(weight.get(0) != null){
                luggages.add( new Lugagge("N/A", weight.get(0), ticket.getId()));
            }    
            if(weight.get(1) != null){
                luggages.add(new Lugagge("N/A", weight.get(1), ticket.getId()));
            }  
              
            TicketView ticketView = new TicketView();
            ticketView.start(event, ticket, luggages);

        }

    }

    @FXML
    void btnGoBackClicked(ActionEvent event) throws IOException {
        PassengerDataView passengerDataView = new PassengerDataView();
        passengerDataView.start(event);

    }

    @FXML
    void radBtnPreRouteClicked(ActionEvent event) {
        preRouteMode();
        radBtnPersoRoute.setSelected(false);

    }

    @FXML
    void radBtnPersoRouteClicked(ActionEvent event) {
        persoRouteMode();
        radBtnPreRoute.setSelected(false);
    }

    @FXML
    void btnCalculateClicked(ActionEvent event) {
        boolean valuesOk;
        if(radBtnPreRoute.isSelected()){
             valuesOk = validatePreRoute();

            if(valuesOk){
                
                lblValue.setText(Double.toString(calPreValue()));
    
            }
    
        }else{
            valuesOk = validatePersoRoute();

            if(valuesOk){
                lblValue.setText(Double.toString(calPersonalValue()));
            }


        }
       
    }

    //Cal

    private double calPreValue(){
        SeatType seatType = cmbBoxSeatType.getValue();
        Route route = cmbBoxRoute.getValue();
        double value = route.getRouteDistance() *seatType.getCostPerKm();
        return value;
    }

    private double calPersonalValue(){
        Station departure = cmbBoxDepartureStation.getValue();
        SeatType seatType = cmbBoxSeatType.getValue();
        Station arrival = cmbBoxArrivalStation.getValue();
        Array<Station> path = purchaseManager.getPersonalPath(departure, arrival);
        double distance = purchaseManager.calPersoPathDistance(path);
        double value = distance * seatType.getCostPerKm();
        return value;

    }

    //Validation
    private boolean validatePreRoute(){
        String message;

        if(!validateCmbBox(cmbBoxRoute)){
            message = "Seleccione una ruta.";
            lblMessage.setText(message);
            return false;
        }

        if(!validateCmbBox(cmbBoxSeatType)){
            message = "Seleccione un tipo de asiento.";
            lblMessage.setText(message);
            return false;
        }
        return true;

    }

    private boolean validatePersoRoute(){
        String message;

        if(!validateCmbBox(cmbBoxDepartureStation)){
            message = "Seleccione una estación de partida.";
            lblMessage.setText(message);
            return false;
        }

        if(!validateCmbBox(cmbBoxArrivalStation)){
            message = "Seleccione una estación de llegada.";
            lblMessage.setText(message);
            return false;
        }

        if(!validateCmbBox(cmbBoxSeatType)){
            message = "Seleccione un tipo de asiento.";
            lblMessage.setText(message);
            return false;
        }

        if(!validateStationsPersoRoute()){
            return false;
        }
        return true;

    }

  
    private boolean validateCmbBox(ComboBox<?> cmbBox){

        if(cmbBox.getSelectionModel().getSelectedItem() == null){
            return false;
        }

        return true;


    }
    private boolean validateStationsPersoRoute(){
        Station departure = cmbBoxDepartureStation.getValue();
        Station arrival = cmbBoxArrivalStation.getValue();
        Array<Station> path = purchaseManager.getPersonalPath(departure, arrival);
        String message = "";


        if(departure.equals(arrival)){
            message = "Ingrese estaciones distintas en los campo de estación de partida y de llegada.";
            lblMessage.setText(message);
            return false;
        }

        if(!path.get(path.size() - 1).equals(arrival)){
            message = "No es posible generar la ruta deseada";
            lblMessage.setText(message);
            return false;
        }
        lblMessage.setText("");
        return true;
        
    }

  

    // Predeterminated route mode
    private void preRouteMode(){
        splActions.getItems().add(splRoute);
        splActions.getItems().remove(splArrival);
        splActions.getItems().remove(splDeparture);
        listStations.getItems().clear();
        listTrains.getItems().clear();

    }
    //PersonalRouteMode
    private void persoRouteMode(){
        splActions.getItems().remove(splRoute);
        splActions.getItems().add(splDeparture);
        splActions.getItems().add(splArrival);
        listStations.getItems().clear();
        listTrains.getItems().clear();

    }

   
    private void setTableValuesPersoMode(){
        listStations.getItems().clear();
        listTrains.getItems().clear();

        Station departure = cmbBoxDepartureStation.getValue();
        Station arrival = cmbBoxArrivalStation.getValue();
        Array<Station> path = purchaseManager.getPersonalPath(departure, arrival);
        LinkedList<Station> pathList = new LinkedList<>();


        if(validateStationsPersoRoute()){

            for(int ii = 0; ii < path.size(); ii++){
                	
                pathList.add( path.get(ii));

            }
            listStations.getItems().setAll(purchaseManager.stationsArrayName(pathList));
            listTrains.getItems().setAll(purchaseManager.getNamesTrainsPersonalPath(path));
        

          
        }
        
    }


    //ComboBoxes Configuration

    private void setComboBoxRoutes(){

        cmbBoxRoute.getItems().addAll(purchaseManager.routeArray());

        cmbBoxRoute.setCellFactory(param -> new ListCell<>() {
        @Override
        protected void updateItem(Route item, boolean empty) {
            super.updateItem(item, empty);

            if (empty || item == null) {
                setText(null);
            } else {
                setText(item.getName()); 
            }
        }
    });

    
    cmbBoxRoute.setConverter(new StringConverter<Route>() {

        @Override
        public Route fromString(String arg0) {
            return null;
        }

        @Override
        public String toString(Route item) {
            if (item == null) {
                return null;
            } else {
                return item.getName(); 
            }
        }
        
    });

    cmbBoxRoute.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
        if(radBtnPreRoute.isSelected()){
            if(newValue != null){
                Route route = newValue;
                
                ObservableList<String> stationsNamesList;
                stationsNamesList = FXCollections.observableArrayList();
                stationsNamesList.setAll(purchaseManager.stationsArrayName(route.getStations()));
                listStations.setItems(stationsNamesList);

                ObservableList<String> trainsList;
                trainsList = FXCollections.observableArrayList();
                trainsList.add(route.getTrains().peek().getName());
                listTrains.setItems(trainsList);
                lblValue.setText("");

            }else{
                listStations.getItems().clear();
                listTrains.getItems().clear();
            }



        }
    });


    }

    private void setComboBoxDeparture(){

        cmbBoxDepartureStation.getItems().addAll(purchaseManager.stationsArray());

        cmbBoxDepartureStation.setCellFactory(param -> new ListCell<>() {
        @Override
        protected void updateItem(Station item, boolean empty) {
            super.updateItem(item, empty);

            if (empty || item == null) {
                setText(null);
            } else {
                setText(item.getName()); 
            }
        }
    });

    
    cmbBoxDepartureStation.setConverter(new StringConverter<Station>() {

        @Override
        public Station fromString(String arg0) {
            return null;
        }

        @Override
        public String toString(Station arg0) {
            if (arg0 == null) {
                return null;
            } else {
                return arg0.getName(); 
            }
        }
        
    });

    cmbBoxDepartureStation.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> { 

        if(radBtnPersoRoute.isSelected()){
            
            if(newValue != null && cmbBoxArrivalStation.getValue() != null){
                setTableValuesPersoMode();
                
            }
        }


        lblValue.setText("");
        
    });


    }   
    private void setComboBoxArrival(){

        cmbBoxArrivalStation.getItems().addAll(purchaseManager.stationsArray());

        cmbBoxArrivalStation.setCellFactory(param -> new ListCell<>() {
        @Override
        protected void updateItem(Station item, boolean empty) {
            super.updateItem(item, empty);

            if (empty || item == null) {
                setText(null);
            } else {
                setText(item.getName()); 
            }
        }
    });

    
    cmbBoxArrivalStation.setConverter(new StringConverter<Station>() {

        @Override
        public Station fromString(String arg0) {
            return null;
        }

        @Override
        public String toString(Station arg0) {
            if (arg0 == null) {
                return null;
            } else {
                return arg0.getName(); 
            }
        }
        
    });

    cmbBoxArrivalStation.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> { 

        if(radBtnPersoRoute.isSelected()){
            
            if(newValue != null && cmbBoxDepartureStation.getValue() != null){
                setTableValuesPersoMode();
                
            }
        }


        lblValue.setText("");
        
    });

    } 
    
    public void setComboBoxSeatType(){
        cmbBoxSeatType.getItems().addAll(purchaseManager.seatTypeArray());

        cmbBoxSeatType.setCellFactory(param -> new ListCell<>() {
        @Override
        protected void updateItem(SeatType item, boolean empty) {
            super.updateItem(item, empty);

            if (empty || item == null) {
                setText(null);
            } else {
                setText(item.getDescription()); 
            }
        }
    });

    
    cmbBoxSeatType.setConverter(new StringConverter<SeatType>() {

        @Override
        public SeatType fromString(String arg0) {
            return null;
        }

        @Override
        public String toString(SeatType arg0) {
            if (arg0 == null) {
                return null;
            } else {
                return arg0.getDescription(); 
            }
        }
        
    });
    cmbBoxSeatType.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> { 
        lblValue.setText("");
        
    });
    } 
    

}

