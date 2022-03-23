package com.mstancl.ordermanagertool.util.fieldColors;

public enum FieldStyle {


    SUCCESS_MESSAGE("-fx-text-fill: GREEN;"),
    ERROR_MESSAGE("-fx-text-fill: RED;"),
    ERROR_STYLE("-fx-border-color: RED; -fx-border-width: 2; -fx-border-radius: 5;"),
    SUCCESS_STYLE("-fx-border-color: #A9A9A9; -fx-border-width: 2; -fx-border-radius: 5;");

    private final String value;

    FieldStyle(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
