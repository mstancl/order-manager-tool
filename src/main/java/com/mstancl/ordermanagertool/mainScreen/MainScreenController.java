package com.mstancl.ordermanagertool.mainScreen;

import com.mstancl.ordermanagertool.Main;
import com.mstancl.ordermanagertool.data.Order;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

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


    public int orderCounter = 1;
    public List<Order> listOfOrders = new ArrayList<>();

    @FXML
    public GridPane orderGrid_grid;

    public Stage orderDetailsStage;


    @FXML
    public void addNewOrder() throws IOException {
        System.out.println(orderCounter);

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


}
