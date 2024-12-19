package org.example.repository.impl;

import org.example.entity.Users;
import org.example.repository.UsersRepository;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public class UsersRepositoryImpl implements UsersRepository {
    public Users save(Session session, Users user) {
        session.persist(user);
        return user;
    }


    public List<Users> findAll(Session session) {
        return session
                .createQuery("from org.example.entity.Users", Users.class)
                .list();
    }

    public Optional<Users> findById(Session session, Long id) {
        return session.byId(Users.class).loadOptional(id);
    }

    public int deleteById(Session session, Long id) {
        return session.createMutationQuery(
                        "DELETE FROM org.example.entity.Users c WHERE c.id = :id"
                )
                .setParameter("id", id)
                .executeUpdate();
    }
}
