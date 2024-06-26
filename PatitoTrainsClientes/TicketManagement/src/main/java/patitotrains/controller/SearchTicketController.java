package patitotrains.controller;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import raul.Model.linkedlist.doubly.circular.LinkedList;
import patitotrains.controller.interfaces.SearchTicketControllerInterface;
import patitotrains.model.domain.Ticket;
import patitotrains.model.domain.TicketManager;
import patitotrains.view.ViewMoreView;
import patitotrains.view.loginView.LoginView;

import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class SearchTicketController implements Initializable, SearchTicketControllerInterface {

    @FXML
    private TableView<Ticket> tableTickets;

    @FXML
    private TableColumn<Ticket, String> columnDestiny;

    @FXML
    private TableColumn<Ticket, String> columnId;

    @FXML
    private TableColumn<Ticket, String> columnName;

    @FXML
    private TableColumn<Ticket, String> columnLastName;

    @FXML
    private TableColumn<Ticket, String> columnPassengerId;

    @FXML
    private TableColumn<Ticket, String> columnSeatType;

    @FXML
    private TableColumn<Ticket, String> columnTrainId;

    @FXML
    private TableColumn<Ticket, Button> columnViewMore;

    @FXML
    private TextField txtFieldSearch;

    @FXML
    private Button btnBack;


    private TicketManager ticketManager;

    public SearchTicketController(@SuppressWarnings("exports") TicketManager ticketManager){
        this.ticketManager = ticketManager;
    }
    public SearchTicketController() throws NotBoundException, RemoteException {
        ticketManager = new TicketManager();


    }

    @FXML
    void btnBackClicked(ActionEvent event) throws Exception {
        LoginView loginView = new LoginView();
        loginView.start(event);
        

    }


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        ObservableList<Ticket> ticketObservableList;

        ticketObservableList = FXCollections.observableArrayList();

        txtFieldSearch.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                String input = txtFieldSearch.getText();

                LinkedList<Ticket> newTicketList = ticketManager.searchTickets(input);
                ticketObservableList.setAll(ticketManager.ticketArray(newTicketList));
                tableTickets.setItems(ticketObservableList);

            }
        });

        setColumnsValues();

    }

    private void setColumnsValues() {
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));

        columnName.setCellValueFactory(cellData -> {
            String type = cellData.getValue().getPassenger().getNames();

            return new SimpleStringProperty(type);

        });

        columnLastName.setCellValueFactory(cellData -> {
            String type = cellData.getValue().getPassenger().getLastNames();

            return new SimpleStringProperty(type);

        });

        columnPassengerId.setCellValueFactory(cellData -> {
            String type = cellData.getValue().getPassenger().getId();

            return new SimpleStringProperty(type);

        });

        columnSeatType.setCellValueFactory(cellData -> {
            String type = cellData.getValue().getSeatType().getDescription();

            return new SimpleStringProperty(type);

        });

        columnTrainId.setCellValueFactory(cellData -> {
            String type = cellData.getValue().getRoute().getTrains().peek().getId();

            return new SimpleStringProperty(type);

        });

        columnDestiny.setCellValueFactory(cellData -> {
            String type = cellData.getValue().getRoute().getStations().peekLast().getName();

            return new SimpleStringProperty(type);

        });

        columnViewMore.setCellValueFactory(cellData -> {
            Button buttonView;
    
            buttonView = new Button();
            buttonView.setText("Ver Mas");

            buttonView.setOnAction(event -> {
                ViewMoreView viewMoreView = new ViewMoreView();
                try {
                    viewMoreView.start(event, cellData.getValue(), ticketManager);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            });

            return new SimpleObjectProperty<Button>(buttonView);

        });
    }

}
