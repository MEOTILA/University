package org.example.service.impl;

import org.example.SessionFactoryInstance;
import org.example.entity.Admin;
import org.example.entity.Student;
import org.example.entity.Users;
import org.example.exceptions.FailedToLoginException;
import org.example.repository.impl.AdminRepositoryImpl;
import org.example.service.AdminService;

import java.util.Optional;

public class AdminServiceImpl implements AdminService {
    AdminRepositoryImpl adminRepositoryImpl = new AdminRepositoryImpl();
    AuthenticationServiceImpl authenticationServiceImpl = new AuthenticationServiceImpl();


    public Admin save(Admin admin) {
        try (var session = SessionFactoryInstance.sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                adminRepositoryImpl.save(session, admin);
                session.getTransaction().commit();
                return admin;
            } catch (Exception e) {
                session.getTransaction().rollback();
                throw new RuntimeException(e);
            }
        }
    }

    public boolean adminLogin(String username, String password) {
        try (var session = SessionFactoryInstance.sessionFactory.openSession()) {
            session.beginTransaction();

            Optional<Admin> optionalAdmin = adminRepositoryImpl.findByUsername(session, username);

            if (optionalAdmin.isPresent()) {
                Admin admin = optionalAdmin.get();

                if (admin.getPassword().equals(password)) {

                    authenticationServiceImpl.setLoggedUser(admin);
                    System.out.println("Welcome Dear " + authenticationServiceImpl.getLoggedInUser().getUsername() + "😍");

                    session.getTransaction().commit();
                    return true;
                } else {
                    throw new FailedToLoginException("Wrong password or username❗");
                }
            } else {
                throw new FailedToLoginException("Admin not found ❗");
            }
        } catch (Exception e) {
            throw new FailedToLoginException("Error during login: " + e.getMessage());
        }
    }

    public void adminLogout() {
        Admin admin = (Admin) authenticationServiceImpl.getLoggedInUser();
        if (admin != null) {
            System.out.println("Good Bye Dear " + admin.getFirstName() + "👋🏻");
            authenticationServiceImpl.logout();
        } else {
            System.out.println("Error!");
        }
    }
}
