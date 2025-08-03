 package service;

import model.Student;
import model.Course;
import dao.StudentDAO;

import java.util.*;

public class RegistrationService {

    private static Scanner scanner = new Scanner(System.in);
    private static List<Student> students = new ArrayList<>();
    private static List<Course> courses = new ArrayList<>();

    public static void start() {
        initializeCourses();

        while (true) {
            System.out.println("\n=== Student Registration System ===");
            System.out.println("1. Register New Student");
            System.out.println("2. View All Students");
            System.out.println("3. Enroll Student in a Course");
            System.out.println("4. View Student's Courses");
            System.out.println("5. Exit");
            System.out.print("Select an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    registerStudent();
                    break;
                case "2":
                    viewStudents();
                    break;
                case "3":
                    enrollStudentInCourse();
                    break;
                case "4":
                    viewStudentCourses();
                    break;
                case "5":
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void initializeCourses() {
        courses.add(new Course("CS101", "Intro to Programming", 3));
        courses.add(new Course("MATH201", "Calculus", 4));
        courses.add(new Course("ENG105", "English Composition", 2));
    }

    private static void registerStudent() {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        System.out.print("Enter student ID: ");
        String id = scanner.nextLine();

        Student student = new Student(name, id);
        students.add(student);
        System.out.println("Student registered successfully.");
    }

    private static void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No students registered.");
            return;
        }

        System.out.println("\nRegistered Students:");
        for (Student s : students) {
            System.out.println("- " + s.getId() + ": " + s.getName());
        }
    }

    private static void enrollStudentInCourse() {
        System.out.print("Enter student ID: ");
        String studentId = scanner.nextLine();
        Student student = findStudentById(studentId);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.println("Available Courses:");
        for (int i = 0; i < courses.size(); i++) {
            Course c = courses.get(i);
            System.out.println((i + 1) + ". " + c.getCode() + " - " + c.getTitle());
        }

        System.out.print("Select course number to enroll: ");
        int courseIndex = Integer.parseInt(scanner.nextLine()) - 1;

        if (courseIndex < 0 || courseIndex >= courses.size()) {
            System.out.println("Invalid course selection.");
            return;
        }

        Course selected = courses.get(courseIndex);
        student.enrollInCourse(selected);
        System.out.println("Student enrolled in " + selected.getTitle() + ".");
    }

    private static void viewStudentCourses() {
        System.out.print("Enter student ID: ");
        String studentId = scanner.nextLine();
        Student student = findStudentById(studentId);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        List<Course> enrolled = student.getCourses();
        if (enrolled.isEmpty()) {
            System.out.println("Student is not enrolled in any courses.");
        } else {
            System.out.println("Courses for " + student.getName() + ":");
            for (Course c : enrolled) {
                System.out.println("- " + c.getCode() + " " + c.getTitle());
            }
        }
    }

    private static Student findStudentById(String id) {
        for (Student s : students) {
            if (s.getId().equalsIgnoreCase(id)) {
                return s;
            }
        }
        return null;
    }
}
