package org.example;


import org.example.entity.Admin;
import org.example.entity.Lesson;
import org.example.entity.Student;
import org.example.entity.Teacher;
import org.example.exceptions.FailedToLoginException;
import org.example.service.impl.*;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

public class Main {

    static UsersServiceImpl userServiceImpl = new UsersServiceImpl();
    static AdminServiceImpl adminServiceImpl = new AdminServiceImpl();
    static StudentServiceImpl studentServiceImpl = new StudentServiceImpl();
    static TeacherServiceImpl teacherServiceImpl = new TeacherServiceImpl();
    static LessonServiceImpl lessonServiceImpl = new LessonServiceImpl();
    static AuthenticationServiceImpl authenticationServiceImpl = new AuthenticationServiceImpl();

    private static final Scanner scanner = new Scanner(System.in);

    public static int getInt() {
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

    public static Long getLong() {
        Long num = 0L;
        try {
            num = scanner.nextLong();
        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
        } finally {
            scanner.nextLine();
        }
        return num;
    }

    public static String getString() {
        String str = null;
        try {
            str = scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
        }
        return str;
    }


    public static void main(String[] args) {

        Student student1 = new Student();
        student1.setFirstName("John");
        student1.setLastName("Doe");
        student1.setUsername("johndoe");
        student1.setPassword("Password123!");
        student1.setPhoneNumber("01234567891");
        student1.setEmail("john.doe@example.com");
        student1.setNationalId("1234567893");
        student1.setStudentId("9876543210");
        //studentServiceImpl.save(student1);

        Teacher teacher1 = new Teacher();
        teacher1.setFirstName("Jane");
        teacher1.setLastName("Smith");
        teacher1.setUsername("janesmith");
        teacher1.setPassword("Password123!");
        teacher1.setPhoneNumber("09876543210");
        teacher1.setEmail("jane.smith@example.com");
        teacher1.setNationalId("1234567892");
        teacher1.setTeacherField("Mathematics");
        teacher1.setTeacherDegree("PhD");
        teacher1.setTeacherId("1234567890");
        //teacherServiceImpl.save(teacher1);

        Admin admin1 = new Admin();
        admin1.setFirstName("Sattar");
        admin1.setLastName("Boushehri");
        admin1.setUsername("admin");
        admin1.setPassword("admin");
        admin1.setPhoneNumber("09121125431");
        admin1.setEmail("admin@example.com");
        admin1.setNationalId("0012301271");
        admin1.setAdminId("5");
        //adminServiceImpl.save(admin1);


        Lesson lesson1 = Lesson.builder().lessonName("Math").build();
        //lessonServiceImpl.save(lesson1);


        System.out.println("Welcome to University app <3");

        while (true) {
            System.out.println("*Main Menu*");
            System.out.println("1. Admin Login");
            System.out.println("2. Teacher Login");
            System.out.println("3. Student Login");
            System.out.println("4. Admin Login");
            System.out.println("5. Teacher Login");
            System.out.println("6. Student Login");
            System.out.println("7. Exit");
            System.out.println("Choose You Action: ");

            int option = getInt();

            Map<Integer, Runnable> menuActions = Map.of(
                    1, () -> adminLoginMenu(),
                    2, () -> teacherLoginMenu(),
                    3, () -> studentLoginMenu(),
                    4, () -> adminMenu(),
                    5, () -> teacherMenu(),
                    6, () -> studentMenu(),
                    7, () -> System.exit(0)
            );

            menuActions.getOrDefault(option, () -> System.out.println("Invalid number. Please try again.")).run();
        }
    }

    private static void adminLoginMenu() {
        try {
            System.out.println("Please Enter Your Username:");
            String loginUsername = getString();
            System.out.println("Please Enter Your Password:");
            String loginPassword = getString();
            if (adminServiceImpl.adminLogin(loginUsername, loginPassword)) {
                adminMenu();
            }

        } catch (FailedToLoginException e) {
            System.out.println("Login failed: " + e.getMessage());
        }
    }

    private static void teacherLoginMenu() {
        try {
            System.out.println("Please Enter Your Username:");
            String loginUsername = getString();
            System.out.println("Please Enter Your Password:");
            String loginPassword = getString();
            if (teacherServiceImpl.teacherLogin(loginUsername, loginPassword)) {
                teacherMenu();
            }

        } catch (FailedToLoginException e) {
            System.out.println("Login failed: " + e.getMessage());
        }
    }

    private static void studentLoginMenu() {
        try {
            System.out.println("Please Enter Your Username:");
            String loginUsername = getString();
            System.out.println("Please Enter Your Password:");
            String loginPassword = getString();
            if (studentServiceImpl.studentLogin(loginUsername, loginPassword)) {
                studentMenu();
            }

        } catch (FailedToLoginException e) {
            System.out.println("Login failed: " + e.getMessage());
        }
    }

    private static void adminMenu() {
        while (true) {
            System.out.println("*Admin Menu*");
            System.out.println("1. View All Teachers List");
            System.out.println("2. View All Student List");
            System.out.println("3. View All Lessons List");
            System.out.println("4. Add a Teacher");
            System.out.println("5. Remove a Teacher");
            System.out.println("6. Add a Student");
            System.out.println("7. Remove a Student");
            System.out.println("8. Add a new Lesson");
            System.out.println("9. Remove a Lesson");
            System.out.println("10. Edit a Lesson");
            System.out.println("11. Change a Lesson's Teacher");
            System.out.println("12. Change Password");
            System.out.println("13. Logout");
            System.out.println("Choose You Action:");

            int option = getInt();


            switch (option) {
                case 1:
                    teacherServiceImpl.findAll();
                    break;

                case 2:
                    studentServiceImpl.findAll();
                    break;

                case 3:
                    lessonServiceImpl.findAll();
                    break;

                case 4:
                    Teacher newTeacher = new Teacher();
                    System.out.println("Please Enter the Firstname:");
                    String trFn = getString();
                    newTeacher.setFirstName(trFn);
                    System.out.println("Please Enter the Lastname:");
                    String trLn = getString();
                    newTeacher.setLastName(trLn);
                    System.out.println("Please Enter the Phone Number:");
                    String trPhoneNum = getString();
                    newTeacher.setPhoneNumber(trPhoneNum);
                    System.out.println("Please Enter the Email:");
                    String trEmail = getString();
                    newTeacher.setEmail(trEmail);
                    System.out.println("Please Enter the National ID:");
                    String trNID = getString();
                    newTeacher.setNationalId(trNID);
                    System.out.println("Please Enter the Teacher Field:");
                    String trField = getString();
                    newTeacher.setTeacherField(trField);
                    System.out.println("Please Enter the Teacher Degree:");
                    String trDegree = getString();
                    newTeacher.setTeacherDegree(trDegree);
                    System.out.println("Please Enter the Teacher ID:");
                    String trID = getString();
                    newTeacher.setTeacherId(trID);

                    newTeacher.setUsername(trID);
                    newTeacher.setPassword(trNID);

                    teacherServiceImpl.save(newTeacher);


                    break;

                case 5:
                    System.out.println("Enter the Teacher ID to Remove:");
                    Long teacherId = getLong();
                    teacherServiceImpl.deleteById(teacherId);
                    break;

                case 6:
                    Student newStudent = new Student();
                    System.out.println("Please Enter the Firstname:");
                    String stFn = getString();
                    newStudent.setFirstName(stFn);
                    System.out.println("Please Enter the Lastname:");
                    String stLn = getString();
                    newStudent.setLastName(stLn);
                    System.out.println("Please Enter the Phone Number:");
                    String stPhoneNum = getString();
                    newStudent.setPhoneNumber(stPhoneNum);
                    System.out.println("Please Enter the Email:");
                    String stEmail = getString();
                    newStudent.setEmail(stEmail);
                    System.out.println("Please Enter the National ID:");
                    String stNID = getString();
                    newStudent.setNationalId(stNID);
                    System.out.println("Please Enter the Student ID:");
                    String stID = getString();
                    newStudent.setStudentId(stID);

                    newStudent.setUsername(stID);
                    newStudent.setPassword(stNID);

                    studentServiceImpl.save(newStudent);

                    break;

                case 7:
                    System.out.println("Enter the Student ID to Remove:");
                    Long studentId = getLong();
                    studentServiceImpl.deleteById(studentId);
                    break;

                case 8:
                    Lesson newLesson = new Lesson();
                    System.out.println("Please Enter the Lesson Name:");
                    String lessonName = getString();
                    newLesson.setLessonName(lessonName);
                    System.out.println("Please Enter the Lesson Unit:");
                    Integer lessonUnit = getInt();
                    newLesson.setLessonUnit(lessonUnit);
                    System.out.println("Please Enter the Lesson Capacity:");
                    Integer lessonCap = getInt();
                    newLesson.setLessonCapacity(lessonCap);
                    System.out.println("Please Enter the Lesson Teacher ID:");
                    Long trId = getLong();
                    System.out.println("Please Enter the Year of the Start:");
                    int year = getInt();
                    System.out.println("Please Enter the Month of the Start:");
                    int month = getInt();
                    System.out.println("Please Enter the Day of the Start:");
                    int day = getInt();
                    LocalDate startDate = LocalDate.of(year, month, day);
                    newLesson.setStartDate(startDate);

                    lessonServiceImpl.save(newLesson, trId);
                    break;

                case 9:
                    System.out.println("Please Enter the Lesson ID to Remove:");
                    Long lessonIdtoRemove = getLong();
                    lessonServiceImpl.deleteById(lessonIdtoRemove);
                    break;

                case 10:

                    System.out.println("Please Enter the Lesson ID:");
                    Long lessonId2 = getLong();

                    Optional<Lesson> optionalLesson2 = lessonServiceImpl.findById(lessonId2);

                    if (optionalLesson2.isPresent()) {
                        Lesson editingLesson = optionalLesson2.get();

                        System.out.println("Please Enter the Lesson Name:");
                        String lessonName2 = getString();
                        editingLesson.setLessonName(lessonName2);

                        System.out.println("Please Enter the Lesson Unit:");
                        Integer lessonUnit2 = getInt();
                        editingLesson.setLessonUnit(lessonUnit2);

                        System.out.println("Please Enter the Lesson Capacity:");
                        Integer lessonCap2 = getInt();
                        editingLesson.setLessonCapacity(lessonCap2);

                        System.out.println("Please Enter the Year of the Start:");
                        int year2 = getInt();
                        System.out.println("Please Enter the Month of the Start:");
                        int month2 = getInt();
                        System.out.println("Please Enter the Day of the Start:");
                        int day2 = getInt();
                        LocalDate startDate2 = LocalDate.of(year2, month2, day2);
                        editingLesson.setStartDate(startDate2);

                        lessonServiceImpl.update(editingLesson, lessonId2);

                        System.out.println("Lesson updated successfully!");
                    } else {
                        System.out.println("Lesson with ID " + lessonId2 + " not found.");
                    }

                    break;

                case 11:
                    System.out.println("Please Enter the Lesson ID:");
                    Long lessonIdToUpdate = getLong();
                    System.out.println("Please Enter the Lesson Teacher ID:");
                    Long trId2 = getLong();

                    Optional<Lesson> optionalLesson = lessonServiceImpl.findById(lessonIdToUpdate);

                    if (optionalLesson.isPresent()) {
                        Lesson updatingLesson = optionalLesson.get();

                        lessonServiceImpl.updateTeacher(updatingLesson, trId2, lessonIdToUpdate);
                        System.out.println("Lesson updated successfully.");
                    } else {
                        System.out.println("Lesson with ID " + lessonIdToUpdate + " not found.");
                    }

                    break;

                case 12:
                    //TODO**********************************************
                    System.out.println("Please Enter Your New Password:");
                    authenticationServiceImpl.getLoggedInUser().getId();
                    break;

                case 13:
                    adminServiceImpl.adminLogout();
                    return;
            }
        }
    }

    private static void teacherMenu() {

        System.out.println("*Teacher Menu*");
        System.out.println("1. View My Lessons");
        System.out.println("2. View My Student List");
        System.out.println("3. Submit Score");
        System.out.println("4. Logout");
        System.out.println("Choose You Action:");

        int option = getInt();

        while (true) {
            switch (option) {
                case 1:
                    break;

                case 2:
                    break;

                case 3:
                    break;

                case 4:
                    teacherServiceImpl.teacherLogout();
                    return;
            }
        }
    }


    private static void studentMenu() {

        System.out.println("*Student Menu*");
        System.out.println("1. Take a Course");
        System.out.println("2. View All Courses");
        System.out.println("3. Change Password");
        System.out.println("4. Logout");
        System.out.println("Choose You Action:");

        int option = getInt();

        while (true) {
            switch (option) {
                case 1:
                    break;

                case 2:
                    break;

                case 3:
                    break;

                case 4:
                    studentServiceImpl.studentLogout();
                    return;
            }
        }
    }


}
