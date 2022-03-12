package com.mstancl.ordermanagertool.dao;

import com.mstancl.ordermanagertool.data.pojo.Order;
import com.mstancl.ordermanagertool.database.DatabaseManager;

import java.util.List;

public class OrderDAO implements DAO<Order> {

    DatabaseManager databaseManager = new DatabaseManager();

    @Override
    public void write(Order order) {
        databaseManager.insert("test.db", order);
    }

    @Override
    public void update(Order order) {
        databaseManager.update("test.db", order);
    }

    @Override
    public void print(Order order) {
        System.out.println(order.toString());
    }

    @Override
    public List<Order> getAllRecords() {
        return databaseManager.returnAllRecords("test.db", "Orders");
    }

    @Override
    public Order getRecordByID(long id) {
        return databaseManager.returnRecordByID("test.db", "Orders", id);
    }
}
