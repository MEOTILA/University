package org.example.repository.impl;

import org.example.entity.Teacher;
import org.example.entity.User;
import org.hibernate.Session;

public class TeacherRepositoryImpl {
    public Teacher save(Session session, Teacher teacher) {
        session.persist(teacher);
        return teacher;
    }
}
