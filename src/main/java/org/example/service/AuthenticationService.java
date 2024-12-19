package org.example.service;

import org.example.entity.Users;

public interface AuthenticationService {
    public void setLoggedUser(Users user);

    public Users getLoggedInUser();

    public void logout();

    public boolean isLoggedIn();
}
