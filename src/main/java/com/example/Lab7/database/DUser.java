package com.example.Lab7.database;

import com.example.Lab7.bean.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DUser {
    private Connection connection;
    private String tableName;

    public DUser() {
        this.connection = new Connection("jdbc:mysql://localhost:3306/lab7", "tal", "tal");
        this.tableName = "user";
    }

    private User getUserObject(Integer id, String username, String password, String name) {
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setPassword(password);
        user.setName(name);
        return user;
    }

    public User getUserById(Integer id) {
        connection.openConnect();
        User user = null;
        ResultSet resultSet = connection.getData("SELECT * FROM " + tableName + " WHERE id = " + id);
        try {
            resultSet.next();

            Integer userId = resultSet.getInt("id");
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            String name = resultSet.getString("name");

            user = getUserObject(userId, username, password, name);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public User getUserByUsernamePassword(String username, String password) {
        connection.openConnect();
        User user = null;
        String sql = "SELECT * FROM "+ tableName +" WHERE username = '" + username + "' AND password = '" + password + "'";
        ResultSet resultSet = connection.getData(sql);

        try {
            resultSet.next();

            Integer userId = resultSet.getInt("id");
            String name = resultSet.getString("name");

            user = getUserObject(userId, username, password, name);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }
}
