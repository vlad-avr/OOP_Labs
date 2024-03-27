package com.aircompany.db.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DaoManager {
    public static final String FLIGHTS_TABLE = "flights";
    public static final String BRIGADES_TABLE = "brigades";
    public static final String RACES_TABLE = "races";
    public static final String CREW_TABLE = "crews";
    public static final String PLANES_TABLE = "planes";

    public Connection getConnection(String dbName, String username, String password) {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + dbName, username, password);
            if (connection != null) {
                System.out.println("Connected somewhere");
            } else {
                System.out.println("Did not connect");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return connection;

    }
}
