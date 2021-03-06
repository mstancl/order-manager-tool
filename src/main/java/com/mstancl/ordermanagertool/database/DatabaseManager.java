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

    private Connection connect(String databaseName) throws SQLException {
        String url = "jdbc:sqlite:" + databaseName;
        if (getConnection() == null || getConnection().isClosed()) {
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
        StringBuilder sqlQuery = new StringBuilder("CREATE TABLE " + tableName);
        if (columns.length > 0) {
            sqlQuery.append("(");
            for (int i = 0; i < columns.length; i++) {
                if (i != columns.length - 1)
                    sqlQuery.append(columns[i]).append(",");
                else
                    sqlQuery.append(columns[i]);
            }
            sqlQuery.append(")");
        }
        try {
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            stmt.execute(sqlQuery.toString());
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
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void update(String databaseName, Order order) {
        String sql = "update Orders set CUSTOMER_NAME = '" + order.getCustomer().getFirstName() + " " + order.getCustomer().getSurname() + "', "
                + "CUSTOMER_PHONE ='" + order.getCustomer().getPhoneNumber() + "',"
                + "CUSTOMER_EMAIL ='" + order.getCustomer().getEmail() + "',"
                + "DATE_WHEN_RECEIVED ='" + order.getDateWhenReceived().format(formatter) + "',"
                + "DATE_WHEN_DUE ='" + order.getDueDate().format(formatter) + "',"
                + "ORDER_TYPE ='" + order.getOrderType() + "',"
                + "DESCRIPTION ='" + order.getDescriptionOfOrder() + "',"
                + "SOLUTION ='" + order.getSolutionForOrder() + "',"
                + "ESTIMATED_PRICE ='" + order.getEstimatedPrice() + "',"
                + "STATUS ='" + order.getStatus().getName() + "' WHERE ID = " + order.getId();
        try {
            Connection conn = connect(databaseName);
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //

    public Order returnRecordByID(String databaseName, String tableName, long id) {
        String sql = "SELECT * FROM " + tableName + " WHERE ID =" + id;
        try {
            Connection conn = connect(databaseName);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            Customer customer = new Customer(rs.getString("CUSTOMER_NAME").split(" ")[0], rs.getString("CUSTOMER_NAME").split(" ")[1], rs.getString("CUSTOMER_PHONE"), rs.getString("CUSTOMER_EMAIL"));
            return new Order(rs.getLong("ID"),
                    customer,
                    LocalDate.parse(Long.toString(rs.getLong("DATE_WHEN_RECEIVED")), formatter),
                    LocalDate.parse(Long.toString(rs.getLong("DATE_WHEN_DUE")), formatter),
                    rs.getString("ORDER_TYPE"),
                    rs.getString("DESCRIPTION"),
                    rs.getString("SOLUTION"),
                    rs.getInt("ESTIMATED_PRICE"),
                    Status.getStatusByName(rs.getString("STATUS"))
            );
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
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
