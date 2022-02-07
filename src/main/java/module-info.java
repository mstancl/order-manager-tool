module com.mstancl.ordermanagertool {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.commons.lang3;


    opens com.mstancl.ordermanagertool to javafx.fxml;
    exports com.mstancl.ordermanagertool;

    exports com.mstancl.ordermanagertool.data;
    opens com.mstancl.ordermanagertool.data to javafx.fxml;

    exports com.mstancl.ordermanagertool.screens.mainScreen;
    opens com.mstancl.ordermanagertool.screens.mainScreen to javafx.fxml;

    exports com.mstancl.ordermanagertool.screens.order;
    opens com.mstancl.ordermanagertool.screens.order to javafx.fxml;
}