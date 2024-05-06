package patitotrains.controller;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import patitotrains.view.PassengerDataView;

import java.io.IOException;

/**
 * Controlador de la vista de bienvenida
 */
public class WelcomePageController {

    @FXML
    private BorderPane pnlImage;

    @FXML
    private BorderPane pnlPurchaseTicket;

    /**
     * Método que se ejecuta al presionar el panel de compra de boletos
     * @param event Evento
     * @throws IOException
     */
    @FXML
    void pnlPurchaseTicketClicked(MouseEvent event) throws IOException {
        PassengerDataView passengerDataView = new PassengerDataView();
        passengerDataView.start(event);
    }

}
