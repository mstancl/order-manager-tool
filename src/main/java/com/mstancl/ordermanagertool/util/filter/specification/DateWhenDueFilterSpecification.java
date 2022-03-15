package com.mstancl.ordermanagertool.util.filter.specification;

import com.mstancl.ordermanagertool.data.pojo.Order;

import java.time.LocalDate;

public class DateWhenDueFilterSpecification implements Specification<Order> {

    LocalDate dateWhenDue;

    public DateWhenDueFilterSpecification(LocalDate dateWhenDue) {
        this.dateWhenDue = dateWhenDue;
    }

    @Override
    public boolean isSatisfied(Order item) {
        return item.getDueDate().equals(dateWhenDue);
    }
}
