package org.example.repository;

import org.example.entity.Lesson;
import org.hibernate.Session;

public interface LessonRepository {
    public Lesson save(Session session, Lesson lesson);
}
