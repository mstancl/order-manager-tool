package com.mstancl.ordermanagertool.util.filter.specification;

import com.mstancl.ordermanagertool.data.pojo.Order;

public class CustomerNameFilterSpecification implements Specification<Order> {

    String customerName;

    public CustomerNameFilterSpecification(String customerName) {
        this.customerName = customerName.replaceAll(" ", "");
    }

    @Override
    public boolean isSatisfied(Order order) {
        String customerName = (order.getCustomer().getFirstName() + order.getCustomer().getSurname()).replaceAll(" ", "");

        if (customerName.equalsIgnoreCase(this.customerName)) {
            return true;
        } else if (customerName.toLowerCase().contains(this.customerName.toLowerCase())) {
            return true;
        } else {
            return false;
        }
    }
}
