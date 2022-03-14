package com.mstancl.ordermanagertool.util.filter.specification;

import com.mstancl.ordermanagertool.data.pojo.Order;

public class IDFilterSpecification implements Specification<Order> {

    long id;

    public IDFilterSpecification(long id) {
        this.id = id;
    }

    @Override
    public boolean isSatisfied(Order order) {
        return this.id == order.getId();
    }
}
