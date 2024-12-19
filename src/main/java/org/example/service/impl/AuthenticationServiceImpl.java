package org.example.service.impl;

import org.example.entity.Users;

public class AuthenticationServiceImpl {
    private Users loggedInUser;

    public void setLoggedUser(Users user) {
        loggedInUser = user;
    }

    public Users getLoggedInUser() {
        return loggedInUser;
    }

    public void logout() {
        loggedInUser = null;
    }

    public boolean isLoggedIn() {
        return loggedInUser != null;
    }
}
