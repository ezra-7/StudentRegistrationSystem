package service;

import dao.GradeDAO;
import model.Grade;
import model.Student;
import model.Course;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class GradeService {
    private GradeDAO gradeDAO;

    public GradeService(GradeDAO gradeDAO) {
        this.gradeDAO = gradeDAO;
    }

    
    public boolean assignGrade(Student student, Course course, double score) {
        if (score < 0 || score > 100) {
            throw new IllegalArgumentException("Score must be between 0 and 100.");
        }
        Grade grade = new Grade(student.getId(), course.getCourseId(), score);
        return gradeDAO.addGrade(grade);
    }

    
    public boolean updateGrade(Student student, Course course, double newScore) {
        if (newScore < 0 || newScore > 100) {
            throw new IllegalArgumentException("Score must be between 0 and 100.");
        }
        Grade grade = new Grade(student.getId(), course.getCourseId(), newScore);
        return gradeDAO.updateGrade(grade);
    }

   
    public double calculateGPA(Student student) {
        List<Grade> grades = gradeDAO.getGradesByStudent(student.getId());
        if (grades.isEmpty()) {
            return 0.0;
        }

        double totalPoints = 0.0;
        int totalCredits = 0;

        for (Grade grade : grades) {
            Course course = getCourseById(grade.getCourseId()); // Assume this fetches the course
            if (course != null) {
                double gradePoint = convertLetterGradeToPoints(grade.getLetterGrade());
                totalPoints += gradePoint * course.getCredit();
                totalCredits += course.getCredit();
            }
        }

        return (totalCredits > 0) ? totalPoints / totalCredits : 0.0;
    }

    
    public String generateTranscript(Student student) {
        List<Grade> grades = gradeDAO.getGradesByStudent(student.getId());
        if (grades.isEmpty()) {
            return "No grades recorded for " + student.getName();
        }

        StringBuilder transcript = new StringBuilder();
        transcript.append("\n=== Transcript for ").append(student.getName()).append(" ===\n");
        transcript.append(String.format("%-10s %-20s %-10s %-10s%n", 
            "Course ID", "Course Title", "Grade", "Credits"));

        double totalPoints = 0.0;
        int totalCredits = 0;

        for (Grade grade : grades) {
            Course course = getCourseById(grade.getCourseId());
            if (course != null) {
                transcript.append(String.format("%-10s %-20s %-10s %-10d%n",
                    course.getCode(),
                    course.getTitle(),
                    grade.getLetterGrade(),
                    course.getCredit()));

                totalPoints += convertLetterGradeToPoints(grade.getLetterGrade()) * course.getCredit();
                totalCredits += course.getCredit();
            }
        }

        double gpa = (totalCredits > 0) ? totalPoints / totalCredits : 0.0;
        transcript.append("\nGPA: ").append(String.format("%.2f", gpa));

        return transcript.toString();
    }

    // Helper method to convert letter grades to points (A=4.0, B=3.0, etc.)
    private double convertLetterGradeToPoints(String letterGrade) {
        switch (letterGrade.toUpperCase()) {
            case "A": return 4.0;
            case "B": return 3.0;
            case "C": return 2.0;
            case "D": return 1.0;
            default: return 0.0;
        }
    }

    
        // Implementation depends on your CourseDAO or data source
        return null; // Replace with actual logic
    }
}