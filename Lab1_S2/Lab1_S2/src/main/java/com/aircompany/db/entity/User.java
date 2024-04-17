package com.aircompany.db.entity;

public class User {
    private String login;
    private String password;

    // Default constructor
    public User() {
    }

    // Getters and setters
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
