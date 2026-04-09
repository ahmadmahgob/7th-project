package model;
import exception.InvalidDataException;
import exception.InvalidGradeException;
import java.util.ArrayList;

public class Student {
    private String studentId;
    private String name;
    private ArrayList<Double> grades;

    public Student(String studentId, String name, ArrayList<Double> grades) throws InvalidDataException {
        if (studentId == null || studentId.trim().isEmpty() || name == null || name.trim().isEmpty()) {
            throw new InvalidDataException("Student ID and name cannot be null or empty");
        }
        this.studentId = studentId.trim();
        this.name = name.trim();
        this.grades = grades != null ? new ArrayList<>(grades) : new ArrayList<>();
    }

    public String getStudentId() { return studentId; }
    public String getName() { return name; }
    public ArrayList<Double> getGrades() { return new ArrayList<>(grades); }

    public void setGrade(int index, double grade) throws InvalidGradeException {
        if (grade < 0 || grade > 100) {
            throw new InvalidGradeException("Grade must be between 0 and 100");
        }
        if (index >= 0 && index < grades.size()) {
            grades.set(index, grade);
        }
    }

    public double calculateAverage() {
        if (grades.isEmpty()) return 0.0;
        double sum = 0;
        for (Double g : grades) sum += g;
        return sum / grades.size();
    }

    public char getLetterGrade() {
        double avg = calculateAverage();
        if (avg >= 90) return 'A';
        if (avg >= 80) return 'B';
        if (avg >= 70) return 'C';
        if (avg >= 60) return 'D';
        return 'F';
    }

    @Override
    public String toString() {
        return "Student{" + studentId + ", " + name + ", avg=" + String.format("%.2f", calculateAverage()) + "}";
    }
}