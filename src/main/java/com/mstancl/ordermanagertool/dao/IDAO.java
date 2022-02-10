package com.mstancl.ordermanagertool.dao;

import java.util.List;

public interface IDAO<T> {

    void write(T t);

    void update(T t);

    void print(T t);

    List<T> getAllRecords();

}
