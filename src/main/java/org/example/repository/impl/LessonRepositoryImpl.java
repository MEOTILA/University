package org.example.repository.impl;

import org.example.entity.Lesson;
import org.example.repository.LessonRepository;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public class LessonRepositoryImpl implements LessonRepository {
    public Lesson save(Session session, Lesson lesson) {
        session.persist(lesson);
        return lesson;
    }

    /*public List<Lesson> findAll(Session session) {
        return session
                .createQuery("from org.example.entity.Lesson", Lesson.class)
                .list();
    }*/

    public List<Lesson> findAll(Session session) {
        return session.createQuery(
                        "SELECT l FROM Lesson l " +
                                "LEFT JOIN FETCH l.teacher t " +
                                "LEFT JOIN FETCH l.students s", Lesson.class)
                .getResultList();
    }


    public Optional<Lesson> findById(Session session, Long id) {
        return session.byId(Lesson.class).loadOptional(id);
    }

    public int deleteById(Session session, Long id) {
        return session.createMutationQuery(
                        "DELETE FROM Lesson c WHERE c.id = :id"
                )
                .setParameter("id", id)
                .executeUpdate();
    }
}
