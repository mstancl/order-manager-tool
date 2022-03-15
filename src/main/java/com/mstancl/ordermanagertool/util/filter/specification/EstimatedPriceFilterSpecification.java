package com.mstancl.ordermanagertool.util.filter.specification;

import com.mstancl.ordermanagertool.data.pojo.Order;
import com.mstancl.ordermanagertool.util.filter.FilterCriteriaComparator;

public class EstimatedPriceFilterSpecification implements Specification<Order> {

    long estimatedPrice;

    FilterCriteriaComparator filterCriteriaComparator;


    public EstimatedPriceFilterSpecification(long estimatedPrice, FilterCriteriaComparator filterCriteriaComparator) {
        this.estimatedPrice = estimatedPrice;
        this.filterCriteriaComparator = filterCriteriaComparator;
    }

    @Override
    public boolean isSatisfied(Order order) {
        return switch (filterCriteriaComparator) {
            case EQUALS -> order.getEstimatedPrice() == this.estimatedPrice;
            case LESS_THAN -> order.getEstimatedPrice() < this.estimatedPrice;
            case GREATER_THAN -> order.getEstimatedPrice() > this.estimatedPrice;
            case LESS_OR_EQUAL_TO -> order.getEstimatedPrice() <= this.estimatedPrice;
            case GREATER_OR_EQUAL_TO -> order.getEstimatedPrice() >= this.estimatedPrice;
        };

    }
}
