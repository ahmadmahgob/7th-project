package thread;
import model.Student;
import io.FileInputService;
import exception.FileProcessingException;

public class StudentFileReader extends Thread {
    private final String filename;
    private final ThreadSafeGradeRepository repository;

    public StudentFileReader(String filename, ThreadSafeGradeRepository repository) {
        this.filename = filename;
        this.repository = repository;
    }

    @Override
    public void run() {
        try {
            var studentsFromFile = FileInputService.readStudentFile(filename);
            for (Student s : studentsFromFile) {
                repository.addStudent(s);
            }
        } catch (Exception e) {
            System.err.println("Error reading file " + filename + ": " + e.getMessage());
        }
    }
}