package org.example.service.service.impl;

import org.example.SessionFactoryInstance;
import org.example.entity.Lesson;
import org.example.entity.Teacher;
import org.example.repository.impl.TeacherRepositoryImpl;
import org.example.service.TeacherService;

import java.util.List;

public class TeacherServiceImpl implements TeacherService {
    TeacherRepositoryImpl teacherRepositoryImpl = new TeacherRepositoryImpl();

    public Teacher save(Teacher teacher) {
        try (var session = SessionFactoryInstance.sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                teacherRepositoryImpl.save(session, teacher);
                session.getTransaction().commit();
                return teacher;
            } catch (Exception e) {
                session.getTransaction().rollback();
                throw new RuntimeException(e);
            }
        }
    }

    public List<Teacher> findAll() {
        try (var session = SessionFactoryInstance.sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                var result = teacherRepositoryImpl.findAll(session);
                System.out.println("List of Teachers:");
                for (Teacher teacher : result) {
                    System.out.println("ID: " + teacher.getId());
                    System.out.println("Teacher First Name: " + teacher.getFirstName());
                    System.out.println("Teacher Last Name: " + teacher.getLastName());
                    System.out.println("Username: " + teacher.getUsername());
                    System.out.println("Phone Number: " + teacher.getPhoneNumber());
                    System.out.println("Email: " + teacher.getEmail());
                    System.out.println("National ID: " + teacher.getNationalId());
                    System.out.println("Teacher Field: " + teacher.getTeacherField());
                    System.out.println("Teacher Degree: " + teacher.getTeacherDegree());
                    System.out.println("Teacher ID: " + teacher.getTeacherId());
                    System.out.println("---------------------------------");
                }
                session.getTransaction().commit();
                return result;
            } catch (Exception e) {
                session.getTransaction().rollback();
                throw new RuntimeException(e);
            }
        }
    }
}
