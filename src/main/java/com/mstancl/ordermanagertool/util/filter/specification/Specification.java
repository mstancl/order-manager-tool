package com.mstancl.ordermanagertool.util.filter.specification;

public interface Specification<T> {

    boolean isSatisfied(T item);

}
