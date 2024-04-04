module upb.train {
    requires javafx.controls;
    requires javafx.fxml;
    requires DataStructure;

    opens upb.trainmanagement to javafx.fxml;
    opens upb.trainmanagement.controller to javafx.fxml;
    opens upb.trainmanagement.model.domain to javafx.base;
    
    exports upb.trainmanagement.view;
    exports upb.trainmanagement.controller;
    exports upb.trainmanagement;
    
    
}
