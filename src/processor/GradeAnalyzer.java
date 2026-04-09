package processor;
import model.Student;
import exception.InvalidGradeException;
import java.util.List;

// Inheritance + Polymorphism (Section IV)
public class GradeAnalyzer extends GradeProcessor {
    
    @Override
    public String processGrades(List<Student> students) {
        try {
            if (students == null || students.isEmpty()) {
                return "No students to process";
            }
            
            double highest = Double.MIN_VALUE;
            double lowest = Double.MAX_VALUE;
            double sum = 0.0;
            
            for (Student s : students) {
                double avg = s.calculateAverage();
                sum += avg;
                if (avg > highest) highest = avg;
                if (avg < lowest) lowest = avg;
            }
            
            double average = sum / students.size();
            
            // Standard deviation
            double sumSq = 0.0;
            for (Student s : students) {
                double diff = s.calculateAverage() - average;
                sumSq += diff * diff;
            }
            double stdDev = Math.sqrt(sumSq / students.size());
            
            return String.format("Highest: %.2f | Lowest: %.2f | Average: %.2f | StdDev: %.2f",
                    highest, lowest, average, stdDev);
                    
        } catch (Exception e) {
            return "Error processing grades: " + e.getMessage();
        }
    }
}