package org.example.service;

import org.example.entity.Users;

public interface UsersService {
    public Users save(Users user);
    public boolean userLogin(String username, String password);
}
