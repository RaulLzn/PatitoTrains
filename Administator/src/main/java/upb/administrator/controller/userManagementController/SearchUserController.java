package upb.administrator.controller.userManagementController;

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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import upb.administrator.controller.userManagementController.interfaces.SearchUserControllerInterface;
import upb.administrator.model.Managers.UserManager;
import upb.administrator.model.domain.User;
import upb.administrator.view.menuViews.MenuView;
import upb.administrator.view.userManagementViews.EditUserView;
import upb.administrator.view.userManagementViews.RegisterUserView;

public class SearchUserController implements Initializable, SearchUserControllerInterface{

    @FXML
    private Button btnGoBack;

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


    private RegisterUserView registerUserView;

    public SearchUserController( UserManager userManager){
        this.userManager = userManager;
        registerUserView = new RegisterUserView();
    }

    public SearchUserController(){
        userManager = new UserManager();
      
        registerUserView = new RegisterUserView();
    }

    @FXML
    void btnGoBackClicked(ActionEvent event) throws Exception {
        MenuView menuView = new MenuView();
        menuView.start( event);
    }

    @FXML
    public void btnRegisterUserClicked( ActionEvent event) throws IOException {
        registerUserView.start(event, userManager);
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

            return new SimpleStringProperty(cellData.getValue().getId());

        });

        columnName.setCellValueFactory(cellData -> {
            String type = cellData.getValue().getNames();

            return new SimpleStringProperty(type);

        });

        columnLastName.setCellValueFactory(cellData -> {
            String type = cellData.getValue().getLastNames();

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
                try {
                    EditUserView editUserView = new EditUserView();
                    editUserView.start(event,  cellData.getValue(), userManager);
                } catch (IOException e) {
                }

                
            });

            return new SimpleObjectProperty<Button>(buttonView);

        });

    }

}
