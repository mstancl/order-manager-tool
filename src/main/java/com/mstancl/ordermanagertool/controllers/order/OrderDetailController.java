package com.mstancl.ordermanagertool.controllers.order;

import com.mstancl.ordermanagertool.data.pojo.Customer;
import com.mstancl.ordermanagertool.data.pojo.Order;
import com.mstancl.ordermanagertool.data.OrderDetailFields;
import com.mstancl.ordermanagertool.data.Status;
import com.mstancl.ordermanagertool.controllers.mainScreen.MainScreenController;
import com.mstancl.ordermanagertool.util.FXMLoaderManager;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

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

        Customer customer = new Customer(StringUtils.capitalize(firstName_textField.getText()) + " " + StringUtils.capitalize(surname_textField.getText()), phoneNumber_textField.getText(), emailAddress_textField.getText());
        Order order = new Order(customer, dateWhenReceived_datePicker.getValue(), dueDate_datePicker.getValue(), orderType_textField.getText(), description_textArea.getText(), solution_textArea.getText(), Integer.parseInt(estimatedPrice_textField.getText()), Status.IN_PROGRESS);
        OrderDetailFields orderDetailFields = new OrderDetailFields(order);


        mainScreenController.orderGrid_grid.add(orderDetailFields.getCustomerName_textField(), 0, mainScreenController.orderCounter);
        mainScreenController.orderGrid_grid.add(orderDetailFields.getPhoneNumber_textField(), 1, mainScreenController.orderCounter);
        mainScreenController.orderGrid_grid.add(orderDetailFields.getEmailAddress_textField(), 2, mainScreenController.orderCounter);
        mainScreenController.orderGrid_grid.add(orderDetailFields.getDateWhenReceived_datePicker(), 3, mainScreenController.orderCounter);
        mainScreenController.orderGrid_grid.add(orderDetailFields.getDueDate_datePicker(), 4, mainScreenController.orderCounter);
        mainScreenController.orderGrid_grid.add(orderDetailFields.getOrderType_textField(), 5, mainScreenController.orderCounter);
        mainScreenController.orderGrid_grid.add(orderDetailFields.getDescription_textArea(), 6, mainScreenController.orderCounter);
        mainScreenController.orderGrid_grid.add(orderDetailFields.getSolution_textArea(), 7, mainScreenController.orderCounter);
        mainScreenController.orderGrid_grid.add(orderDetailFields.getEstimatedPrice_textField(), 8, mainScreenController.orderCounter);
        mainScreenController.orderGrid_grid.add(orderDetailFields.getStatus_textField(), 9, mainScreenController.orderCounter);

        mainScreenController.orderCounter++;
        mainScreenController.listOfOrders.add(order);

        mainScreenController.orderDetailsStage.close();


    }


}
