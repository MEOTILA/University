package org.example.repository;

import org.example.entity.Student;
import org.hibernate.Session;

public interface StudentRepository {
    public Student save(Session session, Student student);
}
