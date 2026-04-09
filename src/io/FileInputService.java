package io;
import model.Student;
import exception.FileProcessingException;
import exception.InvalidDataException;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class FileInputService {

    public static ArrayList<Student> readStudentFile(String filename) throws FileProcessingException {
        ArrayList<Student> students = new ArrayList<>();
        
        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split(",");
                if (parts.length < 3) continue;

                String id = parts[0].trim();
                String name = parts[1].trim();
                ArrayList<Double> grades = new ArrayList<>();

                for (int i = 2; i < parts.length; i++) {
                    try {
                        double g = Double.parseDouble(parts[i].trim());
                        grades.add(g);
                    } catch (NumberFormatException e) {
                        throw new FileProcessingException("Invalid grade format in " + filename, e);
                    }
                }

                try {
                    students.add(new Student(id, name, grades));
                } catch (InvalidDataException e) {
                    throw new FileProcessingException("Invalid student data in " + filename, e);
                }
            }
        } catch (java.io.IOException e) {
            throw new FileProcessingException("Cannot read file: " + filename, e);
        }
        return students;
    }

    public static void readMultipleFiles(String[] filenames, thread.ThreadSafeGradeRepository repo) {
        for (String file : filenames) {
            thread.StudentFileReader reader = new thread.StudentFileReader(file, repo);
            reader.start();
        }
    }
}