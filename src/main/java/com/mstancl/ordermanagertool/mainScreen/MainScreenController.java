package com.mstancl.ordermanagertool.mainScreen;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;

public class MainScreenController {


    @FXML
    public Button addNewOrder_button;

    @FXML
    public Button editOrder_button;

    @FXML
    public Button deleteOrder_button;


    private int orderCounter = 1;

    @FXML
    public GridPane orderGrid_grid;


    @FXML
    public void addNewOrder() {
        System.out.println(orderCounter);


      /*  RowConstraints newOrderRow = new RowConstraints();
        newOrderRow.setVgrow(Priority.NEVER);
        newOrderRow.setMaxHeight(40);

        orderGrid_grid.getRowConstraints().add(orderCounter,newOrderRow);*/


        orderGrid_grid.add(new TextArea(),0,orderCounter);
        orderGrid_grid.add(new TextArea(),1,orderCounter);
        orderGrid_grid.add(new TextArea(),2,orderCounter);
        orderGrid_grid.add(new DatePicker(),3,orderCounter);
        orderGrid_grid.add(new DatePicker(),4,orderCounter);
        orderGrid_grid.add(new TextArea(),5,orderCounter);
        orderGrid_grid.add(new TextArea(),6,orderCounter);
        orderGrid_grid.add(new TextArea(),7,orderCounter);
        orderGrid_grid.add(new TextArea(),8,orderCounter);
        orderGrid_grid.add(new Button("Add order"),9,orderCounter);


        orderCounter++;



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
