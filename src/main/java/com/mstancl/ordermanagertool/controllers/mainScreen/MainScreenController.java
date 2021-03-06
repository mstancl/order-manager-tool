package com.mstancl.ordermanagertool.controllers.mainScreen;

import com.mstancl.ordermanagertool.Main;
import com.mstancl.ordermanagertool.controllers.order.OrderDetailController;
import com.mstancl.ordermanagertool.dao.OrderDAO;
import com.mstancl.ordermanagertool.data.enums.HighlightColor;
import com.mstancl.ordermanagertool.data.enums.Status;
import com.mstancl.ordermanagertool.data.orderLine.OrderLineDetailFields;
import com.mstancl.ordermanagertool.data.pojo.Order;
import com.mstancl.ordermanagertool.util.TextFieldListeners;
import com.mstancl.ordermanagertool.util.filter.FilterCriteriaComparator;
import com.mstancl.ordermanagertool.util.filter.OrderFilter;
import com.mstancl.ordermanagertool.util.filter.specification.*;
import com.mstancl.ordermanagertool.util.sort.OrderSorter;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class MainScreenController {


    @FXML
    public Button addNewOrder_button;

    @FXML
    public Button editOrder_button;

    @FXML
    public GridPane orderGrid_grid;

    public Stage orderDetailsStage;


    @FXML
    public TextField idFilter_textField;

    @FXML
    public TextField customerNameFilter_textField;

    @FXML
    public ComboBox<String> statusFilter_comboBox;

    @FXML
    public DatePicker dateWhenReceivedFilter_datePicker;

    @FXML
    public DatePicker dateWhenDueFilter_datePicker;

    @FXML
    public TextField estimatedPriceFilter_textField;

    @FXML
    public Button clearFilters_button;

    @FXML
    public Button applyFilter_button;

    @FXML
    public ComboBox<String> dateWhenReceivedFilterComparator_combobox;

    @FXML
    public ComboBox<String> dateWhenDueFilterComparator_combobox;

    @FXML
    public ComboBox<String> estimatedPriceFilterComparator_combobox;

    @FXML
    public Label newStatusCount_label;

    @FXML
    public Label inProgressStatusCount_label;

    @FXML
    public Label doneStatusCount_label;

    @FXML
    public Label archivedStatusCount_label;
    @FXML
    public Label cancelledStatusCount_label;


    public List<OrderLineDetailFields> listOfOrderFields = new ArrayList<>();

    private final OrderDAO orderDAO = new OrderDAO();

    RowConstraints newOrderRow = new RowConstraints();

    private final OrderSorter orderSorter = new OrderSorter();
    private final OrderFilter orderFilter = new OrderFilter();


    @FXML
    public void initialize() {
        reloadAllOrdersFromDatabase();

        newOrderRow.setVgrow(Priority.NEVER);
        newOrderRow.setMaxHeight(40);

        TextFieldListeners.allowOnlyNumbersForInput(idFilter_textField);
        TextFieldListeners.allowOnlyNumbersForInput(estimatedPriceFilter_textField);

        List.of(dateWhenReceivedFilterComparator_combobox, dateWhenDueFilterComparator_combobox, estimatedPriceFilterComparator_combobox)
                .forEach(combobox -> {
                    combobox.setItems(FXCollections.observableArrayList(FilterCriteriaComparator.getAllSigns()));
                    combobox.setValue(FilterCriteriaComparator.getAllSigns().get(0));
                });

        statusFilter_comboBox.setItems(FXCollections.observableArrayList(Status.getAllNames()));
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
                .ifPresent(x -> {
                    try {
                        showOrderDetailsScreen(x.getOrder());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
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
        Function<Order, Long> getId = Order::getId;
        List<Order> sortedList = orderSorter.getListOfOrdersSortedBy(listOfOrderFields, getId);

        removeAllRowsFromOrderGrid();
        addOrdersToOrderGrid(sortedList);
    }

    @FXML
    public void sortByDateWhenReceived() {
        Function<Order, LocalDate> getDateWhenReceived = Order::getDateWhenReceived;
        List<Order> sortedList = orderSorter.getListOfOrdersSortedBy(listOfOrderFields, getDateWhenReceived);

        removeAllRowsFromOrderGrid();
        addOrdersToOrderGrid(sortedList);
    }

    @FXML
    public void sortByDueDate() {
        Function<Order, LocalDate> getDueDate = Order::getDueDate;
        List<Order> sortedList = orderSorter.getListOfOrdersSortedBy(listOfOrderFields, getDueDate);

        removeAllRowsFromOrderGrid();
        addOrdersToOrderGrid(sortedList);
    }


    @FXML
    public void sortByEstimatedPrice() {
        Function<Order, Integer> getEstimatedPrice = Order::getEstimatedPrice;
        List<Order> sortedList = orderSorter.getListOfOrdersSortedBy(listOfOrderFields, getEstimatedPrice);

        removeAllRowsFromOrderGrid();
        addOrdersToOrderGrid(sortedList);
    }

    public void addOrdersToOrderGrid(List<Order> listOfOrders) {
        for (Order order : listOfOrders) {
            addOrdersToOrderGrid(order);
        }
    }

    public void addOrdersToOrderGrid(Order order) {
        orderGrid_grid.getRowConstraints().add(listOfOrderFields.size(), newOrderRow);
        OrderLineDetailFields orderLineDetailFields = new OrderLineDetailFields(order);

        for (int i = 1; i <= orderLineDetailFields.getListOfAllNodes().size(); i++) {
            orderGrid_grid.add(orderLineDetailFields.getListOfAllNodes().get(i - 1), i, listOfOrderFields.size());
            Pane pane = new Pane();
            orderGrid_grid.add(pane, i, listOfOrderFields.size());
            orderLineDetailFields.addPaneToTheListOfPanes(pane);
        }
        listOfOrderFields.add(orderLineDetailFields);
    }

    public void removeAllRowsFromOrderGrid() {
        listOfOrderFields
                .forEach(
                        x -> x.getListOfAllNodes().forEach(
                                node -> orderGrid_grid.getChildren().remove(node))
                );
        listOfOrderFields = new ArrayList<>();
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

    private void showOrderDetailsScreen(Order order) throws IOException {

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

    }

    @FXML
    public void clearFilters() {
        idFilter_textField.setText(null);
        customerNameFilter_textField.setText(null);
        statusFilter_comboBox.setValue(null);
        dateWhenReceivedFilter_datePicker.setValue(null);
        dateWhenDueFilter_datePicker.setValue(null);
        estimatedPriceFilter_textField.setText(null);
        reloadAllOrdersFromDatabase();
    }

    @FXML
    public void applyFilter() {
        List<Specification<Order>> listOfFilters = new ArrayList<>();

        if (idFilter_textField.getText() != null && !idFilter_textField.getText().isBlank()) {
            listOfFilters.add(new IDFilterSpecification(Long.parseLong(idFilter_textField.getText())));
        }
        if (customerNameFilter_textField.getText() != null && !customerNameFilter_textField.getText().isBlank()) {
            listOfFilters.add(new CustomerNameFilterSpecification(customerNameFilter_textField.getText()));
        }
        if (statusFilter_comboBox.getValue() != null) {
            listOfFilters.add(new StatusFilterSpecification(Status.getStatusByName(statusFilter_comboBox.getValue())));
        }
        if (dateWhenReceivedFilter_datePicker.getValue() != null) {
            listOfFilters.add(new DateWhenReceivedFilterSpecification(dateWhenReceivedFilter_datePicker.getValue(), FilterCriteriaComparator.getEnum(dateWhenReceivedFilterComparator_combobox.getValue())));
        }
        if (dateWhenDueFilter_datePicker.getValue() != null) {
            listOfFilters.add(new DateWhenDueFilterSpecification(dateWhenDueFilter_datePicker.getValue(), FilterCriteriaComparator.getEnum(dateWhenDueFilterComparator_combobox.getValue())));
        }
        if (estimatedPriceFilter_textField.getText() != null && !estimatedPriceFilter_textField.getText().isBlank()) {
            listOfFilters.add(new EstimatedPriceFilterSpecification(Long.parseLong(estimatedPriceFilter_textField.getText()), FilterCriteriaComparator.getEnum(estimatedPriceFilterComparator_combobox.getValue())));
        }

        removeAllRowsFromOrderGrid();
        addOrdersToOrderGrid(orderDAO.getAllRecords(orderFilter, listOfFilters));

    }

    public void reloadAllOrdersFromDatabase() {
        removeAllRowsFromOrderGrid();
       /* addOrdersToOrderGrid(
                orderDAO.getAllRecords(orderFilter, List.of(
                        new NotMatchingStatusFilterSpecification(Status.ARCHIVED),
                        new NotMatchingStatusFilterSpecification(Status.CANCELLED))
                )
        );*/
        addOrdersToOrderGrid(orderDAO.getAllRecords());
        updateStatusOrderCounter();
    }

    public void updateStatusOrderCounter() {
        List<Order> listOfAllOrders = orderDAO.getAllRecords();
        newStatusCount_label.setText(String.valueOf(listOfAllOrders.stream().filter(x -> x.getStatus() == Status.NEW).count()));
        inProgressStatusCount_label.setText(String.valueOf(listOfAllOrders.stream().filter(x -> x.getStatus() == Status.IN_PROGRESS).count()));
        doneStatusCount_label.setText(String.valueOf(listOfAllOrders.stream().filter(x -> x.getStatus() == Status.DONE).count()));
        archivedStatusCount_label.setText(String.valueOf(listOfAllOrders.stream().filter(x -> x.getStatus() == Status.ARCHIVED).count()));
        cancelledStatusCount_label.setText(String.valueOf(listOfAllOrders.stream().filter(x -> x.getStatus() == Status.CANCELLED).count()));

    }


}
