package com.mstancl.ordermanagertool.dao;

import com.mstancl.ordermanagertool.data.pojo.Order;
import com.mstancl.ordermanagertool.util.filter.OrderFilter;
import com.mstancl.ordermanagertool.util.filter.specification.Specification;

import java.util.List;

public interface DAO<T> {

    void write(T t);

    void update(T t);

    void print(T t);

    List<T> getAllRecords();
    List<T> getAllRecords(OrderFilter orderFilter, Specification<Order> filter);
    List<T> getAllRecords(OrderFilter orderFilter,List<Specification<Order>> listOfFilters);

    T getRecordByID(long id);

}
