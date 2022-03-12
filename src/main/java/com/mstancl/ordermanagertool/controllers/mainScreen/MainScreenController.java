package com.mstancl.ordermanagertool.controllers.mainScreen;

import com.google.common.collect.Comparators;
import com.mstancl.ordermanagertool.Main;
import com.mstancl.ordermanagertool.controllers.order.OrderDetailController;
import com.mstancl.ordermanagertool.dao.OrderDAO;
import com.mstancl.ordermanagertool.data.enums.HighlightColor;
import com.mstancl.ordermanagertool.data.orderLine.OrderLineDetailFields;
import com.mstancl.ordermanagertool.data.pojo.Order;
import com.mstancl.ordermanagertool.util.filter.OrderFilter;
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
import java.util.stream.Collectors;

public class MainScreenController {


    @FXML
    public Button addNewOrder_button;

    @FXML
    public Button editOrder_button;

    @FXML
    public GridPane orderGrid_grid;

    public Stage orderDetailsStage;

    public int orderCounter;

    public List<OrderLineDetailFields> listOfOrderFields = new ArrayList<>();

    private final OrderDAO orderDAO = new OrderDAO();

    RowConstraints newOrderRow = new RowConstraints();

    private final OrderFilter orderFilter = new OrderFilter();


    @FXML
    public void initialize() {
        removeAllRowsFromOrderGrid();
        addOrdersToOrderGrid(orderDAO.getAllRecords());

        newOrderRow.setVgrow(Priority.NEVER);
        newOrderRow.setMaxHeight(40);
    }

    @FXML
    public void addNewOrder() throws IOException {
        showOrderDetailsScreen();
    }

    @FXML
    public void editOrder() {
        listOfOrderFields.stream()
                .filter(OrderLineDetailFields::isToBeEdited)
                .findFirst()
                .ifPresent(x -> showOrderDetailsScreen(x.getOrder()));
    }

    @FXML
    public void orderGridRowClicked(MouseEvent mouseEvent) {
        Node clickedElement = mouseEvent.getPickResult().getIntersectedNode();
        Integer rowIndexOfClickedElement = GridPane.getRowIndex(clickedElement);
        if (rowIndexOfClickedElement != null) {
            listOfOrderFields
                    .forEach(order -> {
                        order.setToBeEdited(false);
                        order.setHighlightColor(null);
                    });
            listOfOrderFields.get(rowIndexOfClickedElement).setToBeEdited(true);
            listOfOrderFields.get(rowIndexOfClickedElement).setHighlightColor(listOfOrderFields.get(rowIndexOfClickedElement).getHighlightColor() == null ? HighlightColor.BLUE : null);
        }
    }

    @FXML
    public void sortByID() {
        List<Order> sortedList = orderFilter.sortByID(listOfOrderFields);

        removeAllRowsFromOrderGrid();
        addOrdersToOrderGrid(sortedList);
    }

    @FXML
    public void sortByDateWhenReceived() {
        List<Order> sortedList = orderFilter.sortByDateWhenReceived(listOfOrderFields);

        removeAllRowsFromOrderGrid();
        addOrdersToOrderGrid(sortedList);
    }

    @FXML
    public void sortByDueDate() {
        List<Order> sortedList = orderFilter.sortByDueDate(listOfOrderFields);

        removeAllRowsFromOrderGrid();
        addOrdersToOrderGrid(sortedList);
    }


    @FXML
    public void sortByEstimatedPrice() {
        List<Order> sortedList = orderFilter.sortByEstimatedPrice(listOfOrderFields);

        removeAllRowsFromOrderGrid();
        addOrdersToOrderGrid(sortedList);
    }

    public void addOrdersToOrderGrid(List<Order> listOfOrders) {
        for (Order order : listOfOrders) {
            addOrdersToOrderGrid(order);
        }
    }

    public void addOrdersToOrderGrid(Order order) {
        orderGrid_grid.getRowConstraints().add(orderCounter, newOrderRow);
        OrderLineDetailFields orderLineDetailFields = new OrderLineDetailFields(order);

        for (int i = 1; i <= orderLineDetailFields.getListOfAllNodes().size(); i++) {
            orderGrid_grid.add(orderLineDetailFields.getListOfAllNodes().get(i - 1), i, orderCounter);
            Pane pane = new Pane();
            orderGrid_grid.add(pane, i, orderCounter);
            orderLineDetailFields.addPaneToTheListOfPanes(pane);
        }
        orderCounter++;
        listOfOrderFields.add(orderLineDetailFields);
    }

    public void removeAllRowsFromOrderGrid() {
        listOfOrderFields
                .forEach(
                        x -> x.getListOfAllNodes().forEach(
                                node -> orderGrid_grid.getChildren().remove(node))
                );
        listOfOrderFields = new ArrayList<>();
        orderCounter = 0;
    }

    private List<Order> getListOfAllOrder() {
        List<Order> listOfOrders = new ArrayList<>();
        for (OrderLineDetailFields orderLineDetailFields : listOfOrderFields) {
            listOfOrders.add(orderLineDetailFields.getOrder());
        }
        return listOfOrders;
    }


    private void showOrderDetailsScreen() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("newOrderScreen.fxml"));
        Parent root = fxmlLoader.load();
        orderDetailsStage = new Stage();
        orderDetailsStage.initModality(Modality.APPLICATION_MODAL);
        orderDetailsStage.setTitle("Order details");
        orderDetailsStage.setScene(new Scene(root));
        orderDetailsStage.show();
    }

    private void showOrderDetailsScreen(Order order) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("newOrderScreen.fxml"));
            Parent root = fxmlLoader.load();
            orderDetailsStage = new Stage();
            orderDetailsStage.initModality(Modality.APPLICATION_MODAL);
            orderDetailsStage.setTitle("Order details");
            orderDetailsStage.setScene(new Scene(root));
            orderDetailsStage.show();

            OrderDetailController orderDetailController = fxmlLoader.getController();

            orderDetailController.getFirstName_textField().setText(order.getCustomer().getFirstName());
            orderDetailController.getSurname_textField().setText(order.getCustomer().getSurname());
            orderDetailController.getEmailAddress_textField().setText(order.getCustomer().getEmail());
            orderDetailController.getPhoneNumber_textField().setText(order.getCustomer().getPhoneNumber());
            orderDetailController.getDateWhenReceived_datePicker().setValue(order.getDateWhenReceived());
            orderDetailController.getDueDate_datePicker().setValue(order.getDueDate());
            orderDetailController.getOrderType_textField().setText(order.getOrderType());
            orderDetailController.getEstimatedPrice_textField().setText(Long.toString(order.getEstimatedPrice()));
            orderDetailController.getDescription_textArea().setText(order.getDescriptionOfOrder());
            orderDetailController.getSolution_textArea().setText(order.getSolutionForOrder());
            orderDetailController.getOrderStatus_comboBox().setValue(order.getStatus().getName());
            orderDetailController.setOrderID(order.getId());

        } catch (IOException e) {
            //TODO
        }
    }


}
