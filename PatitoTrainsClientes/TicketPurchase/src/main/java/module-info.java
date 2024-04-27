module patitotrains {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.graphics;
    requires EstructurasDeDatos;
    requires java.rmi;

    opens patitotrains to javafx.fxml;
    opens patitotrains.controller to javafx.fxml;
    opens patitotrains.model.domain to javafx.fxml;
    
    exports patitotrains;
    exports patitotrains.view;
    exports patitotrains.controller;
    exports patitotrains.model.domain;
    exports patitotrains.view.interfaces;


    
}
