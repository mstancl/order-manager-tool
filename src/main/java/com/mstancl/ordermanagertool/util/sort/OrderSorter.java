package com.mstancl.ordermanagertool.util.sort;

import com.google.common.collect.Comparators;
import com.mstancl.ordermanagertool.data.orderLine.OrderLineDetailFields;
import com.mstancl.ordermanagertool.data.pojo.Order;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class OrderSorter {


    public List<Order> getListOfOrdersSortedBy(List<OrderLineDetailFields> listOfOrderFields, Function functionalComparator){
        List<Order> listOfOrders = listOfOrderFields
                .stream()
                .map(OrderLineDetailFields::getOrder)
                .collect(Collectors.toList());

        if (Comparators.isInOrder(listOfOrders, Comparator.comparing(functionalComparator))) {
            listOfOrders.sort(Comparator.comparing(functionalComparator).reversed());
        } else {
            listOfOrders.sort(Comparator.comparing(functionalComparator));
        }
        return listOfOrders;
    }

}
