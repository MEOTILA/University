package org.example.repository.impl;

import org.example.entity.User;
import org.example.repository.UserRepository;
import org.hibernate.Session;

public class UserRepositoryImpl implements UserRepository {
    public User save(Session session, User user) {
        session.persist(user);
        return user;
    }
}
