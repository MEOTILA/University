package org.example.repository;

import org.example.entity.Teacher;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public interface TeacherRepository {
    public Teacher save(Session session, Teacher teacher);

    public List<Teacher> findAll(Session session);

    public Optional<Teacher> findById(Session session, Long id);

    Teacher findById2(Session session, Long teacherId);

    public int deleteById(Session session, Long id);

    public Optional<Teacher> findByUsername(Session session, String username);
}
