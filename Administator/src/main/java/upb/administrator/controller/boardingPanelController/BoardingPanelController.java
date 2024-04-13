package upb.administrator.controller.boardingPanelController;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import upb.administrator.model.Managers.BoardingPanelManager;
import upb.administrator.model.domain.Ticket;

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

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        boardingPanelManager = new BoardingPanelManager();
        setValues();
    }

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
           
            return new SimpleStringProperty( cellData.getValue().getRoute().getTrain().getName());

        });
        columnTrainId.setCellValueFactory(cellData -> {
           
            return new SimpleStringProperty( cellData.getValue().getRoute().getTrain().getId());

        });


    }

}
