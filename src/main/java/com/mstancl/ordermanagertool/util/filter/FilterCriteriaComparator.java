package com.mstancl.ordermanagertool.util.filter;

import java.util.Arrays;
import java.util.List;

public enum FilterCriteriaComparator {

    EQUALS("="),
    LESS_THAN("<"),
    GREATER_THAN(">"),
    LESS_OR_EQUAL_TO("<="),
    GREATER_OR_EQUAL_TO(">=");

    String sign;

    FilterCriteriaComparator(String sign) {
        this.sign = sign;
    }

    public String getSign() {
        return sign;
    }

    public static List<String> getAllSigns() {
        return Arrays.stream(values())
                .map(FilterCriteriaComparator::getSign)
                .toList();
    }


}
