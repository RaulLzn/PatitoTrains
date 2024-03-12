package upb.trainmanagement.controller;

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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Screen;
import javafx.stage.Stage;
import upb.trainmanagement.model.domain.Train;
import upb.trainmanagement.model.domain.TrianManager;

public class SearchTrainController implements Initializable{

    @FXML
    private Button btnRegisterTrain;

    @FXML
    private TableView<Train> tableTrains;

    @FXML
    private TableColumn<Train, Button> columnEdit;

    @FXML
    private TableColumn<Train, String> columnId;

    @FXML
    private TableColumn<Train, String> columnName;

    @FXML
    private TableColumn<Train, String> columnOnJourney;

    @FXML
    private TableColumn<Train, String> columnPassengerWagons;

    @FXML
    private TableColumn<Train, String> columnStatus;

    @FXML
    private TableColumn<Train, String> columnTrainModel;

    @FXML
    private TableColumn<Train, String> columnMileage;


    @FXML
    private TextField txtFieldSearch;

    private Button buttonEdit;

    private TrianManager trainManager;

    private ObservableList<Train> trainsObservableList;
    
    private Stage stageRegister;

    private Scene sceneRegister;

    private Stage stageEdit;

    private Scene sceneEdit;

    public SearchTrainController(@SuppressWarnings("exports") LinkedList<Train> list){
        trainManager = new TrianManager();
        trainManager.setTrainList(list);
    }

    public SearchTrainController(){
        
        trainManager = new TrianManager();

        // Codigo utilizado para las pruebas(Temporal)
        // Una vez se conecte el modulo con la base de datos esta informacion sera bajada de esta misma
        trainManager.addTrain("10000", "Charala Train", trainManager.getTrainTypeById("001"), 6);
        trainManager.addTrain("10001", "Granados Train", trainManager.getTrainTypeById("001"), 6);
        trainManager.addTrain("10002", "Stormwind Express", trainManager.getTrainTypeById("001"), 8);
        trainManager.addTrain("10003", "Ironforge Limited", trainManager.getTrainTypeById("002"), 15);
        trainManager.addTrain("10004", "Orgrimmar Express", trainManager.getTrainTypeById("001"), 10);
        trainManager.addTrain("10005", "Darnassus Local", trainManager.getTrainTypeById("002"), 5);
        trainManager.addTrain("10006", "Undercity Commuter", trainManager.getTrainTypeById("001"), 12);
        trainManager.addTrain("10007", "Silvermoon Shuttle", trainManager.getTrainTypeById("002"), 7);
        trainManager.addTrain("10008", "Exodar Express", trainManager.getTrainTypeById("001"), 14);
        trainManager.addTrain("10009", "Thunder Bluff Transit", trainManager.getTrainTypeById("002"), 9);
        trainManager.addTrain("10010", "Gnomeregan Rapid", trainManager.getTrainTypeById("001"), 11);
        trainManager.addTrain("10011", "Sen'jin Sprinter", trainManager.getTrainTypeById("002"), 6);
        trainManager.addTrain("10012", "Stranglethorn Express", trainManager.getTrainTypeById("001"), 18);
        trainManager.addTrain("10013", "Winterspring Wanderer", trainManager.getTrainTypeById("002"), 13);
        trainManager.addTrain("10014", "Darkshore Deluxe", trainManager.getTrainTypeById("001"), 8);
        trainManager.addTrain("10015", "Durotar Bullet", trainManager.getTrainTypeById("002"), 17);
        trainManager.addTrain("10016", "Thousand Needles Transit", trainManager.getTrainTypeById("001"), 4);
        trainManager.addTrain("10017", "Feralas Flyer", trainManager.getTrainTypeById("002"), 15);
        trainManager.addTrain("10018", "Tanaris Thunder", trainManager.getTrainTypeById("001"), 10);
        trainManager.addTrain("10019", "Silithus Swift", trainManager.getTrainTypeById("002"), 12);
        trainManager.addTrain("10020", "Desolace Express", trainManager.getTrainTypeById("001"), 5);
        trainManager.addTrain("10021", "Mulgore Mover", trainManager.getTrainTypeById("002"), 16);
        trainManager.addTrain("10022", "Felwood Flyer", trainManager.getTrainTypeById("001"), 7);
        trainManager.addTrain("10023", "Ashenvale Arrow", trainManager.getTrainTypeById("002"), 14);
        trainManager.addTrain("10024", "Azshara Zephyr", trainManager.getTrainTypeById("001"), 9);
        trainManager.addTrain("10025", "Un'Goro Underground", trainManager.getTrainTypeById("002"), 11);
        trainManager.addTrain("10026", "Wailing Caverns Cruiser", trainManager.getTrainTypeById("001"), 6);
        trainManager.addTrain("10027", "Train Tracker", trainManager.getTrainTypeById("002"), 7);
        trainManager.addTrain("10028", "Train Transporter", trainManager.getTrainTypeById("001"), 14);
        trainManager.addTrain("10029", "Train Traveler", trainManager.getTrainTypeById("002"), 9);
        trainManager.addTrain("10030", "Train Trailblazer", trainManager.getTrainTypeById("001"), 11);
        trainManager.addTrain("10031", "Train Transit", trainManager.getTrainTypeById("002"), 6);
        trainManager.addTrain("10032", "Train Thunder", trainManager.getTrainTypeById("001"), 18);
        trainManager.addTrain("10033", "Train Travelogue", trainManager.getTrainTypeById("002"), 13);
        trainManager.addTrain("10034", "Train Trekker", trainManager.getTrainTypeById("001"), 8);
        trainManager.addTrain("10035", "Train Turbo", trainManager.getTrainTypeById("002"), 17);
        trainManager.addTrain("10036", "Train Tornado", trainManager.getTrainTypeById("001"), 4);

        trainManager.getTrainById("10001").setDisabled(true);
        trainManager.getTrainById("10002").setDisabled(true);
        trainManager.getTrainById("10002").setOnJourney(true);

        trainManager.getTrainById("10003").setOnJourney(true);
        trainManager.getTrainById("10004").setOnJourney(true);
        trainManager.getTrainById("10005").setOnJourney(true);

        trainManager.getTrainById("10005").setMileage(30);
        trainManager.getTrainById("10005").setMileage(300);
        trainManager.getTrainById("10008").setMileage(15000);
        trainManager.getTrainById("10012").setMileage(6000);
        trainManager.getTrainById("10016").setMileage(45000);
        trainManager.getTrainById("10020").setMileage(80000);
        trainManager.getTrainById("10024").setMileage(20000);
        trainManager.getTrainById("10028").setMileage(7500);
        trainManager.getTrainById("10032").setMileage(42000);
        trainManager.getTrainById("10036").setMileage(90000);
        trainManager.getTrainById("10001").setMileage(60000);

        //

    }
    @FXML
    void btnRegisterTrainClicked(ActionEvent event) throws IOException {
        Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/RegisterTrain.fxml"));
        loader.setControllerFactory(controllerClass -> new RegisterTrainController( trainManager.getTrainList()));
        Parent root =loader.load();
        stageRegister = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        sceneRegister = new Scene(root, screenSize.getWidth(), screenSize.getHeight());
        
        stageRegister.setScene(sceneRegister);

        stageRegister.show();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        trainsObservableList = FXCollections.observableArrayList();
        
        txtFieldSearch.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                String input = txtFieldSearch.getText();
          
                LinkedList<Train> newTrainList = trainManager.searchTrainsByString(input);
                trainsObservableList.setAll( TrianManager.trainArray(newTrainList));
                tableTrains.setItems(trainsObservableList);
                
            }
        });

        trainsObservableList.setAll(trainManager.trainArray());
        tableTrains.setItems(trainsObservableList);
        setColumnsAlignment();
        setColumnsValues();
        
    }   

    private void setColumnsAlignment(){

        columnOnJourney.setStyle( "-fx-alignment: CENTER;");
        columnTrainModel.setStyle( "-fx-alignment: CENTER;");
        columnEdit.setStyle( "-fx-alignment: CENTER;");
        columnStatus.setStyle( "-fx-alignment: CENTER;");
        columnPassengerWagons.setStyle("-fx-alignment: CENTER;");
        columnMileage.setStyle("-fx-alignment: CENTER;");


    }
    
    private void setColumnsValues(){
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnPassengerWagons.setCellValueFactory(cellData -> {
            Integer amtPassagerWagons = cellData.getValue().getPassengerWagons().length();
            String amString = Integer.toString(amtPassagerWagons);
            return new SimpleStringProperty(amString);

        });

         
        columnTrainModel.setCellValueFactory(cellData -> {
            String type = cellData.getValue().getType().getDescription();

            return new SimpleStringProperty(type);

        });
        
        columnStatus.setCellValueFactory(cellData -> {
            boolean disabled = cellData.getValue().isDisabled();
            String disabledAsString;

            if(disabled){
                disabledAsString = "Deshabilitado";
            }else{
                disabledAsString = "Habilitado";
            }

            return new SimpleStringProperty(disabledAsString);

        });

        columnOnJourney.setCellValueFactory(cellData -> {
            boolean onJourney = cellData.getValue().isOnJourney();
            String onJourneyAString;

            if(onJourney){
                onJourneyAString = "SI";
            }else{
                onJourneyAString = "NO";
            }

            return new SimpleStringProperty(onJourneyAString);

        });

        columnMileage.setCellValueFactory(cellData -> {
         

            return new SimpleStringProperty(Integer.toString(cellData.getValue().getMileage()));

        });

        columnEdit.setCellValueFactory(cellData -> {
            buttonEdit = new Button();
            buttonEdit.setText("Editar");

            buttonEdit.setOnAction(event -> {

                Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/EditTrain.fxml"));
                //CAMBIAR UNA VEZ SE CONECTE EL MODULO A LA BASE
                // Se le pasa al contructor del controlador la trainList para simular el flujo a lo largo de el modulo
                // de tal manera que si se hacen cambios en un tren estos se ven reflejados
                // Sin embargo una vez se conecte el modulo a la base no se le pasara la lista al constructor
                loader.setControllerFactory(controllerClass -> new EditTrainController(cellData.getValue().getId(), trainManager.getTrainList()));
                Parent root;
                try {
                    root = loader.load();
                    sceneEdit = new Scene(root, screenSize.getWidth(), screenSize.getHeight());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                stageEdit = (Stage) ((Node)event.getSource()).getScene().getWindow();
                stageEdit.setScene(sceneEdit);
                stageEdit.show();
    

            });

            return new SimpleObjectProperty<Button>(buttonEdit);

        });

    }

    

}
