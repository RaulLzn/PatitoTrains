package upb.ticketmanagement.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.edu.upb.linkedList.doubly.LinkedList;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import upb.ticketmanagement.controller.interfaces.SearchTicketControllerInterface;
import upb.ticketmanagement.model.domain.Ticket;
import upb.ticketmanagement.model.domain.TicketManager;

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

    private TicketManager ticketManager;

    public SearchTicketController(@SuppressWarnings("exports") TicketManager ticketManager){
        this.ticketManager = ticketManager;
    }
    public SearchTicketController() {
        ticketManager = new TicketManager();


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

        ticketObservableList.setAll(ticketManager.ticketArray());
        tableTickets.setItems(ticketObservableList);
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
            String type = cellData.getValue().getRoute().getTrain().getId();

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
                

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/ViewMore.fxml"));
                loader.setControllerFactory(controllerClass -> new ViewMoreController(cellData.getValue(),  ticketManager));
                Parent root;
                try {
                    Scene sceneView;
                    root = loader.load();
                    sceneView = new Scene(root);
                    Stage stageView;
                    stageView = (Stage) ((Node)event.getSource()).getScene().getWindow();
                    stageView.setScene(sceneView);
                    stageView.setMaximized(false);
                    stageView.setMaximized(true);
                    stageView.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }  
            });

            return new SimpleObjectProperty<Button>(buttonView);

        });
    }

}
