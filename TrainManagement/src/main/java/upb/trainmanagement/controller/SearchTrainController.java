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
import upb.trainmanagement.controller.interfaces.SearchTrainControllerInterface;
import upb.trainmanagement.model.domain.Train;
import upb.trainmanagement.model.domain.TrainManager;

public class SearchTrainController implements Initializable, SearchTrainControllerInterface{



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

    private TrainManager trainManager;



 
    //Eliminar una vez se tenga conectado a base, hecho para simular el flujo mantener los datos
    public SearchTrainController(@SuppressWarnings("exports")TrainManager trainManager){
        trainManager = new TrainManager();
        this.trainManager = trainManager;
    }
    //------------------------------------

    public SearchTrainController(){
        
        trainManager = new TrainManager();


    }
    @FXML
    public void btnRegisterTrainClicked(@SuppressWarnings("exports") ActionEvent event) throws IOException {

        Stage stageRegister;

        Scene sceneRegister;
    
        Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/RegisterTrain.fxml"));
        loader.setControllerFactory(controllerClass -> new RegisterTrainController( trainManager));
        Parent root =loader.load();
        stageRegister = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        sceneRegister = new Scene(root, screenSize.getWidth(), screenSize.getHeight());
        
        stageRegister.setScene(sceneRegister);

        stageRegister.show();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        ObservableList<Train> trainsObservableList;


        trainsObservableList = FXCollections.observableArrayList();
        
        txtFieldSearch.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                String input = txtFieldSearch.getText();
          
                LinkedList<Train> newTrainList = trainManager.searchTrains(input);
                trainsObservableList.setAll( trainManager.trainArray(newTrainList));
                tableTrains.setItems(trainsObservableList);
                
            }
        });

        trainsObservableList.setAll(trainManager.trainArray());
        tableTrains.setItems(trainsObservableList);
        setColumnsValues();
        
    }   

    private void setColumnsValues(){
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnPassengerWagons.setCellValueFactory(cellData -> {
            Integer amtPassagerWagons = cellData.getValue().getPassengerWagons().lenght();
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
         

            return new SimpleStringProperty(Double.toString(cellData.getValue().getMileage()));

        });

        columnEdit.setCellValueFactory(cellData -> {
            Button buttonEdit;
    
            buttonEdit = new Button();
            buttonEdit.setText("Editar");

            buttonEdit.setOnAction(event -> {
                
           

                Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/EditTrain.fxml"));
                //CAMBIAR UNA VEZ SE CONECTE EL MODULO A LA BASE
                // Se le pasa al contructor del controlador la trainList para simular el flujo a lo largo de el modulo
                // de tal manera que si se hacen cambios en un tren estos se ven reflejados
                // Sin embargo una vez se conecte el modulo a la base no se le pasara la lista al constructor
                loader.setControllerFactory(controllerClass -> new EditTrainController(cellData.getValue(), trainManager));
                Parent root;
                try {
                    Scene sceneEdit;
                    root = loader.load();
                    sceneEdit = new Scene(root, screenSize.getWidth(), screenSize.getHeight());
                    Stage stageEdit;
                    stageEdit = (Stage) ((Node)event.getSource()).getScene().getWindow();
                    stageEdit.setScene(sceneEdit);
                    stageEdit.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }  
            });

            return new SimpleObjectProperty<Button>(buttonEdit);

        });

    }

    

}
