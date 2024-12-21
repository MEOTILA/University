package org.example.repository;

import org.example.entity.Admin;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public interface AdminRepository {
    public Admin save(Session session, Admin admin);
    public List<Admin> findAll(Session session);
    public Optional<Admin> findById(Session session, Long id);
    public int deleteById(Session session, Long id);
}
