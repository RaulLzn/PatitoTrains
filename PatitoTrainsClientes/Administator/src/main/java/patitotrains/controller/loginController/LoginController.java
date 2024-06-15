package patitotrains.controller.loginController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import patitotrains.model.Managers.LoginManager;
import patitotrains.view.menuViews.MenuView;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * Clase que controla la vista de login
 */
public class LoginController {

    @FXML
    private Button btnLogin;

    @FXML
    private BorderPane pnlImage;

    @FXML
    private TextField txtFieldPassword;

    @FXML
    private TextField txtFieldUser;
    
    @FXML
    private Label lblMessage;

    private LoginManager loginManager;

    /**
     * Constructor de la clase
     */
    public LoginController() throws NotBoundException, RemoteException {
        loginManager = new LoginManager();
    }

    /**
     * Método que se ejecuta al presionar el botón de login
     * @param event Evento
     * @throws Exception
     */
    @FXML
    void btnLoginClicked(ActionEvent event) throws Exception {
        MenuView menuView = new MenuView();
        String user = txtFieldUser.getText();
        String password = txtFieldPassword.getText();

        if(loginManager.login(user, password)){
            menuView.start(event);
        }else{
            lblMessage.setText("El usuario o contraseña son incorrectos.");
        }
    }

}
