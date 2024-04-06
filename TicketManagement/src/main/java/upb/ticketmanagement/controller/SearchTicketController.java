package upb.ticketmanagement.controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import com.edu.upb.array.Array;
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
import upb.ticketmanagement.model.domain.ContactPerson;
import upb.ticketmanagement.model.domain.ContainerWagon;
import upb.ticketmanagement.model.domain.Lugagge;
import upb.ticketmanagement.model.domain.Passenger;
import upb.ticketmanagement.model.domain.Route;
import upb.ticketmanagement.model.domain.Station;
import upb.ticketmanagement.model.domain.Ticket;
import upb.ticketmanagement.model.domain.TicketManager;
import upb.ticketmanagement.model.domain.Train;
import upb.ticketmanagement.model.domain.types.IdType;
import upb.ticketmanagement.model.domain.types.SeatType;
import upb.ticketmanagement.model.domain.types.TrainType;

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

        // Eliminar una vez implementado con base de datos, hecho para simular flujo
        SeatType premium = new SeatType("001", "Premium", 1800);
        SeatType executive = new SeatType("002", "Ejecutivo", 1200);
        SeatType standard = new SeatType("003", "Standard", 1000);

        IdType ti = new IdType("001", "T.I");
        IdType cc = new IdType("002", "C.C");

        TrainType arnold = new TrainType("001", "Arnold", 32);
        TrainType mercedes = new TrainType("002", "Mercedes", 28);

        Train train1 = new Train("06530", "Tren Amarillo", arnold, 10, 5);
        Train train2 = new Train("06545", "Tren Azul", mercedes, 8, 4);
        Train train3 = new Train("09378", "Tren Rojo", arnold, 12, 6);

        Lugagge lugagge1 = new Lugagge("0003", 60, "1a00000000000000000000000000000");
        Lugagge lugagge2 = new Lugagge("0002", 68, "1a00000000000000000000000000000");

        train1.getLuggageWagons().add(new ContainerWagon("138"));
        train1.getLuggageWagons().get(0).getLuggages().add(lugagge1);
        train1.getLuggageWagons().get(0).getLuggages().add(lugagge2);

        Station station1 = new Station("001", "Estacion Triangulo");
        Station station2 = new Station("002", "Estacion Cuadrado");
        Station station3 = new Station("003", "Estacion Circulo");
        Station station4 = new Station("004", "Estacion Pentagono");

        LinkedList<Station> stations1 = new LinkedList<>();
        stations1.add(station1);
        stations1.add(station2);

        LinkedList<Station> stations2 = new LinkedList<>();
        stations2.add(station1);
        stations2.add(station2);
        stations2.add(station3);
        stations2.add(station4);

        Route route1 = new Route("Ruta del Sol", train1, stations1,
                LocalDateTime.of(2024, 9, 26, 8, 0, 0), LocalDateTime.of(2024, 9, 26, 10, 0, 0), 100);
        Route route2 = new Route("Ruta de Luna", train2, stations2,
                LocalDateTime.of(2024, 9, 26, 7, 0, 0), LocalDateTime.of(2024, 9, 26, 10, 0, 0), 50);
        Route route3 = new Route("Ruta de Luna", train3, stations2,
                LocalDateTime.of(2024, 9, 26, 7, 0, 0), LocalDateTime.of(2024, 9, 26, 10, 0, 0), 50);

        Array<String> numbers = new Array<>(5);
        numbers.add("300102301");
        numbers.add("386102301");
        numbers.add("326302301");

        Array<String> numbers2 = new Array<>(5);
        numbers2.add("300912389");
        numbers2.add("300128912");
        numbers2.add("300012310");

        ticketManager.addTicket(new Ticket("1a00000000000000000000000000000",
                new Passenger("John", "Doe", numbers, "123456789", cc, "123 Main St",
                        new ContactPerson("Jane", "Doe", numbers2, "987654321")),
                75000, standard, route1));

        ticketManager.addTicket(new Ticket("2a00000000000000000000000000000",
                new Passenger("Alice", "Smith", numbers, "987654321", ti, "456 Elm St",
                        new ContactPerson("Bob", "Smith", numbers, "123456789")),
                90000, executive, route2));

        ticketManager.addTicket(new Ticket("3a00000000000000000000000000000",
                new Passenger("Emily", "Johnson", numbers, "456789123", cc, "789 Oak St",
                        new ContactPerson("Jack", "Johnson", numbers, "654321987")),
                110000, premium, route3));

        ticketManager.addTicket(new Ticket("4a00000000000000000000000000000",
                new Passenger("Michael", "Williams", numbers, "654321987", cc, "987 Maple St",
                        new ContactPerson("Olivia", "Williams", numbers, "789123456")),
                80000, standard, route1));

        ticketManager.addTicket(new Ticket("5a00000000000000000000000000000",
                new Passenger("Sophia", "Brown", numbers, "789123456", ti, "321 Pine St",
                        new ContactPerson("William", "Brown", numbers, "456789123")),
                95000, executive, route2));

        ticketManager.addTicket(new Ticket("6a00000000000000000000000000000",
                new Passenger("Ethan", "Jones", numbers, "321654987", cc, "654 Cedar St",
                        new ContactPerson("Ava", "Jones", numbers, "852963741")),
                120000, premium, route3));

        ticketManager.addTicket(new Ticket("7a00000000000000000000000000000",
                new Passenger("Isabella", "Davis", numbers, "852963741", cc, "753 Birch St",
                        new ContactPerson("James", "Davis", numbers, "369852147")),
                85000, standard, route1));

        ticketManager.addTicket(new Ticket("8a00000000000000000000000000000",
                new Passenger("Noah", "Miller", numbers, "369852147", ti, "147 Walnut St",
                        new ContactPerson("Emma", "Miller", numbers, "159753456")),
                100000, executive, route2));

        ticketManager.addTicket(new Ticket("9a00000000000000000000000000000",
                new Passenger("Oliver", "Wilson", numbers, "159753456", cc, "369 Chestnut St",
                        new ContactPerson("Charlotte", "Wilson", numbers, "753159456")),
                115000, premium, route3));

        ticketManager.addTicket(new Ticket("10a0000000000000000000000000000",
                new Passenger("Liam", "Taylor", numbers, "753159456", cc, "852 Oakwood St",
                        new ContactPerson("Amelia", "Taylor", numbers, "456789321")),
                88000, standard, route1));
        ticketManager.addTicket(new Ticket("11a0000000000000000000000000000",
                new Passenger("Emma", "Anderson", numbers, "987654321", ti, "456 Elm St",
                        new ContactPerson("John", "Anderson", numbers, "123456789")),
                90000, executive, route2));

        ticketManager.addTicket(new Ticket("12a0000000000000000000000000000",
                new Passenger("James", "Brown", numbers, "456789123", cc, "789 Oak St",
                        new ContactPerson("Sophia", "Brown", numbers, "654321987")),
                110000, premium, route3));

        ticketManager.addTicket(new Ticket("13a0000000000000000000000000000",
                new Passenger("Olivia", "Clark", numbers, "654321987", cc, "987 Maple St",
                        new ContactPerson("Michael", "Clark", numbers, "789123456")),
                80000, standard, route1));

        ticketManager.addTicket(new Ticket("14a0000000000000000000000000000",
                new Passenger("William", "Davis", numbers, "789123456", ti, "321 Pine St",
                        new ContactPerson("Isabella", "Davis", numbers, "456789123")),
                95000, executive, route2));

        ticketManager.addTicket(new Ticket("15a0000000000000000000000000000",
                new Passenger("Ava", "Evans", numbers, "321654987", cc, "654 Cedar St",
                        new ContactPerson("Ethan", "Evans", numbers, "852963741")),
                120000, premium, route3));

        ticketManager.addTicket(new Ticket("16a0000000000000000000000000000",
                new Passenger("Charlotte", "Fisher", numbers, "852963741", cc, "753 Birch St",
                        new ContactPerson("Oliver", "Fisher", numbers, "369852147")),
                85000, standard, route1));

        ticketManager.addTicket(new Ticket("17a0000000000000000000000000000",
                new Passenger("Daniel", "Garcia", numbers, "369852147", ti, "147 Walnut St",
                        new ContactPerson("Emily", "Garcia", numbers, "159753456")),
                100000, executive, route2));

        ticketManager.addTicket(new Ticket("18a0000000000000000000000000000",
                new Passenger("Hannah", "Harris", numbers, "159753456", cc, "369 Chestnut St",
                        new ContactPerson("Noah", "Harris", numbers, "753159456")),
                115000, premium, route3));

        ticketManager.addTicket(new Ticket("19a0000000000000000000000000000",
                new Passenger("Ian", "Irwin", numbers, "753159456", cc, "852 Oakwood St",
                        new ContactPerson("Olivia", "Irwin", numbers, "456789321")),
                88000, standard, route1));

        ticketManager.addTicket(new Ticket("20a0000000000000000000000000000",
                new Passenger("Julia", "Johnson", numbers, "123456789", cc, "123 Main St",
                        new ContactPerson("Alexander", "Johnson", numbers, "987654321")),
                75000, standard, route1));
        ticketManager.addTicket(new Ticket("21a0000000000000000000000000000",
                new Passenger("Pikachu", "Ketchum", numbers, "987654321", ti, "123 Elm St",
                        new ContactPerson("Ash", "Ketchum", numbers, "123456789")),
                90000, executive, route2));

        ticketManager.addTicket(new Ticket("22a0000000000000000000000000000",
                new Passenger("Charizard", "Flame", numbers, "456789123", cc, "456 Oak St",
                        new ContactPerson("Charmeleon", "Flame", numbers, "654321987")),
                110000, premium, route3));

        ticketManager.addTicket(new Ticket("23a0000000000000000000000000000",
                new Passenger("Squirtle", "Turtle", numbers, "654321987", cc, "789 Maple St",
                        new ContactPerson("Blastoise", "Turtle", numbers, "789123456")),
                80000, standard, route1));

        ticketManager.addTicket(new Ticket("24a0000000000000000000000000000",
                new Passenger("Bulbasaur", "Seed", numbers, "789123456", ti, "321 Pine St",
                        new ContactPerson("Ivysaur", "Seed", numbers, "456789123")),
                95000, executive, route2));

        ticketManager.addTicket(new Ticket("25a0000000000000000000000000000",
                new Passenger("Mewtwo", "Psychic", numbers, "321654987", cc, "654 Cedar St",
                        new ContactPerson("Mew", "Psychic", numbers, "852963741")),
                120000, premium, route3));

        ticketManager.addTicket(new Ticket("26a0000000000000000000000000000",
                new Passenger("Snorlax", "Sleepy", numbers, "852963741", cc, "753 Birch St",
                        new ContactPerson("Jigglypuff", "Sleepy", numbers, "369852147")),
                85000, standard, route1));

        ticketManager.addTicket(new Ticket("27a0000000000000000000000000000",
                new Passenger("Gyarados", "Dragon", numbers, "369852147", ti, "147 Walnut St",
                        new ContactPerson("Magikarp", "Dragon", numbers, "159753456")),
                100000, executive, route2));

        ticketManager.addTicket(new Ticket("28a0000000000000000000000000000",
                new Passenger("Eevee", "Evolve", numbers, "159753456", cc, "369 Chestnut St",
                        new ContactPerson("Vaporeon", "Evolve", numbers, "753159456")),
                115000, premium, route3));

        ticketManager.addTicket(new Ticket("29a0000000000000000000000000000",
                new Passenger("Dragonite", "Dragon", numbers, "753159456", cc, "852 Oakwood St",
                        new ContactPerson("Dratini", "Dragon", numbers, "456789321")),
                88000, standard, route1));

        ticketManager.addTicket(new Ticket("30a0000000000000000000000000000",
                new Passenger("Gengar", "Ghost", numbers, "123456789", cc, "123 Main St",
                        new ContactPerson("Haunter", "Ghost", numbers, "987654321")),
                75000, standard, route1));

        // -----

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
                loader.setControllerFactory(controllerClass -> new ViewMoreController(cellData.getValue().getId(),  ticketManager));
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
