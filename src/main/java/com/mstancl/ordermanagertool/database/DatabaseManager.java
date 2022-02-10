package com.mstancl.ordermanagertool.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DatabaseManager {

    public static void main(String[] args) {
        createNewTable("test.db","orders","ID INT PRIMARY KEY     NOT NULL","NAME           TEXT    NOT NULL,","AGE            INT     NOT NULL,","ADDRESS        CHAR(50),","SALARY         REAL");
    }

    public static void createNewTable(String databaseFileName, String tableName, String... columns) {
        Connection c = null;
        Statement stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:" + databaseFileName);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "CREATE TABLE " + tableName +
                    "(ID INT PRIMARY KEY     NOT NULL," +
                    " NAME           TEXT    NOT NULL, " +
                    " AGE            INT     NOT NULL, " +
                    " ADDRESS        CHAR(50), " +
                    " SALARY         REAL)";
            String sqlQuery =  "CREATE TABLE " + tableName;
            if (columns.length>1){
             sqlQuery+="(";
             for (String column : columns){
                 sqlQuery+=column;
             }
                sqlQuery+=")";
            }
            System.out.println(sqlQuery);


            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Table created successfully");
    }


}
