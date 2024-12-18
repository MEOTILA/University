package org.example.repository.impl;

import org.example.entity.Lesson;
import org.example.entity.Student;
import org.example.entity.Teacher;
import org.example.repository.TeacherRepository;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public class TeacherRepositoryImpl implements TeacherRepository {
    public Teacher save(Session session, Teacher teacher) {
        session.persist(teacher);
        return teacher;
    }

    public List<Teacher> findAll(Session session) {
        return session
                .createQuery("from org.example.entity.Teacher", Teacher.class)
                .list();
    }

    public Optional<Teacher> findById(Session session, Long id) {
        return session.byId(Teacher.class).loadOptional(id);
    }
}
