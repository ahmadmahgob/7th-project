# Advanced Programming Final Project - Ahmed

## Student Info
- Name: Ahmed
- Student ID: [WRITE YOUR REAL STUDENT ID HERE]
- Course: CS244 / CC316 Advanced Programming Applications
- Submission Date: April 2026

## How to Run
1. Open the project in NetBeans (JDK 8).
2. Place the 3 input files inside the data/ folder:
   - students_1.txt
   - students_2.txt
   - students_3.txt
3. Run Main.java.
4. The report will automatically be generated in the reports/grade_report.txt folder.

## Features Implemented
- Full Object-Oriented Design (Encapsulation, Abstraction using GradeProcessor, Polymorphism, Association, Aggregation, Composition)
- 3 Custom Exceptions (InvalidDataException, InvalidGradeException, FileProcessingException)
- Multi-Threading (StudentFileReader extends Thread + GradeCalculatorTask implements Runnable)
- Thread synchronization using ThreadSafeGradeRepository (synchronized + wait/notify producer-consumer)
- ResourceManager with two locks showing commented deadlock + correct prevention
- File I/O using Scanner for input and PrintWriter for reports
- Exact required GRADE SUMMARY REPORT with histogram

## Sample Output
(After running Main.java you will see the report in reports/grade_report.txt)

## Challenges I Faced
- Coordinating multiple threads so the report generates only after all files are read
- Implementing wait() and notify() correctly in the repository
- Handling all exceptions (checked + unchecked) without crashing the program
- Following the exact folder structure required by the instructor

## GitHub Structure
- Used develop branch + feature branches (feature/oop-design, feature/multithreading, etc.)
- Minimum 5 meaningful commits following the required format
