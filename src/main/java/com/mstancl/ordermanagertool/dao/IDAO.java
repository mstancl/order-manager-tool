package com.mstancl.ordermanagertool.dao;

public interface IDAO<T> {

    void write(T t);

    void update(T t);

    void print(T t);

}
