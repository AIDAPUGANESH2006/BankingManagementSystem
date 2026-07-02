package com.bank.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL =
            "jdbc:mysql://localhost:3306/banking_management_system";

    private static final String USER = "root";

    private static final String PASSWORD = "Ganesh@2006"; // <-- Put your MySQL password here

    private static Connection connection;

    private DBConnection() {}

    public static Connection getConnection() {

        try {

            if (connection == null || connection.isClosed()) {

                connection = DriverManager.getConnection(URL, USER, PASSWORD);

                System.out.println("Database Connected Successfully.");

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return connection;
    }
}