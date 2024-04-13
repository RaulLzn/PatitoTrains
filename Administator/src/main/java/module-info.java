module upb.train {
    requires javafx.controls;
    requires javafx.fxml;
    requires DataStructure;
    requires javafx.graphics;
    requires javafx.base;

    opens upb.administrator.controller.trainManagementController to javafx.fxml;
    opens upb.administrator.controller.userManagementController to javafx.fxml;
    opens upb.administrator.controller.menuController to javafx.fxml;
    opens upb.administrator.controller.boardingPanelController to javafx.fxml;

    opens upb.administrator.model.domain to javafx.base;
    
    exports upb.administrator.view.trainManagementViews;
    exports upb.administrator.view.userManagementViews;
    exports upb.administrator.view.menuViews;
    exports upb.administrator.view.boardingPanelView;
    exports upb.administrator;
    
    
}
