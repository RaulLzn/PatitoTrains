module patitotrains {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;
    requires java.rmi;
    requires EstructurasDeDatos;

    opens patitotrains.controller.trainManagementController to javafx.fxml;
    opens patitotrains.controller.userManagementController to javafx.fxml;
    opens patitotrains.controller.menuController to javafx.fxml;
    opens patitotrains.controller.routeManagementController to javafx.fxml;
    opens patitotrains.controller.boardingPanelController to javafx.fxml;
    opens patitotrains.controller.loginController to javafx.fxml;
    opens patitotrains.model.domain to javafx.base;
    
    exports patitotrains.view.trainManagementViews;
    exports patitotrains.view.userManagementViews;
    exports patitotrains.view.menuViews;
    exports patitotrains.view.boardingPanelView;
    exports patitotrains.view.routeManagementView;
    opens patitotrains.view.loginView to javafx.fxml;
    exports patitotrains;
    opens patitotrains.model.domain.types to javafx.base;


}
