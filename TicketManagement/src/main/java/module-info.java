module patitotrains {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.graphics;
    requires java.rmi;
    requires EstructurasDeDatos;

    opens patitotrains to javafx.fxml;
    opens patitotrains.controller to javafx.fxml;
    opens patitotrains.controller.loginController  to javafx.fxml;
    opens patitotrains.model.domain to javafx.base;
    
    exports patitotrains.view;
    exports patitotrains.controller;
    exports patitotrains.controller.loginController ;
    exports patitotrains;
    
    
}
