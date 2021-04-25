package com.example.Lab7.service;

import com.example.Lab7.database.DUser;

public class UserService {
    private DUser dUser;

    public UserService() {
        dUser = new DUser();
    }

    public boolean checkUser(String username, String password){
        return dUser.getUserByUsernamePassword(username, password) != null;
    }
}
