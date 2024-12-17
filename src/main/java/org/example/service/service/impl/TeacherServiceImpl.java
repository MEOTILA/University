package org.example.service.service.impl;

import org.example.SessionFactoryInstance;
import org.example.entity.Teacher;
import org.example.repository.impl.TeacherRepositoryImpl;

public class TeacherServiceImpl {
    TeacherRepositoryImpl teacherRepositoryImpl = new TeacherRepositoryImpl();

    public Teacher save(Teacher teacher) {
        try (var session = SessionFactoryInstance.sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                teacherRepositoryImpl.save(session, teacher);
                session.getTransaction().commit();
                return teacher;
            } catch (Exception e) {
                session.getTransaction().rollback();
                throw new RuntimeException(e);
            }
        }
    }
}
