package upb.administrator.controller.userManagementController;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.edu.upb.array.Array;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import upb.administrator.controller.userManagementController.interfaces.RegisterUserControllerInterface;
import upb.administrator.model.Managers.UserManager;
import upb.administrator.model.domain.User;
import upb.administrator.view.userManagementViews.SearchUserView;


public class RegisterUserController implements Initializable, RegisterUserControllerInterface{

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnGoBack;

    @FXML
    private Label labelMessage;

    @FXML
    private Label lblAssignedId;

    @FXML
    private SplitPane spl1;

    @FXML
    private TextField txtFieldNmrOne;

    @FXML
    private TextField txtFieldNmrTwo;

    @FXML
    private TextField txtFieldNmrThree;

    @FXML
    private TextField txtFieldNmrFour;

    @FXML
    private TextField txtFieldNmrFive;

    @FXML
    private TextField txtFieldPassword;

    @FXML
    private TextField txtFieldConfirmPassword;

    @FXML
    private TextField txtFieldUser;

    @FXML
    private TextField txtFieldLastName;

    @FXML
    private TextField txtFieldName;

    private UserManager userManager;



    public RegisterUserController(UserManager userManager){
        this.userManager = userManager;
        
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    }

    @FXML
    public void btnAddClicked(ActionEvent event) {

        //Variables
        String message = "";
        String assignedIdMessage = "";
        boolean valuesFormatOk = true;
        boolean toAdd = true;
        String name;
        String lastName;
        String id;
        String user;
        String password;
        String confirmPassword;
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
            user = txtFieldUser.getText();
            lastName = txtFieldLastName.getText();
            password = txtFieldPassword.getText();
            confirmPassword = txtFieldConfirmPassword.getText();
            nmrs = getArrayNumbers();
            id = Integer.toString(userManager.getEmployeeIdCounter() + 1);

            if(!confirmPassword.equals(password)){
                message = "Las contraseñas no coinciden.";
                toAdd = false;
            }
            
            if(userManager.existUserByName(user)){
                message = "Ya existe alguien con ese nombre de usuario."; 
                toAdd = false;
            }


            if(toAdd){
                User userToAdd = new User(name, lastName, nmrs, id, user, password, false);

                if(userManager.addUser(userToAdd)){

                    userManager.setEmployeeIdCounter(userManager.getEmployeeIdCounter() + 1);
                    assignedIdMessage = "ID Asignado: " + id;
                    message = name + " " + lastName  +" con id " + id + " registrado con el usuario " + user + ".";
                    clear();
                }else{
                    message = "No ha sido posible registrar el Usuario.";
                }

            }
            

        }
        lblAssignedId.setText(assignedIdMessage);
        labelMessage.setText(message);
       
    }

    @FXML
    public void btnGoBackClicked(ActionEvent event) {
        try {
            SearchUserView searchUserView = new SearchUserView();
            searchUserView.start(event, userManager);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void clear(){
        txtFieldName.clear();
        txtFieldLastName.clear();
        txtFieldNmrOne.clear();
        txtFieldNmrTwo.clear();
        txtFieldNmrThree.clear();
        txtFieldNmrFour.clear();
        txtFieldNmrFive.clear();
        txtFieldUser.clear();
        txtFieldPassword.clear();
        txtFieldConfirmPassword.clear();
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
