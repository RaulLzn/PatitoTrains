package upb.administrator.controller.trainManagementController;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import upb.administrator.controller.trainManagementController.interfaces.RegisterTrainControllerInterface;
import upb.administrator.model.Managers.TrainManager;
import upb.administrator.model.domain.types.TrainType;
import upb.administrator.view.trainManagementViews.SearchTrainView;

public class RegisterTrainController implements Initializable, RegisterTrainControllerInterface{

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

    private SearchTrainView searchTrainView;



    public RegisterTrainController(){
        trainManager = new TrainManager();
        searchTrainView = new SearchTrainView();
       
    }
    public RegisterTrainController( TrainManager trainManager){
        this.trainManager = trainManager;
        searchTrainView = new SearchTrainView();

        
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        cmbBoxModel.getItems().addAll(trainManager.trainTypesDescription());
        
    }

    @FXML
    public void btnGoBackClicked( ActionEvent event){
        
        try {
            searchTrainView.start(event, trainManager);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void btnAddClicked( ActionEvent event) {
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
                    clear();
                }else{
                    message = "No ha sido posible registrar el tren.";
                }

            }
            

        }
        lblAssignedId.setText(assignedIdMessage);
        labelMessage.setText(message);
    }

    private void clear(){

        txtFieldName.clear();
        cmbBoxModel.getSelectionModel().clearSelection();
        txtFieldAmtWagons.clear();



    }

    private boolean validateCmbBox(){

        if(cmbBoxModel.getSelectionModel().getSelectedItem() == null){
            return false;
        }

        return true;


    }

    private boolean validateTxtName(){
        
        if(txtFieldName.getText().isBlank()){
            return false;
        }
        
        return true;
    }

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

    private int amtWagons(){
        try {
            int amtWagons = Integer.parseInt(txtFieldAmtWagons.getText());
            return amtWagons;
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private TrainType trainTypeSelected(){
        return trainManager.getTrainTypeByDescription(cmbBoxModel.getSelectionModel().getSelectedItem());

    }

   
}
