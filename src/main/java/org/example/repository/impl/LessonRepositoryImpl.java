package org.example.repository.impl;

import org.example.entity.Lesson;
import org.hibernate.Session;

public class LessonRepositoryImpl {
    public Lesson save(Session session, Lesson lesson) {
        session.persist(lesson);
        return lesson;
    }
}
