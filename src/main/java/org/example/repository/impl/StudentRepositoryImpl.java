package org.example.repository.impl;

import org.example.entity.Student;
import org.hibernate.Session;

public class StudentRepositoryImpl {
    public Student save(Session session, Student student) {
        session.persist(student);
        return student;
    }
}
