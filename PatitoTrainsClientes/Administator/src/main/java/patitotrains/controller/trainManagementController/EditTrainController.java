package patitotrains.controller.trainManagementController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import patitotrains.controller.trainManagementController.interfaces.EditTrainControllerInterface;
import patitotrains.model.Managers.LoggerManager;
import patitotrains.model.Managers.TrainManager;
import patitotrains.model.domain.Train;
import patitotrains.view.trainManagementViews.SearchTrainView;

import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

/**
 * Clase que controla la vista de edición de trenes
 */
public class EditTrainController implements Initializable, EditTrainControllerInterface {

    @FXML
    private BorderPane brdPaneApply;

    @FXML
    private BorderPane brdPaneCancel;

    @FXML
    private BorderPane brdPaneEdit;

    @FXML
    private Button btnApply;

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnEdit;

    @FXML
    private Label lblMileage;

    @FXML
    private Button btnGoBack;

    @FXML
    private Label labelMessage;

    @FXML
    private Label lblAmtLugaggeWagons;

    @FXML
    private Label lblModel;

    @FXML
    private Label lblOnJourney;

    @FXML
    private RadioButton radBtnDisable;

    @FXML
    private RadioButton radBtnEnable;

    @FXML
    private SplitPane spl1;

    @FXML
    private SplitPane spltPaneActions;

    @FXML
    private TextField txtFieldAmtPassangerWagons;

    @FXML
    private Label lblId;

    @FXML
    private TextField txtFieldName;
    @FXML
    private Label lblCargoCapacity;

    private TrainManager trainManager;

    private Train train;

    private SearchTrainView searchTrainView;

    /**
     * Constructor de la clase
     * @throws NotBoundException
     * @throws RemoteException
     */
    public EditTrainController() throws NotBoundException, RemoteException {
        trainManager = new TrainManager();
        trainManager.pullData();
        searchTrainView = new SearchTrainView();
    }

    /**
     * Constructor de la clase
     * @param train Tren
     * @param trainManager Manager de trenes
     */
    public EditTrainController( Train train,  TrainManager trainManager){
        this.trainManager = trainManager;
        this.train = train;
        searchTrainView = new SearchTrainView();
    }

    /**
     * Método que se ejecuta al inicializar la vista
     * @param arg0 URL
     * @param arg1 ResourceBundle
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        spltPaneActions.getItems().remove(brdPaneCancel);
        spltPaneActions.getItems().remove(brdPaneApply);

        setValues();
        
    }

    /**
     * Método que se ejecuta al presionar el botón de editar
     * @param event Evento
     */
    @FXML
    public void btnEditClicked( ActionEvent event) {
        if(!train.isOnJourney()){
            edit();
            
        }else{
            labelMessage.setText("No puedes editar un tren que esta en viaje.");
        }

    }

    /**
     * Método que se ejecuta al presionar el botón de regresar
     * @param event Evento
     */
    @FXML
    public void btnGoBackClicked( ActionEvent event)  {
       
        try {
            searchTrainView.start(event);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Método que se ejecuta al presionar el botón de deshabilitar
     * @param event Evento
     */
    @FXML
    public  void radBtnDisableClicked( ActionEvent event) {
        radBtnEnable.setSelected(false);

    }

    /**
     * Método que se ejecuta al presionar el botón de habilitar
     * @param event Evento
     */
    @FXML
    public void radBtnEnableClicked( ActionEvent event) {
        radBtnDisable.setSelected(false);

    }

    /**
     * Método que se ejecuta al presionar el botón de aplicar
     * @param event Evento
     * @throws NotBoundException
     * @throws RemoteException
     */
    @FXML
    public void btnApplyClicked( ActionEvent event) throws NotBoundException, RemoteException {
        String message = "";
        boolean valuesFormatOk = true;
        boolean toEdit = true;

        String txtName;
        int amtWagons;
        boolean status;

        if(!validateTxtAmtWagons()){
            message = "Ingrese un valor valido en la seccion Cantidad de vagones de pasajeros.";
            valuesFormatOk = false;
        }

        if(!validateTxtName()){
            message = "Ingrese un valor valido en la seccion Nombre.";
            valuesFormatOk = false;
        }

        if(!validateStatus()){
            message = "Seleccione una opccion en el estado del tren.";
            valuesFormatOk = false;
        }

        if(valuesFormatOk){
            txtName = txtFieldName.getText();
            amtWagons = getAmtWagons();
            status = getStatus();

            if(!txtName.equals(train.getName())){
                toEdit = !trainManager.existTrainByName(txtName);
                message = "Ya existe un tren con ese nombre. Ingrese un nombre distinto."; 
            }

            if( !trainManager.validateAmtPassengerWagons(amtWagons, train.getType())){
                toEdit = false;
                message = "No es posible añadir un tren con la cantidad de vagones que ingreso. Recuerde que un tren " + 
                train.getType().getDescription() + " tan solo puede cargar " + train.getType().getCargoCapacity() + " vagones.";
            }
            if(txtName.equals(train.getName()) && amtWagons == train.getPassengerWagons().length()
            && getStatus() == train.isDisabled()){
                toEdit = false;
                message = "No ha editado ningun campo.";
            }

            if(toEdit){
                if(trainManager.editTrain(train.getId(), txtName, amtWagons, status)){
                    String statusAsString;
                    train = trainManager.getTrainById(train.getId());

                    if(status){
                        statusAsString = "Deshabilitado";
                    }else{
                        statusAsString = "Habilitado";
                    }

                    message = "Tren " + train.getId()+ " " + train.getName() + " con " + train.getPassengerWagons().length() + " vagones de pasajeros  y en un estado "+ statusAsString + " ha sido exitosamente editado." ;
                    LoggerManager log = new LoggerManager();
                    log.logAction("Edicion de tren: " + train.getId() + " " + train.getName() );
                }else{
                    message = "No ha sido posible editar el tren.";
                }

                noEdit();
                setValues();


            }
        }

        labelMessage.setText(message);
    }   

    @FXML
    public void btnCancelClicked( ActionEvent event) {
        noEdit();
        setValues();


    }
    private void edit(){

        spltPaneActions.getItems().remove(brdPaneEdit);
        spltPaneActions.getItems().add(brdPaneApply);
        spltPaneActions.getItems().add(brdPaneCancel);

        txtFieldAmtPassangerWagons.setEditable(true);
        txtFieldAmtPassangerWagons.setEditable(true);
        txtFieldName.setEditable(true);
        radBtnDisable.setDisable(false);
        radBtnEnable.setDisable(false);


    }
    private void noEdit(){
        spltPaneActions.getItems().remove(brdPaneCancel);
        spltPaneActions.getItems().remove(brdPaneApply);
        spltPaneActions.getItems().add(brdPaneEdit);

        txtFieldAmtPassangerWagons.setEditable(false);
        txtFieldAmtPassangerWagons.setEditable(false);
        txtFieldName.setEditable(false);
        radBtnDisable.setDisable(true);
        radBtnEnable.setDisable(true);

        radBtnDisable.setSelected(false);
        radBtnEnable.setSelected(false);
    }

    private void setValues(){
        lblId.setText(train.getId());
        txtFieldName.setText(train.getName());
        txtFieldAmtPassangerWagons.setText(Integer.toString(train.getPassengerWagons().length()));
        lblAmtLugaggeWagons.setText(Integer.toString(train.getLuggageWagons().length()));
        lblCargoCapacity.setText(Integer.toString(train.getType().getCargoCapacity()));
        lblModel.setText(train.getType().getDescription());
        lblMileage.setText(Double.toString(train.getMileage()));
        
        if(train.isOnJourney()){
            lblOnJourney.setText("SI");
        }else{
            lblOnJourney.setText("NO");
        }

        if(train.isDisabled()){
            radBtnDisable.setSelected(true);
        }else{
            radBtnEnable.setSelected(true);
        }
    }

    private boolean validateTxtName(){
        
        if(txtFieldName.getText().isBlank()){
            return false;
        }
        
        return true;
    }

    private boolean validateTxtAmtWagons(){
        
        if(txtFieldAmtPassangerWagons.getText().isBlank()){
            return false;
        }
    
        try {
            Integer.parseInt(txtFieldAmtPassangerWagons.getText());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean validateStatus(){
        if(!radBtnDisable.isSelected() && !radBtnEnable.isSelected()){
            return false;
        }
        return true;


    }

    private int getAmtWagons(){
        try {
            int amtWagons = Integer.parseInt(txtFieldAmtPassangerWagons.getText());
            return amtWagons;
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    private boolean getStatus(){
        if(radBtnDisable.isSelected()){
            return true;
        }else{
            return false;
        }

    }
 
}
