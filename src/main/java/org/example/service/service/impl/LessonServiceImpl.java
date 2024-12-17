package org.example.service.service.impl;

import org.example.SessionFactoryInstance;
import org.example.entity.Lesson;
import org.example.entity.Student;
import org.example.entity.Teacher;
import org.example.repository.impl.LessonRepositoryImpl;
import org.example.service.LessonService;

import java.util.List;

public class LessonServiceImpl implements LessonService {
    LessonRepositoryImpl lessonRepositoryImpl = new LessonRepositoryImpl();

    public Lesson save(Lesson lesson) {
        try (var session = SessionFactoryInstance.sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                lessonRepositoryImpl.save(session, lesson);
                session.getTransaction().commit();
                return lesson;
            } catch (Exception e) {
                session.getTransaction().rollback();
                throw new RuntimeException(e);
            }
        }
    }

    public List<Lesson> findAll() {
        try (var session = SessionFactoryInstance.sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                var result = lessonRepositoryImpl.findAll(session);
                System.out.println("List of Lessons:");
                for (Lesson lesson : result) {
                    System.out.println("ID: " + lesson.getId());
                    System.out.println("Lesson Name: " + lesson.getLessonName());
                    System.out.println("Lesson Unit: " + lesson.getLessonUnit());
                    System.out.println("Lesson Capacity: " + lesson.getLessonCapacity());
                    System.out.println("Lesson Name: " + lesson.getTeacher());
                    System.out.println("Start Date: " + lesson.getStartDate());
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
