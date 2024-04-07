package upb.usermanagement.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.edu.upb.array.Array;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import upb.usermanagement.model.domain.Employee;
import upb.usermanagement.model.domain.User;
import upb.usermanagement.model.domain.UserManager;

public class EditUserController implements Initializable{

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
    private Button btnGoBack;

    @FXML
    private Label labelMessage;

    @FXML
    private SplitPane pnlConfirmPassword;

    @FXML
    private RadioButton radBtnDisable;

    @FXML
    private RadioButton radBtnEnable;

    @FXML
    private SplitPane spl1;

    @FXML
    private SplitPane spltPaneActions;

    @FXML
    private Label lblFieldId;

    @FXML
    private TextField txtFieldLastName;

    @FXML
    private TextField txtFieldName;

    @FXML
    private TextField txtFieldNmrFive;

    @FXML
    private TextField txtFieldNmrFour;

    @FXML
    private TextField txtFieldNmrOne;

    @FXML
    private TextField txtFieldNmrThree;

    @FXML
    private TextField txtFieldNmrTwo;

    @FXML
    private TextField txtFieldPassword;

    @FXML
    private TextField txtFieldConfirmPassword;

    @FXML
    private TextField txtFieldUser;

    private User user;

    private UserManager userManager;

    public EditUserController(@SuppressWarnings("exports") User user, @SuppressWarnings("exports") UserManager userManager){
        this.user = user;
        this.userManager = userManager;



    }

    @FXML
    void btnApplyClicked(ActionEvent event) {
        //Variables
        String message = "";
        boolean valuesFormatOk = true;
        boolean toAdd = true;
        String name;
        String lastName;
        String id;
        String userName;
        String password;
        String confirmPassword;
        boolean isDisabled;
        Array<String> nmrs = new Array<>(5);


        //validate format
        if(!validateTxtConfirmPassword()){
            message = "Ingrese un valor valido en la sección de Confirmación de Contraseña.";
            valuesFormatOk = false;
        }

        if(!validateTxtPassword()){
            message = "Ingrese un valor valido en la sección de Contraseña.";
            valuesFormatOk = false;
        }

        if(!validateTxtUser()){
            message = "Ingrese un valor valido en la sección de Usuario.";
            valuesFormatOk = false;
        }

        if(!validateTxtNumbers()){
            message = "Ingrese al menos un valor valido en la sección de Numeros.";
            valuesFormatOk = false;
        }


        if(!validateTxtLastName()){
            message = "Ingrese un valor valido en la seccion Apellidos.";
            valuesFormatOk = false;
        }


        if(!validateTxtName()){
            message = "Ingrese un valor valido en la seccion Nombres.";
            valuesFormatOk = false;
        }

        if(valuesFormatOk){

            name = txtFieldName.getText();
            userName = txtFieldUser.getText();
            lastName = txtFieldLastName.getText();
            password = txtFieldPassword.getText();
            confirmPassword = txtFieldConfirmPassword.getText();
            nmrs = getArrayNumbers();
            Employee employee = (Employee) user.getPerson();
            id = employee.getId();

            if(!confirmPassword.equals(password)){
                message = "Las contraseñas no coinciden.";
                toAdd = false;
            }
            
            if(userManager.existUserByName(userName) && !userName.equals(user.getUserName())){
                message = "Ya existe alguien con ese nombre de usuario."; 
                toAdd = false;
            }


            if(toAdd){

                if(radBtnEnable.isSelected()){
                    isDisabled = false;
                }

                else{
                    isDisabled = true;
                }

                User userEdited = new User(userName, confirmPassword, isDisabled, 
                new Employee(name, lastName, nmrs, id));

                if(userManager.editUser(user, userEdited)){

                    message = name + " " + lastName  +" con id " + id + " editado con el usuario " + userName + ".";
                    user = userEdited;
                    setValues();
                    noEditMode();
                }else{
                    message = "No ha sido posible registrar el Usuario.";
                }

            }
        }  
            labelMessage.setText(message);
            


    }

    @FXML
    void btnCancelClicked(ActionEvent event) {
        noEditMode();
        setValues();


    }

 
    @FXML
    void btnEditClicked(ActionEvent event) {
        editMode();
    }

    @FXML
    void btnGoBackClicked(ActionEvent event) throws IOException {

        Stage stageBack;
        Scene sceneBack;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/SearchUser.fxml"));
        //CAMBIAR UNA VEZ SE CONECTE EL MODULO A LA BASE
                // Se le pasa al contructor del controlador la trainList para simular el flujo a lo largo de el modulo
                // de tal manera que si se hacen cambios en un tren estos se ven reflejados
                // Sin embargo una vez se conecte el modulo a la base no se le pasara la lista al constructor
        loader.setControllerFactory(controllerClass -> new SearchUserController( userManager));
        Parent root =loader.load();
        stageBack = (Stage) ((Node)event.getSource()).getScene().getWindow();
        sceneBack = new Scene(root);
        stageBack.setScene(sceneBack);
        stageBack.setMaximized(false);
        stageBack.setMaximized(true);
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

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        spltPaneActions.getItems().remove(brdPaneCancel);
        spltPaneActions.getItems().remove(brdPaneApply);
        setValues();
       
    }

    private void setValues(){
        Employee employee = (Employee) user.getPerson();
       

        lblFieldId.setText(employee.getId());
        txtFieldName.setText(user.getPerson().getNames());
        txtFieldLastName.setText(user.getPerson().getLastNames());
        setNmrsValue();

        txtFieldUser.setText(user.getUserName());
        txtFieldPassword.setText(user.getPassword());
        txtFieldConfirmPassword.setText(user.getPassword());
        setDisalbeValue();
       


    }

    private void setDisalbeValue(){
        if(user.isDisabled()){
            radBtnDisable.setSelected(true);
            radBtnEnable.setSelected(false);
        }else{
            radBtnDisable.setSelected(false);
            radBtnEnable.setSelected(true);
        }


    }

    private void noEditMode(){

        spltPaneActions.getItems().remove(brdPaneCancel);
        spltPaneActions.getItems().remove(brdPaneApply);
        spltPaneActions.getItems().add(brdPaneEdit);

        txtFieldName.setEditable(false);
        txtFieldLastName.setEditable(false);
        txtFieldNmrOne.setEditable(false);
        txtFieldNmrTwo.setEditable(false);
        txtFieldNmrThree.setEditable(false);
        txtFieldNmrFour.setEditable(false);
        txtFieldNmrFive.setEditable(false);

        txtFieldUser.setEditable(false);
        txtFieldPassword.setEditable(false);
        txtFieldConfirmPassword.setEditable(false);

        radBtnDisable.setDisable(true);
        radBtnEnable.setDisable(true);

    }
    
    private void editMode(){
        spltPaneActions.getItems().remove(brdPaneEdit);
        spltPaneActions.getItems().add(brdPaneApply);
        spltPaneActions.getItems().add(brdPaneCancel);

        txtFieldName.setEditable(true);
        txtFieldLastName.setEditable(true);
        txtFieldNmrOne.setEditable(true);
        txtFieldNmrTwo.setEditable(true);
        txtFieldNmrThree.setEditable(true);
        txtFieldNmrFour.setEditable(true);
        txtFieldNmrFive.setEditable(true);

        txtFieldUser.setEditable(true);
        txtFieldPassword.setEditable(true);
        txtFieldConfirmPassword.setEditable(true);

        radBtnDisable.setDisable(false);
        radBtnEnable.setDisable(false);
    }
        
    private void setNmrsValue(){
        Array<String> nmr = user.getPerson().getNumbers();

        txtFieldNmrOne.setText("");
        txtFieldNmrTwo.setText("");
        txtFieldNmrThree.setText("");
        txtFieldNmrFour.setText("");
        txtFieldNmrFive.setText("");

        for(int ii = 0; ii < nmr.size(); ii++){
            if(nmr.get(ii) != null){

                if(ii == 0){
                    txtFieldNmrOne.setText(nmr.get(ii));
                    
                }

                if(ii == 1){
                    txtFieldNmrTwo.setText(nmr.get(ii));
                    
                }

                if(ii == 2){
                    txtFieldNmrThree.setText(nmr.get(ii));
                    
                }

                if(ii == 3){
                    txtFieldNmrFour.setText(nmr.get(ii));
                    
                }

                if(ii == 4){
                    txtFieldNmrFive.setText(nmr.get(ii));
                    
                }
            }
        }
    }   

    private boolean validateTxtName(){
        
        if(txtFieldName.getText().isBlank()){
            return false;
        }
        
        return true;
    }
    private boolean validateTxtLastName(){
        
        if(txtFieldLastName.getText().isBlank()){
            return false;
        }
        
        return true;
    }

    private boolean validateTxtNumbers(){

        
        if(!txtFieldNmrOne.getText().isBlank()){
            return true;
        }
        if(!txtFieldNmrTwo.getText().isBlank()){
            return true;
        }
        
        if(!txtFieldNmrThree.getText().isBlank()){
            return true;
        }
        
        if(!txtFieldNmrFour.getText().isBlank()){
            return true;
        }
        
        if(!txtFieldNmrFive.getText().isBlank()){
            return true;
        }
        
        return false;
    }
    private boolean validateTxtUser(){
        if(txtFieldUser.getText().isBlank()){
            return false;
        }
        
        return true;
    }

    private boolean validateTxtPassword(){
        if(txtFieldPassword.getText().isBlank()){
            return false;
        }
        
        return true;
    }

    private boolean validateTxtConfirmPassword(){
        if(txtFieldConfirmPassword.getText().isBlank()){
            return false;
        }
        
        return true;
    }

    private Array<String> getArrayNumbers(){

        Array<String> array = new Array<>(5);

        
        if(!txtFieldNmrOne.getText().isBlank()){
            array.add(txtFieldNmrOne.getText());
        }
        if(!txtFieldNmrTwo.getText().isBlank()){
            array.add(txtFieldNmrTwo.getText());
        }
        
        if(!txtFieldNmrThree.getText().isBlank()){
            array.add(txtFieldNmrThree.getText());
        }
        
        if(!txtFieldNmrFour.getText().isBlank()){
            array.add(txtFieldNmrFour.getText());
        }
        
        if(!txtFieldNmrFive.getText().isBlank()){
            array.add(txtFieldNmrFive.getText());
        }
        
        return array;
    }


}
