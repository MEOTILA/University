package org.example.service.impl;

import org.example.SessionFactoryInstance;
import org.example.entity.Users;
import org.example.exceptions.FailedToLoginException;
import org.example.repository.impl.UsersRepositoryImpl;
import org.example.service.UsersService;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.Optional;

public class UsersServiceImpl implements UsersService {
    UsersRepositoryImpl userRepositoryImpl = new UsersRepositoryImpl();
    AuthenticationServiceImpl authenticationServiceImpl = new AuthenticationServiceImpl();

    public Users save(Users user) {
        try (var session = SessionFactoryInstance.sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                userRepositoryImpl.save(session, user);
                session.getTransaction().commit();
                return user;
            } catch (Exception e) {
                session.getTransaction().rollback();
                throw new RuntimeException(e);
            }
        }
    }

    public boolean userLogin(String username, String password) {
        try (var session = SessionFactoryInstance.sessionFactory.openSession()) {
            session.beginTransaction();

            Optional<Users> optionalUser = userRepositoryImpl.findByUsername(session, username);

            if (optionalUser.isPresent()) {
                Users user = optionalUser.get();

                if (user.getPassword().equals(password)) {

                    authenticationServiceImpl.setLoggedUser(user);
                    System.out.println("Welcome Dear " + authenticationServiceImpl.getLoggedInUser().getUsername() + "😍");

                    session.getTransaction().commit();
                    return true;
                } else {
                    throw new RuntimeException("Wrong password ❗");
                }
            } else {
                throw new RuntimeException("User not found ❗");
            }
        } catch (Exception e) {
            throw new FailedToLoginException("Error during login: " + e.getMessage());
        }
    }

}
