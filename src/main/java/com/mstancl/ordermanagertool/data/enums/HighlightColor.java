package com.mstancl.ordermanagertool.data.enums;

public enum HighlightColor {

    BLUE("#0000FF"),
    YELLOW("#FFFF00"),
    RED("#FF0000"),
    GREEN("#00FF00");

    private final String code;

    HighlightColor(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
