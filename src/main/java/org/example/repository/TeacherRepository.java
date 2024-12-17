package org.example.repository;

import org.example.entity.Student;
import org.example.entity.Teacher;
import org.hibernate.Session;

import java.util.List;

public interface TeacherRepository {
    public Teacher save(Session session, Teacher teacher);
    public List<Teacher> findAll(Session session);

}
