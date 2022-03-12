package com.mstancl.ordermanagertool.util.filter;

import com.google.common.collect.Comparators;
import com.mstancl.ordermanagertool.data.orderLine.OrderLineDetailFields;
import com.mstancl.ordermanagertool.data.pojo.Order;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class OrderFilter {


    public List<Order> sortByID(List<OrderLineDetailFields> listOfOrderFields) {
        List<Order> listOfOrders = listOfOrderFields
                .stream()
                .map(OrderLineDetailFields::getOrder)
                .collect(Collectors.toList());

        if (Comparators.isInOrder(listOfOrders, Comparator.comparing(Order::getId))) {
            listOfOrders.sort(Comparator.comparing(Order::getId).reversed());
        } else {
            listOfOrders.sort(Comparator.comparing(Order::getId));
        }
        return listOfOrders;
    }

    public List<Order> sortByDateWhenReceived(List<OrderLineDetailFields> listOfOrderFields) {
        List<Order> listOfOrders = listOfOrderFields
                .stream()
                .map(OrderLineDetailFields::getOrder)
                .collect(Collectors.toList());

        if (Comparators.isInOrder(listOfOrders, Comparator.comparing(Order::getDateWhenReceived))) {
            listOfOrders.sort(Comparator.comparing(Order::getDateWhenReceived).reversed());
        } else {
            listOfOrders.sort(Comparator.comparing(Order::getDateWhenReceived));
        }
        return listOfOrders;
    }

    public List<Order> sortByDueDate(List<OrderLineDetailFields> listOfOrderFields) {
        List<Order> listOfOrders = listOfOrderFields
                .stream()
                .map(OrderLineDetailFields::getOrder)
                .collect(Collectors.toList());

        if (Comparators.isInOrder(listOfOrders, Comparator.comparing(Order::getDueDate))) {
            listOfOrders.sort(Comparator.comparing(Order::getDueDate).reversed());
        } else {
            listOfOrders.sort(Comparator.comparing(Order::getDueDate));
        }
        return listOfOrders;
    }

    public List<Order> sortByEstimatedPrice(List<OrderLineDetailFields> listOfOrderFields) {
        List<Order> listOfOrders = listOfOrderFields
                .stream()
                .map(OrderLineDetailFields::getOrder)
                .collect(Collectors.toList());

        if (Comparators.isInOrder(listOfOrders, Comparator.comparing(Order::getEstimatedPrice))) {
            listOfOrders.sort(Comparator.comparing(Order::getEstimatedPrice).reversed());
        } else {
            listOfOrders.sort(Comparator.comparing(Order::getEstimatedPrice));
        }

        return listOfOrders;
    }

}
