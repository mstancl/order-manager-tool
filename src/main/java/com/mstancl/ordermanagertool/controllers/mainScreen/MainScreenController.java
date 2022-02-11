package com.mstancl.ordermanagertool.controllers.mainScreen;

import com.google.common.collect.Comparators;
import com.mstancl.ordermanagertool.Main;
import com.mstancl.ordermanagertool.dao.OrderDAO;
import com.mstancl.ordermanagertool.data.OrderDetailFields;
import com.mstancl.ordermanagertool.data.pojo.Order;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MainScreenController {


    @FXML
    public Button addNewOrder_button;

    @FXML
    public Button editOrder_button;

    @FXML
    public GridPane orderGrid_grid;

    public Stage orderDetailsStage;

    public int orderCounter;

    public List<Order> listOfOrders = new ArrayList<>();
    public List<OrderDetailFields> listOfOrderFields = new ArrayList<>();

    private final OrderDAO orderDAO = new OrderDAO();

    RowConstraints newOrderRow = new RowConstraints();

    @FXML
    public void initialize() {
        removeAllRowsFromOrderGrid();

        newOrderRow.setVgrow(Priority.NEVER);
        newOrderRow.setMaxHeight(40);

        addOrdersToOrderGrid(orderDAO.getAllRecords());

    }

    @FXML
    public void addNewOrder() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("newOrderScreen.fxml"));
        Parent root = fxmlLoader.load();
        orderDetailsStage = new Stage();
        orderDetailsStage.initModality(Modality.APPLICATION_MODAL);
        orderDetailsStage.setTitle("ABC");
        orderDetailsStage.setScene(new Scene(root));
        orderDetailsStage.show();

    }


    @FXML
    public void editOrder() {
        removeAllRowsFromOrderGrid();
    }

    @FXML
    public void orderGridRowClicked(MouseEvent e) {
        Node source = e.getPickResult().getIntersectedNode();
        int rowIndex = GridPane.getRowIndex(source);
        for (Node node : orderGrid_grid.getChildren()) {
            if (GridPane.getRowIndex(node) == rowIndex && node instanceof Pane && GridPane.getColumnIndex(node) != 0 && GridPane.getColumnIndex(node) != 12) {
                node.setStyle("-fx-border-color:#0000FF;");
            }
        }

    }

    @FXML
    public void orderByID() {
        List<Order> sortedList = listOfOrders;

        if (Comparators.isInOrder(sortedList, Comparator.comparing(Order::getId))) {
            sortedList.sort(Comparator.comparing(Order::getId).reversed());
        } else {
            sortedList.sort(Comparator.comparing(Order::getId));
        }

        removeAllRowsFromOrderGrid();
        addOrdersToOrderGrid(sortedList);
    }

    @FXML
    public void orderByDateWhenReceived() {
        List<Order> sortedList = listOfOrders;

        if (Comparators.isInOrder(sortedList, Comparator.comparing(Order::getDateWhenReceived))) {
            sortedList.sort(Comparator.comparing(Order::getDateWhenReceived).reversed());
        } else {
            sortedList.sort(Comparator.comparing(Order::getDateWhenReceived));
        }

        removeAllRowsFromOrderGrid();
        addOrdersToOrderGrid(sortedList);
    }

    @FXML
    public void orderByDueDate() {
        List<Order> sortedList = listOfOrders;

        if (Comparators.isInOrder(sortedList, Comparator.comparing(Order::getDueDate))) {
            sortedList.sort(Comparator.comparing(Order::getDueDate).reversed());
        } else {
            sortedList.sort(Comparator.comparing(Order::getDueDate));
        }

        removeAllRowsFromOrderGrid();
        addOrdersToOrderGrid(sortedList);
    }


    @FXML
    public void orderByEstimatedPrice() {
        List<Order> sortedList = listOfOrders;

        if (Comparators.isInOrder(sortedList, Comparator.comparing(Order::getEstimatedPrice))) {
            sortedList.sort(Comparator.comparing(Order::getEstimatedPrice).reversed());
        } else {
            sortedList.sort(Comparator.comparing(Order::getEstimatedPrice));
        }

        removeAllRowsFromOrderGrid();
        addOrdersToOrderGrid(sortedList);
    }

    public void addOrdersToOrderGrid(List<Order> sortedList) {
        for (Order order : sortedList) {
            addOrdersToOrderGrid(order);
        }
    }

    public void addOrdersToOrderGrid(Order order) {
        orderGrid_grid.getRowConstraints().add(orderCounter, newOrderRow);
        OrderDetailFields orderDetailFields = new OrderDetailFields(order);

        orderGrid_grid.add(orderDetailFields.getId_label(), 1, orderCounter);
        orderGrid_grid.add(orderDetailFields.getCustomerName_textField(), 2, orderCounter);
        orderGrid_grid.add(orderDetailFields.getPhoneNumber_textField(), 3, orderCounter);
        orderGrid_grid.add(orderDetailFields.getEmailAddress_textField(), 4, orderCounter);
        orderGrid_grid.add(orderDetailFields.getDateWhenReceived_datePicker(), 5, orderCounter);
        orderGrid_grid.add(orderDetailFields.getDueDate_datePicker(), 6, orderCounter);
        orderGrid_grid.add(orderDetailFields.getOrderType_textField(), 7, orderCounter);
        orderGrid_grid.add(orderDetailFields.getDescription_textArea(), 8, orderCounter);
        orderGrid_grid.add(orderDetailFields.getSolution_textArea(), 9, orderCounter);
        orderGrid_grid.add(orderDetailFields.getEstimatedPrice_textField(), 10, orderCounter);
        orderGrid_grid.add(orderDetailFields.getStatus_textField(), 11, orderCounter);

        //to allow the mouse event to register which row/column was clicked
        for (int i = 0; i < 13; i++) {
            orderGrid_grid.add(new Pane(), i, orderCounter);
        }

        orderCounter++;
        listOfOrderFields.add(orderDetailFields);
        listOfOrders.add(order);
    }

    private void removeAllRowsFromOrderGrid() {
        for (OrderDetailFields orderDetailFields : listOfOrderFields) {
            orderGrid_grid.getChildren().remove(orderDetailFields.getId_label());
            orderGrid_grid.getChildren().remove(orderDetailFields.getCustomerName_textField());
            orderGrid_grid.getChildren().remove(orderDetailFields.getPhoneNumber_textField());
            orderGrid_grid.getChildren().remove(orderDetailFields.getEmailAddress_textField());
            orderGrid_grid.getChildren().remove(orderDetailFields.getDateWhenReceived_datePicker());
            orderGrid_grid.getChildren().remove(orderDetailFields.getDueDate_datePicker());
            orderGrid_grid.getChildren().remove(orderDetailFields.getOrderType_textField());
            orderGrid_grid.getChildren().remove(orderDetailFields.getDescription_textArea());
            orderGrid_grid.getChildren().remove(orderDetailFields.getSolution_textArea());
            orderGrid_grid.getChildren().remove(orderDetailFields.getEstimatedPrice_textField());
            orderGrid_grid.getChildren().remove(orderDetailFields.getStatus_textField());
        }
        listOfOrderFields = new ArrayList<>();
        listOfOrders = new ArrayList<>();
        orderCounter = 0;
    }


}
