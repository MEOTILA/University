package org.example.repository;

import org.example.entity.User;
import org.hibernate.Session;

public interface UserRepository {
    public User save(Session session, User user);
}
