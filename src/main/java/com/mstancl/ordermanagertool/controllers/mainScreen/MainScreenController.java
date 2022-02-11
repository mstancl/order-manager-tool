package com.mstancl.ordermanagertool.controllers.mainScreen;

import com.google.common.collect.Comparators;
import com.mstancl.ordermanagertool.Main;
import com.mstancl.ordermanagertool.dao.OrderDAO;
import com.mstancl.ordermanagertool.data.orderLine.OrderLineDetailFields;
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
    private int highLightedRowForModification = -1;

    public List<Order> listOfOrders = new ArrayList<>();
    public List<OrderLineDetailFields> listOfOrderFields = new ArrayList<>();

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
        if (highLightedRowForModification > 0) {

        }
    }

    @FXML
    public void orderGridRowClicked(MouseEvent e) {
        Node source = e.getPickResult().getIntersectedNode();
        Integer rowIndex = GridPane.getRowIndex(source);
        for (Node node : orderGrid_grid.getChildren()) {
            if (GridPane.getRowIndex(node).equals(rowIndex) && node instanceof Pane && GridPane.getColumnIndex(node) != 0 && GridPane.getColumnIndex(node) != 12) {
                node.setStyle("-fx-border-color:#0000FF;");
                highLightedRowForModification = rowIndex;
                /*node.getStyleClass().clear();
                node.setStyle(null);*/
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
        OrderLineDetailFields orderLineDetailFields = new OrderLineDetailFields(order);

        orderGrid_grid.add(orderLineDetailFields.getId_label(), 1, orderCounter);
        orderGrid_grid.add(orderLineDetailFields.getCustomerName_textField(), 2, orderCounter);
        orderGrid_grid.add(orderLineDetailFields.getPhoneNumber_textField(), 3, orderCounter);
        orderGrid_grid.add(orderLineDetailFields.getEmailAddress_textField(), 4, orderCounter);
        orderGrid_grid.add(orderLineDetailFields.getDateWhenReceived_datePicker(), 5, orderCounter);
        orderGrid_grid.add(orderLineDetailFields.getDueDate_datePicker(), 6, orderCounter);
        orderGrid_grid.add(orderLineDetailFields.getOrderType_textField(), 7, orderCounter);
        orderGrid_grid.add(orderLineDetailFields.getDescription_textArea(), 8, orderCounter);
        orderGrid_grid.add(orderLineDetailFields.getSolution_textArea(), 9, orderCounter);
        orderGrid_grid.add(orderLineDetailFields.getEstimatedPrice_textField(), 10, orderCounter);
        orderGrid_grid.add(orderLineDetailFields.getStatus_textField(), 11, orderCounter);

        //to allow the mouse event to register which row/column was clicked
        for (int i = 0; i < 13; i++) {
            Pane pane = new Pane();
            orderGrid_grid.add(pane, i, orderCounter);
            orderLineDetailFields.addPaneToTheListOfPanes(pane);
        }

        orderCounter++;
        listOfOrderFields.add(orderLineDetailFields);
        listOfOrders.add(order);
    }

    private void removeAllRowsFromOrderGrid() {
        for (OrderLineDetailFields orderLineDetailFields : listOfOrderFields) {
            orderGrid_grid.getChildren().remove(orderLineDetailFields.getId_label());
            orderGrid_grid.getChildren().remove(orderLineDetailFields.getCustomerName_textField());
            orderGrid_grid.getChildren().remove(orderLineDetailFields.getPhoneNumber_textField());
            orderGrid_grid.getChildren().remove(orderLineDetailFields.getEmailAddress_textField());
            orderGrid_grid.getChildren().remove(orderLineDetailFields.getDateWhenReceived_datePicker());
            orderGrid_grid.getChildren().remove(orderLineDetailFields.getDueDate_datePicker());
            orderGrid_grid.getChildren().remove(orderLineDetailFields.getOrderType_textField());
            orderGrid_grid.getChildren().remove(orderLineDetailFields.getDescription_textArea());
            orderGrid_grid.getChildren().remove(orderLineDetailFields.getSolution_textArea());
            orderGrid_grid.getChildren().remove(orderLineDetailFields.getEstimatedPrice_textField());
            orderGrid_grid.getChildren().remove(orderLineDetailFields.getStatus_textField());
            orderGrid_grid.getChildren().removeAll(orderLineDetailFields.getListOfPaneFields());
        }

        listOfOrderFields = new ArrayList<>();
        listOfOrders = new ArrayList<>();
        orderCounter = 0;
    }


}
