package com.mstancl.ordermanagertool.database;

import com.mstancl.ordermanagertool.data.enums.Status;
import com.mstancl.ordermanagertool.data.pojo.Customer;
import com.mstancl.ordermanagertool.data.pojo.Order;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {


    private Connection conn;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

    private Connection getConnection() {
        return conn;
    }

    /*public static void main(String[] args) {
        DatabaseManager databaseManager = new DatabaseManager();
       // databaseManager.returnAllRecords("test.db", "Orders");
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

        try {
            Connection conn = connect(databaseName);
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, order.getId());
            pstmt.setString(2, order.getCustomer().getFirstName() + " " + order.getCustomer().getSurname());
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

    public List<Order> returnAllRecords(String databaseName, String tableName) {
        String sql = "SELECT * FROM " + tableName;
        List<Order> listOfOrders = new ArrayList<>();
        try {
            Connection conn = connect(databaseName);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {

                Customer customer = new Customer(rs.getString("CUSTOMER_NAME").split(" ")[0], rs.getString("CUSTOMER_NAME").split(" ")[1], rs.getString("CUSTOMER_PHONE"), rs.getString("CUSTOMER_EMAIL"));
                listOfOrders.add(new Order(rs.getLong("ID"),
                        customer,
                        LocalDate.parse(Long.toString(rs.getLong("DATE_WHEN_RECEIVED")), formatter),
                        LocalDate.parse(Long.toString(rs.getLong("DATE_WHEN_DUE")), formatter),
                        rs.getString("ORDER_TYPE"),
                        rs.getString("DESCRIPTION"),
                        rs.getString("SOLUTION"),
                        rs.getInt("ESTIMATED_PRICE"),
                        Status.getStatusByName(rs.getString("STATUS"))
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return listOfOrders;
    }


}
