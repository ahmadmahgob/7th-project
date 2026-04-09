package processor;
import exception.InvalidGradeException;
import java.util.List;

// Abstract class - Section IV
public abstract class GradeProcessor {
    
    protected void validateGrades(List<Double> grades) throws InvalidGradeException {
        if (grades == null) return;
        for (Double g : grades) {
            if (g == null || g < 0 || g > 100) {
                throw new InvalidGradeException("Grade must be between 0 and 100");
            }
        }
    }
    
    // Abstract method - Polymorphism
    public abstract String processGrades(List<model.Student> students);
}