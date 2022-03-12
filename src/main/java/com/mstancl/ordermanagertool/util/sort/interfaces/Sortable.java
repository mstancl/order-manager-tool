package com.mstancl.ordermanagertool.util.sort.interfaces;

import java.util.List;
import java.util.function.Function;

public interface Sortable<T,U> {



    List<T> sort(List<T> items, Function<T, U> comparator);


}
