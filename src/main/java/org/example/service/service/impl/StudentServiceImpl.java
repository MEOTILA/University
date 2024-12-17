package org.example.service.service.impl;

import org.example.SessionFactoryInstance;
import org.example.entity.Student;
import org.example.repository.impl.StudentRepositoryImpl;

public class StudentServiceImpl {
    StudentRepositoryImpl studentRepositoryImpl = new StudentRepositoryImpl();
    public Student save(Student student) {
        try (var session = SessionFactoryInstance.sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                studentRepositoryImpl.save(session, student);
                session.getTransaction().commit();
                return student;
            } catch (Exception e) {
                session.getTransaction().rollback();
                throw new RuntimeException(e);
            }
        }
    }
}
