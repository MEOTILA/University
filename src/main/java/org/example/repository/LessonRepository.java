package org.example.repository;

import org.example.entity.Lesson;
import org.example.entity.Student;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public interface LessonRepository {
    public Lesson save(Session session, Lesson lesson);
    public List<Lesson> findAll(Session session);
    public Optional<Lesson> findById(Session session, Long id);

}
