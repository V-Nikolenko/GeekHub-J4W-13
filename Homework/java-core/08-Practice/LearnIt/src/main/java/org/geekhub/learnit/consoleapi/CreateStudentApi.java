package org.geekhub.learnit.consoleapi;

import org.geekhub.learnit.service.StudentService;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CreateStudentApi {

    private final Scanner scanner;
    private final StudentService studentService;

    public CreateStudentApi(Scanner scanner, StudentService studentService) {
        this.scanner = scanner;
        this.studentService = studentService;
    }

    public void createStudent() {
        String name = getInputName();
        Map<String, Double> scores = getInputScores();

        studentService.createStudent(name, scores);
    }

    private String getInputName() {
        System.out.println("Please enter student name.");
        return scanner.nextLine();
    }

    private Map<String, Double> getInputScores() {
        Map<String, Double> scores = new HashMap<>();
        while (true) {
            System.out.println("Enter subject.");
            String subjectName = scanner.nextLine();

            System.out.println("Please enter from 0-100 to set " + subjectName + " score.");
            double subjectScore = Double.parseDouble(scanner.nextLine());

            scores.put(subjectName, subjectScore);

            System.out.println("Do you want to add another subject? (y/n)");
            String answer = scanner.nextLine();
            if (answer.equalsIgnoreCase("n")) {
                break;
            }
        }
        return scores;
    }
}
