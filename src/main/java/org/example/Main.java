package org.example;


import org.example.service.impl.LessonServiceImpl;
import org.example.service.impl.StudentServiceImpl;
import org.example.service.impl.TeacherServiceImpl;
import org.example.service.impl.UserServiceImpl;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static UserServiceImpl userService = new UserServiceImpl();
    static StudentServiceImpl studentServiceImpl = new StudentServiceImpl();
    static TeacherServiceImpl teacherServiceImpl = new TeacherServiceImpl();
    static LessonServiceImpl lessonServiceImpl = new LessonServiceImpl();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {

            System.out.println("Welcome to University app <3");
            System.out.println("Main Menu");
            System.out.println("1. Admin Login");
            System.out.println("2. Teacher Login");
            System.out.println("3. Student Login");
            System.out.println("Choose You Action: ");

            int option = getNum();

            switch (option) {
                case 1:
                    adminMenu();
                    break;

                case 2:
                    teacherMenu();
                    break;

                case 3:
                    studentMenu();
                    break;

                case 4:
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid number. Please try again.");
                    break;
            }

        }
    }

    private static void adminMenu() {

        System.out.println("*Admin Menu*");
        System.out.println("1. View All Teachers List");
        System.out.println("2. View All Student List");
        System.out.println("3. View All Lessons List");
        System.out.println("4. Add a Teacher");
        System.out.println("5. Remove a Teacher");
        System.out.println("6. Add a new Lesson");
        System.out.println("7. Edit a Lesson");
        System.out.println("8. Add a Lesson to a Teacher");
        System.out.println("9. Main Menu");
        System.out.println("Choose You Action:");

        int option = getNum();

        while (true) {
            switch (option) {
                case 1:
                    break;

                case 2:
                    break;

                case 3:
                    break;

                case 4:
                    break;

                case 5:
                    break;

                case 6:
                    break;

                case 7:
                    break;

                case 8:
                    break;

                case 9:
                    return;
            }
        }
    }

        private static void teacherMenu () {

            System.out.println("*Teacher Menu*");
            System.out.println("1. View My Lessons");
            System.out.println("2. View My Student List");
            System.out.println("3. Submit Score");
            System.out.println("4. Main Menu");
            System.out.println("Choose You Action:");

            int option = getNum();

            while (true) {
                switch (option) {
                    case 1:
                        break;

                    case 2:
                        break;

                    case 3:
                        break;

                    case 4:
                        return;
                }
            }
        }


        private static void studentMenu () {

            System.out.println("*Student Menu*");
            System.out.println("1. Take a Course");
            System.out.println("2. View All Courses");
            System.out.println("3. Change Password");
            System.out.println("4. Main Menu");
            System.out.println("Choose You Action:");

            int option = getNum();

            while (true) {
                switch (option) {
                    case 1:
                        break;

                    case 2:
                        break;

                    case 3:
                        break;

                    case 4:
                        return;
                }
            }
        }

    public static int getNum() {
        int num = 0;
        try {
            num = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
        } finally {
            scanner.nextLine();
        }
        return num;
    }

    public static String getString() {
        String input = null;
        try {
            input = scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
        }
        return input;
    }




    }


























/*        User user1 = new User (null,"Bell", "Balla", "Bell" , "123456",
                123457890L,1234567891L);
        userService.save(user1);

        Student student1 = Student.builder().lastName("bill").build();
        studentServiceImpl.save(student1);

        Teacher teacher1 = Teacher.builder().firstName("Sina").build();
        teacherServiceImpl.save(teacher1);

        Lesson lesson1 = Lesson.builder().lessonName("Math").build();
        lessonServiceImpl.save(lesson1);*/

        /*studentServiceImpl.findAll();
        lessonServiceImpl.findAll();
        teacherServiceImpl.findAll();*/

/*        lessonServiceImpl.findById(1L);
        studentServiceImpl.findById(1L);
        teacherServiceImpl.findById(1L);*/

/*        lessonServiceImpl.findById(1L);
        lessonServiceImpl.deleteById(1L);

        studentServiceImpl.findById(1L);
        studentServiceImpl.deleteById(1L);

        teacherServiceImpl.findById(1L);
        teacherServiceImpl.deleteById(1L);*/

/*        Teacher teacher1 = Teacher.builder().id(2L).password("123").build();
        teacherServiceImpl.updatePassword(teacher1);*/





