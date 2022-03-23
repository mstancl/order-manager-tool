package com.mstancl.ordermanagertool.data.enums;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Status {

    NEW("New"),
    IN_PROGRESS("In progress"),
    DONE("Done"),
    ARCHIVED("Archived"),
    CANCELLED("Cancelled");

    private final String name;

    Status(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }


    public static List<String> getAllNames() {
        return Arrays
                .stream(values())
                .map(Status::getName)
                .toList();
    }

    public static Status getStatusByName(String name) {
        for (Status status : values()) {
            if (status.getName().equals(name)) {
                return status;
            }
        }
        return null;
    }

    public static List<Status> getAllStatusesExcept(Status... statusToExclude) {
        return Arrays
                .stream(values()).toList()
                .stream()
                .filter(status -> !Arrays.asList(statusToExclude).contains(status))
                .collect(Collectors.toList());
    }


}
