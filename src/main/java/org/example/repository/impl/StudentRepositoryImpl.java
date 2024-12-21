package org.example.repository.impl;

import org.example.entity.Student;
import org.example.repository.StudentRepository;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public class StudentRepositoryImpl implements StudentRepository {
    public Student save(Session session, Student student) {
        session.persist(student);
        return student;
    }

    public List<Student> findAll(Session session) {
        return session
                .createQuery("from org.example.entity.Student", Student.class)
                .list();
    }

    public Optional<Student> findById(Session session, Long id) {
        return session.byId(Student.class).loadOptional(id);
    }

    public int deleteById(Session session, Long id) {
        return session.createMutationQuery(
                        "DELETE FROM org.example.entity.Student c WHERE c.id = :id"
                )
                .setParameter("id", id)
                .executeUpdate();
    }
}
