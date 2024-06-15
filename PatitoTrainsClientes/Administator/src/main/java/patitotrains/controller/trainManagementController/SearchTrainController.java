package patitotrains.controller.trainManagementController;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import patitotrains.controller.trainManagementController.interfaces.SearchTrainControllerInterface;
import raul.Model.linkedlist.doubly.circular.LinkedList;
import patitotrains.model.Managers.TrainManager;
import patitotrains.model.domain.Train;
import patitotrains.view.menuViews.MenuView;
import patitotrains.view.trainManagementViews.EditTrainView;
import patitotrains.view.trainManagementViews.RegisterTrainView;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

/**
 * Clase que controla la vista de búsqueda de trenes
 */
public class SearchTrainController implements Initializable, SearchTrainControllerInterface {


    @FXML
    private Button btnGoBack;

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

    /**
     * Constructor de la clase
     * @param trainManager Manager de trenes
     */
    public SearchTrainController(TrainManager trainManager){
        this.trainManager = trainManager;
    }

    /**
     * Constructor de la clase
     * @throws NotBoundException
     * @throws RemoteException
     */
    public SearchTrainController() throws NotBoundException, RemoteException {
        trainManager = new TrainManager();
    }

    void btnGoBackClicked(ActionEvent event) throws Exception {
        MenuView viewMenu;
        viewMenu = new MenuView();
        viewMenu.start(event);
        
    }

    /**
     * Método que se ejecuta al presionar el botón de registrar tren
     * @param event Evento
     * @throws IOException
     */
    @FXML
    public void btnRegisterTrainClicked( ActionEvent event) throws IOException {
        RegisterTrainView registerView;
        registerView = new RegisterTrainView();
        registerView.start(event, trainManager);
    }

    /**
     * Método que se ejecuta al inicializar la vista
     * @param arg0 URL
     * @param arg1 ResourceBundle
     */
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

    /**
     * Método que asigna los valores a las columnas de la tabla
     */
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
         

            return new SimpleStringProperty(Double.toString(cellData.getValue().getMileage()));

        });

        columnEdit.setCellValueFactory(cellData -> {
            Button buttonEdit;
    
            buttonEdit = new Button();
            buttonEdit.setText("Editar");

            buttonEdit.setOnAction(event -> {
                try {
                    
                    EditTrainView editView;
                    editView = new EditTrainView();
                    editView.start(event, trainManager, cellData.getValue());
                } catch (IOException e) {
                    e.printStackTrace();
                }
        
            });

            return new SimpleObjectProperty<Button>(buttonEdit);

        });

    }

    

}
