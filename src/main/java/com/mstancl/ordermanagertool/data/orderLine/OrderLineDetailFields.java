package com.mstancl.ordermanagertool.data.orderLine;

import com.mstancl.ordermanagertool.data.enums.HighlightColor;
import com.mstancl.ordermanagertool.data.pojo.Order;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class OrderLineDetailFields {

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

    List<Pane> listOfPaneFields = new ArrayList<>();
    List<Node> listOfAllNodes = new ArrayList<>();

    private final Order order;

    private HighlightColor highlightColor;

    private boolean toBeEdited;

    public OrderLineDetailFields(Order order) {
        this.order = order;
        setId_label(new Label(Long.toString(order.getId())));
        setCustomerName_textField(new TextField(order.getCustomer().getFirstName() + " " + order.getCustomer().getSurname()));
        setPhoneNumber_textField(new TextField(order.getCustomer().getPhoneNumber()));
        setEmailAddress_textField(new TextField(order.getCustomer().getEmail()));
        setDateWhenReceived_datePicker(new DatePicker(order.getDateWhenReceived()));
        setDueDate_datePicker(new DatePicker(order.getDueDate()));
        setOrderType_textField(new TextField(order.getOrderType()));
        setDescription_textArea(new TextField(order.getDescriptionOfOrder()));
        setSolution_textArea(new TextField(order.getSolutionForOrder()));
        setEstimatedPrice_textField(new TextField(String.valueOf(order.getEstimatedPrice())));
        setStatus_textField(new TextField(order.getStatus().getName()));
        setToBeEdited(false);
    }

    public HighlightColor getHighlightColor() {
        return highlightColor;
    }

    public void setHighlightColor(HighlightColor highlightColor) {
        this.highlightColor = highlightColor;
        if (highlightColor != null) {
            for (Node node : listOfAllNodes) {
                node.setStyle("-fx-border-color:" + highlightColor.getCode() + ";");
            }
        } else {
            for (Node node : listOfAllNodes) {
                node.setStyle(null);
            }
        }
    }

    public Order getOrder() {
        return order;
    }

    public Label getId_label() {
        return id_label;
    }

    public void setId_label(Label id_label) {
        this.id_label = id_label;
        this.listOfAllNodes.add(id_label);
    }

    public TextField getCustomerName_textField() {
        return customerName_textField;
    }

    public void setCustomerName_textField(TextField customerName_textField) {
        this.customerName_textField = customerName_textField;
        this.customerName_textField.setEditable(false);
        this.listOfAllNodes.add(customerName_textField);
    }

    public TextField getPhoneNumber_textField() {
        return phoneNumber_textField;
    }

    public void setPhoneNumber_textField(TextField phoneNumber_textField) {
        this.phoneNumber_textField = phoneNumber_textField;
        this.phoneNumber_textField.setEditable(false);
        this.listOfAllNodes.add(phoneNumber_textField);
    }

    public TextField getEmailAddress_textField() {
        return emailAddress_textField;
    }

    public void setEmailAddress_textField(TextField emailAddress_textField) {
        this.emailAddress_textField = emailAddress_textField;
        this.emailAddress_textField.setEditable(false);
        this.listOfAllNodes.add(emailAddress_textField);
    }

    public DatePicker getDateWhenReceived_datePicker() {
        return dateWhenReceived_datePicker;
    }

    public void setDateWhenReceived_datePicker(DatePicker dateWhenReceived_datePicker) {
        this.dateWhenReceived_datePicker = dateWhenReceived_datePicker;
        this.dateWhenReceived_datePicker.setEditable(false);
        this.listOfAllNodes.add(dateWhenReceived_datePicker);

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
        this.listOfAllNodes.add(dueDate_datePicker);
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
        this.listOfAllNodes.add(orderType_textField);
    }

    public TextField getDescription_textArea() {
        return description_textArea;
    }

    public void setDescription_textArea(TextField description_textArea) {
        this.description_textArea = description_textArea;
        this.description_textArea.setEditable(false);
        this.listOfAllNodes.add(description_textArea);
        this.description_textArea.setTooltip(new Tooltip(description_textArea.getText()));
    }

    public TextField getSolution_textArea() {
        return solution_textArea;
    }

    public void setSolution_textArea(TextField solution_textArea) {
        this.solution_textArea = solution_textArea;
        this.solution_textArea.setEditable(false);
        this.listOfAllNodes.add(solution_textArea);
        this.solution_textArea.setTooltip(new Tooltip(solution_textArea.getText()));
    }

    public TextField getEstimatedPrice_textField() {
        return estimatedPrice_textField;
    }

    public void setEstimatedPrice_textField(TextField estimatedPrice_textField) {
        this.estimatedPrice_textField = estimatedPrice_textField;
        this.estimatedPrice_textField.setEditable(false);
        this.listOfAllNodes.add(estimatedPrice_textField);
    }

    public TextField getStatus_textField() {
        return status_textField;
    }

    public void setStatus_textField(TextField status_textField) {
        this.status_textField = status_textField;
        this.status_textField.setEditable(false);
        this.listOfAllNodes.add(status_textField);
    }

    public List<Pane> getListOfPaneFields() {
        return listOfPaneFields;
    }

    public void setListOfPaneFields(List<Pane> listOfPaneFields) {
        this.listOfPaneFields = listOfPaneFields;
    }

    public void addPaneToTheListOfPanes(Pane pane) {
        this.listOfPaneFields.add(pane);
    }

    public List<Node> getListOfAllNodes() {
        return listOfAllNodes;
    }


    public boolean isToBeEdited() {
        return toBeEdited;
    }

    public void setToBeEdited(boolean toBeEdited) {
        this.toBeEdited = toBeEdited;
    }
}
