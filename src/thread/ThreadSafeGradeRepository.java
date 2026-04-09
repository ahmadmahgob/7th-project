package thread;
import model.Student;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

public class ThreadSafeGradeRepository {
    private final ConcurrentHashMap<String, Student> students = new ConcurrentHashMap<>();
    private boolean productionDone = false;

    public synchronized void addStudent(Student s) {
        if (s != null) {
            students.put(s.getStudentId(), s);
            notifyAll();
        }
    }

    public synchronized Collection<Student> getAllStudents() {
        while (students.isEmpty() && !productionDone) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        return students.values();
    }

    public synchronized void setProductionDone() {
        productionDone = true;
        notifyAll();
    }
}