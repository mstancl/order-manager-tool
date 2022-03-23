package com.mstancl.ordermanagertool.util.filter;

import com.mstancl.ordermanagertool.data.pojo.Order;
import com.mstancl.ordermanagertool.util.filter.specification.Specification;

import java.util.List;

public class OrderFilter {


    public List<Order> getOrdersMatchingSpecification(List<Order> listOfOrders, Specification<Order> specification) {
        return listOfOrders
                .stream()
                .filter(specification::isSatisfied)
                .toList();
    }



}
