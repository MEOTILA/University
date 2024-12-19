package org.example.service.impl;

import org.example.SessionFactoryInstance;
import org.example.entity.Users;
import org.example.repository.impl.UsersRepositoryImpl;
import org.example.service.UsersService;

public class UsersServiceImpl implements UsersService {
    UsersRepositoryImpl userRepositoryImpl = new UsersRepositoryImpl();

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
}
