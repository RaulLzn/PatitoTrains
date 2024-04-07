module upb.train {
    requires javafx.controls;
    requires javafx.fxml;
    requires DataStructure;
    requires javafx.graphics;
    requires javafx.base;

    opens upb.usermanagement to javafx.fxml;
    opens upb.usermanagement.controller to javafx.fxml;
    opens upb.usermanagement.model.domain to javafx.base;
    
    exports upb.usermanagement.view;
    exports upb.usermanagement.controller;
    exports upb.usermanagement;
    
    
}
