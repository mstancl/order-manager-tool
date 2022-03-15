package com.mstancl.ordermanagertool.util.filter.specification;

import com.mstancl.ordermanagertool.data.pojo.Order;
import com.mstancl.ordermanagertool.util.filter.FilterCriteriaComparator;

import java.time.LocalDate;

public class DateWhenReceivedFilterSpecification implements Specification<Order> {

    LocalDate dateWhenReceived;
    FilterCriteriaComparator filterCriteriaComparator;


    public DateWhenReceivedFilterSpecification(LocalDate dateWhenReceived) {
        this.dateWhenReceived = dateWhenReceived;
    }

    public DateWhenReceivedFilterSpecification(LocalDate dateWhenReceived, FilterCriteriaComparator filterCriteriaComparator) {
        this.dateWhenReceived = dateWhenReceived;
        this.filterCriteriaComparator = filterCriteriaComparator;
    }

    @Override
    public boolean isSatisfied(Order item) {
        return item.getDateWhenReceived().equals(dateWhenReceived);
    }
}
