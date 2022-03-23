package com.mstancl.ordermanagertool.util.filter.specification;

import com.mstancl.ordermanagertool.data.enums.Status;
import com.mstancl.ordermanagertool.data.pojo.Order;

public class NotMatchingStatusFilterSpecification implements Specification<Order> {

    Status status;

    public NotMatchingStatusFilterSpecification(Status status) {
        this.status = status;
    }

    @Override
    public boolean isSatisfied(Order item) {

        return item.getStatus() != status;
    }
}
