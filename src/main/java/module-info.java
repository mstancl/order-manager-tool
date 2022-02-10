module com.mstancl.ordermanagertool {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.commons.lang3;
    requires java.sql;


    opens com.mstancl.ordermanagertool to javafx.fxml;
    exports com.mstancl.ordermanagertool;

    exports com.mstancl.ordermanagertool.data;
    opens com.mstancl.ordermanagertool.data to javafx.fxml;

    exports com.mstancl.ordermanagertool.controllers.mainScreen;
    opens com.mstancl.ordermanagertool.controllers.mainScreen to javafx.fxml;

    exports com.mstancl.ordermanagertool.controllers.order;
    opens com.mstancl.ordermanagertool.controllers.order to javafx.fxml;

    opens com.mstancl.ordermanagertool.dao to javafx.fxml;
    exports com.mstancl.ordermanagertool.dao;

    opens com.mstancl.ordermanagertool.database to javafx.fxml;
    exports com.mstancl.ordermanagertool.database;


}