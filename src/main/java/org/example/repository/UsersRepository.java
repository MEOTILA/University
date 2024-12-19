package org.example.repository;

import org.example.entity.Student;
import org.example.entity.Users;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public interface UsersRepository {
    public Users save(Session session, Users user);
    public List<Users> findAll(Session session);
    public Optional<Users> findById(Session session, Long id);
    public int deleteById(Session session, Long id);
}
