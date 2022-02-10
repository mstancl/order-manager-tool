package com.mstancl.ordermanagertool.database;

import com.mstancl.ordermanagertool.data.pojo.Customer;
import com.mstancl.ordermanagertool.data.pojo.Order;

import java.sql.*;
import java.time.format.DateTimeFormatter;

public class DatabaseManager {


    private Connection conn;

    private Connection getConnection() {
        return conn;
    }

  /*  public static void main(String[] args) {
        DatabaseManager databaseManager = new DatabaseManager();
        databaseManager.createNewTable("test.db", "Orders", "ID INT PRIMARY KEY     NOT NULL", "CUSTOMER_NAME           TEXT    NOT NULL", "CUSTOMER_PHONE           TEXT    NOT NULL", "CUSTOMER_EMAIL           TEXT    NOT NULL", "DATE_WHEN_RECEIVED            INT     NOT NULL", "DATE_WHEN_DUE            INT     NOT NULL", "ORDER_TYPE           TEXT    NOT NULL", "DESCRIPTION           TEXT    NOT NULL", "SOLUTION           TEXT    NOT NULL", "ESTIMATED_PRICE           INT    NOT NULL", "STATUS           TEXT    NOT NULL");
    }*/

    private Connection connect(String databaseName) {
        String url = "jdbc:sqlite:" + databaseName;
        if (getConnection() == null) {
            try {
                conn = DriverManager.getConnection(url);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return getConnection();
    }

    public void createNewTable(String databaseName, String tableName, String... columns) {
        String url = "jdbc:sqlite:" + databaseName;

        // SQL statement for creating a new table
        String sqlQuery = "CREATE TABLE " + tableName;
        if (columns.length > 0) {
            sqlQuery += "(";
            for (int i = 0; i < columns.length; i++) {
                if (i != columns.length - 1)
                    sqlQuery += columns[i] + ",";
                else
                    sqlQuery += columns[i];
            }
            sqlQuery += ")";
        }
        try {
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            stmt.execute(sqlQuery);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insert(String databaseName, Order order) {
        String sql = "INSERT INTO Orders(ID,CUSTOMER_NAME,CUSTOMER_PHONE,CUSTOMER_EMAIL,DATE_WHEN_RECEIVED,DATE_WHEN_DUE,ORDER_TYPE,DESCRIPTION,SOLUTION,ESTIMATED_PRICE,STATUS) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        try {
            Connection conn = connect(databaseName);
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, order.getId());
            pstmt.setString(2, order.getCustomer().getFullName());
            pstmt.setString(3, order.getCustomer().getPhoneNumber());
            pstmt.setString(4, order.getCustomer().getEmail());
            pstmt.setLong(5, Long.parseLong(order.getDateWhenReceived().format(formatter)));
            pstmt.setLong(6, Long.parseLong(order.getDueDate().format(formatter)));
            pstmt.setString(7, order.getOrderType());
            pstmt.setString(8, order.getDescriptionOfOrder());
            pstmt.setString(9, order.getSolutionForOrder());
            pstmt.setLong(10, order.getEstimatedPrice());
            pstmt.setString(11, order.getStatus().getName());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


}
