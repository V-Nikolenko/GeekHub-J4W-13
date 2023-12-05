package org.geekhub.learnit.consoleapi;

import org.geekhub.learnit.model.Student;
import org.geekhub.learnit.service.StudentService;

import java.util.List;
import java.util.Scanner;

public class GetStudentApi {

    private final Scanner scanner;
    private final StudentService studentService;

    public GetStudentApi(Scanner scanner,
                         StudentService studentService) {
        this.scanner = scanner;
        this.studentService = studentService;
    }

    public void printLastCreatedStudent() {
        Student student = studentService.getLastCreatedStudent();
        if (student == null) {
            System.out.println("No student found!");
            return;
        }

        System.out.println("Last created student is " + student);
    }

    public void printSpecificStudent() {
        System.out.println("In which student you're interested in?");
        List<Student> students = studentService.getStudents();
        for (int index = 0; index < students.size(); index++) {
            String name = students.get(index).name();
            System.out.println(index + " - " + name);
        }

        int index = Integer.parseInt(scanner.nextLine());

        Student student = studentService.getStudent(index);
        if (student == null) {
            System.out.println("No student found!");
            return;
        }

        System.out.println(student);
    }

    public void printAverageScores() {
        List<Student> students = studentService.getStudents();
        if (students.isEmpty()) {
            System.out.println("No student found!");
            return;
        }

        for (Student student : students) {
            double averageScore = student.getAverageScore();
            System.out.println("Student " + student.name() + " score is " + averageScore);
        }
    }
}
