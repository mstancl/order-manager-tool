/*
package com.mstancl.ordermanagertool.util.filter;

import com.mstancl.ordermanagertool.data.pojo.Order;
import com.mstancl.ordermanagertool.util.filter.interfaces.Sort;
import com.mstancl.ordermanagertool.util.filter.interfaces.Specification;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class BetterSorter implements Sort<Order, LocalDate> {


    @Override
    public List<Order> sort(List<Order> items, Specification<Order> specification) {
        if (specification.isSatisfied(items)){

            items.sort(specification.getComparator()).reversed());
        }else {
            items.sort(specification.getComparator());
        }
        return null;
    }

    @Override
    public List<Order> sort(List<Order> items, Specification<Order, LocalDate> specification) {
        return null;
    }
}
*/
