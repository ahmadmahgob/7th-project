import model.Course;
import model.Student;
import thread.ThreadSafeGradeRepository;
import thread.ResourceManager;
import io.FileInputService;
import io.ReportGenerator;
import java.util.Collection;

public class Main {
    public static void main(String[] args) {
        try {
            System.out.println("=== Starting Advanced Programming Project ===");

            Course course = new Course("CS244", "Advanced Programming");
            ThreadSafeGradeRepository repo = new ThreadSafeGradeRepository();
            
            String[] files = {
                "C:\\Users\\ABDULRHMAN\\Desktop\\7th project\\data\\students_1.txt",
                "C:\\Users\\ABDULRHMAN\\Desktop\\7th project\\data\\students_2.txt",
                "C:\\Users\\ABDULRHMAN\\Desktop\\7th project\\data\\students_3.txt"
            };

            System.out.println("Reading files using threads...");

            // Start readers and wait for each file reader to finish
            for (String file : files) {
                thread.StudentFileReader reader = new thread.StudentFileReader(file, repo);
                reader.start();
                reader.join();   // انتظر الملف ده يخلص تماماً
            }

            // الآن ننتظر شوية صغيرة جداً عشان آخر GradeCalculatorTask تخلص
            Thread.sleep(1000);
            repo.setProductionDone();

            Collection<Student> allStudents = repo.getAllStudents();
            System.out.println("Total students loaded: " + allStudents.size());

            for (Student s : allStudents) {
                course.addStudent(s);
            }

            System.out.println("Total students in Course: " + course.getStudents().size());

            // Generate Report
            ReportGenerator rg = new ReportGenerator();
            rg.generateSummaryReport(course);

            // Deadlock prevention demo
            ResourceManager rm = new ResourceManager();
            rm.safeUpdateGrades();

            System.out.println("\n=== PROJECT COMPLETED SUCCESSFULLY ===");
            System.out.println("✅ Report generated in reports/grade_report.txt");

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}