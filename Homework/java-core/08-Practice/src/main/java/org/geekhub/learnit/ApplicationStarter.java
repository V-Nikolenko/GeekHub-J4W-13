package org.geekhub.learnit;

import org.geekhub.learnit.model.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class ApplicationStarter {

    public static List<Student> students = new ArrayList<>();
    public static boolean isNotExit = false;
    public static Exception exception = null;
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Student student = null;
        Map<String, Double> scores = null;
        isNotExit = true;
        while (isNotExit) {
            System.out.println("Enter option: ");
            System.out.println("create");
            System.out.println("get-last-created-student-scores");
            System.out.println("get-specific-student-info");
            System.out.println("average");
            System.out.println("exit");
            switch (scanner.nextLine()) {
                case "create":
                    if (scores == null) {
                        scores = new HashMap<>();
                    } else {
                        scores.entrySet().removeIf(entry -> true);
                    }
                    System.out.println("Enter name:");
                    String name = scanner.nextLine();
                    while (isNotExit && exception == null) {
                        try {
                            System.out.println("Enter subject. Empty if exit");
                            String subjectName = scanner.nextLine();
                            if (subjectName.isEmpty()) {
                                throw new Exception("Empty subject name.");
                            } else {
                                System.out.println("Please enter from 0-100 to set " + subjectName  + " score.");
                                int subjectScore = Integer.parseInt(scanner.nextLine());
                                if (subjectScore > 0 && subjectScore <= 100) {
                                    scores.put(subjectName, (double) subjectScore);
                                } else {
                                    throw new Exception("Score is less than 0 or higher than 100");
                                }
                            }
                        } catch (Exception exception1) {
                            exception = exception1;
                        }
                    }

                    Student studentnew = new Student(name, new HashMap<>(scores));
                    student = studentnew;
                    students.add(studentnew);
                    exception = null;
                    break;
                case "get-last-created-student-scores":
                    if (student != null) {
                        StringBuilder string = new StringBuilder().append("Student: ").append(student.name()).append(" scores: ");
                        for (Map.Entry scores2 : scores.entrySet()) {
                            string.append("%nSubject :").append(scores2.getKey()).append(" scores: ").append(scores2.getValue());
                        }
                        System.out.println(string.toString());
                    } else {
                        try {
                            throw new Exception("No students in the list");
                        } catch (Throwable e) {
                            isNotExit = false;
                            System.out.println("Error!");
                        }
                    }
                    break;
                case "get-specific-student-info":
                    System.out.println("In which student you're interested in?");
                    Integer i = 0;
                    int i1 = 0;
                    for (Student student1 : students) {
                        String string = new StringBuilder().append("Student: ").append(student1.name()).append(" index: ").append(i++).toString();
                        System.out.println(string);
                    }

                    System.out.println("Enter index:");
                    i1 = Integer.parseInt(scanner.nextLine());
                    Student student1 = students.get(i1);
                    if (student1 != null) {
                        StringBuilder string = new StringBuilder().append("Student: ").append(student1.name()).append(" scores: ");
                        for (Map.Entry scores2 : student1.scores().entrySet()) {
                            string.append("%nSubject :").append(scores2.getKey()).append(" scores: ").append(scores2.getValue());
                        }
                        System.out.println(string.toString());
                    } else {
                        try {
                            throw new Exception("No students in the list");
                        } catch (Throwable e) {
                            isNotExit = false;
                            System.out.println("Error!");
                        }
                    }
                    exception = null;
                    break;
                case "average":
                    if (students.size() > 0) {
                        for (Student student2 : students) {
                            Map<String, Double> scores3 = student2.scores();

                            double averageScore = scores3.values().stream()
                                .mapToDouble(Double::doubleValue)
                                .average()
                                .orElse(0);
                            System.out.println("Student " + student2.name() + " score is " + averageScore);
                        }
                        break;
                    }

                    System.out.println("No student found!");
                    break;
                case "exit":
                    isNotExit = false;
                default:
                    System.out.println("Ooops! Something went wrong :(");

            }
        }
        scanner = null;
    }
}
