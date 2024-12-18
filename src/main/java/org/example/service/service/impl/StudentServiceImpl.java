package org.example.service.service.impl;

import org.example.SessionFactoryInstance;
import org.example.entity.Lesson;
import org.example.entity.Student;
import org.example.repository.impl.StudentRepositoryImpl;
import org.example.service.StudentService;

import java.util.List;
import java.util.Optional;

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

    public Optional<Student> findById(Long id) {
        try (var session = SessionFactoryInstance.sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                Optional<Student> res = studentRepositoryImpl.findById(session, id);

                if (res.isPresent()) {
                    Student student = res.get();

                    System.out.println("Founded Student:");
                    System.out.println("ID: " + student.getId());
                    System.out.println("Student First Name: " + student.getFirstName());
                    System.out.println("Student Last Name: " + student.getLastName());
                    System.out.println("Username: " + student.getUsername());
                    System.out.println("Phone Number: " + student.getPhoneNumber());
                    System.out.println("Email: " + student.getEmail());
                    System.out.println("National ID: " + student.getNationalId());
                    System.out.println("Student ID: " + student.getStudentId());
                    System.out.println("---------------------------------");
                } else {
                    System.out.println("No student found with ID: " + id);
                }

                session.getTransaction().commit();
                return res;
            } catch (Exception e) {
                session.getTransaction().rollback();
                throw new RuntimeException(e);
            }
        }
    }

    public void deleteById(Long id) {
        try (var session = SessionFactoryInstance.sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                var affectedRows = studentRepositoryImpl.deleteById(session, id);
                if (affectedRows == 0)
                    throw new RuntimeException("Student Not Found!");
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
                throw new RuntimeException(e);
            }
        }
    }

    public Student update(Student student) {
        try (var session = SessionFactoryInstance.sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                var c = studentRepositoryImpl.findById(session, student.getId())
                        .orElseThrow(() -> new RuntimeException("Student not found"));

                c.setFirstName(student.getFirstName());
                c.setLastName(student.getLastName());
                c.setUsername(student.getUsername());
                c.setPassword(student.getPassword());
                c.setPhoneNumber(student.getPhoneNumber());
                c.setEmail(student.getEmail());
                c.setNationalId(student.getNationalId());
                c.setStudentId(student.getStudentId());

                studentRepositoryImpl.save(session, c);
                session.getTransaction().commit();
                return c;
            } catch (Exception e) {
                session.getTransaction().rollback();
                throw new RuntimeException(e);
            }
        }
    }
}
