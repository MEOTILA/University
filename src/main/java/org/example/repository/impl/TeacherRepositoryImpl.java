package org.example.repository.impl;

import org.example.entity.Student;
import org.example.entity.Teacher;
import org.example.repository.TeacherRepository;
import org.hibernate.Session;

import java.util.List;

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
}
