package model;
import java.util.ArrayList;

public class Course {
    private String courseCode;
    private String courseName;
    private ArrayList<Student> students;

    public Course(String courseCode, String courseName) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.students = new ArrayList<>();
    }

    public void addStudent(Student s) {
        if (s != null) students.add(s);
    }

    public double getClassAverage() {
        if (students.isEmpty()) return 0.0;
        double sum = 0;
        for (Student s : students) sum += s.calculateAverage();
        return sum / students.size();
    }

    public Student getTopStudent() {
        if (students.isEmpty()) return null;
        Student top = students.get(0);
        for (Student s : students) {
            if (s.calculateAverage() > top.calculateAverage()) top = s;
        }
        return top;
    }

    public ArrayList<Student> getStudents() {
        return new ArrayList<>(students);
    }

    public String getCourseCode() { return courseCode; }
    public String getCourseName() { return courseName; }
}