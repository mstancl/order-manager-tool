package com.mstancl.ordermanagertool.util.filter.specification;

import com.mstancl.ordermanagertool.data.pojo.Order;
import com.mstancl.ordermanagertool.util.filter.FilterCriteriaComparator;

import java.time.LocalDate;

public class DateWhenDueFilterSpecification implements Specification<Order> {

    LocalDate dateWhenDue;
    FilterCriteriaComparator filterCriteriaComparator;

    public DateWhenDueFilterSpecification(LocalDate dateWhenDue) {
        this.dateWhenDue = dateWhenDue;
    }

    public DateWhenDueFilterSpecification(LocalDate dateWhenDue,FilterCriteriaComparator filterCriteriaComparator) {
        this.dateWhenDue = dateWhenDue;
        this.filterCriteriaComparator = filterCriteriaComparator;
    }

    @Override
    public boolean isSatisfied(Order item) {
        return item.getDueDate().equals(dateWhenDue);
    }
}
