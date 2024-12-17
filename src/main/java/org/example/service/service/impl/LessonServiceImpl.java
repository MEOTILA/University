package org.example.service.service.impl;

import org.example.SessionFactoryInstance;
import org.example.entity.Lesson;
import org.example.entity.Teacher;
import org.example.repository.impl.LessonRepositoryImpl;

public class LessonServiceImpl {
    LessonRepositoryImpl lessonRepositoryImpl = new LessonRepositoryImpl();

    public Lesson save(Lesson lesson) {
        try (var session = SessionFactoryInstance.sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                lessonRepositoryImpl.save(session, lesson);
                session.getTransaction().commit();
                return lesson;
            } catch (Exception e) {
                session.getTransaction().rollback();
                throw new RuntimeException(e);
            }
        }
    }
}
