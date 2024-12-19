package org.example.service;

import org.example.entity.Student;
import org.example.entity.Teacher;

public class AuthenticationService {
    private static Teacher loggedInTeacher;
    private static Student loggedInStudent;

    public static void setLoggedInTeacher(Teacher teacher) {
        loggedInTeacher = teacher;
    }

    public static Teacher getLoggedInTeacher() {
        return loggedInTeacher;
    }

    public static void TeacherLogout() {
        loggedInTeacher = null;
    }

    public static boolean teacherIsLoggedIn() {
        return loggedInTeacher != null;
    }




    public static void setLoggedInStudent(Student student) {
        loggedInStudent = student;
    }

    public static Student getLoggedInStudent() {
        return loggedInStudent;
    }

    public static void studentLogout() {
        loggedInStudent = null;
    }

    public static boolean StudentIsLoggedIn() {
        return loggedInStudent != null;
    }
}
