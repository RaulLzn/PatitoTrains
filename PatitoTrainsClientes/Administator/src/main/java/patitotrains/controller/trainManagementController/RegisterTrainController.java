package patitotrains.controller.trainManagementController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import patitotrains.controller.trainManagementController.interfaces.RegisterTrainControllerInterface;
import patitotrains.model.Managers.LoggerManager;
import patitotrains.model.Managers.TrainManager;
import patitotrains.model.domain.types.TrainType;
import patitotrains.view.trainManagementViews.SearchTrainView;

import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

/**
 * Clase que controla la vista de registro de trenes
 */
public class RegisterTrainController implements Initializable, RegisterTrainControllerInterface {

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnGoBack;

    @FXML
    private ComboBox<String> cmbBoxModel;

    @FXML
    private Label labelMessage;

    @FXML
    private SplitPane spl1;

    @FXML
    private TextField txtFieldName;

    @FXML
    private TextField txtFieldAmtWagons;

    @FXML
    private Label lblAssignedId;

    private TrainManager trainManager;

    /**
     * Constructor de la clase
     * @throws NotBoundException
     * @throws RemoteException
     */
    public RegisterTrainController() throws NotBoundException, RemoteException {
        trainManager = new TrainManager();

       
    }

    /**
     * Constructor de la clase
     * @param trainManager Manager de trenes
     */
    public RegisterTrainController( TrainManager trainManager){
        this.trainManager = trainManager;
    }

    /**
     * Método que inicializa la vista
     * @param arg0 URL
     * @param arg1 ResourceBundle
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        cmbBoxModel.getItems().addAll(trainManager.trainTypesDescription());
        
    }

    /**
     * Método que se ejecuta al presionar el botón de regresar
     * @param event Evento
     */
    @FXML
    public void btnGoBackClicked( ActionEvent event){
        
        try {
            SearchTrainView searchTrainView = new SearchTrainView();
            searchTrainView.start(event, trainManager);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Método que se ejecuta al presionar el botón de añadir
     * @param event Evento
     * @throws NotBoundException
     * @throws RemoteException
     */
    @FXML
    public void btnAddClicked( ActionEvent event) throws NotBoundException, RemoteException {
        String message = "";
        String assignedIdMessage = "";
        boolean valuesFormatOk = true;
        boolean toAdd = true;
        String name;
        String id;
        TrainType type;
        int amtPassengerWagons;

        

        if(!validateTxtAmtWagons()){
            message = "Ingrese un valor valido en la seccion Cantidad de vagones de pasajeros.";
            valuesFormatOk = false;
        }
        if(!validateCmbBox()){
            message = "Ingrese un valor valido en la seccion Modelo.";
            valuesFormatOk = false;
        }
        if(!validateTxtName()){
            message = "Ingrese un valor valido en la seccion Nombre.";
            valuesFormatOk = false;
        }

        

        if(valuesFormatOk){

            name = txtFieldName.getText();
            type = trainTypeSelected();
            amtPassengerWagons = amtWagons();
            id = Integer.toString(trainManager.getTrainIdCounter() + 1);

            if(!trainManager.validateAmtPassengerWagons(amtPassengerWagons, type)){
                message = "No es posible añadir un tren con la cantidad de vagones que ingreso. Recuerde que un tren " + 
                type.getDescription()+ " tan solo puede cargar " + type.getCargoCapacity() + " vagones.";
               toAdd = false;
            }
            if(trainManager.existTrainByName(name)){
                message = "Ya existe un tren con ese nombre. Ingrese un nombre distinto."; 
                toAdd = false;
            }


            if(toAdd){
                

                if(trainManager.addTrain(id, name, type, amtPassengerWagons)){

                    trainManager.setTrainIdCounter(trainManager.getTrainIdCounter() + 1);
                    assignedIdMessage = "ID Asignado: " + id;
                    message = name + " " + id  +" modelo " + type.getDescription() + " de " + amtPassengerWagons + " vagones registrado exitosamente.";
                    LoggerManager log = new LoggerManager();
                    log.logAction("Registro de tren: " + name + " " + id  +" modelo " + type.getDescription() + " de " + amtPassengerWagons + " vagones registrado exitosamente.");
                    clear();
                }else{
                    message = "No ha sido posible registrar el tren.";
                }

            }
            

        }
        lblAssignedId.setText(assignedIdMessage);
        labelMessage.setText(message);
    }

    /**
     * Método que limpia los campos de texto
     */
    private void clear(){

        txtFieldName.clear();
        cmbBoxModel.getSelectionModel().clearSelection();
        txtFieldAmtWagons.clear();



    }

    /**
     * Método que valida el campo de texto de la cantidad de vagones
     * @return boolean
     */
    private boolean validateCmbBox(){

        if(cmbBoxModel.getSelectionModel().getSelectedItem() == null){
            return false;
        }

        return true;


    }

    /**
     * Método que valida el campo de texto del nombre
     * @return boolean
     */
    private boolean validateTxtName(){
        
        if(txtFieldName.getText().isBlank()){
            return false;
        }
        
        return true;
    }

    /**
     * Método que valida el campo de texto de la cantidad de vagones
     * @return boolean
     */
    private boolean validateTxtAmtWagons(){
        
        if(txtFieldAmtWagons.getText().isBlank()){
            return false;
        }
    
        try {
            Integer.parseInt(txtFieldAmtWagons.getText());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }

    }

    /**
     * Método que retorna la cantidad de vagones
     * @return int
     */
    private int amtWagons(){
        try {
            int amtWagons = Integer.parseInt(txtFieldAmtWagons.getText());
            return amtWagons;
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    /**
     * Método que retorna el tipo de tren seleccionado
     * @return TrainType
     */
    private TrainType trainTypeSelected(){
        return trainManager.getTrainTypeByDescription(cmbBoxModel.getSelectionModel().getSelectedItem());

    }

   
}
