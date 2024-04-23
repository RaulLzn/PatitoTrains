package patitotrains.controller;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.StringConverter;
import patitotrains.model.domain.ContactPerson;
import patitotrains.model.domain.Passenger;
import patitotrains.model.manager.PurchaseManager;
import patitotrains.model.domain.types.IdType;
import patitotrains.view.RouteDataView;
import patitotrains.view.WelcomePageView;

import raul.Model.array.Array;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class PassengerDataController implements Initializable{

    @FXML
    private Button btnContinue;

    @FXML
    private Button btnGoBack;

    @FXML
    private ComboBox<IdType> cmbBoxTypeId;

    @FXML
    private Label lblMessage;

    @FXML
    private SplitPane spl1;

    @FXML
    private TextField txtFieldContactNmrFive;

    @FXML
    private TextField txtFieldContactNmrFour;

    @FXML
    private TextField txtFieldContactNmrOne;

    @FXML
    private TextField txtFieldContactNmrThree;

    @FXML
    private TextField txtFieldContactNmrTwo;

    @FXML
    private TextField txtFieldAddress;

    @FXML
    private TextField txtFieldContactLastName;

    @FXML
    private TextField txtFieldContactName;


    @FXML
    private TextField txtFieldId;

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
    private TextField txtFieldWeightOne;

    @FXML
    private TextField txtFieldWeightTwo;

    private PurchaseManager purchaseManager;

    public PassengerDataController() throws NotBoundException, RemoteException {
        purchaseManager = new PurchaseManager();
        purchaseManager.pullIdTypes();
    }
    @FXML
    void btnGoBackClicked(ActionEvent event) throws IOException {
        WelcomePageView welcomePageView = new WelcomePageView();
        welcomePageView.start(event);
    }


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
       setCmbSeatTypeValues();
    }

    @FXML
    void btnContinueClicked(ActionEvent event) throws IOException {
        String message = "";
        boolean valuesFormatOk;
        boolean toContinue = true;

        String name;
        String lastName;
        String identification;
        IdType idType;
        String address;
        int weightOne = 0;
        int weightTwo = 0;
        Array<String> numbers;
        String contactName;
        String contactLastName;
        Array<String> contactNumbers;
        

        valuesFormatOk = validateFormat();

        if(valuesFormatOk){
            name = txtFieldName.getText();
            lastName = txtFieldLastName.getText();
            identification = txtFieldId.getText();
            idType = cmbBoxTypeId.getValue();
            address = txtFieldAddress.getText();

            if(!txtFieldWeightOne.getText().isBlank()){
                weightOne = Integer.parseInt(txtFieldWeightOne.getText());
            }

            if(!txtFieldWeightTwo.getText().isBlank()){
                weightTwo = Integer.parseInt(txtFieldWeightTwo.getText());
            }
           
           
            
            contactName = txtFieldContactName.getText();
            contactLastName = txtFieldContactLastName.getText();
            

            if(weightOne != 0){
                if(!purchaseManager.validateLuggageWeight(weightOne)){
                    message = "La maleta 1 es demasiado pesada.";
                    toContinue = false;
                }
            }
            if(weightTwo != 0){
                if(!purchaseManager.validateLuggageWeight(weightTwo)){
                    message = "La maleta 2 es demasiado pesada.";
                    toContinue = false;
                }
             }
            if(toContinue){
                numbers = getArrayNumbers();
                contactNumbers = getArrayContactNumbers();
                ContactPerson contactPerson = new ContactPerson(contactName, contactLastName, contactNumbers);
                Passenger passenger = new Passenger(name, lastName, numbers, identification, idType, address, contactPerson);
                Array<Integer> weight = new Array<>(2);
                if(weightOne != 0){
                    weight.add(weightOne);
                }

                if(weightTwo != 0){
                    weight.add(weightTwo);
                }
                continueNextPage(event, passenger, weight);
                
            }
            lblMessage.setText(message);
        }

    }   

    private void continueNextPage(Event event, Passenger passenger, Array<Integer> weights) throws IOException{
        RouteDataView routeDataView = new RouteDataView();
        routeDataView.start(event, passenger, weights);
    }

    private boolean validateFormat() {
        String message = "";

        if(!validateTxtField(txtFieldName)){
            message = "Ingrese un valor valido en la seccion de nombre.";
            lblMessage.setText(message);
            return false;
        }

        if(!validateTxtField(txtFieldLastName)){
            message = "Ingrese un valor valido en la seccion de apellido.";
            lblMessage.setText(message);
            return false;
        }
        if(!validateTxtField(txtFieldId)){
            message = "Ingrese un valor valido en la seccion de identificación.";
            lblMessage.setText(message);
            return false;
        }
        if(!validateCmbBox(cmbBoxTypeId)){
            message = "Seleccione un tipo de identificación.";
            lblMessage.setText(message);
            return false;
        }
        if(!validateTxtField(txtFieldAddress)){
            message = "Ingrese un valor valido en la seccion de dirección.";
            lblMessage.setText(message);
            return false;
        }

        if(!txtFieldWeightOne.getText().isBlank()){
            if(!validateTxtFieldLuggage(txtFieldWeightOne)){
                message = "Ingrese un valor valido en la seccion del peso de la maleta 1.";
                lblMessage.setText(message);
                return false;
            }
        }
        
        if(!txtFieldWeightTwo.getText().isBlank()){
            if(!validateTxtFieldLuggage(txtFieldWeightTwo)){
                message = "Ingrese un valor valido en la seccion del peso de la maleta 2.";
                lblMessage.setText(message);
                return false;
            }
        }
        if(!validateTxtNumbers()){
            message = "Ingrese al menos uno de sus numeros telefónicos.";
            lblMessage.setText(message);
            return false;
        }

        if(!validateTxtField(txtFieldContactName)){
            message = "Ingrese un valor valido en la seccion de nombre de la persona de contacto.";
            lblMessage.setText(message);
            return false;
        }

        if(!validateTxtField(txtFieldLastName)){
            message = "Ingrese un valor valido en la seccion de apellido de la persona de contacto.";
            lblMessage.setText(message);
            return false;
        }

        if(!validateTxtContactNumbers()){
            message = "Ingrese al menos uno de los numeros telefónicos de su persona de contacto.";
            lblMessage.setText(message);
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
    private boolean validateTxtContactNumbers(){

        
        if(!txtFieldContactNmrOne.getText().isBlank()){
            return true;
        }
        if(!txtFieldContactNmrTwo.getText().isBlank()){
            return true;
        }
        
        if(!txtFieldContactNmrThree.getText().isBlank()){
            return true;
        }
        
        if(!txtFieldContactNmrFour.getText().isBlank()){
            return true;
        }
        
        if(!txtFieldContactNmrFive.getText().isBlank()){
            return true;
        }
        
        return false;
    }

    
    private boolean validateTxtField(TextField textField){
        
        if(textField.getText().isBlank()){
            return false;
        }
        
        return true;
    }

    private boolean validateTxtFieldLuggage(TextField textField){
        
        if(textField.getText().isBlank()){
            return true;
        }
    
        try {
            double weight = Double.parseDouble(textField.getText());
            if(weight <= 0){
                return false;
            }

            return true;
        } catch (NumberFormatException e) {
            return false;
        }

    }

    private boolean validateCmbBox(ComboBox<?> cmbBox){

        if(cmbBox.getSelectionModel().getSelectedItem() == null){
            return false;
        }

        return true;


    }


    private void setCmbSeatTypeValues(){
        cmbBoxTypeId.getItems().addAll(purchaseManager.getSeatTypeArray());

       cmbBoxTypeId.setCellFactory(param -> new ListCell<>() {
        @Override
        protected void updateItem(IdType item, boolean empty) {
            super.updateItem(item, empty);

            if (empty || item == null) {
                setText(null);
            } else {
                setText(item.getDescription()); 
            }
        }
    });

    
    cmbBoxTypeId.setConverter(new StringConverter<IdType>() {

        @Override
        public IdType fromString(String arg0) {
            return null;
        }

        @Override
        public String toString(IdType idType) {
            if (idType == null) {
                return null;
            } else {
                return idType.getDescription(); 
            }
        }
        
    });

    }

    private Array<String> getArrayNumbers(){

        Array<String> array = new Array<>(5) ;

        
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
    private Array<String> getArrayContactNumbers(){

        Array<String> array = new Array<>(5) ;

        
        if(!txtFieldContactNmrOne.getText().isBlank()){
            array.add(txtFieldContactNmrOne.getText());
        }
        if(!txtFieldContactNmrTwo.getText().isBlank()){
            array.add(txtFieldContactNmrTwo.getText());
        }
        
        if(!txtFieldContactNmrThree.getText().isBlank()){
            array.add(txtFieldContactNmrThree.getText());
        }
        
        if(!txtFieldContactNmrFour.getText().isBlank()){
            array.add(txtFieldContactNmrFour.getText());
        }
        
        if(!txtFieldContactNmrFive.getText().isBlank()){
            array.add(txtFieldContactNmrFive.getText());
        }
        
        return array;
    }
    
}
