package com.mstancl.ordermanagertool.data.pojo;
import  com.mstancl.ordermanagertool.data.Status;


import java.time.LocalDate;

public class Order {

    private long id;
    private Customer customer;
    private LocalDate dateWhenReceived;
    private LocalDate dueDate;
    private String orderType;
    private String descriptionOfOrder;
    private String solutionForOrder;
    private int estimatedPrice;
    private Status status;

    public Order(long id,Customer customer, LocalDate dateWhenReceived, LocalDate dueDate, String orderType, String descriptionOfOrder, String solutionForOrder, int estimatedPrice,Status status) {
        this.id = id;
        this.customer = customer;
        this.dateWhenReceived = dateWhenReceived;
        this.dueDate = dueDate;
        this.orderType = orderType;
        this.descriptionOfOrder = descriptionOfOrder;
        this.solutionForOrder = solutionForOrder;
        this.estimatedPrice = estimatedPrice;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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


    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
