module com.mstancl.ordermanagertool {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.mstancl.ordermanagertool to javafx.fxml;
    exports com.mstancl.ordermanagertool;

    exports com.mstancl.ordermanagertool.data;
    opens com.mstancl.ordermanagertool.data to javafx.fxml;

    exports com.mstancl.ordermanagertool.mainScreen;
    opens com.mstancl.ordermanagertool.mainScreen to javafx.fxml;

    exports com.mstancl.ordermanagertool.order;
    opens com.mstancl.ordermanagertool.order to javafx.fxml;
}