package com.mstancl.ordermanagertool.controllers.order;

import com.itextpdf.text.DocumentException;
import com.mstancl.ordermanagertool.controllers.mainScreen.MainScreenController;
import com.mstancl.ordermanagertool.dao.OrderDAO;
import com.mstancl.ordermanagertool.data.enums.Status;
import com.mstancl.ordermanagertool.data.pojo.Customer;
import com.mstancl.ordermanagertool.data.pojo.Order;
import com.mstancl.ordermanagertool.util.export.AddContentToPDF;
import com.mstancl.ordermanagertool.util.fxml.FXMLoaderManager;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;


@Data
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
    public ComboBox<String> orderStatus_comboBox;

    @FXML
    public Button print_button;

    private long orderID = -1;


    @FXML
    public void initialize() {
        orderStatus_comboBox.setItems(FXCollections.observableArrayList(Status.getAllStatuses()));
        orderStatus_comboBox.setValue(Status.NEW.getName());
    }

    @FXML
    public void confirmOrder() {
        MainScreenController mainScreenController = FXMLoaderManager.getFxmLoader().getController();

        Customer customer = new Customer(StringUtils.capitalize(firstName_textField.getText().toLowerCase().trim()), StringUtils.capitalize(surname_textField.getText().toLowerCase().trim()), phoneNumber_textField.getText(), emailAddress_textField.getText());
        Order order = new Order(orderID == -1 ? mainScreenController.listOfOrderFields.size() + 1 : orderID, customer, dateWhenReceived_datePicker.getValue(), dueDate_datePicker.getValue(), orderType_textField.getText(), description_textArea.getText(), solution_textArea.getText(), Integer.parseInt(estimatedPrice_textField.getText()), Status.getStatusByName(orderStatus_comboBox.getValue()));

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

    @FXML
    public void printOrderToPDF() throws DocumentException, IOException {
        MainScreenController mainScreenController = FXMLoaderManager.getFxmLoader().getController();

        Customer customer = new Customer(StringUtils.capitalize(firstName_textField.getText().toLowerCase().trim()), StringUtils.capitalize(surname_textField.getText().toLowerCase().trim()), phoneNumber_textField.getText(), emailAddress_textField.getText());
        Order order = new Order(orderID == -1 ? mainScreenController.listOfOrderFields.size() + 1 : orderID, customer, dateWhenReceived_datePicker.getValue(), dueDate_datePicker.getValue(), orderType_textField.getText(), description_textArea.getText(), solution_textArea.getText(), Integer.parseInt(estimatedPrice_textField.getText()), Status.getStatusByName(orderStatus_comboBox.getValue()));

        AddContentToPDF.writeToPDF("pdfs/originalTemplate.pdf", "pdfs/orderPrint" + order.getId() + order.getCustomer().getFirstName() + order.getCustomer().getSurname() + ".pdf", order);

    }

}
