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
import javafx.scene.control.RadioButton;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import upb.administrator.controller.userManagementController.interfaces.EditUserControllerInterface;
import upb.administrator.model.Managers.UserManager;
import upb.administrator.model.domain.Employee;
import upb.administrator.model.domain.User;
import upb.administrator.view.userManagementViews.SearchUserView;



public class EditUserController implements Initializable, EditUserControllerInterface{

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

    private SearchUserView searchUserView;

    public EditUserController(User user, UserManager userManager){
        this.user = user;
        this.userManager = userManager;
        searchUserView = new SearchUserView();


    }

    @FXML
    public void btnApplyClicked(ActionEvent event) {
        //Variables
        String message = "";
        boolean valuesFormatOk = true;
        boolean toEdit = true;
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

            
            isDisabled = getStatus();


            if(!confirmPassword.equals(password)){
                message = "Las contraseñas no coinciden.";
                toEdit = false;
            }
            
            if(userManager.existUserByName(userName) && !userName.equals(user.getUserName())){
                message = "Ya existe alguien con ese nombre de usuario."; 
                toEdit = false;
            }

            if(name.equals(user.getPerson().getNames()) && lastName.equals(user.getPerson().getLastNames())
            && userName.equals(user.getUserName()) && password.equals(user.getPassword()) && confirmPassword.equals(user.getPassword()) 
            && nmrsAreSame() && isDisabled == user.isDisabled() ) {
            
                toEdit = false;
                message = "No ha editado ningun campo.";
            }

            if(toEdit){

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
    public void btnCancelClicked(ActionEvent event) {
        noEditMode();
        setValues();


    }

 
    @FXML
    public
    void btnEditClicked(ActionEvent event) {
        editMode();
    }

    @FXML
    public
    void btnGoBackClicked(ActionEvent event) throws IOException {
        searchUserView.start(event, userManager);
    }

    @FXML
    public void radBtnDisableClicked(ActionEvent event) {
        radBtnEnable.setSelected(false);
    }

    @FXML
    public void radBtnEnableClicked(ActionEvent event) {
        radBtnDisable.setSelected(false);

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        spltPaneActions.getItems().remove(brdPaneCancel);
        spltPaneActions.getItems().remove(brdPaneApply);
        setValues();
       
    }

    private boolean nmrsAreSame(){

            Array<String> nmrs = user.getPerson().getNumbers();

            if(!txtFieldNmrOne.getText().equals(nmrs.get(0))){

                if( !(txtFieldNmrOne.getText().isBlank() && (nmrs.get(0) == null))){
                    return false;
                }

            }

        
            if(!txtFieldNmrTwo.getText().equals(nmrs.get(1)) ){

                if( !(txtFieldNmrTwo.getText().isBlank() && (nmrs.get(1) == null))){
                    return false;
                }
                
            }

        
    
            if(! txtFieldNmrThree.getText().equals(nmrs.get(2))){
                if( !(txtFieldNmrThree.getText().isBlank() && (nmrs.get(2) == null))){
                    return false;
                }
                
            }

        

            if(!txtFieldNmrFour.getText().equals(nmrs.get(3))){
                if( !(txtFieldNmrFour.getText().isBlank()  && (nmrs.get(3) == null))){
                    return false;
                }
                
                
            }
        
            if(!txtFieldNmrFive.getText().equals(nmrs.get(4))){
                if( !(txtFieldNmrFive.getText().isBlank() && (nmrs.get(4) == null))){
                    return false;
                }
                
            }

        return true;
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
    private boolean getStatus(){
        if(radBtnDisable.isSelected()){
            return true;
        }else{
            return false;
        }
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
