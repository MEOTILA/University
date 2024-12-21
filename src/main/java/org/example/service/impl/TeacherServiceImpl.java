package org.example.service.impl;

import org.example.SessionFactoryInstance;

import org.example.entity.Student;
import org.example.entity.Teacher;
import org.example.exceptions.FailedToLoginException;
import org.example.exceptions.TeacherNotFoundException;
import org.example.repository.impl.TeacherRepositoryImpl;
import org.example.service.TeacherService;

import java.util.List;
import java.util.Optional;

public class TeacherServiceImpl implements TeacherService {
    TeacherRepositoryImpl teacherRepositoryImpl = new TeacherRepositoryImpl();
    AuthenticationServiceImpl authenticationServiceImpl = new AuthenticationServiceImpl();

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

    public Optional<Teacher> findById(Long id) {
        try (var session = SessionFactoryInstance.sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                Optional<Teacher> res = teacherRepositoryImpl.findById(session, id);

                if (res.isPresent()) {
                    Teacher teacher = res.get();

                    System.out.println("Founded Teacher:");
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
                } else {
                    System.out.println("No teacher found with ID: " + id);
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
                var affectedRows = teacherRepositoryImpl.deleteById(session, id);
                if (affectedRows == 0)
                    throw new TeacherNotFoundException("Teacher Not Found!" + id);
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
                throw new RuntimeException(e);
            }
        }
    }

    public Teacher update(Teacher teacher) {
        try (var session = SessionFactoryInstance.sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                var c = teacherRepositoryImpl.findById(session, teacher.getId())
                        .orElseThrow(() -> new RuntimeException("Teacher not found!"));

                c.setFirstName(teacher.getFirstName());
                c.setLastName(teacher.getLastName());
                c.setUsername(teacher.getUsername());
                c.setPassword(teacher.getPassword());
                c.setPhoneNumber(teacher.getPhoneNumber());
                c.setEmail(teacher.getEmail());
                c.setNationalId(teacher.getNationalId());
                c.setTeacherField(teacher.getTeacherField());
                c.setTeacherDegree(teacher.getTeacherDegree());
                c.setTeacherId(teacher.getTeacherId());

                teacherRepositoryImpl.save(session, c);
                session.getTransaction().commit();
                return c;
            } catch (Exception e) {
                session.getTransaction().rollback();
                throw new RuntimeException(e);
            }
        }
    }


    public Teacher updatePassword(Teacher teacher) {
        try (var session = SessionFactoryInstance.sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                var c = teacherRepositoryImpl.findById(session, teacher.getId())
                        .orElseThrow(() -> new RuntimeException("Teacher not found"));

                c.setPassword(teacher.getPassword());

                teacherRepositoryImpl.save(session, c);
                session.getTransaction().commit();
                return c;
            } catch (Exception e) {
                session.getTransaction().rollback();
                throw new RuntimeException(e);
            }
        }
    }

    public boolean teacherLogin(String username, String password) {
        try (var session = SessionFactoryInstance.sessionFactory.openSession()) {
            session.beginTransaction();

            Optional<Teacher> optionalTeacher = teacherRepositoryImpl.findByUsername(session, username);

            if (optionalTeacher.isPresent()) {
                Teacher teacher = optionalTeacher.get();

                if (teacher.getPassword().equals(password)) {

                    authenticationServiceImpl.setLoggedUser(teacher);
                    System.out.println("Welcome Dear " + authenticationServiceImpl.getLoggedInUser().getUsername() + "😍");

                    session.getTransaction().commit();
                    return true;
                } else {
                    throw new FailedToLoginException("Wrong password or username ❗");
                }
            } else {
                throw new FailedToLoginException("Teacher not found ❗");
            }
        } catch (Exception e) {
            throw new FailedToLoginException("Error during login: " + e.getMessage());
        }
    }

    public void teacherLogout() {
        Teacher teacher = (Teacher) authenticationServiceImpl.getLoggedInUser();
        if (teacher != null) {
            System.out.println("Good Bye Dear " + teacher.getFirstName() + "👋🏻");
            authenticationServiceImpl.logout();
        } else {
            System.out.println("Error!");
        }
    }
}
