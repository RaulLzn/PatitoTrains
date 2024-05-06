package patitotrains.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import patitotrains.model.domain.Luggage;
import patitotrains.model.domain.Ticket;
import patitotrains.model.domain.Train;
import patitotrains.view.WelcomePageView;
import raul.Model.array.Array;
import raul.Model.linkedlist.doubly.circular.LinkedList;
import raul.Model.util.Iterator.Iterator;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

/**
 * Controlador de la vista de boleto
 */
public class TicketController implements Initializable {

    private Array<String> wagonids;
    @FXML
    private Button btnFinish;

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

    private Ticket ticket;
    Array<Luggage> luggagesArray;


    /**
     * Constructor de la clase
     * @param ticket Boleto
     * @param luggagesArray Arreglo de equipaje
     * @param wagonids Arreglo de identificadores de vagones
     */
    @SuppressWarnings("exports")
    public TicketController(Ticket ticket, Array<Luggage> luggagesArray, Array<String> wagonids){
        this.ticket = ticket;
        this.luggagesArray = luggagesArray;
        this.wagonids = wagonids;
    }

    /**
     * Método que se ejecuta al dar clic en el botón de finalizar
     * @param event Evento
     * @throws IOException
     */
    @FXML
    void btnFinishClicked(ActionEvent event) throws IOException {
        WelcomePageView welcomePageView = new WelcomePageView();
        welcomePageView.start(event);

    }

    /**
     * Método que inicializa la vista
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        setValues();
        
    }

    /**
     * Método que establece los valores de los elementos de la vista
     */
    private void setValues(){

        lblArrivalTime.setText(ticket.getRoute().getArrivalTime().toString());
        lblDepartureTime.setText(ticket.getRoute().getDepartureTime().toString());
        lblPurchaseDate.setText(localTimeFormat(ticket.getPurchasDateTime()));
        
        lblContactLastName.setText(ticket.getPassenger().getContactPerson().getLastNames());
        lblContactName.setText(ticket.getPassenger().getContactPerson().getNames());
        lblDestiny.setText(ticket.getRoute().getStations().peekLast().getName());
        lblId.setText(ticket.getId());
        
        lblIdTrain.setText(triansIdAsString(ticket.getRoute().getTrains()));
        lblPassengerAddress.setText(ticket.getPassenger().getAddress());
        lblPassengerId.setText(ticket.getPassenger().getId());
        lblPassengerIdType.setText(ticket.getSeatType().getDescription());
        lblPassengerLastName.setText(ticket.getPassenger().getLastNames());
        lblPassengerName.setText(ticket.getPassenger().getNames());
        
        lblSeatType.setText(ticket.getSeatType().getDescription());
        lblValue.setText(Double.toString(ticket.getValue()));

        
        if(luggagesArray.get(0) != null){
            lblLuggageOneId.setText(luggagesArray.get(0).getId());
            lblLuggageOneWeight.setText(Double.toString(luggagesArray.get(0).getWeight()));
            lblLuggageOneContainerId.setText(wagonids.get(0));
        }

        if(luggagesArray.get(1) != null){
            lblLuggageTwoId.setText(luggagesArray.get(1).getId());
            lblLuggageTwoWeight.setText(Double.toString(luggagesArray.get(1).getWeight()));
            lblLuggageTwoContainerId.setText(wagonids.get(1));
        }
        
        String passengerNmrs = "";

        for(int ii = 0; ii < ticket.getPassenger().getNumbers().size(); ii++){
            passengerNmrs += ticket.getPassenger().getNumbers().get(ii) + "\n";
        }
        txtAreaPassengerNumbers.setText(passengerNmrs);

        String contactNmrs = "";

        for(int ii = 0; ii < ticket.getPassenger().getContactPerson().getNumbers().size(); ii++){
            contactNmrs += ticket.getPassenger().getContactPerson().getNumbers().get(ii) + "\n";
        }
        txtAreaContactNumbers.setText(contactNmrs);

    }

    /**
     * Método que convierte una lista de trenes a una cadena de texto
     * @param trains Lista de trenes
     * @return Cadena de texto
     */
    private String triansIdAsString(LinkedList<Train> trains){
        String ret = "";
        Iterator<Train> iter = trains.iterator();
        while(iter.hasNext()){

            ret += iter.next().getId() + "\t";
        }
        return ret;


    }

    /**
     * Método que convierte una fecha a una cadena de texto
     * @param time Fecha
     * @return Cadena de texto
     */
    private String localTimeFormat(LocalDateTime time){
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
