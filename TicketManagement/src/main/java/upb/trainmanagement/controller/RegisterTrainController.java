package upb.trainmanagement.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.edu.upb.linkedList.doubly.LinkedList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
import javafx.stage.Stage;
import upb.trainmanagement.controller.interfaces.RegisterTrainControllerInterface;
import upb.trainmanagement.model.domain.Train;
import upb.trainmanagement.model.domain.TrianManager;
import upb.trainmanagement.model.domain.types.TrainType;

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

    private TrianManager trainManager;




    public RegisterTrainController(){
        trainManager = new TrianManager();
        trainManager.setTrainIdCounter(10036); // Temporal, el contador de ID sera bajado de la base de datos, esto es usado para las pruebas,
        // Hasta que se conecte el modulo a la base de datos
        
    }
    public RegisterTrainController(@SuppressWarnings("exports") LinkedList<Train> list){
        trainManager = new TrianManager();
        trainManager.setTrainList(list);
        trainManager.setTrainIdCounter(10036);; // Temporal, el contador de ID sera bajado de la base de datos, esto es usado para las pruebas,
        // Hasta que se conecte el modulo a la base de datos
        
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        cmbBoxModel.getItems().addAll(trainManager.trainTypesDescription());
        
    }

    @FXML
    public void btnGoBackClicked(@SuppressWarnings("exports") ActionEvent event) throws IOException{
        
        Stage stageBack;

        Scene sceneBack;
        Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/SearchTrain.fxml"));
        //CAMBIAR UNA VEZ SE CONECTE EL MODULO A LA BASE
                // Se le pasa al contructor del controlador la trainList para simular el flujo a lo largo de el modulo
                // de tal manera que si se hacen cambios en un tren estos se ven reflejados
                // Sin embargo una vez se conecte el modulo a la base no se le pasara la lista al constructor
        loader.setControllerFactory(controllerClass -> new SearchTrainController( trainManager.getTrainList()));
        Parent root =loader.load();
        stageBack = (Stage) ((Node)event.getSource()).getScene().getWindow();
        sceneBack = new Scene(root, screenSize.getWidth(), screenSize.getHeight());
        stageBack.setScene(sceneBack);
        stageBack.show();

    }

    @FXML
    public void btnAddClicked(@SuppressWarnings("exports") ActionEvent event) {
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
