package org.example.repository;

import org.example.entity.Teacher;
import org.hibernate.Session;

public interface TeacherRepository {
    public Teacher save(Session session, Teacher teacher);
}
