package upb.ticketmanagement.controller;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.time.LocalDateTime;
import com.edu.upb.array.Array;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import upb.ticketmanagement.controller.interfaces.ViewMoreControllerInterface;
import upb.ticketmanagement.model.domain.Lugagge;
import upb.ticketmanagement.model.domain.Ticket;
import upb.ticketmanagement.model.domain.TicketManager;

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
    public void btnGoBackClicked(@SuppressWarnings("exports") ActionEvent event) throws IOException {
        Stage stageBack;
        Scene sceneBack;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/SearchTicket.fxml"));
        //CAMBIAR UNA VEZ SE CONECTE EL MODULO A LA BASE
                // Se le pasa al contructor del controlador la trainList para simular el flujo a lo largo de el modulo
                // de tal manera que si se hacen cambios en un tren estos se ven reflejados
                // Sin embargo una vez se conecte el modulo a la base no se le pasara la lista al constructor
        loader.setControllerFactory(controllerClass -> new SearchTicketController( ticketManager));
        Parent root;
        
            root = loader.load();
            stageBack = (Stage) ((Node)event.getSource()).getScene().getWindow();
            sceneBack = new Scene(root);
            stageBack.setScene(sceneBack);
            stageBack.setMaximized(false);
            stageBack.setMaximized(true);
            stageBack.show();
       
        
    }

    public ViewMoreController(String ticketId, @SuppressWarnings("exports") TicketManager ticketManager){
        this.ticketManager = ticketManager;
        ticket = ticketManager.getTicketById(ticketId);

      


    }
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        setValues();
        
    }

    private void setValues(){

        lblArrivalTime.setText(localDateTimeFormat( ticket.getRoute().getArrivalTime()));
        lblPurchaseDate.setText(localDateTimeFormat(ticket.getPurchasDateTime()));
        lblDepartureTime.setText(localDateTimeFormat(ticket.getRoute().getDepartureTime()));
        
        lblContactLastName.setText(ticket.getPassenger().getContactPerson().getLastNames());
        lblContactName.setText(ticket.getPassenger().getContactPerson().getNames());
        lblDestiny.setText(ticket.getRoute().getStations().peek().getName());
        lblId.setText(ticket.getId());
        lblIdTrain.setText(ticket.getRoute().getTrain().getId());
        lblPassengerAddress.setText(ticket.getPassenger().getAddress());
        lblPassengerId.setText(ticket.getPassenger().getId());
        lblPassengerIdType.setText(ticket.getSeatType().getDescription());
        lblPassengerLastName.setText(ticket.getPassenger().getLastNames());
        lblPassengerName.setText(ticket.getPassenger().getNames());
        
        lblSeatType.setText(ticket.getSeatType().getDescription());
        lblValue.setText(Double.toString(ticket.getValue()));

        
        Array<Lugagge> lugagge = ticketManager.getLugaggeFromTicket(ticket);
        if(lugagge.get(0) != null){
            lblLuggageOneId.setText(lugagge.get(0).getId());
            lblLuggageOneWeight.setText(Integer.toString(lugagge.get(0).getWeight()));
            lblLuggageOneContainerId.setText(ticketManager.getWagonIdFromLugagge(lugagge.get(0), ticket.getRoute().getTrain()));
        }

        if(lugagge.get(1) != null){
            lblLuggageTwoId.setText(lugagge.get(1).getId());
            lblLuggageTwoWeight.setText(Integer.toString(lugagge.get(1).getWeight()));
            lblLuggageTwoContainerId.setText(ticketManager.getWagonIdFromLugagge(lugagge.get(1), ticket.getRoute().getTrain()));
        }
        
        String passengerNmrs = "";

        for(int ii = 0; ii < ticket.getPassenger().getNumbers().size(); ii++){
            passengerNmrs += ticket.getPassenger().getNumbers().get(ii) + "\n";
        }
        txtAreaPassengerNumbers.setText(passengerNmrs);

        String contactNmrs = "";

        for(int ii = 0; ii < ticket.getPassenger().getNumbers().size(); ii++){
            contactNmrs += ticket.getContactPerson().getNumbers().get(ii) + "\n";
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