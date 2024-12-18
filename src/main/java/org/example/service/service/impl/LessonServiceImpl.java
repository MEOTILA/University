package org.example.service.service.impl;

import org.example.SessionFactoryInstance;
import org.example.entity.Lesson;
import org.example.repository.impl.LessonRepositoryImpl;
import org.example.service.LessonService;

import java.util.List;
import java.util.Optional;

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

    public Optional<Lesson> findById(Long id) {
        try (var session = SessionFactoryInstance.sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                Optional<Lesson> res = lessonRepositoryImpl.findById(session, id);

                if (res.isPresent()) {
                    Lesson lesson = res.get();

                    System.out.println("Founded Lesson:");
                    System.out.println("ID: " + lesson.getId());
                    System.out.println("Lesson Name: " + lesson.getLessonName());
                    System.out.println("Lesson Unit: " + lesson.getLessonUnit());
                    System.out.println("Lesson Capacity: " + lesson.getLessonCapacity());
                    System.out.println("Teacher: " + lesson.getTeacher());
                    System.out.println("Start Date: " + lesson.getStartDate());
                    System.out.println("---------------------------------");
                } else {
                    System.out.println("No lesson found with ID: " + id);
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
                var affectedRows = lessonRepositoryImpl.deleteById(session, id);
                if (affectedRows == 0)
                    throw new RuntimeException("Lesson Not Found!");
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
                throw new RuntimeException(e);
            }
        }
    }

    public Lesson update(Lesson lesson) {
        try (var session = SessionFactoryInstance.sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                var c = lessonRepositoryImpl.findById(session, lesson.getId())
                        .orElseThrow(() -> new RuntimeException("Lesson not found"));

                c.setLessonName(lesson.getLessonName());
                c.setLessonUnit(lesson.getLessonUnit());
                c.setLessonCapacity(lesson.getLessonCapacity());
                c.setTeacher(lesson.getTeacher());
                c.setStartDate(lesson.getStartDate());
                lessonRepositoryImpl.save(session, c);
                session.getTransaction().commit();
                return c;
            } catch (Exception e) {
                session.getTransaction().rollback();
                throw new RuntimeException(e);
            }
        }
    }
}
