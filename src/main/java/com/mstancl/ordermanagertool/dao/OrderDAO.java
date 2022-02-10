package com.mstancl.ordermanagertool.dao;

import com.mstancl.ordermanagertool.data.pojo.Order;
import com.mstancl.ordermanagertool.database.DatabaseManager;

public class OrderDAO implements IDAO<Order> {

    DatabaseManager databaseManager = new DatabaseManager();

    @Override
    public void write(Order order) {
        databaseManager.insert("test.db", order);
    }

    @Override
    public void update(Order order) {

    }

    @Override
    public void print(Order order) {

    }
}
