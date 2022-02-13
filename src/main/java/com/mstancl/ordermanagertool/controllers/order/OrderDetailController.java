package com.mstancl.ordermanagertool.controllers.order;

import com.mstancl.ordermanagertool.controllers.mainScreen.MainScreenController;
import com.mstancl.ordermanagertool.dao.OrderDAO;
import com.mstancl.ordermanagertool.data.enums.Status;
import com.mstancl.ordermanagertool.data.pojo.Customer;
import com.mstancl.ordermanagertool.data.pojo.Order;
import com.mstancl.ordermanagertool.util.FXMLoaderManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.apache.commons.lang3.StringUtils;


public class OrderDetailController {


    private final OrderDAO orderDAO = new OrderDAO();

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

    private long orderID = -1;


    @FXML
    public void confirmOrder() {
        MainScreenController mainScreenController = FXMLoaderManager.getFxmLoader().getController();

        Customer customer = new Customer(StringUtils.capitalize(firstName_textField.getText().toLowerCase().trim()), StringUtils.capitalize(surname_textField.getText().toLowerCase().trim()), phoneNumber_textField.getText(), emailAddress_textField.getText());
        Order order = new Order(orderID == -1 ? mainScreenController.orderCounter + 1 : orderID, customer, dateWhenReceived_datePicker.getValue(), dueDate_datePicker.getValue(), orderType_textField.getText(), description_textArea.getText(), solution_textArea.getText(), Integer.parseInt(estimatedPrice_textField.getText()), Status.IN_PROGRESS);

        mainScreenController.addOrdersToOrderGrid(order);

        if (orderDAO.getRecordByID(order.getId()) == null) {
            orderDAO.write(order);
        } else {
            orderDAO.update(order);
            mainScreenController.removeAllRowsFromOrderGrid();
            mainScreenController.addOrdersToOrderGrid(orderDAO.getAllRecords());
        }

        mainScreenController.orderDetailsStage.close();

    }

    public Button getConfirmOrder_button() {
        return confirmOrder_button;
    }

    public void setConfirmOrder_button(Button confirmOrder_button) {
        this.confirmOrder_button = confirmOrder_button;
    }

    public TextField getFirstName_textField() {
        return firstName_textField;
    }

    public void setFirstName_textField(TextField firstName_textField) {
        this.firstName_textField = firstName_textField;
    }

    public TextField getSurname_textField() {
        return surname_textField;
    }

    public void setSurname_textField(TextField surname_textField) {
        this.surname_textField = surname_textField;
    }

    public TextField getEmailAddress_textField() {
        return emailAddress_textField;
    }

    public void setEmailAddress_textField(TextField emailAddress_textField) {
        this.emailAddress_textField = emailAddress_textField;
    }

    public TextField getPhoneNumber_textField() {
        return phoneNumber_textField;
    }

    public void setPhoneNumber_textField(TextField phoneNumber_textField) {
        this.phoneNumber_textField = phoneNumber_textField;
    }

    public DatePicker getDateWhenReceived_datePicker() {
        return dateWhenReceived_datePicker;
    }

    public void setDateWhenReceived_datePicker(DatePicker dateWhenReceived_datePicker) {
        this.dateWhenReceived_datePicker = dateWhenReceived_datePicker;
    }

    public DatePicker getDueDate_datePicker() {
        return dueDate_datePicker;
    }

    public void setDueDate_datePicker(DatePicker dueDate_datePicker) {
        this.dueDate_datePicker = dueDate_datePicker;
    }

    public TextField getOrderType_textField() {
        return orderType_textField;
    }

    public void setOrderType_textField(TextField orderType_textField) {
        this.orderType_textField = orderType_textField;
    }

    public TextField getEstimatedPrice_textField() {
        return estimatedPrice_textField;
    }

    public void setEstimatedPrice_textField(TextField estimatedPrice_textField) {
        this.estimatedPrice_textField = estimatedPrice_textField;
    }

    public TextArea getDescription_textArea() {
        return description_textArea;
    }

    public void setDescription_textArea(TextArea description_textArea) {
        this.description_textArea = description_textArea;
    }

    public TextArea getSolution_textArea() {
        return solution_textArea;
    }

    public void setSolution_textArea(TextArea solution_textArea) {
        this.solution_textArea = solution_textArea;
    }

    public long getOrderID() {
        return orderID;
    }

    public void setOrderID(long orderID) {
        this.orderID = orderID;
    }
}
