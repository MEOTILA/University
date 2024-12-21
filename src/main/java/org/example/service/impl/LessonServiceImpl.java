package org.example.service.impl;

import org.example.SessionFactoryInstance;
import org.example.entity.Lesson;
import org.example.entity.Student;
import org.example.entity.Teacher;
import org.example.exceptions.LessonNotFoundException;
import org.example.exceptions.TeacherNotFoundException;
import org.example.repository.impl.LessonRepositoryImpl;
import org.example.repository.impl.TeacherRepositoryImpl;
import org.example.service.LessonService;

import java.util.List;
import java.util.Optional;

public class LessonServiceImpl implements LessonService {
    LessonRepositoryImpl lessonRepositoryImpl = new LessonRepositoryImpl();
    TeacherRepositoryImpl teacherRepositoryImpl = new TeacherRepositoryImpl();

    /*public Lesson save(Lesson lesson) {
        try (var session = SessionFactoryInstance.sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                if (lesson.getTeacher() == null) {
                    throw new RuntimeException("Teacher cannot be null for the lesson");
                }

                if (lesson.getStudents() != null) {
                    for (Student student : lesson.getStudents()) {
                        student.getLessons().add(lesson);
                    }
                }
                lessonRepositoryImpl.save(session, lesson);
                session.getTransaction().commit();
                return lesson;
            } catch (Exception e) {
                session.getTransaction().rollback();
                throw new RuntimeException(e);
            }
        }
    }*/

    public Lesson save(Lesson lesson, Long teacherId) {
        try (var session = SessionFactoryInstance.sessionFactory.openSession()) {
            try {
                session.beginTransaction();

                Teacher teacher = teacherRepositoryImpl.findById2(session, teacherId);
                if (teacher == null) {
                    throw new RuntimeException("Teacher with ID " + teacherId + " not found.");
                }

                lesson.setTeacher(teacher);

                if (lesson.getStudents() != null) {
                    for (Student student : lesson.getStudents()) {
                        student.getLessons().add(lesson);
                    }
                }

                // Save the lesson
                lessonRepositoryImpl.save(session, lesson);
                session.getTransaction().commit();
                return lesson;
            } catch (Exception e) {
                session.getTransaction().rollback();
                throw new RuntimeException("Error saving lesson", e);
            }
        }
    }


    public List<Lesson> findAll() {
        try (var session = SessionFactoryInstance.sessionFactory.openSession()) {
            session.beginTransaction();

            List<Lesson> result = lessonRepositoryImpl.findAll(session);

            System.out.println("List of Lessons:");
            if (result != null && !result.isEmpty()) {
                for (Lesson lesson : result) {
                    System.out.println("ID: " + lesson.getId());
                    System.out.println("Lesson Name: " + lesson.getLessonName());
                    System.out.println("Lesson Unit: " + lesson.getLessonUnit());
                    System.out.println("Lesson Capacity: " + lesson.getLessonCapacity());
                    System.out.println("Teacher: " + lesson.getTeacher());
                    System.out.println("Start Date: " + lesson.getStartDate());
                    System.out.println("Students: " + lesson.getStudents());
                    System.out.println("---------------------------------");
                }
            } else {
                System.out.println("No lessons found.");
            }

            session.getTransaction().commit();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error retrieving lessons", e);
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
                    System.out.println("Students: " + lesson.getStudents());
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
                    throw new LessonNotFoundException("Lesson Not Found!" + id);
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
                throw new RuntimeException(e);
            }
        }
    }

    public Lesson update(Lesson lesson, Long lessonId) {
        try (var session = SessionFactoryInstance.sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                var existingLesson = lessonRepositoryImpl.findById(session, lessonId)
                        .orElseThrow(() -> new RuntimeException("Lesson not found!"));

                existingLesson.setLessonName(lesson.getLessonName());
                existingLesson.setLessonUnit(lesson.getLessonUnit());
                existingLesson.setLessonCapacity(lesson.getLessonCapacity());
                existingLesson.setStartDate(lesson.getStartDate());

                if (!existingLesson.getStudents().equals(lesson.getStudents())) {
                    existingLesson.getStudents().clear();
                    existingLesson.getStudents().addAll(lesson.getStudents());
                    for (Student student : lesson.getStudents()) {
                        student.getLessons().add(existingLesson);
                    }
                }

                lessonRepositoryImpl.save(session, existingLesson);
                session.getTransaction().commit();
                return existingLesson;
            } catch (Exception e) {
                session.getTransaction().rollback();
                throw new RuntimeException(e);
            }
        }
    }

    public Lesson updateTeacher(Lesson lesson, Long teacherId, Long lessonId) {
        try (var session = SessionFactoryInstance.sessionFactory.openSession()) {
            try {
                session.beginTransaction();

                var updatingLesson = lessonRepositoryImpl.findById(session, lessonId)
                        .orElseThrow(() -> new RuntimeException("Lesson not found!"));

                Teacher teacher = teacherRepositoryImpl.findById2(session, teacherId);
                if (teacher == null) {
                    throw new TeacherNotFoundException("Teacher with ID " + teacherId + " not found.");
                }


                updatingLesson.setLessonName(lesson.getLessonName());
                updatingLesson.setLessonUnit(lesson.getLessonUnit());
                updatingLesson.setLessonCapacity(lesson.getLessonCapacity());
                updatingLesson.setTeacher(teacher);
                updatingLesson.setStartDate(lesson.getStartDate());

                if (!updatingLesson.getStudents().equals(lesson.getStudents())) {
                    for (Student student : updatingLesson.getStudents()) {
                        if (!lesson.getStudents().contains(student)) {
                            student.getLessons().remove(updatingLesson);
                        }
                    }

                    updatingLesson.getStudents().clear();
                    updatingLesson.getStudents().addAll(lesson.getStudents());

                    for (Student student : lesson.getStudents()) {
                        student.getLessons().add(updatingLesson);  // Ensure bi-directional relationship
                    }
                }

                lessonRepositoryImpl.save(session, updatingLesson);

                session.getTransaction().commit();

                return updatingLesson;
            } catch (Exception e) {
                session.getTransaction().rollback();
                throw new RuntimeException("Error updating teacher for the lesson", e);
            }
        }
    }
}
