package com.mstancl.ordermanagertool.data.enums;

public enum Status {

    NEW("New"),
    IN_PROGRESS("In progress"),
    DONE("Done"),
    ARCHIVED76("Archived"),
    CANCELLED("Cancelled");

    private final String name;

    Status(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public static Status getStatusByName(String name) {
        for (Status status : values()) {
            if (status.getName().equals(name)) {
                return status;
            }
        }
        return null;
    }


}
