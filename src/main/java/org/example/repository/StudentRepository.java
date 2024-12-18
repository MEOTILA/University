package org.example.repository;

import org.example.entity.Student;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public interface StudentRepository {
    public Student save(Session session, Student student);
    public List<Student> findAll(Session session);
    public Optional<Student> findById(Session session, Long id);
}
