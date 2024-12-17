package org.example.repository;

import org.example.entity.Student;
import org.hibernate.Session;

import java.util.List;

public interface StudentRepository {
    public Student save(Session session, Student student);
    public List<Student> findAll(Session session);
}
