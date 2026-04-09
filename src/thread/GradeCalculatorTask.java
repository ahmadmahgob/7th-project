package thread;
import model.Student;

public class GradeCalculatorTask implements Runnable {
    private final Student student;
    private final ThreadSafeGradeRepository repository;

    public GradeCalculatorTask(Student student, ThreadSafeGradeRepository repository) {
        this.student = student;
        this.repository = repository;
    }

    @Override
    public void run() {
        try {
            student.calculateAverage(); // trigger calculation
            repository.addStudent(student);
            System.out.println("✓ Processed: " + student.getStudentId());
        } catch (Exception e) {
            System.err.println("Calculator task error: " + e.getMessage());
        }
    }
}