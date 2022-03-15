package com.mstancl.ordermanagertool.util.filter.specification;

import com.mstancl.ordermanagertool.data.pojo.Order;
import com.mstancl.ordermanagertool.util.filter.FilterCriteriaComparator;

import java.time.LocalDate;

public class DateWhenReceivedFilterSpecification implements Specification<Order> {

    LocalDate dateWhenReceived;
    FilterCriteriaComparator filterCriteriaComparator;

    public DateWhenReceivedFilterSpecification(LocalDate dateWhenReceived, FilterCriteriaComparator filterCriteriaComparator) {
        this.dateWhenReceived = dateWhenReceived;
        this.filterCriteriaComparator = filterCriteriaComparator;
    }


    @Override
    public boolean isSatisfied(Order order) {
        return switch (filterCriteriaComparator) {
            case EQUALS -> order.getDateWhenReceived().compareTo(this.dateWhenReceived) == 0;
            case LESS_THAN -> order.getDateWhenReceived().compareTo(this.dateWhenReceived) < 0;
            case GREATER_THAN -> order.getDateWhenReceived().compareTo(this.dateWhenReceived) > 0;
            case LESS_OR_EQUAL_TO -> order.getDateWhenReceived().compareTo(this.dateWhenReceived) <= 0;
            case GREATER_OR_EQUAL_TO -> order.getDateWhenReceived().compareTo(this.dateWhenReceived) >= 0;
        };
    }

}
