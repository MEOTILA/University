package org.example.service;

import org.example.entity.Admin;
import org.example.entity.Student;

public interface AdminService {
    public Admin save(Admin admin);

    public boolean adminLogin(String username, String password);

    public void adminLogout();
}
