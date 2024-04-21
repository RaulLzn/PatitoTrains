module upb.train {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires DataStructure;
    requires javafx.base;

    opens upb.administrator.controller.trainManagementController to javafx.fxml;
    opens upb.administrator.controller.userManagementController to javafx.fxml;
    opens upb.administrator.controller.menuController to javafx.fxml;
    opens upb.administrator.controller.routeManagementController to javafx.fxml;
    opens upb.administrator.controller.boardingPanelController to javafx.fxml;
    opens upb.administrator.controller.loginController to javafx.fxml;
    opens upb.administrator.model.domain to javafx.base;
    
    exports upb.administrator.view.trainManagementViews;
    exports upb.administrator.view.userManagementViews;
    exports upb.administrator.view.menuViews;
    exports upb.administrator.view.boardingPanelView;
    exports upb.administrator.view.routeManagementView;
    opens upb.administrator.view.loginView to javafx.fxml;
    exports upb.administrator;
    
    
}
