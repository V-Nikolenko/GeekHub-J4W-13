package org.geekhub.learnit.consoleapi;

import java.util.Scanner;

public class MainMenu {

    private static final String MENU_OPTIONS = """
        Enter option:
        create
        get-last-created-student-scores
        get-specific-student-info
        average
        exit""";

    private final Scanner scanner;
    private final CreateStudentApi createStudentApi;
    private final GetStudentApi getStudentApi;

    public MainMenu(Scanner scanner,
                    CreateStudentApi createStudentApi,
                    GetStudentApi getStudentApi) {
        this.scanner = scanner;
        this.createStudentApi = createStudentApi;
        this.getStudentApi = getStudentApi;
    }

    public void printMenu() {
        boolean exit = false;
        while (!exit) {
            System.out.println(MENU_OPTIONS);

            switch (scanner.nextLine()) {
                case "create" -> createStudentApi.createStudent();
                case "get-last-created-student-scores" -> getStudentApi.printLastCreatedStudent();
                case "get-specific-student-info" -> getStudentApi.printSpecificStudent();
                case "average" -> getStudentApi.printAverageScores();
                case "exit" -> exit = true;
                default -> System.out.println("Ooops! Something went wrong :(");
            }
        }
    }
}
