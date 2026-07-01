package com.fenohasinaKantoMinosoa.transport;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnexionBD {

    private static final String URL =
        "jdbc:postgresql://localhost:5432/transport_test";
    private static final String USER = "postgres";
    private static final String PASSWORD = "police";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}