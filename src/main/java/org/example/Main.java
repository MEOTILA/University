package org.example;


import org.example.entity.Lesson;
import org.example.entity.Student;
import org.example.entity.Teacher;
import org.example.entity.User;
import org.example.repository.impl.LessonRepositoryImpl;
import org.example.service.service.impl.LessonServiceImpl;
import org.example.service.service.impl.StudentServiceImpl;
import org.example.service.service.impl.TeacherServiceImpl;
import org.example.service.service.impl.UserServiceImpl;

public class Main {
    static UserServiceImpl userService = new UserServiceImpl();
    static StudentServiceImpl studentService = new StudentServiceImpl();
    static TeacherServiceImpl teacherService = new TeacherServiceImpl();
    static LessonServiceImpl lessonService = new LessonServiceImpl();

    public static void main(String[] args) {

        User user3 = new User (null,"Bell", "Balla", "Bell" , "123456",
                123457890L,1234567891L);
        userService.save(user3);

        Student student1 = Student.builder().lastName("bill").build();
        studentService.save(student1);

        Teacher teacher1 = Teacher.builder().firstName("Sina").build();
        teacherService.save(teacher1);

        Lesson lesson1 = Lesson.builder().lessonName("Math").build();
        lessonService.save(lesson1);



    }
}