package com.ishika.smartstudent.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL      = "jdbc:mysql://localhost:3306/smartstudent";
    private static final String USER     = "root";
    private static final String PASSWORD = "00000000";

    public static Connection getConnection() throws SQLException {
        /* -------- add this block -------- */
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");   // registers JDBC driver
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL driver not found in class-path", e);
        }
        /* -------------------------------- */

        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
