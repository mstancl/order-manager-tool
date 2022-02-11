package com.mstancl.ordermanagertool.controllers.order;

import com.mstancl.ordermanagertool.controllers.mainScreen.MainScreenController;
import com.mstancl.ordermanagertool.dao.OrderDAO;
import com.mstancl.ordermanagertool.data.Status;
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


    @FXML
    public void confirmOrder() {
        MainScreenController mainScreenController = FXMLoaderManager.getFxmLoader().getController();

        Customer customer = new Customer(StringUtils.capitalize(firstName_textField.getText().toLowerCase().trim()) + " " + StringUtils.capitalize(surname_textField.getText().toLowerCase().trim()), phoneNumber_textField.getText(), emailAddress_textField.getText());
        Order order = new Order(mainScreenController.orderCounter + 1, customer, dateWhenReceived_datePicker.getValue(), dueDate_datePicker.getValue(), orderType_textField.getText(), description_textArea.getText(), solution_textArea.getText(), Integer.parseInt(estimatedPrice_textField.getText()), Status.IN_PROGRESS);

        mainScreenController.addOrdersToOrderGrid(order);

        orderDAO.write(order);

        mainScreenController.orderDetailsStage.close();


    }


}
