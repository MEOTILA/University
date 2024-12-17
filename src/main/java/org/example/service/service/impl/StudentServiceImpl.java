package org.example.service.service.impl;

import org.example.SessionFactoryInstance;
import org.example.entity.Student;
import org.example.repository.impl.StudentRepositoryImpl;
import org.example.service.StudentService;

import java.util.List;

public class StudentServiceImpl implements StudentService {
    StudentRepositoryImpl studentRepositoryImpl = new StudentRepositoryImpl();
    public Student save(Student student) {
        try (var session = SessionFactoryInstance.sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                studentRepositoryImpl.save(session, student);
                session.getTransaction().commit();
                return student;
            } catch (Exception e) {
                session.getTransaction().rollback();
                throw new RuntimeException(e);
            }
        }
    }

    public List<Student> findAll() {
        try (var session = SessionFactoryInstance.sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                var result = studentRepositoryImpl.findAll(session);
                System.out.println("List of Students:");
                for (Student student : result) {
                    System.out.println("ID: " + student.getId());
                    System.out.println("Student First Name: " + student.getFirstName());
                    System.out.println("Student Last Name: " + student.getLastName());
                    System.out.println("Username: " + student.getUsername());
                    System.out.println("Phone Number: " + student.getPhoneNumber());
                    System.out.println("Email: " + student.getEmail());
                    System.out.println("National ID: " + student.getNationalId());
                    System.out.println("Student ID: " + student.getStudentId());
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
