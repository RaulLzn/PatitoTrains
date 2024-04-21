module upb.train {
    requires javafx.controls;
    requires javafx.fxml;
    requires DataStructure;
    requires javafx.base;
    requires javafx.graphics;

    opens upb.ticketmanagement to javafx.fxml;
    opens upb.ticketmanagement.controller to javafx.fxml;
    opens upb.ticketmanagement.controller.loginController  to javafx.fxml;
    opens upb.ticketmanagement.model.domain to javafx.base;
    
    exports upb.ticketmanagement.view;
    exports upb.ticketmanagement.controller;
    exports upb.ticketmanagement.controller.loginController ;
    exports upb.ticketmanagement;
    
    
}
