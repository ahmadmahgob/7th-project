package io;
import model.Course;
import model.Student;
import processor.GradeAnalyzer;
import java.io.PrintWriter;

public class ReportGenerator {

    public void generateSummaryReport(Course course) {
        try (PrintWriter writer = new PrintWriter("reports/grade_report.txt")) {
            
            writer.println("=====================================");
            writer.println("     GRADE SUMMARY REPORT");
            writer.println("=====================================");
            writer.println("Course: " + course.getCourseCode() + " - " + course.getCourseName());
            writer.println("Total Students: " + course.getStudents().size());
            writer.println("Class Average: " + String.format("%.2f", course.getClassAverage()));
            
            Student top = course.getTopStudent();
            writer.println("Top Student: " + (top != null ? top.toString() : "None"));
            
            GradeAnalyzer analyzer = new GradeAnalyzer();
            String stats = analyzer.processGrades(course.getStudents());
            writer.println("Statistics: " + stats);
            
            writer.println("\nGrade Distribution:");
            printGradeDistribution(writer, course);
            
            writer.println("\nReport generated successfully.");
            
        } catch (Exception e) {
            System.err.println("Report generation error: " + e.getMessage());
        }
    }

    private void printGradeDistribution(PrintWriter writer, Course course) {
        int a = 0, b = 0, c = 0, d = 0, f = 0;
        for (Student s : course.getStudents()) {
            char grade = s.getLetterGrade();
            if (grade == 'A') a++;
            else if (grade == 'B') b++;
            else if (grade == 'C') c++;
            else if (grade == 'D') d++;
            else f++;
        }
        writer.println("A: " + "*".repeat(a) + " (" + a + ")");
        writer.println("B: " + "*".repeat(b) + " (" + b + ")");
        writer.println("C: " + "*".repeat(c) + " (" + c + ")");
        writer.println("D: " + "*".repeat(d) + " (" + d + ")");
        writer.println("F: " + "*".repeat(f) + " (" + f + ")");
    }
}