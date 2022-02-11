package com.mstancl.ordermanagertool.data.orderLine;

import javafx.scene.Node;

public class OrderLineField {

    private final Node orderField;

    public OrderLineField(Node node){
        this.orderField = node;
    }

    public Node getOrderField() {
        return orderField;
    }



}
