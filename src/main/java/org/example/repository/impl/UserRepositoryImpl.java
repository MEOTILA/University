package org.example.repository.impl;

import org.example.entity.User;
import org.hibernate.Session;

public class UserRepositoryImpl {
    public User save(Session session, User user) {
        session.persist(user);
        return user;
    }
}
