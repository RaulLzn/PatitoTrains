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
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import upb.trainmanagement.model.domain.Train;
import upb.trainmanagement.model.domain.TrianManager;

public class EditTrainController implements Initializable {

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

    private Stage stageBack;

    private Scene sceneBack;


    private TrianManager trainManager;

    Train train;

    String idtrain;
    public EditTrainController(){
        trainManager = new TrianManager();

        idtrain = "0057";
        trainManager.addTrain("0057", "Charala Tren", trainManager.getTrainTypeById("001"), 12);
        train = trainManager.getTrainById(idtrain);

    }
    public EditTrainController(String idTrain, @SuppressWarnings("exports") LinkedList<Train> list){
        trainManager = new TrianManager();
        trainManager.setTrainList(list);
        this.idtrain = idTrain;
        train = trainManager.getTrainById(idtrain);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        spltPaneActions.getItems().remove(brdPaneCancel);
        spltPaneActions.getItems().remove(brdPaneApply);

       setValues();
        

        
    }


    @FXML
    void btnEditClicked(ActionEvent event) {
        if(!train.isOnJourney()){
            spltPaneActions.getItems().remove(brdPaneEdit);
            spltPaneActions.getItems().add(brdPaneApply);
            spltPaneActions.getItems().add(brdPaneCancel);

            txtFieldAmtPassangerWagons.setEditable(true);
            txtFieldAmtPassangerWagons.setEditable(true);
            txtFieldName.setEditable(true);
            radBtnDisable.setDisable(false);
            radBtnEnable.setDisable(false);
        }else{
            labelMessage.setText("No puedes editar un tren que esta en viaje.");
        }
        

    }

    @FXML
    void btnGoBackClicked(ActionEvent event) throws IOException {
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
    void radBtnDisableClicked(ActionEvent event) {
        radBtnEnable.setSelected(false);

    }

    @FXML
    void radBtnEnableClicked(ActionEvent event) {
        radBtnDisable.setSelected(false);

    }
    @FXML
    void btnApplyClicked(ActionEvent event) {
        String message = "";
        boolean valuesFormatOk = true;
        boolean toEdit = true;

        String txtName;
        String txtId;
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
            txtId = lblId.getText();
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
                if(trainManager.editTrain(idtrain, txtName, amtWagons, status)){
                    String statusAsString;

                    if(status){
                        statusAsString = "Deshabilitado";
                    }else{
                        statusAsString = "Habilitado";
                    }

                    message = "Tren " + train.getId()+ " " + train.getName() + " con " + train.getPassengerWagons().length() + " vagones de pasajeros  y en un estado "+ statusAsString + " ha sido exitosamente editado." ;
                    idtrain = txtId;
                }else{
                    message = "No ha sido posible editar el tren.";
                }

                labelMessage.setText(message);

            
                spltPaneActions.getItems().remove(brdPaneCancel);
                spltPaneActions.getItems().remove(brdPaneApply);
                spltPaneActions.getItems().add(brdPaneEdit);

                txtFieldAmtPassangerWagons.setEditable(false);
                txtFieldAmtPassangerWagons.setEditable(false);
                txtFieldName.setEditable(false);

                

                radBtnDisable.setDisable(true);
                radBtnEnable.setDisable(true);

                

                setValues();


            }
        }

        labelMessage.setText(message);
    }   

    @FXML
    void btnCancelClicked(ActionEvent event) {
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
        setValues();


    }

    private void setValues(){
        lblId.setText(train.getId());
        txtFieldName.setText(train.getName());
        txtFieldAmtPassangerWagons.setText(Integer.toString(train.getPassengerWagons().length()));
        lblAmtLugaggeWagons.setText(Integer.toString(train.getLuggageWagons().length()));
        lblCargoCapacity.setText(Integer.toString(train.getType().getCargoCapacity()));
        lblModel.setText(train.getType().getDescription());
        lblMileage.setText(Integer.toString(train.getMileage()));
        
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

    public boolean validateTxtName(){
        
        if(txtFieldName.getText().isBlank()){
            return false;
        }
        
        return true;
    }

    public boolean validateTxtAmtWagons(){
        
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

    public boolean validateStatus(){
        if(!radBtnDisable.isSelected() && !radBtnEnable.isSelected()){
            return false;
        }
        return true;


    }

    public int getAmtWagons(){
        try {
            int amtWagons = Integer.parseInt(txtFieldAmtPassangerWagons.getText());
            return amtWagons;
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    public boolean getStatus(){
        if(radBtnDisable.isSelected()){
            return true;
        }else{
            return false;
        }

    }
 
}
