package com.example.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBManager {
    
    public Connection getConnection(String dbName, String username, String password){
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + dbName, username, password);
            if(connection != null){
                System.out.println("Connected somewhere");
            }else{
                System.out.println("Did not connect");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }

}
