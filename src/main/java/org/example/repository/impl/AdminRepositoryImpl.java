package org.example.repository.impl;

import org.example.entity.Admin;
import org.example.entity.Teacher;
import org.example.repository.AdminRepository;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public class AdminRepositoryImpl implements AdminRepository {
    @Override
    public Admin save(Session session, Admin admin) {
        session.persist(admin);
        return admin;
    }

    @Override
    public List<Admin> findAll(Session session) {
        return session
                .createQuery("from org.example.entity.Admin", Admin.class)
                .list();
    }

    @Override
    public Optional<Admin> findById(Session session, Long id) {
        return Optional.empty();
    }

    @Override
    public int deleteById(Session session, Long id) {
        return 0;
    }
}