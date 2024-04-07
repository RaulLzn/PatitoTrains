
package upb.usermanagement.controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.io.IOException;
import com.edu.upb.linkedList.doubly.LinkedList;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import upb.usermanagement.model.domain.Employee;
import upb.usermanagement.model.domain.User;
import upb.usermanagement.model.domain.UserManager;

public class SearchUserController implements Initializable{

    @FXML
    private Button btnRegisterUser;

    @FXML
    private TableColumn<User, Button> columnEdit;

    @FXML
    private TableColumn<User, String> columnId;

    @FXML
    private TableColumn<User, String> columnLastName;

    @FXML
    private TableColumn<User, String> columnName;

    @FXML
    private TableColumn<User, String> columnState;

    @FXML
    private TableColumn<User, String> columnUser;

    @FXML
    private TableView<User> tableUsers;

    @FXML
    private TextField txtFieldSearch;

    private UserManager userManager;

    public SearchUserController(@SuppressWarnings("exports") UserManager userManager){
        this.userManager = userManager;
    }

    public SearchUserController(){
        userManager = new UserManager();
    }


    @FXML
    void btnRegisterUserClicked(ActionEvent event) {
        Stage stageRegister;

        Scene sceneRegister;
    

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/RegisterUser.fxml"));
        loader.setControllerFactory(controllerClass -> new RegisterUserController( userManager));
        Parent root;
        try {
            root = loader.load();
            stageRegister = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        sceneRegister = new Scene(root);
        
        stageRegister.setScene(sceneRegister);
        stageRegister.setMaximized(false);
        stageRegister.setMaximized(true);
        stageRegister.show();
        } catch (IOException e) {
            
        }
        

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        ObservableList<User> userObservableList;

        userObservableList = FXCollections.observableArrayList();

        txtFieldSearch.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                String input = txtFieldSearch.getText();

                LinkedList<User> newTicketList = userManager.searchUser(input);
                userObservableList.setAll(userManager.userArray(newTicketList));
                tableUsers.setItems(userObservableList);

            }
        });

        userObservableList.setAll(userManager.userArray());
        tableUsers.setItems(userObservableList);
        setColumnsValues();
       
    }

    private void setColumnsValues(){
        columnId.setCellValueFactory(cellData -> {
            Employee type = (Employee) cellData.getValue().getPerson();

            return new SimpleStringProperty(type.getId());

        });

        columnName.setCellValueFactory(cellData -> {
            String type = cellData.getValue().getPerson().getNames();

            return new SimpleStringProperty(type);

        });

        columnLastName.setCellValueFactory(cellData -> {
            String type = cellData.getValue().getPerson().getLastNames();

            return new SimpleStringProperty(type);

        });

        columnUser.setCellValueFactory(cellData -> {
            String type = cellData.getValue().getUserName();

            return new SimpleStringProperty(type);

        });

        columnState.setCellValueFactory(cellData -> {
            String type ;
            if( cellData.getValue().isDisabled()){
                type = "Desahabilitado";
            }else{
                type = "Habilitado";
            }

            return new SimpleStringProperty(type);

        });


        columnEdit.setCellValueFactory(cellData -> {
            Button buttonView;
    
            buttonView = new Button();
            buttonView.setText("Editar");

            buttonView.setOnAction(event -> {
                

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/EditUser.fxml"));
                loader.setControllerFactory(controllerClass -> new EditUserController(cellData.getValue(),  userManager));
                Parent root;
                try {
                    Scene sceneView;
                    root = loader.load();
                    sceneView = new Scene(root);
                    Stage stageView;
                    stageView = (Stage) ((Node)event.getSource()).getScene().getWindow();
                    stageView.setScene(sceneView);
                    stageView.setMaximized(false);
                    stageView.setMaximized(true);
                    stageView.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }  
            });

            return new SimpleObjectProperty<Button>(buttonView);

        });

    }

}
