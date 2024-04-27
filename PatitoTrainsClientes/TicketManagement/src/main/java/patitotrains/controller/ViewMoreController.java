package patitotrains.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import raul.Model.array.Array;
import patitotrains.controller.interfaces.ViewMoreControllerInterface;
import patitotrains.model.domain.Luggage;
import patitotrains.model.domain.Ticket;
import patitotrains.model.domain.TicketManager;
import patitotrains.view.SearchTicketView;

import java.net.URL;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class ViewMoreController implements Initializable, ViewMoreControllerInterface {
    
    @FXML
    private Button btnGoBack;

    @FXML
    private Label lblArrivalTime;

    @FXML
    private Label lblContactLastName;

    @FXML
    private Label lblContactName;

    @FXML
    private Label lblDepartureTime;

    @FXML
    private Label lblDestiny;

    @FXML
    private Label lblId;

    @FXML
    private Label lblIdTrain;

    @FXML
    private Label lblLuggageOneContainerId;

    @FXML
    private Label lblLuggageOneId;

    @FXML
    private Label lblLuggageOneWeight;

    @FXML
    private Label lblLuggageTwoContainerId;

    @FXML
    private Label lblLuggageTwoId;

    @FXML
    private Label lblLuggageTwoWeight;

    @FXML
    private Label lblPassengerAddress;

    @FXML
    private Label lblPassengerId;

    @FXML
    private Label lblPassengerIdType;

    @FXML
    private Label lblPassengerLastName;

    @FXML
    private Label lblPassengerName;

    @FXML
    private Label lblPurchaseDate;

    @FXML
    private Label lblSeatType;

    @FXML
    private Label lblValue;

    @FXML
    private SplitPane spl1;

    @FXML
    private TextArea txtAreaContactNumbers;

    @FXML
    private TextArea txtAreaPassengerNumbers;


    Ticket ticket;
    TicketManager ticketManager;

    @FXML
    public void btnGoBackClicked(@SuppressWarnings("exports") ActionEvent event) {
       SearchTicketView searchTicketView = new SearchTicketView();
       try {
        searchTicketView.start(event);
    } catch (Exception e) {
     
    }
       
        
    }

    public ViewMoreController(@SuppressWarnings("exports") Ticket ticket, @SuppressWarnings("exports") TicketManager ticketManager){
        this.ticketManager = ticketManager;
        this.ticket = ticket;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        try {
            setValues();
        } catch (RemoteException e) {
            e.printStackTrace();

        }
    }

    private void setValues() throws RemoteException {

        lblArrivalTime.setText(ticket.getRoute().getArrivalTime().toString());
        lblPurchaseDate.setText(localDateTimeFormat(ticket.getPurchasDateTime()));
        lblDepartureTime.setText(ticket.getRoute().getDepartureTime().toString());
        
        lblContactLastName.setText(ticket.getPassenger().getContactPerson().getLastNames());
        lblContactName.setText(ticket.getPassenger().getContactPerson().getNames());
        lblDestiny.setText(ticket.getRoute().getStations().peekLast().getName());
        lblId.setText(ticket.getId());
        lblIdTrain.setText(ticket.getRoute().getTrains().peek().getId());
        lblPassengerAddress.setText(ticket.getPassenger().getAddress());
        lblPassengerId.setText(ticket.getPassenger().getId());
        lblPassengerIdType.setText(ticket.getSeatType().getDescription());
        lblPassengerLastName.setText(ticket.getPassenger().getLastNames());
        lblPassengerName.setText(ticket.getPassenger().getNames());
        
        lblSeatType.setText(ticket.getSeatType().getDescription());
        lblValue.setText(Double.toString(ticket.getValue()));

        
        Array<Luggage> lugagge = ticketManager.getLugaggeFromTicket(ticket);
        if(lugagge.get(0) != null){
            lblLuggageOneId.setText(lugagge.get(0).getId());
            lblLuggageOneWeight.setText(Double.toString(lugagge.get(0).getWeight()));
            lblLuggageOneContainerId.setText(ticketManager.getWagonIdFromLugagge(lugagge.get(0), ticket.getRoute().getTrains().peek()));
        }

        if(lugagge.get(1) != null){
            lblLuggageTwoId.setText(lugagge.get(1).getId());
            lblLuggageTwoWeight.setText(Double.toString(lugagge.get(1).getWeight()));
            lblLuggageTwoContainerId.setText(ticketManager.getWagonIdFromLugagge(lugagge.get(1), ticket.getRoute().getTrains().peek()));
        }
        
        String passengerNmrs = "";

        for(int ii = 0; ii < ticket.getPassenger().getNumbers().size(); ii++){
            passengerNmrs += ticket.getPassenger().getNumbers().get(ii) + "\n";
        }
        txtAreaPassengerNumbers.setText(passengerNmrs);

        String contactNmrs = "";

        for(int ii = 0; ii < ticket.getPassenger().getNumbers().size(); ii++){
            contactNmrs += ticket.getPassenger().getContactPerson().getNumbers().get(ii) + "\n";
        }
        txtAreaContactNumbers.setText(contactNmrs);

    }

    private String localDateTimeFormat(LocalDateTime time){
        String toRet = "";

        String day = Integer.toString(time.getDayOfMonth());

        String month = Integer.toString(time.getMonthValue());

        String minute = Integer.toString(time.getMinute());

        if(day.length() == 1){
            toRet += "0" + day;

        }else{
            toRet += day;
        }

        if(month.length() == 1){
            toRet += "/0" + month;

        }else{
            toRet+= "/"+ month;
        }

        toRet += "/" + time.getYear();

        toRet += " " + time.getHour() ;

        if(minute.length() == 1){
            toRet += ":0" + minute;

        }else{
            toRet += ":" + minute;
        }

        return toRet;


    }

}