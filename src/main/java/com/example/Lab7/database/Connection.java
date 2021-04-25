package com.example.Lab7.database;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Connection {
    private String url;
    private String username;
    private String password;
    private java.sql.Connection connection;
    private boolean isConnect = false;

    public Connection(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public java.sql.Connection getConnection() {
        if (!isConnect) {
            openConnect();
            isConnect = true;
        }
        return connection;
    }

    public boolean isConnect() {
        return isConnect;
    }

    public void setConnect(boolean connect) {
        isConnect = connect;
    }

    public void setConnection(java.sql.Connection connection) {
        this.connection = connection;
    }

    public void openConnect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            if (!isConnect) {
                connection = DriverManager.getConnection(url, username, password);
                isConnect = true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void closeConnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public ResultSet getData(String sql) {
        ResultSet resultSet = null;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException sqlEx) {
            System.out.println(sqlEx);
        }
        return resultSet;
    }
}
