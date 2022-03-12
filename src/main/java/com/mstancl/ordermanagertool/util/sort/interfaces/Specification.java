package com.mstancl.ordermanagertool.util.sort.interfaces;

import java.util.List;
import java.util.function.Function;

public interface Specification<T,U> {

    boolean isSatisfied(List<T> items);

    Function<T,U> getFunctionalComparator();

}
