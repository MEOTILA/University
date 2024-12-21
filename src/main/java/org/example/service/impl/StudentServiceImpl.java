package org.example.service.impl;

import org.example.SessionFactoryInstance;

import org.example.entity.Admin;
import org.example.entity.Student;
import org.example.exceptions.FailedToLoginException;
import org.example.exceptions.StudentNotFoundException;
import org.example.repository.impl.StudentRepositoryImpl;
import org.example.service.StudentService;

import java.util.List;
import java.util.Optional;

public class StudentServiceImpl implements StudentService {
    StudentRepositoryImpl studentRepositoryImpl = new StudentRepositoryImpl();
    AuthenticationServiceImpl authenticationServiceImpl = new AuthenticationServiceImpl();

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
                    throw new StudentNotFoundException("Student not found with ID: " + id);
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

    public Student updatePassword(Student student) {
        try (var session = SessionFactoryInstance.sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                var c = studentRepositoryImpl.findById(session, student.getId())
                        .orElseThrow(() -> new RuntimeException("Student not found"));

                c.setPassword(student.getPassword());

                studentRepositoryImpl.save(session, c);
                session.getTransaction().commit();
                return c;
            } catch (Exception e) {
                session.getTransaction().rollback();
                throw new RuntimeException(e);
            }
        }
    }

    public boolean studentLogin(String username, String password) {
        try (var session = SessionFactoryInstance.sessionFactory.openSession()) {
            session.beginTransaction();

            Optional<Student> optionalStudent = studentRepositoryImpl.findByUsername(session, username);

            if (optionalStudent.isPresent()) {
                Student student = optionalStudent.get();

                if (student.getPassword().equals(password)) {

                    authenticationServiceImpl.setLoggedUser(student);
                    System.out.println("Welcome Dear " + authenticationServiceImpl.getLoggedInUser().getUsername() + "😍");

                    session.getTransaction().commit();
                    return true;
                } else {
                    throw new FailedToLoginException("Wrong password or username ❗");
                }
            } else {
                throw new FailedToLoginException("Admin not found ❗");
            }
        } catch (Exception e) {
            throw new FailedToLoginException("Error during login: " + e.getMessage());
        }
    }

    public void studentLogout() {
        Student student = (Student) authenticationServiceImpl.getLoggedInUser();
        if (student != null) {
            System.out.println("Good Bye Dear " + student.getFirstName() + "👋🏻");
            authenticationServiceImpl.logout();
        } else {
            System.out.println("Error!");
        }
    }
}
