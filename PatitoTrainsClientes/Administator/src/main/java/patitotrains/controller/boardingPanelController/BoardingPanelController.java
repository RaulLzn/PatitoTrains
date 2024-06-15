package patitotrains.controller.boardingPanelController;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import patitotrains.model.Managers.BoardingPanelManager;
import patitotrains.model.domain.Ticket;

import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

/**
 * Clase que controla la vista de la tabla de abordaje
 */
public class BoardingPanelController implements Initializable{
    
    @FXML
    private TableColumn<Ticket, String> columnId;
    @FXML
    private TableColumn<Ticket, String> columnLastName;

    @FXML
    private TableColumn<Ticket, String> columnName;

    @FXML
    private TableColumn<Ticket, String> columnSeatType;

    @FXML
    private TableColumn<Ticket, String> columnTrain;

    @FXML
    private TableColumn<Ticket, String> columnTrainId;

    @FXML
    private TableView<Ticket> tableTickets;

    BoardingPanelManager boardingPanelManager;

    /**
     * Método que inicializa la vista
     * @param arg0 URL
     * @param arg1 ResourceBundle
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        try {
            boardingPanelManager = new BoardingPanelManager();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        } catch (NotBoundException e) {
            throw new RuntimeException(e);
        }
        setValues();
        configureColumnValueFactories(); // Llamamos al nuevo método para configurar las propiedades de celda
    }

    /**
     * Método que configura las propiedades de celda
     */
    private void configureColumnValueFactories() {
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPassenger().getNames()));
        columnLastName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPassenger().getLastNames()));
        columnSeatType.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSeatType().getDescription()));
        columnTrain.setCellValueFactory(cellData -> {
            Ticket ticket = cellData.getValue();
            if (ticket.getRoute().getTrains().isEmpty()) {
                return new SimpleStringProperty("No train available");
            } else {
                return new SimpleStringProperty(ticket.getRoute().getTrains().peek().getName());
            }
        });
        columnTrainId.setCellValueFactory(cellData -> {
            Ticket ticket = cellData.getValue();
            if (ticket.getRoute().getTrains().isEmpty()) {
                return new SimpleStringProperty("No train available");
            } else {
                return new SimpleStringProperty(ticket.getRoute().getTrains().peek().getId());
            }
        });
    }

    /**
     * Método que establece los valores de la tabla
     */
    public void setValues(){
        boardingPanelManager.pullData();
        ObservableList<Ticket> ticketObservableList;
        ticketObservableList = FXCollections.observableArrayList();
        ticketObservableList.setAll(boardingPanelManager.getTicketArrayOrdered());
        tableTickets.setItems(ticketObservableList);

        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnName.setCellValueFactory(cellData -> {
           
            return new SimpleStringProperty( cellData.getValue().getPassenger().getNames());

        });
        columnLastName.setCellValueFactory(cellData -> {
           
            return new SimpleStringProperty( cellData.getValue().getPassenger().getLastNames());

        });
        columnSeatType.setCellValueFactory(cellData -> {
           
            return new SimpleStringProperty( cellData.getValue().getSeatType().getDescription());

        });
        columnTrain.setCellValueFactory(cellData -> {
           
            return new SimpleStringProperty( cellData.getValue().getRoute().getTrains().peek().getName());

        });
        columnTrainId.setCellValueFactory(cellData -> {
           
            return new SimpleStringProperty( cellData.getValue().getRoute().getTrains().peek().getId());

        });


    }

}
