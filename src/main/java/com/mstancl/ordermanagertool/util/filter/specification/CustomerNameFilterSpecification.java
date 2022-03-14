package com.mstancl.ordermanagertool.util.filter.specification;

import com.mstancl.ordermanagertool.data.pojo.Order;

public class CustomerNameFilterSpecification implements Specification<Order> {

    String customerName;

    public CustomerNameFilterSpecification(String customerName) {
        this.customerName = customerName;
    }

    @Override
    public boolean isSatisfied(Order order) {
        String customerName = order.getCustomer().getFirstName() + order.getCustomer().getSurname();
        return customerName.equalsIgnoreCase(customerName.replaceAll(" ", ""));
    }
}
