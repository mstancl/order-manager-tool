package com.mstancl.ordermanagertool.data;

import com.mstancl.ordermanagertool.data.pojo.Order;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;

public class OrderDetailFields {

    Label id_label;

    TextField customerName_textField;

    TextField phoneNumber_textField;

    TextField emailAddress_textField;

    DatePicker dateWhenReceived_datePicker;

    DatePicker dueDate_datePicker;

    TextField orderType_textField;

    TextField description_textArea;

    TextField solution_textArea;

    TextField estimatedPrice_textField;

    TextField status_textField;


    public OrderDetailFields(Order order) {

        setId_label(new Label(Long.toString(order.getId())));
        setCustomerName_textField(new TextField(order.getCustomer().getFullName()));
        setPhoneNumber_textField(new TextField(order.getCustomer().getPhoneNumber()));
        setEmailAddress_textField(new TextField(order.getCustomer().getEmail()));
        setDateWhenReceived_datePicker(new DatePicker(order.getDateWhenReceived()));
        setDueDate_datePicker(new DatePicker(order.getDueDate()));
        setOrderType_textField(new TextField(order.getOrderType()));
        setDescription_textArea(new TextField(order.getDescriptionOfOrder()));
        setSolution_textArea(new TextField(order.getSolutionForOrder()));
        setEstimatedPrice_textField(new TextField(String.valueOf(order.getEstimatedPrice())));
        setStatus_textField(new TextField(order.getStatus().getName()));
    }

    public Label getId_label() {
        return id_label;
    }

    public void setId_label(Label id_label) {
        this.id_label = id_label;
    }

    public TextField getCustomerName_textField() {
        return customerName_textField;
    }

    public void setCustomerName_textField(TextField customerName_textField) {
        this.customerName_textField = customerName_textField;
        this.customerName_textField.setEditable(false);
    }

    public TextField getPhoneNumber_textField() {
        return phoneNumber_textField;
    }

    public void setPhoneNumber_textField(TextField phoneNumber_textField) {
        this.phoneNumber_textField = phoneNumber_textField;
        this.phoneNumber_textField.setEditable(false);
    }

    public TextField getEmailAddress_textField() {
        return emailAddress_textField;
    }

    public void setEmailAddress_textField(TextField emailAddress_textField) {
        this.emailAddress_textField = emailAddress_textField;
        this.emailAddress_textField.setEditable(false);
    }

    public DatePicker getDateWhenReceived_datePicker() {
        return dateWhenReceived_datePicker;
    }

    public void setDateWhenReceived_datePicker(DatePicker dateWhenReceived_datePicker) {
        this.dateWhenReceived_datePicker = dateWhenReceived_datePicker;
        this.dateWhenReceived_datePicker.setEditable(false);

        this.dateWhenReceived_datePicker.setOnMouseClicked(e -> {
            if (!this.dateWhenReceived_datePicker.isEditable())
                this.dateWhenReceived_datePicker.hide();
        });


    }

    public DatePicker getDueDate_datePicker() {
        return dueDate_datePicker;
    }

    public void setDueDate_datePicker(DatePicker dueDate_datePicker) {
        this.dueDate_datePicker = dueDate_datePicker;
        this.dueDate_datePicker.setEditable(false);
        this.dueDate_datePicker.setOnMouseClicked(e -> {
            if (!this.dueDate_datePicker.isEditable())
                this.dueDate_datePicker.hide();
        });

    }

    public TextField getOrderType_textField() {
        return orderType_textField;
    }

    public void setOrderType_textField(TextField orderType_textField) {
        this.orderType_textField = orderType_textField;
        this.orderType_textField.setEditable(false);
    }

    public TextField getDescription_textArea() {
        return description_textArea;
    }

    public void setDescription_textArea(TextField description_textArea) {
        this.description_textArea = description_textArea;
        this.description_textArea.setEditable(false);
        this.description_textArea.setTooltip(new Tooltip(description_textArea.getText()));
    }

    public TextField getSolution_textArea() {
        return solution_textArea;
    }

    public void setSolution_textArea(TextField solution_textArea) {
        this.solution_textArea = solution_textArea;
        this.solution_textArea.setEditable(false);
        this.solution_textArea.setTooltip(new Tooltip(solution_textArea.getText()));
    }

    public TextField getEstimatedPrice_textField() {
        return estimatedPrice_textField;
    }

    public void setEstimatedPrice_textField(TextField estimatedPrice_textField) {
        this.estimatedPrice_textField = estimatedPrice_textField;
        this.estimatedPrice_textField.setEditable(false);
    }

    public TextField getStatus_textField() {
        return status_textField;
    }

    public void setStatus_textField(TextField status_textField) {
        this.status_textField = status_textField;
        this.status_textField.setEditable(false);
    }
}
