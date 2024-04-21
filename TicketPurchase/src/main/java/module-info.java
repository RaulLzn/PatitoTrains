module upb.train {
    requires javafx.controls;
    requires javafx.fxml;
    requires DataStructure;
    requires javafx.base;
    requires javafx.graphics;

    opens upb.ticketpurchase to javafx.fxml;
    opens upb.ticketpurchase.controller to javafx.fxml;
    opens upb.ticketpurchase.model.domain to javafx.base;
    
    
    exports upb.ticketpurchase.view;
    exports upb.ticketpurchase.controller;
    exports upb.ticketpurchase;
    
    
}
