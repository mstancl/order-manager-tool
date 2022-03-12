/*
package com.mstancl.ordermanagertool.util.sort.specifications;

import com.google.common.collect.Comparators;
import com.mstancl.ordermanagertool.data.pojo.Order;
import com.mstancl.ordermanagertool.util.sort.interfaces.Specification;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public class SortByDateWhenReceivedSpecification implements Specification<Order,LocalDate>{


    @Override
    public boolean isSatisfied(List<Order> items) {
        Function<Order, LocalDate> getDateWhenReceived = Order::getDateWhenReceived;
        return Comparators.isInOrder(items, Comparator.comparing(getDateWhenReceived));
    }

    @Override
    public Function<Order,LocalDate> getFunctionalComparator() {
        return null;
    }


}

*/
