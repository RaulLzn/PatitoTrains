package patitotrains.controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import patitotrains.view.PassengerDataView;

public class WelcomePageController {

    @FXML
    private BorderPane pnlImage;

    @FXML
    private BorderPane pnlPurchaseTicket;

    @FXML
    void pnlPurchaseTicketClicked(MouseEvent event) throws IOException {
        PassengerDataView passengerDataView = new PassengerDataView();
        passengerDataView.start(event);
    }

}
