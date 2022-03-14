package com.mstancl.ordermanagertool.util.filter.specification;

import com.mstancl.ordermanagertool.data.pojo.Order;

public class EstimatedPriceFilterSpecification implements Specification<Order> {

    long estimatedPrice;

    public EstimatedPriceFilterSpecification(long estimatedPrice) {
        this.estimatedPrice = estimatedPrice;
    }

    @Override
    public boolean isSatisfied(Order order) {
        return this.estimatedPrice == order.getEstimatedPrice();
    }
}
