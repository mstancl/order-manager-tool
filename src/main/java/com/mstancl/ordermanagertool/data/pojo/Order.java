package com.mstancl.ordermanagertool.data.pojo;

import com.mstancl.ordermanagertool.data.Status;

import java.time.LocalDate;

public class Order {

    private Customer customer;
    private LocalDate dateWhenReceived;
    private LocalDate dueDate;
    private String orderType;
    private String descriptionOfOrder;
    private String solutionForOrder;
    private int estimatedPrice;
    private int orderID;
    private Status status;

    public Order(Customer customer, LocalDate dateWhenReceived, LocalDate dueDate, String kindOfOrder, String descriptionOfOrder, String solutionForOrder, int estimatedPrice,Status status) {
        this.customer = customer;
        this.dateWhenReceived = dateWhenReceived;
        this.dueDate = dueDate;
        this.orderType = kindOfOrder;
        this.descriptionOfOrder = descriptionOfOrder;
        this.solutionForOrder = solutionForOrder;
        this.estimatedPrice = estimatedPrice;
        this.status = status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDate getDateWhenReceived() {
        return dateWhenReceived;
    }

    public void setDateWhenReceived(LocalDate dateWhenReceived) {
        this.dateWhenReceived = dateWhenReceived;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
