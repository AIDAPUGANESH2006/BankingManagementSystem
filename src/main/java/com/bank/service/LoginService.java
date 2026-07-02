package com.bank.service;

import com.bank.dao.UserDAO;
import com.bank.model.User;

public class LoginService {

    private UserDAO userDAO = new UserDAO();

    public User authenticate(String username, String password) {

        return userDAO.login(username, password);

    }
}