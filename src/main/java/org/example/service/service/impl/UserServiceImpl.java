package org.example.service.service.impl;

import org.example.SessionFactoryInstance;
import org.example.entity.User;
import org.example.repository.impl.UserRepositoryImpl;

public class UserServiceImpl {
    UserRepositoryImpl userRepositoryImpl = new UserRepositoryImpl();

    public User save(User user) {
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
}
