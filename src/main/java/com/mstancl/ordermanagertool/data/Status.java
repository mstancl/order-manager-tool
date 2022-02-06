package com.mstancl.ordermanagertool.data;

public enum Status {

    NEW("new"),
    IN_PROGRESS("In progress"),
    DONE("Done"),
    CANCELLED("Cancelled");

    private final String name;

    Status(String name) {
        this.name = name;
    }


    public String getName() {
        return this.name;
    }

}
