package com.mstancl.ordermanagertool.data;

import java.util.Calendar;

public class Order {

    private Customer customer;
    private Calendar dateWhenReceived;
    private Calendar dueDate;
    private String kindOfOrder;
    private String descriptionOfOrder;
    private String solutionForOrder;
    private int estimatedPrice;
    private int orderID;

    public Order(Customer customer, Calendar dateWhenReceived, Calendar dueDate, String kindOfOrder, String descriptionOfOrder, String solutionForOrder, int estimatedPrice) {
        this.customer = customer;
        this.dateWhenReceived = dateWhenReceived;
        this.dueDate = dueDate;
        this.kindOfOrder = kindOfOrder;
        this.descriptionOfOrder = descriptionOfOrder;
        this.solutionForOrder = solutionForOrder;
        this.estimatedPrice = estimatedPrice;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Calendar getDateWhenReceived() {
        return dateWhenReceived;
    }

    public void setDateWhenReceived(Calendar dateWhenReceived) {
        this.dateWhenReceived = dateWhenReceived;
    }

    public Calendar getDueDate() {
        return dueDate;
    }

    public void setDueDate(Calendar dueDate) {
        this.dueDate = dueDate;
    }

    public String getKindOfOrder() {
        return kindOfOrder;
    }

    public void setKindOfOrder(String kindOfOrder) {
        this.kindOfOrder = kindOfOrder;
    }

    public String getDescriptionOfOrder() {
        return descriptionOfOrder;
    }

    public void setDescriptionOfOrder(String descriptionOfOrder) {
        this.descriptionOfOrder = descriptionOfOrder;
    }

    public String getSolutionForOrder() {
        return solutionForOrder;
    }

    public void setSolutionForOrder(String solutionForOrder) {
        this.solutionForOrder = solutionForOrder;
    }

    public int getEstimatedPrice() {
        return estimatedPrice;
    }

    public void setEstimatedPrice(int estimatedPrice) {
        this.estimatedPrice = estimatedPrice;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }
}
