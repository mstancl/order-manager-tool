package com.mstancl.ordermanagertool.controllers.mainScreen;

import com.mstancl.ordermanagertool.Main;
import com.mstancl.ordermanagertool.dao.OrderDAO;
import com.mstancl.ordermanagertool.data.OrderDetailFields;
import com.mstancl.ordermanagertool.data.Status;
import com.mstancl.ordermanagertool.data.pojo.Customer;
import com.mstancl.ordermanagertool.data.pojo.Order;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainScreenController {


    @FXML
    public Button addNewOrder_button;

    @FXML
    public Button editOrder_button;

    @FXML
    public Button deleteOrder_button;

    @FXML
    public GridPane orderGrid_grid;

    public Stage orderDetailsStage;

    public int orderCounter = 1;
    public List<Order> listOfOrders = new ArrayList<>();
    private final OrderDAO orderDAO = new OrderDAO();

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
        System.out.println("TEST EDIT");
    }

    @FXML
    public void deleteOrder() {
        System.out.println("TEST DELETE");
    }

    @FXML
    public void initialize() {

        System.out.println("Initializing...");
        for (Order order : orderDAO.getAllRecords()) {

            RowConstraints newOrderRow = new RowConstraints();
            newOrderRow.setVgrow(Priority.NEVER);
            newOrderRow.setMaxHeight(40);

            orderGrid_grid.getRowConstraints().add(orderCounter, newOrderRow);

            OrderDetailFields orderDetailFields = new OrderDetailFields(order);

            orderGrid_grid.add(orderDetailFields.getId_label(), 0, orderCounter);
            orderGrid_grid.add(orderDetailFields.getCustomerName_textField(), 1, orderCounter);
            orderGrid_grid.add(orderDetailFields.getPhoneNumber_textField(), 2, orderCounter);
            orderGrid_grid.add(orderDetailFields.getEmailAddress_textField(), 3, orderCounter);
            orderGrid_grid.add(orderDetailFields.getDateWhenReceived_datePicker(), 4, orderCounter);
            orderGrid_grid.add(orderDetailFields.getDueDate_datePicker(), 5, orderCounter);
            orderGrid_grid.add(orderDetailFields.getOrderType_textField(), 6, orderCounter);
            orderGrid_grid.add(orderDetailFields.getDescription_textArea(), 7, orderCounter);
            orderGrid_grid.add(orderDetailFields.getSolution_textArea(), 8, orderCounter);
            orderGrid_grid.add(orderDetailFields.getEstimatedPrice_textField(), 9, orderCounter);
            orderGrid_grid.add(orderDetailFields.getStatus_textField(), 10, orderCounter);

            orderCounter++;
            listOfOrders.add(order);
        }


    }


}
