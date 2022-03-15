package com.mstancl.ordermanagertool.util.filter.specification;

import com.mstancl.ordermanagertool.data.pojo.Order;

import java.time.LocalDate;

public class DateWhenReceivedFilterSpecification implements Specification<Order> {

    LocalDate dateWhenReceived;

    public DateWhenReceivedFilterSpecification(LocalDate dateWhenReceived) {
        this.dateWhenReceived = dateWhenReceived;
    }

    @Override
    public boolean isSatisfied(Order item) {
        return item.getDateWhenReceived().equals(dateWhenReceived);
    }
}
