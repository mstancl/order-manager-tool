package com.mstancl.ordermanagertool.util.filter.specification;

import com.mstancl.ordermanagertool.data.pojo.Order;
import com.mstancl.ordermanagertool.util.filter.FilterCriteriaComparator;

import java.time.LocalDate;

public class DateWhenDueFilterSpecification implements Specification<Order> {

    LocalDate dateWhenDue;
    FilterCriteriaComparator filterCriteriaComparator;


    public DateWhenDueFilterSpecification(LocalDate dateWhenDue, FilterCriteriaComparator filterCriteriaComparator) {
        this.dateWhenDue = dateWhenDue;
        this.filterCriteriaComparator = filterCriteriaComparator;
    }

    @Override
    public boolean isSatisfied(Order order) {
        return switch (filterCriteriaComparator) {
            case EQUALS -> order.getDueDate().compareTo(this.dateWhenDue) == 0;
            case LESS_THAN -> order.getDueDate().compareTo(this.dateWhenDue) < 0;
            case GREATER_THAN -> order.getDueDate().compareTo(this.dateWhenDue) > 0;
            case LESS_OR_EQUAL_TO -> order.getDueDate().compareTo(this.dateWhenDue) <= 0;
            case GREATER_OR_EQUAL_TO -> order.getDueDate().compareTo(this.dateWhenDue) >= 0;
        };
    }
}
