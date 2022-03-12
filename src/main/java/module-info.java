module com.mstancl.ordermanagertool {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.commons.lang3;
    requires java.sql;
    requires org.xerial.sqlitejdbc;
    requires com.google.common;


    exports com.mstancl.ordermanagertool;
    requires itextpdf;


    exports com.mstancl.ordermanagertool.data.pojo;
    opens com.mstancl.ordermanagertool.data.pojo to javafx.fxml;

    exports com.mstancl.ordermanagertool.data.orderLine;
    opens com.mstancl.ordermanagertool.data.orderLine to javafx.fxml;

    exports com.mstancl.ordermanagertool.data.enums;
    opens com.mstancl.ordermanagertool.data.enums to javafx.fxml;

    exports com.mstancl.ordermanagertool.controllers.mainScreen;
    opens com.mstancl.ordermanagertool.controllers.mainScreen to javafx.fxml;

    exports com.mstancl.ordermanagertool.controllers.order;
    opens com.mstancl.ordermanagertool.controllers.order to javafx.fxml;

    opens com.mstancl.ordermanagertool.dao to javafx.fxml;
    exports com.mstancl.ordermanagertool.dao;

    opens com.mstancl.ordermanagertool.database to javafx.fxml;
    exports com.mstancl.ordermanagertool.database;


}