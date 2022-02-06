package com.mstancl.ordermanagertool.order;

import com.mstancl.ordermanagertool.data.Status;
import com.mstancl.ordermanagertool.mainScreen.MainScreenController;
import com.mstancl.ordermanagertool.util.FXMLoaderManager;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

import javafx.scene.layout.StackPane;
import javafx.stage.Popup;
import org.apache.commons.lang3.StringUtils;


public class OrderDetailController {


    @FXML
    public Button confirmOrder_button;

    @FXML
    public TextField firstName_textField;

    @FXML
    public TextField surname_textField;

    @FXML
    public TextField emailAddress_textField;

    @FXML
    public TextField phoneNumber_textField;

    @FXML
    public DatePicker dateWhenReceived_datePicker;

    @FXML
    public DatePicker dueDate_datePicker;

    @FXML
    public TextField orderType_textField;

    @FXML
    public TextField estimatedPrice_textField;

    @FXML
    public TextArea description_textArea;

    @FXML
    public TextArea solution_textArea;


    @FXML
    public void confirmOrder() {
        MainScreenController mainScreenController = FXMLoaderManager.getFxmLoader().getController();

        RowConstraints newOrderRow = new RowConstraints();
        newOrderRow.setVgrow(Priority.NEVER);
        newOrderRow.setMaxHeight(40);

        mainScreenController.orderGrid_grid.getRowConstraints().add(mainScreenController.orderCounter, newOrderRow);

        TextField customerName = new TextField(StringUtils.capitalize(firstName_textField.getText()) + " " + StringUtils.capitalize(surname_textField.getText()));
        customerName.setEditable(false);

        TextField phoneNumber = new TextField(phoneNumber_textField.getText());
        phoneNumber.setEditable(false);

        TextField emailAddress = new TextField(emailAddress_textField.getText());
        emailAddress.setEditable(false);

        DatePicker dateWhenReceived = new DatePicker(dateWhenReceived_datePicker.getValue());
        dateWhenReceived.setEditable(false);

        dateWhenReceived.setOnMouseClicked(e -> {
            if (!dateWhenReceived.isEditable())
                dateWhenReceived.hide();
        });

        DatePicker dueDate = new DatePicker(dueDate_datePicker.getValue());
        dueDate.setEditable(false);

        dueDate.setOnMouseClicked(e -> {
            if (!dueDate.isEditable())
                dueDate.hide();
        });


        TextField orderType = new TextField(orderType_textField.getText());
        orderType.setEditable(false);

        TextField description = new TextField(description_textArea.getText());
        description.setEditable(false);
        description.setTooltip(new Tooltip(description_textArea.getText()));

        TextField solution = new TextField(solution_textArea.getText());
        solution.setEditable(false);
        solution.setTooltip(new Tooltip(solution_textArea.getText()));

        TextField estimatedPrice = new TextField(estimatedPrice_textField.getText());
        estimatedPrice.setEditable(false);

        TextField status = new TextField(Status.IN_PROGRESS.getName());
        status.setEditable(false);

        mainScreenController.orderGrid_grid.add(customerName, 0, mainScreenController.orderCounter);
        mainScreenController.orderGrid_grid.add(phoneNumber, 1, mainScreenController.orderCounter);
        mainScreenController.orderGrid_grid.add(emailAddress, 2, mainScreenController.orderCounter);
        mainScreenController.orderGrid_grid.add(dateWhenReceived, 3, mainScreenController.orderCounter);
        mainScreenController.orderGrid_grid.add(dueDate, 4, mainScreenController.orderCounter);
        mainScreenController.orderGrid_grid.add(orderType, 5, mainScreenController.orderCounter);
        mainScreenController.orderGrid_grid.add(description, 6, mainScreenController.orderCounter);
        mainScreenController.orderGrid_grid.add(solution, 7, mainScreenController.orderCounter);
        mainScreenController.orderGrid_grid.add(estimatedPrice, 8, mainScreenController.orderCounter);
        mainScreenController.orderGrid_grid.add(status, 9, mainScreenController.orderCounter);

        mainScreenController.orderCounter++;

        mainScreenController.orderDetailsStage.close();


    }


}
