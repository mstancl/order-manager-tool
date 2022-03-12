package com.mstancl.ordermanagertool.data.enums;


import java.util.ArrayList;
import java.util.List;

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


    public static List<String> getAllStatuses() {
        List<String> statuses = new ArrayList<>();
        for (Status status : values()) {
            statuses.add(status.getName());
        }
        return statuses;
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
