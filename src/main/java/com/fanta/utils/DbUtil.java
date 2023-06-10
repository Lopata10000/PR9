package com.fanta.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DbUtil {
    private static final String URL = "jdbc:postgresql://localhost:5432/Theater";
    private static final String USER_NAME = "postgres";
    private static final String PASSWORD = "45435";

    private DbUtil(){}

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL,  USER_NAME, PASSWORD);
    }

}
